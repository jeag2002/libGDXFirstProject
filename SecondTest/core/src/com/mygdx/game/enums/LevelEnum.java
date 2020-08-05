package com.mygdx.game.enums;

import com.mygdx.game.logic.*;

public enum LevelEnum {
	
	DESERT(0,"LEVEL 1",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100, 100, 4),
	JUNGLE(1,"LEVEL 2",GameLogicInformation.JUNGLE_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100, 100, 4),
	WINTER(2,"LEVEL 3",GameLogicInformation.WINTER_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100,0,4),
	VOLCANO(3,"LEVEL 4",GameLogicInformation.VOLCANO_LEVEL, GameLogicInformation.NO_LIGHTS, 100,100,100,4),
	SPACE(4,"LEVEL 5", GameLogicInformation.SPACE_LEVEL, GameLogicInformation.NO_LIGHTS,100,100,100,4),
	CITY(5,"LEVEL 6", GameLogicInformation.CITY_LEVEL, GameLogicInformation.NO_LIGHTS,100, 100,100,4),
	DESERT_LIGHTS(6,"LEVEL 7",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.LIGHTS,100,100,100,4),
	FABRIC(7,"LEVEL 8",GameLogicInformation.FABRIC_LEVEL,GameLogicInformation.NO_LIGHTS,100, 100,100,4),
	VOLCANO_LIGHTS(8,"LEVEL 9",GameLogicInformation.VOLCANO_LEVEL, GameLogicInformation.LIGHTS,100, 100,100,4),
	FABRIC_LIGHTS(9,"LEVEL 10",GameLogicInformation.FABRIC_LEVEL, GameLogicInformation.LIGHTS,100, 100,100,4);
	
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
		LevelEnum LEnum =  LevelEnum.DESERT;
		
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
