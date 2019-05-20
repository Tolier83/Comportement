package system;
import java.awt.Color;
import java.awt.Graphics;

public class CNourriture extends CObject {
	public double rayon = 10;
	Color color;
	public int quantite = 10;
	
	
	public CNourriture(double _x, double _y, Color _color, double _rayon) {
		this.posX = _x;
		this.posY = _y;
		this.rayon = _rayon;
		this.color = _color;
	}

	public void afficher(Graphics pG) 
	{
		pG.setColor(color);
		pG.fillArc((int)this.posX, (int)this.posY, (int)this.rayon, (int)this.rayon, 0, 360);
		pG.fillOval((int)this.posX, (int)this.posY, (int)quantite, (int)quantite);
		
	}
	
	public void decreaseSize()
	{
		if(quantite > 0)
			quantite--;
	}
}
