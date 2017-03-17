package de.tudarmstadt.informatik.fop.breakout.logic;

import de.tudarmstadt.informatik.fop.breakout.ui.Block;
import de.tudarmstadt.informatik.fop.breakout.ui.GamePanel;

public class Logic implements Runnable {

	GamePanel game;
	private boolean run;
	Ball ball;
	Player player;
	private Block[][] blocklist;

	public Logic(GamePanel game) {
		this.game = game;
		ball = new Ball(this);
		player = new Player(this);
		generateBlocks(new TransformMaps(3).loadMap());
	}
	
	public void newGame(){
		game.newGame();
		game.generateBlocks(blocklist);
		game.showPlayer(player);
		ball = new Ball(this);
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Ball getBall(){
		return ball;
	}

	public void run() {
		while (run) {
			if (ball.move(game.getBlocks(), player.getPaddleX())) {
				player.decreaseLive();
				game.livecounter(player.getHitsLeft());
				game.moveBall(1000, 1000);
				ball = new Ball(this);
				run = false;
			}
			game.moveBall((int)ball.getX(), (int)ball.getY());
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub

	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean getRun() {
		return run;
	}

	public void blockhit(int column, int row) {
		game.removeBlock(column, row);
		blocklist[row][column] = null; // NEU
		if(checkWin()){
			game.gameende(true);
		}
	}

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

	public void gameende() {
		// TODO Auto-generated method stub
		game.gameende(false);
		
	}
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
	public void movePaddle(boolean left){
		game.movePaddle(left);
	}
	
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
