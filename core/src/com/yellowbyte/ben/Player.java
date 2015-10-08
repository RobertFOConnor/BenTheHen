package com.yellowbyte.ben;

import com.yellowbyte.ben.states.GameOverState;

public class Player {

	private int score = 0;
	private int lives = 3;
	
	public Player(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int amount) {
		score += amount;
		
		
		/*if(score % 10 == 0) {
			if (BenGame.googleServices.isSignedIn()) {
				 if (score >= 10) BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQAQ");
				 if (score >= 50) BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQAg");
				 if (score >= 100) BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQAw");
				 if (score >= 300) BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQBA");
				 if (score >= 1000) BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQBQ");
			}
		}*/
		/*if (BenGame.googleServices.isSignedIn()) {
			 if (score >= 10) BenGame.actionResolver.unlockAchievementGPGS(Constants.firstAchievement);
			 if (score >= 50) BenGame.actionResolver.unlockAchievementGPGS(Constants.secondAchievement);
			 if (score >= 100) BenGame.actionResolver.unlockAchievementGPGS(Constants.thirdAchievement);
			 if (score >= 300) BenGame.actionResolver.unlockAchievementGPGS(Constants.fourthAchievement);
			 if (score >= 1000) BenGame.actionResolver.unlockAchievementGPGS(Constants.fifthAchievement);
		}*/
	}
	
	public void addLives(int amount) {
		lives += amount;
	}
	
	public void loseLife() {
		lives--;
		
		if (lives <= 0) {
			endGame();
		}	
	}
	
	/*Ends the game and changes to GameOver Screen*/	
	public void endGame() {
		compareHighScore();
		
		BenGame.hsBounds = Fonts.font.getBounds(BenGame.hsLine);
		
		BenGame.finalScore = "Final Score: " + score;
		BenGame.sub1 = Fonts.fontM.getBounds(BenGame.finalScore);	
		
		BenGame.benMode = false;
		
		
		//add submit button?
		//BenGame.googleServices.submitScore(score);
		
		
		BenGame.context.setState(new GameOverState());		
	}
	
	private void compareHighScore() {
		if(score > BenGame.settings.getHighscore()) {
			
			
			BenGame.settings.setHighscore(score);		
			BenGame.hsLine = "New High Score: " + BenGame.settings.getHighscore();
		} else {
			BenGame.hsLine = "High Score: " + BenGame.settings.getHighscore();
		}
		
		BenGame.saveData();
	}
}
