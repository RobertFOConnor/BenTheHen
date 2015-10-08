package com.yellowbyte.ben.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Fonts;

public class StartState extends State {

	private Texture title;
	
	public StartState() {
		if(BenGame.settings.isAnimated()) {
			initAnimation();
		} else {
			removeAnimation();
		}
		
		title = Assets.TITLE_TEXT;
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(title, xPos+(BenGame.WIDTH/2)-(title.getWidth()/2), (BenGame.HEIGHT/2)-(title.getHeight()/2));
		Fonts.fontM.draw(sb, BenGame.touchtoStart, (BenGame.WIDTH/2)-(BenGame.sub1.width/2), (400)-(BenGame.sub1.height/2));
		Fonts.fontS.draw(sb, "(c)   YELLOWBYTE   2015", 30, BenGame.HEIGHT-30);		
	}

	@Override
	public void update() {
		if (Gdx.input.justTouched() && animDone) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			BenGame.context.setState(new MainMenuState());
			
		} else if(!animDone) {
			slideIn();
		}
	}


	@Override
	public void dispose() {
		
	}


	@Override
	public void resume() {
		
	}
}
