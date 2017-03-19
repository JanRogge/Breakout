package de.tudarmstadt.informatik.fop.breakout.logic;

import de.tudarmstadt.informatik.fop.breakout.ui.Block;
import de.tudarmstadt.informatik.fop.breakout.ui.SoundClips;

/**
 * Ball mit Kollisionens berechnung mit Blöcken,Wänden und dem Schläger
 * 
 * @author Jan Rogge
 */
public class Ball {

	double y;
	double x;
	double angle;
	double speed;
	Block[][] block;
	double blockx;
	double blocky;
	Logic log;
	int size;
	boolean hit;

	/**
	 * Konstruktor Initialisiert alle wichtigen Werte
	 * 
	 * @param log Logic Object in der das Spiel läuft
	 */
	public Ball(Logic log) {
		hit = false;
		y = 540;
		x = 388;
		size = 25;
		speed = 0.5;
		angle = (Math.random() - 0.5) * 140;
// 		angle = 180 * angle / Math.abs(angle) - angle;
		this.log = log;
	}

	/**
	 * Gibt zurück ob der Ball in aktuellen movement ein Object getroffen hat
	 * 
	 * @return boolean hit
	 */
	public boolean getHit() {
		return hit;
	}

	/**
	 * Setzten der Y-Position des Balls für Testzwecke
	 * 
	 * @param i neue Y-Position
	 */
	public void setY(double i) {
		this.y = i;
	}

	/**
	 * Gibt die Y Position zurueck
	 * 
	 * @return = y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setzten der X-Position des Balls für Testzwecke
	 * 
	 * @param i neue X-Position
	 */
	public void setX(double i) {
		this.x = i;
	}

	/**
	 * Gibt die X Position zurueck
	 * 
	 * @return = x
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setzten des Winkels des Balls für Testzwecke
	 * 
	 * @param i neuer Winkel
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Gibt den Winkle zurueck
	 * 
	 * @return = angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Setzten der Groeße des Balls
	 * 
	 * @param i neue Groeße
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gibt die Groeße des Balls zurueck
	 * 
	 * @return = size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Setzten der Geschwindigkeit des Balls
	 * 
	 * @param i neue Geschwindigkeit
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Gibt die Geschwindigkeit des Balls zurueck
	 * 
	 * @return = speed
	 */
	public float getSpeed() {
		return (float) speed;
	}

	/**
	 * Bewegt den Ball
	 * 
	 * @param blocks
	 *            = Position der Blöcke
	 * @param posPlayer
	 *            = Position des Spielers
	 * @return = Flag welche angibt ob der Ball aus dem Feld geflogen ist
	 * 				True = Ball aus dem Feld geflogen
	 */
	public boolean move(Block[][] blocks, int posPlayer) {
		hit = false;
		double altY = y;
		double altX = x;
		boolean end = false;
		block = blocks;

		y += Math.cos(Math.toRadians(angle)) * speed;
		x += Math.sin(Math.toRadians(angle)) * speed;

		if (y <= 1) { // Kollision auf der Oberen Seite
			// speedUp();
			hitWall(altX - (altY - 1) / (altY - y) * (altX - x), false); // x bei position 1
			angle = 180 * angle / Math.abs(angle) - angle; //Neuer Winkel
			y = 2 - y; //y auf vor die Wand setzten
			hit = true;
		} else if (y < 301) {
			boolean alreadychanged = false; //Boolean zum einmaligen winkel wechseln sollten 2 Bloecke auf einmal getroffen werden
			for (int q = 1; q <= block.length; q++) {
				for (int w = 1; w <= block[q - 1].length; w++) {
					if (block[q - 1][w - 1] != null) {
						// Block unten
						if ((int) y == block[q - 1][w - 1].getY() + block[q - 1][w - 1].getHeight() && (x
								+ 25 > block[q - 1][w - 1].getLocation().getX()
								&& x < block[q - 1][w - 1].getLocation().getX() + block[q - 1][w - 1].getWidth())) {
							hit = true;
							alreadychanged = gotHit(q,w,alreadychanged,true); //Changed 19.03
						}
						// Block oben
						else if ((int) y + 25 == block[q - 1][w - 1].getY() && (x + 25 > block[q - 1][w - 1]
								.getLocation().getX()
								&& x < block[q - 1][w - 1].getLocation().getX() + block[q - 1][w - 1].getWidth())) {
							hit = true;
							alreadychanged = gotHit(q,w,alreadychanged,true);
						}
						// Block links
						else if ((int) x == block[q - 1][w - 1].getX() + block[q - 1][w - 1].getWidth() && (y
								+ 25 > block[q - 1][w - 1].getLocation().getY()
								&& y < block[q - 1][w - 1].getLocation().getY() + block[q - 1][w - 1].getHeight())) {
							hit = true;
							alreadychanged = gotHit(q,w,alreadychanged,false);

						}
						// Block rechts
						else if ((int) x + 25 == block[q - 1][w - 1].getX() && (y + 25 > block[q - 1][w - 1]
								.getLocation().getY()
								&& y < block[q - 1][w - 1].getLocation().getY() + block[q - 1][w - 1].getHeight())) {
							hit = true;
							alreadychanged = gotHit(q,w,alreadychanged,false);
						}
					}
				}
			}
		} else if (y > 550) { // Kollision mit dem Schlaeger
			// speedUp();
			double x550 = hitWall(altX - (altY - 550) / (altY - y) * (altX - x), false); // x bei 550
			if (posPlayer < x550 && x550 < posPlayer + 130) { // Schlaeger getroffen
				if (posPlayer < x550 && x550 < posPlayer + 40) { //linkes Schlaegerdrittel
					angle = (70 + angle) * Math.random() - 70;
				}
				else if (posPlayer + 90 < x550 && x550 < posPlayer + 130) { //rechtes Schlaegerdrittel
					angle = (70 - angle) * Math.random() + angle;
				}
				if (angle == 0) {
					angle = 180;
				} else {
					angle = 180 * angle / Math.abs(angle) - angle;
				}
				y = 1100 - y;
				playSound(false);
				hit = true;
			} else {
				end = true;
			}
		}
		x = hitWall(x, true);
		return end;
	}
	
