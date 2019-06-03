package system;

import java.awt.Color;
import java.util.*;

public class CEnvironement extends Observable {
	private static CEnvironement sInstance = null;
    protected Random mRandomGen;
    public double mWidth;
    public double mHeight;
    public ArrayList<CBase> mBaseList;
    public Vector<CNourriture> mNourritureList;
    //public ArrayList<CNourriture> mNourritureList;
    public ArrayList<CZoneAEviter> mZoneAEviterList;
    public ArrayList<CAgent> cimetiere;
    //protected int mIterCounts = 0;

    private CEnvironement() {
    	mBaseList = new ArrayList<CBase>();
    	mNourritureList = new Vector<CNourriture>();
    	mZoneAEviterList = new ArrayList<CZoneAEviter>();
    	cimetiere = new ArrayList<CAgent>();
    
        // Création du générateur aléatoire.
        mRandomGen = new Random();
    }

    /**
     * Singleton
     * @return
     */
    public static CEnvironement getInstance() {
		if(sInstance == null) {
			sInstance = new CEnvironement();
		}
		return sInstance;
	}
        
    /**
     * 
     * @param pNourrite
     * @return
     */
    public CNourriture catchNourriture(CNourriture pNourrite) {
        	pNourrite.decreaseSize();
            // TODO CNAM : 2 lignes de code Ã  ajouter ici.
        	return pNourrite;
    }
    
    /**
     * Initialisation de l'environnement de base
     * @param _nbBase
     * @param _nbAgents
     * @param x
     * @param y
     * @param _nbNourriture
     */
    public void init(int _nbBase, int _nbAgents, int x, int y, int _nbNourriture) 
    {  	
    	mWidth = x;
    	mHeight = y;
    	mBaseList.clear();
    	// Ajout du nombre de bases
    	for(int i = 0; i < _nbBase; i++)
    	{
    		mBaseList.add(new CBase(x/10.0, y/10.0, _nbAgents, Color.RED, 10));
    	}
    		
    	mNourritureList.clear();
    	// Ajout de la nourriture sur le canvas
    	for(int i = 0; i < _nbNourriture; i++)
    	{
    		mNourritureList.add(new CNourriture(x/2.0, y/2.0, Color.BLACK, 20));
    	}
    	// Clean des obstacles
    	mZoneAEviterList.clear();
    }
    /**
     * 
     */
    public void update()
    {
    	for(CBase b : mBaseList)
    	{
    		b.bougerAgents();

    	}
    	combatAgent();
    	setChanged();
    	notifyObservers();
    }
    
    public void combatAgent() {
    	for(CBase b : mBaseList) {
    		for(CAgent agent : b.fourmiz) {
    			// chaque agent d'une base regarde si il rentre en contact avaec un agent 
    			searchAgentColision(b,agent);
    		}
    		
    		if(cimetiere.size() >0) {
    			b.fourmiz.removeAll(cimetiere);
        		System.out.println(b.color.toString());
        		System.out.println(b.fourmiz.size());
            	int nbDel = cimetiere.size();
            	b.nbAgents -= nbDel;
            	cimetiere.clear();
    		}
    		
    	}
    	
    	
    }
    
    /**
     * Gestion de la collision entre agents
     * @param base
     * @param mAgent
     */
    public CAgent searchAgentColision(CBase base, CAgent mAgent) {
    	for(CBase b : mBaseList) {
    		if(base!= b) {
    			for(CAgent agent : b.fourmiz) {
    				
    			/*	System.out.println(Math.round(agent.posX));
    				System.out.println(Math.round(mAgent.posX));
    				System.out.println("/");
    				System.out.println(Math.round(agent.posY));
    				System.out.println(Math.round(mAgent.posY)); */
    				
        			if((Math.round(mAgent.posX) == Math.round(agent.posX)) && (Math.round(mAgent.posY)== Math.round(agent.posY))) {
        		
        					// si notre agent est de meme attaque, les deux perdent des points 
        				base.killAgents(mAgent,cimetiere);
        				
        			}
        		}		
			}
    	}return mAgent;
    }
    
    
}