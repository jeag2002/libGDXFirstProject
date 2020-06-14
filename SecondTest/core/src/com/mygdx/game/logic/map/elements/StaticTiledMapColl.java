package com.mygdx.game.logic.map.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.logic.GameLogicInformation;

public class StaticTiledMapColl extends StaticTiledMapTile{

	public StaticTiledMapColl(TextureRegion copy, float X, float Y, float W, float H, World world) {
		super(copy);
		createCollElement(X,Y,W,H,world);
	}
	
	
	private void createCollElement(float X, float Y, float W, float H,World world) {
		
		BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
       
    	float iniW =  W/2/GameLogicInformation.PIXELS_TO_METERS;
    	float iniH =  H/2/GameLogicInformation.PIXELS_TO_METERS;
        
        bodyDef2.position.set(0,0);
        FixtureDef fixtureDef2 = new FixtureDef();

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(-iniW/2,-iniH/2,iniW/2,-iniH/2);
        fixtureDef2.shape = edgeShape;

        Body bodyEdgeScreen = world.createBody(bodyDef2);
        bodyEdgeScreen.createFixture(fixtureDef2);
        edgeShape.dispose();
	}

}
