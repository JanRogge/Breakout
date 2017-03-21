package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.tudarmstadt.informatik.fop.breakout.logic.Logic;

/**
 * Hauptfenster in dem die Panel liegen
 * @author Jan Rogge, Adriano Rodrigues
 *
 */
public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = -1946965675021998367L;
	private GamePanel startGui = new GamePanel();
	private MenuPanel menu = new MenuPanel();
	private Logic log;
	private boolean firstgame = true;
	private final Dimension d = this.getToolkit().getScreenSize();
	private BackgroundSounds bg;
	
	/**
	 * Konstruktor erzeugt das Frame
	 */
	public MainFrame() {
		setTitle("Breakout");
		setLayout(null);
		setResizable(false);
		setContentPane(menu);
		setSize(menu.getWidth() - 10, menu.getHeight() + 13); 
		setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2)); //Positioniert dsa Fenster in der Mitte des Bildschirms
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		requestFocusInWindow();
		
		bg = new BackgroundSounds(true);
		bg.start();
	}
	/**
	 * Gibt das Panel zureuck in dem das Spiel laeuft
	 * @return GamePanel startGui
	 */
	public GamePanel getGamePanel() {
		return startGui;
	}
	
	/**
	 * Toggle zwischen dem Menu und GamePanel
	 */
	public void switchPanel(){
		if(getContentPane() == startGui){
			setContentPane(menu);
			bg.setMenu(true);
		} else{
			setContentPane(startGui);
			bg.setMenu(false);
		}
		requestFocusInWindow();
	}
	
	/**
	 * Setzt die Logic f�r den KeyListener
	 * @param log Logic die im KeyListener verwendet wird
	 */
	public void setLogic(Logic log){
		this.log = log;
		addKeyListener(GameListener.getInstance(startGui, log));
	}
	
	/**
	 * Startet ein neues Spiel auf der GUI
	 */
	public void newGame(){
			log.newGame();
			startGui.moveBall(388, 540);
			firstgame = false;	
	}
	/**
	 * Gibt zureuck ob beim druck auf Resume ein neues Spiel gestartet werden soll oder noch ein altes vorhanden ist
	 * @return boolean firstgame
	 */
	public boolean getfirstGame(){
		return firstgame;
	}
	/**
	 * Setzt die boolean firstgame 
	 * @param game boolean ob beim resume druecken ein neues spiel gestartet werden soll oder nicht
	 */
	public void setfirstGame(boolean game){
		this.firstgame = game;
	}
}
