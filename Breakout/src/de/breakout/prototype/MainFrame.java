package de.breakout.prototype;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1946965675021998367L;
	private Prototype startGui = new Prototype();
	private MenuPanel menu = new MenuPanel();
	private Logic log;
	private final Dimension d = this.getToolkit().getScreenSize();
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
	}
	public Prototype getGamePanel() {
		return startGui;
	}
	public void switchPanel(){
		if(getContentPane() == startGui){
			setContentPane(menu);
		} else{
			setContentPane(startGui);
		}
		requestFocusInWindow();
	}
	public void setLogic(Logic log){
		this.log = log;
		addKeyListener(GameListener.getInstance(startGui, log));
	}
}
