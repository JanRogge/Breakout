package de.tudarmstadt.informatik.fop.breakout.ui;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

/**
 * SoundClips ist dafuer verantwortlich dass die Hintergrundmusik abgespielt wird
 * 
 * @author Jan Rogge
 */
public class SoundClips extends Thread {
	private static SoundClips instance;
	static Clip music;
	static Clip music2;
	static Clip music3;
	static Clip music4;
	long clipTime;
	int file = 0;
	AudioInputStream song = null;
	AudioInputStream song2 = null;
	AudioInputStream song3 = null;
	AudioInputStream song4 = null;
	AudioFormat audioFormat;
	AudioFormat audioFormat2;
	AudioFormat audioFormat3;
	AudioFormat audioFormat4;
	FloatControl gainControl;
	int size;
	int size2;
	int size3;
	int size4;
	byte[] sound;
	byte[] sound2;
	byte[] sound3;
	byte[] sound4;
	DataLine.Info info;
	DataLine.Info info2;
	DataLine.Info info3;
	DataLine.Info info4;
	long i;
	long temp;
	
	/**
	 * Das Singelton-Pattern
	 */
	public static SoundClips getInstance() {
		if (instance == null) {
			instance = new SoundClips();
		}
		return instance;
	}

	/**
	 * Der Konstruktor laedt alle soundclips
	 */
	SoundClips() {
		load();
		load2();
		load3();
		load4();
	}
	
	/**
	 * Setzt die zu Abspielende SoundFile
	 * @param file int File auswahl
	 */
	public void setFile(int file){
		this.file = file;
	}

	/**
	 * Spielt die SoundClips ab
	 */
	public void run() {
		while(true){
			try {
				if(file == 1){
					music.setMicrosecondPosition(0);
					music.start();
					sleep(250);
					music.stop();
					file = 0;
				} else if(file == 2){
					music2.setMicrosecondPosition(0);
					music2.start();
					sleep(250);
					music2.stop();
					file = 0;
				} else if(file == 3){
					music3.setMicrosecondPosition(0);
					music3.start();
					sleep(1200);
					music3.stop();
					file = 0;
				} else if(file == 4){
					music4.setMicrosecondPosition(0);
					music4.start();
					sleep(1200);
					music4.stop();
					file = 0;
				} else {
					//System.out.println("");
					sleep(2);
				}
				
			}

			catch (Exception e) {
			}
		}
			

	}

	/**
	 * Laedt Song1 (hit Block)
	 */
	public void load() {
		try {
			song = AudioSystem.getAudioInputStream(new File(
					"sounds/hitBlock.wav"));
			audioFormat = song.getFormat();
			size = (int) (audioFormat.getFrameSize() * song.getFrameLength());
			sound = new byte[size];
			info = new DataLine.Info(Clip.class, audioFormat, size);
			song.read(sound, 0, size);
			music = (Clip) AudioSystem.getLine(info);
			music.open(audioFormat, sound, 0, size);
			gainControl = (FloatControl) music
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-40.0f); // Reduce volume by 10 decibels.
		} catch (Exception e) {

		}

	}

	/**
	 * Laedt Song2 (Hit Stick)
	 */
	public void load2() {
		try {
			song2 = AudioSystem.getAudioInputStream(new File(
					"sounds/hitStick.wav"));
			audioFormat2 = song2.getFormat();
			size2 = (int) (audioFormat2.getFrameSize() * song2.getFrameLength());
			sound2 = new byte[size2];
			info2 = new DataLine.Info(Clip.class, audioFormat2, size2);
			song2.read(sound2, 0, size2);
			music2 = (Clip) AudioSystem.getLine(info2);
			music2.open(audioFormat2, sound2, 0, size2);
			gainControl = (FloatControl) music2
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-40.0f); // Reduce volume by 10 decibels.
		} catch (Exception e) {

		}

	}
	
	/**
	 * Laedt Song3 (Game Start)
	 */
	public void load3() {
		try {
			song3 = AudioSystem.getAudioInputStream(new File(
					"sounds/StartGame.wav"));
			audioFormat3 = song3.getFormat();
			size3 = (int) (audioFormat3.getFrameSize() * song3.getFrameLength());
			sound3 = new byte[size3];
			info3 = new DataLine.Info(Clip.class, audioFormat3, size3);
			song3.read(sound3, 0, size3);
			music3 = (Clip) AudioSystem.getLine(info3);
			music3.open(audioFormat3, sound3, 0, size3);
			gainControl = (FloatControl) music3
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
		} catch (Exception e) {

		}

	}
	
	/**
	 * Laedt Song4 (Game Ende)
	 */
	public void load4() {
		try {
			song4 = AudioSystem.getAudioInputStream(new File(
					"sounds/EndGame.wav"));
			audioFormat4 = song4.getFormat();
			size4 = (int) (audioFormat4.getFrameSize() * song4.getFrameLength());
			sound2 = new byte[size4];
			info4 = new DataLine.Info(Clip.class, audioFormat4, size4);
			song4.read(sound4, 0, size4);
			music4 = (Clip) AudioSystem.getLine(info4);
			music4.open(audioFormat4, sound4, 0, size4);
			gainControl = (FloatControl) music4
					.getControl(FloatControl.Type.MASTER_GAIN);
			//gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
		} catch (Exception e) {

		}

	}
}