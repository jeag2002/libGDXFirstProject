package com.mygdx.game.logic;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.elements.player.Player;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class GameElementLogic {
	
	private World world;
	private Player player;
	private GamePlayScreen gPS;
	private SpawnPool spawnPool;
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
	
	
	public GameElementLogic(GamePlayScreen gPS) {
		
		this.gPS = gPS;
		this.world = new World(new Vector2(0,0),true);
	}
	
	public void initWorld () {
		spawnPool = new SpawnPool(world);
		spawnPool.addPool(SpawnType.Enemy_01, enemies);
		spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Explosion, explosions);
	}
	
	public void initPlayer(SpawnType playerType, float iniPositionX, float iniPositionY, float width, float height) {
		player = new Player(this.spawnPool,playerType,ElementEnum.GUN_PLAYER_1_A,this.world,this.gPS);
		player.setLocationAndSize(iniPositionX, iniPositionY, width, height);
	}
	
	
	public World getWorld() {
		return world;
	}
	
	public Player getPlayer() {
		return player;
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
    
	
	

}