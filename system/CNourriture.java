package system;
import java.awt.Color;
import java.awt.Graphics;

public class CNourriture extends CObject {
	public double rayon = 10;
	Color color;
	public int zoneSize = 1;
	
	
	public CNourriture(double _x, double _y, Color _color, double _rayon) {
		this.posX = _x;
		this.posY = _y;
		this.rayon = _rayon;
		this.color = _color;
	}

	public void CreerZoneNourriture(Graphics pG) 
	{
		pG.setColor(color);
		pG.fillOval((int)this.posX, (int)this.posY, (int)rayon, (int)rayon);
	}
	
	public void DiminueNourriture()
	{
		zoneSize--;
	}
}
