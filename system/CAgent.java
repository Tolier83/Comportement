package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




//Etat de combat -> recuperation de nourriture

public class CAgent extends CObject {
	protected final static double STEP = 1;
	protected final static double CHANGING_DIRECTION_PROB = 0.05;
	public static final double DISTANCE_MIN = 5;
	public static final double SIZE = 5;
	
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
	
	protected void MiseAJourPosition() {
		mSpeedX += CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		mSpeedY += CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		posX += STEP * mSpeedX;
		posY += STEP * mSpeedY;
    }

	public boolean EviterMurs() {
		
		double lWidth = CEnvironement.getInstance().mWidth;
		double lHeight = CEnvironement.getInstance().mHeight;
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
	      	normalize();
            return true;
	    }
	    return false;
	}
	
    protected void updateDirection(List<CNourriture> pNourritureList) {
        // Où aller ?
        List<CNourriture> lInZone = new ArrayList();
        lInZone.addAll(pNourritureList);
        lInZone.removeIf(d -> (distance(d) > d.rayon));
        Collections.sort(lInZone, (CNourriture g1, CNourriture g2) -> (distance(g1) < distance(g2) ? -1: 1));
        CNourriture lGoal = null;
        if (!lInZone.isEmpty()) {
            lGoal = lInZone.get(0);
        }

        // Avons-nous un but ?
        if (lGoal == null || mBusy) {
            // Déplacement aléatoire
            if (CEnvironement.getInstance().mRandomGen.nextDouble() < CHANGING_DIRECTION_PROB) {
                mSpeedX = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
                mSpeedY = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
            }
            if (mBusy && lGoal == null) {
                mBusy = false;
            }
        }
        else {
            // Aller au but
            mSpeedX = lGoal.posX - posX;
            mSpeedY = lGoal.posY - posY;
            // But atteint ?
            if (distance(lGoal) < STEP) {
                if (mLoading == null) {
                    //if (CEnvironement.getInstance().mRandomGen.nextDouble() < lGoal.catchingProbability()) {
                        mLoading = CEnvironement.getInstance().catchNourriture(lGoal);
                    //}
                }
                else {
                    //SCEnvironement.getInstance().putDownNourriture(lGoal);
                    mLoading = null;
                }
                mBusy = Boolean.TRUE;
            }
        }
        normalize();
    }
    
    protected boolean EviterObstacles() {
    	ArrayList<CZoneAEviter> obstacles = CEnvironement.getInstance().mZoneAEviterList;
        if (!obstacles.isEmpty()) {
            // Recherche de l'obstacle le plus proche
            CZoneAEviter obstacleProche = obstacles.get(0);
            double distanceCarre = DistanceCarre(obstacleProche);
            for (CZoneAEviter o : obstacles) {
                if (DistanceCarre(o) < distanceCarre) {
                    obstacleProche = o;
                    distanceCarre = DistanceCarre(o);
                }
            }
            
            if (distanceCarre < (obstacleProche.rayon * obstacleProche.rayon)) {
                // Si collision, calcul du vecteur diff
                double distance = Math.sqrt(distanceCarre);
                double diffX = (obstacleProche.posX - posX) / distance;
                double diffY = (obstacleProche.posY - posY) / distance;
                mSpeedX = mSpeedX - diffX / 2;
                mSpeedY = mSpeedY - diffY / 2;
                normalize();
                return true;
            }
        }
        return false;        
    }
    
    protected void MiseAJour() {
    	EviterObstacles();
    	EviterMurs();
        MiseAJourPosition();
    }
}
