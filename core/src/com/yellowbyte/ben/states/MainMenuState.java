package com.yellowbyte.ben.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Button;
import com.yellowbyte.ben.Fonts;
import com.yellowbyte.ben.TextButton;

public class MainMenuState extends State{

	private TextButton newgameButton, achievementsButton, leaderboardsButton, settingsButton, rateButton;
	private Button creditsButton;
	
	public MainMenuState() {
		if(BenGame.settings.isAnimated()) {
			initAnimation();
		} else {
			removeAnimation();
		}
		
		newgameButton = new TextButton("NEW GAME", Fonts.menuFont, new Vector2(xPos, 1400));
		newgameButton.center();
		achievementsButton = new TextButton("ACHIEVEMENTS", Fonts.menuFont, new Vector2(xPos, 1100));
		achievementsButton.center();
		leaderboardsButton = new TextButton("LEADERBOARDS", Fonts.menuFont, new Vector2(xPos, 800));
		leaderboardsButton.center();
		creditsButton = new Button(Assets.CREDITS, new Vector2(-BenGame.WIDTH+30, 30));
		settingsButton = new TextButton("SETTINGS", Fonts.menuFont, new Vector2(xPos, 500));
		settingsButton.center();
		
		rateButton = new TextButton("RATE APP!", Fonts.font, new Vector2(xPos, 70));
		rateButton.center();
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		newgameButton.render(sb);
		achievementsButton.render(sb);
		leaderboardsButton.render(sb);
		settingsButton.render(sb);
		rateButton.render(sb);
	}

	
	@Override
	public void update() {
		if (Gdx.input.justTouched() && animDone) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			if (newgameButton.checkTouch(touch)) {
				BenGame.context.setState(new GameState(-1));
				
			/*} else if(achievementsButton.checkTouch(touch)) {
				BenGame.googleServices.getAchievementsGPGS();
				
			} else if(leaderboardsButton.checkTouch(touch)) {
				BenGame.googleServices.showScores();
				*/
			} else if(settingsButton.checkTouch(touch)) {
				BenGame.context.setState(new SettingsState());

			} else if(rateButton.checkTouch(touch)) {
				//BenGame.googleServices.rateGame();
			}
			
		} else if(!animDone) {
			xPos += animSpeed;
			newgameButton.getPosition().add(animSpeed, 0);
			achievementsButton.getPosition().add(animSpeed, 0);
			leaderboardsButton.getPosition().add(animSpeed, 0);
			settingsButton.getPosition().add(animSpeed, 0);
			creditsButton.getPosition().add(animSpeed, 0);
			rateButton.getPosition().add(animSpeed, 0);
			
			if(xPos >= 0) {
				animDone = true;
			} 
		}
	}


	@Override
	public void dispose() {
	}


	@Override
	public void resume() {
	}
}
