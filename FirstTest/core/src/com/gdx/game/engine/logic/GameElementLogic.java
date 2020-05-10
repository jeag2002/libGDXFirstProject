package com.gdx.game.engine.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.enemies.simplenemy.SimpleEnemy;
import com.gdx.game.elements.enemies.turrets.Cannon;
import com.gdx.game.elements.enemies.turrets.Mine;
import com.gdx.game.elements.enemies.turrets.Turret;
import com.gdx.game.elements.explosions.SimpleExplosion;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.items.Bonus;
import com.gdx.game.elements.items.Meteor;
import com.gdx.game.elements.player.Player;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.BonusTypeEnum;
import com.gdx.game.stages.enums.DynamicEnemyTypeEnum;
import com.gdx.game.stages.enums.ExplosionsEnum;
import com.gdx.game.stages.enums.LaserTypePlayer;
import com.gdx.game.stages.enums.ShootEnemyType;
import com.gdx.game.stages.enums.SpawnType;
import com.gdx.game.stages.enums.StaticEnemyTypeEnum;
import com.gdx.game.utils.NewItem;
import com.gdx.game.utils.NewStaticItem;

/**
 * Element Logics
 * @author jeag2
 *
 */

public class GameElementLogic {
	
	private SpawnPool spawnPool;
	
