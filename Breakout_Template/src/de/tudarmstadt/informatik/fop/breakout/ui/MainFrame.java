package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.tudarmstadt.informatik.fop.breakout.logic.Logic;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1946965675021998367L;
	private GamePanel startGui = new GamePanel();
	private MenuPanel menu = new MenuPanel();
	private Logic log;
	private boolean firstgame = true;
	private final Dimension d = this.getToolkit().getScreenSize();
	private BackgroundSounds bg;
	public MainFrame() {
		//setAlwaysOnTop(true);
		setTitle("Breakout");
		setLayout(null);
		setResizable(false);
		//setContentPane(startGui);
		setContentPane(menu);
		//setSize(startGui.getWidth() - 10, startGui.getHeight() + 13); 
		setSize(menu.getWidth() - 10, menu.getHeight() + 13); 
		setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2)); //Positioniert dsa Fenster in der Mitte des Bildschirms
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		requestFocusInWindow();
		
		bg = new BackgroundSounds(true);
		bg.start();
	}
	public GamePanel getGamePanel() {
		return startGui;
	}
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
	public void setLogic(Logic log){
		this.log = log;
		addKeyListener(GameListener.getInstance(startGui, log));
	}
	public void newGame(){
			log.newGame();
			startGui.moveBall(388, 540);
			firstgame = false;	
	}
	public boolean getfirstGame(){
		return firstgame;
	}
}
