package com.mygdx.game.elements.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.utils.NewItem;

public class CollisionPlayerObject{
	
    private float X;
    private float Y;
	private float W;
    private float H;
    private int index_X;
    private int index_Y;
    
    private World world;
    private Body body;
    private PolygonShape shape;
    private FixtureDef fixtureDef;
    
    private String idCode;
    private SpawnType spawnType;
    private SpawnType spawnSubType;
    
    
    public CollisionPlayerObject(World world, SpawnType type, String idCode) {
    	
    	super();
    	
    	this.X = 0;
    	this.Y = 0;
    	this.W = 0;
    	this.H = 0;
        this.index_X = 0;
        this.index_Y = 0;
        
    	this.idCode = idCode;
    	this.world = world;
    	this.spawnType = type;
    	this.spawnSubType = SpawnType.Item;
    	  	
    }
    
    
    public CollisionPlayerObject(World world, SpawnType type, SpawnType subType, String idCode) {
    	
    	super();
    	
    	this.X = 0;
    	this.Y = 0;
    	this.W = 0;
    	this.H = 0;
    	this.index_X = 0;
        this.index_Y = 0;
        
    	this.idCode = idCode;
    	this.world = world;
    	this.spawnType = type;
    	this.spawnSubType = subType;
    	  	
    }
    
    
    
    public void setSpawnSubTypeColl(SpawnType subType) {
    	this.spawnSubType = subType;
    }
    
    
    
    public void createCollisionObject(float X, float Y, float W, float H, BodyType type, boolean isSensor) {
    	
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
    	fixtureDef.isSensor = isSensor;
    	
    	
    	body.createFixture(fixtureDef);
    	
        this.index_X = (int)X/SecondTestGDX.tileWidth_TL;
        this.index_Y = (int)Y/SecondTestGDX.tileHeight_TL;
    	
    	
    	body.setUserData(new NewItem(spawnType, spawnSubType, idCode, X, Y, W, H, index_X, index_Y));
    	shape.dispose();
    
    }
    
    
    public void createCollisionObject(float X, float Y, float W, float H, BodyType type) {
    	createCollisionObject(X, Y, W, H, type, false);
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
    
    public Vector2 getLateralVelocity() {
        Vector2 currentRightNormal = body.getWorldVector(new Vector2(-1,-1));
        return currentRightNormal.mulAdd(currentRightNormal,(-1)*currentRightNormal.dot(body.getLinearVelocity()));
    }
    
    //http://www.iforce2d.net/b2dtut/top-down-car/
    public void skiddingInIce() {
    	
    	float maxLateralImpulse = 0.5f;
    	Vector2 impulse = getLateralVelocity().scl(body.getMass());
    	if ( impulse.len2() > maxLateralImpulse ) {
    	     impulse = impulse.scl(maxLateralImpulse / impulse.len2());
    	}
    	body.applyLinearImpulse(impulse, body.getWorldCenter(),true); 	
    	body.applyAngularImpulse(body.getInertia()*0.1f*body.getAngularVelocity()*(-1), true);
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
