package de.tudarmstadt.informatik.fop.breakout.logic;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;

/**
 * Spieler Objekt 
 * 
 * Treffbar und JLabel
 * 
 * @author Jan Rogge, Daniel Trageser
 *
 */
public class Player extends JLabel implements IHitable{

	private static final long serialVersionUID = 1L;
	private int lives;
	private Logic log;
	
	/**
	 * Konstruktor Initialisiert alle wichtigen Werte
	 * 
	 * @param log Logic Object in der das Spiel l�uft
	 */
	public Player(Logic log){
		this.log = log;
		lives = 3;
		setOpaque(false);
		setBackground(Color.BLACK);
		setBounds(350, 570, 130, 25);
		ImageIcon imageForPaddle = new ImageIcon("images/stick.png"); //Schlaeger bild
		setIcon(imageForPaddle);
	}

	@Override
	public void setHitsLeft(int value) {
		// TODO Auto-generated method stub
		lives = value;
	}

	@Override
	public int getHitsLeft() {
		// TODO Auto-generated method stub
		return lives;
	}

	@Override
	public void addHitsLeft(int value) {
		// TODO Auto-generated method stub
		lives += value;
	}

	@Override
	public boolean hasHitsLeft() {
		// TODO Auto-generated method stub
		if(lives == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * Gibt die X-Position des Schlaegers im Panel zurueck
	 * 
	 * @return getX() X Position des Schlaegers
	 */
	public int getPaddleX(){
		return getX();
	}
	
	/**
	 * Reduziert die Leben des Spielers um eins
	 * Und beendet das Spiel falls keine Leben mehr vorhanden sind
	 */
	public void decreaseLive(){
		if(lives > 1){
			lives --;
		} else{
			lives --;
			log.gameende();
		}
		
	}
}
