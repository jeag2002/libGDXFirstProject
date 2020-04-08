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
        rectCollision.setPosition(getX() + collOffsetX, getY() + collOffsetY);
        return rectCollision;
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
