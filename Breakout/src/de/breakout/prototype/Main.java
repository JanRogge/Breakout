package de.breakout.prototype;

public class Main {

	/**
	 * Hallo das ist ein Kommentar
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		Logic log = new Logic(main.getGamePanel());
		Thread thread = new Thread(log);
		main.getGamePanel().generateBlocks(new int[][] {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
				{1,2,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
				{1,2,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
				{1,2,3,3,3,3,3,3,3,3,3,3,3,3,2,1},
				{1,2,3,3,3,3,3,4,3,3,3,3,3,3,2,1},
				{1,2,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
				{1,2,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
											});
		thread.start();
	}
}
