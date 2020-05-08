package com.gdx.game.engine.logic;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.enemies.simplenemy.SimpleEnemy;
import com.gdx.game.elements.enemies.turrets.Cannon;
import com.gdx.game.elements.enemies.turrets.Mine;
import com.gdx.game.elements.enemies.turrets.Turret;
import com.gdx.game.elements.gun.Missile;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.items.Bonus;
import com.gdx.game.elements.items.Meteor;
import com.gdx.game.elements.player.Player;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.ExplosionsEnum;
import com.gdx.game.stages.enums.SpawnType;
import com.gdx.game.utils.NewItem;

public class CollisionEngine implements ContactListener {
	
	private GamePlayScreen gPS;
	private GameElementLogic gEL;
	private SpawnPool spawnPool;
	private Player player;
	
	
	public CollisionEngine(GamePlayScreen gPS, SpawnPool spawnPool, Player player, GameElementLogic gEL) {
		this.gPS = gPS;
		this.gEL = gEL;
		this.spawnPool = spawnPool;
		this.player = player;
		
	}
	

	
	@Override
    public void beginContact(Contact contact) {
    	
    	
    	if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {
    	       
    	
        	String objectStrA = (String)contact.getFixtureA().getBody().getUserData();
        	String objectStrB = (String)contact.getFixtureB().getBody().getUserData();
        	
        	
        	SpawnObject objectA = spawnPool.getElementWithCollisionById(objectStrA);
        	SpawnObject objectB = spawnPool.getElementWithCollisionById(objectStrB);
        	
        	String msg = "";
        	
        	if ((objectA != null) && (objectB != null)) {
            	if ((
            		(objectA instanceof Missile) || 
            		(objectA instanceof Meteor) || 
            		(objectA instanceof SimpleEnemy) || 
            		(objectA instanceof Turret) ||
            		(objectA instanceof Mine) ||
            		(objectA instanceof Cannon)) &&
            	(
            		(objectB instanceof Missile) || 
            		(objectB instanceof Meteor) || 
            		(objectB instanceof SimpleEnemy) || 
            		(objectB instanceof Turret) ||
            		(objectB instanceof Mine) ||
            		(objectB instanceof Cannon))) {
            		
            		
            		
            		boolean isMissilePlayerA = false;
            		boolean isMissilePlayerB = false;
            		boolean isMissileEnemyA = false;
            		boolean isMissileEnemyB = false;
            		boolean isEnemyA = false;
            		boolean isEnemyB = false;
            		boolean isMeteorA = false;
            		boolean isMeteorB = false;
            		boolean isTurretA = false;
            		boolean isTurretB = false;
            		boolean isMineA = false;
            		boolean isMineB = false;
            		boolean isCannonA = false;
            		boolean isCannonB = false;
            		
            		
            		SimpleEnemy enemyA = null;
            		SimpleEnemy enemyB = null;
            		
            		Meteor meteorA = null;
            		Meteor meteorB = null;
            		
            		Turret turretA = null;
            		Turret turretB = null;
            		
            		Mine mineA = null;
            		Mine mineB = null;
            		
            		Cannon cannonA = null;
            		Cannon cannonB = null;
            		
            		
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
            			
            		}else if (objectA instanceof Turret) {
            			turretA = (Turret)objectA;
            			isTurretA = true;
            			
            		}else if (objectA instanceof Mine) {
            			mineA = (Mine)objectA;
            			isMineA = true;
            			
            		}else if (objectA instanceof Cannon) {
            			cannonA = (Cannon)objectA;
            			isCannonA = true;
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
            			
            		}else if (objectB instanceof Turret) {
            			turretB = (Turret)objectB;
            			isTurretB = true;
            		
            		}else if (objectB instanceof Mine) {
            			mineB = (Mine)objectB;
            			isMineB = true;
            			
            		}else if (objectB instanceof Cannon) {
            			cannonB = (Cannon)objectB;
            			isCannonB = true;
            		}
            		
            		
            		if (isMissilePlayerA && isEnemyB) {
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
            			
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyB.getX(),enemyB.getY());
            			
            		}else if (isMissilePlayerB && isEnemyA) {
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
            			
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyA.getX(),enemyA.getY());
            		}
            		
            		
            		if (isMissilePlayerA && isMeteorB) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorB.getX(),meteorB.getY());
            			
