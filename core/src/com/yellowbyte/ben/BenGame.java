package com.yellowbyte.ben;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.yellowbyte.ben.camera.OrthoCamera;
import com.yellowbyte.ben.states.Context;
import com.yellowbyte.ben.states.StartState;
//import com.yellowbyte.google.IGoogleServices;

public class BenGame extends ApplicationAdapter {
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920;
	public static boolean benMode = false;	
	public static float r, g, b = 0;
	public static String touchtoStart = "[Touch to Start]";
	public static String finalScore = "";
	public static String hsLine = "";	
	public static TextBounds sub1, hsBounds;	
	//public static IGoogleServices googleServices;
	public static SaveManager saveManager;
	public static Settings settings;	
	public static OrthoCamera camera;		
	public static TextureRegion unmuteSymbol, muteSymbol;
	public static Texture bg;
	public static Button muteButton;	
	public static Context context;
	
	private SpriteBatch sb;
	private Vector2 touch;	
	

	/*public BenGame(IGoogleServices googleServices) {
		super();
		BenGame.googleServices = googleServices;
	}*/
	
	
	@Override
	public void create () {
		
		Gdx.input.setCatchBackKey(true);
		touch = new Vector2(0,0);
		sb = new SpriteBatch();
		camera = new OrthoCamera();
		camera.resize();		
		saveManager = new SaveManager(true);		
		settings = new Settings();
		
		if(saveManager.loadDataValue("SETTINGS", Settings.class) != null) {
			settings = saveManager.loadDataValue("SETTINGS", Settings.class);
		}
		
		
		Fonts.loadFonts();		
        sub1 = Fonts.fontM.getBounds(touchtoStart);
        
        setSky();		
		Assets.load();
		
		unmuteSymbol = Assets.UNMUTE;
		muteSymbol = Assets.MUTE;
		
		Vector2 mutePos = new Vector2(BenGame.WIDTH-120, 20);
		
		if(!settings.isSoundEnabled()) {
        	muteButton = new Button(muteSymbol, mutePos);
        } else {
        	muteButton = new Button(unmuteSymbol, mutePos);
        }
		
		bg = new Texture("multibg.png");		
		context = new Context(new StartState());
	}
	
	public static void setSky() {
		if(settings.getSkyMode().equals("RANDOM")) {
			r = (float) (Math.random()*1);
			g = (float) (Math.random()*1);
			b = (float) (Math.random()*1);
			
		} else {
			r = (float) 108/255;
			g = (float) 181/255;
			b = (float) 255/255;
		}
	}
	
	/*Turns the sky a darker shade of RED*/	
	public static void darkenSky() {
		
		if (r < (float) 0.7) {
			r += (float) 0.01;
		}

		if (g > (float) (52 / 255)) {
			g -= (float) 0.005;
		}

		if (b > 0) {
			b -= (float) 0.005;
		}		
	}
	

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(r, g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(bg, 0, 0);

		context.getState().update();

		if (Gdx.input.justTouched()) {
			touch = BenGame.camera.unprojectCoordinates(Gdx.input.getX(),
					Gdx.input.getY());

			if (muteButton.checkTouch(touch)) {
				settings.setSoundEnabled(!settings.isSoundEnabled());
				if (settings.isSoundEnabled()) {
					muteButton.setTexture(unmuteSymbol);
					Assets.THEME_MUSIC.play();
				} else {
					muteButton.setTexture(muteSymbol);
					Assets.THEME_MUSIC.pause();
				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			  // Do nothing
		}

		context.getState().render(sb);
		sb.draw(muteButton.getTexture(), muteButton.getPosition().x,
				muteButton.getPosition().y);
		sb.end();

	}
	
	
	@Override
	public void resume () {
		context.getState().resume();
	}
	
	@Override
	public void dispose () {
		context.getState().dispose();
		sb.dispose();
		saveData();
	}


	public static void saveData() {
		saveManager.saveDataValue("SETTINGS", settings);
	}
}
