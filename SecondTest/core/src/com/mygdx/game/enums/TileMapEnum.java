package com.mygdx.game.enums;


import com.mygdx.game.*;

public enum TileMapEnum {

	BLOCK_A_01(SecondTestGDX.resources.block_A_01,0,256,128,256,128),
	BLOCK_A_02(SecondTestGDX.resources.block_A_02,1,128,128,64,64),
	
	BLOCK_B_01(SecondTestGDX.resources.block_B_01,2,256,128,256,128),
	BLOCK_B_02(SecondTestGDX.resources.block_B_02,3,128,128,64,64),
	
	BLOCK_C_01(SecondTestGDX.resources.block_C_01,4,256,128,256,128),
	BLOCK_C_02(SecondTestGDX.resources.block_C_02,5,128,128,64,64),
	
	BLOCK_D_01(SecondTestGDX.resources.block_D_01,6,256,128,256,128),
	BLOCK_D_02(SecondTestGDX.resources.block_D_02,7,128,128,64,64),

	PALM_01(SecondTestGDX.resources.Palm_01,8,128,128,64,64),
	PALM_02(SecondTestGDX.resources.Palm_02,9,128,128,64,64),
	PALM_03(SecondTestGDX.resources.Palm_03,10,128,128,64,64),
	
	CACTUS_01(SecondTestGDX.resources.Cactus_01,11,128,128,64,64),
	CACTUS_02(SecondTestGDX.resources.Cactus_02,12,128,128,64,64),
	CACTUS_03(SecondTestGDX.resources.Cactus_03,13,128,128,64,64),
	
	GROUND_TILE_01_A(SecondTestGDX.resources.ground_tile_01_A,14, 256, 256, 256, 256),
	GROUND_TILE_01_B(SecondTestGDX.resources.ground_tile_01_B,15, 256, 256, 256, 256),
	GROUND_TILE_01_C(SecondTestGDX.resources.ground_tile_01_C,16, 256, 256, 256, 256),
	GROUND_TILE_01_D(SecondTestGDX.resources.ground_tile_01_D,17, 256, 256, 256, 256),
	
	GROUND_TILE_02_A(SecondTestGDX.resources.ground_tile_02_A,18, 256, 256, 256, 256),
	GROUND_TILE_02_B(SecondTestGDX.resources.ground_tile_02_B,19, 256, 256, 256, 256),
	GROUND_TILE_02_C(SecondTestGDX.resources.ground_tile_02_C,20, 256, 256, 256, 256),
	GROUND_TILE_02_D(SecondTestGDX.resources.ground_tile_02_D,21, 256, 256, 256, 256),
	
	CZECH_01(SecondTestGDX.resources.Czech_01,11,256,256,64,64),
	CZECH_02(SecondTestGDX.resources.Czech_02,12,256,256,64,64);
	
	
	private String tileMapStr;
	private int index;
	private int width_bef;
	private int height_bef;
	private int width_show;
	private int height_show;
	
	private TileMapEnum(String tileMapStr, int index, int width_bef, int height_bef, int width_show, int height_show) {
		this.tileMapStr = tileMapStr;
		this.index = index;
		
		this.width_bef = width_bef;
		this.height_bef = height_bef;
		this.width_show = width_show;
		this.height_show = height_show;
	}
	
	public String getTileMapStr() {
		return this.tileMapStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getWidthShow() {
		return width_show;
	}

	public void setWidthShow(int width) {
		this.width_show = width;
	}
	
	public int getWidthBef() {
		return width_bef;
	}

	public void setWidthBef(int width) {
		this.width_bef = width;
	}
	
	public int getHeightShow() {
		return height_show;
	}

	public void setHeightShow(int height) {
		this.height_show = height;
	}
	
	public int getHeightBef() {
		return height_bef;
	}

	public void setHeightBef(int height) {
		this.height_bef = height;
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
