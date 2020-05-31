package com.mygdx.game.enums;

import com.mygdx.game.SecondTestGDX;

public enum BackgroundMusicEnum {
	
	MUSIC_1(SecondTestGDX.resources.musicGameplay_1,1),
	MUSIC_2(SecondTestGDX.resources.musicGameplay_2,2),
	MUSIC_3(SecondTestGDX.resources.musicGameplay_3,3),
	MUSIC_4(SecondTestGDX.resources.musicGameplay_4,4),
	MUSIC_5(SecondTestGDX.resources.musicGameplay_5,5),
	MUSIC_6(SecondTestGDX.resources.musicGameplay_6,6);
	
	
	
	private String musicStr;
	private int index;
	
	private BackgroundMusicEnum(String musicStr, int index) {
		this.musicStr = musicStr;
		this.index = index;
	}
	
	public String getMusicStr() {
		return this.musicStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public BackgroundMusicEnum getByIndex(int index) {
		
		BackgroundMusicEnum BME[] = BackgroundMusicEnum.values();
		BackgroundMusicEnum music = BackgroundMusicEnum.MUSIC_1;
		
		for(int i=0; i<BME.length; i++) {
			BackgroundMusicEnum BMElem = BME[i];
			if (BMElem.getIndex() == index) {
				music = BMElem;
				break;
			}
		}
		
		return music;
	}
	
	
	
	

}
