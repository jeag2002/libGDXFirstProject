package com.mygdx.game.enums;

import com.mygdx.game.SecondTestGDX;

public enum ElementEnum {
	
	HULL_PLAYER_1(SecondTestGDX.resources.bodyPlayer_01, 0, 256, 256, 64, 64),
	GUN_PLAYER_1_A(SecondTestGDX.resources.gunPlayer_01_A, 1, 94, 212, 32, 64),
	TRACK_01(SecondTestGDX.resources.track_01,2,42,246,16,64),
	TRACK_02(SecondTestGDX.resources.track_02,3,42,246,16,64);
	
	
	
	private String elementStr;
	private int index;
	private int width_bef;
	private int height_bef;
	private int width_show;
	private int height_show;
	
	
	private ElementEnum(String elementStr, int index, int width_bef, int height_bef, int width_show, int height_show) {
		this.elementStr = elementStr;
		this.index = index;
		
		this.width_bef = width_bef;
		this.height_bef = height_bef;
		this.width_show = width_show;
		this.height_show = height_show;
	}
	
	public String getElementStr() {
		return this.elementStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getWidthShow() {
		return width_show;
	}

	public void setWidthShow(int width) {
		this.width_show = width;
	}
	
	public int getWidthBef() {
		return width_bef;
	}

	public void setWidthBef(int width) {
		this.width_bef = width;
	}
	
	public int getHeightShow() {
		return height_show;
	}

	public void setHeightShow(int height) {
		this.height_show = height;
	}
	
	public int getHeightBef() {
		return height_bef;
	}

	public void setHeightBef(int height) {
		this.height_bef = height;
	}
	
	
	
	public ElementEnum getByIndex(int index) {
		
		ElementEnum EE[] = ElementEnum.values();
		ElementEnum elem =  ElementEnum.HULL_PLAYER_1;
		
		for(int i=0; i<EE.length; i++) {
			ElementEnum EElem = EE[i];
			if (EElem.getIndex() == index) {
				elem = EElem;
				break;
			}
		}
		
		return elem;
	}
	
	
	
	

}