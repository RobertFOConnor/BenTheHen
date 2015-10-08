package com.yellowbyte.ben.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Button;
import com.yellowbyte.ben.Fonts;
import com.yellowbyte.ben.TextButton;

public class GameOverState extends State{

	private Texture gameOver;
	private TextButton againButton, leaderboardsButton, fSc, hSc;
	
	public GameOverState() {
		//BenGame.googleServices.showAds(true);
		
		initAnimation();
		gameOver = Assets.GAMEOVER;
		
		againButton = new TextButton("Try Again?", Fonts.menuFont, new Vector2((BenGame.WIDTH/2)-250, yPos+720));
		leaderboardsButton = new TextButton("Top Scores", Fonts.font, new Vector2(BenGame.WIDTH-280, (BenGame.HEIGHT-240)));

		
		TextureRegion back = Assets.BACK;
		backButton = new Button(back, new Vector2((back.getRegionWidth()/3), BenGame.HEIGHT-(back.getRegionHeight()+(back.getRegionWidth()/3)+140)));
		
		fSc = new TextButton( BenGame.finalScore, Fonts.fontM, new Vector2(0, yPos+(500)));
		hSc = new TextButton( BenGame.hsLine, Fonts.font, new Vector2(0, yPos+(380)));
		
		fSc.center();
		hSc.center();
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(gameOver, (BenGame.WIDTH/2)-(gameOver.getWidth()/2), yPos+(BenGame.HEIGHT/2)-(gameOver.getHeight()/2)+100);
		againButton.render(sb);
		
		
		if(animDone) {
			backButton.render(sb);
			leaderboardsButton.render(sb);
		}		
		
		fSc.render(sb);
		hSc.render(sb);
	}

	@Override
	public void update() {
		if (Gdx.input.justTouched() && animDone) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			if (againButton.checkTouch(touch)) {
				BenGame.context.setState(new GameState(-1));
			} else if (leaderboardsButton.checkTouch(touch)) {
				//BenGame.googleServices.showScores();
			} else if (backButton.checkTouch(touch)) {
				BenGame.context.setState(new MainMenuState());
			}
		} else if(!animDone) {
			againButton.getPosition().add(0, animSpeed);
			fSc.getPosition().add(0, animSpeed);
			hSc.getPosition().add(0, animSpeed);
			slideUp();
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
