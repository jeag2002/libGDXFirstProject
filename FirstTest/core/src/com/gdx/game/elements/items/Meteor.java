package com.gdx.game.elements.items;

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
import com.gdx.game.stages.enums.MeteorTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Meteor extends DynamicCollObject implements SpawnObject {
	
	private float speed;
    private float timer;
    private float power;
    private int index;
    
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    private boolean spawned;
    private SpawnPool pool;
	
    private boolean sensePlus = true;
    
    private Texture[] text_meteor;

    
    private MeteorTypeEnum typeMeteor;
    private SpawnType type;
    

	public Meteor(SpawnType type, World world) {
		super(world);
		pool = null;
		this.type = type;
	}
	
	
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	public void init(MeteorTypeEnum type, float power, float xStart, float yStart, float angle, float speed) {
		 
		 this.speed = speed;
		 this.power = power;
		 this.index = 0;
		 this.timer = 0;
		 
		 position.set(xStart, yStart);
	     direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();

	     this.typeMeteor = type;
	     text_meteor = new Texture[2];
		 
	     
	     setReference(this);
		 if (typeMeteor.equals(MeteorTypeEnum.METEORTYPEONE)) {
			 text_meteor[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMeteor_01_a,Texture.class); 
			 text_meteor[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMeteor_01_b,Texture.class);
		 }else if (typeMeteor.equals(MeteorTypeEnum.METEORTYPETWO)) {
			 text_meteor[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMeteor_02_a,Texture.class); 
			 text_meteor[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMeteor_02_b,Texture.class);
		 }
		 
		 super.init(text_meteor,0);
		 super.setPosition(xStart, yStart);
		 super.setSize(64, 64);
		 super.setSpeed(this.speed, this.speed);
		 super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		 
	}
	
	public void animate(float delta) {
		this.AnimationLoop(delta,true);
	}
	
	
	@Override
	public void AnimationLoop(float delta, boolean loop) {
		
		timer += delta;
	    if (timer >= 0.05f) {
	    	timer = 0.0f;
			if (this.sensePlus) {
				index++;
				if (index < text_meteor.length) {setTextureToSpriteByIndex(index);}
				if (index == text_meteor.length) {sensePlus = false;}
			}else {
				index--;
				if (index >= 0) {setTextureToSpriteByIndex(index);}
				if (index < 0) {sensePlus = true;}
			}
		}
	}
	
	
	
	@Override
	public void AnimationByTime(float delta) {
	
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
        			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(this.getBody())) {
        				GameElementLogic.toDeletedBodiesWithCollision.add(this.getBody());
        			}
	         }
		}
	}

	
	public String toString() {
		return " Missile (" + getCode() + ") ORIGIN (" + type.toString() + ") TYPE (" + typeMeteor.toString() + ")";
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

	
	
	
	

}
