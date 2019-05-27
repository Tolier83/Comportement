package main;
import java.net.*; 
import java.io.*; 

public class CServer
{ 
	//init
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private DataInputStream inputTram	 = null; 

	public CServer(int port) 
	{ 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Waiting for a client ..."); 
			socket = server.accept(); 
			System.out.println("Client accepted"); 

			// takes input from the client socket 
			inputTram = new DataInputStream( 
				new BufferedInputStream(socket.getInputStream())); 

			String line = ""; 

			while (!line.equals("Fin")) 
			{ 
				try
				{ 
					line = inputTram.readUTF(); 
					System.out.println(line); 
					
				} 
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			inputTram.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) 
	{ 
		CServer server = new CServer(41000); 
	} 
} 
