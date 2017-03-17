package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.tudarmstadt.informatik.fop.breakout.logic.Logic;

/**
 * Der GameListener sorgt daf�r das das Spiel erkennt welche Taste gedr�ckt wird
 * @author Jan
 */
public class GameListener implements KeyListener{
	
	private String rightKey = "Rechts";
	private String leftKey = "Links";
	
	
	private static GameListener instance;
	private GamePanel proto;
	private Logic log;
	
	private boolean right;
	private boolean left;
	
	/**
	 * Das Singelton-Pattern
	 */
	public static GameListener getInstance(GamePanel proto, Logic log) {
		if (instance == null) {
			instance = new GameListener(proto, log);
		}
		return instance;
	}
	
	/**
	 * Der Konstruktor
	 */
	GameListener(GamePanel proto, Logic log) {
		this.proto=proto;
		this.log = log;
	}
	
	/**
	 * wird aufgerufen wenn eine Taste gedr�ckt wurde
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == 37) {
			//left = true;
			log.movePaddle(true);
		}
		else if (arg0.getKeyCode() == 39) {
			//right = true;
			log.movePaddle(false);
		}
		else if (arg0.getKeyCode() == 32){
			//start Thread starten
			if(!log.getRun()){
				log.setRun(true);
				new Thread(log).start();
			}
		}
		else if (arg0.getKeyCode() == 27){
			//esc Pause Game
			log.setRun(false);
			proto.switchBack();
		}
		else if (arg0.getKeyCode() == 80){
			//pause Thread anhalten
			log.setRun(false);
		}

	}
	
	/**
	 * wird aufgerufen wenn eine Taste losgelassen wurde
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
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
