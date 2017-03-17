package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;

public class Block extends JLabel implements IHitable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5646901583617268564L;
	private static final int HEIGHT = 30;
	private static final int WIDHT = 50;
	private static final int SPACE = 0;
	private int health;
	private int type;
	private String id;

	public Block(int x, int y, int health, String id){
		this.id = id;
		this.health = health;
		this.type = blockType();
		setOpaque(true);
		background();
		if(id.equals(GameParameters.TOP_BORDER_ID)){
			setSize(800, 0);
		}
		setSize(WIDHT, HEIGHT);
		setLocation(SPACE +(WIDHT+SPACE)*(x-1), SPACE +(HEIGHT+SPACE)*(y-1));
		if(id.equals(GameParameters.TOP_BORDER_ID)){
			setSize(800, 0);
			setLocation(0,0);
		} else if(id.equals(GameParameters.RIGHT_BORDER_ID)){
			setSize(0, 600);
			setLocation(800,0);
		} else if(id.equals(GameParameters.LEFT_BORDER_ID)){
			setSize(0, 600);
			setLocation(0,0);
		}
	}
	
	public String getID(){
		return id;
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
	
	public void reduceHealth(){
		health--;
		background();
	}

	@Override
	public void setHitsLeft(int value) {
		// TODO Auto-generated method stub
		health = value;
	}

	@Override
	public int getHitsLeft() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public void addHitsLeft(int value) {
		// TODO Auto-generated method stub
		health += value;
	}

	@Override
	public boolean hasHitsLeft() {
		// TODO Auto-generated method stub
		if(health == 0){
			return false;
		}
		return true;
	}
}
