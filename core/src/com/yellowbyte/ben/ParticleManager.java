package com.yellowbyte.ben;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ParticleManager {

	//Particle Objects
	private ParticleEffectPool yellowfeatherPool, greenfeatherPool, bluefeatherPool;
	private Array<PooledEffect> effects = new Array<PooledEffect>();
	
	public enum EffectType { 
    	Yellow,
    	Green,
    	Blue
   }
	
	public ParticleManager() {
		ParticleEffect particleEffect = new ParticleEffect();
		ParticleEffect particleEffect2 = new ParticleEffect();
		ParticleEffect particleEffect3 = new ParticleEffect();
		
		particleEffect.load(Gdx.files.internal("effects/chickpop.p"), Gdx.files.internal("effects"));
		yellowfeatherPool = new ParticleEffectPool(particleEffect, 1, 2);
		particleEffect2.load(Gdx.files.internal("effects/chickpop.p"), Gdx.files.internal("effects2"));
		greenfeatherPool = new ParticleEffectPool(particleEffect2, 1, 2);
		particleEffect3.load(Gdx.files.internal("effects/chickpop.p"), Gdx.files.internal("effects3"));
		bluefeatherPool = new ParticleEffectPool(particleEffect3, 1, 2);	
	}
	
	public void addEffect(EffectType type, float x, float y) {
		PooledEffect effect;
		
		switch (type) {
		case Yellow:
			effect = yellowfeatherPool.obtain();
			break;
		case Green:
			effect = greenfeatherPool.obtain();
			break;
		case Blue:
			effect = bluefeatherPool.obtain();
			break;
		default:
			effect = yellowfeatherPool.obtain();
			break;
		}
		
		effect.setPosition(x, y);
		effects.add(effect);
	}
	
	public void render(SpriteBatch sb) {
		for (int i = effects.size - 1; i >= 0; i--) {
		    PooledEffect effect = effects.get(i);
		    effect.draw(sb, Gdx.graphics.getDeltaTime());
		    if (effect.isComplete()) {
		        effect.free();
		        effects.removeIndex(i);
		        effect.dispose();
		    }
		}
	}
}
