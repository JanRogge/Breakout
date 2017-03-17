package de.tudarmstadt.informatik.fop.breakout.test.adapter;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;
import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import de.tudarmstadt.informatik.fop.breakout.logic.Ball;
import de.tudarmstadt.informatik.fop.breakout.logic.Logic;
import de.tudarmstadt.informatik.fop.breakout.logic.Player;
import de.tudarmstadt.informatik.fop.breakout.ui.Block;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.MainFrame;
import eea.engine.entity.Entity;

public class Adapter implements GameParameters {

  /*
   * the instance of our game, extends StateBasedGame
   */
	Breakout breakout;
	Ball ball;
	Player player;
	Logic log;

  //TODO you should declare the additional attributes you may require here.

	/**
	 * Use this constructor to initialize everything you need.
	 */
	public Adapter() {
		breakout = null;
		MainFrame main = new MainFrame();
		main.setVisible(false);
		log = new Logic(main.getGamePanel());
		main.setLogic(log);
		ball = log.getBall();
		player = log.getPlayer();
		log.newGame();
	}

	/* ***************************************************
	 * ********* initialize, run, stop the game **********
	 * ***************************************************
	 * 
	 * You can normally leave this code as it is.
	 */

	public StateBasedGame getStateBasedGame() {
		return breakout;
	}

	/**
	 * Diese Methode initialisiert das Spiel im Debug-Modus, d.h. es wird ein
	 * AppGameContainer gestartet, der keine Fenster erzeugt und aktualisiert.
	 * 
	 * Sie müssen diese Methode erweitern
	 */
	public void initializeGame() {

    // Initialize the game in debug mode (no GUI output)
		breakout = new Breakout(true);

	}

	/**
	 * Stop a running game
	 */
	public void stopGame() {
		breakout = null;
	}

	public void changeToGameplayState() {
		//this.getStateBasedGame().enterState(GAMEPLAY_STATE);
	}

	public void changeToHighScoreState() {
		this.getStateBasedGame().enterState(HIGHSCORE_STATE);
	}

	/* ***************************************************
	 * ********************** Ball **********************
	 * ***************************************************
	 */
	
	/**
	 * Returns a new Entity that represents a ball with ID ballID.
	 * It was added for tests, as we do not know what class/package will represent
	 * your "ball" entity.
	 * 
	 * @param ballID the ID for the new ball instance
	 * @return an entity representing a ball with the ID passed in as ballID
	 */
	public Entity createBallInstance(String ballID) {
	  //TODO write code that returns a ball instance with ID 'ballID'
	  return null;
	}

	/**
	 * Returns an instance of the IHitable interface that represents a block
	 * with the ID as passed in and the requested number of hits left (1 = next
	 * hit causes the block to vanish, 2 = it takes two hits, ...)
	 * 
	 * @param blockID the ID the returns block entity should have
	 * @param hitsUntilDestroyed the number of hits (> 0) the block should have left
	 * before it vanishes (1 = vanishes with next touch by ball)
	 * @return an entity representing a block with the given ID and hits left
	 */
	public IHitable createBlockInstance(String blockID, int hitsUntilDestroyed) {
	  //TODO write code that returns a block instance with ID 'blockID'
	  // and that requires hitsUntilDestroyed "hits" until it vanishes
	  return new Block(0,0,hitsUntilDestroyed, blockID);
	}

	/**
	 * sets the ball's orientation (angle). 
	 * Note: the name of the method is somewhat unfortunate, but is taken from EEA's entity.
	 * 
	 * @param i the new orientation angle for the ball (0...360)
	 */
	public void setRotation(int i) {
	  //TODO write code sets the ball rotation to the value passed in
		ball.setAngle(i);
	}

  /**
   * returns the ball's orientation (angle). 
   * Note: the name of the method is somewhat unfortunate, but is taken from EEA's entity.
   * 
   * @return the orientation angle for the ball (0...360)
   */
	public float getRotation() {
    //TODO write code retrieves the ball's rotation
	  return (float) ball.getAngle(); // this is only a dummy value to prevent compilation problems!
	}

	/**
	 * Sets the ball's position to the coordinate provide
	 * 
	 * @param vector2f the target position for the ball
	 */
	public void setPosition(Vector2f vector2f) {
	  //TODO provide code that sets the position of the ball to the coordinates passed in
		ball.setX(vector2f.getX() - 15);
		ball.setY(vector2f.getY() - 15);
	}

  /**
   * returns a definition of the ball's size. Typically, the size of the ball will
   * be constant, but programmers may introduce bonus items that shrink or enlarge the ball.
   * 
   * @return the size of the ball
   */
	public Vector2f getSize() {
	  //TODO write code that retrieves the size of the ball
	  return new Vector2f(ball.getSize(), ball.getSize());
	}

	/**
	 * returns the current speed of the ball's movement
	 * 
	 * @return the ball's speed
	 */
	public float getSpeed() {
    //TODO write code to retrieve the ball speed
	  return ball.getSpeed();
	}

	/**
	 * sets the current speed of the ball to the given value
	 * 
	 * @param speed the new speed of the ball
	 */
	public void setSpeed(float speed) {
    //TODO write code to set the ball speed
		ball.setSpeed(speed);
	}

