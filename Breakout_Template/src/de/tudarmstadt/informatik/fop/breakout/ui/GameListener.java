package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.tudarmstadt.informatik.fop.breakout.logic.Logic;

/**
 * Der GameListener sorgt dafuer das das Spiel erkennt welche Taste gedrueckt wird
 * @author Jan Rogge
 */
public class GameListener implements KeyListener{
	
	
	private static GameListener instance;
	private GamePanel proto;
	private Logic log;
	
	
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
	
	@Override
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == 37) {
			//Move left
			log.movePaddle(true);
		}
		else if (arg0.getKeyCode() == 39) {
			//Move right
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

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
