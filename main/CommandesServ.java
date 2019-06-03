package main;


import java.io.*;

class CommandesServ implements Runnable
{
	CServer m_Serv; 
	BufferedReader m_in; 
	String m_strCommande=""; 
	Thread m_t; 

	CommandesServ(CServer Serv)
  {
    m_Serv=Serv; 
    m_in = new BufferedReader(new InputStreamReader(System.in));
    m_t = new Thread(this); 
    m_t.start(); 
  }
  
  public void run()
  {
    try
    {
      while ((m_strCommande=m_in.readLine())!=null)
      {
        if (m_strCommande.equalsIgnoreCase("quit")) 
          System.exit(0); 
        else if(m_strCommande.equalsIgnoreCase("total")) 
        {
          System.out.println("Nombre de connectes : "+m_Serv.getNbClients());
          System.out.println("--------");
        }
        else
        {
          System.out.println("Cette commande n'est pas supportee");
          System.out.println("Quitter : \"quit\"");
          System.out.println("Nombre de connectes : \"total\"");
          System.out.println("--------");
        }
        System.out.flush();
      }
    }
    catch (IOException e) {System.out.println(e);}
  }
}