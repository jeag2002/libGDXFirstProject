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
import com.gdx.game.elements.items.Bonus;
import com.gdx.game.elements.items.Meteor;
import com.gdx.game.elements.player.Player;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.BonusTypeEnum;
import com.gdx.game.stages.enums.EnemyTypes;
import com.gdx.game.stages.enums.ExplosionsEnum;
import com.gdx.game.stages.enums.MeteorTypeEnum;
import com.gdx.game.stages.enums.SpawnType;
import com.gdx.game.utils.NewItem;

/**
 * Element Logics
 * @author jeag2
 *
 */

public class GameElementLogic {
	
	private SpawnPool spawnPool;
	
	private static final int lowTimerLimit = 1;
	private static final int highTimerLimit = 5;
	
	private static final int GENERATE_NEW_ENEMY = 0;
	private static final int GENERATE_NEW_METEOR_A = 1;
	private static final int GENERATE_NEW_METEOR_B = 2;
	
	private static final int GENERATE_BONUS = 1;
	
	private float timer;
	private float spawnEnemyLimit;
	private boolean enemyGenerationTrigger;
	
	private Random random;
	private Random random_Element;
	private Random random_Bonus;
	
	
	private World world;
	private TiledMap tiledMap;
	
	private Player player;
	
	private Sound sfxExplosion;
	private float sfxExplosionVolume; 
	
	private Sound sfxCrash;
	private float sfxCrashVolume;
	
	private Sound sfxBonus;
	private float sfxBonusVolume;
	
	
	private GamePlayScreen gPS;
	
	private ArrayList<SpawnObject> enemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
    
