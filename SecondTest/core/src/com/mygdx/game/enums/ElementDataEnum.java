package com.mygdx.game.enums;

public enum ElementDataEnum {
	
	PLAYER(0,"PLAYER",SpawnType.Player_01,100,100,100),
	DRON(1,"DRON",SpawnType.Enemy_01,100,100,100),
	TANK(2,"TANK",SpawnType.Enemy_02,100,100,100),
	CENTROID(3,"CENTROID",SpawnType.Enemy_03,100,100,100),
	MINE(4,"MINE",SpawnType.Item_Mine,0,0,0);
	
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
