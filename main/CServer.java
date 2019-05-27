package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector; 

public class CServer
{ 

	private int m_nbClient =0 ; //nb Client Connecter
	public ArrayList<PrintWriter> m_TabClient; //TabClient
	public int clientDelete = 0;
	
	public static void main(String args[]) 
	{ 
		CServer server = new CServer();
		server.m_TabClient = new ArrayList<PrintWriter>();
		try
		{
			Integer port = 40000;
			new CommandesServ(server);
			ServerSocket ss = null;
			try
			{
				ss = new ServerSocket(port);
				printBienvenue(port);
			} catch (UnknownHostException e) 
			{
			    e.printStackTrace();
			} catch (IOException e) 
			{
			   e.printStackTrace();
			}
			
			while(true)
			{
				new ServerThread(ss.accept(),server);
			}
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	static private void printBienvenue(Integer port)
	{
		System.out.println("Ouai Bienvenue");
	}
	
	synchronized public void sendAll(String message,String sLast)
	{
		System.out.println("sendall");
		PrintWriter out;
		for(int i = 0; i<m_TabClient.size();i++)
		{
			out = m_TabClient.get(i);
			if(out != null)
			{
				out.print(message + sLast);
				out.flush();
			}
		}
	}
	
	
	  synchronized public void delClient(int i)
	  {
		  m_nbClient--; // del client
		  clientDelete++;
		  
		  System.out.println(i);
	    if (m_TabClient.get(i) != null) 
	    {
	    	m_TabClient.remove(i);
	    }
	  }

	  synchronized public int addClient(PrintWriter out)
	  {
		  m_nbClient++; 
		  m_TabClient.add(out); 
	    return m_TabClient.size()-1; // on retourne le numéro du client ajouté (size-1)
	  }

	  synchronized public int getNbClients()
	  {
	    return m_nbClient; // retourne le nombre de clients connectés
	  }
} 
