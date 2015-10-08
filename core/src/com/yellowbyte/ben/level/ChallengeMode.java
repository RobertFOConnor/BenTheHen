package com.yellowbyte.ben.level;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.Assets;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.Player;
import com.yellowbyte.ben.birds.Bird;
import com.yellowbyte.ben.birds.Wiggles;
import com.yellowbyte.ben.birds.Zoomer;
import com.yellowbyte.ben.entity.EntityManager;

public class ChallengeMode extends Mode {

	private Player player;
	private EntityManager entityManager;
	
	public ChallengeMode(Player player, EntityManager entityManager) {
		this.player = player;
		this.entityManager = entityManager;
		
		GOAL_NUMBER = -1;
	}
	
	
	@Override
	public void saveBird() {
		
		int score = player.getScore();
		
		
		if (score % 20 == 0) {
			if (score % 100 == 0) {
				player.addLives(1);

				if (score % 1000 == 0) {
					SPEED_LIMIT += 1;
					CHICK_LIMIT++;
				}
			}

			if (CHICK_NUM < CHICK_LIMIT) {
				CHICK_NUM++;
			}
		}

		if(FALL_SPEED < SPEED_LIMIT) {
			FALL_SPEED += 1;
		}
	}

	@Override
	public void addBird(Vector2 birdPos) {
		
		int score = player.getScore();		
		int firstTeir = 100;
		
		float birdRandom = 0;
		
		if(score > firstTeir) {
			birdRandom = (int) MathUtils.random(0, 10);
		}
		
		int birdSpeed = (int) MathUtils.random(FALL_SPEED, FALL_SPEED+4);
		
		if(birdRandom>7 && score > firstTeir){
			if(BenGame.settings.isAnimated()) {
				entityManager.addEntity(new Wiggles(Assets.GREEN_ANIM, birdPos, birdSpeed));			
			} else {
				entityManager.addEntity(new Wiggles(EntityManager.chick2, birdPos, birdSpeed));
			}
		} else if(birdRandom==3 && score > 300){

			entityManager.addEntity(new Zoomer(EntityManager.chick3, birdPos, birdSpeed));	
			
		} else {
			if(BenGame.settings.isAnimated()) {
				entityManager.addEntity(new Bird(Assets.CHICK_ANIM, birdPos, birdSpeed));
			} else {
				entityManager.addEntity(new Bird(EntityManager.chick, birdPos, birdSpeed));
			}
		}
	}

}
