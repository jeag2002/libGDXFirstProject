package com.gdx.game.engine.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.enemies.simplenemy.SimpleEnemy;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.stages.enums.EnemyTypes;
import com.gdx.game.stages.enums.SpawnType;

/**
 * Element Logics
 * @author jeag2
 *
 */

public class GameElementLogic {
	
	private SpawnPool spawnPool;
	
	private static final int lowTimerLimit = 1;
	private static final int highTimerLimit = 5;
	
	private float timer;
	private float spawnEnemyLimit;
	
	private Random random;
	
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
	
    
    public GameElementLogic() {
    	init();
    	timer = 0.0f;
    	
    	random = new Random();
    	spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
    	
    }
    
    private void init() {
    	spawnPool = new SpawnPool();
        spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Enemy_Simple_1, enemies);
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
    
    
    public void generateElements(float delta) {
    	generateEnemies(delta);
    }
    
    
    
    public void generateEnemies(float delta) {
    	
    	timer += delta * GameLevelLogic.speedUpFactor;
    	
    	if (timer >= spawnEnemyLimit) {
    		
    		generateEnemy(EnemyTypes.ENEMY_SIMPLE_1,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - random.nextInt(100));
    		timer = 0;
    		spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
    	}
    }
    
    public void generateEnemy(EnemyTypes type, float posX, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f);
    	se.setSpawned(true);
    	
    }
    
    

}
