package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics;



//Etat de combat -> recuperation de nourriture

public class CAgent extends CObject {
	protected final static double STEP = 4;
	protected final static double CHANGING_DIRECTION_PROB = 0.05;
	public static final double DISTANCE_MIN = 5;
	public static final double SIZE = 5;
	private int indexPheromones = 0;
	
	
	protected CNourriture mLoading;
	protected double mSpeedX;
	protected double mSpeedY;
	public List<CPheromone> pheromones;
	
	protected int mCombat;
	public int PointdeVie;
	public static final int maxCombat = 5;
	public static final int minCombat = 0;	
	public boolean mBusy = false;
	
	protected void normalize() {
		double lLenght = Math.sqrt(mSpeedX * mSpeedX + mSpeedY * mSpeedY);
		mSpeedX /= lLenght;
		mSpeedY /= lLenght;
	}
	
	public CAgent(double pPosX, double pPosY) {
		this.pheromones = new ArrayList<CPheromone>();
		posX = pPosX;
		posY = pPosY;
		this.pheromones.add(new CPheromone((int)posX, (int)posY));
		
		mSpeedX = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		mSpeedY = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
		normalize();
	}
	
	public boolean isLoaded() {
		return mBusy = true;
	}
	
	public boolean unLoad() {
		return mBusy = false;
	}
	
	protected void MiseAJourPosition() {
		 if (CEnvironement.getInstance().mRandomGen.nextDouble() < CHANGING_DIRECTION_PROB) {
             mSpeedX = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
             mSpeedY = CEnvironement.getInstance().mRandomGen.nextDouble() - 0.5;
         }
		posX += STEP * mSpeedX;
		posY += STEP * mSpeedY;
		if(indexPheromones == 10) {
			indexPheromones = 0;
			this.pheromones.add(new CPheromone((int)posX, (int)posY));
		}
		indexPheromones ++;

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
        // OÃ¹ aller ?
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
    
    public void drawPheromones(Graphics pG, Color baseColor) {
    		
    	if(pheromones.size() > 0 ) {
    		for (int i = 0; i < pheromones.size(); i++) {
    			if(pheromones.get(i).PHEROMONE_ALPHA > 30) {
    				Color myColour = new Color(baseColor.getRed(),
    						baseColor.getGreen(),
    						baseColor.getBlue(),
    						pheromones.get(i).PHEROMONE_ALPHA);
    				pG.setColor(myColour);
    				pG.fillOval(pheromones.get(i).getPosX(),
    						pheromones.get(i).getPosY(),
    						pheromones.get(i).PHEROMONE_HEIGHT,
    						pheromones.get(i).PHEROMONE_WIDTH
        				);
    			}

			}
    		

    	}

		
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
    
    public boolean nourritureFind() {
		if (this.mBusy == false) {
			for (CNourriture mNourriture : CEnvironement.getInstance().mNourritureList) {
				double mRayon = mNourriture.getRayon();
				if ((this.posX >= (mNourriture.getPosX() - mRayon) && (this.posX) <= (mNourriture.getPosX() + mRayon)) && ((this.posY >= (mNourriture.getPosY() - mRayon)) && (this.posY <= (mNourriture.getPosY() + mRayon)))) {
					mNourriture.decreaseSize();
					this.isLoaded();
					return true;
				}
			}
		}
		return false;
	}
    
    public boolean hitHome() {
    	if (this.mBusy == true) {
    		for (CBase mBase : CEnvironement.getInstance().mBaseList) {
    			double mRayon = mBase.getRayon();
    			if ((this.posX >= (mBase.getPosX() - mRayon) && (this.posX) <= (mBase.getPosX() + mRayon)) && ((this.posY >= (mBase.getPosY() - mRayon)) && (this.posY <= (mBase.getPosY() + mRayon)))) {
					//System.out.println("j'ai posé la pêhce");
					return true;
				}
    		}
    	}
		return false;
    }
    
    protected void MiseAJour() {
    	EviterObstacles();
    	EviterMurs();
    	nourritureFind();
    	hitHome();
        MiseAJourPosition();
    }
    
    protected void combat() { 
    	// attaque et point de vie d'un agent
    	PointdeVie = 10;
		mCombat = (int) (Math.random() * ( maxCombat - minCombat ));
	}

    
}
