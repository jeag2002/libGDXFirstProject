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
    Wall_Space("Wall_Space",20),
    Wall_Island("Wall_Island",21),
    Wall_Wasteland("Wall_Wasteland",22),
    
    Forest_Desert("Forest_Desert",23),
    Forest_Jungle("Forest_Jungle",24),
    Forest_Fabric("Forest_Fabric",25),
    Forest_Winter("Forest_Winter",26),
    Forest_Badlands("Forest_Badlands",27),
    Forest_Volcano("Forest_Volcano",28),
    Forest_City("Forest_City",29),
    Forest_Space("Forest_Space",30),
    Forest_Island("Forest_Island",31),
    Forest_Wasteland("Forest_Wasteland",32),
    
    Path_Node("Node",33),
    Item_PlatformPlayer("PlatformPlayer",34),
    Item_PlatformEnemy("PlatformEnemy",35),
    Item_PlatformEndLevel("PlatformEndLevel",36),
    Item_Mine("PlatformMine",37),
    Item_Bonus("Bonus",38),
    Missile_Laser("Laser", 39),
    Missile_Plasma("Plasma",40),
    Missile_Pulse("Pulse",41),
    Simple_Explosion("Simple_Explosion",42),
    Item_Bonus_Life("Bonus_Life",43),
    Item_Bonus_Shield("Bonus_Shield",44),
    Item_Bonus_Bullet("Bonus_Bullet",45),
    Missile_Missile("Missile",46),
    Tank_Level_1("Tank_Level_1",47),
    Tank_Level_2("Tank_Level_2",48),
    Tank_Level_3("Tank_level_3",49),
    Item_Bonus_Gun("Bonus_Gun",50),
    Missile_Flame("Flame",51),
    Fire("Fire",52),
    Item_Bonus_Nuke("Bonus_Nuke",53),
    Item_Bonus_Score("Bonus_Score",54),
    Big_Explosion("Big_Explosion",55),
    Missile_Grenade("Grenade",56);
    
    
    
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
