package com.gdx.game.elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.engine.logic.GameLevelLogic;

public class CollisionObject{



    private float X;
    private float Y;
	private float W;
    private float H;
    
    
    private World world;
    private Body body;
    private PolygonShape shape;
    private FixtureDef fixtureDef;
    
    private DynamicCollObject ref;
    
    public CollisionObject(World world) {
    	
    	this.X = 0;
    	this.Y = 0;
    	this.W = 0;
    	this.H = 0;
    	
    	this.world = world;
    	
    }
    
    public void setReference(DynamicCollObject ref) {
    	this.ref = ref;
    }
    
    
    public void createCollisionObject(float X, float Y, float W, float H, BodyType type) {
    	
    	BodyDef bodyDef = new BodyDef();
    	bodyDef.type = BodyDef.BodyType.DynamicBody;
    	
    	this.X = X;
    	this.Y = Y;
    	this.W = W;
    	this.H = H;
    	
    	float iniX = (X+W/2)/GameLevelLogic.PIXELS_TO_METERS;
    	float iniY = (Y+H/2)/GameLevelLogic.PIXELS_TO_METERS;
    	bodyDef.position.set(iniX,iniY);
    	
    	body = world.createBody(bodyDef);
    	shape = new PolygonShape();
    	
    	float iniW =  W/2/GameLevelLogic.PIXELS_TO_METERS;
    	float iniH =  H/2/GameLevelLogic.PIXELS_TO_METERS;
    	shape.setAsBox(iniW, iniH);
    	
    	fixtureDef = new FixtureDef();
    	fixtureDef.shape = shape;
    	
    	body.createFixture(fixtureDef);
    	body.setUserData(ref.getCode());
    	
    	shape.dispose();
    	
    }
    
    
    public Body getBody() {
    	return body;
    }
    
    
    public void setCollisionRef(float X, float Y) {
    	this.X = (X+this.W/2)/GameLevelLogic.PIXELS_TO_METERS;
    	this.Y = (Y+this.H/2)/GameLevelLogic.PIXELS_TO_METERS;
    	body.setTransform(this.X, this.Y, 0);	
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
	
	public float getWColl() {
		return W;
	}
	
	public void setWColl(float w) {
		W = w;
	}

	public float getHColl() {
		return H;
	}

	public void setHColl(float h) {
		H = h;
	}

	
}
