package com.gdx.game.stages.enums;

public enum ShootEnemyType {
	SHOOT_SIMPLE ("SHOOT_SIMPLE",0),
	SHOOT_DOUBLE ("SHOOT_DOUBLE",1);
	
	private String shootStr;
	private int index;
	
	private ShootEnemyType(String shootStr, int index) {
		this.shootStr = shootStr;
		this.index = index;
	}
	
	public String getShootStr() {
		return this.shootStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public ShootEnemyType getByIndex (int index) {
		
		ShootEnemyType SET[] = ShootEnemyType.values();
		
		ShootEnemyType shoot = ShootEnemyType.SHOOT_SIMPLE;
		
		for(int i=0; i<SET.length; i++) {
			shoot = SET[i];
			if (shoot.getIndex() == index) {
				break;
			}
		}
		
		return shoot;
		
	}

	
}
