package com.gdx.game.elements.enemies.turrets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Cannon extends ShootObject implements SpawnObject {
	
	private SpawnType type;
	private boolean spawned;
	private boolean blocked;
	
	private Texture[] cannon;
	private Texture label;
	private Sprite base;
	private Sprite body;
	private float angle;
	private float timer;
	private float speed;
	private float tethaAngle;
	
	private SpawnPool pool;
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    private static final float bgSpeed = 50.0f;
    private static final float intervalGun = 0.35f;
    
    private int actPosition;
	
	
	public Cannon(SpawnType type, SpawnPool sP, World world) {
		super(sP, world);
		this.type = type;
		
	}
	
	public void init(float xStart, float yStart, float angle, float speed, boolean blocked) {
		
		setReference(this);
		this.angle = angle;
		this.timer = 0;
		this.speed = speed;
		this.blocked = blocked;
		
		this.tethaAngle = 0;
		
		
		position.set(xStart, yStart);
	    direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();
	    
	    cannon = new Texture[1];
	    cannon[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class); 
		
		label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCannon_01,Texture.class);
		base = new Sprite(label);
		
		label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCannon_02,Texture.class);
		body = new Sprite(label);
		
		
		super.init(cannon,0);
		super.setPosition(xStart, yStart);
		super.setSize(64, 64);
		super.setSpeed(0, 0);
		super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		base.setOriginCenter();
        base.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
        
        body.setOriginCenter();
        body.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	
	}
	
	
	@Override
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
		
	}

	@Override
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return this.spawned;
	}

	@Override
	public void setPool(SpawnPool pool) {
		this.pool = pool;
		
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		super.setPosition(FirstTestGDX.screenWidth, 0);
		
	}

	@Override
	public void update(float delta, float boostFactor) {
		
		if (isSpawned()) {	
			
			if (this.blocked) {
				movement.set(direction).scl(GameLevelLogic.speedUpFactor * bgSpeed * delta* (-1));
		        position.add(movement);
			}else {
				 movement.set(direction).scl(speed * delta * boostFactor);
		         position.add(movement);
			}
			
			super.setPosition(position.x, position.y);
	        super.setCollisionRef(position.x, position.y);
	        
	        
	         base.setOriginCenter();
	         base.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	         
	         body.setOriginCenter();
	         body.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	        
	        
	         timer += delta * boostFactor;
	         if (timer >= intervalGun) {
	        	 
	        	 timer = 0;
	        	 base.setRotation((float) tethaAngle);
	        	 setGun(tethaAngle);
	        	 
	        	 this.tethaAngle += 45;
	        	 if (this.tethaAngle >= 360) {tethaAngle = 0;}
	        	 
	         }
	        
	   
	        if (getX() < 0 || getY() < 0) {
    			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(this.getBody())) {
    				GameElementLogic.toDeletedBodiesWithCollision.add(this.getBody());
    			}
	        } 
		}
	}
	
	
	public void setGun(double angle) {
		setGunPower(100.0f);
		setShootingInterval(intervalGun);
		addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX() + (getWidth()/2)  , getY() + (getHeight()/2) , 0 , 0, 16,16);
	}
	

	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationByTime(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
		// TODO Auto-generated method stub
		
	}

}
