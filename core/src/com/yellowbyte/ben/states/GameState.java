package com.yellowbyte.ben.states;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yellowbyte.ben.BenGame;
import com.yellowbyte.ben.GUI;
import com.yellowbyte.ben.ParticleManager;
import com.yellowbyte.ben.Player;
import com.yellowbyte.ben.camera.OrthoCamera;
import com.yellowbyte.ben.entity.EntityManager;

public class GameState extends State {
	
	
	//Display Objects
	private Player player;
	private EntityManager entityManager;
	private ParticleManager particleManager;
	private GUI GUIManager;
	private OrthoCamera camera;
	
	private FPSLogger logger;

	
	public GameState(int level) {
		
		//BenGame.googleServices.showAds(false);
		
		camera = BenGame.camera;
		player = new Player(3);
		
		
		particleManager = new ParticleManager();
		GUIManager = new GUI(player);		
		entityManager = new EntityManager(camera, level, player, particleManager, GUIManager);
		
		//Initialize Background
		BenGame.setSky();	
		
		logger = new FPSLogger();
	}
	
	
	@Override
	public void update() {		
		entityManager.update();		
		
		GUIManager.update();
	}	
	
	
	@Override
	public void render(SpriteBatch sb) {		
		
		particleManager.render(sb);

		entityManager.render(sb);
		
		GUIManager.render(sb);
		
		logger.log();		
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