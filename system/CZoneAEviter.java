package system;

public class CZoneAEviter extends CObject {
	public double rayon;
	public double posX;
	public double posY;
	
	public void ZoneAEviter(double _x, double _y, double _rayon) {
        posX = _x;
        posY = _y;
        rayon = _rayon;
    }
}
