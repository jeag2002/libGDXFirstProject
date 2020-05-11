package com.gdx.game.stages.enums;

public enum CannonTypeEnum {
	CannonSpiral("Cannon_Spiral",0),
	CannonRadial("Cannon_Radial",1);
	
	private String cannonTypeStr;
	private int index;
	
	private CannonTypeEnum(String cannonTypeStr, int index) {
		this.cannonTypeStr = cannonTypeStr;
		this.index = index;
	}
	
	public String getCannonTypeStr() {
		return this.cannonTypeStr;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	
	public CannonTypeEnum getByIndex (int index) {
		
		CannonTypeEnum CTE[] = CannonTypeEnum.values();
		CannonTypeEnum cannon = CannonTypeEnum.CannonSpiral;
		
		for(int i=0; i<CTE.length; i++) {
			CannonTypeEnum CTElem = CTE[i];
			if (CTElem.getIndex() == index) {
				 cannon = CTElem;
			}
		}
		return cannon;
		
	}
	
	
}
