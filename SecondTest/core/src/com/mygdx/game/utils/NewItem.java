package com.mygdx.game.utils;

import com.mygdx.game.enums.DynamicElementPositionEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.ia.MapConnection;

public class NewItem {
	
	private SpawnType type;
	private SpawnType subType;

	private DynamicElementPositionEnum ppEnum;
	
	private float X;
	private float Y;

	private int index_X;
	private int index_Y;
	
	private float width;
	private float height;
	
	private float angle;
	private float speed;
	
	private int index; 
	
	private String idCode;
	
	
	public NewItem(NewItem copy) {
		
		this.type = copy.getType();
		this.subType = copy.getSubType();
		
		this.ppEnum = copy.getPlayerPosition();
		this.X = copy.getX();
		this.Y = copy.getY();
		
		this.index_X = copy.getIndex_X();
		this.index_Y = copy.getIndex_Y();
		
		this.width = copy.getWidth();
		this.height = copy.getHeight();
		this.angle = copy.getAngle();
		this.speed = copy.getSpeed();
		this.index = copy.getIndex();
		this.idCode = copy.getIdCode();
		
	}
	
	
	
	public NewItem() {
		this.type = SpawnType.Item;
		this.subType = SpawnType.Item;
		
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = 0.0f;
		this.Y = 0.0f;
		
		this.index_X = 0;
		this.index_Y = 0;
		
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = "";
	}
	
	
	public NewItem(SpawnType type, String idCode) {
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = 0.0f;
		this.Y = 0.0f;
		
		this.index_X = 0;
		this.index_Y = 0;
		
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
	}
	
	public NewItem(SpawnType type, String idCode, int index_X, int index_Y) {
		
		
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = 0.0f;
		this.Y = 0.0f;
		
		this.index_X = index_X;
		this.index_Y = index_Y;
		
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
		
		
	}
	
	public NewItem(SpawnType type, String idCode, float X, float Y, float W, float H, int index_X, int index_Y) {
		
		
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = X;
		this.Y = Y;
		
		this.index_X = index_X;
		this.index_Y = index_Y;
		
		this.width = W;
		this.height = H;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
		
		
	}
	
	
	
	
	public NewItem(SpawnType type, SpawnType subType, String idCode, float X, float Y, float W, float H) {
		
		
		super();
		this.type = type;
		this.subType = subType;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = X;
		this.Y = Y;
		
		this.index_X = 0;
		this.index_Y = 0;
		
		this.width = W;
		this.height = H;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
		
		
	}
	
	
	public NewItem(SpawnType type, SpawnType subType, String idCode, float X, float Y, float W, float H, int index_X, int index_Y) {
		
		
		super();
		this.type = type;
		this.subType = subType;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = X;
		this.Y = Y;
		
		this.index_X = index_X;
		this.index_Y = index_Y;
		
		this.width = W;
		this.height = H;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
		
		
	}
	
	
	
	
	public NewItem(SpawnType type, String idCode, float X, float Y, float W, float H) {
		
		
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = X;
		this.Y = Y;
		
		this.index_X = 0;
		this.index_Y = 0;
		
		this.width = W;
		this.height = H;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = idCode;
		
		
	}
	
	
	

	public NewItem(SpawnType type, float x, float y) {
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		
		this.index_X = 0;
		this.index_Y = 0;
		
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = "";		
	}
	
	public NewItem(SpawnType type, float x, float y, int index) {
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		this.index_X = 0;
		this.index_Y = 0;
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = index;
		this.idCode = "";
	}
	
	public NewItem(SpawnType type, float x, float y, int index_X, int index_Y, int index) {
		
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		this.index_X = index_X;
		this.index_Y = index_Y;
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = index;
		this.idCode = "";
		
		
	}
	
	
	
	public NewItem(SpawnType type, DynamicElementPositionEnum position, float x, float y) {
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = position;
		this.X = x;
		this.Y = y;
		this.index_X = 0;
		this.index_Y = 0;
		this.width = 0.0f;
		this.height = 0.0f;
		this.angle = 0.0f;
		this.speed = 0.0f;
		this.index = 0;
		this.idCode = "";
	}
	
	
	public NewItem(SpawnType type, float x, float y, float width, float height, float angle, float speed) {
		
		super();
		this.type = type;
		this.subType = SpawnType.Item;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		this.index_X = 0;
		this.index_Y = 0;
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.speed = speed;
		this.index = 0;
		this.idCode = "";
	}
	
	
	public NewItem(SpawnType type, SpawnType subType, float x, float y, float width, float height) {
		
		super();
		this.type = type;
		this.subType = subType;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		this.index_X = 0;
		this.index_Y = 0;
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.speed = 0;
		this.index = 0;
		this.idCode = "";
		
	}
	
	
	public NewItem(SpawnType type, SpawnType subType, float x, float y, float width, float height, float angle, float speed) {
		
		super();
		this.type = type;
		this.subType = subType;
		this.ppEnum = DynamicElementPositionEnum.IDLE;
		this.X = x;
		this.Y = y;
		this.index_X = 0;
		this.index_Y = 0;
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.speed = speed;
		this.index = 0;
		this.idCode = "";
	}
	
	
	
	
	public String toString() {
		if (type.equals(SpawnType.Path_Node)) {
			return "node id " + index +  "(" + index_X + "," + index_Y + ")";
		}else {
			return "type " + type + " enum " + ppEnum +  " X (" + X + ") Y (" + Y + ") width (" + width + ") height (" + height + ") angle (" + angle + ") speed (" + speed + ") idCode [" + idCode + "]"; 
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null) return false;
	        if (getClass() != obj.getClass()) return false;
	        
	        final NewItem other = (NewItem)obj;
	        
	        boolean test = (other.getIndex_X() == this.getIndex_X()) && (other.getIndex_Y() == this.getIndex_Y());
	        return test;
	        
	        
	}        
	
	
	public DynamicElementPositionEnum getPlayerPosition() {
		return ppEnum;
	}
	
	public SpawnType getType() {
		return type;
	}

	public void setType(SpawnType type) {
		this.type = type;
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex_X() {
		return index_X;
	}


	public void setIndex_X(int index_X) {
		this.index_X = index_X;
	}


	public int getIndex_Y() {
		return index_Y;
	}


	public void setIndex_Y(int index_Y) {
		this.index_Y = index_Y;
	}
	
	public SpawnType getSubType() {
		return subType;
	}

	public void setSubType(SpawnType subType) {
		this.subType = subType;
	}
	

}
