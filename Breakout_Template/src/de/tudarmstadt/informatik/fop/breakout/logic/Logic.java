package de.tudarmstadt.informatik.fop.breakout.logic;

import de.tudarmstadt.informatik.fop.breakout.ui.Block;
import de.tudarmstadt.informatik.fop.breakout.ui.GamePanel;
import de.tudarmstadt.informatik.fop.breakout.ui.SoundClips;

/**
 * Logic Einheit
 * Zustaendig f�r Abfragen und den Ablauf des Spiels
 * 
 * @author Jan Rogge
 *
 */
public class Logic implements Runnable {

	GamePanel game;
	private boolean run;
	Ball ball;
	Player player;
	private Block[][] blocklist;

	/**
	 * Konstruktor
	 * 
	 * @param game GamePanel(Anzeige des Spiels)
	 */
	public Logic(GamePanel game) {
		this.game = game;
	}
	
	/**
	 * Legt einen neuen Ball und Spieler an l�dt eine neue Karte und startet ein neues Spiel
	 */
	public void newGame(){
		SoundClips.getInstance().setFile(3);
		ball = new Ball(this);
		player = new Player(this);
		generateBlocks(new TransformMaps(1).loadMap()); //Map hier auswählen 1-6
		game.newGame();
		game.generateBlocks(blocklist);
		game.showPlayer(player);
	}
	
	/**
	 * Gibt den Spieler zurueck
	 * @return Player player
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Gibt den Ball zurueck
	 * @return Ball ball
	 */
	public Ball getBall(){
		return ball;
	}

	/**
	 * Ablauf des Spiels
	 */
	public void run() {
		while (run) {
			if (ball.move(game.getBlocks(), player.getPaddleX())) { //Bewegt den Ball einen Schritt und schaut ob der Ball nach unten rausgeflogen ist
				player.decreaseLive();
				game.livecounter(player.getHitsLeft());
				game.moveBall(1000, 1000);
				ball = new Ball(this);
				run = false;
			}
			game.moveBall((int)ball.getX(), (int)ball.getY()); //Bewegt den ball auf der Anzeige zu der neuen Position
			try {
				Thread.sleep(8); //wartet 8 millisekunden
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Setzt das run f�r den Thread
	 * @param run boolean true or false zum anhalten des Threads
	 */
	public void setRun(boolean run) {
		this.run = run;
	}

	/**
	 * Gibt zur�ck ob der Thread aktuell l�uft oder nicht
	 * @return run 
	 */
	public boolean getRun() {
		return run;
	}

	/**
	 * Wird aufgerufen wenn ein Block getroffen wird
	 * l�scht diesen block vom Spielfield und aus der Liste
	 * Wenn alle Bl�cke weg sind wird das Spiel beendet
	 * 
	 * @param column x
	 * @param row y
	 */
	public void blockhit(int column, int row) {
		game.removeBlock(column, row);
		blocklist[row][column] = null; // NEU
		if(checkWin()){
			game.gameende(true);
			SoundClips.getInstance().setFile(4);
		}
	}

	/**
	 * Sucht einen Block anhand seiner ID aus der Liste an Bl�cken
	 * 
	 * @param blockID ID des Blocks
	 * @return Block Object
	 */
	public Block findBlock(String blockID){
		for(int y = 1;y <=blocklist.length;y++){
			for(int x = 1;x <=blocklist[y-1].length;x++){
				if(blocklist[y-1][x-1] != null){
					if(blocklist[y-1][x-1].getID().equals(blockID)){
						return blocklist[y-1][x-1];
					}
				}
				
			}
		}
		return null;
	}

	/**
	 * Wird aufgerufen wenn das Spiel verloren wurde
	 */
	public void gameende() {
		SoundClips.getInstance().setFile(4);
		game.gameende(false);
	}
	
	/**
	 * Ueberprueft ob die Liste an bloecken leer ist
	 * @return true=wenn alle bloecke weg sind, false = sollte noch mindestens ein block vorhanden sein
	 */
	public boolean checkWin(){
		for(int y = 1;y <=blocklist.length;y++){
			for(int x = 1;x <=blocklist[y-1].length;x++){
				if(blocklist[y-1][x-1] != null){
					return false;
				}
				
			}
		}
		return true;
	}
	
	/**
	 * Bewegt den Spieler in der Anzeige nach links oder rechts
	 * @param left true = links, false = rechts
	 */
	public void movePaddle(boolean left){
		game.movePaddle(left);
	}
	
	/**
	 * Generiert aus einem 2D int Array eine Liste aller vorhandenen Bloecke
	 * @param list 2D int Array mit werten von 0-4 und der groe�e [10][16]
	 */
	public void generateBlocks(int[][] list){
		blocklist = new Block[10][16];
		for(int y = 1;y <=list.length;y++){
			for(int x = 1;x <=list[y-1].length;x++){
				if(list[y-1][x-1] == 1){
					blocklist[y-1][x-1] = new Block(x,y,1, "block" + (x-1) + "_" + (y-1));
				} else if(list[y-1][x-1] == 2){
					blocklist[y-1][x-1] = new Block(x,y,2, "block" + (x-1) + "_" + (y-1));
				} else if(list[y-1][x-1] == 3){
					blocklist[y-1][x-1] = new Block(x,y,3, "block" + (x-1) + "_" + (y-1));
				} else if(list[y-1][x-1] == 4){
					blocklist[y-1][x-1] = new Block(x,y,4, "block" + (x-1) + "_" + (y-1));
				} 
			}
		}
	}
}
