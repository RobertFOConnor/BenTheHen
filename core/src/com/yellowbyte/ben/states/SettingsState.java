package com.yellowbyte.ben.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Button;
import com.yellowbyte.ben.Fonts;
import com.yellowbyte.ben.TextButton;

public class SettingsState extends State {

	private TextButton animButton, skyButton;
	private Button benButton;
	private String animationSet = "OFF";
	
	public SettingsState() {
		if(BenGame.settings.isAnimated()) {
			initAnimation();
		} else {
			removeAnimation();
		}
		
		benButton = new Button(Assets.SECRET_BEN, new Vector2(xPos+200, 0));
		int random = (int) MathUtils.random(0, 50);
		
		if(random == 12 && BenGame.settings.getHighscore() > 100) {
			BenGame.benMode = true;
		} else {
			BenGame.benMode = false;
		}
		
		animButton = new TextButton("ANIMATIONS:", Fonts.menuFont,  new Vector2(xPos+50, 1200-(BenGame.sub1.height/2)));		
		skyButton = new TextButton("SKY MODE:", Fonts.menuFont, new Vector2(xPos+50, 800-(BenGame.sub1.height/2)));	
		
		if(BenGame.settings.isAnimated()) {
			animationSet = "ON";
		}
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		backButton.render(sb);
		
		animButton.render(sb);
		skyButton.render(sb);
		
		Fonts.fontM.draw(sb, ""+animationSet, xPos+BenGame.WIDTH-200, 1200-(BenGame.sub1.height/2));
		Fonts.fontM.draw(sb, ""+BenGame.settings.getSkyMode(), xPos+BenGame.WIDTH-400, 800-(BenGame.sub1.height/2));
		
		if(BenGame.benMode) {
			benButton.render(sb);
		}
	}

	
	@Override
	public void update() {
		
		if (Gdx.input.justTouched() && animDone) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			if (backButton.checkTouch(touch)) {
				BenGame.context.setState(new MainMenuState());
				
			} else if (animButton.checkTouch(touch)) {
				BenGame.settings.setAnimated(!BenGame.settings.isAnimated());
				
				if(BenGame.settings.isAnimated()) {
					animationSet = "ON";
				} else {
					animationSet = "OFF";
				}
				
				BenGame.saveData();
				
				
			} else if (skyButton.checkTouch(touch)) {
				
				if(BenGame.settings.getSkyMode() == "RANDOM") {
					BenGame.settings.setSkyMode("NORMAL");					
				} else {
					BenGame.settings.setSkyMode("RANDOM");	
				}
				BenGame.setSky();
				
				BenGame.saveData();
				
			} else if (benButton.checkTouch(touch) && BenGame.benMode) {
				BenGame.settings.setAnimated(false);
				BenGame.context.setState(new GameState(-1));				
			}	
			
		} else if(!animDone) {
			backButton.getPosition().add(animSpeed,0);
			animButton.getPosition().add(animSpeed,0);
			skyButton.getPosition().add(animSpeed,0);
			benButton.getPosition().add(animSpeed,0);
			slideIn();
		}
	}


	@Override
	public void dispose() {
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
