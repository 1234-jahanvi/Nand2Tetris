import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HackAssembler
{
    public static void main(String[] args) 
    {
        Code binary_code = new Code();
        SymbolTable symbol_table = new SymbolTable();

        //First Pass
        int command_number = -1;
        Parser firstPass = new Parser(args[0]);
        while(firstPass.hasMoreCommands())
        {
            firstPass.advance();
            if(firstPass.command_type().equals("L_COMMAND"))
            {
                String label_name = firstPass.symbol();
                if(!symbol_table.find(label_name))
                {
                    symbol_table.add(label_name,command_number+1);
                }
            }
            else 
            {
                command_number = command_number + 1;
            }
        }
        
        //Second Pass
        Parser obj = new Parser(args[0]);
        String output_file_path = "../nand2tetris/projects/06/outputs/" + obj.file_name.replaceAll("asm","hack");
        int n = 16;
        try
        {
            File output_file = new File(output_file_path);
            FileWriter fr = new FileWriter(output_file, false);
            while(obj.hasMoreCommands())
            {
                obj.advance();
                String binary_command ="";
                if(obj.command_type().equals("A_COMMAND"))
                {
                    //A-instruction = @xxx
                    String xxx = obj.symbol();
                    try
                    {
                        //Runs if the xxx is a decimal
                        Integer.parseInt(xxx);
                        binary_command = "0" + binary_code.decimalToBinary(xxx) + "\n";
                    }
                    catch(NumberFormatException e)
                    {
                        //Runs when xxx is not a decimal but a variable or label
                        if(symbol_table.find(xxx))
                        {
                            String symbol_value = Integer.toString(symbol_table.symbolValue(xxx));
                            binary_command = "0" + binary_code.decimalToBinary(symbol_value) + "\n";
                        }
                        else
                        {
                            symbol_table.add(xxx,n);
                            String symbol_value = Integer.toString(n);
                            binary_command = "0" + binary_code.decimalToBinary(symbol_value) + "\n";
                            n=n+1;
                        }
                    }
                    fr.write(binary_command);
                }
                else if(obj.command_type().equals("C_COMMAND"))
                {
                    binary_command = "111" + binary_code.compBinary(obj.comp()) + binary_code.destBinary(obj.dest()) + binary_code.jumpBinary(obj.jump()) +"\n";
                    fr.write(binary_command);
                }
            }
            fr.close();
        }
        catch(IOException e)
        {
            System.out.println("Error occurred");
        } 
    }
}