	/**
	 * provide a proper code mapping to a check if your ball entity collides with
	 * 'otherEntity'. You will have to access your ball instance for this purpose.
	 * 
	 * @param otherEntity another entity that the ball may (or may not) collide with
	 * 
	 * @return true if the two entities have collided. Note: your ball should by default
	 * not collide with itself (or other balls, if there are any), null, the background,
	 * or "passable" entities (e.g. other image you have placed on the screen). It should only
	 * collide with the stick if the orientation is correct (>90 but <270 degrees, else it would
	 * "collide with the underside of the stick") but should be "gone" then already).
	 * It should also collide with the borders if the orientation is correct for this, e.g.,
	 * only collide with the top border if the orientation is fitting).
	 */
	public boolean collides(Entity otherEntity) {
	  //TODO write code to test if the ball collides with 'otherEntity'
		Block b = null;
		if(otherEntity.getID().equals(TOP_BORDER_ID)){
			b = new Block(0, 0, 9999999, otherEntity.getID());
		} else if(otherEntity.getID().equals(RIGHT_BORDER_ID)){
			b = new Block(0, 0, 9999999, otherEntity.getID());
			ball.setX(ball.getX() + 6);
			ball.setY(ball.getY());
		} else if(otherEntity.getID().equals(LEFT_BORDER_ID)){
			b = new Block(0, 0, 9999999, otherEntity.getID());
		} else if(otherEntity.getID().equals(STICK_ID)){
			ball.setX(ball.getX());
			ball.setY(ball.getY()-25);
		}
		ball.move(new Block[][]{{b},{b}}, 400);
		
	  return ball.getHit();
	}

	/* ***************************************************
	 * ********************** Player *********************
	 * ***************************************************
	 */
	
	/**
	 * ensures that the player has "value" additional lives (=additional balls left).
	 * 
	 * @param value the number of additional balls/lives to be added.
   */
	public void addLives(int value) {
	  //TODO write code to add the given number to the player's lives
		player.addHitsLeft(value);
	}

	/**
	 * ensures that the player has exactly "playerLives" balls/lives left.
	 * 
	 * @param playerLives the number of lives/balls the player shall have left
	 */
	public void setLives(int playerLives) {
	  //TODO write code to set the number of player's lives to playerLives
		player.setHitsLeft(playerLives);
	}

	/**
	 * queries your classes for the number of lives/balls the player has left
	 * 
	 * @return the number of lives/balls left
	 */
	public int getLivesLeft() {
	  //TODO write code to retrieve the number of lives left
	  return player.getHitsLeft();
	}

	/**
	 * checks if the player still has at least one live/ball left
	 * 
	 * @return true if the player still has at least one live/ball left, else false.
	 */
	public boolean hasLivesLeft() {
	  //TODO write code to test if the player has at least one live left
	  return player.hasHitsLeft();
	}

	/* ***************************************************
	 * ********************** Block **********************
	 * ***************************************************
	 */

	/**
	 * Sets a number of necessary hits for degrading this block
	 * 
	 * @param hitsLeft
	 *            number of necessary hits
	 * @param blockID
	 *            blockID ID of the chosen block
	 */
	public void setHitsLeft(int hitsLeft, String blockID) {
	  //TODO write code to set the number of required hits for 'blockID' to hitsLeft
		log.findBlock(blockID).setHitsLeft(hitsLeft);
	}

	/**
	 * Returns the number of necessary hits for degrading this block
	 * 
	 * @param blockID
	 *            ID of the chosen block
	 * @return number of hits
	 */
	public int getHitsLeft(String blockID) {
	  //TODO write code to return how many hits 'blockID' needs to vanish
	  return log.findBlock(blockID).getHitsLeft();
	}

	/**
	 * Adds a number of necessary hits for degrading this block
	 * 
	 * @param hitsLeft
	 *            number of hits added
	 * @param blockID
	 *            blockID ID of the chosen block
	 */
	public void addHitsLeft(int hitsLeft, String blockID) {
    //TODO write code to add the given number to the block's "hit capacity"
		log.findBlock(blockID).addHitsLeft(hitsLeft);
	}

	/**
	 * Returns whether the block has hits left
	 * 
	 * @param blockID
	 *            blockID ID of the chosen block
	 * @return true, if block has hits left, else false
	 */
	public boolean hasHitsLeft(String blockID) {
    //TODO write code to return if the given block still has hits left
	  return log.findBlock(blockID).hasHitsLeft();
	}

	/* ***************************************************
	 * ********************** Stick **********************
	 * ***************************************************
	 */
	
	/**
	 * returns the current position of the stick
	 * 
	 * @return the current position of the stick
	 */
	public Vector2f getStickPosition() {
	  //TODO write code to return the position of the stick
	  return new Vector2f(player.getX(), player.getY()); // these are arbitrary values(!)
	}

	/* ***************************************************
	 * ********************** Input **********************
	 * ***************************************************
	 */

	/**
	 * This Method should emulate the key down event.
	 * 
	 * @param updatetime
	 *            : Zeitdauer bis update-Aufruf
	 * @param input
	 *            : z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyDown(int updatetime, boolean input) {
	  //TODO write code that handles a "key pressed" event
	  // note: do not forget to call app.updateGame(updatetime);
		for(int i = 0; i <= updatetime; i++){
			log.movePaddle(input);
		}
		
	}

	/**
	 * This Method should emulate the pressing of the right arrow key.
	 */
	public void handleKeyDownRightArrow() {
	  //TODO write code for handling a "right arrow" key press
	  // hint: you may use the above method.
		handleKeyDown(1000, false);
	}

	/**
	 * This Method should emulate the pressing of the left arrow key.
	 */
	public void handleKeyDownLeftArrow() {
    //TODO write code for handling a "left arrow" key press
    // hint: you may use the above method.
		handleKeyDown(1000 ,true);
	}
}
