package com.gdx.game.elements;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.gdx.game.elements.gun.Missile;
import com.gdx.game.engine.GamePlay;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.SpawnType;

/**
 * Child class used by player and enemies. Represents items that can spawn other items
 *
 */

public class ShootObject extends CollisionObject {
	
	 private static final int MAXGUNS = 10;

	 private SpawnType missilesPool; 
	 private Gun guns[] = new Gun[MAXGUNS];
	 private boolean shootingActive;
	 private int gunsCount = 0;
	 private float timer;
	 private float gunPower = 1.0f;
	 private float shootingInterval = 0.5f;
	 private MissileTypeEnum gunType;
	
	 private boolean shootEvent;
	 
	 
	private GamePlay gP;
	 
	 
	 public class Gun {
	        boolean active = false;
	        float speed = 100.0f;
	        float angle;
	        float offsetX;
	        float offsetY;
	        float originX;
	        float originY;
	        
	        public Gun() {
	        }
	        
	        public Gun(float angle, float speed) {
	            this.speed = speed;
	            this.angle = angle;
	        }
	 }
	 
	 public void addGun(float angle, float speed, float originX, float originY, float offsetX, float offsetY) {
	        if (gunsCount < MAXGUNS) {
	            Gun gun = guns[gunsCount];
	            gun.speed = speed;
	            gun.angle = angle;
	            
	            gun.originX = originX;
	            gun.originY = originY;
	            
	            gun.offsetX = offsetX;
	            gun.offsetY = offsetY;
	            gun.active = true;
	            gunsCount++;
	        }else {
	        	
	        	Gun gun = null;
	        	boolean found = false;
	        	int index = 0;
	        	for(index=0; index<MAXGUNS && !found; index++) {
	        		found = !guns[index].active;
	        	}
	        	
	        	if (index <MAXGUNS) {
	        		guns[index].speed = speed;
	        		guns[index].angle = angle;
	        		guns[index].originX = originX;
	        		guns[index].originY = originY;
	        		guns[index].offsetX = offsetX;
	        		guns[index].offsetY = offsetY;
	        		guns[index].active = true;
	        	}
	        	
	        	
	        }
	 }
	 
	

	public void setShootingActive(boolean active) {
	      this.shootingActive = active;
	}

	public void setGunPower(float power) {
	     this.gunPower = power;
	}

	public void setShootingInterval(float interval) {
	     this.shootingInterval = interval;
	}
	
	
	public ShootObject(GamePlay gP) {
			this.gP = gP;
	        for(int i=0; i<MAXGUNS; ++i) {
	            Gun gun = new Gun(0, 0);
	            guns[i] = gun;
	        }
	        shootEvent = false;
	        
	}

	
	
	public void init(SpawnType missilPool) {
		this.missilesPool = missilPool;
		this.shootingActive = true;
		this.timer = 0.0f;
		
	}
	
	public void resetGuns() {
		for(Gun g: guns) {g.active = false;}
		this.gunsCount = 0;
	}
	 
	public void update(float delta) {
        if (shootingActive) {
            timer += delta;
            if (timer >= shootingInterval) {
                shoot();
                timer = 0;
            }
        }
    }
	
	public boolean isShootEvent() {
		return shootEvent;
	}
	

	public void setShootEvent(boolean shootEvent) {
		this.shootEvent = shootEvent;
	}

	
	
    public void setGunType(MissileTypeEnum type) {
	        this.gunType = type;
	}
	
	public void shoot() {

	     for (Gun g: guns) {
	         if (g.active) {
	            Missile m = (Missile) gP.getgEL().getSpawnPool().getFromPool(missilesPool);
	            m.init(gunType, gunPower,g.originX + g.offsetX, g.originY  + g.offsetY, g.angle, g.speed);
	            m.setPool(gP.getgEL().getSpawnPool());
	            g.active = false;
	         }
	      }
	 }

	
	 
	 
	 

	
	
	
}
