package gui;

import system.CAgent;
import system.CBase;
import system.CEnvironement;
import system.CNourriture;
import system.CZoneAEviter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class CMainPanel extends JPanel implements Observer, MouseListener {
    private static final int GARBAGES_COUNT = 50;
    private static final int AGENTS_COUNT = 30;

    private static final int AGENT_WIDTH = 3;
    private static final int AGENT_HEIGHT = 3;

    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 10;

    private Timer mTimer;
    boolean mInProgress = false;
    private TimerTask mTask;
    private CEnvironement mEnv;


    public CMainPanel() {
        // Fond noir.
    	this.setBackground(new Color(0, 0, 0));

        // Gestion souris.
    	this.addMouseListener(this);
    }
    
    public void launch() {
        mEnv = CEnvironement.getInstance();
        mEnv.init(GARBAGES_COUNT, AGENTS_COUNT, getWidth(), getHeight(), 3);
        mEnv.addObserver(this);
    }

    @Override
    public void update(Observable pObservable, Object pArg) {
        this.repaint();
        int lLoadedAgents = 0;
        // Compter le nombre d'agents chargés et l'afficher dans la console.
        int i = 0;
        for (CAgent p : mEnv.mAgentsList) {
        	++i;
        }
        System.out.println(i);
    }
    
    public void displayAgent(CAgent pAgent, Graphics pG) {
        // Afficher un agent chargé en blanc et un non chargé en orange.
    	Color color = new Color(255,255,255);
    	if(pAgent.getBusy() == true) {
    		color = new Color(255,0,0);
    	}
    	pG.setColor(color);
    	pG.fillArc(
        		(int) pAgent.posX-3,
        		(int) pAgent.posY-3,
        		AGENT_WIDTH, AGENT_HEIGHT, 0, 360);
        // TODO CNAM : 5 lignes de code à ajouter ici.
    }
    
    public void displayGarbage(CNourriture pNourriture, Graphics pG) {
        // Choisir la couleur des déchets selon leur type :
    	Color color = new Color(0,0,0);
    	if(pNourriture.mType == 0) {
    		color = new Color(255,0,0);
    	} else if (pNourriture.mType == 1) {
    		color = new Color(0,255,0);
    	} else if (pNourriture.mType == 2) {
    		color = new Color(0,0,255);
    	} else {
    		color = new Color(255,255,255);
    	}
    	pG.setColor(color);
        // 1 -> rouge
        // 2 -> vert
        // 3 -> bleu

        // Base : carré.
    	if(pNourriture.squareSize != 0) {
    	pG.drawRect(
        		(int) pNourriture.posX,
        		(int) pNourriture.posY,
        		pNourriture.squareSize, pNourriture.squareSize);
    	
        // Zone d'influence (ronde).
    	pG.setColor(color);
        pG.drawArc(
        		(int) pNourriture.posX- pNourriture.influenceZone()/2 + 2,
        		(int) pNourriture.posY- pNourriture.influenceZone()/2 + 2,
        		pNourriture.influenceZone(), pNourriture.influenceZone(), 0, 360);
    	}
        // TODO CNAM : 4 lignes de code à ajouter ici.
    }

    @Override
    public void paintComponent(Graphics pG) {
        super.paintComponent(pG);

        for (CAgent p : mEnv.mAgentsList) {
        	displayAgent(p, pG);
        }
        for (CNourriture o : mEnv.mGarbagesList) {
        	displayGarbage(o, pG);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		mTimer.cancel();
            mTimer = null;
            mInProgress = Boolean.FALSE;
    	}
    	else if (e.getButton() == MouseEvent.BUTTON3) {
    		 mTimer = new Timer();
             mTask = new TimerTask() {
                 @Override
                 public void run() {
                     mEnv.update();
                 }
             };
             mTimer.scheduleAtFixedRate(mTask, TIMER_DELAY, TIMER_PERIOD);
             mInProgress = Boolean.TRUE;
      	}
    }

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
