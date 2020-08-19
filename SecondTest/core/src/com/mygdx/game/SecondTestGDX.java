package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.resources.Resources;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.LoadingScreen;

public class SecondTestGDX extends Game{
	
	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	
	
	/*
	public static int tileWidth = 256;
	public static int tileHeight = 256;
	
	//(256x256)
	public static int sizeMapTileWidth_BG = 8;
	public static int sizeMapTileHeight_BG = 6;
	
	//public static int sizeMapTileWidth_BG = 4;
	//public static int sizeMapTileHeight_BG = 3;
	
	//(64x64)
	public static int sizeMapTileWidth_TL = 16;
	public static int sizeMapTileHeight_TL = 12;
	
	//public static int sizeMapTileWidth_TL = 8;
	//public static int sizeMapTileHeight_TL = 6;
	
	//size (64x64) TILES
	public static int tileWidth_TL = 128;
	public static int tileHeight_TL = 128;
	*/
	
	
	
	public static int tileWidth = 256;
	public static int tileHeight = 256;
	
	//(256x256)
	public static int sizeMapTileWidth_BG = 30;
	public static int sizeMapTileHeight_BG = 30;
	
	//(64x64)
	public static int sizeMapTileWidth_TL = 60;
	public static int sizeMapTileHeight_TL = 60;
	
	//size (64x64) TILES
	public static int tileWidth_TL = 128;
	public static int tileHeight_TL = 128;
	
	
	
	/*
	public static int tileWidth = 256;
	public static int tileHeight = 256;
	
	//(256x256)
	public static int sizeMapTileWidth_BG = 30;
	public static int sizeMapTileHeight_BG = 30;
	
	//(64x64)
	public static int sizeMapTileWidth_TL = 120;
	public static int sizeMapTileHeight_TL = 120;
	
	//size (64x64) TILES
	public static int tileWidth_TL = 64;
	public static int tileHeight_TL = 64;
	*/
	
	//size (32x32) TILES PLAYER
	public static int tilePlayerWidth_TL = 64;
	public static int tilePlayerHeight_TL = 64;
	
	
	
	public static float ratioX = 1.0f;
	public static float ratioY = 1.0f;
	
	public static float ASPECT_RATIO = (float)screenWidth/(float)screenHeight;
	public static float screenCenterX = (screenWidth / 2);
	public static float screenCenterY = (screenHeight / 2);
	
	
	public static Resources resources;	
	public LoadingScreen loadingScreen;
	public GamePlayScreen gamePlayScreen;
	
	public static boolean isMouseEnabled = false;
	public static boolean debugEngineEnabled = true;
	//public static boolean graphGridEnabled = false;
	
	
	@Override
	public void create() {
		
		ShaderProgram.pedantic = false;
		
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
