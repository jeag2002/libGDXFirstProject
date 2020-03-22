package com.gdx.game.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.engine.parallax.ParallaxBackground;
import com.gdx.game.engine.parallax.ParallaxLayer;
import com.gdx.game.screens.GamePlayScreen;
import java.util.Random;

public class GamePlay {
	
	private GamePlayScreen gamePlayScreen;
	private ParallaxBackground parallaxBackground;
	
	
	//game states
	private boolean started;
	private boolean gameover;
	private boolean paused;
	
	
	//parallax
	private float speedUpFactor;
	private float bgSpeed = -500.0f;
	
	
	public static Random random;
	
	public GamePlay(GamePlayScreen gs) {
		this.gamePlayScreen = gs;
		random = new Random(System.currentTimeMillis());
		init();
	}
	
	private void init() {
		initBackground();
		speedUpFactor = 1.0f;
	}
	
	private void initBackground() {
		
		 ParallaxLayer layers[] = new ParallaxLayer[4];
	     layers[0] = new ParallaxLayer(0.0f, 0.15f);  //BG
	     layers[1] = new ParallaxLayer(0.0f, 0.4f);   //Stars
	     layers[2] = new ParallaxLayer(0.0f, 0.55f);  //Planets
	     layers[3] = new ParallaxLayer(0.0f, 1.0f);   //Meteors
	     
	     
	     layers[0].addPart(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgBackgroundParallaxBG, Texture.class)));
	     layers[1].addPart(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgBackgroundParallaxStars, Texture.class)));
	     layers[2].addPart(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgBackgroundParallaxPlanets, Texture.class)));
	     layers[3].addPart(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgBackgroundParallaxMeteors, Texture.class)));
	    
	     parallaxBackground = new ParallaxBackground(layers);
	}
	
	public void update(float delta){
		parallaxBackground.move(delta, 0.0f, speedUpFactor * bgSpeed);
	}
	
	public void draw(SpriteBatch sb) {
		parallaxBackground.draw(sb);
	}
	
    public boolean isGameover() {
        return gameover;
    }
    
    public void start() {
        started = true;
        gameover = false;
    }
    
    public void pause() {
        paused = true;
    }
    
    public void resume(){
        paused = false;
    }


}
