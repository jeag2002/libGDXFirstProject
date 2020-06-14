package com.mygdx.game.enums;

public enum SpawnType {
    Player_01 ("Player_01",0),
    Player_02 ("Player_02",1),
    Player_03 ("Player_03",2),
    Player_04 ("Player_04",3),
    Enemy_01 ("Enemy_01",4),
    Enemy_02 ("Enemy_02",5),
    Enemy_03 ("Enemy_03",6),
    Enemy_04 ("Enemy_04",7),
    Explosion ("Explosion",8),
    Item ("Item",9),
    MissileEnemy ("MissileEnemy",10),
    MissilePlayer ("MissilePlayer",11);
    
    private String elementStr;
	private int index;
	
	private SpawnType(String elementStr, int index) {
		this.elementStr = elementStr;
		this.index = index;
	}
	
	public String getElementStr() {
		return this.elementStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public static SpawnType getByIndex(int index) {
		
		SpawnType ST[] = SpawnType.values();
		SpawnType STelem =  SpawnType.Player_01;
		
		for(int i=0; i<ST.length; i++) {
			SpawnType elem = ST[i];
			if (elem.getIndex() == index) {
				STelem = elem;
				break;
			}
		}
		
		return STelem;
	}
	
	
    
}
