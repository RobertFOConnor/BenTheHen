package com.yellowbyte.ben.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Fonts;

public class CreditsState extends State {


	public CreditsState() {
		if(BenGame.settings.isAnimated()) {
			initAnimation();
		} else {
			removeAnimation();
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		backButton.render(sb);
		Fonts.fontM.draw(sb, "Created by: Robert O'Connor", xPos+(BenGame.WIDTH/2)-(BenGame.sub1.width/2), (BenGame.HEIGHT-600)-(BenGame.sub1.height/2));	
		Fonts.font.draw(sb, "Built with LibGDX", xPos+(BenGame.WIDTH/2)-(BenGame.sub1.width/2), (BenGame.HEIGHT-800)-(BenGame.sub1.height/2));
		Fonts.font.draw(sb, "Written in Java", xPos+(BenGame.WIDTH/2)-(BenGame.sub1.width/2), (BenGame.HEIGHT-1000)-(BenGame.sub1.height/2));
	}

	
	@Override
	public void update() {
		
		if (Gdx.input.justTouched() && animDone) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			if (backButton.checkTouch(touch)) {
				BenGame.context.setState(new MainMenuState());
				
			} 
		} else if(!animDone) {
			backButton.setXPosition(animSpeed);
			slideIn();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
