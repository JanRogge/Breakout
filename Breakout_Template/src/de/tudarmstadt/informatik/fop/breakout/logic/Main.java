package de.tudarmstadt.informatik.fop.breakout.logic;

import de.tudarmstadt.informatik.fop.breakout.ui.MainFrame;
import de.tudarmstadt.informatik.fop.breakout.ui.SoundClips;

public class Main {

	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		Logic log = new Logic(main.getGamePanel());
		main.setLogic(log);
		SoundClips.getInstance().start();
	}
}
