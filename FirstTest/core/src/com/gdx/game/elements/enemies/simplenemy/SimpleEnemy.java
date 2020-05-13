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
import com.gdx.game.stages.enums.DynamicEnemyTypeEnum;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.PlayerPartType;
import com.gdx.game.stages.enums.SpawnType;

public class SimpleEnemy extends ShootObject implements SpawnObject{
	
	
    private static final int INDEX_SHADOW_ENEMY_1 = 0;
    private static final int INDEX_EXHAUST_ENEMY_1 = 1;
   
	
	private boolean spawned;
	private SpawnPool sP;
	
	private static final float intervalGun = 0.4f;
	private DynamicEnemyTypeEnum eTypes;
	
	
	private float timer;
	
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    
    private float speed = 0;
    private float angle = 0;
    
    private float animation = 0;
    
    private boolean activateGun;
   
    
    ArrayList<PlayerPart> player_parts;
    
	
	public SimpleEnemy(SpawnPool sP, World world) {
		super(sP, world);
		this.sP = sP; 
		this.timer = 0;
		this.activateGun = true;
	}
	
	public void init(DynamicEnemyTypeEnum eTypes, float posX, float posY,  float angle, float speed, boolean activateGun) {
		super.init(SpawnType.MissileEnemy);
		
		this.speed = speed;
		this.angle = angle;
		this.position.set(posX, posY);
		this.eTypes = eTypes;
		this.activateGun = activateGun;
		
		player_parts = new ArrayList<PlayerPart>();
		
	    direction.set((float)Math.cos(Math.toRadians(this.angle)), (float)Math.sin(Math.toRadians(this.angle))).nor();
		
	    
	    setReference(this);
	    Texture[] text_es = new Texture[5];
	    
		if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3) ) {
			
			this.eTypes = eTypes;
			text_es[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_1_01,Texture.class);
			text_es[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_1_02,Texture.class);
			text_es[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_1_03,Texture.class);
			text_es[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_1_04,Texture.class);
			text_es[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_1_05,Texture.class);
			
		}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2)) {
		
			text_es[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_2_01,Texture.class);
			text_es[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_2_02,Texture.class);
			text_es[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_2_03,Texture.class);
			text_es[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_2_04,Texture.class);
			text_es[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_1_2_05,Texture.class);
		
		}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2)) {
			this.eTypes = eTypes;
			text_es[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_1_01,Texture.class);
			text_es[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_1_02,Texture.class);
			text_es[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_1_03,Texture.class);
			text_es[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_1_04,Texture.class);
			text_es[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_1_05,Texture.class);
			
		}else if  (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2)) {
			text_es[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_2_01,Texture.class);
			text_es[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_2_02,Texture.class);
			text_es[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_2_03,Texture.class);
			text_es[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_2_04,Texture.class);
			text_es[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgEnemy_2_2_05,Texture.class);
		}
		
		init(text_es,0);
		setSize(64, 64);
		setPosition(posX, posY);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		setAnimationPartsEnemySimple1(getX(), getY(), getWidth(), getHeight());
		
		
		setShootingActive(true);
		resetGuns();

	}
	
	
	public void setAnimationPartsEnemySimple1(float iniPositionX, float iniPositionY, float width, float height) {
		
		Texture[] shadowTXT = new Texture[5];
		
		if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3)) {
		
			shadowTXT[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_01,Texture.class);
			shadowTXT[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_02,Texture.class);
			shadowTXT[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_03,Texture.class);
			shadowTXT[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_04,Texture.class);
			shadowTXT[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_1_05,Texture.class);
		
		} else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2)) {
			
			shadowTXT[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_2_01,Texture.class);
			shadowTXT[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_2_02,Texture.class);
			shadowTXT[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_2_03,Texture.class);
			shadowTXT[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_2_04,Texture.class);
			shadowTXT[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowEnemy_2_05,Texture.class);
			
		}
		
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
		
			setGunPower(100.0f);
			//setShootingInterval(intervalGun);
			
			if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1)) {	
				
				addGun(MissileTypeEnum.LASER_1,this.angle, this.speed*1.5f, getX() , getY(), (getWidth()/2), (-1)*getHeight(),10,30);
				
			}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2)){
				
				addGun(MissileTypeEnum.LASER_1,this.angle, this.speed*1.5f, getX() , getY(), (getWidth()/2)-20, (-1)*getHeight(),10,30);
				addGun(MissileTypeEnum.LASER_1,this.angle, this.speed*1.5f, getX() , getY(), (getWidth()/2)+20, (-1)*getHeight(),10,30);
			
			}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3)){
				
				addGun(MissileTypeEnum.MISSIL_1,this.angle, this.speed*1.5f, getX() , getY(), (getWidth()/2), (-1)*getHeight(),10,30);
				
			}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2)) {
				
				addGun(MissileTypeEnum.PROTON_1, 90.0f, this.speed*1.5f, getX() , getY() , (getWidth()/2), (-1)*getHeight(),16,16);
				
			}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2)){
				
				addGun(MissileTypeEnum.PROTON_1, 90.0f, this.speed*1.5f, getX(), getY(), (getWidth()/2)-20, (-1)*getHeight(), 16,16);
				addGun(MissileTypeEnum.PROTON_1, 90.0f, this.speed*1.5f, getX(), getY(), (getWidth()/2)+20, (-1)*getHeight(), 16,16);
				
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
		
		AnimationByMovement(PlayerMovements.IDLE, 0, 0, false, false);
		if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3)) {	
			AnimationByMovementPartEnemy1(this.animation);
		}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2)) {
			AnimationByMovementPartEnemy2(this.animation);
		}
	
		if (isSpawned()) {	 
			 movement.set(direction).scl(speed * delta * boostFactor);
	         position.add(movement);
	        
	         
	         if (activateGun) {
		         timer += delta * boostFactor;
		         if (timer >= intervalGun) {
		        	 timer = 0;
		        	 setGun();
		         }
	         }
	         
	         
	         if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1) || 
	             eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2) ||   
	             eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2) ||
	             eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2) ||
	             eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3)) {
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
	
	public void AnimationByMovementPartEnemy2(float animation) {
		
		if (angle == 45) {player_parts.get(this.INDEX_SHADOW_ENEMY_1).AnimationByMovement(PlayerMovements.LEFT, 400, 0, true, false);}
		else if (angle == 135) {player_parts.get(this.INDEX_SHADOW_ENEMY_1).AnimationByMovement(PlayerMovements.RIGHT, 400, 0, true, false);}
		
		player_parts.get(this.INDEX_EXHAUST_ENEMY_1).AnimationByMovement(PlayerMovements.UP, 0, 400, false, true);
	}
	
	
	
	
	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
		
		if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_2) ||  eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_1_LEVEL_3) ) {
			this.setTextureToSpriteByIndex(0);
		}else if (eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2) || eTypes.equals(DynamicEnemyTypeEnum.ENEMY_SIMPLE_2_LEVEL_2) ) {
			if (angle == 45) {this.setTextureToSpriteByIndex(2);} //RIGHT
			else if (angle == 135) {this.setTextureToSpriteByIndex(4);} //LEFT
		}
	}

	@Override
	public void AnimationByTime(float delta) {
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
	}

}
