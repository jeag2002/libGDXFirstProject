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
	
	HULL_ENEMY2(SecondTestGDX.resources.hullEnemy_2_A, 21, 256, 256, 64, 64),
	GUN_ENEMY2(SecondTestGDX.resources.gunEnemy_2_A, 22, 94, 212, 32, 64),
	TRACK_03(SecondTestGDX.resources.tire_03,23,42,246,16,64),
	TRACK_04(SecondTestGDX.resources.tire_04,24,42,246,16,64),
	
	ENEMY_3_01(SecondTestGDX.resources.enemy_3_01,25,256,256,64,64),
	ENEMY_3_02(SecondTestGDX.resources.enemy_3_02,26,256,256,64,64),
	ENEMY_3_03(SecondTestGDX.resources.enemy_3_03,27,256,256,64,64),
	ENEMY_3_04(SecondTestGDX.resources.enemy_3_04,28,256,256,64,64),
	ENEMY_3_05(SecondTestGDX.resources.enemy_3_05,29,256,256,64,64),
	ENEMY_3_06(SecondTestGDX.resources.enemy_3_06,30,256,256,64,64),
	ENEMY_3_07(SecondTestGDX.resources.enemy_3_07,31,256,256,64,64),
	ENEMY_3_08(SecondTestGDX.resources.enemy_3_08,32,256,256,64,64),
	ENEMY_3_09(SecondTestGDX.resources.enemy_3_09,33,256,256,64,64),
	ENEMY_3_0A(SecondTestGDX.resources.enemy_3_0A,34,256,256,64,64),
	
	PLATFORM_01(SecondTestGDX.resources.platform, 35, 256,256,64,64),
	PLATFORM_DOT_R(SecondTestGDX.resources.dot_a, 36, 256,256,64,64),
	PLATFORM_DOT_B(SecondTestGDX.resources.dot_b, 37, 256,256,64,64),
	PLATFORM_DOT_E(SecondTestGDX.resources.dot_c, 38, 256,256,64,64),
	
	LASER(SecondTestGDX.resources.laser, 39,128,128,32,64),
	PLASMA(SecondTestGDX.resources.plasma, 40,128,128,32,32),
	PULSE(SecondTestGDX.resources.pulse, 41,14,13,16,16),
	
	ENEMY_4_01(SecondTestGDX.resources.enemy_4_01,42,256,256,64,64),
	ENEMY_4_02(SecondTestGDX.resources.enemy_4_02,43,256,256,64,64),
	ENEMY_4_03(SecondTestGDX.resources.enemy_4_03,44,256,256,64,64),
	ENEMY_4_04(SecondTestGDX.resources.enemy_4_04,45,256,256,64,64),
	ENEMY_4_05(SecondTestGDX.resources.enemy_4_05,46,256,256,64,64),
	ENEMY_4_06(SecondTestGDX.resources.enemy_4_06,47,256,256,64,64),
	ENEMY_4_07(SecondTestGDX.resources.enemy_4_07,48,256,256,64,64),
	ENEMY_4_08(SecondTestGDX.resources.enemy_4_08,49,256,256,64,64),
	ENEMY_4_09(SecondTestGDX.resources.enemy_4_09,50,256,256,64,64),
	ENEMY_4_0A(SecondTestGDX.resources.enemy_4_0A,51,256,256,64,64),
	
	MISSILE(SecondTestGDX.resources.missile,52,128,128,64,64),
	
	HULL_ENEMY3(SecondTestGDX.resources.hullEnemy_2_B, 53, 256, 256, 64, 64),
	GUN_ENEMY3(SecondTestGDX.resources.gunEnemy_2_B, 54, 94, 212, 32, 64),
	TRACK_05(SecondTestGDX.resources.tire_05,55,42,246,16,64),
	TRACK_06(SecondTestGDX.resources.tire_06,56,42,246,16,64),
	
	HULL_ENEMY4(SecondTestGDX.resources.hullEnemy_2_C, 57, 256, 256, 64, 64),
	GUN_ENEMY4(SecondTestGDX.resources.gunEnemy_2_C, 58, 94, 212, 32, 64),
	
	MISSILE_1(SecondTestGDX.resources.missile_1,59,16,16,16,32),
	
	BONUS_LIFE(SecondTestGDX.resources.hp_bonus,60,128,128,32,32),
	BONUS_SHIELD(SecondTestGDX.resources.shield_bonus,61,128,128,32,32),
	BONUS_AMMO(SecondTestGDX.resources.armor_bonus,62,128,128,32,32),
	BONUS_GUN(SecondTestGDX.resources.ammunition_bonus, 63, 128,128,32,32),
	
	GUN_PLAYER_1_B(SecondTestGDX.resources.gunPlayer_01_B, 64, 94, 212, 32, 64),
	GUN_PLAYER_1_C(SecondTestGDX.resources.gunPlayer_01_C, 65, 94, 212, 32, 64),
	GUN_PLAYER_1_D(SecondTestGDX.resources.gunPlayer_01_D, 66, 94, 212, 32, 64),
	
	FLAME_1(SecondTestGDX.resources.flame_1, 67, 128, 128, 32, 32),
	FLAME_2(SecondTestGDX.resources.flame_2, 68, 128, 128, 64, 64),
	FLAME_3(SecondTestGDX.resources.flame_3, 69, 128, 128, 128, 128),
	FLAME_4(SecondTestGDX.resources.flame_4, 70, 128, 128, 256, 256),
	
	MISSILE_A(SecondTestGDX.resources.rocket_01, 71, 20,16,64,32),
	MISSILE_B(SecondTestGDX.resources.rocket_02, 72, 20,16,64,32),
	MISSILE_C(SecondTestGDX.resources.rocket_03, 73, 20,16,64,32),
	
	BONUS_NUKE(SecondTestGDX.resources.bonus_Nuke, 74, 128,128,32,32),
	BONUS_SCORE(SecondTestGDX.resources.bonus_Coin, 75, 128,128,32,32),
	
	GUN_PLAYER_1_E(SecondTestGDX.resources.gunPlayer_01_E, 76, 94, 212, 32, 64),
	GUN_PLAYER_1_F(SecondTestGDX.resources.gunPlayer_01_F, 77, 94, 212, 32, 64),
	GRENADE(SecondTestGDX.resources.grenade,78,512,512,32,32),
	
	GUN_ENEMY5(SecondTestGDX.resources.gunEnemy_2_A_1,79,94, 212, 32, 64),
	GUN_ENEMY6(SecondTestGDX.resources.gunEnemy_2_B_1,80,94, 212, 32, 64),
	GUN_ENEMY7(SecondTestGDX.resources.gunEnemy_2_C_1,81,94, 212, 32, 64);
	
	
	
	
	
	
	
	
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
