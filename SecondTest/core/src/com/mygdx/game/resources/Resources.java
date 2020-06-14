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
	public String gameButton = "gui/start/GameMode_BTN.png";
	public String settingsButton = "gui/start/Settings_BTN.png";
	public String scoreButton = "gui/start/Score_BTN.png";
	
	public String headerSplash = "gui/start/Header.png";
	public String tableSplash = "gui/start/Table.png";
	
	//blocks
	public String block_A_01 = "maps/terrain/Block_A_01.png";
	public String block_A_02 = "maps/terrain/Block_A_02.png";
	public String block_B_01 = "maps/terrain/Block_B_01.png";
	public String block_B_02 = "maps/terrain/Block_B_02.png";
	public String block_C_01 = "maps/terrain/Block_C_01.png";
	public String block_C_02 = "maps/terrain/Block_C_02.png";
	public String block_D_01 = "maps/terrain/Block_D_01.png";
	public String block_D_02 = "maps/terrain/Block_D_02.png";
	
	
	//ground
	public String ground_tile_01_A = "maps/terrain/Ground_Tile_01_A.png"; 
	public String ground_tile_01_B = "maps/terrain/Ground_Tile_01_B.png";
	public String ground_tile_01_C = "maps/terrain/Ground_Tile_01_C.png";
	public String ground_tile_01_D = "maps/terrain/Ground_Tile_01_D.png";
	
	public String ground_tile_02_A = "maps/terrain/Ground_Tile_02_A.png";
	public String ground_tile_02_B = "maps/terrain/Ground_Tile_02_B.png";
	public String ground_tile_02_C = "maps/terrain/Ground_Tile_02_C.png";
	public String ground_tile_02_D = "maps/terrain/Ground_Tile_02_D.png";
	
	//elements_terrains
	public String Palm_01 = "maps/terrain/Palm_01.png";
	public String Palm_02 = "maps/terrain/Palm_02.png";
	public String Palm_03 = "maps/terrain/Palm_03.png";
	
	public String Cactus_01 = "maps/terrain/Cactus_01.png";
	public String Cactus_02 = "maps/terrain/Cactus_02.png";
	public String Cactus_03 = "maps/terrain/Cactus_03.png";
	
	public String Czech_01 = "maps/terrain/Czech_01.png";
	public String Czech_02 = "maps/terrain/Czech_02.png";
	
	//elements
	public String platform = "elements/Platform.png";
	public String dot_a = "elements/Dot_A.png";
	public String dot_b = "elements/Dot_B.png";
	
	//player 
	public String gunPlayer_01_A = "elements/player_1/Gun_01.png";
	public String bodyPlayer_01 = "elements/player_1/Hull_01.png";
	public String track_01 = "elements/player/Track_1_A.png";
	public String track_02 = "elements/player/Track_1_B.png";
	
	//exhaust
	public String exhaustFire_01 = "elements/player/exhaust/Exhaust_01_01.png";
	public String exhaustFire_02 = "elements/player/exhaust/Exhaust_01_02.png";
	public String exhaustFire_03 = "elements/player/exhaust/Exhaust_01_03.png";
	public String exhaustFire_04 = "elements/player/exhaust/Exhaust_01_04.png";
	public String exhaustFire_05 = "elements/player/exhaust/Exhaust_01_05.png";
	public String exhaustFire_06 = "elements/player/exhaust/Exhaust_01_06.png";
	public String exhaustFire_07 = "elements/player/exhaust/Exhaust_01_07.png";
	
	//tyres
	public String tire_01 = "elements/tires/Tire_Track_01.png";
	public String tire_02 = "elements/tires/Tire_Track_02.png";
	
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
		load(gameButton,Texture.class);
		load(settingsButton,Texture.class);
		load(scoreButton,Texture.class);
		
		load(headerSplash,Texture.class);
		load(tableSplash,Texture.class);
		
		load(block_A_01,Texture.class);				
		load(block_A_02,Texture.class);
		
		load(block_B_01,Texture.class);
		load(block_B_02,Texture.class);
		
		load(block_C_01,Texture.class);
		load(block_C_02,Texture.class);
		
		load(block_D_01,Texture.class);
		load(block_D_02,Texture.class);
		
		load(ground_tile_01_A,Texture.class); 
		load(ground_tile_01_B,Texture.class);
		load(ground_tile_01_C,Texture.class);
		load(ground_tile_01_D,Texture.class);
		
		load(ground_tile_02_A,Texture.class);
		load(ground_tile_02_B,Texture.class);
		load(ground_tile_02_C,Texture.class);
		load(ground_tile_02_D,Texture.class); 
		
		load(Palm_01,Texture.class);
		load(Palm_02,Texture.class);
		load(Palm_03,Texture.class);
		
		load(Cactus_01,Texture.class);
		load(Cactus_02,Texture.class);
		load(Cactus_03,Texture.class);
		
		load(Czech_01,Texture.class);
		load(Czech_02,Texture.class);
		
		load(platform,Texture.class);
		load(dot_a,Texture.class);
		load(dot_b,Texture.class);
		
		load(gunPlayer_01_A,Texture.class);
		load(bodyPlayer_01,Texture.class);
		load(track_01,Texture.class);
		load(track_02,Texture.class);
		
		load(exhaustFire_01,Texture.class);
		load(exhaustFire_02,Texture.class);
		load(exhaustFire_03,Texture.class);
		load(exhaustFire_04,Texture.class);
		load(exhaustFire_05,Texture.class);
		load(exhaustFire_06,Texture.class);
		load(exhaustFire_07,Texture.class);
		
		load(tire_01,Texture.class);
		load(tire_02,Texture.class);
		
		
		Gdx.app.log(TAG, "ASSETS LOADED");
	}
	
	
	public void initLoadedAssets() {
		
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
		font1.getData().setScale(0.75f,0.75f);
		
	}
}