	private static final int lowTimerLimit = 1;
	private static final int highTimerLimit = 5;
	
	
	private static final int GENERATE_BONUS = 1;	
	private static final int ENEMY_2_LEFT = 0;
	private static final int ENEMY_2_RIGHT = 1;
	
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
	private ArrayList<SpawnObject> staticEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> obstacles = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> mines = new ArrayList<SpawnObject>();
    private ArrayList<SpawnObject> cannons = new ArrayList<SpawnObject>();
    
    
    private ArrayList<NewStaticItem> rectArray = new ArrayList<NewStaticItem>();
    
    
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
    
    
    public void processStaticTiledObject() {
    	
    	if (tiledMap.getLayers().get(GameLevelInformation.TURRET)!=null) {
	    	for(MapObject object : tiledMap.getLayers().get(GameLevelInformation.TURRET).getObjects().getByType(RectangleMapObject.class)) {
	    		Rectangle rect = ((RectangleMapObject)object).getRectangle(); 
	    		rectArray.add(new NewStaticItem(rect, GameLevelInformation.TURRET));
	    	}
    	}
    	
    	if (tiledMap.getLayers().get(GameLevelInformation.TURRET_BOSS)!=null) {
	    	for(MapObject object : tiledMap.getLayers().get(GameLevelInformation.TURRET_BOSS).getObjects().getByType(RectangleMapObject.class)) {
	    		Rectangle rect = ((RectangleMapObject)object).getRectangle(); 
	    		rectArray.add(new NewStaticItem(rect, GameLevelInformation.TURRET_BOSS));
	    	}
    	}
    	
    	if (tiledMap.getLayers().get(GameLevelInformation.MINE)!=null) {
    		for(MapObject object : tiledMap.getLayers().get(GameLevelInformation.MINE).getObjects().getByType(RectangleMapObject.class)) {
	    		Rectangle rect = ((RectangleMapObject)object).getRectangle(); 
	    		rectArray.add(new NewStaticItem(rect, GameLevelInformation.MINE));
	    	}
    	}
    	
    	if (tiledMap.getLayers().get(GameLevelInformation.CANNON)!=null) {
    		for(MapObject object : tiledMap.getLayers().get(GameLevelInformation.CANNON).getObjects().getByType(RectangleMapObject.class)) {
	    		Rectangle rect = ((RectangleMapObject)object).getRectangle(); 
	    		rectArray.add(new NewStaticItem(rect, GameLevelInformation.CANNON));
	    	}
    	}
    	
    }
    
    
    public void generateStaticTiledObject(float cameraX, float cameraY) {
    	
    	Iterator<NewStaticItem> iterator = rectArray.iterator();

    	while (iterator.hasNext()) {
    		NewStaticItem item = iterator.next();
    	    if ((item.getRect().y) <= cameraY) {
    	       if (item.getMapElement().equalsIgnoreCase(GameLevelInformation.TURRET)) {	
    	    	   StaticEnemyTypeEnum turret = StaticEnemyTypeEnum.TURRET_LEVEL_1;
    	    	   if (GameLevelInformation.getLevel() > GameLevelInformation.FIRST_LEVEL) {
	    	    	   turret = StaticEnemyTypeEnum.TURRET_LEVEL_2;
	    	       }
	    	       generateTurret(turret, item.getRect().x, FirstTestGDX.screenHeight);
	    	       
    	       }else if (item.getMapElement().equalsIgnoreCase(GameLevelInformation.TURRET_BOSS)) {
    	    	   generateTurret(StaticEnemyTypeEnum.TURRET_BOSS, item.getRect().x, FirstTestGDX.screenHeight);
    	       }else if (item.getMapElement().equalsIgnoreCase(GameLevelInformation.MINE)) {
    	    	   generateMine(item.getRect().x, FirstTestGDX.screenHeight);
    	       }else if (item.getMapElement().equalsIgnoreCase(GameLevelInformation.CANNON)) {
    	    	   generateCannon(item.getRect().x, FirstTestGDX.screenHeight,true);
    	       }
        	   iterator.remove();
            }
    	}
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
    
    
    public void playCrash() {
    	sfxCrash.play(sfxCrashVolume);
    }
    
    public void playBonus() {
    	sfxBonus.play(sfxBonusVolume);
    }
    
    
    private void init(World world) {
    	spawnPool = new SpawnPool(world);
        spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        spawnPool.addPool(SpawnType.Enemy_Simple_1, enemies);
        spawnPool.addPool(SpawnType.Static_Enemy, staticEnemies);
        spawnPool.addPool(SpawnType.Explosion, explosions);
        spawnPool.addPool(SpawnType.Obstacle, obstacles);
        spawnPool.addPool(SpawnType.Item, items);
        spawnPool.addPool(SpawnType.Mine, mines);
        spawnPool.addPool(SpawnType.Cannon, cannons);
        
    }
    
    
    public World getWorld() {
    	return world;
    }
    
    public void restart() {
        missilesEnemies.clear();
        missilesPlayer.clear();
        enemies.clear();
        staticEnemies.clear();
        explosions.clear();
        obstacles.clear();
        items.clear();
        mines.clear();
        cannons.clear();
        
        toCreatedItemsWithCollision.clear();
        toDeletedBodiesWithCollision.clear();
        toDeletedBodiesWithoutCollision.clear();
        
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
    
    
    //DETECT COLLISION WITH END MAP
    public void processCollisionWorld(OrthographicCamera camera) {
       
       if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {
       
    	   TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
    	   float totalHeight = layer.getHeight() * layer.getTileHeight();
    	   boolean isEndEpisode = (camera.position.y - 64) > totalHeight;
    	   //player.setEndMap(isEndEpisode);
    	   gPS.getgLL().setEndLevel(isEndEpisode);
       }
    }
    
    
    //DETECT COLLISION WITH DYNAMIC ELEMENTS
    public void processCollision(float delta) {
    	world.step(delta, 1, 1);
    	world.setContactListener(new CollisionEngine(gPS,spawnPool,player,this));
    }
    
    
    public void setTypeShoot(Bonus bonus) {
    	
    	BonusTypeEnum typeBonus = bonus.getBonus();
    	
    	if (typeBonus.equals(BonusTypeEnum.BonusAmmo)) {
    		LaserTypePlayer lTP = player.getlTypePlayer();
    
    		if (lTP.getIndex() >= 99 && lTP.getIndex() < 103) {
    			lTP = lTP.getByIndex(lTP.getIndex()+1);
    		}
    	
    		player.setlTypePlayer(lTP);
    		gPS.getgLL().setShootTypePlayer(lTP);
    		
    	}else if (typeBonus.equals(BonusTypeEnum.BonusHealth)) {
    		
    		if (gPS.getgLL().getLifePlayer() < GameLevelLogic.MAX_LIFE) {
    			gPS.getgLL().addLife(1);
    		}
    		
    	}else if (typeBonus.equals(BonusTypeEnum.BonusShield)) {
    		
    		if (gPS.getgLL().getShieldPlayer() < GameLevelLogic.MAX_SHIELD) {
    			gPS.getgLL().addShield(1);
    		}
    	}
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
    	staticEnemies.clear();
        missilesEnemies.clear();
        missilesPlayer.clear();
        explosions.clear();
        obstacles.clear();
        items.clear();
        mines.clear();
        cannons.clear();
        
        toCreatedItemsWithCollision.clear();
        toDeletedBodiesWithCollision.clear();
        toDeletedBodiesWithoutCollision.clear();
    	
    }
    
    
    public void drawSpawns(SpriteBatch sb) {
        
        for (SpawnObject se: staticEnemies) {
        	if (se.isSpawned()) {
        		se.draw(sb);
        	}
        }
        
        for (SpawnObject o: mines) {
            if (o.isSpawned())
                o.draw(sb);
        }
        
        for (SpawnObject o: cannons) {
            if (o.isSpawned())
                o.draw(sb);
        }
        
        
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
    	
    	 for (SpawnObject se: staticEnemies) {
          	if (se.isSpawned()) {
          		se.update(delta, GameLevelLogic.speedUpFactor);
          	}
         }
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
         for (SpawnObject o: mines) {
             if (o.isSpawned())
                 o.update(delta, GameLevelLogic.speedUpFactor);
         }
         for (SpawnObject o: cannons) {
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
		    		
		    		if (GameLevelInformation.getLevel() == GameLevelInformation.FIRST_LEVEL) {
		    			levelOneEnemyGeneration();
		    		}else if (GameLevelInformation.getLevel() == GameLevelInformation.SECOND_LEVEL) {
		    			levelTwoEnemyGeneration();
		    		}else if (GameLevelInformation.getLevel() >= GameLevelInformation.THIRD_LEVEL) {
		    			levelThreeEnemyGeneration();
		    		}
		    		
		    		timer = 0;
		    		spawnEnemyLimit =  random.nextInt(highTimerLimit - lowTimerLimit) + lowTimerLimit;
		    	}
	    	}
    	}
    }
    
    
    
