package com.mygdx.game.elements;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.World;
//import com.gdx.game.elements.gun.Missile;
//import com.gdx.game.engine.GamePlay;
//import com.gdx.game.stages.enums.MissileTypeEnum;
//import com.gdx.game.stages.enums.SpawnType;

/**
 * Child class used by player and enemies. Represents items that can spawn other items
 *
 */

public abstract class ShootObject extends DynamicCollObject {
	

	 //private SpawnType missilesPool; 
	 
	 private ArrayList<Gun> guns = new ArrayList<Gun>();
	 
	 private boolean shootingActive;
	 private float timer;
	 private float gunPower = 1.0f;
	 private float shootingInterval = 0.5f;
	
	 private boolean shootEvent;
	 
	 
	//private SpawnPool spawnPool;
	
	public ShootObject(/*SpawnPool spawnPool,*/ World world) {
		super(world);
		//this.spawnPool = spawnPool;
        shootEvent = false;
        
}
	 
	 
	 public class Gun {
	        boolean active = false;
	        float speed = 100.0f;
	        float angle;
	        float offsetX;
	        float offsetY;
	        float originX;
	        float originY;
	        float width;
	        float height;
	        //MissileTypeEnum missType;
	        
	        
	        
	        public Gun() {
	        }
	        
	        public Gun(float angle, float speed) {
	            this.speed = speed;
	            this.angle = angle;
	        }
	 }
	 
	 public void addGun(/*MissileTypeEnum missType,*/ float angle, float speed, float originX, float originY, float offsetX, float offsetY, float width, float height) {
		 	
		 	Gun gun = new Gun(0,0);
		 	//gun.missType = missType;
		 	gun.speed = speed;
		 	gun.angle = angle;
         
		 	gun.originX = originX;
		 	gun.originY = originY;
         
		 	gun.offsetX = offsetX;
		 	gun.offsetY = offsetY;
		 	
		 	gun.width = width;
		 	gun.height = height;
		 	
		 	gun.active = true;
		 	
		 	guns.add(gun);
		 	
	 }
	 
	

	public void setShootingActive(boolean active) {
	      this.shootingActive = active;
	}

	public void setGunPower(float power) {
	     this.gunPower = power;
	}
	
	/*
	public void init(SpawnType missilPool) {
		this.missilesPool = missilPool;
		this.shootingActive = true;
		this.timer = 0.0f;
		
	}
	*/
	
	public void resetGuns() {
		for(Gun g: guns) {g.active = false;}
	}
	 
	public void update(float delta) {
        if (shootingActive) {
            shoot();
        }
    }
	
	public boolean isShootEvent() {
		return shootEvent;
	}
	

	public void setShootEvent(boolean shootEvent) {
		this.shootEvent = shootEvent;
	}

	
	public void shoot() {
		
		ArrayList<Gun> removableGun = new ArrayList<Gun>();
		/*
	    for (Gun g: guns) {
	         if (g.active) {
	            Missile m = (Missile) spawnPool.getFromPool(missilesPool);  
	            m.init(g.missType, gunPower,g.originX + g.offsetX, g.originY  + g.offsetY, g.angle, g.speed, g.width, g.height);
	            m.setPool(spawnPool);
	            removableGun.add(g);
	         }
	     }
	     */
	     guns.removeAll(removableGun);
	} 
	
	
}
