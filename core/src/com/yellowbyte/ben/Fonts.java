package com.yellowbyte.ben;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {

	public static BitmapFont font, fontS, fontM, menuFont;
	
	public static void loadFonts() {
       
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 65;
        font = generator.generateFont(parameter);
        parameter.size = 100;
        fontM = generator.generateFont(parameter);
        parameter.size = 60;
        fontS = generator.generateFont(parameter);
        parameter.size = 150;
        menuFont = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!	
	}
	
	public static float getWidth(BitmapFont f, String s) {
		return f.getBounds(s).width;
	}
}
