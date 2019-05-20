package system;

import java.awt.Color;
import java.awt.Graphics;

public class CZoneAEviter extends CObject {
	public double rayon;
	Color color;
	
	public CZoneAEviter(double _x, double _y, double _rayon) {
        this.posX = _x;
        this.posY = _y;
        rayon = _rayon;
    }
	
	public void afficher(Graphics pG) 
	{
		pG.setColor(color);
		pG.fillRect((int) this.posX, (int)this.posY, 4, 4);
		
	}
}