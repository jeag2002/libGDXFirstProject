package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.resources.Resources;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.LoadingScreen;

public class SecondTestGDX extends Game{
	
	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	
	
	public static int sizeMapTileWidth = 150;
	public static int sizeMapTileHeight = 150;
	
	public static int tileWidth = 64;
	public static int tileHeight = 64;
	
	
	public static float ratioX = 1.0f;
	public static float ratioY = 1.0f;
	
	public static float ASPECT_RATIO = (float)screenWidth/(float)screenHeight;
	public static float screenCenterX = (screenWidth / 2);
	public static float screenCenterY = (screenHeight / 2);
	
	
	public static Resources resources;	
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
		ratioX = (float)SecondTestGDX.screenWidth /  (float)Gdx.graphics.getWidth();
		ratioY = (float)SecondTestGDX.screenHeight / (float)Gdx.graphics.getHeight();
	}
	
	

}
