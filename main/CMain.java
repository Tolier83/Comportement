package main;

import javax.swing.JFrame;

import gui.CMainPanel;

public class CMain {
    public static final String TITLE = "Comportement Groupe 1 Raimon Molinari Nolot Luppo";
    public static final int WIDTH = 768;
    public static final int HEIGHT = 768;


    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	JFrame fenetre = new JFrame();
    	fenetre.setTitle(TITLE);
    	fenetre.setSize(WIDTH, HEIGHT);
    	fenetre.setLocationRelativeTo(null);
    	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	fenetre.setResizable(false);

        // Cr�ation du contenu.
    	CMainPanel panel = new CMainPanel();
    	fenetre.setContentPane(panel);

        // Affichage.
    	fenetre.setVisible(true);

        // Lancement processus.
    	panel.launch();
    }
}
