import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser
{
    String file_name;
    String current_command;
    File inputFile;
    Scanner dataReader;
    String commandType;

    Parser(String fileName)
    {
        this.current_command="";
        //opens file
        String filepath ="../nand2tetris/projects/06/" + fileName;
        try
        {
            this.inputFile = new File(filepath);
            this.file_name = inputFile.getName();
            this.dataReader = new Scanner(inputFile);  
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error occurred");
        }
    }

    public boolean hasMoreCommands()
    {
        return this.dataReader.hasNextLine();
    }

    public void advance()
    {
        this.current_command= this.dataReader.nextLine();
        this.current_command = this.current_command.replaceAll("\\s+","");
        if(this.current_command.startsWith("//"))
        {
            this.advance();
        }
        else if(this.current_command.equals(""))
        {
            this.advance();
        }
        else return;
    }

    public String command_type()
    {
        if(this.current_command.startsWith("@"))
        {
            this.commandType="A_COMMAND";
        }
        else if(this.current_command.startsWith("(") && this.current_command.endsWith(")"))
        {
            this.commandType="L_COMMAND";
        }
        else
        {
            this.commandType="C_COMMAND";
        }
        return this.commandType;
    }

    public String symbol()
    {
        if(this.commandType.equals("A_COMMAND"))
        {
            return this.current_command.substring(1);
        }
        else
        {
            return this.current_command.substring(this.current_command.indexOf("(") + 1, this.current_command.indexOf(")"));
        }
    }

    public String dest()
    {
        if(this.commandType.equals("C_COMMAND"))
        {
            String dest_field="";
            if(this.current_command.contains("="))
            {
                dest_field = this.current_command.substring(0, this.current_command.indexOf("="));
            }
            return dest_field;
        }
        else return "";
    }

    public String comp()
    {
        if(this.commandType.equals("C_COMMAND"))
        {
            String comp_field="";
            if(this.current_command.contains("="))  //dest=comp;jmp or dest=comp
            {
                if(this.current_command.contains(";"))
                {
                    //dest=comp;jmp
                    comp_field = this.current_command.substring(this.current_command.indexOf("=") + 1, this.current_command.indexOf(";"));
                }
                else
                {
                    //dest=comp
                    comp_field = this.current_command.substring(this.current_command.indexOf("=") + 1);
                }
            }
            else    //comp;jmp
            {
                if(this.current_command.contains(";"))
                {
                    comp_field = this.current_command.substring(0, this.current_command.indexOf(";"));
                }
                else
                {
                    comp_field = this.current_command;
                }
            }
            return comp_field;
        }
        else return "";
    }

    public String jump()
    {
        if(this.commandType.equals("C_COMMAND"))
        {
            String jump_field="";
            if(this.current_command.contains(";"))
            {
                jump_field = this.current_command.substring(this.current_command.indexOf(";") + 1);
            }
            return jump_field;
        }
        else return "";
    }
}