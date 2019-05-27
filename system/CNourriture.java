package system;
import java.awt.Color;
import java.awt.Graphics;

public class CNourriture extends CObject {
	public double rayon = 20;
	Color color;
	public int quantite = 20;
	
	
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
		if(quantite > 0 && rayon > 0 ) {
			quantite--;
			rayon--;
		}
		else {
			System.out.println("plus de nourriture");
		}
	}
	
	public double getPosX() {
		return this.posX;
	}
	
	public double getPosY() {
		return this.posY;
	}
	
	public double getRayon() {
		return this.rayon;
	}
}
