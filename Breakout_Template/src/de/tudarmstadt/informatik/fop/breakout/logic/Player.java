package de.tudarmstadt.informatik.fop.breakout.logic;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;

public class Player extends JLabel implements IHitable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int lives;
	private Logic log;
	
	public Player(Logic log){
		this.log = log;
		lives = 3;
		setOpaque(false);
		setBackground(Color.BLACK);
		setBounds(350, 570, 130, 25);
		ImageIcon imageForOne = new ImageIcon("images/stick.png");
		setIcon(imageForOne);
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
	
	public int getPaddleX(){
		return getX();
	}
	
	public void decreaseLive(){
		if(lives > 1){
			lives --;
		} else{
			lives --;
			log.gameende();
		}
		
	}
}
