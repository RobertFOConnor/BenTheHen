package com.yellowbyte.ben.states;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Button;

public abstract class State {
	
	public Vector2 touch;
	public Sound click;
	
	protected float xPos, yPos, animSpeed;
	protected boolean animDone = false;
	protected Button backButton;

	public abstract void render(SpriteBatch sb);

	public abstract void update();
	
	public abstract void dispose();
	
	public abstract void resume();
	
	public void initAnimation() {
		xPos = -BenGame.WIDTH;
		yPos = -BenGame.HEIGHT;
		animSpeed = 60;
		initBackButton();
	}
	
	public void removeAnimation() {
		xPos = 0;
		yPos = 0;
		animDone = true;
		initBackButton();
	}
	
	public void initBackButton() {
		TextureRegion back = Assets.BACK;
		backButton = new Button(back, new Vector2(xPos+(back.getRegionWidth()/3), BenGame.HEIGHT-(back.getRegionWidth()+(back.getRegionHeight()/3)+140)));
	}
	
	
	public void slideIn() {
		xPos += animSpeed;
		
		if(xPos >= 0) {
			animDone = true;
		} 
	}
	
	public void slideUp() {
		yPos += animSpeed;
		
		if(yPos >= 0) {
			animDone = true;
		} 
	}

}
