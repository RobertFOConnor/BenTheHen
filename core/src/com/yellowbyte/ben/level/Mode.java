package com.yellowbyte.ben.level;

import com.badlogic.gdx.math.Vector2;

public abstract class Mode {
	
	
	protected int CHICK_NUM = 1;
	protected int CHICK_LIMIT = 4;
	protected int SPEED_LIMIT = 18;
	protected int FALL_SPEED = 10;
	
	protected int GOAL_NUMBER = -1;
	
	
	public abstract void saveBird();
	
	public abstract void addBird(Vector2 birdPos);
	
	
	
	public int getChickNum() {
		return CHICK_NUM;
	}
	
	public int getFallSpeed() {
		return FALL_SPEED;
	}

	public void setFallSpeed(int f) {
		FALL_SPEED  = f;		
	}

	public int getGoalNumber() {
		return GOAL_NUMBER;
	}
}
