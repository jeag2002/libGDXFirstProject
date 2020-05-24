package com.mygdx.game.logic;

import com.mygdx.game.SecondTestGDX;

public class GameLogicInformation {

	public static final int START = 0;
	public static final int INTERMISSION = 1;
	public static final int LEVEL_1 = 2;
	public static final int LEVEL_2 = 3;
	public static final int LEVEL_3 = 4;
	public static final int LEVEL_4 = 5;
	public static final int LEVEL_5 = 6;
	
	public static final int END = 7;
	
	private static int level;
	
	public static final String backGround_Start = SecondTestGDX.resources.imgSplash; 
	public static final String backGround_Start_MP3 = SecondTestGDX.resources.musicSplash;
	
	
	public static void setLevel(int level) {
		
		if ((level >= START) && (level < END)) {
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
		}else {
			return "";
		}
	}
	
	public static String getBackgroundMusic() {
		if (level == START) {
			return backGround_Start_MP3;
		}else {
			return "";
		}
	}
	
}
