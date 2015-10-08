package com.yellowbyte.ben.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected TextureRegion texture;
	private Animation animation;
	protected Vector2 pos;
	float stateTime = 0f;
	protected int type = 1;
	protected int worth = 1;
	
	protected boolean isAnimated = false;
	
	public Entity(Vector2 pos) {
		this.texture = null;
		this.pos = pos;
	}
	
	public Entity(TextureRegion texture, Vector2 pos) {
		this.texture = texture;
		this.pos = pos;
	}
	
	public Entity(Animation animation, Vector2 pos) {
		this.animation = animation;
		this.texture = animation.getKeyFrame(stateTime, true);
		this.pos = pos;
		isAnimated = true;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}

	public void renderAnim(SpriteBatch sb) {
		stateTime += Gdx.graphics.getDeltaTime();
		sb.draw(animation.getKeyFrame(stateTime, true), pos.x, pos.y);

	}
	
	public void setXPosition(float XSpeed) {
		this.pos.x += XSpeed;
	}
	
	public void setYPosition(float fallSpeed) {
		this.pos.y -= fallSpeed;
	}
	
	public Vector2 getPosition() {
		return pos;
	}
	
	public void setTexture(TextureRegion t) {
		texture = t;
	}
	
	public TextureRegion getTexture() {
		return texture;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pos.x, pos.y, texture.getRegionWidth(), texture.getRegionHeight());
	}

	public boolean checkTouch(Vector2 touch) {
		if(getBounds().contains(touch)) {
			return true;
		}
		return false;
	}
	
	public boolean isAnimated() {
		return isAnimated;
	}
	
	public int getWorth() {
		return worth;
	}
}
