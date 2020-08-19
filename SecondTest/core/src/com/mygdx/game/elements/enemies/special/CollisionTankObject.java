package com.mygdx.game.elements.enemies.special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.utils.NewItem;

public class CollisionTankObject extends IAPathFindingObject{
	
    private float X;
    private float Y;
	private float W;
    private float H;
    
    private World world;
    private Body body;
    private PolygonShape shape;
    private FixtureDef fixtureDef;
    
    private String idCode;
    private SpawnType spawnType;
    
    public CollisionTankObject(World world, SpawnType type, String idCode) {
    	
    	super();
    	
    	this.X = 0;
    	this.Y = 0;
    	this.W = 0;
    	this.H = 0;
    	this.idCode = idCode;
    	this.world = world;
    	this.spawnType = type;
    	
    }
    
    
    public void createCollisionObject(float X, float Y, float W, float H, BodyType type) {
    	    	
    	BodyDef bodyDef = new BodyDef();
    	bodyDef.type = BodyDef.BodyType.DynamicBody;
    	
    	this.X = X;
    	this.Y = Y;
    	this.W = W;
    	this.H = H;
    	
    	float iniX = (X+W/2)/GameLogicInformation.PIXELS_TO_METERS;
    	float iniY = (Y+H/2)/GameLogicInformation.PIXELS_TO_METERS;
    	bodyDef.position.set(iniX,iniY);
    	
    	body = world.createBody(bodyDef);
    	shape = new PolygonShape();
    	
    	float iniW =  W/2/GameLogicInformation.PIXELS_TO_METERS;
    	float iniH =  H/2/GameLogicInformation.PIXELS_TO_METERS;
    	shape.setAsBox(iniW, iniH);
    	
    	fixtureDef = new FixtureDef();
    	fixtureDef.shape = shape;
    	
    	//fixtureDef.filter.groupIndex = GameLogicInformation.GROUP_MONSTER;
    	
    	body.createFixture(fixtureDef);
    	
    	body.setUserData(new NewItem(spawnType, idCode, X, Y, W, H));
    	
    	
    	shape.dispose();	
    }
    
    public void setCollisionAngleRef(float X, float Y, float angle) {	
    	this.X = (X+this.W/2)/GameLogicInformation.PIXELS_TO_METERS;
    	this.Y = (Y+this.H/2)/GameLogicInformation.PIXELS_TO_METERS;
    	body.setTransform(this.X, this.Y, angle);
    }
    
    public void setCollisionVel(float X, float Y) {
    	body.setLinearVelocity(X, Y);
    }
    
    public void setCollisionAngle(float angle) {
    	body.setAngularVelocity(angle);
    }
    
    public Vector2 getPositionFromBodyToPixel() {
    	Vector2 bodyPos = body.getPosition();
    	bodyPos.x = (bodyPos.x * GameLogicInformation.PIXELS_TO_METERS) - this.W/2; 
    	bodyPos.y = (bodyPos.y * GameLogicInformation.PIXELS_TO_METERS) - this.H/2;
    	return bodyPos;	
    }
    
    
    
    public Body getBody() {
    	return body;
    }
    
    public String getIdCode() {
    	return idCode;
    }
    
    public void setCollisionRef(float X, float Y) {
    	this.X = (X+this.W/2)/GameLogicInformation.PIXELS_TO_METERS;
    	this.Y = (Y+this.H/2)/GameLogicInformation.PIXELS_TO_METERS;
    	body.setTransform(this.X, this.Y, 0);	
    }
    
    public float getXColl() {
		return body.getTransform().getPosition().x;
	}


	public void setXColl(float x) {
		X = x;
	}


	public float getYColl() {
		return body.getTransform().getPosition().y;
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
