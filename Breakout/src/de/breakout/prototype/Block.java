package de.breakout.prototype;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5646901583617268564L;
	private static final int HEIGHT = 30;
	private static final int WIDHT = 50;
	private static final int SPACE = 0;
	private int health;

	public Block(int x, int y, int health){
		this.health = health;
		setOpaque(true);
		background();
		setSize(WIDHT, HEIGHT);
		setLocation(SPACE +(WIDHT+SPACE)*(x-1), SPACE +(HEIGHT+SPACE)*(y-1));
	}
	
	public void background(){
		switch(health){
			case(1):
				ImageIcon imageForOne = new ImageIcon("images/block_3.png");
				setIcon(imageForOne);
				setBackground(Color.BLACK);
				break;
			case(2):
				setBackground(Color.RED);
				ImageIcon imageForTwo = new ImageIcon("images/block_2.png");
				setIcon(imageForTwo);
				break;
			case(3):
				setBackground(Color.GREEN);
				ImageIcon imageForThree = new ImageIcon("images/block_1.png");
				setIcon(imageForThree);
				break;
			case(4):
				setBackground(Color.CYAN);
				break;
			default:
				setOpaque(false);
				break;
		}
	}
	
	public int getHealth(){
		return health;
	}
	
	public void reduceHealth(){
		health--;
		background();
	}
}
