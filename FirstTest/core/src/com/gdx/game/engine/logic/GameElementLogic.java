package com.gdx.game.engine.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.enemies.simplenemy.SimpleEnemy;
import com.gdx.game.elements.explosions.SimpleExplosion;
import com.gdx.game.elements.gun.Missile;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.player.Player;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.EnemyTypes;
import com.gdx.game.stages.enums.ExplosionsEnum;
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
	private boolean enemyGenerationTrigger;
	private Random random;
	
	private World world;
	private TiledMap tiledMap;
	
	private Player player;
	
	private Sound sfxExplosion;
	private float sfxExplosionVolume; 
	
	private Sound sfxCrash;
	private float sfxCrashVolume;
	
	
	private GamePlayScreen gPS;
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
    
    public static final ArrayList<Body> toDeletedBodiesWithCollision = new ArrayList<Body>();
    public static final ArrayList<SpawnObject> toDeletedBodiesWithoutCollision = new ArrayList<SpawnObject>();
    
	
    
    public GameElementLogic(GamePlayScreen gPS) {
    	
    	this.timer = 0.0f;
    	
    	this.random = new Random();
    	this.spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
    	this.world = new World(new Vector2(0, 0),true);
    	
    	this.enemyGenerationTrigger = false;
    	this.gPS = gPS;
    	
    	init(world);
    	setExplosionSound("sounds/explosion.ogg",0.25f);
    	setCrashSound("sounds/crash.wav",0.25f);
    }
    
    public void setTiledMap(TiledMap tiledMap) {
    	this.tiledMap = tiledMap;
    }
    
    
    public void setCrashSound(String path, float volume) {
	     sfxCrash = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxCrashVolume = volume;
	}
    
    
    public void setExplosionSound(String path, float volume) {
	     sfxExplosion = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxExplosionVolume = volume;
	}
    
    
    private void init(World world) {
    	spawnPool = new SpawnPool(world);
        spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Enemy_Simple_1, enemies);
        spawnPool.addPool(SpawnType.Explosion, explosions);
        spawnPool.addPool(SpawnType.Obstacle, obstacles);
        spawnPool.addPool(SpawnType.Item, items);
    }
    
    
    public World getWorld() {
    	return world;
    }
    
    public void restart() {
        missilesEnemies.clear();
        missilesPlayer.clear();
        enemies.clear();
        explosions.clear();
        obstacles.clear();
        items.clear();
    }
    
   
    public void setPlayer(Player player) {
    	this.player = player;
    }
    
    
    
    public void explosionGeneration(float x, float y) {
    	SimpleExplosion sE = (SimpleExplosion) spawnPool.getFromPool(SpawnType.Explosion);
    	sE.init(ExplosionsEnum.ExplosionTypeOne, x, y);
        sE.setPool(spawnPool);
        sfxExplosion.play(sfxExplosionVolume);
    }
    
    
    //DETECT COLLISION WITH STATIC ELEMENTS
    public void processCollisionWorld(OrthographicCamera camera) {
       
       TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
       float totalHeight = layer.getHeight() * layer.getTileHeight();
       boolean isEndEpisode = (camera.position.y - 64) > totalHeight;
       
       player.setEndMap(isEndEpisode);
       gPS.getgLL().setEndLevel(isEndEpisode);
       
    }
    
    
    
    
    //DETECT COLLISION WITH DYNAMIC ELEMENTS
    public void processCollision(float delta) {
    	
    	world.step(delta, 1, 1);
    	
    	
    	world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            	
            	String objectStrA = (String)contact.getFixtureA().getBody().getUserData();
            	String objectStrB = (String)contact.getFixtureB().getBody().getUserData();
            	
            	
            	SpawnObject objectA = spawnPool.getElementWithCollisionById(objectStrA);
            	SpawnObject objectB = spawnPool.getElementWithCollisionById(objectStrB);
            	
            	String msg = "";
            	
            	if ((objectA != null) && (objectB != null)) {
	            	if (((objectA instanceof Missile) || (objectA instanceof SimpleEnemy)) &&
	            	((objectB instanceof Missile) || (objectB instanceof SimpleEnemy))) {
	            		
	            		boolean isMissilePlayerA = false;
	            		boolean isMissilePlayerB = false;
	            		boolean isMissileEnemyA = false;
	            		boolean isMissileEnemyB = false;
	            		boolean isEnemyA = false;
	            		boolean isEnemyB = false;
	            		
	            		SimpleEnemy enemyA = null;
	            		SimpleEnemy enemyB = null;
	            		
	            		
	            		if (objectA instanceof Missile) {
	            			Missile missile = (Missile)objectA;	
	            			isMissilePlayerA = missile.getType().equals(SpawnType.MissilePlayer);
	            			isMissileEnemyA = !isMissilePlayerA;
	            			
	            		}else if (objectA instanceof SimpleEnemy) {
	            			enemyA = (SimpleEnemy)objectA;
	            			isEnemyA = true;
	            		}
	            		
	            		if (objectB instanceof Missile) {
	            			Missile missile = (Missile)objectB;	
	            			isMissilePlayerB = missile.getType().equals(SpawnType.MissilePlayer);
	            		    isMissileEnemyB = !isMissilePlayerB;
	            		}else if (objectB instanceof SimpleEnemy) {
	            			enemyB = (SimpleEnemy)objectB;
	            			isEnemyB = true;
	            		}
	            		
	            		
	            		if (isMissilePlayerA && isEnemyB) {
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			explosionGeneration(enemyB.getX(),enemyB.getY());
	            			
	            		}else if (isMissilePlayerB && isEnemyA) {
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			explosionGeneration(enemyA.getX(),enemyA.getY());
	            		}
	            		
	            		if ((isMissilePlayerA && (isMissileEnemyB || isEnemyB)) || 
	            		   (isMissilePlayerB && (isMissileEnemyA || isEnemyA))) {
		            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
		        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
		        			}
		            		
		            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
		        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
		        			}
	            		}
	
	            	}
            	}
            	
            	
            	if (((player.getCode().equals(objectStrA )) && ((objectB instanceof Missile) || (objectB instanceof SimpleEnemy)))){
            		
            		
            		boolean isMissileEnemy = false;
            		boolean isEnemy = false;
            		
            		if (objectB instanceof Missile) {
            			Missile missile = (Missile)objectB;	
            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
            		
            		}else if (objectB instanceof SimpleEnemy) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			SimpleEnemy enemy = (SimpleEnemy)objectB;
            			explosionGeneration(enemy.getX(),enemy.getY());            			
            			isEnemy = true;
            		}
            		
            		
            		//Gdx.app.log("[COLLISION]",msg);
            		if (isMissileEnemy || isEnemy) {
            			gPS.getgLL().processCollision();
            			sfxCrash.play(sfxCrashVolume);
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
	        			}
            		}
    			}
            	
            	if (((player.getCode().equals(objectStrB )) && ((objectA instanceof Missile) || (objectA instanceof SimpleEnemy)))){
            		
            		boolean isMissileEnemy = false;
            		boolean isEnemy = false;
            		
            	    if (objectA instanceof Missile) {
            			Missile missile = (Missile)objectB;	
            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
            			
            		}else if (objectA instanceof SimpleEnemy) {
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			SimpleEnemy enemy = (SimpleEnemy)objectB;
            			explosionGeneration(enemy.getX(),enemy.getY());
            			isEnemy = true;
            		}
            		
            	    
            		
            		if (isMissileEnemy || isEnemy) {
            			sfxCrash.play(sfxCrashVolume);
            			gPS.getgLL().processCollision();
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
	        			}
            		}
            		
    			}
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });
    }
    
    
    public void removeOldBodies() {
    	for(Body body: toDeletedBodiesWithCollision) 
    	{
    		String objStr = (String)body.getUserData();
    		SpawnObject obj = spawnPool.getElementWithCollisionById(objStr);
    		spawnPool.returnToPool(obj);  		
    		world.destroyBody(body);
    	}
    	toDeletedBodiesWithCollision.clear();
    	
    	for(SpawnObject object: toDeletedBodiesWithoutCollision) {
    		spawnPool.returnToPool(object);
    	}
    	
    	toDeletedBodiesWithoutCollision.clear();
    }
    
    
    
    
    public SpawnPool getSpawnPool() {
    	return spawnPool;
    }
    
    
    public void dispose() {
    	world.dispose();
    	
    	enemies.clear();
        missilesEnemies.clear();
        missilesPlayer.clear();
        explosions.clear();
        obstacles.clear();
        items.clear();
        
        toDeletedBodiesWithCollision.clear();
        toDeletedBodiesWithoutCollision.clear();
    	
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
    
    
    public boolean isEnemyGenerationTrigger() {
		return enemyGenerationTrigger;
	}

	public void setEnemyGenerationTrigger(boolean enemyGenerationTrigger) {
		this.enemyGenerationTrigger = enemyGenerationTrigger;
	}
    
    
    public void generateEnemies(float delta) {
    	
    	if (enemyGenerationTrigger) {
	    	timer += delta * GameLevelLogic.speedUpFactor;
	    	
	    	if (timer >= spawnEnemyLimit) {
	    		
	    		generateEnemy(EnemyTypes.ENEMY_SIMPLE_1,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
	    		timer = 0;
	    		spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
	    	}
    	}
    }
    
   
	public void generateEnemy(EnemyTypes type, float posX, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f);
    	se.setSpawned(true);
    	
    }
}



