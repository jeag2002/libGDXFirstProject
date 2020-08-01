package com.mygdx.game.elements.enemies.drons;

import java.util.ArrayList;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.DynElementPart;
import com.mygdx.game.elements.enemies.IASteeringBehaviourEnemiesObject;
import com.mygdx.game.elements.enemies.ShootEnemiesObject;
import com.mygdx.game.enums.DynamicElementPartType;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.SteeringPresets;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class SimpleEnemy  extends ShootEnemiesObject implements SpawnObject, Telegraph{

	
	private static final float TRANSITION_BETWEEN_ANIM = 0.05f;
	private static final float INTERVAL_BETWEEN_SHOOT = 0.25f;
	
	private GamePlayScreen gPS;
	private SpawnType typeEnemy;
	
	private ArrayList<DynElementPart> enemy_parts;
	private float timer;
	private float timer_shoot;
	
	private int indexHullWander = 0;
	private int indexHullAttack = 0;
	
	private boolean isSpawned;
	
	private StateMachine<SimpleEnemy, SimpleEnemyStateEnum> stateMachine;
	
	private PointLight light;
	
	private float angle;
	
	private SpawnPool pool;
	
		
	public SimpleEnemy(SpawnPool spawnPool, SpawnType type, World world, GamePlayScreen gPS) {
		super(spawnPool, type, world);
		
		this.gPS = gPS;
		this.typeEnemy = type;
		
		this.pool = spawnPool;
		
		this.timer = 0.0f;
		this.timer_shoot = 0.0f;
		
		this.angle = 0.0f;
		
		this.isSpawned = false;
		
		enemy_parts = new ArrayList<DynElementPart>();
		
	}
	
	public void init (RayHandler rayHandler, float iniPositionX, float iniPositionY, float width,  float height,  float angle, float speed, boolean activateGun) {
		
		super.init(SpawnType.MissileEnemy);
		setIniAnimation();
		setIniAnimationParts();
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		setSpeed(0, 0);
		
		setShootingActive(activateGun);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		this.light.setSoftnessLength(0f);
		this.light.attachToBody(this.getBody());
		
		this.setShootingRayHandler(rayHandler);
		super.setShootingActive(false);
		
		stateMachine = new DefaultStateMachine<SimpleEnemy, SimpleEnemyStateEnum>(this, SimpleEnemyStateEnum.SLEEP);
		setIA();
		
	}
	
	

	
	public GamePlayScreen getGamePlayScreen() {return gPS;}
	public StateMachine<SimpleEnemy, SimpleEnemyStateEnum> getStateMachine(){return stateMachine;}
	
	@Override
	public boolean handleMessage(Telegram msg) {
		return stateMachine.handleMessage(msg);
	}
	
	
	public void setIA() {
		
		IASteeringBehaviourEnemiesObject entity = new IASteeringBehaviourEnemiesObject();
		entity.iniBehaviour(this.getBody(), 10);
		
		IASteeringBehaviourEnemiesObject target = new IASteeringBehaviourEnemiesObject();
		target.iniBehaviour(this.gPS.getGamePlay().getGameLogic().getPlayer().getBody(), 10);
		
		if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SLEEP)) {
			
			this.setMaxLinearSpeed(0);
			this.setMaxLinearAcceleration(0);
			this.setMaxAngularAcceleration(0);
			this.setMaxAngularSpeed(0);
			
			this.getBody().setLinearVelocity(0.0f, 0.0f);
			this.getBody().setAngularVelocity(0.0f);	
			light.setActive(false);
			super.setShootingActive(false);
			
			this.setBehavior(null);
		}else if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SEEK)) {
			
			this.setBehavior(SteeringPresets.arrive(entity, target, true));
			light.setActive(true);
			super.setShootingActive(false);
		
		}else if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.ATTACK)) {
			
			this.setMaxLinearSpeed(0);
			this.setMaxLinearAcceleration(0);
			this.setMaxAngularAcceleration(0);
			this.setMaxAngularSpeed(0);
			
			this.getBody().setLinearVelocity(0.0f, 0.0f);
			this.getBody().setAngularVelocity(0.0f);			
			
			this.setBehavior(null);
			light.setActive(true);
			super.setShootingActive(true);
		}
	}
	
	
	
	
	
	private void setIniAnimation() {
		
		if (typeEnemy.equals(SpawnType.Enemy_01)) {
			Texture[] hullTXT = GameLogicElementInformation.Enemy_01_Wander;
			init(hullTXT,indexHullWander);
		}	
	}
	
	
	public void setAnimation() {
		
		if (typeEnemy.equals(SpawnType.Enemy_01)) {
			if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SLEEP) || stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SEEK)) {
				Texture[] hullTXT = GameLogicElementInformation.Enemy_01_Wander;
				init(hullTXT,indexHullWander);
			}else if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.ATTACK)) {
				Texture[] hullTXT = GameLogicElementInformation.Enemy_01_Body;
				//init(hullTXT,indexHullAttack);
				init(hullTXT,indexHullWander);
			}
		}
	}
	
	private void setIniAnimationParts() {
		if (typeEnemy.equals(SpawnType.Enemy_01)) {
			
			DynElementPart enemy_1_left_wing = new DynElementPart(DynamicElementPartType.LEFT_WING_ENEMY_1);
			enemy_1_left_wing.init(GameLogicElementInformation.Enemy_01_LeftWing, 0);
			enemy_1_left_wing.setSize(ElementEnum.ENEMY_1_LEFT_WING.getWidthShow(), ElementEnum.ENEMY_1_LEFT_WING.getHeightShow());
	    	enemy_parts.add(enemy_1_left_wing);
			
	    	DynElementPart enemy_1_right_wing = new DynElementPart(DynamicElementPartType.RIGHT_WING_ENEMY_1);
			enemy_1_right_wing.init(GameLogicElementInformation.Enemy_01_RightWing, 0);
			enemy_1_right_wing.setSize(ElementEnum.ENEMY_1_RIGHT_WING.getWidthShow(), ElementEnum.ENEMY_1_RIGHT_WING.getHeightShow());
	    	enemy_parts.add(enemy_1_left_wing);
	 
		}
	}
	
	
	@Override
	public void AnimationByMovement(PlayerMovementsEnum movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
	}

	@Override
	public void AnimationByTime(float delta) {
		
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
		
		timer += delta;
		
		if (typeEnemy.equals(SpawnType.Enemy_01)) {
				
			if (timer > TRANSITION_BETWEEN_ANIM) {
				timer = 0.0f;
				indexHullWander++;
				if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SLEEP) || stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.SEEK)) {
					if (indexHullWander >= GameLogicElementInformation.Enemy_01_Wander.length) {indexHullWander = 0;}
					setTextureToSpriteByIndex(indexHullWander); 
				}else if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.ATTACK)) {
					//if (indexHullAttack >= GameLogicElementInformation.Enemy_01_Body.length) {indexHullWander = 0;}
					//setTextureToSpriteByIndex(indexHullAttack);
					if (indexHullWander >= GameLogicElementInformation.Enemy_01_Wander.length) {indexHullWander = 0;}
					setTextureToSpriteByIndex(indexHullWander);
					
				}			
			}
		}		
	}

	
	
	@Override
	public void setSpawned(boolean spawned) {	
		this.isSpawned = spawned;
	}

	
	@Override
	public boolean isSpawned() {
		return isSpawned;
	}

	
	@Override
	public void setPool(SpawnPool pool) {		
		this.pool = pool;
	}

	
	@Override
	public void kill(SpawnPool pool) {
		dispose();
		super.setPosition(SecondTestGDX.screenWidth, 0);
	}

	
	@Override
	public void update(float delta, float boostFactor) {
		
		stateMachine.update();
		setIA();
		
		super.updateBehaviour(delta);
		updatePosition();
		updateAnimation(delta, boostFactor);
		rotateAnimation();
		
		shootGeneration(delta);
		updateShooting(delta);
	}
	
	
	public void updatePosition() {
		float posX = this.getXColl() * GameLogicInformation.PIXELS_TO_METERS - getWidth()/2;
		float posY = this.getYColl() * GameLogicInformation.PIXELS_TO_METERS - getHeight()/2;
		super.setPosition(posX, posY);
	}
	
	
	public void updateAnimation(float delta, float boostFactor) {
		AnimationLoop(delta, true);
	}
	
	public void rotateAnimation() {
		
		this.angle = this.getBody().getAngle() * MathUtils.radiansToDegrees;			
		this.angle += 90;
		getSprite().setOriginCenter();
		getSprite().setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
		getSprite().setRotation(this.angle);
	}
	
	public void updateShooting(float delta) {
		if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.ATTACK)) {
			super.update(delta);
		}
	}
	
	
	public void shootGeneration(float delta) {
	    	
		 	if (stateMachine.getCurrentState().equals(SimpleEnemyStateEnum.ATTACK)) {
		 
		    	timer_shoot += delta;
				float speedGun = 800.0f;
				
				if (timer_shoot  >= INTERVAL_BETWEEN_SHOOT) {
					
					float x = (float) ((getX()+16) + 8.0 * Math.cos(this.angle*MathUtils.degRad)); 
					float y = (float) ((getY()) + 8.0 * Math.sin(this.angle*MathUtils.degRad)); 
					timer_shoot = 0.0f;
					this.addGun(SpawnType.Missile_Plasma, this.angle, speedGun, x , y, 0, 0, ElementEnum.PLASMA.getWidthShow(), ElementEnum.PLASMA.getHeightShow());
					this.setShootEvent(true);
				}
			
		 	}
	 }
	
	
	
	
	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		for(DynElementPart pP: enemy_parts) {
			pP.draw(sb);
		}
	}

	@Override
	public Body getBox2DBody() {
		return super.getBody();
	}

	
	public void dispose() {
		light.remove();
		//light.dispose();
		enemy_parts.clear();
	}
	
}
