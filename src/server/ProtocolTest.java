package server;

import data.HashData;
import data.IntegerData;
import data.ListData;
import data.SSetData;
import data.SetData;

public class ProtocolTest {
    private static final int WAITING = 0;
    private static final int WAITDATA = 1;
    private static final int WAITCMD = 2;

    private int state = WAITING;

    private String[] help = {"h", "help"};
    private String[] quit = {"q", "quit", "exit"};
    private String[] structure = {"int", "list", "set", "ssets", "hash"};
    private String[] cmd = {"SET", "GET", "HELP", "EXPIRE", "TTL" };
    private String[] listCmd = {"RPUSH", "LPUSH", "LRANGE", "LLEN", "LPOP", "RPOP"};
    private String[] setCmd = {"SADD", "SREM", "SISMEMBER", "SMEMBERS", "SUNION", "ZADD"};
    private String[] hashCmd = {"HSET", "HGETALL", "HGET"};
    
    private IntegerData intData = new IntegerData();
    private ListData listData = new ListData();
    private SetData setData = new SetData();
    private SSetData ssetData = new SSetData();
    private HashData hashData = new HashData();
    
    public String processInput(String theInput) {
        String theOutput = null;
        
        if (state == WAITING) {
            theOutput = "Waiting structure mode ";
            state = WAITDATA;
        } 
        
        else if(state == WAITDATA)
        {
            if(isQuit(theInput)) theOutput = "Bye.";
            else if(isHelp(theInput)) theOutput = "Type help CMD to know more about a command." ;
        	
            else if(isData(theInput))
        	{
        		theOutput = "Enter in mode "+ theInput;
        		
        		state = WAITCMD;
        	}
            
            else theOutput = "Invalid structure ";
            
        }
        
        else if (state == WAITCMD) 
        {
        	String[] command = theInput.split("[ ]");             
            theInput = command[0];
        	      	
            if(isQuit(theInput))
            {
            	theOutput = "Go back to structure choice ";
            	state = WAITING;
            }
            
            else if(isHelp(theInput)) theOutput = "Type help CMD to know more about a command." ;
            
            else if (isCmd(theInput)) {
                theOutput = "Waiting response of the treating data class";        
                state = WAITING;
            } 
            
            else theOutput = "Invalid command ";

        }
        
        return theOutput;
    }

	private boolean isData(String theInput) {
		boolean res = false;
		for(int i=0; i<structure.length && !res; i++) {if(structure[i].equalsIgnoreCase(theInput)) res = true ; }
		return res;
	}

	private boolean isHelp(String theInput) {
		boolean res = false;
		for(int i=0; i<help.length && !res; i++) {if(help[i].equalsIgnoreCase(theInput)) res = true ; }
		return res;
	}

	private boolean isQuit(String theInput) {
		boolean res = false;
		for(int i=0; i<quit.length && !res; i++) {if(quit[i].equalsIgnoreCase(theInput)) res = true ; }
		return res;
	}

	private boolean isCmd(String theInput) {
		boolean res = false;
		for(int i=0; i<cmd.length && !res; i++) {if(cmd[i].equalsIgnoreCase(theInput)) res = true ; }
		for(int i=0; i<listCmd.length && !res; i++) { if(listCmd[i].equalsIgnoreCase(theInput)) res = true ; }
		for(int i=0; i<setCmd.length && !res; i++) { if(setCmd[i].equalsIgnoreCase(theInput)) res = true ; }
		for(int i=0; i<hashCmd.length && !res; i++) { if(hashCmd[i].equalsIgnoreCase(theInput)) res = true ; }

		return res;
	}
}