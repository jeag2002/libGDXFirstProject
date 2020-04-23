package com.gdx.game.stages.enums;

public enum MissileTypeEnum {
	LASER_1 ("LASER_1", 99),
	LASER_1_ANIM ("LASER_1_ANIM",100);
	
	private String missileStr;
	private int index;
	
	private MissileTypeEnum(String missileStr, int index) {
		this.missileStr = missileStr;
		this.index = index;
	}
	
	public String getMissileStr() {
		return this.missileStr;
	}
	
	public int getIndex() {
		return this.index;
	}

}
