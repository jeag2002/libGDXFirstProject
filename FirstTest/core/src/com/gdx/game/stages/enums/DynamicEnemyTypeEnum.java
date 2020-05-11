package com.gdx.game.stages.enums;

public enum DynamicEnemyTypeEnum {
	
	//ENEMIES FIRST LEVEL
	ENEMY_SIMPLE_1 ("ENEMY_SIMPLE_1",0),
	ENEMY_SIMPLE_2 ("ENEMY_SIMPLE_2",1),
	METEORTYPEONE ("METEORTYPEONE",2),
	METEORTYPETWO ("METEORTYPETWO",3),
	
	//ENEMIES SECOND LEVEL
	ENEMY_SIMPLE_1_LEVEL_2 ("ENEMY_SIMPLE_1_LEVEL_2",4),
	ENEMY_SIMPLE_2_LEVEL_2 ("ENEMY_SIMPLE_2_LEVEL_2",5),
	METEORTYPEONE_LEVEL_2 ("METEORTYPEONE_LEVEL_2",6),
	METEORTYPETWO_LEVEL_2 ("METEORTYPETWO_LEVEL_2",7),
	
	//ENEMIES THIRD LEVEL
	GROUP_ENEMIES_1_LEVEL_3("GROUP_ENEMIES_1_LEVEL_3",8), //--> TWO ENEMY SIMPLE LEVEL HOR
	GROUP_ENEMIES_2_LEVEL_3("GROUP_ENEMIES_2_LEVEL_3",9), //--> TWO ENEMY SIMPLE LEVEL VERT
	CANNON_LEVEL_3("CANNON_LEVEL_3",10),
	ENEMY_SIMPLE_1_LEVEL_3("ENEMY_SIMPLE_1_LEVEL_3",11),
	METEORTYPEONE_LEVEL_3 ("METEORTYPEONE_LEVEL_3",12),
	METEORTYPETWO_LEVEL_3 ("METEORTYPETWO_LEVEL_3",13);
	
	
	
	private String enemyStr;
	private int index;
	
	private DynamicEnemyTypeEnum(String enemyStr, int index) {
		this.enemyStr = enemyStr;
		this.index = index;
	}
	
	public String getEnemy() {
		return this.enemyStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public DynamicEnemyTypeEnum getByIndex (int index) {
		
		DynamicEnemyTypeEnum DETE[] = DynamicEnemyTypeEnum.values();
		
		DynamicEnemyTypeEnum enemy = DynamicEnemyTypeEnum.ENEMY_SIMPLE_1;
		
		for(int i=0; i<DETE.length; i++) {
			DynamicEnemyTypeEnum DETElem = DETE[i];
			if (DETElem.getIndex() == index) {
				enemy = DETElem;
			}
		}
		
		return enemy;
		
	}
	
	
}
