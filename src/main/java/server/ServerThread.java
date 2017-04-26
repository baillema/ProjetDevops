package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This runnable class handles the communication between the client and the server.
 * 
 * @author Neliron
 *
 */

public class ServerThread implements Runnable {

	private Socket socket;
	private Protocol protocol;
	private int port;
	
	/**
	 * Create a new ServerThread.
	 * 
	 * @param port the port on which the server listens
	 * @param protocol the protocol used by the client
	 * @param clientSocket the socket of the client
	 */
	public ServerThread(int port, Protocol protocol, Socket clientSocket) {
	    this.port = port;
		this.protocol = new Protocol();
		this.socket = clientSocket;
	}
	
	/**
	 * Runs the thread.
	 * Firstly it sends a welcome message to the client and then it runs the communication.
	 */
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