    public static final ArrayList<Body> toDeletedBodiesWithCollision = new ArrayList<Body>();
    public static final ArrayList<SpawnObject> toDeletedBodiesWithoutCollision = new ArrayList<SpawnObject>();
    public static final ArrayList<NewItem> toCreatedItemsWithCollision = new ArrayList<NewItem>(); 
    
    
	
    
    public GameElementLogic(GamePlayScreen gPS) {
    	
    	this.timer = 0.0f;
    	
    	this.random = new Random();
    	this.random_Element = new Random();
    	this.random_Bonus = new Random();
    	
    	this.spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
    	
    	
    	this.world = new World(new Vector2(0, 0),true);
    	
    	this.enemyGenerationTrigger = false;
    	this.gPS = gPS;
    	
    	init(world);
    	setExplosionSound("sounds/explosion.ogg",0.25f);
    	setCrashSound("sounds/crash.wav",0.25f);
    	setBonusSound("sounds/bonus.wav",0.25f);
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
    
    public void setBonusSound(String path, float volume) {
    	 sfxBonus = Gdx.audio.newSound(Gdx.files.internal(path));
    	 sfxBonusVolume = volume;
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
    
    
    
    public void explosionGeneration(ExplosionsEnum explosion, float x, float y) {
    	SimpleExplosion sE = (SimpleExplosion) spawnPool.getFromPool(SpawnType.Explosion);
    	sE.init(explosion, x, y);
        sE.setPool(spawnPool);
        sfxExplosion.play(sfxExplosionVolume);
    }
    
    
    //DETECT COLLISION WITH STATIC ELEMENTS
    public void processCollisionWorld(OrthographicCamera camera) {
       
       if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {
       
    	   TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
    	   float totalHeight = layer.getHeight() * layer.getTileHeight();
    	   boolean isEndEpisode = (camera.position.y - 64) > totalHeight;
       
    	   player.setEndMap(isEndEpisode);
    	   gPS.getgLL().setEndLevel(isEndEpisode);
       }
       
       
    }
    
    //DETECT COLLISION WITH DYNAMIC ELEMENTS
    public void processCollision(float delta) {
    	
    	world.step(delta, 1, 1);
    	
    	
    	world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            	
            	
            	if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {
            	       
            	
	            	String objectStrA = (String)contact.getFixtureA().getBody().getUserData();
	            	String objectStrB = (String)contact.getFixtureB().getBody().getUserData();
	            	
	            	
	            	SpawnObject objectA = spawnPool.getElementWithCollisionById(objectStrA);
	            	SpawnObject objectB = spawnPool.getElementWithCollisionById(objectStrB);
	            	
	            	String msg = "";
	            	
	            	if ((objectA != null) && (objectB != null)) {
		            	if (((objectA instanceof Missile) || (objectA instanceof Meteor) || (objectA instanceof SimpleEnemy)) &&
		            	((objectB instanceof Missile) || (objectB instanceof Meteor) || (objectB instanceof SimpleEnemy))) {
		            		
		            		boolean isMissilePlayerA = false;
		            		boolean isMissilePlayerB = false;
		            		boolean isMissileEnemyA = false;
		            		boolean isMissileEnemyB = false;
		            		boolean isEnemyA = false;
		            		boolean isEnemyB = false;
		            		boolean isMeteorA = false;
		            		boolean isMeteorB = false;
		            		
		            		
		            		SimpleEnemy enemyA = null;
		            		SimpleEnemy enemyB = null;
		            		
		            		Meteor meteorA = null;
		            		Meteor meteorB = null;
		            		
		            		
		            		if (objectA instanceof Missile) {
		            			Missile missile = (Missile)objectA;	
		            			isMissilePlayerA = missile.getType().equals(SpawnType.MissilePlayer);
		            			isMissileEnemyA = !isMissilePlayerA;
		            			
		            		}else if (objectA instanceof SimpleEnemy) {
		            			enemyA = (SimpleEnemy)objectA;
		            			isEnemyA = true;
		            			
		            		}else if (objectA instanceof Meteor) {
		            			meteorA = (Meteor)objectA;
		            			isMeteorA = true;
		            		}
		            		
		            		if (objectB instanceof Missile) {
		            			
		            			Missile missile = (Missile)objectB;	
		            			isMissilePlayerB = missile.getType().equals(SpawnType.MissilePlayer);
		            		    isMissileEnemyB = !isMissilePlayerB;
		            		    
		            		}else if (objectB instanceof SimpleEnemy) {
		            			
		            			enemyB = (SimpleEnemy)objectB;
		            			isEnemyB = true;
		            			
		            		}else if (objectB instanceof Meteor) {
		            			meteorB = (Meteor)objectB;
		            			isMeteorB = true;
		            		}
		            		
		            		
		            		if (isMissilePlayerA && isEnemyB) {
		            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
		            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
		            			
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyB.getX(),enemyB.getY());
		            			
		            		}else if (isMissilePlayerB && isEnemyA) {
		            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
		            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
		            			
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyA.getX(),enemyA.getY());
		            		}
		            		
		            		
		            		if (isMissilePlayerA && isMeteorB) {
		            			
		            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorB.getX(),meteorB.getY());
		            			
		            			toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteorB.getX(),meteorB.getY()));
		            			
		            			//activateBonus(meteorB.getX(), meteorB.getY());
		            			
		            		}else if (isMissilePlayerB && isMeteorA) {
		            			
		            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorA.getX(),meteorA.getY());
		            			
		            			toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteorA.getX(),meteorA.getY()));
		            			
		            			//activateBonus(meteorA.getX(), meteorA.getY());
		            			
		            		}
		            		
		            		
		            		
		            		if (isEnemyA && isMeteorB) {
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyA.getX(),enemyA.getY());
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorB.getX(),meteorB.getY());
		            			
		            			
		            			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
			        			}
			            		
			            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
			        			}
		            			
		            			
		            		}else if (isEnemyB && isMeteorA) {
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyB.getX(),enemyB.getY());
		            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorA.getX(),meteorA.getY());
		            			
		            			
		            			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
			        			}
			            		
			            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
			        			}
			            		 
		            		}
		            		
		            		if ((isMissilePlayerA && (isMissileEnemyB || isEnemyB || isMeteorB)) || 
		            		   (isMissilePlayerB && (isMissileEnemyA || isEnemyA || isMeteorA))) {
			            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
			        			}
			            		
			            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
			        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
			        			}
		            		}
		
		            	}
	            	}
	            	
	            	
	            	if (((player.getCode().equals(objectStrA )) && ((objectB instanceof Meteor) || (objectB instanceof Missile) || (objectB instanceof Bonus) || (objectB instanceof SimpleEnemy)))){
	            		
	            		
	            		boolean isMissileEnemy = false;
	            		boolean isEnemy = false;
	            		boolean isMeteor = false;
	            		boolean isBonus = false;
	            		
	            		Bonus bonus = null;
	            		
	            		
	            		if (objectB instanceof Missile) {
	            			Missile missile = (Missile)objectB;	
	            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
	            		
	            		}else if (objectB instanceof SimpleEnemy) {
	            			
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
	            			SimpleEnemy enemy = (SimpleEnemy)objectB;
	            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemy.getX(),enemy.getY());            			
	            			isEnemy = true;
	            		
	            		}else if (objectB instanceof Meteor) {
	            			
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			Meteor meteor = (Meteor)objectB;
	            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteor.getX(),meteor.getY());
	            			
	            			toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteor.getX(),meteor.getY()));
	            			
	            			isMeteor = true;
	            			
	            		}else if (objectB instanceof Bonus) {
	            			bonus = (Bonus)objectB;
	            			sfxBonus.play(sfxBonusVolume);
	            			isBonus = true;
	            		}
	            		
	            		 if (isBonus) {  
		            	    if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
			        			GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
			        		}
		            	    	
		            	 }
	            		
	            		
	            	
	            		if (isMissileEnemy || isEnemy || isMeteor) {
	            			gPS.getgLL().processCollision();
	            			sfxCrash.play(sfxCrashVolume);
	            			
	            			if (gPS.getgLL().isGameOver()) {
	            				player.setEndMap(true);
	            				explosionGeneration(ExplosionsEnum.ExplosionTypeTwo,player.getX(),player.getY());     
	            			}
	            			
		            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
		        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
		        			}
	            		}
	    			}
	            	
	            	if (((player.getCode().equals(objectStrB )) && ((objectA instanceof Meteor) || (objectA instanceof Missile) || (objectA instanceof Bonus) || (objectA instanceof SimpleEnemy)))){
	            		
	            		boolean isMissileEnemy = false;
	            		boolean isEnemy = false;
	            		boolean isMeteor = false;
	            		boolean isBonus = false;
	            		
	            		Bonus bonus = null;
	            		
	            	    if (objectA instanceof Missile) {
	            			
	            	    	Missile missile = (Missile)objectA;	
	            			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
	            			
	            		}else if (objectA instanceof SimpleEnemy) {
	            			
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
	            			SimpleEnemy enemy = (SimpleEnemy)objectA;
	            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemy.getX(),enemy.getY());
	            			isEnemy = true;
	            			
	            		}else if (objectA instanceof Meteor) {
	            			
	            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
	            			Meteor meteor = (Meteor)objectA;
	            			explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteor.getX(),meteor.getY());
	            			
	            			toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteor.getX(),meteor.getY()));
	            			
	            			isMeteor = true;
	            			
	            		}else if (objectA instanceof Bonus) {
	            			bonus = (Bonus)objectA;
	            			sfxBonus.play(sfxBonusVolume);
	            			isBonus = true;
	            		}
	            	    
	            	    
	            	    if (isBonus) {
	            	   
	            	    	if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
		        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
		        			}
	            	    	
	            	    }
	            	    
	            	   
	            		if (isMissileEnemy || isEnemy || isMeteor) {
	            			sfxCrash.play(sfxCrashVolume);
	            			gPS.getgLL().processCollision();
	            			
	            			if (gPS.getgLL().isGameOver()) {
	            				player.setEndMap(true);
	            				explosionGeneration(ExplosionsEnum.ExplosionTypeTwo,player.getX(),player.getY());     
	            			}
	            			
		            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
		        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
		        			}
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
    
    
    public void createNewBodies() {
    	
    	for (NewItem item: toCreatedItemsWithCollision)
    	{
    		if (item.getType().equals(SpawnType.Item)) {
    			activateBonus(item.getX(), item.getY());    		
    		}
    	}
    	
    	toCreatedItemsWithCollision.clear();
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
    	generateNewItem(delta);
    }
    
    
    public boolean isEnemyGenerationTrigger() {
		return enemyGenerationTrigger;
	}

	public void setEnemyGenerationTrigger(boolean enemyGenerationTrigger) {
		this.enemyGenerationTrigger = enemyGenerationTrigger;
	}
    
    
    public void generateNewItem(float delta) {
    	
    	if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {
	    	if (enemyGenerationTrigger) {
		    	timer += delta * GameLevelLogic.speedUpFactor;
		    	
		    	if (timer >= spawnEnemyLimit) {
		    		
		    		int next = this.random_Element.nextInt(3);
		    		
		    		if (next == this.GENERATE_NEW_ENEMY) {
		    			generateEnemy(EnemyTypes.ENEMY_SIMPLE_1,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
		    		}else if (next == this.GENERATE_NEW_METEOR_A) {
		    			generateMeteor(MeteorTypeEnum.METEORTYPEONE,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
		    		}else if (next == this.GENERATE_NEW_METEOR_B) {
		    			generateMeteor(MeteorTypeEnum.METEORTYPETWO,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
		    		}
		    		
		    		timer = 0;
		    		spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
		    	}
	    	}
    	}
    }
    
   
	public void generateEnemy(EnemyTypes type, float posX, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f);
    	se.setSpawned(true);
    }
	
	
	
	public void generateMeteor(MeteorTypeEnum type, float posX, float posY) {
		Meteor m = (Meteor)spawnPool.getFromPool(SpawnType.Obstacle);
		m.init(type, 0, posX, posY, 90.0f, -280.0f);
		m.setSpawned(true);
	}
	
	
	public void generateBonus(BonusTypeEnum type, float posX, float posY) {
		
		Bonus b = (Bonus)spawnPool.getFromPool(SpawnType.Item);
		b.init(type, posX, posY);
		b.setSpawned(true);
		
	}
	
	
	public void activateBonus(float posX, float posY) {
		int latch = this.random_Bonus.nextInt(2);
		if (latch == this.GENERATE_BONUS) {
			generateBonus(BonusTypeEnum.BonusAmmo,posX,posY);
		}
	}
	
	
	
}



