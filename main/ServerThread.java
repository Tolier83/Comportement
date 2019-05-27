
package main;

import java.net.*;
import java.io.*;

class ServerThread implements Runnable
{
  private Thread m_t; 
  private Socket m_s; 
  private PrintWriter m_out; // flux de sortie
  private BufferedReader m_in; // flux d'entr�e
  private CServer m_CServer2; //classe principale
  private int m_numClient=0; // num�ro de client g�r� par ce thread

  ServerThread(Socket s, CServer blablaServ) 
  {
	  m_CServer2 =blablaServ;
	  m_s=s; 
    try
    {
      m_out = new PrintWriter(m_s.getOutputStream());
      m_in = new BufferedReader(new InputStreamReader(m_s.getInputStream()));
      m_numClient = blablaServ.addClient(m_out);
    }
    catch (IOException e){ }

    m_t = new Thread(this); 
    m_t.start(); 
  }

  public void run()
  {
    String message = ""; 
    //String message1 = ""; 
  
    System.out.println("Un nouveau client s'est connecte, no "+m_numClient);
    try
    {
    	
      char charCur[] = new char[1]; 
      //message1= m_in.readLine();
      
      while(m_in.read(charCur, 0, 1)!=-1) 
      {
    	  
        if (charCur[0] != '\u0000' && charCur[0] != '\n' && charCur[0] != '\r') // "\u0000 " == null
                message += charCur[0];
        else //if(!message.equalsIgnoreCase("")) 
        {
        	System.out.println(charCur[0]);
          if(charCur[0]=='\u0000'&& charCur[0] == '\n' && charCur[0] == '\r')
        	  m_CServer2.sendAll(message,""+charCur[0]);
          else 
        	  m_CServer2.sendAll(message,""); 
          
          message = ""; 
        }
        
        System.out.println(message);
      }
      //System.out.println(message);
    }
    catch (Exception e){ }
    finally //deconnexion du client
    {
      try
      {
      	// on indique la deconnexion du client
        System.out.println("Le client no "+m_numClient+" s'est deconnecte");
        m_CServer2.delClient(m_numClient); // on supprime le client de la liste
        m_s.close(); // fermeture du socke
      }
      catch (IOException e){System.out.println(e); }
    }
  }
}