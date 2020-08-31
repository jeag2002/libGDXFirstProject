package com.mygdx.game.elements.enemies.centroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.ElementDefinitionObject;
import com.mygdx.game.elements.players.ShootPlayerObject;
import com.mygdx.game.enums.ElementDataEnum;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class WatchTowerEnemy  extends ShootPlayerObject implements SpawnObject, Telegraph{

	
	private static final float TRANSITION_BETWEEN_ANIM = 0.05f;
	private static final float INTERVAL_BETWEEN_SHOOT = 0.5f;
	
	private GamePlayScreen gPS;
	private SpawnType typeEnemy;
	
	private float timer;
	private float timer_shoot;
	
	private int indexHullWander = 0;
	
	private boolean isSpawned;
	
	private StateMachine<WatchTowerEnemy, WatchTowerEnemyStateEnum> stateMachine;
	
	private PointLight light;
	
	private float angle;
	
	private SpawnPool pool;
	
	private ElementDefinitionObject eDO;
	
    private Sound sfxShot;
    private float sfxShotVolume; 
	
		
	public WatchTowerEnemy(SpawnPool spawnPool, SpawnType type, World world, GamePlayScreen gPS) {
		super(spawnPool, type, world);
		
		this.gPS = gPS;
		this.typeEnemy = type;
		
		ElementDataEnum eDU = ElementDataEnum.getBySpawnType(type);
    	this.eDO = new ElementDefinitionObject.Builder().setLife(eDU.getLife()).build();
		
		this.pool = spawnPool;
		
		this.timer = 0.0f;
		this.timer_shoot = 0.0f;
		
		this.angle = 0.0f;
		
		this.isSpawned = false;
		
		setShotSound("sounds/laser4.mp3", sfxShotVolume);
		
	}
	
	public void init (RayHandler rayHandler, float iniPositionX, float iniPositionY, float width,  float height,  float angle, float speed, boolean activateGun) {
		
		super.initShootingEngine(SpawnType.MissileEnemy);
		setIniAnimation();
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		setSpeed(0, 0);
		
		setShootingActive(activateGun);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		this.light.setSoftnessLength(0f);
		this.light.attachToBody(this.getBody());
		this.light.setActive(false);
		
		this.setShootingRayHandler(rayHandler);
		super.setShootingActive(false);
		
		stateMachine = new DefaultStateMachine<WatchTowerEnemy, WatchTowerEnemyStateEnum>(this, WatchTowerEnemyStateEnum.SLEEP);	
	}
	
	public void setShotSound(String path, float volume) {
	     sfxShot = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxShotVolume = volume;
	}
	
	
	public GamePlayScreen getGamePlayScreen() {return gPS;}
	public StateMachine<WatchTowerEnemy, WatchTowerEnemyStateEnum> getStateMachine(){return stateMachine;}
	
	@Override
	public boolean handleMessage(Telegram msg) {
		return stateMachine.handleMessage(msg);
	}
	
	
	public void setIA() {
		
		if (stateMachine.getCurrentState().equals(WatchTowerEnemyStateEnum.SLEEP)) {
			light.setActive(false);
			super.setShootingActive(false);		
		}else if (stateMachine.getCurrentState().equals(WatchTowerEnemyStateEnum.ATTACK)) {
			light.setActive(true);
			super.setShootingActive(true);
		}
	}
	
	
	
	
	
	private void setIniAnimation() {
		
		if (typeEnemy.equals(SpawnType.Enemy_03)) {
			Texture[] hullTXT = GameLogicElementInformation.Enemy_04;
			init(hullTXT,indexHullWander);
		}	
	}
	
	
	public void setAnimation() {
		
		if (typeEnemy.equals(SpawnType.Enemy_03)) {
			Texture[] hullTXT = GameLogicElementInformation.Enemy_04;
			init(hullTXT,indexHullWander);
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
		
		if (typeEnemy.equals(SpawnType.Enemy_03)) {
				
			if (timer > TRANSITION_BETWEEN_ANIM) {
				timer = 0.0f;
				indexHullWander++;
				if (indexHullWander >= GameLogicElementInformation.Enemy_04.length) {indexHullWander = 0;}
				setTextureToSpriteByIndex(indexHullWander); 		
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
		angleShooting();
		updateAnimation(delta, boostFactor);
		shootGeneration(delta);
		updateShooting(delta);
		
	}
	
	
	public void updateAnimation(float delta, float boostFactor) {
		AnimationLoop(delta, true);
	}
	
	
	public void angleShooting() {
		if (stateMachine.getCurrentState().equals(WatchTowerEnemyStateEnum.ATTACK)) {
			
	    	Vector2 watchTower = new Vector2();
	    	Vector2 player = new Vector2();
			
	    	watchTower.x = getX();
	    	watchTower.y = getY();
	    	
	    	player.x = this.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX();
	    	player.y = this.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY();
	    	
	    	this.angle = (float) Math.atan2((watchTower.y-player.y),(watchTower.x-player.x));
	    	this.angle = (this.angle*MathUtils.radDeg);
	    	this.angle = this.angle+180;
			
		}
	}

	
	public void updateShooting(float delta) {
		if (stateMachine.getCurrentState().equals(WatchTowerEnemyStateEnum.ATTACK)) {
			super.update(delta);
		}
	}
	
	
	public void shootGeneration(float delta) {
	    	
		 	if (stateMachine.getCurrentState().equals(WatchTowerEnemyStateEnum.ATTACK)) {
		 
		    	timer_shoot += delta;
				float speedGun = 800.0f;
				
				if (timer_shoot  >= INTERVAL_BETWEEN_SHOOT) {	
					float x = (float) ((getX()+getWidth()/2) + 8.0 * Math.cos(this.angle*MathUtils.degRad)); 
					float y = (float) ((getY()+getHeight()/2) + 8.0 * Math.sin(this.angle*MathUtils.degRad)); 
					timer_shoot = 0.0f;
					this.addGun(SpawnType.Missile_Pulse, this.angle, speedGun, x , y, 0, 0, ElementEnum.PULSE.getWidthShow(), ElementEnum.PULSE.getHeightShow());
					this.setShootEvent(true);
					
					sfxShot.play();
				}
		 	}
	}
	
	public ElementDefinitionObject getStatsDynElement() {
		return this.eDO;
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}

	@Override
	public Body getBox2DBody() {
		return super.getBody();
	}

	
	public void dispose() {
		light.remove();
	}

	@Override
	public SpawnType getType() {
		return this.typeEnemy;
	}

	@Override
	public SpawnType getSubType() {
		// TODO Auto-generated method stub
		return this.typeEnemy;
	}
	
}
