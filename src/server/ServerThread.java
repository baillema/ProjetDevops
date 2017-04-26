package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServerThread implements Runnable {

	private ReentrantReadWriteLock clientAcceptLock;
	private Socket socket;
	private ProtocolTest protocol;
	private int port;
	
	public ServerThread(int port, ProtocolTest protocol, Socket clientSocket, ReentrantReadWriteLock lock) {
	    this.port = port;
		this.protocol = new ProtocolTest();
		this.socket = clientSocket;   
		this.clientAcceptLock = lock;
		
	}
	
	@Override
	public void run() {
		
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String outputLine = protocol.processInput(null);
			
			System.out.println("Server : " + outputLine);
			
            out.println(outputLine);
            
            String inputLine;
            while ((inputLine = in.readLine()) != null && !outputLine.equals("Bye.")) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
            }
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
	                + port + " or listening for a connection");
			e.printStackTrace();
		}
		
	}

}
