import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HackAssembler
{
    public static void main(String[] args) 
    {
        Parser obj = new Parser(args[0]);
        Code binary_code = new Code();
        
        String output_file_path = "../nand2tetris/projects/06/outputs/" + obj.file_name.replaceAll("asm","hack");

        try
        {
            File output_file = new File(output_file_path);
            FileWriter fr = new FileWriter(output_file, false);
            while(obj.hasMoreCommands())
            {
                obj.advance();
                //String curr_command = obj.current_command;
                String binary_command ="";
                if(obj.command_type().equals("A_COMMAND"))
                {
                    binary_command = "0" + binary_code.decimalToBinary(obj.symbol()) + "\n";
                }
                else if(obj.command_type().equals("C_COMMAND"))
                {
                    binary_command = "111" + binary_code.compBinary(obj.comp()) + binary_code.destBinary(obj.dest()) + binary_code.jumpBinary(obj.jump()) +"\n";
                }
                fr.write(binary_command);
                //System.out.println(obj.current_command);
            }
            fr.close();
        }
        catch(IOException e)
        {
            System.out.println("Error occurred");
        } 
    }
}