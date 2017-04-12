package server;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.io.*;

public class ServerTest {
	
	public static HashMap<Socket, ProtocolTest> clients = new HashMap<Socket, ProtocolTest>();
	public static ArrayList<Thread> clientThreads = new ArrayList<Thread>();
	
	public static ReentrantReadWriteLock clientAcceptLock = new ReentrantReadWriteLock();
	
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java ServerTest <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portNumber);
        
        while(true){
			try {
				
				clientAcceptLock.writeLock().lock();
				
				ProtocolTest prot = new ProtocolTest();
				Socket clientSocket = serverSocket.accept();
				clients.put(clientSocket, prot);
				
				clientThreads.add(new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							
							clientAcceptLock.readLock().lock();
							
							PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
							BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							String outputLine = prot.processInput(null);
				            out.println(outputLine);

				            String inputLine;
				            while ((inputLine = in.readLine()) != null && !outputLine.equals("Bye.")) {
				                outputLine = prot.processInput(inputLine);
				                out.println(outputLine);
				            }
						} catch (IOException e) {
							System.out.println("Exception caught when trying to listen on port "
					                + portNumber + " or listening for a connection");
							e.printStackTrace();
						}
					}
				}));
				

				clientAcceptLock.writeLock().unlock();
				
			} catch (IOException e) {
				serverSocket.close();
				e.printStackTrace();
			}
		}
        
        /*try {
			clientAccept.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        clientAccept.interrupt();
        
        for(Thread t : clientThreads){
        	t.interrupt();
        }
        
        serverSocket.close();*/
    }
}
