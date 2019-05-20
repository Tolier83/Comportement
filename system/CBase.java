package system;
import java.util.ArrayList;


public class CBase extends CObject{
	
	public int rayon = 10;
	public int nbAgents = 0;
	ArrayList<CAgent> fourmiz;
	
	public CBase(double _x, double _y, int _nbAgents)
	{
		this.posX = _x;
		this.posY = _y;
		this.nbAgents = _nbAgents; 
		this.fourmiz = new ArrayList<CAgent>();
		
		for(int i = 0; i < this.nbAgents;i++)
		{
			this.fourmiz.add(new CAgent(this.posX, this.posY));
		}
	}
	
	public bougerAgents()
	{	
		for(int cpt = 0; cpt < this.nbAgents; cpt++)
		{
			this.fourmiz.get(cpt).updatePosition();
		}
	}
	
	public afficherBase()
	{
		
	}
	
}
