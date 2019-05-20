package gui;

import system.CAgent;
import system.CBase;
import system.CEnvironement;
import system.CNourriture;
import system.CZoneAEviter;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class CMainPanel extends JPanel implements Observer {
    private static final int BASE_COUNT = 3;
    private static final int AGENTS_COUNT = 30;
    private static final int NOURRITURE_COUNT = 2;
   // private static final int AGENT_WIDTH = 3;
   // private static final int AGENT_HEIGHT = 3;

    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 10;

    public Timer mTimer;
    boolean mInProgress = false;
    public TimerTask mTask;
    public CEnvironement mEnv;


    public CMainPanel() {
        // Fond noir.
    	this.setBackground(new Color(128, 128, 128));
    }
    
    public void launch() {
        mEnv = CEnvironement.getInstance();
        mEnv.init(BASE_COUNT, AGENTS_COUNT, getWidth(), getHeight(),NOURRITURE_COUNT);
        mEnv.addObserver(this);
        mTimer = new Timer();
        mTask = new TimerTask()
        		{
        			@Override
        			public void run() {mEnv.update();}
        		};
        mTimer.scheduleAtFixedRate(mTask, TIMER_DELAY, TIMER_PERIOD);
        		
    }
    
    @Override
    public void update(Observable pObservable, Object pArg) 
    {
    	this.repaint();
    }

    @Override
    public void paintComponent(Graphics pG) {
        super.paintComponent(pG);
    	System.out.println("DESSINER");
        for(CBase b : mEnv.mBaseList)
        {
        	b.afficherBase(pG);
        	b.afficherAgents(pG);
        }
        for(CNourriture n : mEnv.mNourritureList)
        {
        	n.afficher(pG);
        }
    }
}
