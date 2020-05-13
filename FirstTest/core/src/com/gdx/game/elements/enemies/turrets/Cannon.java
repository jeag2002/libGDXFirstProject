package com.gdx.game.elements.enemies.turrets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.stages.enums.CannonTypeEnum;
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
	
	private boolean rotation;
	
	private SpawnPool pool;
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    private static final float bgSpeed = 50.0f;
    private static final float intervalGunSpiral = 0.15f;
    private static final float intervalGunRadial = 0.0f;
    
    private static final float intervalGunMill = 0.5f;
    
    
    
    private CannonTypeEnum cT;
    
    private int actPosition;
	
	
	public Cannon(SpawnType type, SpawnPool sP, World world) {
		super(sP, world);
		this.type = type;
		
	}
	
	public void init(CannonTypeEnum cT, float xStart, float yStart, float angle, float speed, boolean blocked) {
		
		super.init(SpawnType.MissileEnemy);
		
		setReference(this);
		this.angle = angle;
		this.timer = 0;
		this.speed = speed;
		this.blocked = blocked;

		this.rotation = true;
		
		this.tethaAngle = 270;
		this.cT = cT;
		
		position.set(xStart, yStart);
	    direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();
	    
	    cannon = new Texture[1];
	    cannon[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class); 
		
	    label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCannon_01,Texture.class);  
		base = new Sprite(label);
		
		if (cT.equals(cT.CannonSpiral)) {
			label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCannon_02,Texture.class);
		}else {
	    	label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCannon_03,Texture.class);
	    }
		
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
        
		setShootingActive(true);
	
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
		this.setPool(pool);
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		super.setPosition(FirstTestGDX.screenWidth, 0);
		
	}
	
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		base.draw(sb);
		body.draw(sb);
	}
	
	

	@Override
	public void update(float delta, float boostFactor) {
		
		
		super.update(delta);
		
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
       	    base.setOriginBasedPosition(getX()+ getWidth()/2, getY()+ getHeight() / 2);
	         
	        body.setOriginCenter();
	        body.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	        
	        
	         timer += delta * boostFactor;
	         
	         if (cT.equals(cT.CannonSpiral)) {
		         if (timer >= intervalGunSpiral) {
		        	 timer = 0;
		        	 base.setRotation((float) tethaAngle);	 
		        	 setGunSpiral((float) tethaAngle);
		        	 this.tethaAngle -= 22.5;
		        	 if (this.tethaAngle <= 0) {tethaAngle = 360;}
		        	 
		         }
	         }else if (cT.equals(cT.CannonRadial)) {
	        	 if (timer >= intervalGunRadial) {
		        	 timer = 0;
		        	 setGunRadial(speed);
		         }
	         
	         }else if (cT.equals(cT.CannonMill)) {
	        	 
	        	 setGunMill(speed,rotation);
	        	 if (timer >= intervalGunMill) {
	        		 timer = 0;
	        		 rotation = !rotation;
	        	 }
	        	 
	         }
	        
	   
	        if (getX() < 0 || getY() < 0) {
    			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(this.getBody())) {
    				GameElementLogic.toDeletedBodiesWithCollision.add(this.getBody());
    			}
	        } 
		}
	}
	
	public void setGunMill(float speed, boolean rotation) {
		
		setGunPower(100.0f);
		if (rotation) {
			addGun(MissileTypeEnum.PROTON_1,0.0f, speed*2, getX(), getY(), (getWidth()/2) ,   (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,90.0f, speed*2, getX(), getY(), (getWidth()/2) ,  (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,180.0f, speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,270.0f, speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
		}else {
			addGun(MissileTypeEnum.PROTON_1,45.0f, speed*2, getX(), getY(), (getWidth()/2) ,  (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,135.0f, speed*2, getX(), getY(), (getWidth()/2),  (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,225.0f, speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
			addGun(MissileTypeEnum.PROTON_1,315.0f, speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
		}
		
	}
	
	
	public void setGunRadial(float speed) {
		
		setGunPower(100.0f);	
		addGun(MissileTypeEnum.PROTON_1,0.0f, speed*2, getX(), getY(), (getWidth()/2) ,  (getHeight()/2), 16, 16);
		addGun(MissileTypeEnum.PROTON_1,90.0f, speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
		addGun(MissileTypeEnum.PROTON_1,180.0f, speed*2, getX(), getY(), (getWidth()/2), (getHeight()/2), 16, 16);
		addGun(MissileTypeEnum.PROTON_1,270.0f, speed*2, getX(), getY(), (getWidth()/2), (getHeight()/2), 16, 16);
	}
	
	public void setGunSpiral(double angle) {
		setGunPower(100.0f);
		addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX(), getY(), (getWidth()/2) , (getHeight()/2), 16, 16);
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
