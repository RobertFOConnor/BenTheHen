package com.yellowbyte.ben.google;

public interface IGoogleServices {
	public void signIn();

	public void signOut();

	public void rateGame();

	public void submitScore(long score);

	public void showScores();

	public boolean isSignedIn();

	public void unlockAchievementGPGS(String achievementId);

	public void getAchievementsGPGS();
	
	public void showAds(boolean show);
}