package de.tudarmstadt.informatik.fop.breakout.logic;

import de.tudarmstadt.informatik.fop.breakout.ui.Block;
import de.tudarmstadt.informatik.fop.breakout.ui.SoundClips;

/**
 * 
 * @author Jan
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
	boolean hit = false;;

	/**
	 * Konstruktor
	 * 
	 * @param side
	 *            = Seite auf der der Ball spawnt false = links true = rechts
	 */
	public Ball(Logic log) {
		y = 540;
		x = 388;
		// y=110;
		// y= 70;
		size = 25;
		speed = 0.5;
		// angle = (Math.random() - 0.5) * 140;
		this.log = log;
		angle=0;
		//angle = -180;
		// angle = 180 * angle / Math.abs(angle) - angle;
	}

	public boolean getHit() {
		return hit;
	}

	public void setY(double i) {
		this.y = i;
	}

	/**
	 * Gibt die X Position zur�ck
	 * 
	 * @return = x
	 */
	public double getY() {
		return y;
	}

	public void setX(double i) {
		this.x = i;
	}

	/**
	 * Gibt die Y Position zur�ck
	 * 
	 * @return = y
	 */
	public double getX() {
		return x;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return (float) speed;
	}

	/**
	 * Bewegt den Ball
	 * 
	 * @param posPlayer1
	 *            = Position des linken Spielers
	 * @param posPlayer2
	 *            = Position des rechten Spielers
	 * @return = Flag welche angibt ob ein Punkt erziehlt wurde false = Kein
	 *         Punkt true = Punkt
	 */
	public boolean move(Block[][] posPlayer1, int posPlayer2) {
		hit = false;
		double altY = y;
		double altX = x;
		boolean end = false;
		block = posPlayer1;

		y += Math.cos(Math.toRadians(angle)) * speed;
		x += Math.sin(Math.toRadians(angle)) * speed;

		if (y <= 1) { // Kollision auf der Oberen Seite
			// speedUp();
			hitWall(altX - (altY - 1) / (altY - y) * (altX - x), false); // x bei position 1
			angle = 180 * angle / Math.abs(angle) - angle;
			y = 2 - y;
			hit = true;
		} else if (y < 301) {
			boolean alreadychanged = false;
			for (int q = 1; q <= block.length; q++) {
				for (int w = 1; w <= block[q - 1].length; w++) {
					if (block[q - 1][w - 1] != null) {
						// Block unten
						if ((int) y == block[q - 1][w - 1].getY() + block[q - 1][w - 1].getHeight() && (x
								+ 25 > block[q - 1][w - 1].getLocation().getX()
								&& x < block[q - 1][w - 1].getLocation().getX() + block[q - 1][w - 1].getWidth())) {
							hit = true;
							if (block[q - 1][w - 1].getHitsLeft() > 1) {
								block[q - 1][w - 1].reduceHealth();
								playSound(true);
							} else {
								log.blockhit(w - 1, q - 1);
								block[q - 1][w - 1] = null;
								playSound(true);
							}
							if (!alreadychanged) {
								hitWall(altX - (altY - 301) / (altY - y) * (altX - x), false);
								if (angle == 0) {
									angle = 180;
								} else {
									angle = 180 * angle / Math.abs(angle) - angle;
								}
								y = y * 2 - y;
								alreadychanged = true;
							}
						}
						// Block oben
						else if ((int) y + 25 == block[q - 1][w - 1].getY() && (x + 25 > block[q - 1][w - 1]
								.getLocation().getX()
								&& x < block[q - 1][w - 1].getLocation().getX() + block[q - 1][w - 1].getWidth())) {
							hit = true;
							if (block[q - 1][w - 1].getHitsLeft() > 1) {
								block[q - 1][w - 1].reduceHealth();
								playSound(true);
							} else {
								log.blockhit(w - 1, q - 1);
								playSound(true);
								block[q - 1][w - 1] = null;
							}
							if (!alreadychanged) {
								hitWall(altX - (altY - 301) / (altY - y) * (altX - x), false);
								if (angle == 0) {
									angle = 180;
								} else {
									angle = 180 * angle / Math.abs(angle) - angle;
								}
								y = y * 2 - y;
								alreadychanged = true;
							}
						}
						// Block links
						else if ((int) x == block[q - 1][w - 1].getX() + block[q - 1][w - 1].getWidth() && (y
								+ 25 > block[q - 1][w - 1].getLocation().getY()
								&& y < block[q - 1][w - 1].getLocation().getY() + block[q - 1][w - 1].getHeight())) {
							hit = true;
							if (block[q - 1][w - 1].getHitsLeft() > 1) {
								block[q - 1][w - 1].reduceHealth();
								playSound(true);
							} else {
								log.blockhit(w - 1, q - 1);
								playSound(true);
								block[q - 1][w - 1] = null;
							}
							if (!alreadychanged) {
								x = x * 2 - x;
								angle *= -1;
								alreadychanged = true;
							}

						}
						// Block rechts
						else if ((int) x + 25 == block[q - 1][w - 1].getX() && (y + 25 > block[q - 1][w - 1]
								.getLocation().getY()
								&& y < block[q - 1][w - 1].getLocation().getY() + block[q - 1][w - 1].getHeight())) {
							hit = true;
							if (block[q - 1][w - 1].getHitsLeft() > 1) {
								block[q - 1][w - 1].reduceHealth();
								playSound(true);
							} else {
								log.blockhit(w - 1, q - 1);
								playSound(true);
								block[q - 1][w - 1] = null;
							}
							if (!alreadychanged) {
								x = x * 2 - x;
								angle *= -1;
								alreadychanged = true;
							}
						}
					}
				}
			}
		} else if (y > 550) { // Kollision auf der unteren Seite
			// speedUp();
			double x550 = hitWall(altX - (altY - 550) / (altY - y) * (altX - x), false); // x
																							// position
																							// bei
																							// 64
			if (posPlayer2 < x550 && x550 < posPlayer2 + 130) { // Schl�ger getroffen
				if (posPlayer2 < x550 && x550 < posPlayer2 + 40) { //Oberes Schl�gerdrittel
					angle = (70 + angle) * Math.random() - 70;
				}
				else if (posPlayer2 + 90 < x550 && x550 < posPlayer2 + 130) { //Unteres Schl�gerdrittel
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
	 * L�sst den Ball von der oberen/unteren Wand abprallen
	 * 
	 * @param y
	 *            = Position des Balls
	 * @param turnAngle
	 *            = Flag welche angibt ob der Winkel umgedreht werden soll
	 * @return = neue y Position
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

	public void playSound(boolean block) {
		if (block) {
			SoundClips.getInstance().setFile(1);
		} else {
			SoundClips.getInstance().setFile(2);
		}
	}

	/**
	 * Erh�ht die Geschwindigkeit des Balls
	 */
	// private void speedUp() {
	// speed *= 1.05;
	// }
}
