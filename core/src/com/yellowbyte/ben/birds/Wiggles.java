package com.yellowbyte.ben.birds;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.BenGame;

import java.util.Random;

public class Wiggles extends Bird {

	private boolean movingRight = true;
	private float XSpeed = 1;
	private float maxSpeed = 10;
	
	public Wiggles(TextureRegion t, Vector2 pos, float speed) {
		super(t, pos, speed);
		isAnimated = false;
		setup();
	}
	
	public Wiggles(Animation animation, Vector2 pos, float speed) {
		super(animation, pos, speed);
		setup();
	}
	
	private void setup() {
		Random random = new Random();
		movingRight = random.nextBoolean();
		type = 2;
	}
	
	@Override
	public void update() {
		
		setYPosition(speed);
		
		if(movingRight) {
			setXPosition(XSpeed);
			
			if(pos.x+texture.getRegionWidth() > BenGame.WIDTH) {
				movingRight = false;
				XSpeed = 1;
			}
		} else {
			setXPosition(-XSpeed);
			
			if(pos.x < 0) {
				movingRight = true;
				XSpeed = 1;
			}
		}
		
		if(XSpeed < maxSpeed) {
			XSpeed++;
		}
	}	
}
