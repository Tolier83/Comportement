package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Etat de combat -> recuperation de nourriture

public class CAgent extends CObject {
	protected final static double STEP = 3;
	protected final static double CHANGING_DIRECTION_PROB = 0.05;
	public static final double DISTANCE_MIN = 5;
	
	protected CNourriture mLoading;
	protected double mSpeedX;
	protected double mSpeedY;
	
	public boolean mBusy = false;
	
	protected void normalize() {
		double lLenght = Math.sqrt(mSpeedX * mSpeedX + mSpeedY * mSpeedY);
		mSpeedX /= lLenght;
		mSpeedY /= lLenght;
	}
	
	public CAgent(double pPosX, double pPosY) {
		posX = pPosX;
		posY = pPosY;
		
		mSpeedX = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		mSpeedY = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		normalize();
	}
	
	public boolean isLoaded() {
		return mBusy = true;
	}

	public void updatePosition() {
		posX += STEP * mSpeedX;
		posY += STEP * mSpeedY;
		
		double lWidth = CEnvironement.getInstance().getWidth();
		double lHeight = CEnvironement.getInstance().getHeight();
		double minWidth = 0.0;
		double minHeight = 0.0;
		
		if (posX < minWidth) {
            posX = minWidth;
        }
        else if (posY < minHeight) {
            posY = minHeight;
        }
        else if (posX > lWidth) {
            posX = lWidth;
        }
        else if (posY > lHeight) {
            posY = lHeight;
        }
		
		 double min = Math.min(posX - minWidth, posY - minHeight);
	        min = Math.min(min, lWidth - posX);
	        min = Math.min(min, lHeight - posY);
	        
	        if (min < DISTANCE_MIN) {
	        	if (min == (posX - minWidth)) {
	        		mSpeedX += 0.3;
	        	}
	        	else if (min == (posY - minHeight)) { 
	        		mSpeedY += 0.3; 
	        	} 
	        	else if (min == (lWidth - posX)) {
	        		mSpeedX -= 0.3;
	        	} 
	        	else if (min == (lHeight - posY)) {
	        		mSpeedY -= 0.3;
	        	}   
	    	}
	        
	        
	        normalize();
	}
}
