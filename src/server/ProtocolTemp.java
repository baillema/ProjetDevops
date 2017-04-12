package server;

import data.IntegerData;
import data.ListData;
import data.SSetData;
import data.SetData;

public class ProtocolTemp {
    private static final int WAITING = 0;
    private static final int WAITDATA = 1;
    private static final int WAITCMD = 2;
    
    public String dataState = "null";

    private int state = WAITING;
    
    private String[] help = {"h", "help"};
    private String[] quit = {"q", "quit", "exit"};
    private String[] structure = {"int", "list", "set", "sset"};
    private String[] cmd = {"SET", "GET", "HELP", "INCR", "REM"};
    
    private IntegerData intData = new IntegerData();
    private ListData listData = new ListData();
    private SetData setData = new SetData();
    private SSetData ssetData = new SSetData();
    
    public String processInput(String theInput) {
        String theOutput = null;
        
        if (state == WAITING) theOutput = waiting();    
        else if(state == WAITDATA) theOutput = waitData(theInput);    
        else if (state == WAITCMD) theOutput = waitCmd(theInput);
        
        return theOutput;
    }

	// Different states
	private String waiting() {
        state = WAITDATA;
        return "Waiting structure mode ";
	}

	private String waitData(String theInput) {
    	String theOutput;
    	if(isQuit(theInput)) theOutput = "Bye.";
        else if(isHelp(theInput)) theOutput = "Type help CMD to know more about a command." ;      	
        else if(isData(theInput))
    	{
    		theOutput = "Enter in mode "+ theInput;
    		dataState = theInput;
    		state = WAITCMD;      		
    	}
        
        else theOutput = "Invalid structure "; 
    	
    	return theOutput;
	}

    private String waitCmd(String theInput)
    {
    	String theOutput;
	  	String[] command = theInput.split("[ ]");

	  	theInput = command[0];
  	      	
	    if(isQuit(theInput))
	    {
	    	theOutput = "Go back to structure choice ";
	    	state = WAITING;
	    }
      
	    else if(isHelp(theInput)) theOutput = "Type help CMD to know more about a command." ;
	    else if (isCmd(theInput)) 
	    {
	    	if(isValidName(command[1]))
	    	{
	    		String name = command[1];
	    		
	    		switch(dataState)
	    		{
	    		case "int" :
	    			theOutput = cmdInt(command, name);
		    		break;
	    		case "list" :
	    			theOutput = cmdList(command, name);
		    		break;
	    		case "set" :
	    			theOutput = cmdSet(command, name);
	    			break;
	    		case "sset" :
	    			theOutput = cmdSSet(command, name);
	    			break;
	    		default :
	    			theOutput = "NOT OK";
	    			break;
	    		}
	    	}
	    	else theOutput = "Invalid command "+dataState;
	    	
	    	state = WAITCMD;
	    }
	    else theOutput = "Invalid command "+dataState;
	  	
	    return theOutput;
	}

	// Command of the different state of structure
    private String cmdInt(String[] command, String name) {   	
    	String theOutput;
    	
    	switch(command[0].toLowerCase())
    	{
    	case "set" :
    		if(isValideValue(command[2], dataState))
			{
				intData.set(name,Integer.parseInt(command[2]));
				theOutput = "OK";
			}
			else theOutput = "Invalid value";
    		break;
    	case "get" :
    		if(intData.isPresent(name)) theOutput = name+" "+(int) intData.get(name);
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "incr" :
    		if(intData.isPresent(name)) theOutput = name+" "+(int) intData.incr(name);
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "rem" :
    		if(intData.isPresent(name))
    		{
    			intData.remove(name);
    			theOutput = name+" successfuly removed";
    		}
    		else theOutput = name+" not present in this scope.";    		
    		break;
    	default :
    		theOutput = "Invalid command";
    		break;
    	}
    	 
    	return theOutput;
	}
    
    private String cmdList(String[] command, String name) {
    	String theOutput;
    	
    	switch(command[0].toLowerCase())
    	{
    	case "set" :
    		if(isValideValue(command[2], dataState))
			{
    			listData.set(name,command[2]);
				theOutput = "OK";
			}
			else theOutput = "Invalid value";
    		break;
    	case "get" :
    		if(listData.isPresent(name)) theOutput = name+" "+listData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "rem" :
    		if(listData.isPresent(name))
    		{	
    			listData.remove(name);
    			theOutput = name+" successfuly removed";
    		}
    		else theOutput = name+" not present in this scope.";
    	default :
    		theOutput = "Invalid command";
    		break;
    	}
    	
		return theOutput;
	}

	private String cmdSet(String[] command, String name) {
		String theOutput;
		switch(command[0].toLowerCase())
    	{
    	case "set" :
    		if(isValideValue(command[2], dataState))
			{
    			setData.set(name,command[2]);
				theOutput = "OK";
			}
			else theOutput = "Invalid value";
    		break;
    	case "get" :
    		if(setData.isPresent(name)) theOutput = name+" "+setData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "rem" :
    		if(setData.isPresent(name))
    		{
    			setData.remove(name);
    			theOutput = name+" successfuly removed";
    		}
    		else theOutput = name+" not present in this scope.";
    		break;
    	default :
    		theOutput = "Invalid command";
    		break;
    	}
		return theOutput;
	}

	private String cmdSSet(String[] command, String name) {
		String theOutput;
		switch(command[0].toLowerCase())
    	{
    	case "set" :
    		if(isValideValue(command[2], dataState))
			{
    			ssetData.set(name,command[2]);
				theOutput = "OK";
			}
			else theOutput = "Invalid value";
    		break;
    	case "get" :
    		if(ssetData.isPresent(name)) theOutput = name+" "+ssetData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "rem" :
    		if(ssetData.isPresent(name))
    		{
    			ssetData.remove(name);
    			theOutput = name+" successfuly removed";
    		}
    		else theOutput = name+" not present in this scope.";
    		break;
    	default :
    		theOutput = "Invalid command";
    		break;
    	}
		return theOutput;
	}
	
	// Miscellaneous tests
	private boolean isValideValue(String string, String data) {
		boolean res;
		switch(data)
		{
		case "int" :
			try  
			{  
				Integer.parseInt(string);
				res = true;
			}
			catch(NumberFormatException nfe){ res = false; }
			break;
		case "list" :
			if(string.endsWith("\"") && string.startsWith("\"")) res = true;
			else res = false;
			break;		
		case "set" :
			if(string.endsWith("\"") && string.startsWith("\"")) res = true;
			else res = false;
			break;
		case "sset" :
			if(string.endsWith("\"") && string.startsWith("\"")) res = true;
			else res = false;
			break;
		default :
			res = false;
			break;
						
		}
		return res;
	}
		
	private boolean isValidName(String string) { 
		return !Character.isDigit(string.charAt(0));		
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
	
		return res;
	}
}