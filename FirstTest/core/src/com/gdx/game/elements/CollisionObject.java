package com.gdx.game.elements;

import com.badlogic.gdx.math.Rectangle;

public class CollisionObject{

    private Rectangle rectCollision = new Rectangle();
    private float collOffsetX = 20;
    private float collOffsetY = 10;
	private float X;
    private float Y;
    
    
    public CollisionObject() {
    	this.X = 0;
    	this.Y = 0;
    }
    
    
    public CollisionObject(float X, float Y) {
    	this.X = X;
    	this.Y = Y;
    	
    }
    
    public void setCollisionRef(float X, float Y) {
    	this.X = X;
    	this.Y = Y;
    }
    

    public void setCollisionArea(int offsetX, int offsetY, int width, int height) {
        rectCollision = new Rectangle(0, 0, width, height);
        collOffsetX = offsetX;
        collOffsetY = offsetY;
    }

    public Rectangle getCollisionRectangle() {
        rectCollision.setPosition(getXColl() + collOffsetX, getYColl() + collOffsetY);
        return rectCollision;
    }
	
    
    
    public float getXColl() {
		return X;
	}


	public void setXColl(float x) {
		X = x;
	}


	public float getYColl() {
		return Y;
	}


	public void setYColl(float y) {
		Y = y;
	}
}
