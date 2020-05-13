package com.gdx.game.elements.enemies.turrets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.player.Player;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.stages.enums.ExplosionsEnum;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.ShootEnemyType;
import com.gdx.game.stages.enums.SpawnType;
import com.gdx.game.stages.enums.StaticEnemyTypeEnum;

public class Turret extends ShootObject implements SpawnObject{
	
	
	private boolean spawned;
	private SpawnPool sP;
	
	private static final float intervalGun = 0.35f;
	private static final float bgSpeed = 50.0f;
	
	private float timer;
	
	private Texture[] turret;
	
	private Texture turret_Base;
	
	private Sprite base;
	
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
	
    private float speed = 0;
    private float angle = 0;
    
    private StaticEnemyTypeEnum type;
    private ShootEnemyType sET;
    
    
    private Player playerRef;
    
	
	public Turret(SpawnPool sP, World world) {
		super(sP, world);
		this.sP = sP; 
	}
	
	
	public void init(StaticEnemyTypeEnum type, ShootEnemyType sET, float xStart, float yStart,  float angle, float speed, Player playerRef) {
		
		super.init(SpawnType.MissileEnemy);
		
		this.speed = speed;
		this.angle = angle;
		this.position.set(xStart, yStart);
		this.type = type;
		this.sET = sET;
		
		turret = new Texture[1];
		
		setReference(this);
		direction.set((float)Math.cos(Math.toRadians(this.angle)), (float)Math.sin(Math.toRadians(this.angle))).nor();
		
		
		if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_1) || 
			type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_2) ||
			type.equals(StaticEnemyTypeEnum.TURRET_BOSS)
			) {
			turret[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.img_turret_00,Texture.class);
		}
		 
		
		
		if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_1)) {
			turret_Base = FirstTestGDX.resources.get(FirstTestGDX.resources.img_turret_01,Texture.class);
		}else if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_2)) {
			turret_Base = FirstTestGDX.resources.get(FirstTestGDX.resources.img_turret_02,Texture.class);
		}else if (type.equals(StaticEnemyTypeEnum.TURRET_BOSS)) {
			turret_Base = FirstTestGDX.resources.get(FirstTestGDX.resources.img_turret_03,Texture.class);
		}
		
		
		base = new Sprite(turret_Base);
		base.setPosition(xStart, yStart);
		base.setSize(64, 64);
		
		super.init(turret,0);
		super.setPosition(xStart, yStart);
		super.setSize(64, 64);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		
		base.setOriginCenter();
        base.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
        base.setRotation(180);
		
			
		this.timer = 0;
		setShootingActive(true);
		
		this.playerRef = playerRef;
		
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
		this.setPool(pool);
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		setPosition(0, FirstTestGDX.screenHeight);
	}

	@Override
	public void update(float delta, float boostFactor) {
		
		super.update(delta);
		
		if (isSpawned()) {	
			
			
			 movement.set(direction).scl(GameLevelLogic.speedUpFactor * bgSpeed * delta* (-1));
	         position.add(movement);
	        
	         super.setPosition(position.x, position.y);
	         super.setCollisionRef(position.x, position.y);
	         
	         
	         double y = (playerRef.getY() + 32) - (getY() + 32);
	         double x = (playerRef.getX() + 32) - (getX() + 32);
	         double tethaDeg = Math.toDegrees(Math.atan2(y, x*(-1)));
	        
	         tethaDeg =  tethaDeg * (-1);
	         
	         base.setOriginCenter();
	         base.setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
	         base.setRotation((float) tethaDeg+270);
	         
	         
	         timer += delta * boostFactor;
	         if (timer >= intervalGun) {
	        	 timer = 0;
	        	 double value = Math.sqrt(Math.pow(playerRef.getX() - getX(),2) + Math.pow(playerRef.getY() - getY(),2));
	        	 if (value <= FirstTestGDX.screenHeight) {
	        		 setGun(tethaDeg);	
	        	 }
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
		//setShootingInterval(intervalGun);
		
		if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_1)){
			
			addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX() + (getWidth()/2)  , getY() + (getHeight()/2) , 0 , 0, 16,16);
		
		}else if (type.equals(StaticEnemyTypeEnum.TURRET_LEVEL_2)){
		
			if (sET.equals(ShootEnemyType.SHOOT_SIMPLE)) {
				
				addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX() + (getWidth()/2)  , getY() + (getHeight()/2) , 0 , 0, 16,16);
				
			}else if (sET.equals(ShootEnemyType.SHOOT_DOUBLE)) {
				
				addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX() + (getWidth()/2) - 20 , getY() + (getHeight()/2) , 0 , 0, 16,16);
				addGun(MissileTypeEnum.PROTON_1,(float)angle, this.speed*2, getX() + (getWidth()/2) + 20 , getY() + (getHeight()/2) , 0 , 0, 16,16);
			}
			
		}else if (type.equals(StaticEnemyTypeEnum.TURRET_BOSS)) {
			
			
			addGun(MissileTypeEnum.MISSIL_1,(float)angle, this.speed*2, getX() + (getWidth()/2)-20, getY() + (getHeight()/2) , 0 , 0, 16,32);
			addGun(MissileTypeEnum.MISSIL_1,(float)angle, this.speed*2, getX() + (getWidth()/2)+20, getY() + (getHeight()/2) , 0 , 0, 16,32);
		}
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
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		base.draw(sb);
	}

}
