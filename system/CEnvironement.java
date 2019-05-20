package system;

import java.util.*;

// Environnement, contenant les déchets et les agents.
// TODO CNAM : cette classe doit être un singleton. Ajouter le code en conséquence aux endroits nécessaires. */
public class CEnvironement extends Observable {
	private static CEnvironement sInstance = null;
    protected Random mRandomGen;
    protected double mWidth;
    protected double mHeight;
    public ArrayList<CGarbage> mGarbagesList;
    public ArrayList<CSorterAgent> mAgentsList;
    protected int mIterCounts = 0;


    private CEnvironement() {
    	mGarbagesList = new ArrayList();
    	mAgentsList = new ArrayList();
    
        // Création du générateur aléatoire.
        mRandomGen = new Random();
    }
    
    public static CEnvironement getInstance() {
		if(sInstance == null) {
			sInstance = new CEnvironement();
		}
		return sInstance;
	}
    
    public void init(int pGarbagesCount, int pAgentsCount, double pWidth, double pHeight, int pGarbagesType) {
        mWidth = pWidth;
        mHeight = pHeight;
        mGarbagesList.clear();
        for (int i = 0; i < pGarbagesCount; i++) {
            CGarbage lGarbage = new CGarbage(mRandomGen.nextDouble() * mWidth, mRandomGen.nextDouble() * mHeight, mRandomGen.nextInt(pGarbagesType));
            mGarbagesList.add(lGarbage);
        }
        mAgentsList.clear();
        for (int i = 0; i < pAgentsCount; i++) {
            CSorterAgent lAgent = new CSorterAgent(mRandomGen.nextDouble() * mWidth, mRandomGen.nextDouble() * mHeight);
            mAgentsList.add(lAgent);
        }
    }
   
    public double getWidth() {
        return mWidth;
    }
    public double getHeight() {
        return mHeight;
    }
    
    public void putDownGarbage(CGarbage pGarbage) {
        pGarbage.increaseSize();
    }
    
    public CGarbage catchGarbage(CGarbage pGarbage) {
        if (pGarbage.mSize == 1) {
        	pGarbage.decreaseSize();
        	pGarbage.squareSize = 0;
            // TODO CNAM : 2 lignes de code à ajouter ici.
        	return pGarbage;
        }
        else {
        	pGarbage.decreaseSize();
        	
            // TODO CNAM : 3 lignes de code à ajouter ici.
        	return pGarbage;
        }
    }
    
    public void update() {
        for (CSorterAgent lAgent : mAgentsList) {
        	lAgent.updatePosition();
        	lAgent.updateDirection(mGarbagesList);
        }
        
        mIterCounts++;
        if (mIterCounts % 500 == 0) {
            Collections.reverse(mGarbagesList);
        }
        
        setChanged();
        notifyObservers();
    }
}
