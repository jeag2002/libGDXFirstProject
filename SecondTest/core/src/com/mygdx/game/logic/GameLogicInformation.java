package com.mygdx.game.logic;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.BackgroundMusicEnum;
import com.mygdx.game.enums.TileMapEnum;

public class GameLogicInformation {

	public static final int START = 0;
	public static final int INTERMISSION = 1;
	public static final int GAMEPLAY = 2;
	
	public static final float PIXELS_TO_METERS = 100f;
	
	public static final int PLAYERS = 1;
	public static final int ENEMIES = 20;
	
	public static final int DESERT_LEVEL = 0;
	public static final int JUNGLE_LEVEL = 1;
	public static final int FABRIC_LEVEL = 2;
	public static final int WINTER_LEVEL = 3;
	
	public static final int FOREST_DESERT = 3;
	public static final int FOREST_JUNGLE = 3;
	public static final int FOREST_FABRIC = 1;
	public static final int FOREST_WINTER = 1;
	public static final int FOREST_DEFAULT = 1;
	
	public static final float speedUpFactor = 1.0f;
	public static final float bgSpeed = 50.0f;
	
	private static int level;
	
	public static final String backGround_Start = SecondTestGDX.resources.imgSplash; 
	public static final String backGround_Intermission = SecondTestGDX.resources.imgIntermission;
	
	public static final String backGround_Start_MP3 = SecondTestGDX.resources.musicSplash;
	public static final String backGround_Intermission_MP3 = SecondTestGDX.resources.musicIntermission;
	
	private static BackgroundMusicEnum mEnum;
	
	public static void setLevel(int level) {
		
		if (level >= START) {
			GameLogicInformation.level = level;
		}else {
			GameLogicInformation.level = GameLogicInformation.START;
		}
	}
	
	public static int getLevel() {
		return GameLogicInformation.level;
	}
	
	public static String getBackgroundImage() {
		if (level == START) {
			return backGround_Start;
		}else if (level == INTERMISSION) {
			return backGround_Intermission;
		}else {
			return "";
		}
	}
	
	public static String getBackgroundMusic() {
		if (level == START) {
			return backGround_Start_MP3;
		}else if (level == INTERMISSION) {
			return backGround_Intermission_MP3;
		}else {
			mEnum = BackgroundMusicEnum.MUSIC_1;
			Random ran = new Random();
			int musicIndex = ran.nextInt(6)+1;
			return mEnum.getByIndex(musicIndex).getMusicStr();
		}
	}
	
	
	public static int getRandomForestTileMap(int index) {
		if (index == DESERT_LEVEL) {return FOREST_DESERT;}
		else if (index == JUNGLE_LEVEL) {return FOREST_JUNGLE;}
		else if (index == FABRIC_LEVEL) {return FOREST_FABRIC;}
		else if (index == WINTER_LEVEL) {return FOREST_WINTER;}
		else {return FOREST_DEFAULT;}
	}
	
	
	
	public static TileMapEnum[] getRandomTileMap(int index) {
		
		TileMapEnum[] levelMap= new TileMapEnum[6];
		
		if (index == DESERT_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING DESERT_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			
			levelMap[3] = TileMapEnum.CACTUS_01;
			levelMap[4] = TileMapEnum.CACTUS_02;
			levelMap[5] = TileMapEnum.CACTUS_03;
			
			
		}else if (index == JUNGLE_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING JUNGLE_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_D;
			levelMap[1] = TileMapEnum.BLOCK_A_02;
			levelMap[2] = TileMapEnum.BLOCK_D_02;
			
			levelMap[3] = TileMapEnum.PALM_01;
			levelMap[4] = TileMapEnum.PALM_02;
			levelMap[5] = TileMapEnum.PALM_03;
			
		}else if (index == FABRIC_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING INDUSTRIAL_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_01_A;
			levelMap[1] = TileMapEnum.BLOCK_C_02;
			levelMap[2] = TileMapEnum.BLOCK_A_02;
			
			levelMap[3] = TileMapEnum.SOLAR_02;
			levelMap[4] = TileMapEnum.CZECH_01;
			levelMap[5] = TileMapEnum.CZECH_02;
			
		
		}else if (index == WINTER_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING WINTER_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_E;
			levelMap[1] = TileMapEnum.BLOCK_E_02;
			levelMap[2] = TileMapEnum.BLOCK_E_01;
			
			levelMap[3] = TileMapEnum.ICEBERG_01;
			levelMap[4] = null;
			levelMap[5] = null;
			
			
		
		}else {
			
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			
			levelMap[3] = TileMapEnum.CACTUS_01;
			levelMap[4] = TileMapEnum.CACTUS_02;
			levelMap[5] = TileMapEnum.CACTUS_03;
			
		}
		
		return levelMap;
	}
	
	
}
