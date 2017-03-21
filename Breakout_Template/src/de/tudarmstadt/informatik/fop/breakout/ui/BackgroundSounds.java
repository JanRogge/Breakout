package de.tudarmstadt.informatik.fop.breakout.ui;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

/**
 * BackgroundSounds ist dafuer verantwortlich dass die Hintergrundmusik abgespielt wird
 * 
 * @author Jan Rogge , Markus Theuerkauf
 */
public class BackgroundSounds extends Thread {
	private static long songPeriod;
	private static long songPeriod2;
	static Clip music;
	static Clip music2;
	long clipTime;
	boolean menu;
	AudioInputStream song = null;
	AudioInputStream song2 = null;
	AudioFormat audioFormat;
	AudioFormat audioFormat2;
	FloatControl gainControl;
	FloatControl gainControl2;
	int size;
	int size2;
	byte[] sound;
	byte[] sound2;
	DataLine.Info info;
	DataLine.Info info2;
	long i;
	long temp;

	/**
	 * Konstruktor l�dt beide Songs am Anfang und setzt die menu boolean
	 * @param menu Menue aktiv oder nicht 
	 */
	public BackgroundSounds(boolean menu) {
		this.menu = menu;
		load();
		load2();
	}

	/**
	 * Spielt die Hintergrundmusik ab
	 */
	public void run() {
		while (true) {
			try {
				music.setMicrosecondPosition(0);
				music.start();

				temp = songPeriod * 960;
				i = 0;
				while (menu && i <= temp) {
					i += 1000;
					sleep(1000);
				}
				music.stop();

				music2.setMicrosecondPosition(0);
				music2.start();

				temp = songPeriod2 * 960;
				i = 0;
				while (!menu && i <= temp) {
					i += 1000;
					sleep(1000);
				}
				music2.stop();
			}

			catch (Exception e) {
			}
		}

	}

	/**
	 * Setzten des Menue Statuses
	 * @param menu
	 */
	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	/**
	 * Laedt Song1 (Menue Musik)
	 */
	public void load() {
		try {
			song = AudioSystem.getAudioInputStream(new File(
					"sounds/Menu_Music.wav"));
			audioFormat = song.getFormat();
			size = (int) (audioFormat.getFrameSize() * song.getFrameLength());
			sound = new byte[size];
			info = new DataLine.Info(Clip.class, audioFormat, size);
			song.read(sound, 0, size);
			songPeriod = song.getFrameLength()
					/ (long) audioFormat.getFrameRate();
			music = (Clip) AudioSystem.getLine(info);
			music.open(audioFormat, sound, 0, size);
			gainControl = (FloatControl) music
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
		} catch (Exception e) {

		}

	}

	/**
	 * Laedt Song2 (Game Musik)
	 */
	public void load2() {
		try {
			song2 = AudioSystem.getAudioInputStream(new File(
					"sounds/Game_Music.wav"));
			audioFormat2 = song2.getFormat();
			size2 = (int) (audioFormat2.getFrameSize() * song2.getFrameLength());
			sound2 = new byte[size2];
			info2 = new DataLine.Info(Clip.class, audioFormat2, size2);
			song2.read(sound2, 0, size2);
			songPeriod2 = song2.getFrameLength()
					/ (long) audioFormat2.getFrameRate();
			music2 = (Clip) AudioSystem.getLine(info2);
			music2.open(audioFormat2, sound2, 0, size2);
		} catch (Exception e) {

		}

	}
}