    public void levelThreeEnemyGeneration() {
    	
    	DynamicEnemyTypeEnum eTE = DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2;
    	int next = this.random_Element.nextInt(4)+8;
    	DynamicEnemyTypeEnum type = eTE.getByIndex(next);
    	
    	switch(type) {	
    	case GROUP_ENEMIES_1_LEVEL_3:
    		generateGroupEnemyOne(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1,200+random.nextInt(FirstTestGDX.screenWidth-200), FirstTestGDX.screenHeight - 50);
    		break;
    	
    	case GROUP_ENEMIES_2_LEVEL_3:
    		generateGroupEnemyTwo(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1,200+random.nextInt(FirstTestGDX.screenWidth-200), FirstTestGDX.screenHeight - 200);
    		break;
    		
    	case CANNON_LEVEL_3:
    		generateCannon(200+random.nextInt(FirstTestGDX.screenWidth-200), FirstTestGDX.screenHeight - 200, false);
    		break;
    		
    	case ENEMY_SIMPLE_1_LEVEL_3:
    		generateEnemy(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    	}
    	
    }
    
    
    public void levelTwoEnemyGeneration() {
    	
    	DynamicEnemyTypeEnum eTE = DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2;
    	int next = this.random_Element.nextInt(4)+4;
    	DynamicEnemyTypeEnum type = eTE.getByIndex(next);
    	
    	switch(type) {
    	case ENEMY_SIMPLE_1_LEVEL_2:
    		generateEnemy(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    		
    	case ENEMY_SIMPLE_2_LEVEL_2:
    		generateEnemyTwo(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2, FirstTestGDX.screenHeight - 50);
    		break;
    	
    	case METEORTYPEONE_LEVEL_2:
    		generateMeteor(DynamicEnemyTypeEnum.METEORTYPEONE_LEVEL_2,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    		
    	case METEORTYPETWO_LEVEL_2:
    		generateMeteor(DynamicEnemyTypeEnum.METEORTYPETWO_LEVEL_2,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    	}
    }
    
    
    
    public void levelOneEnemyGeneration() {
    	
    	//0-3
    	DynamicEnemyTypeEnum eTE = DynamicEnemyTypeEnum.ENEMY_SIMPLE_1;
    	int next = this.random_Element.nextInt(4);
    	DynamicEnemyTypeEnum type = eTE.getByIndex(next);
    	
    	switch(type) {
    	case ENEMY_SIMPLE_1:
    		generateEnemy(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    		
    	case ENEMY_SIMPLE_2:
    		generateEnemyTwo(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2, FirstTestGDX.screenHeight - 50);
    		break;
    	
    	case METEORTYPEONE:
    		generateMeteor(DynamicEnemyTypeEnum.METEORTYPEONE,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    		
    	case METEORTYPETWO:
    		generateMeteor(DynamicEnemyTypeEnum.METEORTYPETWO,20+random.nextInt(FirstTestGDX.screenWidth-100), FirstTestGDX.screenHeight - 50);
    		break;
    			
    	}
    }
    
    
    public void generateGroupEnemyTwo(DynamicEnemyTypeEnum type, float posX, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f, false);
    	se.setSpawned(true);
    	
    	se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY-se.getHeight(), 90.0f, -280.0f, false);
    	se.setSpawned(true);
    	
    	se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY-(se.getHeight()*2), 90.0f, -280.0f, false);
    	se.setSpawned(true);
    	
    	
    }
    
    
    public void generateGroupEnemyOne(DynamicEnemyTypeEnum type, float posX, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f, true);
    	se.setSpawned(true);
    	
    	se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX+se.getWidth(), posY, 90.0f, -280.0f, true);
    	se.setSpawned(true);
    }
    
    
    
    
    public void generateEnemyTwo(DynamicEnemyTypeEnum type, float posY) {
    	
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	
    	boolean gun = this.random_Bonus.nextBoolean(); 	
    	int latch = this.random_Bonus.nextInt(2);
   
    	if (latch == ENEMY_2_LEFT) {
    		se.init(type, 30, posY, 135.0f, -280.0f, gun);
    	}else if (latch == ENEMY_2_RIGHT) {
    		se.init(type, FirstTestGDX.screenWidth-30, posY, 45.0f, -280.0f, gun);
    	}
    		
    	se.setSpawned(true);
    }
    
   
	public void generateEnemy(DynamicEnemyTypeEnum type, float posX, float posY) {
    	
		boolean gun = this.random_Bonus.nextBoolean();
		
    	SimpleEnemy se = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_Simple_1);
    	se.init(type, posX, posY, 90.0f, -280.0f, gun);
    	se.setSpawned(true);
    }
	
	
	
