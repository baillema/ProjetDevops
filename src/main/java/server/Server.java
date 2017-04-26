package server;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.io.*;

public class Server {
	
	public static HashMap<Socket, Protocol> clients = new HashMap<Socket, Protocol>();
	public static ArrayList<Thread> clientThreads = new ArrayList<Thread>();
	
	public static ReentrantReadWriteLock clientAcceptLock = new ReentrantReadWriteLock();
	
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
				Thread t = new Thread(new ServerThread(portNumber, prot, clientSocket, clientAcceptLock));
				clientThreads.add(t);
				
				t.start();
				
			} catch (IOException e) {
				serverSocket.close();
				e.printStackTrace();
			}
		}while(!clientThreads.isEmpty());
        
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
