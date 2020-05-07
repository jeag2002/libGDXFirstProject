package com.gdx.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.gdx.game.engine.logic.GameLevelInformation;

public class NewStaticItem {
	
	private String mapElement;
	private Rectangle rect;
	
	public NewStaticItem() {
		rect = new Rectangle();
		mapElement = GameLevelInformation.TURRET;
	}
	
	public NewStaticItem(Rectangle rect, String mapElement) {
		this.rect = rect;
		this.mapElement = mapElement;
	}
	
	public String getMapElement() {
		return mapElement;
	}
	public void setMapElement(String mapElement) {
		this.mapElement = mapElement;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	

}
