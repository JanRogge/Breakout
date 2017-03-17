package de.tudarmstadt.informatik.fop.breakout.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransformMaps {
	Scanner in;
	String path;
	
	public TransformMaps(int map){
		if(map == 1){
		path = "maps/level1.map";
		} else if(map == 2){
			path = "maps/level3.map";
		} else if(map == 3){
			path = "maps/level3.map";
		} 
		
	}
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