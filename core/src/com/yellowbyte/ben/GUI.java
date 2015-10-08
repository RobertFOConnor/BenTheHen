package com.yellowbyte.ben;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GUI { 

	private Texture scoreBoard, livesBoard;
	private Player player;
	
	private Array<ScoreMessage> messageArray;
	private BitmapFont font;
	private Vector2 SCORE_POS, LIVES_POS;
	
	
	public GUI(Player p) {
		scoreBoard = Assets.SCOREBOARD;
		livesBoard = Assets.LIVESBOARD;
		player = p;
		
		messageArray = new Array<ScoreMessage>();
		font = Fonts.font;
		
		SCORE_POS = new Vector2(220, BenGame.HEIGHT-17);
		LIVES_POS = new Vector2(BenGame.WIDTH-110, BenGame.HEIGHT-20);
	}
	
	public void update() {
		for(ScoreMessage message : messageArray) {
			message.update();
			if(!message.isAnimating()) {
				messageArray.removeValue(message, false);
			}
		}
	}
	
	
	public void render(SpriteBatch sb) {
		sb.draw(scoreBoard, 0,BenGame.HEIGHT-scoreBoard.getHeight());
		sb.draw(livesBoard, (BenGame.WIDTH-livesBoard.getWidth()),BenGame.HEIGHT-scoreBoard.getHeight());
		font.draw(sb, "" + player.getScore(), SCORE_POS.x, SCORE_POS.y);
		font.draw(sb, "" + player.getLives(), LIVES_POS.x, LIVES_POS.y);
		
		for(ScoreMessage message : messageArray) {
			message.render(sb);
		}		
	}

	public void addScoreMessage(ScoreMessage scoreMessage) {
		messageArray.add(scoreMessage);
		
	}
}
