package com.gdx.game.engine.logic;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.stages.enums.SpawnType;

/**
 * Element Logics
 * @author jeag2
 *
 */

public class GameElementLogic {
	
	private SpawnPool spawnPool;
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
	
    
    public GameElementLogic() {
    	init();
    }
    
    private void init() {
    	spawnPool = new SpawnPool();
        spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Enemy, enemies);
        spawnPool.addPool(SpawnType.Explosion, explosions);
        spawnPool.addPool(SpawnType.Obstacle, obstacles);
        spawnPool.addPool(SpawnType.Item, items);
    }
    
    public void restart() {
        missilesEnemies.clear();
        missilesPlayer.clear();
        enemies.clear();
        explosions.clear();
        obstacles.clear();
        items.clear();
    }
    
    public SpawnPool getSpawnPool() {
    	return spawnPool;
    }
    
    
    public void drawSpawns(SpriteBatch sb) {
        for (SpawnObject e: enemies) {
            if (e.isSpawned())
                e.draw(sb);
        }
        for (SpawnObject m: missilesEnemies) {
            if (m.isSpawned())
                m.draw(sb);
        }
        for (SpawnObject mp: missilesPlayer) {
            if (mp.isSpawned())
                mp.draw(sb);
        }
        for (SpawnObject ex: explosions) {
            if (ex.isSpawned())
                ex.draw(sb);
        }
        for (SpawnObject i: items) {
            if (i.isSpawned())
                i.draw(sb);
        }
        for (SpawnObject o: obstacles) {
            if (o.isSpawned())
                o.draw(sb);
        }
    }
    
    public void updateSpawns(float delta) {
    	 
    	for (SpawnObject e: enemies) {
             if (e.isSpawned())
                 e.update(delta, GameLevelLogic.speedUpFactor);
         }
         for (SpawnObject m: missilesEnemies) {
             if (m.isSpawned())
                 m.update(delta, GameLevelLogic.speedUpFactor);
         }
         for (SpawnObject mp: missilesPlayer) {
             if (mp.isSpawned())
                 mp.update(delta, 1.0f);
         }
         for (SpawnObject ex: explosions) {
             if (ex.isSpawned())
                 ex.update(delta, 1.0f);
         }
         for (SpawnObject i: items) {
             if (i.isSpawned())
                 i.update(delta, 1.0f);
         }
         for (SpawnObject o: obstacles) {
             if (o.isSpawned())
                 o.update(delta, GameLevelLogic.speedUpFactor);
         }
    }
    

}
