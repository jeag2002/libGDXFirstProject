package com.gdx.game.engine.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.gdx.game.elements.gun.Missile;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.player.Player;
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
	
	private boolean enemyGenerationTrigger;
	
	private Random random;
	
	private World world;
	
	private Player player;
	
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
    
    public static final ArrayList<Body> toDeletedBodies = new ArrayList<Body>();
	
    
    public GameElementLogic() {
    	
    	timer = 0.0f;
    	
    	random = new Random();
    	spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
    	
    	//world = new World(new Vector2(0, -10.0f),true);
    	world = new World(new Vector2(0, 0),true);
    	
    	enemyGenerationTrigger = false;
    	
    	init(world);
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
    
    
    public void processCollision(float delta) {
    	
    	world.step(delta, 1, 1);
    	
    	
    	world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            	
            	String objectStrA = (String)contact.getFixtureA().getBody().getUserData();
            	String objectStrB = (String)contact.getFixtureB().getBody().getUserData();
            	
            	
            	SpawnObject objectA = spawnPool.getElementById(objectStrA);
            	SpawnObject objectB = spawnPool.getElementById(objectStrB);
            	
            	String msg = "";
            	
            	if ((objectA != null) && (objectB != null)) {
	            	if (((objectA instanceof Missile) || (objectA instanceof SimpleEnemy)) &&
	            	((objectB instanceof Missile) || (objectB instanceof SimpleEnemy))) {
	            		
	            		boolean isMissilePlayer = false;
	            		if (objectA instanceof Missile) {
	            			Missile missile = (Missile)objectA;	
	            			//msg += " Missile A <" + missile.toString() + ">";
	            			isMissilePlayer = missile.getType().equals(SpawnType.MissilePlayer);
	            		}else if (objectA instanceof SimpleEnemy) {
	            			SimpleEnemy enemy = (SimpleEnemy)objectA;
	            			//msg += " Enemy A <" + enemy.toString()  + ">";
	            		}
	            		
	            		if (objectB instanceof Missile) {
	            			Missile missile = (Missile)objectB;	
	            			//msg += " Missile B <" + missile.toString()  + ">";
	            			if (missile.getType().equals(SpawnType.MissilePlayer)){
	            				isMissilePlayer = !isMissilePlayer;
	            			}
	            		}else if (objectB instanceof SimpleEnemy) {
	            			SimpleEnemy enemy = (SimpleEnemy)objectB;
	            			//msg += " Enemy B <" + enemy.toString()  + ">";
	            		}
	            		
	            		//Gdx.app.log("[COLLISION]",msg);
	            		if (isMissilePlayer) {
		            		if (!GameElementLogic.toDeletedBodies.contains(contact.getFixtureA().getBody())) {
		        				GameElementLogic.toDeletedBodies.add(contact.getFixtureA().getBody());
		        			}
		            		
		            		if (!GameElementLogic.toDeletedBodies.contains(contact.getFixtureB().getBody())) {
		        				GameElementLogic.toDeletedBodies.add(contact.getFixtureB().getBody());
		        			}
	            		}
	
	            	}
            	}
            	
            	
            	if (((player.getCode().equals(objectStrA )) && ((objectB instanceof Missile) || (objectB instanceof SimpleEnemy)))){
            		
            		
            		boolean isMissileEnemy = false;
            		boolean isEnemy = false;
            		//msg = " Player A <"+ objectStrA +">";
            		if (objectB instanceof Missile) {
            			Missile missile = (Missile)objectB;	
            			//msg += " Missile B <" + missile.toString()  + ">";
            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
            		}else if (objectB instanceof SimpleEnemy) {
            			SimpleEnemy enemy = (SimpleEnemy)objectB;
            			//msg += " Enemy B <" + enemy.toString()  + ">";
            			isEnemy = true;
            		}
            		
            		//Gdx.app.log("[COLLISION]",msg);
            		if (isMissileEnemy || isEnemy) {
	            		if (!GameElementLogic.toDeletedBodies.contains(contact.getFixtureB().getBody())) {
	        				GameElementLogic.toDeletedBodies.add(contact.getFixtureB().getBody());
	        			}
            		}
            		
    		
    			}
            	
            	if (((player.getCode().equals(objectStrB )) && ((objectA instanceof Missile) || (objectA instanceof SimpleEnemy)))){
            		
            		boolean isMissileEnemy = false;
            		boolean isEnemy = false;
            		
            	    if (objectA instanceof Missile) {
            			Missile missile = (Missile)objectB;	
            			//msg += " Missile A <" + missile.toString()  + ">";
            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
            			
            		}else if (objectA instanceof SimpleEnemy) {
            			SimpleEnemy enemy = (SimpleEnemy)objectB;
            			//msg += " Enemy A <" + enemy.toString()  + ">";
            			isEnemy = true;
            		}
            		
            	    //msg = " Player B <"+ objectStrB +">";
            	    
            		//Gdx.app.log("[COLLISION]",msg);
            		
            		
            		if (isMissileEnemy || isEnemy) {
	            		if (!GameElementLogic.toDeletedBodies.contains(contact.getFixtureA().getBody())) {
	        				GameElementLogic.toDeletedBodies.add(contact.getFixtureA().getBody());
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
    	
    	
    	
    	for(Body body: toDeletedBodies) 
    	{
    		
    		String objStr = (String)body.getUserData();
    		SpawnObject obj = spawnPool.getElementById(objStr);
    		spawnPool.returnToPool(obj);
    		
    		/*
    		if (obj instanceof Missile) {
    			
    			Missile miss = (Missile)obj;
    			Gdx.app.log("[COLLISION DESTROY]","delete " +  miss.toString());
    			
    		}else if (obj instanceof SimpleEnemy) {
    			
    			SimpleEnemy sE = (SimpleEnemy)obj;
    			Gdx.app.log("[COLLISION DESTROY]", "delete "  + sE.toString());
    			
    		}
    		*/
    		
    		world.destroyBody(body);
    	}
    	toDeletedBodies.clear();
    	
    }
    
    
    
    public SpawnPool getSpawnPool() {
    	return spawnPool;
    }
    
    
    public void dispose() {
    	world.dispose();
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



