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
	
	
	public static TileMapEnum[] getRandomTileMap(int index) {
		
		TileMapEnum[] levelMap= new TileMapEnum[4];
		
		if (index == DESERT_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING DESERT_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			levelMap[3] = TileMapEnum.CACTUS_03;
			
		}else if (index == JUNGLE_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING JUNGLE_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_D;
			levelMap[1] = TileMapEnum.BLOCK_A_02;
			levelMap[2] = TileMapEnum.BLOCK_D_02;
			levelMap[3] = TileMapEnum.PALM_01;
			
		}else if (index == FABRIC_LEVEL) {
			
			Gdx.app.log("[GameLogicInformation]", "GENERATING INDUSTRIAL_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_01_A;
			levelMap[1] = TileMapEnum.BLOCK_C_02;
			levelMap[2] = TileMapEnum.BLOCK_A_02;
			levelMap[3] = TileMapEnum.CZECH_01;
			
		
		}else {
			
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			levelMap[3] = TileMapEnum.CACTUS_03;
			
		}
		
		return levelMap;
	}
	
	
}
