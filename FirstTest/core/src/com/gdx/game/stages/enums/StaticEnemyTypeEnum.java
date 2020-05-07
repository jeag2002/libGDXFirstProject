package com.gdx.game.stages.enums;

public enum StaticEnemyTypeEnum {
	
	TURRET_LEVEL_1("TURRET_LEVEL_1",0),
	TURRET_LEVEL_2("TURRET_LEVEL_2",1),
	TURRET_BOSS("TURRET_BOSS",2),
	MINE("MINE",3),
	CANNON("CANNON",4);
	
	private String enemyStr;
	private int index;
	
	private StaticEnemyTypeEnum(String enemyStr, int index) {
		this.enemyStr = enemyStr;
		this.index = index;
	}
	
	public String getEnemy() {
		return this.enemyStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public StaticEnemyTypeEnum getByIndex (int index) {
		
		StaticEnemyTypeEnum SETE[] = StaticEnemyTypeEnum.values();
		StaticEnemyTypeEnum enemy = StaticEnemyTypeEnum.TURRET_LEVEL_1;
		
		for(int i=0; i<SETE.length; i++) {
			StaticEnemyTypeEnum SETElem = SETE[i];
			if (SETElem.getIndex() == index) {
				enemy = SETElem;
			}
		}
		return enemy;
	}

}
