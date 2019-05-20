package system;

import java.util.*;

public class CEnvironement extends Observable {
	private static CEnvironement sInstance = null;
    protected Random mRandomGen;
    /*protected double mWidth;
    protected double mHeight;*/
    public ArrayList<CBase> mBaseList;
    public ArrayList<CNourriture> mNourritureList;
    
    //protected int mIterCounts = 0;

    private CEnvironement() {
    	mBaseList = new ArrayList();
    	mNourritureList = new ArrayList();
    
        // Création du générateur aléatoire.
        mRandomGen = new Random();
    }
    
    public static CEnvironement getInstance() {
		if(sInstance == null) {
			sInstance = new CEnvironement();
		}
		return sInstance;
	}
    
    public void init() {
    	mBaseList.clear();
    	mNourritureList.clear();

    	CBase lBase = ;
    	CNourriture lNourriture = ;
    	
    	mBaseList.add(lBase);
    	mNourritureList.add(lNourriture);
    }
}
