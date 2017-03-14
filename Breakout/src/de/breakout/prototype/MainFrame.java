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
	private final Dimension d = this.getToolkit().getScreenSize();
	public MainFrame() {
		setAlwaysOnTop(true);
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
		addKeyListener(GameListener.getInstance(startGui));
		requestFocusInWindow();
	}
	public Prototype getGamePanel() {
		return startGui;
	}
	public void switchPanel(){
		setContentPane(startGui);
		setSize(startGui.getWidth() - 10, startGui.getHeight() + 13); 
		requestFocusInWindow();
	}
}
