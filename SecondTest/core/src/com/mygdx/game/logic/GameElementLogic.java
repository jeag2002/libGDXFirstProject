package com.mygdx.game.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.enemies.centroid.WatchTowerEnemy;
import com.mygdx.game.elements.enemies.drons.SimpleEnemy;
import com.mygdx.game.elements.enemies.special.tanks.TankEnemy;
import com.mygdx.game.elements.explosions.SimpleExplosion;
import com.mygdx.game.elements.items.Item;
import com.mygdx.game.elements.players.simpleplayer.Player;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.ia.MapGraph;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.logic.map.SimpleMapGeneration;
import com.mygdx.game.logic.map.elements.StaticTiledMapColl;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.NewItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import box2dLight.RayHandler;

public class GameElementLogic {
	
	private World world;
	private Player player;
	private GamePlayScreen gPS;
	private SpawnPool spawnPool;
	
	private Random rand;
	
	private RayHandler rayHandler;
	
    private Sound sfxExplosion;
    private float sfxExplosionVolume; 
	
	private ArrayList<SpawnObject> enemiesDron = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> enemiesTank = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> enemiesTurrets = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesEnemies = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> missilesPlayer = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> items = new ArrayList<SpawnObject>();
	private ArrayList<SpawnObject> explosions = new ArrayList<SpawnObject>();
	
	
	public GameElementLogic(GamePlayScreen gPS) {
		
		this.gPS = gPS;
		this.world = new World(new Vector2(0,0),true);
		this.rayHandler = new RayHandler(world);
		
		this.rand = new Random();
		
		rayHandler.setCulling(true);
        rayHandler.useDiffuseLight(true);
        rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f,1.0f);
        
