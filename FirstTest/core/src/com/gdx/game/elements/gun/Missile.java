package com.gdx.game.elements.gun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicObject;
import com.gdx.game.elements.SpawnObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.stages.enums.MissileTypeEnum;

public class Missile extends DynamicObject implements SpawnObject {
	
	
    public float power;
    private float speed;
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    private boolean spawned;
    private SpawnPool pool;
	
    private Texture[] text_laser_1;
    
    
	public Missile() {
		super();
		pool = null;
	}
	
	
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	public void init(MissileTypeEnum type, float power, float xStart, float yStart, float angle, float speed) {
		 
		 this.speed = speed;
		 this.power = power;
		 
		 position.set(xStart, yStart);
	     direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();

		 
		 if (type.equals(MissileTypeEnum.LASER_1)) {
			 text_laser_1 = new Texture[3];
			 text_laser_1[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_small,Texture.class); 
			 text_laser_1[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_medium,Texture.class);
			 text_laser_1[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_large,Texture.class);
			 
			 super.init(text_laser_1);
			 super.setSize(10, 30);
			 super.setSpeed(this.speed, this.speed);
		 }
	 }
	

	@Override
	public void setSpawned(boolean spawned) {	
		this.spawned = spawned;
	}

	@Override
	public boolean isSpawned() {
		return this.spawned;
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		super.setPosition(FirstTestGDX.screenWidth, 0);
	}

	@Override
	public void update(float delta, float boostFactor) {
		super.animate(delta);
		if (isSpawned()) {
			 
			 movement.set(direction).scl(speed * delta * boostFactor);
	         position.add(movement);
	         super.setPosition(position.x, position.y);
	         
	         if (position.x > FirstTestGDX.screenWidth || 
	             position.x < 0 || 
	             position.y > FirstTestGDX.screenHeight || 
	             position.y < 0) {
	                pool.returnToPool(this);
	         }
		}
	}

	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}
}
