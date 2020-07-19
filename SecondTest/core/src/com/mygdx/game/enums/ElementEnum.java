package com.mygdx.game.enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SecondTestGDX;

public enum ElementEnum {	
	
	HULL_PLAYER_1(SecondTestGDX.resources.bodyPlayer_01, 0, 256, 256, 64, 64),
	GUN_PLAYER_1_A(SecondTestGDX.resources.gunPlayer_01_A, 1, 94, 212, 32, 64),
	TRACK_01(SecondTestGDX.resources.track_01,2,42,246,16,64),
	TRACK_02(SecondTestGDX.resources.track_02,3,42,246,16,64),
	
	EXHAUST_01(SecondTestGDX.resources.exhaustFire_01,4,22,29,16,32),
	EXHAUST_02(SecondTestGDX.resources.exhaustFire_02,5,22,29,16,32),
	EXHAUST_03(SecondTestGDX.resources.exhaustFire_03,6,22,29,16,32),
	EXHAUST_04(SecondTestGDX.resources.exhaustFire_04,7,22,29,16,32),
	EXHAUST_05(SecondTestGDX.resources.exhaustFire_05,8,22,29,16,32),
	EXHAUST_06(SecondTestGDX.resources.exhaustFire_06,9,22,29,16,32),
	EXHAUST_07(SecondTestGDX.resources.exhaustFire_07,10,22,29,16,32),
	
	ENEMY_1_01(SecondTestGDX.resources.enemy_1_01,11,256,256,64,64),
	ENEMY_1_02(SecondTestGDX.resources.enemy_1_02,12,256,256,64,64),
	ENEMY_1_03(SecondTestGDX.resources.enemy_1_03,13,256,256,64,64),
	ENEMY_1_04(SecondTestGDX.resources.enemy_1_04,14,256,256,64,64),
	ENEMY_1_05(SecondTestGDX.resources.enemy_1_05,15,256,256,64,64),
	ENEMY_1_06(SecondTestGDX.resources.enemy_1_06,16,256,256,64,64),
	ENEMY_1_07(SecondTestGDX.resources.enemy_1_07,17,256,256,64,64),
	ENEMY_1_08(SecondTestGDX.resources.enemy_1_08,18,256,256,64,64),
	ENEMY_1_09(SecondTestGDX.resources.enemy_1_09,19,256,256,64,64),
	ENEMY_1_0A(SecondTestGDX.resources.enemy_1_0A,20,256,256,64,64),
	
	ENEMY_1_LEFT_WING(SecondTestGDX.resources.enemy_1_part_01,21,256,256,16,16),
	ENEMY_1_RIGHT_WING(SecondTestGDX.resources.enemy_1_part_02,22,256,256,16,16),
	ENEMY_1_BODY(SecondTestGDX.resources.enemy_1_part_03,23,256,256,16,16),
	
	HULL_ENEMY2(SecondTestGDX.resources.hullEnemy_2_A, 24, 256, 256, 64, 64),
	GUN_ENEMY2(SecondTestGDX.resources.gunEnemy_2_A, 25, 94, 212, 32, 64),
	TRACK_03(SecondTestGDX.resources.tire_03,26,42,246,16,64),
	TRACK_04(SecondTestGDX.resources.tire_04,27,42,246,16,64);
	
	
	
	
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
