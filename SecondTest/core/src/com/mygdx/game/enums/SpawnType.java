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
    MissilePlayer ("MissilePlayer",11),
    Border("Border",12),
    
    Wall_Desert("Wall_Desert",13),
    Wall_Jungle("Wall_Jungle",14),
    Wall_Fabric("Wall_Fabric",15),
    Wall_Winter("Wall_Winter",16),
    Wall_Badlands("Wall_Badlands",17),
    Wall_Volcano("Wall_Volcano",18),
    
    Forest_Desert("Forest_Desert",19),
    Forest_Jungle("Forest_Jungle",20),
    Forest_Fabric("Forest_Fabric",21),
    Forest_Winter("Forest_Winter",22),
    Forest_Badlands("Forest_Badlands",23),
    Forest_Volcano("Forest_Volcano",24);
    
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
