package com.gdx.game.elements.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.engine.logic.GameLevelInformation;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.engine.parallax.ParallaxBackground;
import com.gdx.game.engine.parallax.ParallaxLayer;

public class Background {
	
	private ParallaxBackground parallaxBackground;
	
	//parallax
	private static final float bgSpeed = -500.0f;
	
	
	public Background() {
		init();
	}
	
	public void init() {
		 ParallaxLayer layers[] = new ParallaxLayer[4];
	     layers[0] = new ParallaxLayer(0.0f, 0.15f);  //BG
	     layers[1] = new ParallaxLayer(0.0f, 0.4f);   //Stars
	     layers[2] = new ParallaxLayer(0.0f, 0.55f);  //Planets
	     layers[3] = new ParallaxLayer(0.0f, 1.0f);   //Meteors
	     
	     String [] backgroundLayers = GameLevelInformation.getBackgroundLevel(GameLevelInformation.getLevel());
	     
	     layers[0].addPart(new TextureRegion(FirstTestGDX.resources.get(backgroundLayers[0], Texture.class)));
	     layers[1].addPart(new TextureRegion(FirstTestGDX.resources.get(backgroundLayers[1], Texture.class)));
	     layers[2].addPart(new TextureRegion(FirstTestGDX.resources.get(backgroundLayers[2], Texture.class)));
	     layers[3].addPart(new TextureRegion(FirstTestGDX.resources.get(backgroundLayers[3], Texture.class)));
	    
	     parallaxBackground = new ParallaxBackground(layers);
	     
	     
	}
	
	public void update(float delta) {
		parallaxBackground.move(delta, 0.0f, GameLevelLogic.speedUpFactor * bgSpeed);
	}
	
	public void draw(SpriteBatch sb) {
		parallaxBackground.draw(sb);
	}

}
