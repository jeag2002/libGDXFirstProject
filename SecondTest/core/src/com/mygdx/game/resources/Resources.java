package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Resources extends AssetManager{

	private static final String TAG = "[RESOURCES]";
	
	public String imgSplash = "splash/splash-2.png";
	public String musicSplash = "music/start.mp3";
	
	public String startButton = "gui/start/Start_BTN.png";
	public String exitButton = "gui/start/Exit_1_BTN.png";
	
	public String headerSplash = "gui/start/Header.png";
	public String tableSplash = "gui/start/Table.png";
	
	
	public void loadAssets() {
		load(imgSplash,Texture.class);
		load(musicSplash,Music.class);
		
		load(startButton,Texture.class);
		load(exitButton,Texture.class);
		
		load(headerSplash,Texture.class);
		load(tableSplash,Texture.class);
		
		Gdx.app.log(TAG, "ASSETS LOADED");
	}
	
	
	public void initLoadedAssets() {
	}
}
