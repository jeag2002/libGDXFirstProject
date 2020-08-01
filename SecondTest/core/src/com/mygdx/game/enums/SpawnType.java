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
    Wall_City("Wall_City",19),
    Forest_Desert("Forest_Desert",20),
    Forest_Jungle("Forest_Jungle",21),
    Forest_Fabric("Forest_Fabric",22),
    Forest_Winter("Forest_Winter",23),
    Forest_Badlands("Forest_Badlands",24),
    Forest_Volcano("Forest_Volcano",25),
    Forest_City("Forest_City",26),
    Path_Node("Node",27),
    Item_PlatformPlayer("PlatformPlayer",28),
    Item_PlatformEnemy("PlatformEnemy",29),
    Item_PlatformEndLevel("PlatformEndLevel",30),
    Item_Mine("PlatformMine",31),
    Item_Bonus("Bonus",32),
    Missile_Laser("Laser", 33),
    Missile_Plasma("Plasma",34),
    Simple_Explosion("Simple_Explosion",35),
    Item_Bonus_Life("Bonus_Life",36),
    Item_Bonus_Shield("Bonus_Shield",37),
    Item_Bonus_Bullet("Bonus_Bullet",38);
    
    
    
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
