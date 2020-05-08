package com.gdx.game.engine.logic;

import com.gdx.game.FirstTestGDX;

public class GameLevelInformation {
	
	public static final int FIRST_LEVEL = 1;
	public static final int SECOND_LEVEL = 2;
	public static final int THIRD_LEVEL = 3;
	public static final int FINAL_LEVEL = 3;
	public static final int END = 4;
	
	public static final String TURRET = "turrets";
	public static final String TURRET_BOSS = "turrets_boss";
	public static final String CANNON = "cannons";
	public static final String MINE = "mines";
	
	
	private static int level;
	
	private static final String map_level_1 = "maps/map_1/tile_test_3_final.tmx";
	private static final String map_level_2 = "";
	private static final String map_level_3 = "";
	
	private static final String music_level_1 = "sounds/levels/gamelevelmusicfinal.mp3";
	private static final String music_level_2 = "";
	private static final String music_level_3 = "";
	
	
	private static final String[] background_level_1 = {
			FirstTestGDX.resources.imgBackgroundParallaxBG_1,
			FirstTestGDX.resources.imgBackgroundParallaxStars_1,
			FirstTestGDX.resources.imgBackgroundParallaxPlanets_1,
			FirstTestGDX.resources.imgBackgroundParallaxMeteors_1
	};
	private static final String[] background_level_2 = {
			FirstTestGDX.resources.imgBackgroundParallaxBG_2,
			FirstTestGDX.resources.imgBackgroundParallaxStars_2,
			FirstTestGDX.resources.imgBackgroundParallaxPlanets_2,
			FirstTestGDX.resources.imgBackgroundParallaxMeteors_2
	};
	private static final String[] background_level_3 = {
			FirstTestGDX.resources.imgBackgroundParallaxBG_3,
			FirstTestGDX.resources.imgBackgroundParallaxStars_3,
			FirstTestGDX.resources.imgBackgroundParallaxPlanets_3,
			FirstTestGDX.resources.imgBackgroundParallaxMeteors_3
	};
	
	public static void setLevel(int level) {
		
		if ((level >= FIRST_LEVEL) && (level < END)) {
			GameLevelInformation.level = level;
		}else {
			GameLevelInformation.level = FIRST_LEVEL;
		}
	}
	
	public static int getLevel() {
		return GameLevelInformation.level;
	}
	
	
	public static String[] getBackgroundLevel(int level) {
		if (level == GameLevelInformation.FIRST_LEVEL) {
			return background_level_1;
		}else if (level == GameLevelInformation.SECOND_LEVEL) {
			return background_level_2;
		}else if (level == GameLevelInformation.THIRD_LEVEL) {
			return background_level_3;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return background_level_3;
		}else {
			return background_level_1;
		}
	}
	
	public static String getMapLevel(int level) {
		if (level == GameLevelInformation.FIRST_LEVEL) {
			return map_level_1;
		}else if (level == GameLevelInformation.SECOND_LEVEL) {
			return map_level_2;
		}else if (level == GameLevelInformation.THIRD_LEVEL) {
			return map_level_3;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return map_level_3;
		}else {
			return map_level_3;
		}
	}
	
	
	public static String getMusicLevel(int level) {
		if (level == GameLevelInformation.FIRST_LEVEL) {
			return music_level_1;
		}else if (level == GameLevelInformation.SECOND_LEVEL) {
			return music_level_2;
		}else if (level == GameLevelInformation.THIRD_LEVEL) {
			return music_level_3;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return music_level_3;
		}else {
			return music_level_1;
		}
	}
	
	
	
	
	
	

}