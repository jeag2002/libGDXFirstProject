package com.mygdx.game.logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.elements.enemies.special.tanks.TankEnemy;
import com.mygdx.game.elements.enemies.special.tanks.TankEnemyStateEnum;
import com.mygdx.game.elements.items.Item;
import com.mygdx.game.elements.players.simpleplayer.Player;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.elements.SpawnObject;
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
	
	private SpawnPool pool;
	private Player player;
	
	
	private ArrayList<StaticTiledMapColl> wallElements;
	private ArrayList<StaticTiledMapColl> forestElements;
	
	
	public CollisionEngine(GamePlayScreen gPS, TiledMap map, ArrayList<StaticTiledMapColl> walls, ArrayList<StaticTiledMapColl> forest) {
		super();
		this.gPS = gPS;
		this.map = map;
		this.wallElements = walls;
		this.forestElements = forest;
		
		this.walls = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_WALLS);
		this.forests = (TiledMapTileLayer)map.getLayers().get(SimpleMapGeneration.INDEX_FOREST);
		this.pool = gPS.getGamePlay().getGameLogic().getSpawnPool();
		this.player = gPS.getGamePlay().getGameLogic().getPlayer();
	}
	
	
	
	
	@Override
	public void beginContact(Contact contact) {
		
		NewItem objectStrA = (NewItem)contact.getFixtureA().getBody().getUserData();
		NewItem objectStrB = (NewItem)contact.getFixtureB().getBody().getUserData();
		
		//System.out.println("Collision detected A (" + objectStrA.getIdCode() + ")" +  objectStrA.getType().toString() + " B (" + objectStrB.getIdCode() + ")" + objectStrB.getType().toString());
		
		//-->MISSILE ENEMY
	    if (objectStrA.getType().equals(SpawnType.MissileEnemy)) {
	    	processEnemyMissile(objectStrA, objectStrB);
	    }else if (objectStrB.getType().equals(SpawnType.MissileEnemy)) {
	    	processEnemyMissile(objectStrB, objectStrA);
	    }
	    
	    //-->MISSILE PLAYER
	    if (objectStrA.getType().equals(SpawnType.MissilePlayer)) {
	    	processPlayerMissile(objectStrA, objectStrB);
	    }else if (objectStrB.getType().equals(SpawnType.MissilePlayer)) {
	    	processPlayerMissile(objectStrB, objectStrA);
	    }
	    
	    //-->ENEMY2
	    if (objectStrA.getType().equals(SpawnType.Enemy_02)) {
	    	processEnemy2(objectStrA, objectStrB);
	    }else if (objectStrB.getType().equals(SpawnType.Enemy_02)) {
	    	processEnemy2(objectStrB, objectStrA);
	    }
	    
	}
	
	
	
	public void processEnemy2(NewItem objectStr, NewItem other) {
		
		
			boolean isIniEnemy = false;
		
			SpawnObject otherObject = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(other.getIdCode());
			
			if (otherObject != null) {
				if (other.getType().equals(SpawnType.Item)) {
					Item item = (Item)otherObject;
					if (item.getSubType().equals(SpawnType.Item_PlatformEnemy)) {
						isIniEnemy = true;
					}
				}
			}
			
			
			
			if 			
			(other.getType().equals(SpawnType.Enemy_01) ||
			other.getType().equals(SpawnType.Enemy_03) ||
			other.getType().equals(SpawnType.Enemy_02) ||
			(other.getType().equals(SpawnType.Item) && !isIniEnemy) ||
			other.getType().equals(SpawnType.Wall_City) ||	
			other.getType().equals(SpawnType.Wall_Badlands) || 
			other.getType().equals(SpawnType.Wall_Desert) ||
			other.getType().equals(SpawnType.Wall_Fabric) ||
			other.getType().equals(SpawnType.Wall_Jungle) ||
			other.getType().equals(SpawnType.Wall_Volcano) ||
			other.getType().equals(SpawnType.Wall_Winter) ||
			other.getType().equals(SpawnType.Forest_Volcano) ||
			other.getType().equals(SpawnType.Forest_Winter) ||
			other.getType().equals(SpawnType.Forest_Space) ||
			other.getType().equals(SpawnType.Border)) {
				
				SpawnObject object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(objectStr.getIdCode());
				
				if 
				(other.getType().equals(SpawnType.Enemy_01) ||
				 other.getType().equals(SpawnType.Enemy_03) ||
				 other.getType().equals(SpawnType.Enemy_02) ||
				 (other.getType().equals(SpawnType.Item) && !isIniEnemy)) {
					
					if (object != null) {
						
						object.getBox2DBody().setLinearVelocity(0, 0);
						object.getBox2DBody().setAngularVelocity(0.0f);	
						
						((TankEnemy)object).getStateMachine().changeState(TankEnemyStateEnum.MOVE);
						
						Telegram msg = new Telegram();
						msg.extraInfo = new NewItem(other);
						((TankEnemy)object).handleMessage(msg);
						
					}
				
				}else if (other.getType().equals(SpawnType.Border)) {	
					
					if (object != null) {
						object.getBox2DBody().setLinearVelocity(0, 0);
						object.getBox2DBody().setAngularVelocity(0.0f);		
						((TankEnemy)object).getStateMachine().changeState(TankEnemyStateEnum.STOP);
					}
					
				}else if (other.getType().equals(SpawnType.Wall_City) ||
						other.getType().equals(SpawnType.Wall_Badlands) || 
						other.getType().equals(SpawnType.Wall_Desert) ||
						other.getType().equals(SpawnType.Wall_Fabric) ||
						other.getType().equals(SpawnType.Wall_Jungle) ||
						other.getType().equals(SpawnType.Wall_Winter)
						){
					
						Cell cell = walls.getCell(other.getIndex_X(), other.getIndex_Y());
						if (cell != null) {
							StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
							if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().add(tile);
							}
						}
						
						((TankEnemy)object).getStateMachine().changeState(TankEnemyStateEnum.MOVE);		
						
						
				}else if (other.getType().equals(SpawnType.Forest_Volcano) ||
						other.getType().equals(SpawnType.Forest_Winter) ||
						other.getType().equals(SpawnType.Forest_Space)) {
						
						Cell cell = forests.getCell(other.getIndex_X(), other.getIndex_Y());
					
						if (cell != null) {
						
							if (other.getType().equals(SpawnType.Forest_Space)) {
								
								if (cell.getTile() instanceof AnimatedTiledMapTile) {
									AnimatedTiledMapTile tile = (AnimatedTiledMapTile)cell.getTile();						
									if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().contains(tile)) {
										gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().add(tile);
									}
								}else {
									StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
									if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
										gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
									}
								}
							
							}else {
								StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
								}
							}
						}
						
						((TankEnemy)object).getStateMachine().changeState(TankEnemyStateEnum.MOVE);	
				}
				
			}
		
	}
	
	
	
	
	public void processPlayerMissile(NewItem objectStr, NewItem other) {
		
		
			if (other.getType().equals(SpawnType.Enemy_01) ||
				other.getType().equals(SpawnType.Enemy_02) ||	
				other.getType().equals(SpawnType.Enemy_03) ||
				
				other.getType().equals(SpawnType.Item) ||
				
				other.getType().equals(SpawnType.MissileEnemy) ||
				other.getType().equals(SpawnType.Wall_City) ||	
				other.getType().equals(SpawnType.Wall_Badlands) || 
				other.getType().equals(SpawnType.Wall_Desert) ||
				other.getType().equals(SpawnType.Wall_Fabric) ||
				other.getType().equals(SpawnType.Wall_Jungle) ||
				other.getType().equals(SpawnType.Wall_Volcano) ||
				other.getType().equals(SpawnType.Wall_Winter) ||
				other.getType().equals(SpawnType.Forest_Volcano) ||
				other.getType().equals(SpawnType.Forest_Winter) ||
				other.getType().equals(SpawnType.Forest_Space) ||
				other.getType().equals(SpawnType.Border)) {
				
					SpawnObject object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(objectStr.getIdCode());
					if (object != null) {
						if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().contains(object)) {
							gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(object);
						}
					}
					
				
					if (other.getType().equals(SpawnType.Enemy_01) ||
						other.getType().equals(SpawnType.Enemy_02) ||
						other.getType().equals(SpawnType.Enemy_03) ||
						other.getType().equals(SpawnType.MissileEnemy)) {
						
						object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(other.getIdCode());
						if (object != null) {
							if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().contains(object)) {
								gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(object);
							}
						}
					}
					
					if (other.getType().equals(SpawnType.Item)) {
						
						object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(other.getIdCode());
						if (object != null) {
							Item item = (Item)object;
							if (item.getSubType().equals(SpawnType.Item_Mine)) {
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().contains(object)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(object);
								}
							}
						}
					} 
					
					
					if (other.getType().equals(SpawnType.Wall_City) ||
					other.getType().equals(SpawnType.Wall_Badlands) || 
					other.getType().equals(SpawnType.Wall_Desert) ||
					other.getType().equals(SpawnType.Wall_Fabric) ||
					other.getType().equals(SpawnType.Wall_Jungle) ||
					other.getType().equals(SpawnType.Wall_Winter)
					) {
						Cell cell = walls.getCell(other.getIndex_X(), other.getIndex_Y());
						if (cell != null) {
							StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
							if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().add(tile);
							}
						}
					}
				
					if (other.getType().equals(SpawnType.Forest_Volcano) ||
					other.getType().equals(SpawnType.Forest_Winter) ||
					other.getType().equals(SpawnType.Forest_Space)) {
						
					Cell cell = forests.getCell(other.getIndex_X(), other.getIndex_Y());
					
					if (cell != null) {
						
						if (other.getType().equals(SpawnType.Forest_Space)) {
							
							if (cell.getTile() instanceof AnimatedTiledMapTile) {
								AnimatedTiledMapTile tile = (AnimatedTiledMapTile)cell.getTile();						
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().add(tile);
								}
							}else {
								StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
								}
							}
						
						}else {
							StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
							if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
								gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
							}
						}
					}
				}
			}
	}
	
	
	
	public void processEnemyMissile(NewItem objectStr, NewItem other) {
	
			
			if (other.getType().equals(SpawnType.Player_01) ||
				other.getType().equals(SpawnType.MissilePlayer) ||
				other.getType().equals(SpawnType.Wall_City) ||	
				other.getType().equals(SpawnType.Wall_Badlands) || 
				other.getType().equals(SpawnType.Wall_Desert) ||
				other.getType().equals(SpawnType.Wall_Fabric) ||
				other.getType().equals(SpawnType.Wall_Jungle) ||
				other.getType().equals(SpawnType.Wall_Volcano) ||
				other.getType().equals(SpawnType.Wall_Winter) ||
				other.getType().equals(SpawnType.Forest_Volcano) ||
				other.getType().equals(SpawnType.Forest_Winter) ||
				other.getType().equals(SpawnType.Forest_Space) ||
				other.getType().equals(SpawnType.Border)) {
			
				SpawnObject object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(objectStr.getIdCode());
				if (object != null) {
					if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().contains(object)) {
						gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(object);
					}
				}
				
				if (other.getType().equals(SpawnType.MissilePlayer)) {
					object = gPS.getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(other.getIdCode());
					if (object != null) {
						if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().contains(object)) {
							gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(object);
						}
					}
				}
				
				
				if (other.getType().equals(SpawnType.Wall_City) ||
					other.getType().equals(SpawnType.Wall_Badlands) || 
					other.getType().equals(SpawnType.Wall_Desert) ||
					other.getType().equals(SpawnType.Wall_Fabric) ||
					other.getType().equals(SpawnType.Wall_Jungle) ||
					other.getType().equals(SpawnType.Wall_Winter)
					) {
					
					Cell cell = walls.getCell(other.getIndex_X(), other.getIndex_Y());
					if (cell != null) {
						StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
						if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().contains(tile)) {
							gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedWallsWithCollision().add(tile);
						}
					}
					
	 
				}
				
				
				if (other.getType().equals(SpawnType.Forest_Volcano) ||
				    other.getType().equals(SpawnType.Forest_Winter) ||
				    other.getType().equals(SpawnType.Forest_Space)) {	
					
					Cell cell = forests.getCell(other.getIndex_X(), other.getIndex_Y());
					
					if (cell != null) {
						
						if (other.getType().equals(SpawnType.Forest_Space)) {
							
							if (cell.getTile() instanceof AnimatedTiledMapTile) {
								AnimatedTiledMapTile tile = (AnimatedTiledMapTile)cell.getTile();						
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedAnimForestWithCollision().add(tile);
								}
							}else {
								StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
								if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
									gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
								}
							}
							
						}else {
							StaticTiledMapColl tile = (StaticTiledMapColl)cell.getTile();
							if (!gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().contains(tile)) {
								gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedForestsWithCollision().add(tile);
							}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	

}
