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

   // private static final int AGENT_WIDTH = 3;
   // private static final int AGENT_HEIGHT = 3;

    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 10;

    private Timer mTimer;
    boolean mInProgress = false;
    private TimerTask mTask;
    private CEnvironement mEnv;


    public CMainPanel() {
        // Fond noir.
    	this.setBackground(new Color(0, 0, 0));
    }
    
    public void launch() {
        mEnv = CEnvironement.getInstance();
        mEnv.init(BASE_COUNT, AGENTS_COUNT, getWidth(), getHeight());
        mEnv.addObserver(this);
    }

    @Override
    public void update(Observable pObservable, Object pArg) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics pG) {
        super.paintComponent(pG);

        for(CBase b : mEnv.mBaseList)
        {
        	b.afficherBase(pG);
        	b.afficherAgents(pG);
        }
    }
}
