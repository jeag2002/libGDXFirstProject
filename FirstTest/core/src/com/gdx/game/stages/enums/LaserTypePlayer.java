package com.gdx.game.stages.enums;

public enum LaserTypePlayer {
	LASER_LEVEL_1 ("LASER_1", 99),
	LASER_LEVEL_2 ("LASER_2", 100),
	LASER_LEVEL_3 ("LASER_3", 101),
	LASER_LEVEL_1_ANIM ("LASER_1_ANIM", 999);
	
	private String missileStr;
	private int index;
	
	private LaserTypePlayer(String missileStr, int index) {
		this.missileStr = missileStr;
		this.index = index;
	}
	
	public String getMissileStr() {
		return this.missileStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public String toString() {
		return this.missileStr + " " + this.index;
	}
	
	public LaserTypePlayer getByIndex (int index) {
		
		LaserTypePlayer lTP[] = LaserTypePlayer.values();
		
		LaserTypePlayer player = LaserTypePlayer.LASER_LEVEL_1;
		
		for(int i=0; i<lTP.length; i++) {
			LaserTypePlayer lTPs = lTP[i];
			if (lTPs.getIndex() == index) {
				player = lTPs;
			}
		}
		
		return player;
		
	}
	
}
