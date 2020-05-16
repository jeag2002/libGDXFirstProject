package com.gdx.game.elements.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicCollObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.stages.enums.BonusTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Bonus extends DynamicCollObject implements SpawnObject {
	
	
	private Texture[] text_bonus;
    private Texture background;
    
    private Sprite backgroundSprite;
	
	private boolean spawned;
    private boolean latchBackground;
    
	private SpawnPool pool;
    
    private float speed;
    private float angle;
    
    private float time;
    
    
    
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    private BonusTypeEnum typeBonus;
    private SpawnType type;
   
	public Bonus(SpawnType type, World world) {
		super(world);
		pool = null;
		this.type = type;
	}
	
	
	public void init(BonusTypeEnum type, float xStart, float yStart, float angle, float speed) {
		
		position.set(xStart, yStart);
		
	    this.typeBonus = type;
	    text_bonus = new Texture[1];
	    
	    this.angle = angle;
	    this.speed = speed;
	    
	    this.time = 0;
	    
	    position.set(xStart, yStart);
	    direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();
	    
	    if (typeBonus.equals(type.BonusAmmo)) {
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_A,Texture.class); 
	    }else if (typeBonus.equals(type.BonusEnergy)) {
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_E,Texture.class); 
	    }else if (typeBonus.equals(type.BonusHealth)){
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_H,Texture.class); 
	    }else if (typeBonus.equals(type.BonusShield)){
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_S,Texture.class); 
	    }
	    
	    background = FirstTestGDX.resources.get(FirstTestGDX.resources.imgItemBorder,Texture.class);
	    backgroundSprite = new Sprite(background);
	    backgroundSprite.setSize(64, 64);
	    backgroundSprite.setPosition(xStart-16, yStart-24); 
	    
	    this.latchBackground = false;
	    
	    super.init(text_bonus,0);
		super.setPosition(xStart, yStart);
		super.setSize(32, 16);
		super.setSpeed(this.speed,this.speed);
		super.setReference(this);
		super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
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
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		backgroundSprite.setPosition(FirstTestGDX.screenWidth, 0);
		super.setPosition(FirstTestGDX.screenWidth, 0);
	}

	@Override
	public void update(float delta, float boostFactor) {	
		/**/
		animate(delta, boostFactor);
		if (isSpawned()) {	 
			 movement.set(direction).scl(speed * delta * boostFactor);
	         position.add(movement);
	         super.setPosition(position.x, position.y);
	         
	         backgroundSprite.setOriginCenter();
	         backgroundSprite.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	         
	         
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
	

	public void animate(float delta, float boostFactor) {
		
		this.time += delta;
		if (this.time >= 0.1) {
			
			if (!this.latchBackground) {
				backgroundSprite.setSize(32, 32);
				
			}else {
				backgroundSprite.setSize(64, 64);
			}
			
			time = 0;
			latchBackground = !latchBackground;
		}
		
	}
	
	
	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		backgroundSprite.draw(sb);
	}
	

	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
	}

	@Override
	public void AnimationByTime(float delta) {
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
	}

}
