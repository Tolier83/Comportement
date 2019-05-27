package system;


public class CPheromone extends CObject {
	
	private int posX;
	private int posY;
	public final int PHEROMONE_HEIGHT = 5; 
	public final int PHEROMONE_WIDTH = 5;
	private int transparence = 1;
	
	/**
	 * Une ph�romone a une une position(x, y)
	 * @param x
	 * @param y
	 */
	public CPheromone(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.transparence = 1;
	}
	
	/**
	 * Une ph�romone peut avoir une transparence
	 * @param x
	 * @param y
	 * @param transparence
	 */
	public CPheromone(int x, int y, int transparence) {
		this.posX = x;
		this.posY = y;
		this.transparence = transparence;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	
	public int getTransparence() {
		return this.transparence;
	}

}
