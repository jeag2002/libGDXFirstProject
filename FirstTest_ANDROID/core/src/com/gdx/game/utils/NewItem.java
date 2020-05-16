package com.gdx.game.utils;

import com.gdx.game.stages.enums.SpawnType;

public class NewItem {
	
	private SpawnType type;
	private float X;
	private float Y;
	
	public NewItem() {
		this.type = SpawnType.Item;
		this.X = 0.0f;
		this.Y = 0.0f;
	}

	public NewItem(SpawnType type, float x, float y) {
		super();
		this.type = type;
		X = x;
		Y = y;
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
	
	
	

}
