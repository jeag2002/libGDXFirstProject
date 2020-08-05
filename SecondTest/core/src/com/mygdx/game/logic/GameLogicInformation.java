package com.mygdx.game.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.BackgroundMusicEnum;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.utils.NewItem;

public class GameLogicInformation {

	
	public static final int NUM_LEVELS = 10;
	public static final int FIRST_LEVEL = 0;
	public static final int END_GAME = 10;
	
	public static final int START = 0;
	public static final int INTERMISSION = 1;
	public static final int GAMEPLAY = 2;
	
	public static final float PIXELS_TO_METERS = 100f;
	
	public static final int PLAYERS = 1;
	
	public static final int ENEMIESDRON = 100;
	public static final int ENEMIESTANK = 4;
	public static final int ENEMIESMINE = 100;
	
	public static final double MIN_DISTANCE_BETWEEN_ENEMIES = 200.0f;
	
	public static final double SEEK_DISTANCE = 750;
	public static final double ATTACK_DISTANCE = 128;
	
	
	public static final int DESERT_LEVEL = 0;
	public static final int JUNGLE_LEVEL = 1;
	public static final int FABRIC_LEVEL = 2;
	public static final int WINTER_LEVEL = 3;
	public static final int BADLAND_LEVEL = 4;
	public static final int VOLCANO_LEVEL = 5;
	public static final int CITY_LEVEL = 6;
	public static final int SPACE_LEVEL = 7;
	
	public static final int FOREST_DESERT = 3;
	public static final int FOREST_JUNGLE = 3;
	public static final int FOREST_FABRIC = 1;
	public static final int FOREST_WINTER = 1;
	public static final int FOREST_BADLAND = 3;
	public static final int FOREST_VOLCANO = 1;
	public static final int FOREST_CITY = 1;
	public static final int FOREST_SPACE = 2;
	
	public static final int FOREST_DEFAULT = 1;
	
	public static final int NO_LIGHTS = 0;
	public static final int LIGHTS = 1;
	
	
	public static final float speedUpFactor = 1.0f;
	public static final float bgSpeed = 50.0f;
	public static final float bgSpeedPos = 20.0f;
	
	private static int level;
	private static int levelGameplay;
	
	
	public static final String backGround_Start = SecondTestGDX.resources.imgSplash;
    //public static final String backGround_Start = SecondTestGDX.resources.imgSplash_1;
	public static final String backGround_Intermission = SecondTestGDX.resources.imgIntermission;
	
	public static final String backGround_Start_MP3 = SecondTestGDX.resources.musicSplash;
	public static final String backGround_Intermission_MP3 = SecondTestGDX.resources.musicIntermission;
	
	private static BackgroundMusicEnum mEnum;
	
	public static void setLevelGamePlay(int level) {
		if ((level >= FIRST_LEVEL) && (level < END_GAME)) {
			GameLogicInformation.levelGameplay = level;
		}else {
			GameLogicInformation.levelGameplay = GameLogicInformation.FIRST_LEVEL;
		}
	}
	
