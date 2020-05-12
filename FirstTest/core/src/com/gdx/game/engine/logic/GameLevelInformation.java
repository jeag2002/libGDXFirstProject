package com.gdx.game.engine.logic;

import com.gdx.game.FirstTestGDX;

public class GameLevelInformation {
	
	public static final int FIRST_LEVEL = 1;
	public static final int SECOND_LEVEL = 2;
	public static final int THIRD_LEVEL = 3;
	public static final int FOURTH_LEVEL = 4;
	public static final int FIFTH_LEVEL = 5;
	public static final int FINAL_LEVEL = 5;
	public static final int END = 6;
	
	public static final String TURRET = "turrets";
	public static final String TURRET_BOSS = "turrets_boss";
	public static final String CANNON = "cannons";
	public static final String MINE = "mines";
	
	
	private static int level;
	
	private static final String map_level_1 = "maps/map_1/tile_test_3_final.tmx";
	private static final String map_level_2 = "maps/map_2/tile_test_4_final.tmx";
	private static final String map_level_3 = "maps/map_3/tile_test_5_final.tmx";
	private static final String map_level_4 = "maps/map_4/tile_test_6_final.tmx";
	private static final String map_level_5 = "maps/map_5/tile_test_7_final.tmx";
	
	private static final String music_level_1 = "sounds/levels/gamelevelmusicfinal.mp3";
	private static final String music_level_2 = "sounds/levels/gamelevel2musicfinal.mp3";
	private static final String music_level_3 = "sounds/levels/gamelevel3musicfinal.mp3";
	private static final String music_level_4 = "sounds/levels/gamelevel4musicfinal.mp3";
	private static final String music_level_5 = "sounds/levels/gamelevel5musicfinal.mp3";
	
	
	
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
	
	private static final String[] background_level_4 = {
			FirstTestGDX.resources.imgBackgroundParallaxBG_4,
			FirstTestGDX.resources.imgBackgroundParallaxStars_4,
			FirstTestGDX.resources.imgBackgroundParallaxPlanets_4,
			FirstTestGDX.resources.imgBackgroundParallaxMeteors_4
	};
	
	private static final String[] background_level_5 = {
			FirstTestGDX.resources.imgBackgroundParallaxBG_5,
			FirstTestGDX.resources.imgBackgroundParallaxStars_5,
			FirstTestGDX.resources.imgBackgroundParallaxPlanets_5,
			FirstTestGDX.resources.imgBackgroundParallaxMeteors_5
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
		}else if (level == GameLevelInformation.FOURTH_LEVEL) {
			return background_level_4;
		}else if (level == GameLevelInformation.FIFTH_LEVEL) {
			return background_level_5;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return background_level_5;
		}else {
			return background_level_5;
		}
	}
	
	public static String getMapLevel(int level) {
		if (level == GameLevelInformation.FIRST_LEVEL) {
			return map_level_1;
		}else if (level == GameLevelInformation.SECOND_LEVEL) {
			return map_level_2;
		}else if (level == GameLevelInformation.THIRD_LEVEL) {
			return map_level_3;
		}else if (level == GameLevelInformation.FOURTH_LEVEL) {
			return map_level_4;
		}else if (level == GameLevelInformation.FIFTH_LEVEL) {
			return map_level_5;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return map_level_5;
		}else {
			return map_level_5;
		}
	}
	
	
	public static String getMusicLevel(int level) {
		if (level == GameLevelInformation.FIRST_LEVEL) {
			return music_level_1;
		}else if (level == GameLevelInformation.SECOND_LEVEL) {
			return music_level_2;
		}else if (level == GameLevelInformation.THIRD_LEVEL) {
			return music_level_3;
		}else if (level == GameLevelInformation.FOURTH_LEVEL) {
			return music_level_4;
		}else if (level == GameLevelInformation.FOURTH_LEVEL) {
			return music_level_4;
		}else if (level == GameLevelInformation.FIFTH_LEVEL) {
			return music_level_5;
		}else if (level == GameLevelInformation.FINAL_LEVEL) {
			return music_level_5;
		}else {
			return music_level_5;
		}
	}
	
}
