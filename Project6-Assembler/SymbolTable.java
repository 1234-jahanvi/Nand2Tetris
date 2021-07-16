import java.util.*;

public class SymbolTable 
{
    Map<String, Integer> symbol_table= new HashMap<String, Integer>();

    SymbolTable()
    {
        //Initializing Pre-defined symbols
        this.symbol_table.put("R0",0);
        this.symbol_table.put("R1",1);
        this.symbol_table.put("R2",2);
        this.symbol_table.put("R3",3);
        this.symbol_table.put("R4",4);
        this.symbol_table.put("R5",5);
        this.symbol_table.put("R6",6);
        this.symbol_table.put("R7",7);
        this.symbol_table.put("R8",8);
        this.symbol_table.put("R9",9);
        this.symbol_table.put("R10",10);
        this.symbol_table.put("R11",11);
        this.symbol_table.put("R12",12);
        this.symbol_table.put("R13",13);
        this.symbol_table.put("R14",14);
        this.symbol_table.put("R15",15);
        this.symbol_table.put("SCREEN",16384);
        this.symbol_table.put("KBD",24576);
        this.symbol_table.put("SP",0);
        this.symbol_table.put("LCL",1);
        this.symbol_table.put("ARG",2);
        this.symbol_table.put("THIS",3);
        this.symbol_table.put("THAT",4);
    }

    public void add(String key, int value)
    {
        this.symbol_table.put(key, value);
    }

    public boolean find(String key)
    {
        return this.symbol_table.containsKey(key);
    }

    public Integer symbolValue(String key)
    {
        return this.symbol_table.get(key);
    }
}