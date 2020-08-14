package com.mygdx.game.elements.missiles;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.players.DynamicCollPlayerObject;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Missile extends DynamicCollPlayerObject implements SpawnObject {
	
    private float speed;
    private float timer;
    private float power;
    private int index;
    
    private float width;
    private float height;
    
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    private boolean spawned;
    private SpawnPool pool;
    
    
    private Texture[] text_laser_1;

    private SpawnType type;
    private SpawnType subType;
    
    private PointLight light;

	public Missile(SpawnType type, World world) {
		super(world, type);
		pool = null;
		this.type = type;
	}
	
	
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	public void init(RayHandler rayHandler, SpawnType subType, float power, float xStart, float yStart, float angle, float speed, float width, float height) {
		 
		 this.speed = speed;
		 this.power = power;
		 this.index = 0;
		 this.timer = 0;
		 
		 this.width = width;
		 this.height = height;
		 
		 position.set(xStart, yStart);
	     direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();

	     this.subType = subType;
		 
	     
	     if (subType.equals(SpawnType.Missile_Laser)) {
	    	 text_laser_1 = GameLogicElementInformation.laser;
	     }else if (subType.equals(SpawnType.Missile_Plasma)) {
	    	 text_laser_1 = GameLogicElementInformation.plasma;
	     }else if (subType.equals(SpawnType.Missile_Pulse)) {
	    	 text_laser_1 = GameLogicElementInformation.pulse;
	     }else if (subType.equals(SpawnType.Missile_Missile)) {
	    	 text_laser_1 = GameLogicElementInformation.missile;
	     }
	     
		 
		 super.init(text_laser_1,0);
		 super.setPosition(xStart, yStart);
		 
		 super.setSize(this.width, this.height);
		 super.setSpeed(this.speed, this.speed);
		 
		 super.getSprite().setOriginCenter();
		 super.rotate(angle-90);
		 
		 super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody,true);
		 
		 this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		 this.light.setSoftnessLength(0f);
		 this.light.attachToBody(this.getBody());
		 
	}
	
	public void animate(float delta) {
		this.AnimationByTime(delta);
	}
	
	
	@Override
	public void AnimationByTime(float delta) {
			 AnimationByTimeLASER_1(delta);
	}
	
	
	
	private void AnimationByTimeLASER_1(float delta) {
		timer += delta;
	    if (timer >= 0.05f) {
	    	timer = 0.0f;
			index++;
			if (index < text_laser_1.length) {getSprite().setTexture(text_laser_1[index]);}
			else {index = 0;}
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
		dispose();
		super.setPosition(SecondTestGDX.screenWidth, 0);
	}

	@Override
	public void update(float delta, float boostFactor) {
		
		animate(delta);
		
		if (isSpawned()) {	 
			 
			 movement.set(direction).scl(speed * delta * boostFactor);
			 //position.add(movement);
	         //super.setPosition(position.x, position.y);
	         //super.setCollisionRef(position.x, position.y);
			  
	        super.setCollisionVel(movement.x, movement.y);
	        Vector2 posRelative = super.getPositionFromBodyToPixel();
	        super.setPosition(posRelative.x, posRelative.y);
			  
	         if (getX() > SecondTestGDX.screenWidth || 
	             getX() < 0 || 
	             getY() > SecondTestGDX.screenHeight || 
	             getY() < 0) {
        			
	         }
		}
	}

	
	public String toString() {
		return " Missile (" + getIdCode() + ") ORIGIN (" + type.toString() + ") ORIGIN (" + type.toString() + ") TYPE (" + subType.toString() + ")";
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
	public void AnimationByMovement(PlayerMovementsEnum movement,float moveStepX, float moveStepY,boolean isAccX, boolean isAccY) {}

	@Override
	public void AnimationLoop(float delta, boolean loop) {}


	@Override
	public Body getBox2DBody() {
		return super.getBody();
	}
	
	public void dispose() {
		light.remove();
	}
	
	
}
