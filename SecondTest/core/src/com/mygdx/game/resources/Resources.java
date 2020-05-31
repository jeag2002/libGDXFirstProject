package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Resources extends AssetManager{

	private static final String TAG = "[RESOURCES]";
	
	public String imgSplash = "splash/splash-2.png";
	public String imgIntermission = "splash/intermission_4.jpg";
	
	private String fnt = "fonts/Bangers_bitmap.fnt";
	public BitmapFont font1;
	
	public String musicSplash = "music/start.mp3";
	public String musicIntermission = "music/intermission.mp3";
	public String musicGameplay_1 = "music/gameplay_1.mp3";
	public String musicGameplay_2 = "music/gameplay_2.mp3";
	public String musicGameplay_3 = "music/gameplay_3.mp3";
	public String musicGameplay_4 = "music/gameplay_4.mp3";
	public String musicGameplay_5 = "music/gameplay_5.mp3";
	public String musicGameplay_6 = "music/gameplay_6.mp3";
	
	public String startButton = "gui/start/Start_BTN.png";
	public String exitButton = "gui/start/Exit_1_BTN.png";
	
	public String headerSplash = "gui/start/Header.png";
	public String tableSplash = "gui/start/Table.png";
	
	//blocks
	public String block_A_01 = "maps/terrain/Block_A_01.png";
	public String block_A_02 = "maps/terrain/Block_A_02.png";
	
	public String ground_tile_01_A = "maps/terrain/Ground_Tile_01_A.png"; 
	public String ground_tile_01_B = "maps/terrain/Ground_Tile_01_B.png";
	public String ground_tile_01_C = "maps/terrain/Ground_Tile_01_C.png";
	
	public String ground_tile_02_A = "maps/terrain/Ground_Tile_02_A.png";
	public String ground_tile_02_B = "maps/terrain/Ground_Tile_02_B.png";
	public String ground_tile_03_C = "maps/terrain/Ground_Tile_02_C.png";
	
	
	public void loadAssets() {
		
		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.magFilter = Texture.TextureFilter.Linear;
		fontParam.minFilter = Texture.TextureFilter.Linear;
		
		load(fnt,BitmapFont.class,fontParam);
		
		load(imgSplash,Texture.class);
		load(imgIntermission,Texture.class);
		
		load(musicSplash,Music.class);
		load(musicIntermission,Music.class);
		
		load(musicGameplay_1,Music.class);
		load(musicGameplay_2,Music.class);
		load(musicGameplay_3,Music.class);
		load(musicGameplay_4,Music.class);
		load(musicGameplay_5,Music.class);
		load(musicGameplay_6,Music.class);
		
		load(startButton,Texture.class);
		load(exitButton,Texture.class);
		
		load(headerSplash,Texture.class);
		load(tableSplash,Texture.class);
		
		
		load(block_A_01,Texture.class);				
		load(block_A_02,Texture.class);
		
		load(ground_tile_01_A,Texture.class); 
		load(ground_tile_01_B,Texture.class);
		load(ground_tile_01_C,Texture.class);
		
		load(ground_tile_02_A,Texture.class);
		load(ground_tile_02_B,Texture.class);
		load(ground_tile_03_C,Texture.class); //1
		
		
		Gdx.app.log(TAG, "ASSETS LOADED");
	}
	
	
	public void initLoadedAssets() {
		
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
		font1.getData().setScale(0.75f,0.75f);
		
	}
}
