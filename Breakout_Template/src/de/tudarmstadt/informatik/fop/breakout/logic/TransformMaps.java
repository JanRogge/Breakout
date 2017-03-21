package de.tudarmstadt.informatik.fop.breakout.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Transformer f�r die Maps in 2D int Arrays
 * 
 * @author Adriano Rodrigues, Daniel Trageser
 *
 */
public class TransformMaps {
	Scanner in;
	String path;
	
	/**
	 * Konstruktor waehlt aus welche Map geladen werden soll
	 * 
	 * @param map ID der Map die geladen werden soll
	 */
	public TransformMaps(int map){
		if(map == 1){
		path = "maps/level1.map";
		} else if(map == 2){
			path = "maps/level2.map";
		} else if(map == 3){
			path = "maps/level3.map";
		} else if(map == 4){
			path = "maps/level4.map";
		} else if(map == 5){
			path = "maps/level5.map";
		} else if(map == 6){
			path = "maps/level6.map";
		} 
		
	}
	/**
	 * Erstellt aus einer .map Datei ein 2-Dimensionales int Array mit den Werten aus der Datei
	 * 
	 * @return myMaps 2D int Array 
	 */
	public int[][] loadMap() {
		int[][] myMaps = new int[10][16];
		try {
			in = new Scanner(new File(path));
			
			int i = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] temp = line.split(",");
				for (int p = 0; p < temp.length; p++) {
					myMaps[i][p] = Integer.parseInt(temp[p]);
				}
				i++;
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myMaps;
	}
}