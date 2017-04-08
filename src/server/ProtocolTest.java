package Server;

public class ProtocolTest {
    private static final int WAITING = 0;
    private static final int WAITCMD = 1;
    private static final int WAITREP = 2;

    private int state = WAITING;

    private String[] help = {"h", "help"};
    private String[] quit = {"q", "quit", "exit"};
    private String[] cmd = {"SET", "GET", "HELP", "EXPIRE", "TTL" };
    private String[] listCmd = {"RPUSH", "LPUSH", "LRANGE", "LLEN", "LPOP", "RPOP"};
    private String[] setCmd = {"SADD", "SREM", "SISMEMBER", "SMEMBERS", "SUNION", "ZADD"};
    private String[] hashCmd = {"HSET", "HGETALL", "HGET"};
    
    public String processInput(String theInput) {
        String theOutput = null;
        
        if (state == WAITING) {
            theOutput = "Waiting command ";

            state = WAITCMD;
        } 
        
        else if (state == WAITCMD) 
        {
            if(isQuit(theInput)) theOutput = "Bye.";
            else if(isHelp(theInput)){ theOutput = "TODO : help" ;}
            
            else if (isCmd(theInput)) 
            {
                theOutput = "Good command ";
                state = WAITING;
            } 
            
            else theOutput = "Invalid command ";

        } 
        
        else if (state == WAITREP) 
        {
            theOutput = "waiting response of the treating data class";
        } 
        
        return theOutput;
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