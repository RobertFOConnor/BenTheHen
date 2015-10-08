package com.yellowbyte.ben;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScoreMessage {

	private Vector2 pos;
	private String message;
	
	BitmapFont font;
	private float alpha;
	
	private boolean animating = true;
	
	
	public ScoreMessage(String message, Vector2 pos) {
		this.message = message;
		this.pos = pos;
        alpha = 1f;
        font = Fonts.fontS;
	}
	
	public void render(SpriteBatch sb) {
		font.draw(sb, message, pos.x, pos.y);
	}
	
	public void update() {
		pos.y += 3;
		alpha -= 0.01f;
		font.setColor(1, 1, 1, alpha);
		
		if(alpha <= 0) {
			animating = false;
		}
	}
	
	public boolean isAnimating() {
		return animating;
	}
}
