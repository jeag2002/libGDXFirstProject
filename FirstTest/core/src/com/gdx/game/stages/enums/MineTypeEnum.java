package com.gdx.game.stages.enums;

public enum MineTypeEnum {
	
	MineSimple("Mine_Simple",0),
	MineRadial("Mine_Radial",1);
	
	private String mineTypeStr;
	private int index;
	
	private MineTypeEnum(String mineTypeStr, int index) {
		this.mineTypeStr = mineTypeStr;
		this.index = index;
	}
	
	public String getMineTypeStr() {
		return this.mineTypeStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public MineTypeEnum getByIndex (int index) {
		
		MineTypeEnum MTE[] = MineTypeEnum.values();
		MineTypeEnum mine = MineTypeEnum.MineSimple;
		
		for(int i=0; i<MTE.length; i++) {
			MineTypeEnum MTElem = MTE[i];
			if (MTElem.getIndex() == index) {
				 mine = MTElem;
			}
		}
		return mine;
		
	}

	

}
