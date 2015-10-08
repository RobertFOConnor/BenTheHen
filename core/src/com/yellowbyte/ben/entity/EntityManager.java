package com.yellowbyte.ben.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.GUI;
import com.yellowbyte.ben.ParticleManager;
import com.yellowbyte.ben.Player;
import com.yellowbyte.ben.ScoreMessage;
import com.yellowbyte.ben.camera.OrthoCamera;
import com.yellowbyte.ben.level.ChallengeMode;
import com.yellowbyte.ben.level.LevelMode;
import com.yellowbyte.ben.level.Mode;

public class EntityManager {

	private final Array<Entity> entities = new Array<Entity>();
	
	private OrthoCamera camera;
	private Player player;
	private GUI GUIManager;
	private ParticleManager particleManager;
	private Mode mode;
	public static TextureRegion chick, chick2, chick3;

	// Sound Array
	private Array<Sound> soundArray;
	
	//Timer
	private long startTime; // Timer variables.
	private long duration = 1000;
	private long timeElapsed;
	private String timer;
	
	
	private Vector2 touch;

	
	
	public EntityManager(OrthoCamera camera, int modeNum, Player player, ParticleManager particleManager, GUI GUIManager) {
		startTime = System.nanoTime();
		
		this.camera = camera;
		this.player = player;
		this.particleManager = particleManager;
		this.GUIManager = GUIManager;
		
		if(modeNum == -1) {
			mode = new ChallengeMode(player, this);
		} else {
			mode = new LevelMode(modeNum, player, this);
		}
		
		
		touch = new Vector2(0,0);
		
		soundArray = new Array<Sound>();
			
		
		if(BenGame.benMode) {
			TextureRegion ben = Assets.BEN;
			chick = ben;
			chick2 = ben;
			chick3 = ben;
			
			//Initialize Sounds		
			soundArray.add(Assets.BEN_SOUND);
			
			/*if (BenGame.googleServices.isSignedIn()) {
				 BenGame.googleServices.unlockAchievementGPGS("CgkIw_f5uq8MEAIQBw");
			}*/
			
		} else {
			chick = Assets.CHICK1;
			chick2 = Assets.GREEN1;
			chick3 = Assets.BLUE1;			
			
			
			//Initialize Sounds
			soundArray.add(Assets.CLUCK_1);
			soundArray.add(Assets.CLUCK_2);
			soundArray.add(Assets.CLUCK_3);
		}
	}
	
	public void update() {
		for(Entity e : entities) {
			e.update();
			
			if(e.getPosition().y < 0-(e.getTexture().getRegionHeight())) { //Bird reaches bottom of screen.
				removeEntity(e);
				player.loseLife();
				if (mode.getFallSpeed() > 10) {
					mode.setFallSpeed(mode.getFallSpeed()-6);
				}
			}
		}
		
		if (Gdx.input.justTouched()) {
			touch = camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			checkTouches(touch);
		}
		
		
		timeElapsed = duration - ((System.nanoTime() - startTime) / 1000000);
		
		if(timeElapsed < 0) {
			addBird();
			startTime = System.nanoTime();
		}		
	}
	
	public void checkTouches(Vector2 touch) {
		for(Entity e : entities) {
			if(e.checkTouch(touch)) {
				removeEntity(e);
				
				saveBird(e);		
			}
		}
	}	
	
	public void render(SpriteBatch sb) {
		for(Entity e : entities) {
			
			if(e.isAnimated) {
				e.renderAnim(sb);
			} else {
				e.render(sb);
			}			
		}
	}
	
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	/*Adds a new bird to the screen*/	
	public void addBird() {
		
		float randomX = (float) (Math.random() * (BenGame.WIDTH-chick.getRegionWidth()));
		Vector2 birdPos = new Vector2(randomX, BenGame.HEIGHT);

		mode.addBird(birdPos);
	}
	
	
	public void saveBird(Entity e) {
		
		createEffect(e.type);
		
		player.addScore(e.getWorth());
		

		if(player.getScore()%10 == 0) {	
			GUIManager.addScoreMessage(new ScoreMessage(player.getScore() + "+", new Vector2(touch.x, touch.y)));
		}
		
		
		mode.saveBird();
		
		
		if(BenGame.settings.isSoundEnabled()) {
			Sound sound = soundArray.get((int) (Math.random() * soundArray.size));
			sound.play();
		}

		BenGame.darkenSky();
		
		if(player.getScore() < mode.getGoalNumber() || mode.getGoalNumber() == -1) {
			//addBird();
		} else {
			player.endGame();
		} 			
	}
	

	
	public void removeEntity(Entity entity) {
		entities.removeValue(entity, false);
	}

	
	
	private void createEffect(int birdtype) {
		
		if(birdtype == 1) {
			particleManager.addEffect(ParticleManager.EffectType.Yellow, touch.x, touch.y);
		} else if(birdtype == 2) {
			particleManager.addEffect(ParticleManager.EffectType.Green, touch.x, touch.y);
		} else {
			particleManager.addEffect(ParticleManager.EffectType.Blue, touch.x, touch.y);
		}	
	}
}
