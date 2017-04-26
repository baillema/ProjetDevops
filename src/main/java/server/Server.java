package server;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

/**
 * This class accepts connections from clients and creates a Protocol instance and a ServerThread thread to manage the clients.
 * @author Neliron
 *
 */

public class Server {
	
	public static HashMap<Socket, Protocol> clients = new HashMap<Socket, Protocol>();
	public static ArrayList<Thread> clientThreads = new ArrayList<Thread>();
	
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java ServerTest <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portNumber);
        
        do{
			try {
				
				Protocol prot = new Protocol();
				Socket clientSocket = new Socket();
				clientSocket = serverSocket.accept();
				
				System.out.println("New client accepted !");
				
				clients.put(clientSocket, prot);
				Thread t = new Thread(new ServerThread(portNumber, prot, clientSocket));
				clientThreads.add(t);
				
				t.start();
				
			} catch (IOException e) {
				serverSocket.close();
				e.printStackTrace();
			}
		}while(!clientThreads.isEmpty());
    }
}
