package de.breakout.prototype;

public class Main {

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		Logic log = new Logic(main.getGamePanel());
		main.setLogic(log);
		SoundClips.getInstance().start();
	}
}
