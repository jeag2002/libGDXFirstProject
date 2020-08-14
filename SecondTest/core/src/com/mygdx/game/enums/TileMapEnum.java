package com.mygdx.game.enums;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.*;

public enum TileMapEnum {

	BLOCK_A_01(SecondTestGDX.resources.block_A_01,0,256,128,256,128),
	BLOCK_A_02(SecondTestGDX.resources.block_A_02,1,128,128,128,128),
	
	BLOCK_B_01(SecondTestGDX.resources.block_B_01,2,256,128,256,128),
	BLOCK_B_02(SecondTestGDX.resources.block_B_02,3,128,128,128,128),
	
	BLOCK_C_01(SecondTestGDX.resources.block_C_01,4,256,128,256,128),
	BLOCK_C_02(SecondTestGDX.resources.block_C_02,5,128,128,128,128),
	
	BLOCK_D_01(SecondTestGDX.resources.block_D_01,6,256,128,256,128),
	BLOCK_D_02(SecondTestGDX.resources.block_D_02,7,128,128,128,128),

	PALM_01(SecondTestGDX.resources.Palm_01,8,128,128,128,128),
	PALM_02(SecondTestGDX.resources.Palm_02,9,128,128,128,128),
	PALM_03(SecondTestGDX.resources.Palm_03,10,128,128,128,128),
	
	CACTUS_01(SecondTestGDX.resources.Cactus_01,11,128,128,128,128),
	CACTUS_02(SecondTestGDX.resources.Cactus_02,12,128,128,128,128),
	CACTUS_03(SecondTestGDX.resources.Cactus_03,13,128,128,128,128),
	
	GROUND_TILE_01_A(SecondTestGDX.resources.ground_tile_01_A,14, 256, 256, 256, 256),
	GROUND_TILE_01_B(SecondTestGDX.resources.ground_tile_01_B,15, 256, 256, 256, 256),
	GROUND_TILE_01_C(SecondTestGDX.resources.ground_tile_01_C,16, 256, 256, 256, 256),
	GROUND_TILE_01_D(SecondTestGDX.resources.ground_tile_01_D,17, 256, 256, 256, 256),
	
	GROUND_TILE_02_A(SecondTestGDX.resources.ground_tile_02_A,18, 256, 256, 256, 256),
	GROUND_TILE_02_B(SecondTestGDX.resources.ground_tile_02_B,19, 256, 256, 256, 256),
	GROUND_TILE_02_C(SecondTestGDX.resources.ground_tile_02_C,20, 256, 256, 256, 256),
	GROUND_TILE_02_D(SecondTestGDX.resources.ground_tile_02_D,21, 256, 256, 256, 256),
	
	
	GROUND_TILE_01_E(SecondTestGDX.resources.ground_tile_01_E,22,256, 256, 256, 256),
	GROUND_TILE_02_E(SecondTestGDX.resources.ground_tile_02_E,23,256, 256, 256, 256),
		
	BLOCK_E_01(SecondTestGDX.resources.block_E_01,24,459,459,128,128),
	BLOCK_E_02(SecondTestGDX.resources.block_E_02,25,255,256,128,128),
	ICEBERG_01(SecondTestGDX.resources.Iceberg_01,26,483,529,128,128),
	
	CZECH_01(SecondTestGDX.resources.Czech_01,27,256,256,128,128),
	CZECH_02(SecondTestGDX.resources.Czech_02,28,256,256,128,128),
	SOLAR_01(SecondTestGDX.resources.Solar_01,29,178,103,128,128),
	SOLAR_02(SecondTestGDX.resources.Solar_02,30,128,128,128,128),
	
	GROUND_TILE_01_F(SecondTestGDX.resources.ground_tile_01_F,31,256, 256, 256, 256),
	GROUND_TILE_02_F(SecondTestGDX.resources.ground_tile_02_F,32,256, 256, 256, 256),
	
	TREE_01(SecondTestGDX.resources.Tree_01,33,128,128,128,128),
	TREE_02(SecondTestGDX.resources.Tree_02,34,128,128,128,128),
	TREE_03(SecondTestGDX.resources.Tree_03,35,128,128,128,128),
	TREE_04(SecondTestGDX.resources.Tree_04,36,128,128,128,128),
	TREE_05(SecondTestGDX.resources.Tree_05,37,128,128,128,128),
	TREE_06(SecondTestGDX.resources.Tree_06,38,128,128,128,128),
	TREE_07(SecondTestGDX.resources.Tree_07,39,128,128,128,128),
	TREE_08(SecondTestGDX.resources.Tree_08,40,128,128,128,128),
	TREE_09(SecondTestGDX.resources.Tree_09,41,128,128,128,128),
	
	BLOCK_F_01(SecondTestGDX.resources.block_F_01,42,256,128,256,128),
	BLOCK_F_02(SecondTestGDX.resources.block_F_02,43,128,128,128,128),
	
	BUILDING_01(SecondTestGDX.resources.Building_01,44,512,512,128,128),
	
	VOLCANO_FLOOR(SecondTestGDX.resources.VolcanoFloor,45,1024,1024,256,256),
	VOLCANO_WALL(SecondTestGDX.resources.VolcanoWall,46,128,128,128,128),
	VOLCANO_FOREST_1(SecondTestGDX.resources.VolcanoForest_1,47,2000,2000,128,128),
	VOLCANO_FOREST_2(SecondTestGDX.resources.VolcanoForest_2,48,2000,2000,128,128),
	
	BACKGROUND_CITY(SecondTestGDX.resources.background_city,49,258,258,256,256),
	WALL_CITY(SecondTestGDX.resources.wall_city,50,128,128,128,128),
	//WALL_CITY(SecondTestGDX.resources.wall_city,50,128,128,256,256),
	WALL_CITY_NORMAL(SecondTestGDX.resources.wall_city_normal,51,128,128,128,128),
	PARK_CITY(SecondTestGDX.resources.parterre,52,128,128,128,128),
	
	HOLE_BADLANDS(SecondTestGDX.resources.hole_1,53,459,459,128,128),
	HOLE_JUNGLE(SecondTestGDX.resources.hole_2,54,459,459,128,128),
	HOLE_CITY(SecondTestGDX.resources.hole_3,55,459,459,128,128),
	HOLE_FABRIC(SecondTestGDX.resources.hole_4,56,459,459,128,128),
	HOLE_VOLCANO(SecondTestGDX.resources.hole_5,57,459,459,128,128),
	HOLE_DESERT(SecondTestGDX.resources.hole_6,58,459,459,128,128),
	
	
	SPACE_WALL(SecondTestGDX.resources.space_wall,59,256,256,128,128),
	SPACE_FOREST_1(SecondTestGDX.resources.space_forest,60,256,256,128,128),
	SPACE_FAN_1(SecondTestGDX.resources.space_fan_1,61,256,256,128,128),
	SPACE_FAN_2(SecondTestGDX.resources.space_fan_2,62,256,256,128,128),
	SPACE_FAN_3(SecondTestGDX.resources.space_fan_3,63,256,256,128,128),
	SPACE_FAN_4(SecondTestGDX.resources.space_fan_4,64,256,256,128,128),
	
	WATER_1(SecondTestGDX.resources.water_1,64,512,512,128,128),
	
	SQUARE(SecondTestGDX.resources.testSquare, 65,128,128,128,128),
	
	BLOCK_D_02_1(SecondTestGDX.resources.block_D_02,65,128,128,256,256);
	
	
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
	
	
	
	public static TileMapEnum getByIndex(int index) {
		
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
