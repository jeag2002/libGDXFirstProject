package com.mygdx.game.utils;

import com.mygdx.game.enums.DynamicElementPositionEnum;
import com.mygdx.game.enums.SpawnType;

public class NewItem {
	
	private SpawnType type;
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
	
	
	public NewItem() {
		this.type = SpawnType.Item;
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
	
	

	public NewItem(SpawnType type, float x, float y) {
		super();
		this.type = type;
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
		return "type " + type + " enum " + ppEnum +  " X (" + X + ") Y (" + Y + ") width (" + width + ") height (" + height + ") angle (" + angle + ") speed (" + speed + ") idCode [" + idCode + "]"; 
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
	

}
