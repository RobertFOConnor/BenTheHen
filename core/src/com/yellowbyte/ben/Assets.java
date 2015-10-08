package com.yellowbyte.ben;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	//public static Texture  GRASS_BG = "multibg.png";
    public static Texture TITLE_TEXT;
    public static Texture GAMEOVER;
    public static Texture chick_sprites;
    public static Texture button_sprites;
    public static TextureRegion CHICK1;
    public static TextureRegion CHICK2;
    public static TextureRegion CHICK3;
    public static TextureRegion GREEN1;
    public static TextureRegion GREEN2;
    public static TextureRegion GREEN3;
    public static Animation CHICK_ANIM;
	public static Animation GREEN_ANIM;
    public static TextureRegion BLUE1;
    public static TextureRegion BEN;
    
    public static Texture SCOREBOARD;
    public static Texture LIVESBOARD;
    
    /*----BUTTONS----*/
    
    public static TextureRegion EGG;
    public static TextureRegion BACK;
    public static TextureRegion UNMUTE;
    public static TextureRegion MUTE;
    public static TextureRegion CREDITS;
    public static TextureRegion SECRET_BEN;
    
    
    //Sounds
    public static Sound CLUCK_1;
    public static Sound CLUCK_2;
    public static Sound CLUCK_3;
    public static Sound BEN_SOUND;
    
    public static Music THEME_MUSIC;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	private static String audioPrefix = "audio\\";
	public static void load () {
		TITLE_TEXT = loadTexture("title.png");
		GAMEOVER = loadTexture("gameover.png");
		chick_sprites = loadTexture("chick_spritesheet.png");
		CHICK1 = new TextureRegion(chick_sprites, 0, 400, 400, 366);
		CHICK2 = new TextureRegion(chick_sprites, 400, 400, 400, 366);
		CHICK3 = new TextureRegion(chick_sprites, 800, 400, 400, 366);
		CHICK_ANIM = new Animation(1/15f, CHICK1, CHICK2, CHICK3);	
		CHICK_ANIM.setPlayMode(Animation.PlayMode.LOOP);
		
		GREEN1 = new TextureRegion(chick_sprites, 0, 766, 400, 366);
		GREEN2 = new TextureRegion(chick_sprites, 400, 766, 400, 366);
		GREEN3 = new TextureRegion(chick_sprites, 800, 766, 400, 366);
		GREEN_ANIM = new Animation(1/15f, GREEN1, GREEN2, GREEN3);
		GREEN_ANIM.setPlayMode(Animation.PlayMode.LOOP);
		
		BLUE1 = new TextureRegion(chick_sprites, 0, 126, 300, 274);
		BEN = new TextureRegion(chick_sprites, 300, 0, 400, 400);
		
		button_sprites = loadTexture("button_spritesheet.png");
		
		BACK = new TextureRegion(button_sprites, 0, 0, 150, 150);
		EGG =  new TextureRegion(button_sprites, 150, 0, 200, 290);
		MUTE = new TextureRegion(button_sprites, 0, 230, 100, 80);
		UNMUTE = new TextureRegion(button_sprites, 0, 150, 100, 80);
		SECRET_BEN = new TextureRegion(button_sprites, 350, 0, 500, 450);
		
		
		
		SCOREBOARD = loadTexture("score.png");
		LIVESBOARD = loadTexture("lives.png");
		CREDITS = new TextureRegion(loadTexture("credits.png"), 0, 0, 59, 85);
		
		
		
		THEME_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
		THEME_MUSIC.setLooping(true);
		THEME_MUSIC.setVolume(0.5f);
		if (BenGame.settings.isSoundEnabled()) THEME_MUSIC.play();
		CLUCK_1 = Gdx.audio.newSound(Gdx.files.internal(audioPrefix+"cluck1.wav"));
		CLUCK_2 = Gdx.audio.newSound(Gdx.files.internal(audioPrefix+"cluck2.wav"));
		CLUCK_3 = Gdx.audio.newSound(Gdx.files.internal(audioPrefix+"cluck3.wav"));
		
		BEN_SOUND = Gdx.audio.newSound(Gdx.files.internal(audioPrefix+"bensound.wav"));
	}

	public static void playSound (Sound sound) {
		if (BenGame.settings.isSoundEnabled()) sound.play(1);
	}
}
