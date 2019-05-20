package main;

import javax.swing.JFrame;

import gui.CMainPanel;

public class CMain {
    public static final String TITLE = "TestComportementTest";
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;


    public static void main(String[] args) {
    	JFrame fenetre = new JFrame();
    	fenetre.setTitle(TITLE);
    	fenetre.setSize(WIDTH, HEIGHT);
    	fenetre.setLocationRelativeTo(null);
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	fenetre.setResizable(false);

        // Création du contenu.
    	CMainPanel panel = new CMainPanel();
    	fenetre.setContentPane(panel);

        // Affichage.
    	fenetre.setVisible(true);

        // Lancement processus.
    	panel.launch();
    }
}
