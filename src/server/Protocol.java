package server;

import data.IntegerData;
import data.ListData;
import data.SSetData;
import data.SetData;

public class Protocol {
    private static final int WAITING = 0;
    private static final int WAITDATA = 1;
    private static final int WAITCMD = 2;
    
    public String dataState = "null";

    private int state = WAITING;
    
    private String[] help = {"h", "help"};
    private String[] quit = {"q", "quit", "exit"};
    private String[] structure = {"int", "list", "set", "sset"};
    private String[] cmd = {"SET", "GET", "HELP", "INCR", "DEL", "DELELMT"};
    
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
  	    
	  	if(isHelp(theInput)) theOutput = "Type help CMD to know more about a command." ;
	  	else if(isQuit(theInput))
	    {
	    	theOutput = "Go back to structure choice ";
	    	state = WAITDATA;
	    }
	    else if (isCmd(theInput)) 
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
    		if(command.length==3)
    		{
    			if(intData.set(name, command[2])) theOutput = "OK";
    			else theOutput = "Invalid value";
    		}
    		else theOutput = "Problem in number of argument";
    		break;
    	case "get" :
    		theOutput = name+" "+(int) intData.get(name);
    		break;
    	case "incr" :
    		if(intData.incr(name)) theOutput = "Increment done";
    		else theOutput = "Value not present";
    		break;
    	case "del" :
    		if(intData.remove(name)) theOutput = name+" successfuly removed";
    		else theOutput = "Value not present";
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
    		if(command.length==3)
    		{	
    			if(listData.set(name,command[2])) theOutput = "OK";
				else theOutput = "Invalid value";
    		}
    		else theOutput = "Problem in number of argument";
    		break;
    	case "get" :
    		if(listData.isPresent(name)) theOutput = name+" "+listData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "del" :
    		if(listData.remove(name)) theOutput = name+" successfuly removed";
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "delelmt" :
    		if(listData.removeElmt(name, command[2])) theOutput = command[2]+" successfuly removed";
    		else theOutput = command[2]+" not present in this scope.";
    		break;
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
    		if(command.length==3)
    		{
	    		if(setData.set(name,command[2]))	theOutput = "OK";
				else theOutput = "Invalid value";
    		}
    		else theOutput = "Problem in number of argument";
    		break;
    	case "get" :
    		if(setData.isPresent(name)) theOutput = name+" "+setData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "del" :
    		if(setData.remove(name)) theOutput = name+" successfuly removed";
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "delelmt" :
        		if(setData.removeElmt(name, command[2])) theOutput = command[2]+" successfuly removed";
        		else theOutput = command[2]+" not present in this scope.";
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
    		if(command.length==3)
    		{
    			if(ssetData.set(name,command[2])) theOutput = "OK";
    			else theOutput = "Invalid value";
    		}
    		else theOutput = "Problem in number of argument";
    		break;
    	case "get" :
    		if(ssetData.isPresent(name)) theOutput = name+" "+ssetData.get(name).toString();
    		else theOutput = name+" not present in this scope.";
    		break;
    	case "del" :
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