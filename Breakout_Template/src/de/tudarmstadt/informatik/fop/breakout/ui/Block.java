package de.tudarmstadt.informatik.fop.breakout.ui;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;

/**
 * Treffbarer Block des Typ JLabel 
 * 
 * @author Jan Rogge, Daniel Trageser
 *
 */
public class Block extends JLabel implements IHitable{

	private static final long serialVersionUID = 5646901583617268564L;
	private static final int HEIGHT = 30;
	private static final int WIDHT = 50;
	private static final int SPACE = 0;
	private int health;
	private int type;
	private String id;

	/**
	 * Konstruktor legt alle wichtigen Werte für den Block fest
	 * 
	 * Erzeugt Demobloecke fuer Tests
	 * 
	 * @param x X-Position des Blocks
	 * @param y Y-Position des Blocks
	 * @param health Leben des Blocks
	 * @param id String ID des Blocks 
	 */
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
		
		//Demo Bloecke
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
		//Ende Demo Bloecke
	}
	
	/**
	 * Gibt die ID des Blocks zurueck
	 * @return id
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * Legt anhand der Startleben des Blocks einen Typen fuer den Block fest anhand dem spaeter die Bilder ausgewaehlt werden
	 * 
	 * @return int Typ des Blocks
	 */
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
	
	/**
	 * Legt anhand des Typen vom Block und der Lebensanzahl das zu ladene Bild fest
	 * 
	 * @param health Leben des Blocks
	 * @param type Typ des Blocks(Startleben)
	 * @return String mit dem Pfad des bildes
	 */
	public String getFittingImageIconString(int health, int type){
		switch(type){
			//Standart Block -> 1 Health Stufen
			case(1):
				return "images/block1_health1.png";
			//Standart Block  -> 2 Health Stufen
			case(2):
				switch(health){
				case(1):
					return "images/block1_health1.png";
				case(2):
					return "images/block1_health3.png";
				}
			//Block mit Kanone -> 3 Health Stufen
			case(3):
				switch(health){
				case(1):
					return "images/block2_health1.png";
				case(2):
					return "images/block2_health2.png";
				case(3):
					return "images/block2_health3.png";
				}
			//Block mit Raumschiff -> 4 Health Stufen
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
	
	/**
	 * Setzt die Bilder fuer den Block abhaenging von deren Leben
	 */
	public void background(){
		switch(health){
			case(1):
				ImageIcon imageForOne = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForOne);
				break;
			case(2):
				ImageIcon imageForTwo = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForTwo);
				break;
			case(3):
				ImageIcon imageForThree = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForThree);
				break;
			case(4):
				ImageIcon imageForFour = new ImageIcon(getFittingImageIconString(health, type));
				setIcon(imageForFour);
				break;
			default:
				setOpaque(false);
				break;
		}
	}
	
	/**
	 * Reduziert das Leben des Blocks um 1
	 */
	public void reduceHealth(){
		setHitsLeft(getHitsLeft()-1); //Changed 18.03
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
