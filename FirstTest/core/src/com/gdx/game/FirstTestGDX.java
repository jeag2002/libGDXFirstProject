package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gdx.game.resources.Resources;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.screens.LoadingScreen;

public class FirstTestGDX extends Game{
	
	//Screen settings 
	public static int screenWidth = 600;
	public static int screenHeight = 900;
	
	public static float ratioX = 1.0f;
	public static float ratioY = 1.0f;
	
	public static float ASPECT_RATIO = (float)screenWidth/(float)screenHeight;
	
	//Resources
	public static Resources resources;
	
	//Screens
	public LoadingScreen loadingScreen;
	public GamePlayScreen gamePlayScreen;
	

	@Override
	public void create() {
		resources = new Resources();
		
		loadingScreen = new LoadingScreen(this);
		gamePlayScreen = new GamePlayScreen(this);
		
		resources.loadAssets();		
		
		setScreen(loadingScreen);
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		resources.dispose();
	}
	
	
	public static void initGraphicRatio() {
		ratioX = (float)FirstTestGDX.screenWidth /  (float)Gdx.graphics.getWidth();
		ratioY = (float)FirstTestGDX.screenHeight / (float)Gdx.graphics.getHeight();
	}
		
}

