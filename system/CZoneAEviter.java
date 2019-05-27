package system;

import java.awt.Color;
import java.awt.Graphics;

public class CZoneAEviter extends CObject {
	public double rayon;
	Color color;
	
	/**
	 * 
	 * @param _x
	 * @param _y
	 * @param _rayon
	 */
	public CZoneAEviter(double _x, double _y, double _rayon) {
        this.posX = _x;
        this.posY = _y;
        rayon = _rayon;
    }
	
	/**
	 * D�ssine l'obstacle sur le canvas
	 * @param pG
	 */
	public void afficher(Graphics pG) 
	{

		pG.fillRect((int) this.posX, (int)this.posY, (int)rayon, (int)rayon);
		
	}
}