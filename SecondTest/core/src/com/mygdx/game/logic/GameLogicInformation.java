package com.mygdx.game.logic;

import java.util.Random;

import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.BackgroundMusicEnum;

public class GameLogicInformation {

	public static final int START = 0;
	public static final int INTERMISSION = 1;
	public static final int GAMEPLAY = 2;
	
	
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
	
}
