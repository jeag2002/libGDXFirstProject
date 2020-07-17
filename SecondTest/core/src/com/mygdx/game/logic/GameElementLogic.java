package com.mygdx.game.logic;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.elements.players.simpleplayer.Player;
import com.mygdx.game.elements.simpleenemy.drons.SimpleEnemy;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.logic.map.elements.StaticTiledMapColl;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.NewItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class GameElementLogic {
	
	private World world;
	private Player player;
	private GamePlayScreen gPS;
	private SpawnPool spawnPool;
	
	private RayHandler rayHandler;
	private PointLight myLight;
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
	
	
	public GameElementLogic(GamePlayScreen gPS) {
		
		this.gPS = gPS;
		this.world = new World(new Vector2(0,0),true);
		this.rayHandler = new RayHandler(world);
		
		rayHandler.setCulling(true);
        rayHandler.useDiffuseLight(true);
        rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f,1.0f);
        
        
        
        
		this.rayHandler.setShadows(true);
		
		this.myLight = new PointLight(rayHandler, 20, Color.WHITE, 3, 0, 0);
		this.myLight.setSoftnessLength(0f);
		
		
	}
	
	public void initWorld () {
		spawnPool = new SpawnPool(world, gPS);
		spawnPool.addPool(SpawnType.Enemy_01, enemies);
		spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Explosion, explosions);
        spawnPool.addPool(SpawnType.Item, items);
	}
	
	public void initPlayer(SpawnType playerType, float iniPositionX, float iniPositionY, float width, float height) {
		player = new Player(this.spawnPool,playerType,ElementEnum.GUN_PLAYER_1_A,this.world,this.gPS);
		player.setLocationAndSize(iniPositionX, iniPositionY, width, height);
		this.myLight.attachToBody(player.getBody());
	}
	
	public void generateEnemy(NewItem itemEnemy) {
		
		SimpleEnemy sE = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_01);
		sE.init(rayHandler, itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight(), 0, 0, false);
		sE.setSpawned(true);
		
	}
	

	public World getWorld() {
		return world;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public SpawnPool getSpawnPool() {
		return spawnPool;
	}
	
	public RayHandler getRayHandler() {
		return rayHandler;
	}
	
	public PointLight getPointLight() {
		return myLight;
	}
	
	public void restart() {
		
		enemies.clear();
		missilesEnemies.clear();
		missilesPlayer.clear();
		explosions.clear();
		
	}
	
	public void drawSpawns(SpriteBatch sb) {
	        
	    for (SpawnObject e: enemies) {
	        if (e.isSpawned()){e.draw(sb);}
	    }
	    for (SpawnObject m: missilesEnemies) {
	        if (m.isSpawned()) {m.draw(sb);}     
	    }	
	    for (SpawnObject mp: missilesPlayer) {
	        if (mp.isSpawned()) {mp.draw(sb);}
	    }
	    for (SpawnObject ex: explosions) {
	        if (ex.isSpawned()) {ex.draw(sb);}
	    }
	}
	
	public void drawPlayer(SpriteBatch sb) {
		if (player != null) {
			player.draw(sb);
		}
	}
	
	public void updateSpawns(float delta) {
    	for (SpawnObject e: enemies) {
            if (e.isSpawned()) {e.update(delta, GameLogicInformation.speedUpFactor);}
        } 
    	for (SpawnObject m: missilesEnemies) {
            if (m.isSpawned()) {m.update(delta, GameLogicInformation.speedUpFactor);}
        }
    	for (SpawnObject mp: missilesPlayer) {
             if (mp.isSpawned()) {mp.update(delta, GameLogicInformation.speedUpFactor);}
        }
        for (SpawnObject ex: explosions) {
             if (ex.isSpawned()) {ex.update(delta, GameLogicInformation.speedUpFactor);}
        }
    }
	
	public void updatePlayer(float delta) {
		if (player != null) {
			player.update(delta);
		}
	}
	
	
	public void renderRayHandler() {
		rayHandler.updateAndRender();
	}
	
	public void configureCollision(TiledMap map, ArrayList<StaticTiledMapColl> walls, ArrayList<StaticTiledMapColl> forest) {
		world.setContactListener(new CollisionEngine(gPS,map, walls, forest));
	}
	
	public void stepWorld(float delta) {
		world.step(delta, 1, 1);
	}
	
	
	public void dispose() {
		world.dispose();
		rayHandler.dispose();
		myLight.dispose();
	}

}
