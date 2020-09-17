package com.mygdx.game.enums;

import com.mygdx.game.logic.*;

public enum LevelEnum {
	
	DESERT(0,"DESERT DAY",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100, 100, 0, "- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	DESERT_NIGHT(1,"DESERT NIGHT",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	JUNGLE(2,"JUNGLE DAY",GameLogicInformation.JUNGLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	CITY(3,"CITY DAY",GameLogicInformation.CITY_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6,"- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	SPACE(4,"SPACE", GameLogicInformation.SPACE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0,"- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	FABRIC(5,"INDUSTRY AREA DAY",GameLogicInformation.FABRIC_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	WINTER(6,"WINTER DAY",GameLogicInformation.WINTER_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0, "- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	VOLCANO(7,"VOLCANO DAY",GameLogicInformation.VOLCANO_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0,"- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	IDLE(12,"IDLE",GameLogicInformation.IDLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 0,"","","");
	
	/*
	DESERT(0,"DESERT DAY",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.NO_LIGHTS, 100, 100, 100, 0, "- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	DESERT_NIGHT(1,"DESERT NIGHT",GameLogicInformation.DESERT_LEVEL, GameLogicInformation.LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	JUNGLE(2,"JUNGLE DAY",GameLogicInformation.JUNGLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	JUNGLE_NIGHT(3,"JUNGLE NIGHT",GameLogicInformation.JUNGLE_LEVEL, GameLogicInformation.LIGHTS, 100, 100, 100, 0, "- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	FABRIC(4,"INDUSTRY AREA DAY",GameLogicInformation.FABRIC_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6, "- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	WINTER(5,"WINTER DAY",GameLogicInformation.WINTER_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0, "- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	VOLCANO(6,"VOLCANO DAY",GameLogicInformation.VOLCANO_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0,"- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	CITY(7,"CITY DAY",GameLogicInformation.CITY_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 0, 0, 6,"- ARRIVE TO EXIT IN TIME (YELLOW)","- DESTROY ALL THE ENEMY TANKS",""),
	SPACE(8,"SPACE", GameLogicInformation.SPACE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 200, 0, 0,"- ARRIVE TO EXIT IN TIME (YELLOW)","- LEFT MAXIMUM " + GameLogicInformation.MIN_ENEMIES_TO_EXIT + " ENEMIES ",""),
	IDLE(12,"IDLE",GameLogicInformation.IDLE_LEVEL, GameLogicInformation.NO_LIGHTS, 0, 100, 0, 0,"","","");
	*/
	private int index;
	private String LevelStr;
	private int type;
	private int lights;
	private int numTurrets;
	private int numDrons;
	private int numMines;
	private int numTanks;
	private String msg_1;
	private String msg_2;
	private String msg_3;


	private LevelEnum(int index, String levelStr, int type, int lights, int numTurrets, int numDrons, int numMines, int numTanks, String msg_1, String msg_2, String msg_3) {
		this.index = index;
		LevelStr = levelStr;
		this.type = type;
		this.lights = lights;
		this.numTurrets = numTurrets;
		this.numDrons = numDrons;
		this.numMines = numMines;
		this.numTanks = numTanks;
		this.msg_1 = msg_1;
		this.msg_2 = msg_2;
		this.msg_3 = msg_3;
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
	
	public String getMsg_1() {
		return msg_1;
	}



	public void setMsg_1(String msg_1) {
		this.msg_1 = msg_1;
	}



	public String getMsg_2() {
		return msg_2;
	}



	public void setMsg_2(String msg_2) {
		this.msg_2 = msg_2;
	}



	public String getMsg_3() {
		return msg_3;
	}



	public void setMsg_3(String msg_3) {
		this.msg_3 = msg_3;
	}
	

}
