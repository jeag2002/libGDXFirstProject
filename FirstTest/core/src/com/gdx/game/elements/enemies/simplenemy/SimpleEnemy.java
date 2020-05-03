package com.gdx.game.elements.enemies.simplenemy;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.elements.player.PlayerPart;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.stages.enums.EnemyTypes;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.PlayerPartType;
import com.gdx.game.stages.enums.SpawnType;

public class SimpleEnemy extends ShootObject implements SpawnObject{
	
	
    private static final int INDEX_SHADOW_ENEMY_1 = 0;
    private static final int INDEX_EXHAUST_ENEMY_1 = 1;
   
	
	private boolean spawned;
	private SpawnPool sP;
	
	private static final float intervalGun = 0.35f;
	private EnemyTypes eTypes;
	
	
	private float timer;
	
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    
    private float speed = 0;
    private float angle = 0;
    
    private float animation = 0;
   
    
    ArrayList<PlayerPart> player_parts;
    
	
	public SimpleEnemy(SpawnPool sP, World world) {
		super(sP, world);
		this.sP = sP; 
		this.timer = 0;
	}
	
	public void init(EnemyTypes eTypes, float posX, float posY,  float angle, float speed) {
		super.init(SpawnType.MissileEnemy);
		
		this.speed = speed;
		this.angle = angle;
		this.position.set(posX, posY);
		this.eTypes = eTypes;
		
		player_parts = new ArrayList<PlayerPart>();
		
	    direction.set((float)Math.cos(Math.toRadians(this.angle)), (float)Math.sin(Math.toRadians(this.angle))).nor();
		
	    
	    setReference(this);
		if (eTypes.equals(EnemyTypes.ENEMY_SIMPLE_1)) {
			
			this.eTypes = eTypes;
			Texture[] text_es1 = new Texture[5];
			text_es1[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_01,Texture.class);
			text_es1[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_02,Texture.class);
			text_es1[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_03,Texture.class);
			text_es1[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_04,Texture.class);
			text_es1[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_05,Texture.class);
			
			init(text_es1,0);
			setSize(64, 64);
			setPosition(posX, posY);
			createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
			
			setAnimationPartsEnemySimple1(getX(), getY(), getWidth(), getHeight());
			
		}
		
		setShootingActive(true);
		resetGuns();

	}
	
	
	public void setAnimationPartsEnemySimple1(float iniPositionX, float iniPositionY, float width, float height) {
		
		Texture[] shadowTXT = new Texture[5];
		shadowTXT[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_01,Texture.class);
		shadowTXT[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_02,Texture.class);
		shadowTXT[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_03,Texture.class);
		shadowTXT[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_04,Texture.class);
		shadowTXT[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_05,Texture.class);
		
		//SHADOWS
		PlayerPart shadow = new PlayerPart(PlayerPartType.SHADOW);
		shadow.init(shadowTXT,0);
		shadow.setSize(32, 32);
		shadow.setPosition(iniPositionX-32, iniPositionX-32);
		shadow.setSpeed(0, 0);
		player_parts.add(shadow);
		
		Texture[] exhaustTxt = new Texture[7];
		exhaustTxt[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_01,Texture.class); //(-)
		exhaustTxt[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_02,Texture.class);
		exhaustTxt[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_03,Texture.class); //(-+)
		exhaustTxt[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_04,Texture.class); //(+-)
		exhaustTxt[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_05,Texture.class);
		exhaustTxt[5] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_06,Texture.class); //(-)
		exhaustTxt[6] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustEnemy_1_07,Texture.class);
		
		//EXHAUST
		PlayerPart exhaust = new PlayerPart(PlayerPartType.BOOSTS);
		exhaust.init(exhaustTxt,0);
		exhaust.setSize(10, 32);
		exhaust.setPosition(iniPositionX+(width/2)-5, iniPositionY+52);
		exhaust.setSpeed(0, 0);
		player_parts.add(exhaust);
		
		
		
	}
	
	public void setGun() {
		if (eTypes.equals(EnemyTypes.ENEMY_SIMPLE_1)) {	
			setGunPower(100.0f);
			setShootingInterval(intervalGun);
			addGun(MissileTypeEnum.LASER_1,this.angle, this.speed*2, getX() , getY(), (getWidth()/2), (-1)*getHeight() - 50,10,30);
		}
	}
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		for(PlayerPart pP: player_parts) {
			pP.draw(sb);
		}
		
	}
	
	
	@Override
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	
	@Override
	public boolean isSpawned() {
		return spawned;
	}

	@Override
	public void setPool(SpawnPool pool) {}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		setPosition(0, FirstTestGDX.screenHeight);
	}
	

	@Override
	public void update(float delta, float boostFactor) {	
		
		super.update(delta);
		
		if (eTypes.equals(EnemyTypes.ENEMY_SIMPLE_1)) {	
			AnimationByMovement(PlayerMovements.IDLE, 0, 0, false, false);
			AnimationByMovementPartEnemy1(this.animation);
		}
	
		if (isSpawned()) {	 
			 movement.set(direction).scl(speed * delta * boostFactor);
	         position.add(movement);
	        
	         
	         
	         
	         timer += delta * boostFactor;
	         if (timer >= intervalGun) {
	        	 timer = 0;
	        	 
	        	 setGun();
	         }
	         
	         if (eTypes.equals(EnemyTypes.ENEMY_SIMPLE_1)) {
	        	 movementParts(delta);
	         }
	         
	         
	         
	         super.setPosition(position.x, position.y);
	         super.setCollisionRef(position.x, position.y);
	         
	         if (getX() > FirstTestGDX.screenWidth || 
	             getX() < 0 || 
	             getY() > FirstTestGDX.screenHeight || 
	             getY() < 0) {
	                //Gdx.app.log("[SIMPLEENEMY]","remove SIMPLEENEMY for reach the limit (" + getCode() + ")");
        			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(this.getBody())) {
        				GameElementLogic.toDeletedBodiesWithCollision.add(this.getBody());
        			}
	                
	                
	         }
		}
	}
	
	
	public String toString() {
		return " SimpleEnemy (" + getCode() + ") TYPE (" + this.eTypes.toString() + ")";
	}
	
	
	public void movementParts(float delta) {
		player_parts.get(this.INDEX_SHADOW_ENEMY_1).setPosition(getX()-32, getY()-32);
		player_parts.get(this.INDEX_EXHAUST_ENEMY_1).setPosition(getX()+(getWidth()/2)-5, getY()+52);
	}
	
	
	public void AnimationByMovementPartEnemy1(float animation) {
		player_parts.get(this.INDEX_SHADOW_ENEMY_1).AnimationByMovement(PlayerMovements.UP, 0, 0, false, false);
		player_parts.get(this.INDEX_EXHAUST_ENEMY_1).AnimationByMovement(PlayerMovements.UP, 0, 400, false, true);
	}
	
	
	
	
	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
		
		if (eTypes.equals(EnemyTypes.ENEMY_SIMPLE_1)) {
			this.setTextureToSpriteByIndex(0);
		}
	}

	@Override
	public void AnimationByTime(float delta) {
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
	}

}
