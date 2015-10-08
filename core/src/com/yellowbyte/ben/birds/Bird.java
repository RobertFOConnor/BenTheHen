package com.yellowbyte.ben.birds;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.entity.Entity;


public class Bird extends Entity {
	
	protected float speed;
	
	public Bird(TextureRegion t, Vector2 pos, float speed) {
		super(t, pos);
		isAnimated = false;
		this.speed = speed;
	}
	
	//animation constructor
	public Bird(Animation animation, Vector2 pos, float speed) {
		super(animation, pos);	
		this.speed = speed;
	}	
		
	
	@Override
	public void update() {
		setYPosition(speed);		
	}	
}
