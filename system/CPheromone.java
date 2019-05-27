package system;

public class CPheromone {
	
	private int posX;
	private int posY;
	public final int PHEROMONE_HEIGHT = 3; 
	public final int PHEROMONE_WIDTH = 3;
	public final int PHEROMONE_ALPHA = 5;
	
	public CPheromone(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}

}
