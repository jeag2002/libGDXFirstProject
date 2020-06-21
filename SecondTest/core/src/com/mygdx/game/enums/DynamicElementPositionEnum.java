package com.mygdx.game.enums;


import com.mygdx.game.SecondTestGDX;

public enum DynamicElementPositionEnum {
	
	LEFTHIGH (0, 0, SecondTestGDX.sizeMapTileWidth_TL/2, SecondTestGDX.sizeMapTileHeight_TL/2, SecondTestGDX.sizeMapTileHeight_TL),
	LEFTDOWN (1, 0, SecondTestGDX.sizeMapTileWidth_TL/2, 0, SecondTestGDX.sizeMapTileHeight_TL),
	RIGHTHIGH (2, SecondTestGDX.sizeMapTileWidth_TL/2, SecondTestGDX.sizeMapTileWidth_TL, SecondTestGDX.sizeMapTileHeight_TL/2, SecondTestGDX.sizeMapTileHeight_TL),
	RIGHTDOWN (3, SecondTestGDX.sizeMapTileWidth_TL/2, SecondTestGDX.sizeMapTileWidth_TL, 0, SecondTestGDX.sizeMapTileHeight_TL),
	IDLE (5,0,0,0,0);
	
	int index;
	int xIni;
	int xFin;
	int yIni;
	int yFin;
	
	private DynamicElementPositionEnum(int index, int xIni, int xFin, int yIni, int yFin) {
		this.index = index;
		this.xIni = xIni;
		this.xFin = xFin;
		this.yIni = yIni;
		this.yFin = yFin;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getXIni() {
		return this.xIni;
	}
	
	public int getXFin() {
		return this.xFin;
	}
	
	public int getYIni() {
		return this.yIni;
	}
	
	public int getYFin() {
		return this.yFin;
	}
	
	public static DynamicElementPositionEnum getByIndex(int index) {
		
		DynamicElementPositionEnum PPE[] = DynamicElementPositionEnum.values();
		DynamicElementPositionEnum position = DynamicElementPositionEnum.IDLE;
		
		for(int i=0; i<PPE.length; i++) {
			DynamicElementPositionEnum PPElem = PPE[i];
			if (PPElem.getIndex() == index) {
				position = PPElem;
				break;
			}
		}
		
		return position;
	}
	
	
	
	
}
