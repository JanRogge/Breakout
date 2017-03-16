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
	private int type;

	public Block(int x, int y, int health){
		this.health = health;
		this.type = blockType();
		setOpaque(true);
		background();
		setSize(WIDHT, HEIGHT);
		setLocation(SPACE +(WIDHT+SPACE)*(x-1), SPACE +(HEIGHT+SPACE)*(y-1));
	}
	
	public int blockType(){
		if(health == 2){
			return 2;
		} else if(health == 3){
			return 3;
		} else if(health == 4){
			return 4;
		} else{
			return 1;
		}
	}
	
	public String getFittingImageIconString(int health, int type){
		switch(type){
			//Block mit Raumschiff -> 4 Health Stufen
			case(1):
				return "images/block1_health1.png";
			//Block mit Kanone -> 4 Health Stufen
			case(2):
				switch(health){
				case(1):
					return "images/block1_health1.png";
				case(2):
					return "images/block1_health3.png";
				}
			//Normaler Block -> 4 Health Stufen
			case(3):
				switch(health){
				case(1):
					return "images/block2_health1.png";
				case(2):
					return "images/block2_health2.png";
				case(3):
					return "images/block2_health3.png";
				}
			case(4):
				switch(health){
				case(1):
					return "images/block4_health1.png";
				case(2):
					return "images/block4_health2.png";
				case(3):
					return "images/block4_health3.png";
				case(4):
					return "images/block4_health4.png";
				}
			default:
				return "images/block1_health1.png";
		}
	}
	
	public void background(){
		switch(health){
			case(1):
				ImageIcon imageForOne = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForOne);
				setBackground(Color.BLACK);
				break;
			case(2):
				setBackground(Color.RED);
				ImageIcon imageForTwo = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForTwo);
				break;
			case(3):
				setBackground(Color.GREEN);
				ImageIcon imageForThree = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForThree);
				break;
			case(4):
				setBackground(Color.CYAN);
				ImageIcon imageForFour = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForFour);
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
