package com.gdx.game.elements;

import java.util.*;

import com.gdx.game.elements.gun.Missile;
import com.gdx.game.stages.enums.SpawnType;

public class SpawnPool {

    private HashMap<SpawnType, ArrayList<SpawnObject>> pools
            = new HashMap<SpawnType, ArrayList<SpawnObject>>();

    public SpawnPool(){}

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

        //Debug
        //printPoolSize();

        return spawn;
    }

    public void returnToPool(SpawnObject object) {
        object.setSpawned(false);
        //Debug
        //printPoolSize();
    }

private SpawnObject createSpawnObject(SpawnType type) {
    try {
    	
        SpawnObject created = null;
        if (type.name() == "MissilePlayer") {
        	created = new Missile();
        }
        else if (type.name() == "MissileEnemy") {
        }
        else if (type.name() == "Enemy") {
        }
        else if (type.name() == "Item") {
        }
        else if (type.name() == "Obstacle") {
        }
        else if (type.name() == "Explosion") {
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