	/**
	 * Reduziert die Leben des Blocks zum 1/Zerstoert ihn wenn er schon bei eins ist und aendert den Winkel des balls
	 * @param q Y-Position des Blocks im Array
	 * @param w X-Position des Blocks im Array
	 * @param alreadychanged Ob der Winkel im gleichen durchlauf schon einmal geaendert wurde(Ball trifft 2 Bloecke gleichzeitig)
	 * @param bottom true wenn der Block von oben oder unten getroffen wird, false wenn von links oder rechts
	 * @return true= winkel geaendert, false= winkel bleibt gleich
	 */
	public boolean gotHit(int q, int w, boolean alreadychanged, boolean bottom){
		//Leben abziehen/Block entfernen
		if (block[q - 1][w - 1].getHitsLeft() > 1) {
			block[q - 1][w - 1].reduceHealth();
			playSound(true);
		} else {
			log.blockhit(w - 1, q - 1);
			playSound(true);
			block[q - 1][w - 1] = null;
		}
		//Winkel aendern
		if(bottom){
			if (!alreadychanged) {
				x = x * 2 - x;
				angle = 180 * angle / Math.abs(angle) - angle;
				return true;
			}
		} else{
			if (!alreadychanged) {
				x = x * 2 - x;
				angle *= -1;
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Laesst den Ball von der linken/rechten Wand abprallen
	 * 
	 * @param x
	 *            = Position des Balls
	 * @param turnAngle
	 *            = Flag welche angibt ob der Winkel umgedreht werden soll
	 * @return = neue x Position
	 */
	protected double hitWall(double x, boolean turnAngle) {
		if (x < 0) { // Links abprallen
			x = Math.abs(x);
			angle *= Boolean.compare(!turnAngle, turnAngle); // Invertiert den Winkel wenn turnAngle
			hit = true;
		} else if (x >= 775) { // Rechts abprallen
			x = 1550 - x;
			angle *= Boolean.compare(!turnAngle, turnAngle); // Invertiert den Winkel wenn turnAngle
			hit = true;
		}
		return x;
	}

	/**
	 * Spielt einen Sound ab wenn der Ball den Schlaeger oder einen Block getroffen hat
	 * 
	 * @param block Boolean ob ein Block getroffen wurde oder doch ein schlaeger
	 */
	public void playSound(boolean block) {
		if (block) {
			SoundClips.getInstance().setFile(1);
		} else {
			SoundClips.getInstance().setFile(2);
		}
	}

	/**
	 * Erhoeht die Geschwindigkeit des Balls
	 */
	// private void speedUp() {
	// speed *= 1.05;
	// }
}
