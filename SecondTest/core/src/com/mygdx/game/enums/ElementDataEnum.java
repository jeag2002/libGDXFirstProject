package com.mygdx.game.enums;

import com.mygdx.game.logic.GameLogicInformation;

public enum ElementDataEnum {
	
	PLAYER(0,"PLAYER",SpawnType.Player_01,GameLogicInformation.MAX_LIFE_PLAYER,GameLogicInformation.MAX_SHIELD_PLAYER, GameLogicInformation.MAX_AMMO_PLAYER),
	DRON(1,"DRON",SpawnType.Enemy_01,GameLogicInformation.MAX_LIFE_DRON,GameLogicInformation.MAX_SHIELD_DRON, GameLogicInformation.MAX_AMMO_DRON),
	TANK_1(2,"TANK_1",SpawnType.Tank_Level_1,GameLogicInformation.MAX_LIFE_TANK_1,GameLogicInformation.MAX_SHIELD_TANK_1, GameLogicInformation.MAX_AMMO_TANK_1),
	TANK_2(3,"TANK_2",SpawnType.Tank_Level_2,GameLogicInformation.MAX_LIFE_TANK_2,GameLogicInformation.MAX_SHIELD_TANK_2, GameLogicInformation.MAX_AMMO_TANK_2),
	TANK_3(4,"TANK_3",SpawnType.Tank_Level_3,GameLogicInformation.MAX_LIFE_TANK_3,GameLogicInformation.MAX_SHIELD_TANK_3, GameLogicInformation.MAX_AMMO_TANK_3),
	CENTROID(5,"CENTROID",SpawnType.Enemy_03,GameLogicInformation.MAX_LIFE_WATCHTOWER,GameLogicInformation.MAX_SHIELD_WATCHTOWER, GameLogicInformation.MAX_AMMO_WATCHTOWER),
	MINE(6,"MINE",SpawnType.Item_Mine,0,0,0);
	
	private int index;
	private String name;
	private SpawnType type;
	private int life;
	private int shield;
	private int ammo;
	
	
	private ElementDataEnum(int index, String name, SpawnType type, int life, int shield, int ammo) {
		this.index = index;
		this.name = name;
		this.type = type;
		this.life = life;
		this.shield = shield;
		this.ammo = ammo;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpawnType getType() {
		return type;
	}

	public void setType(SpawnType type) {
		this.type = type;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getAmmo() {
		return ammo;
	}
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	
	public static ElementDataEnum getBySpawnType(SpawnType type) {
		
		ElementDataEnum EDE[] = ElementDataEnum.values();
		ElementDataEnum elem =  ElementDataEnum.PLAYER;
		
		for(int i=0; i<EDE.length; i++) {
			ElementDataEnum EElem = EDE[i];
			if (EElem.getType().equals(type)) {
				elem = EElem;
				break;
			}
		}
		
		return elem;
	}
	
	
	
	public static ElementDataEnum getByIndex(int index) {
		
		ElementDataEnum EDE[] = ElementDataEnum.values();
		ElementDataEnum elem =  ElementDataEnum.PLAYER;
		
		for(int i=0; i<EDE.length; i++) {
			ElementDataEnum EElem = EDE[i];
			if (EElem.getIndex() == index) {
				elem = EElem;
				break;
			}
		}
		
		return elem;
	}
	
	

}
