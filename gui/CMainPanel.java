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
        for (CBase p : mEnv.mBaseList) {
        	++i;
        }
        System.out.println(i);
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
