package com.yellowbyte.ben;


public class GameManager {

	private int chickNum = 1;
	private int chickLimit = 4;
	public static float SPEEDLIMIT = 18;
	public static float FALLSPEED = 10;
	
	private Player player;
	
	public GameManager(Player player) {
		this.player = player;
	}
	
	
	public void saveBird() {
		int score = player.getScore();

		if (score % 100 == 0) {
			player.addLives(1);

			if (score % 1000 == 0) {
				SPEEDLIMIT += 1;
				chickLimit++;
			}
		}

		if (chickNum < chickLimit) {
				chickNum++;
			}				
					
		
		if(FALLSPEED < SPEEDLIMIT) {
			FALLSPEED += 1;
		}
	}
	
	public void addBird() {
		
	}
}
