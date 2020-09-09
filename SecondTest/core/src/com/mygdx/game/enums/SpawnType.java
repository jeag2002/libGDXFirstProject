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
    Forest_Desert("Forest_Desert",22),
    Forest_Jungle("Forest_Jungle",23),
    Forest_Fabric("Forest_Fabric",24),
    Forest_Winter("Forest_Winter",25),
    Forest_Badlands("Forest_Badlands",26),
    Forest_Volcano("Forest_Volcano",27),
    Forest_City("Forest_City",28),
    Forest_Space("Forest_Space",29),
    Path_Node("Node",30),
    Item_PlatformPlayer("PlatformPlayer",31),
    Item_PlatformEnemy("PlatformEnemy",32),
    Item_PlatformEndLevel("PlatformEndLevel",33),
    Item_Mine("PlatformMine",34),
    Item_Bonus("Bonus",35),
    Missile_Laser("Laser", 36),
    Missile_Plasma("Plasma",37),
    Missile_Pulse("Pulse",38),
    Simple_Explosion("Simple_Explosion",39),
    Item_Bonus_Life("Bonus_Life",40),
    Item_Bonus_Shield("Bonus_Shield",41),
    Item_Bonus_Bullet("Bonus_Bullet",42),
    Missile_Missile("Missile",43),
    Tank_Level_1("Tank_Level_1",44),
    Tank_Level_2("Tank_Level_2",45),
    Tank_Level_3("Tank_level_3",46),
    Item_Bonus_Gun("Bonus_Gun",47),
    Missile_Flame("Flame",48),
    Fire("Fire",49),
    Item_Bonus_Nuke("Bonus_Nuke",50),
    Item_Bonus_Score("Bonus_Score",51),
    Big_Explosion("Big_Explosion",52),
    Missile_Grenade("Grenade",39);
    
    
    
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
