package com.gdx.game.elements;

import java.util.*;

import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.elements.enemies.simplenemy.SimpleEnemy;
import com.gdx.game.elements.enemies.turrets.Turret;
import com.gdx.game.elements.explosions.SimpleExplosion;
import com.gdx.game.elements.gun.Missile;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.items.Bonus;
import com.gdx.game.elements.items.Meteor;
import com.gdx.game.stages.enums.SpawnType;

public class SpawnPool {

    private HashMap<SpawnType, ArrayList<SpawnObject>> pools
            = new HashMap<SpawnType, ArrayList<SpawnObject>>();
    
    
    private World world;
    
    public SpawnPool(World world){
    	this.world = world;
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
    	
    	pools.get(SpawnType.MissileEnemy).remove(object);
    	pools.get(SpawnType.MissilePlayer).remove(object);
    	pools.get(SpawnType.Enemy_Simple_1).remove(object);
    	pools.get(SpawnType.Static_Enemy).remove(object);
    	pools.get(SpawnType.Explosion).remove(object);
    	pools.get(SpawnType.Obstacle).remove(object);
    	pools.get(SpawnType.Item).remove(object);
    	
    }
    
    
    public SpawnObject getElementWithCollisionById(String uuid) {
    	
    	SpawnObject returnObject = null;
    	
    	ArrayList<SpawnObject> eS1 = pools.get(SpawnType.Enemy_Simple_1);
    	for(SpawnObject sO: eS1){
    		SimpleEnemy sE = (SimpleEnemy)sO;
    		if (sE.getCode().equalsIgnoreCase(uuid)) {
    			returnObject = sO;
    			break;
    		}
    	}
    	
    	if (returnObject == null) {
    		
    		eS1 = pools.get(SpawnType.Static_Enemy);
        	for(SpawnObject sO: eS1){
        		Turret sE = (Turret)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        	}
    	}
    	
    	if (returnObject == null) {
    		eS1 = pools.get(SpawnType.MissilePlayer);
        	for(SpawnObject sO: eS1){
        		Missile sE = (Missile)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        	}
    	}
    	
    	if (returnObject == null) {
    		eS1 = pools.get(SpawnType.MissileEnemy);
        	for(SpawnObject sO: eS1){
        		Missile sE = (Missile)sO;
        		if (sE.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        	}
    	}
    	
    	
    	if (returnObject == null) {
    		eS1 = pools.get(SpawnType.Obstacle);
        	for(SpawnObject sO: eS1){
        		Meteor m = (Meteor)sO;
        		if (m.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        	}
    	}
    	
    	if (returnObject == null) {
    		
    		eS1 = pools.get(SpawnType.Item);
        	for(SpawnObject sO: eS1){
        		Bonus m = (Bonus)sO;
        		if (m.getCode().equalsIgnoreCase(uuid)) {
        			returnObject = sO;
        			break;
        		}
        	}
    	}
    	
    	
    	return returnObject;
    }
    

    private SpawnObject createSpawnObject(SpawnType type) {
    try {
    	
        SpawnObject created = null;
        if (type.name() == "MissilePlayer") {
        	created = new Missile(type,this.world);
        }
        else if (type.name() == "MissileEnemy") {
        	created = new Missile(type,this.world);
        }
        else if (type.name() == "Enemy_Simple_1") {
        	created = new SimpleEnemy(this, this.world);
        }
        else if (type.name() == "Static_Enemy") {
        	created = new Turret(this,world);
        }
        else if (type.name() == "Item") {
        	created = new Bonus(type,this.world);
        }
        else if (type.name() == "Obstacle") {
        	created = new Meteor(type, this.world);
        }
        else if (type.name() == "Explosion") {
        	created = new SimpleExplosion(this);
        }
        else {
            System.err.println("SpawnPool: " + type.name()
                    + " not able to spawn. Maybe forgot to add in createSpawnObject()?");
        }

        return created;

    } catch(Exception e) {
        System.err.println(e);
        System.err.println("Type name: " + type.name());
    }
    return null;
}

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
}