            			GameElementLogic.toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteorB.getX(),meteorB.getY()));
            			
            		}else if (isMissilePlayerB && isMeteorA) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorA.getX(),meteorA.getY());
            			
            			GameElementLogic.toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteorA.getX(),meteorA.getY()));	
            		}
            		
            		
            		if (isMissilePlayerA && isTurretB) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,turretB.getX(),turretB.getY());
            			
            			
            		}else if (isMissilePlayerB && isTurretA) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,turretA.getX(),turretA.getY());
            		
            		}
            		
            		if (isMissilePlayerA && isMineB) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,mineB.getX(),mineB.getY());
            			
            			
            		}else if (isMissilePlayerB && isMineA) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,mineA.getX(),mineA.getY());
            		
            		}
            		
            		if (isMissilePlayerA && isCannonB) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,cannonB.getX(),cannonB.getY());
            			
            			
            		}else if (isMissilePlayerB && isCannonA) {
            			
            			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,cannonA.getX(),cannonA.getY());
            		
            		}
            		
            		
            		
            		
            		
            		if (isEnemyA && isMeteorB) {
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyA.getX(),enemyA.getY());
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorB.getX(),meteorB.getY());
            			
            			
            			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
	        			}
	            		
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
	        			}
            			
            			
            		}else if (isEnemyB && isMeteorA) {
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemyB.getX(),enemyB.getY());
            			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteorA.getX(),meteorA.getY());
            			
            			
            			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
	        			}
	            		
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
	        			}
	            		 
            		}
            		
            		if ((isMissilePlayerA && (isMissileEnemyB || isEnemyB || isMeteorB || isTurretB || isMineB || isCannonB)) || 
            		   (isMissilePlayerB && (isMissileEnemyA || isEnemyA || isMeteorA || isTurretA || isMineA || isCannonA))) {
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
	        			}
	            		
	            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
	        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
	        			}
            		}

            	}
        	}
        	
        	
        	if (((player.getCode().equals(objectStrA )) && ((objectB instanceof Meteor) || (objectB instanceof Missile) || (objectB instanceof Turret) || (objectB instanceof Mine) || (objectB instanceof Cannon) || (objectB instanceof Bonus) || (objectB instanceof SimpleEnemy)))){
        		
        		
        		boolean isMissileEnemy = false;
        		boolean isEnemy = false;
        		boolean isMeteor = false;
        		boolean isBonus = false;
        		boolean isTurret = false;	
        		boolean isMine = false;
        		boolean isCannon = false;
        	
        		
        		Bonus bonus = null;
        		
        		
        		if (objectB instanceof Missile) {
        			Missile missile = (Missile)objectB;	
        			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
        		
        		}else if (objectB instanceof SimpleEnemy) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			SimpleEnemy enemy = (SimpleEnemy)objectB;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemy.getX(),enemy.getY());            			
        			isEnemy = true;
        		
        		}else if (objectB instanceof Turret) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Turret turret = (Turret)objectB;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, turret.getX(), turret.getY());            			
        			isTurret = true;
        		
        		}else if (objectB instanceof Mine) { 
        		
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Mine mine = (Mine)objectB;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, mine.getX(), mine.getY());            			
        			isMine = true;
        		
        		
        		}else if (objectB instanceof Cannon) {
        		
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Cannon cannon = (Cannon)objectB;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, cannon.getX(), cannon.getY());            			
        			isCannon = true;
        			
        		
        		}else if (objectB instanceof Meteor) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			Meteor meteor = (Meteor)objectB;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteor.getX(),meteor.getY());
        			
        			gEL.toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteor.getX(),meteor.getY()));
        			
        			isMeteor = true;
        			
        		}else if (objectB instanceof Bonus) {
        			bonus = (Bonus)objectB;
        			gEL.playBonus();
        			isBonus = true;
        		}
        		
        		 if (isBonus) {  
        			gEL.setTypeShoot(bonus); 
        			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
	        			GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
	        		}	
            	 }
        		
        		
        	
        		if (isMissileEnemy || isEnemy || isMeteor || isTurret || isMine || isCannon) {
        			gPS.getgLL().processCollision();
        			gEL.playCrash();
        			
        			if (gPS.getgLL().isGameOver()) {
        				player.setEndMap(true);
        				gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeTwo,player.getX(),player.getY());     
        			}
        			
            		if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureB().getBody())) {
        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureB().getBody());
        			}
        		}
			}
        	
        	if (((player.getCode().equals(objectStrB )) && ((objectA instanceof Meteor) || (objectA instanceof Missile) || (objectA instanceof Turret) || (objectA instanceof Mine) || (objectA instanceof Cannon) ||(objectA instanceof Bonus) || (objectA instanceof SimpleEnemy)))){
        		
        		boolean isMissileEnemy = false;
        		boolean isEnemy = false;
        		boolean isMeteor = false;
        		boolean isBonus = false;
        		boolean isTurret = false;
        		boolean isMine = false;
        		boolean isCannon = false;
        		
        		Bonus bonus = null;
        		
        	    if (objectA instanceof Missile) {
        			
        	    	Missile missile = (Missile)objectA;	
        			isMissileEnemy = missile.getType().equals(SpawnType.MissileEnemy);
        			
        		}else if (objectA instanceof SimpleEnemy) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			SimpleEnemy enemy = (SimpleEnemy)objectA;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,enemy.getX(),enemy.getY());
        			isEnemy = true;
        			
        		}else if (objectA instanceof Turret) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Turret turret = (Turret)objectA;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, turret.getX(), turret.getY());            			
        			isTurret = true;
        		
        		}else if (objectA instanceof Mine) { 
        		
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Mine mine = (Mine)objectA;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, mine.getX(), mine.getY());            			
        			isMine = true;
        		
        		
        		}else if (objectA instanceof Cannon) {
        		
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			gPS.getgLL().setKills(gPS.getgLL().getKills()+1);
        			Cannon cannon = (Cannon)objectA;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne, cannon.getX(), cannon.getY());            			
        			isCannon = true;
        			
        		
        		}else if (objectA instanceof Meteor) {
        			
        			gPS.getgLL().setScorePlayer(gPS.getgLL().getScorePlayer()+100);
        			Meteor meteor = (Meteor)objectA;
        			gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeOne,meteor.getX(),meteor.getY());
        			
        			gEL.toCreatedItemsWithCollision.add(new NewItem(SpawnType.Item,meteor.getX(),meteor.getY()));
        			
        			isMeteor = true;
        			
        		}else if (objectA instanceof Bonus) {
        			bonus = (Bonus)objectA;
        			gEL.playBonus();
        			isBonus = true;
        		}
        	    
        	    
        	    if (isBonus) {
        	    	gEL.setTypeShoot(bonus); 
        	    	if (!GameElementLogic.toDeletedBodiesWithCollision.contains(contact.getFixtureA().getBody())) {
        				GameElementLogic.toDeletedBodiesWithCollision.add(contact.getFixtureA().getBody());
        			}
        	    	
        	    }
        	    
        	   
        		if (isMissileEnemy || isEnemy || isMeteor || isTurret || isMine || isCannon) {
        			gEL.playCrash();
        			gPS.getgLL().processCollision();
        			
        			if (gPS.getgLL().isGameOver()) {
        				player.setEndMap(true);
        				gEL.explosionGeneration(ExplosionsEnum.ExplosionTypeTwo,player.getX(),player.getY());     
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
		// TODO Auto-generated method stub
		
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
