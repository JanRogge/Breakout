package de.breakout.prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Der GameListener sorgt daf�r das das Spiel erkennt welche Taste gedr�ckt wird
 * @author Simeon Kublenz
 */
public class GameListener implements KeyListener{
	
	private String rightKey = "Rechts";
	private String leftKey = "Links";
	
	
	private static GameListener instance;
	private Prototype proto;
	
	private boolean right;
	private boolean left;
	
	/**
	 * Das Singelton-Pattern
	 */
	public static GameListener getInstance(Prototype proto) {
		if (instance == null) {
			instance = new GameListener(proto);
		}
		return instance;
	}
	
	/**
	 * Der Konstruktor
	 */
	GameListener(Prototype proto) {
		this.proto=proto;
		System.out.println(proto);
	}
	
	/**
	 * wird aufgerufen wenn eine Taste gedr�ckt wurde
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {

		System.out.println("Heys");
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 37) {
			//left = true;
			System.out.println("here");
			proto.movePaddle(true);
		}
		else if (arg0.getKeyCode() == 39) {
			//right = true;
			proto.movePaddle(false);
		}
		else if (arg0.getKeyCode() == 22){
			
		}

	}
	
	/**
	 * wird aufgerufen wenn eine Taste losgelassen wurde
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (KeyEvent.getKeyText((arg0.getKeyCode())).equals(leftKey)) {
			left = false;
		}
		else if (KeyEvent.getKeyText((arg0.getKeyCode())).equals(rightKey)) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}



	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}
	
	
	public String getLeft() {
		return leftKey;
	}
	
	public void setLeft(String left) {
		if (left != null) {
			leftKey = left;
		}
	}
	
	public String getRight() {
		return rightKey;
	}
	
	public void setRight(String right) {
		if (right != null) {
			rightKey = right;
		}
	}
	
	public void restart() {
		left = false;
		right = false;	
	}
}
