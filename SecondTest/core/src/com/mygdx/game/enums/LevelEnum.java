package com.mygdx.game.enums;

import com.mygdx.game.logic.*;

public enum LevelEnum {
	
	DESERT(0,"DESERT DAY",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100, 100, 0),
	DESERT_NIGHT(1,"DESERT NIGHT",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.LIGHTS, 0, 0, 100, 4),
	JUNGLE(2,"JUNGLE DAY",GameLogicInformation.JUNGLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0,7),
	FABRIC(3,"FABRIC DAY",GameLogicInformation.FABRIC_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0,6),
	WINTER(4,"WINTER DAY",GameLogicInformation.WINTER_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100,0, 0),
	VOLCANO(5,"VOLCANO DAY",GameLogicInformation.VOLCANO_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 0),
	CITY(6,"CITY DAY",GameLogicInformation.CITY_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 6 ),
	SPACE(7,"SPACE", GameLogicInformation.SPACE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 0),
	BADLANDS(8,"BADLANDS DAY", GameLogicInformation.BADLAND_LEVEL, GameLogicInformation.NO_LIGHTS, 100,100,100,0),
	IDLE(12,"IDLE",GameLogicInformation.IDLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 0);
	
	
	
	
	
	private int index;
	private String LevelStr;
	private int type;
	private int lights;
	private int numTurrets;
	private int numDrons;
	private int numMines;
	private int numTanks;


	private LevelEnum(int index, String levelStr, int type, int lights, int numTurrets, int numDrons, int numMines, int numTanks) {
		this.index = index;
		LevelStr = levelStr;
		this.type = type;
		this.lights = lights;
		this.numTurrets = numTurrets;
		this.numDrons = numDrons;
		this.numMines = numMines;
		this.numTanks = numTanks;
	}
	
	
	
	public static LevelEnum getByIndex(int index) {
		
		LevelEnum LE[] = LevelEnum.values();
		LevelEnum LEnum =  LevelEnum.IDLE;
		
		for(int i=0; i<LE.length; i++) {
			LevelEnum elem = LE[i];
			if (elem.getIndex() == index) {
				LEnum = elem;
				break;
			}
		}
		
		return LEnum;
	}
	
	public int getNumTurrets() {
		return numTurrets;
	}
	public void setNumTurrets(int numTurrets) {
		this.numTurrets = numTurrets;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getLevelStr() {
		return LevelStr;
	}
	public void setLevelStr(String levelStr) {
		LevelStr = levelStr;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLights() {
		return lights;
	}
	public void setLights(int lights) {
		this.lights = lights;
	}
	public int getNumDrons() {
		return numDrons;
	}
	public void setNumDrons(int numDrons) {
		this.numDrons = numDrons;
	}
	public int getNumMines() {
		return numMines;
	}
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
	public int getNumTanks() {
		return numTanks;
	}
	public void setNumTanks(int numTanks) {
		this.numTanks = numTanks;
	}
	

}
