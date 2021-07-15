import java.util.*;

public class Code 
{
    Map<String, String> dest_map = new HashMap<String, String>();
    Map<String, String> comp_map = new HashMap<String, String>();
    Map<String, String> jump_map = new HashMap<String, String>();
    
    Code()
    {
        //Lookup Table
        // Dest Field Map
        dest_map.put("M", "001");
        dest_map.put("D", "010");
        dest_map.put("MD", "011");
        dest_map.put("A", "100");
        dest_map.put("AM", "101");
        dest_map.put("AD", "110");
        dest_map.put("AMD", "111");
        dest_map.put("","000");

        //Jump Field Map
        jump_map.put("JGT", "001");
        jump_map.put("JEQ", "010");
        jump_map.put("JGE", "011");
        jump_map.put("JLT", "100");
        jump_map.put("JNE", "101");
        jump_map.put("JLE", "110");
        jump_map.put("JMP", "111");
        jump_map.put("","000");

        //Comp Field Map
        comp_map.put("0",  "101010");
        comp_map.put("1",  "111111");
        comp_map.put("-1", "111010");
        comp_map.put("D",  "001100");
        comp_map.put("A",  "110000");
        comp_map.put("!D", "001101");
        comp_map.put("!A", "110001");
        comp_map.put("-D", "001111");
        comp_map.put("-A", "110011");
        comp_map.put("D+1", "011111");
        comp_map.put("A+1", "110111");
        comp_map.put("D-1", "001110");
        comp_map.put("A-1", "110010");
        comp_map.put("D+A", "000010");
        comp_map.put("D-A", "010011");
        comp_map.put("A-D", "000111");
        comp_map.put("D&A", "000000");
        comp_map.put("D|A", "010101");
    }
    
    public String destBinary(String dest_mnemonic)
    {
        return dest_map.get(dest_mnemonic);
    }

    public String compBinary(String comp_mnemonic)
    {
        String comp_bits = "";
        if(comp_mnemonic.contains("M"))
        {
            comp_mnemonic = comp_mnemonic.replaceAll("M","A");
            comp_bits = "1" + comp_map.get(comp_mnemonic);
        }
        else
        {
            comp_bits = "0" + comp_map.get(comp_mnemonic);
        }
        return comp_bits;
    }

    public String jumpBinary(String jump_mnemonic)
    {
        return jump_map.get(jump_mnemonic);
    }

    public String decimalToBinary(String value)
    {
        int decimal = Integer.valueOf(value);
        String binary_string = Integer.toBinaryString(decimal);

        //add leading zeroes to make it 15 bits wide
        String zeroes ="000000000000000";
        binary_string = zeroes.substring(binary_string.length(),zeroes.length()) + binary_string;
        
        return binary_string;
    }
}