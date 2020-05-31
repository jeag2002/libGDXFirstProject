package com.mygdx.game.enums;


import com.mygdx.game.*;

public enum TileMapEnum {

	BLOCK_A_01(SecondTestGDX.resources.block_A_01,0),
	BLOCK_A_02(SecondTestGDX.resources.block_A_02,1),
	
	GROUND_TILE_01_A(SecondTestGDX.resources.ground_tile_01_A,2),
	GROUND_TILE_01_B(SecondTestGDX.resources.ground_tile_01_B,3),
	GROUND_TILE_01_C(SecondTestGDX.resources.ground_tile_01_C,4),
	
	GROUND_TILE_02_A(SecondTestGDX.resources.ground_tile_02_A,5),
	GROUND_TILE_02_B(SecondTestGDX.resources.ground_tile_02_B,6),
	GROUND_TILE_02_C(SecondTestGDX.resources.ground_tile_03_C,7);
	
	
	private String tileMapStr;
	private int index;
	
	private TileMapEnum(String tileMapStr, int index) {
		this.tileMapStr = tileMapStr;
		this.index = index;
	}
	
	public String getTileMapStr() {
		return this.tileMapStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public TileMapEnum getByIndex(int index) {
		
		TileMapEnum TME[] = TileMapEnum.values();
		TileMapEnum tile = TileMapEnum.GROUND_TILE_02_C;
		
		for(int i=0; i<TME.length; i++) {
			TileMapEnum TMElem = TME[i];
			if (TMElem.getIndex() == index) {
				tile = TMElem;
				break;
			}
		}
		
		return tile;
	}
	
}