		this.rayHandler.setShadows(true);
		setExplosionSound("sounds/explosion.ogg", 0.97f);
		
	}
	
	public void initWorld () {
		spawnPool = new SpawnPool(world, gPS);
		
		spawnPool.addPool(SpawnType.Enemy_01, enemiesDron);
		spawnPool.addPool(SpawnType.Enemy_02, enemiesTank);
		spawnPool.addPool(SpawnType.Enemy_03, enemiesTurrets);
		
		spawnPool.addPool(SpawnType.MissileEnemy, missilesEnemies);
        spawnPool.addPool(SpawnType.MissilePlayer, missilesPlayer);
        
        spawnPool.addPool(SpawnType.Explosion, explosions);
        spawnPool.addPool(SpawnType.Item, items);
	}
	
	public void initPlayer(SpawnType playerType, float iniPositionX, float iniPositionY, float width, float height) {
		player = new Player(this.spawnPool,playerType,ElementEnum.GUN_PLAYER_1_A,this.world,this.gPS);
		player.setLocationAndSize(rayHandler, iniPositionX, iniPositionY, width, height);
	}
	
	public void generateEnemyDRON(NewItem itemEnemy) {
		SimpleEnemy sE = (SimpleEnemy)spawnPool.getFromPool(SpawnType.Enemy_01);
		sE.init(rayHandler, itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight(), 0, 0, false);
		sE.setSpawned(true);
	}
	
	public void generateEnemyWATCHTOWER(NewItem itemEnemy) {
		WatchTowerEnemy sE = (WatchTowerEnemy)spawnPool.getFromPool(SpawnType.Enemy_03);
		sE.init(rayHandler, itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight(), 0, 0, false);
		sE.setSpawned(true);
	}
	
	
	public void generateEnemyTANK(MapGraph map, NewItem itemEnemy, NewItem Objective) {
		TankEnemy tank = (TankEnemy)spawnPool.getFromPool(SpawnType.Enemy_02);
		tank.init(map, itemEnemy.getSubType(), itemEnemy, Objective, rayHandler, itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight(), 0, 0, false);
		tank.setSpawned(true);
	}
	
	public void generateItem(SpawnType subItem, NewItem itemEnemy) {
		Item item = (Item)spawnPool.getFromPool(SpawnType.Item);
		item.init(rayHandler, subItem ,itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight());
		item.setSpawned(true);
	}
	
	
	public void setExplosionSound(String path, float volume) {
	     sfxExplosion = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxExplosionVolume = volume;
	}
	
	
	public void generateBonus(float x, float y, float width, float height) {
		
		Item item = (Item)spawnPool.getFromPool(SpawnType.Item);
		
		SpawnType subType = SpawnType.Item_Bonus_Life;
		int i = rand.nextInt(4);
		
		if (i == 0) {subType = SpawnType.Item_Bonus_Life;}
		else if (i == 1) {subType = SpawnType.Item_Bonus_Shield;}
		else if (i == 2) {subType = SpawnType.Item_Bonus_Gun;}
		else {subType = SpawnType.Item_Bonus_Bullet;}
		
		item.init(rayHandler, subType , x+width/2-16, y+height/2-16, 32, 32);
		item.setSpawned(true);
	}
	
	
	public void generateExplosion(NewItem itemEnemy) {
		SimpleExplosion explosion = (SimpleExplosion)spawnPool.getFromPool(SpawnType.Explosion);
		explosion.init(rayHandler, itemEnemy.getSubType(), itemEnemy.getX(), itemEnemy.getY(), itemEnemy.getWidth(), itemEnemy.getHeight());
		explosion.setSpawned(true);
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
	
	public ArrayList<SpawnObject> getTanks(){
		return this.enemiesTank;
	}

	
	public void restart() {
		
		enemiesDron.clear();
		enemiesTank.clear();
		enemiesTurrets.clear();
		
		missilesEnemies.clear();
		missilesPlayer.clear();
		
		explosions.clear();
		items.clear();
		
		spawnPool.clear();
	}
	
	public void drawSpawns(SpriteBatch sb) {
		
		for (SpawnObject ex: items) {
	        if (ex.isSpawned()) {ex.draw(sb);}
	    }   
		for (SpawnObject e: enemiesTank) {
	        if (e.isSpawned()){e.draw(sb);}
	    }
	    for (SpawnObject e: enemiesDron) {
	        if (e.isSpawned()){e.draw(sb);}
	    }
	    for(SpawnObject e:enemiesTurrets) {
	    	if(e.isSpawned()) {e.draw(sb);}
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
    	for (SpawnObject e: enemiesDron) {
            if (e.isSpawned()) {e.update(delta, GameLogicInformation.speedUpFactor);}
        }
    	for (SpawnObject e: enemiesTank) {
            if (e.isSpawned()) {e.update(delta, GameLogicInformation.speedUpFactor);}
        }
    	for(SpawnObject e: enemiesTurrets) {
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
        for (SpawnObject ex: items) {
	        if (ex.isSpawned()) {ex.update(delta, GameLogicInformation.speedUpFactor);}
	    }
    }
	
	
	public void createSpawn() {
		
		for(NewItem item: spawnPool.getCreatedBodiesWithCollision()) {
			
			if (item.getType().equals(SpawnType.Explosion)) {
				this.generateExplosion(item);
				//sfxExplosion.play();
			}else if (item.getType().equals(SpawnType.Item) && item.getSubType().equals(SpawnType.Item_Bonus)) {
				this.generateBonus(item.getX(), item.getY(), item.getWidth(), item.getHeight());
			}
			
		}
		
		spawnPool.getCreatedBodiesWithCollision().clear();
		
	}
	
	
	
	
	
	public void removeSpawn() {
			
		    for (StaticTiledMapColl cell: spawnPool.getDeletedWallsWithCollision()) {
		    	
		    	Cell tileCell = ((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_ALTERNATIVE)).getCell(cell.getIndexX(), cell.getIndexY());
		    	
		    	if (tileCell != null) {
		    		StaticTiledMapColl LightMapCell = (StaticTiledMapColl)tileCell.getTile();
		    		world.destroyBody(LightMapCell.getBody());
		    	}
		    	
		        world.destroyBody(cell.getBody());
		        ((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_WALLS)).setCell(cell.getIndexX(),cell.getIndexY(), null);
		        ((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_ALTERNATIVE)).setCell(cell.getIndexX(),cell.getIndexY(), null);
			    ((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_LIGHTMAPSWALLS)).setCell(cell.getIndexX(),cell.getIndexY(), null);
			   
		    }
		    spawnPool.getDeletedWallsWithCollision().clear();
		    
		    
		    for (AnimatedTiledMapTile cell: spawnPool.getDeletedAnimForestWithCollision()) {
		    	StaticTiledMapTile data[] = cell.getFrameTiles();
		    	for(int i=0; i<data.length; i++) {
		    		StaticTiledMapColl tile = (StaticTiledMapColl)data[i];
		    		world.destroyBody(tile.getBody());
		    		((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_FOREST)).setCell(tile.getIndexX(),tile.getIndexY(), null);
		    		((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_LIGHTMAPSFOREST)).setCell(tile.getIndexX(),tile.getIndexY(), null);
		    	}
		    	
		    	
		    	
		    }
		    
		    spawnPool.getDeletedAnimForestWithCollision().clear();
		 
		    for (StaticTiledMapColl cell: spawnPool.getDeletedForestsWithCollision()) {
		    	world.destroyBody(cell.getBody());
		    	((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_FOREST)).setCell(cell.getIndexX(),cell.getIndexY(), null);
		    	((TiledMapTileLayer)this.gPS.getGamePlay().getTiledMap().getLayers().get(SimpleMapGeneration.INDEX_LIGHTMAPSFOREST)).setCell(cell.getIndexX(),cell.getIndexY(), null);
		    }
		    
		    spawnPool.getDeletedForestsWithCollision().clear();
		    
			for(SpawnObject sO: spawnPool.getDeletedBodiesWithCollision()) {
				sO.kill(spawnPool);
				spawnPool.returnToPool(sO);
				if (sO.getBox2DBody() != null) {
					world.destroyBody(sO.getBox2DBody());
				}
			}
			spawnPool.getDeletedBodiesWithCollision().clear();
			
			for (SpawnObject sO: spawnPool.getDeletedBodiesWithOutCollision()) {
				spawnPool.returnToPool(sO);
			}
			spawnPool.getDeletedBodiesWithOutCollision().clear();
		
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
		spawnPool.clear();
	}

}
