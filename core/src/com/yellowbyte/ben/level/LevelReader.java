package com.yellowbyte.ben.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelReader {

	private ArrayList<String> lines = new ArrayList<String>();
	private ArrayList<Integer> rules = new ArrayList<Integer>();
	
	public LevelReader(int levelNum) {
		
		// Read in the level file
		try {
			String path = "levels/L" + levelNum + ".txt";

			FileHandle file = Gdx.files.internal(path);
			BufferedReader reader = new BufferedReader(file.reader());

			String line = reader.readLine();

			for (int i = 0; line != null; i++) {
				if (i == 0) {
					lines.add(line);
				} else {
					rules.add(Integer.parseInt(line));
				}
				
				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getLines() {
		return lines;
	}
	
	public ArrayList<Integer> getRules() {
		return rules;
	}
}
