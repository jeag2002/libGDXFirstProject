package com.mygdx.game.logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.elements.player.Player;
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
	
	private ArrayList<StaticTiledMapColl> wallElements;
	private ArrayList<StaticTiledMapColl> forestElements;
	
	
	public CollisionEngine(GamePlayScreen gPS, TiledMap map, ArrayList<StaticTiledMapColl> walls, ArrayList<StaticTiledMapColl> forest) {
		this.gPS = gPS;
		this.map = map;
		this.wallElements = walls;
		this.forestElements = forest;
	}
	
	@Override
	public void beginContact(Contact contact) {
		
		NewItem objectStrA = (NewItem)contact.getFixtureA().getBody().getUserData();
		NewItem objectStrB = (NewItem)contact.getFixtureB().getBody().getUserData();
		
		System.out.println("ObjectStrA " +  objectStrA.getType() + " ObjectStrB " +  objectStrB.getType());
		
		//WALLS
		walls = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_WALLS);
		//FORESTS
		forests = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_FOREST);
		//DYNAMIC ELEMENTS
		SpawnPool pool = gPS.getGamePlay().getGameLogic().getSpawnPool();
		//PLAYER
		Player player = gPS.getGamePlay().getGameLogic().getPlayer();
		
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
	
	
	private void collisionDynamicToDynamic(Object DynamicA, Object DynamicB) {
	}
	
	private void collisionDynamicToPlayer(Object DynamicA, Player playerB) {
	}
	
	private void collisionPlayerToDynamic(Player playerA, Object DynamicB) {
	}
	
	private void collisionDynamicToStatic(Object DynamicA, Object StaticB) {
	}
	
	private void collisionStaticToDynamic(Object StaticA, Object DynamicB) {
	}
	
	private void collisionStaticToPlayer(Object StaticA, Player playerB) {
	}
	
	private void collisionPlayerToStatic(Player playerA, Object StaticB) {
	}

}