	public void generateMeteor(DynamicEnemyTypeEnum type, float posX, float posY) {
		Meteor m = (Meteor)spawnPool.getFromPool(SpawnType.Obstacle);
		m.init(type, 0, posX, posY, 90.0f, -280.0f);
		m.setSpawned(true);
	}
	
	
	public void generateBonus(float posX, float posY) {
		
		Bonus b = (Bonus)spawnPool.getFromPool(SpawnType.Item);
		
		BonusTypeEnum type = BonusTypeEnum.BonusAmmo;
		
		if (GameLevelInformation.getLevel() >= GameLevelInformation.SECOND_LEVEL) {
			int typeBonus = this.random_Bonus.nextInt(3);
			
			if (typeBonus == 0) {
				type = BonusTypeEnum.BonusAmmo;
			}else if (typeBonus == 1) {
				type = BonusTypeEnum.BonusHealth;
			}else if (typeBonus == 2) {
				type = BonusTypeEnum.BonusShield;
			}
		}
		
		b.init(type, posX, posY, 90.0f, -280.0f);
		b.setSpawned(true);
	}
	
	
	public void generateTurret(StaticEnemyTypeEnum type, float posX, float posY) {
		
		ShootEnemyType sET = ShootEnemyType.SHOOT_SIMPLE;
		
		
		if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_2)) {
			int latch = this.random_Bonus.nextInt(2);
			sET = sET.getByIndex(latch);
		}
		
		Turret t = (Turret)spawnPool.getFromPool(SpawnType.Static_Enemy);
		t.init(type, sET, posX, posY, 90.0f, -280.0f,player);
		t.setSpawned(true);
	}
	
	
	public void generateMine(float posX, float posY) {
		
		Mine m = (Mine)spawnPool.getFromPool(SpawnType.Mine);
		m.init(posX, posY, 90);
		m.setSpawned(true);
	}
	
	public void generateCannon(float posX, float posY, boolean isStatic) {
		
		Cannon c = (Cannon)spawnPool.getFromPool(SpawnType.Cannon);
		c.init(posX, posY, 90, -280.0f, isStatic);
		c.setSpawned(true);
	}
	
	public void activateBonus(float posX, float posY) {
		int latch = this.random_Bonus.nextInt(2);
		if (latch == this.GENERATE_BONUS) {
			generateBonus(posX,posY);
		}
	}
	
	
	
}



