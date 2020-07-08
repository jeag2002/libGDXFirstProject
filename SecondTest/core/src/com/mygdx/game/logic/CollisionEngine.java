package com.mygdx.game.logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.elements.players.simpleplayer.Player;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.logic.map.SimpleMapGeneration;
import com.mygdx.game.logic.map.elements.StaticTiledMapColl;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.NewItem;

public class CollisionEngine implements ContactListener{
	
	private GamePlayScreen gPS;
	private TiledMap map;
	
	private TiledMapTileLayer walls;
	private TiledMapTileLayer forests;
	
	private SpawnPool pool;
	private Player player;
	
	
	private ArrayList<StaticTiledMapColl> wallElements;
	private ArrayList<StaticTiledMapColl> forestElements;
	
	
	public CollisionEngine(GamePlayScreen gPS, TiledMap map, ArrayList<StaticTiledMapColl> walls, ArrayList<StaticTiledMapColl> forest) {
		this.gPS = gPS;
		this.map = map;
		this.wallElements = walls;
		this.forestElements = forest;
		
		this.walls = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_WALLS);
		this.forests = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_FOREST);
		this.pool = gPS.getGamePlay().getGameLogic().getSpawnPool();
		this.player = gPS.getGamePlay().getGameLogic().getPlayer();
	}
	
	//https://experto.dev/patron-de-diseno-observer-en-java/
	
	
	@Override
	public void beginContact(Contact contact) {
		
		NewItem objectStrA = (NewItem)contact.getFixtureA().getBody().getUserData();
		NewItem objectStrB = (NewItem)contact.getFixtureB().getBody().getUserData();
		//System.out.println("ObjectStrA " +  objectStrA.getType() + " ObjectStrB " +  objectStrB.getType());
		
		if ((objectStrA.getType().equals(SpawnType.Player_01) && (objectStrB.getType().equals(SpawnType.Wall) || objectStrB.getType().equals(SpawnType.Forest_Winter)))) {
			collisionPlayerToStatic(objectStrA, objectStrB); 
		}else if (((objectStrA.getType().equals(SpawnType.Wall) || objectStrA.getType().equals(SpawnType.Forest_Winter))) && objectStrB.getType().equals(SpawnType.Player_01)) {
			collisionStaticToPlayer(objectStrA, objectStrB);
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	
	private StaticTiledMapColl getElementById(ArrayList<StaticTiledMapColl> elements, String ID) {
		StaticTiledMapColl response = null;
		for(StaticTiledMapColl elem: elements){
			if (elem.getCodeId().equals(ID)) {
				response = elem;
				break;
			}
		}
		return response;
	}
	
	
	private void collisionDynamicToDynamic(NewItem DynamicA, NewItem DynamicB) {
	}
	
	private void collisionDynamicToPlayer(NewItem DynamicA, NewItem playerB) {
	}
	
	private void collisionPlayerToDynamic(NewItem playerA, NewItem DynamicB) {
	}
	
	private void collisionDynamicToStatic(NewItem DynamicA, NewItem StaticB) {
	}
	
	private void collisionStaticToDynamic(NewItem StaticA, NewItem DynamicB) {
	}
	
	private void collisionStaticToPlayer(NewItem StaticA, NewItem playerB) {
	}
	
	private void collisionPlayerToStatic(NewItem playerA, NewItem StaticB) {
	}

}
