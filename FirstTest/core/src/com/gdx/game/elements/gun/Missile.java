package com.gdx.game.elements.gun;

import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicCollObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Missile extends DynamicCollObject implements SpawnObject {
	
    private float speed;
    private float timer;
    private float power;
    private int index;
    
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    private boolean spawned;
    private SpawnPool pool;
	
    private Texture[] text_laser_1;

    
    private MissileTypeEnum typeMissile;
    private SpawnType type;
    

	public Missile(SpawnType type, World world) {
		super(world);
		pool = null;
		this.type = type;
	}
	
	
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	public void init(MissileTypeEnum type, float power, float xStart, float yStart, float angle, float speed) {
		 
		 this.speed = speed;
		 this.power = power;
		 this.index = 0;
		 this.timer = 0;
		 
		 position.set(xStart, yStart);
	     direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();

	     this.typeMissile = type;
		 
	     
	     setReference(this);
		 if (typeMissile.equals(MissileTypeEnum.LASER_1)) {
			 text_laser_1 = new Texture[3];
			 text_laser_1[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_small,Texture.class); 
			 text_laser_1[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_medium,Texture.class);
			 text_laser_1[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_large,Texture.class);
			 
			 super.init(text_laser_1,0);
			 super.setPosition(xStart, yStart);
			 super.setSize(10, 30);
			 super.setSpeed(this.speed, this.speed);
			 super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		 }
	}
	
	public void animate(float delta) {
		
		if (typeMissile.equals(MissileTypeEnum.LASER_1)) {
			this.AnimationByTime(delta);
		}
	}
	
	
	@Override
	public void AnimationByTime(float delta) {
		 if (typeMissile.equals(MissileTypeEnum.LASER_1)) {
			 AnimationByTimeLASER_1(delta);
		 }
	}
	
	
	
	private void AnimationByTimeLASER_1(float delta) {
		timer += delta;
	    if (timer >= 0.05f) {
	    	timer = 0.0f;
			index++;
			if (index < text_laser_1.length) {getSprite().setTexture(text_laser_1[index]);}
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
		animate(delta);
		
		if (isSpawned()) {	 
			 movement.set(direction).scl(speed * delta * boostFactor);
	         position.add(movement);
	         super.setPosition(position.x, position.y);
	         super.setCollisionRef(position.x, position.y);
	         
	         if (position.x > FirstTestGDX.screenWidth || 
	             position.x < 0 || 
	             position.y > FirstTestGDX.screenHeight || 
	             position.y < 0) {
	                //Gdx.app.log("[MISSILE]","remove MISSILE for reach the limit (" + getCode() + ")");
        			if (!GameElementLogic.toDeletedBodies.contains(this.getBody())) {
        				GameElementLogic.toDeletedBodies.add(this.getBody());
        			}
	         }
		}
	}

	
	public String toString() {
		return " Missile (" + getCode() + ") ORIGIN (" + type.toString() + ") TYPE (" + typeMissile.toString() + ")";
	}
	
	
	public SpawnType getType() {
		return type;
	}


	public void setType(SpawnType type) {
		this.type = type;
	}
	
	

	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}

	@Override
	public void AnimationByMovement(PlayerMovements movement,float moveStepX, float moveStepY,boolean isAccX, boolean isAccY) {}

	@Override
	public void AnimationLoop(float delta, boolean loop) {}
	
	
	
	
}