	public static int getLevelGamePlay() {
		return GameLogicInformation.levelGameplay;
	}
	
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
		else if (index == BADLAND_LEVEL) {return FOREST_BADLAND;}
		else if (index == VOLCANO_LEVEL) {return FOREST_VOLCANO;}
		else if (index == CITY_LEVEL) {return FOREST_CITY;}
		else if (index == SPACE_LEVEL) {return FOREST_SPACE;}
		else {return FOREST_DEFAULT;}
	}
	
	
	
	public static TileMapEnum[] getRandomTileMap(int index) {
		
		TileMapEnum[] levelMap= new TileMapEnum[8];
		
		if (index == DESERT_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING DESERT_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			
			levelMap[3] = TileMapEnum.CACTUS_01;
			levelMap[4] = TileMapEnum.CACTUS_02;
			levelMap[5] = TileMapEnum.CACTUS_03;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
			
		}else if (index == JUNGLE_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING JUNGLE_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_D;
			levelMap[1] = TileMapEnum.BLOCK_A_02;
			levelMap[2] = TileMapEnum.BLOCK_D_02;
			
			levelMap[3] = TileMapEnum.PALM_01;
			levelMap[4] = TileMapEnum.PALM_02;
			levelMap[5] = TileMapEnum.PALM_03;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
		}else if (index == FABRIC_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING INDUSTRIAL_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_01_A;
			levelMap[1] = TileMapEnum.BLOCK_C_02;
			levelMap[2] = TileMapEnum.BLOCK_A_02;
			
			levelMap[3] = TileMapEnum.SOLAR_02;
			levelMap[4] = TileMapEnum.CZECH_01;
			levelMap[5] = TileMapEnum.CZECH_02;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
		
		}else if (index == WINTER_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING WINTER_LEVEL");
			levelMap[0] = TileMapEnum.GROUND_TILE_02_E;
			levelMap[1] = TileMapEnum.BLOCK_E_02;
			levelMap[2] = TileMapEnum.BLOCK_E_01;
			
			levelMap[3] = TileMapEnum.ICEBERG_01;
			
			levelMap[4] = null;
			levelMap[5] = null;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
		}else if (index == BADLAND_LEVEL) {	
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING BADLAND_LEVEL");
			
			levelMap[0] = TileMapEnum.GROUND_TILE_02_F;
			levelMap[1] = TileMapEnum.BUILDING_01;
			levelMap[2] = TileMapEnum.BLOCK_F_02;
			
			levelMap[3] = TileMapEnum.TREE_07;
			levelMap[4] = TileMapEnum.TREE_08;
			levelMap[5] = TileMapEnum.TREE_09;
			
			levelMap[6] = null;
			levelMap[7] = null;
		
		}else if (index == VOLCANO_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING VOLCANO_LEVEL");
			
			levelMap[0] = TileMapEnum.GROUND_TILE_01_B;
			levelMap[1] = TileMapEnum.BLOCK_F_02;
			levelMap[2] = TileMapEnum.VOLCANO_FOREST_1;
			levelMap[3] = TileMapEnum.VOLCANO_WALL;
			
			levelMap[4] = null;
			levelMap[5] = null;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
		}else if (index == CITY_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING CITY_LEVEL");
			levelMap[0] = TileMapEnum.BACKGROUND_CITY;
			levelMap[1] = TileMapEnum.BLOCK_A_02;
			levelMap[2] = TileMapEnum.WALL_CITY;
			levelMap[3] = TileMapEnum.PARK_CITY;
			levelMap[4] = null;
			levelMap[5] = null;
		
		}else if (index == SPACE_LEVEL) {
			
			Gdx.app.log("[GAMELOGICINFORMATION]", "GENERATING SPACE_LEVEL");
		
			levelMap[0] = TileMapEnum.GROUND_TILE_02_A;
			
			levelMap[1] = TileMapEnum.SPACE_WALL;
			levelMap[2] = TileMapEnum.SPACE_WALL;
			
			levelMap[3] = TileMapEnum.SPACE_FOREST_1;
			
			levelMap[4] = TileMapEnum.SPACE_FAN_1;
			levelMap[5] = TileMapEnum.SPACE_FAN_2;
			levelMap[6] = TileMapEnum.SPACE_FAN_3;
			levelMap[7] = TileMapEnum.SPACE_FAN_4;
			
			
		}else {
			
			levelMap[0] = TileMapEnum.GROUND_TILE_02_C;
			levelMap[1] = TileMapEnum.BLOCK_B_02;
			levelMap[2] = TileMapEnum.BLOCK_C_02;
			
			levelMap[3] = TileMapEnum.CACTUS_01;
			levelMap[4] = TileMapEnum.CACTUS_02;
			levelMap[5] = TileMapEnum.CACTUS_03;
			
			levelMap[6] = null;
			levelMap[7] = null;
			
		}
		
		return levelMap;
	}
	
	
}
