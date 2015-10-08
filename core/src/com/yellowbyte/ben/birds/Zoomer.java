package com.yellowbyte.ben.birds;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Zoomer extends Bird {

	private float YSpeed = 10;
	private float YSpeedLimit = 30;
	
	public Zoomer(TextureRegion t, Vector2 pos, float speed) {
		super(t, pos, speed);
		type = 3;
	}
	

	@Override
	public void update() {
		
		setYPosition(YSpeed);
		
		if(YSpeed < YSpeedLimit) {
			YSpeed++;
		}
	}	
}
