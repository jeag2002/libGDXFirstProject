package com.mygdx.game.logic.elements;

import java.util.*;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.elements.complexenemy.tanks.TankEnemy;
import com.mygdx.game.elements.simpleenemy.drons.SimpleEnemy;
import com.mygdx.game.enums.*;
import com.mygdx.game.screens.GamePlayScreen;

public class SpawnPool {

    private HashMap<SpawnType, ArrayList<SpawnObject>> pools
            = new HashMap<SpawnType, ArrayList<SpawnObject>>();
    
    private String nameOfPackage = "com.mygdx.game.logic.elements.*";

    private World world;
    private GamePlayScreen gPS;
    
    public SpawnPool(World world, GamePlayScreen gPS){
    	this.world = world;
    	this.gPS = gPS;
    }
    
    
    public void addPool(SpawnType type, ArrayList<SpawnObject> pool) {
        this.pools.put(type, pool);
    }

    public SpawnObject getFromPool(SpawnType type) {
    	
        ArrayList<SpawnObject> pool = pools.get(type);
        if (pool == null) {
            System.err.println("Pool of type " + type.name() +
                    " doesn't exist. Maybe forgot to create an" +
                    " ArrayList instance for that pool?");
            return null;
        }
        
        SpawnObject found = null;
        for (int i = 0; i<pool.size(); ++i) {
            if (!pool.get(i).isSpawned()) {
                found = pool.get(i);
                break;
            }
        }
        if (found != null) {
            found.setSpawned(true);
            return found;
        }
        SpawnObject spawn = createSpawnObject(type);

        if (spawn == null) {
            System.err.println("Instance of Spawn of type "
                    + type.name() + " could not be created");
            return null;
        }
        
        spawn.setSpawned(true);
        pools.get(type).add(spawn);

        return spawn;
    }

    public void returnToPool(SpawnObject object) {
        object.setSpawned(false);
    }
    
    
   public SpawnObject getDynamicElementtWithCollisionById(String uuid) {
    	
    	SpawnObject returnObject = null;
    	
    	//DRONE
    	ArrayList<SpawnObject> eS1 = pools.get(SpawnType.Enemy_01);
    	for(SpawnObject sO: eS1){
    		
    		SimpleEnemy sE = (SimpleEnemy)sO;
    		if (sE.getIdCode().equalsIgnoreCase(uuid)) {
    			returnObject = sO;
    			break;
    		}
    		
    	}
    	
    	//TANK
    	if (returnObject == null) {
    		
    		eS1 = pools.get(SpawnType.Enemy_02);
        	for(SpawnObject sO: eS1){
        		/*
        		Turret sE = (Turret)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        		*/
        	}
    	}
    	
    	//MINE
    	if (returnObject == null) {
    		
    		eS1 = pools.get(SpawnType.Enemy_03);
    		for (SpawnObject sO: eS1) {
    			
    		}
    		
    	}
    	
    	
    	if (returnObject == null) {
    		eS1 = pools.get(SpawnType.MissilePlayer);
        	for(SpawnObject sO: eS1){
        		
        		/*
        		Missile sE = (Missile)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        		*/
        	}
    	}
    	
    	if (returnObject == null) {
    		eS1 = pools.get(SpawnType.MissileEnemy);
        	for(SpawnObject sO: eS1){
        		/*
        		Missile sE = (Missile)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        		*/
        	}
    	}
    	
    	
    	if (returnObject == null) {
    		
    		eS1 = pools.get(SpawnType.Item);
        	for(SpawnObject sO: eS1){
        		/*
        		Bonus m = (Bonus)sO;
        		if (m.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        		*/
        	}
    	}
    	
    	
    	return returnObject;
    }
    
    
    
    

    private SpawnObject createSpawnObject(SpawnType type) {

    String className = nameOfPackage + type.name();
    try {
        SpawnObject created = null;
        if (type.name() == "MissilePlayer") {
        }else if (type.name() == "MissileEnemy") {
        }else if (type.name() == "Enemy_01") {
            created = new SimpleEnemy(this,SpawnType.Enemy_01,world,gPS);
        }else if (type.name() == "Enemy_02") {
        	created = new TankEnemy(this, SpawnType.Enemy_02, ElementEnum.GUN_ENEMY2, world, gPS);
        }else if (type.name() == "Enemy_03") {
    	}else if (type.name() == "Item") {
        }else if (type.name() == "Obstacle") {
        }else if (type.name() == "Explosion") {
        }else {
            System.err.println("SpawnPool: " + type.name()
                    + " not able to spawn. Maybe forgot to add in createSpawnObject()?");
        }

        return created;

    } catch(Exception e) {
        System.err.println(e);
        System.err.println("Type name: " + type.name());
        System.err.println("Class name: " + className);
    }
    return null;
}

}

/*
  private void printPoolSize() {
        //Debug pool size
        Iterator it = pools.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            SpawnType type = (SpawnType) pair.getKey();
            ArrayList<SpawnObject> pool = (ArrayList<SpawnObject>) pair.getValue();
            int countNotSpawned = 0;
            int countSpawned = 0;
            for (SpawnObject spawn: pool) {
                if (!spawn.isSpawned())
                    countNotSpawned++;
                else
                    countSpawned++;
            }
            System.out.println("Pool: " + type.name() + " -> Size:  " + pool.size() +  ", Spawned: " + countSpawned);
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
 */


