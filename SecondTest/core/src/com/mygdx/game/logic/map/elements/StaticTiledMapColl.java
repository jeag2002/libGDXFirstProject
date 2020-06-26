package com.mygdx.game.logic.map.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.enums.TileMapLevelEnum;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.utils.GUID;
import com.mygdx.game.utils.NewItem;

public class StaticTiledMapColl extends StaticTiledMapTile{
	
	private SpawnType type;
	private float X;
	private float Y;
	private float W;
	private float H;
	private String idCode;
	
	

	public StaticTiledMapColl(SpawnType type, TextureRegion copy, float X, float Y, float W, float H, World world) {
		super(copy);
		this.type = type;
		this.X = X;
		this.Y = Y;
		this.W = W;
		this.H = H;
		this.idCode = GUID.get();
		
		createCollElement(X,Y,W,H,world);
	}
	
	private void createCollElement(float X, float Y, float W, float H,World world) {
		
    	BodyDef bodyDef = new BodyDef();
    	bodyDef.type = BodyDef.BodyType.StaticBody;
    	
    	this.X = X;
    	this.Y = Y;
    	this.W = W;
    	this.H = H;
    	
    	float iniX = (X+W/2)/GameLogicInformation.PIXELS_TO_METERS;
    	float iniY = (Y+H/2)/GameLogicInformation.PIXELS_TO_METERS;
    	bodyDef.position.set(iniX,iniY);
    	
    	Body body = world.createBody(bodyDef);
    	PolygonShape shape = new PolygonShape();
    	
    	float iniW =  W/2/GameLogicInformation.PIXELS_TO_METERS;
    	float iniH =  H/2/GameLogicInformation.PIXELS_TO_METERS;
    	shape.setAsBox(iniW, iniH);
    	
    	FixtureDef fixtureDef = new FixtureDef();
    	fixtureDef.shape = shape;
    	
    	body.createFixture(fixtureDef);
    	body.setUserData(new NewItem(type,idCode));
    	
    	shape.dispose();
	}
	
	public String getCodeId() {
		return idCode;
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

	public float getW() {
		return W;
	}
	
	public void setW(float w) {
		W = w;
	}
	
	public float getH() {
		return H;
	}
	
	public void setH(float h) {
		H = h;
	}

	

}
