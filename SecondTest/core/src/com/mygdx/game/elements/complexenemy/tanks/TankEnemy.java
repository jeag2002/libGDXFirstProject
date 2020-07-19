package com.mygdx.game.elements.complexenemy.tanks;

import java.util.ArrayList;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.elements.DynElementPart;
import com.mygdx.game.elements.complexenemy.ShootTankObject;
import com.mygdx.game.enums.DynamicElementPartType;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.ia.MapGraph;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.NewItem;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class TankEnemy extends ShootTankObject implements SpawnObject{

	
	private GamePlayScreen gPS;
	private SpawnType typeEnemy;
	private ArrayList<DynElementPart> enemy_parts;
	private float timer;
	private boolean isSpawned;
	private PointLight light;
	
	private ElementEnum cannonType;
	
	
	public TankEnemy(SpawnPool spawnPool, SpawnType type, ElementEnum cannonType, World world, GamePlayScreen gPS) {
		super(spawnPool, type, world);
		this.gPS = gPS;
		this.typeEnemy = type;	
		this.cannonType = cannonType;
		this.timer = 0.0f;
		this.isSpawned = false;
		enemy_parts = new ArrayList<DynElementPart>();
	}
	
	
	public void init (MapGraph graph, NewItem posTask, NewItem posObjective, RayHandler rayHandler, float iniPositionX, float iniPositionY, float width,  float height,  float angle, float speed, boolean activateGun) {
		
		super.init(SpawnType.MissileEnemy);
		
		setAnimation();
		setAnimationParts(iniPositionX, iniPositionY, width, height);
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		setSpeed(0, 0);
		
		setShootingActive(activateGun);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);

		this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		this.light.setSoftnessLength(0f);
		this.light.attachToBody(this.getBody());
		
		generateIAPathFinding(graph, posTask, posObjective);
		
	}
	
    public void setAnimation() {
	    	
	    	if (typeEnemy.equals(SpawnType.Enemy_02)) {
	    		Texture[] hullTXT = GameLogicElementInformation.hullEnemy02Text;
	    		init(hullTXT,0);
	    	}
	}
	
    public void setAnimationParts(float iniPositionX, float iniPositionY, float width, float height) {
    	
    	
    	
    	DynElementPart track_left = new DynElementPart(DynamicElementPartType.TRACK_LEFT_ENEMY_2);
    	track_left.init(GameLogicElementInformation.trackEnemyText, 0);
    	track_left.setSize(ElementEnum.TRACK_03.getWidthShow(), ElementEnum.TRACK_03.getHeightShow());
    	track_left.setPosition(iniPositionX+8, iniPositionY);
    	enemy_parts.add(track_left);
    	
    	DynElementPart track_right = new DynElementPart(DynamicElementPartType.TRACK_RIGHT_ENEMY_2);
    	track_right.init(GameLogicElementInformation.trackEnemyText, 0);
    	track_right.setSize(ElementEnum.TRACK_03.getWidthShow(), ElementEnum.TRACK_03.getHeightShow());
    	track_right.setPosition(iniPositionX+48-8, iniPositionY);
    	enemy_parts.add(track_right);
    	
    	
    	DynElementPart gun = new DynElementPart(DynamicElementPartType.GUN_ENEMY_2);
    	Texture gunTXT[] = null;
    	if (cannonType.equals(ElementEnum.GUN_ENEMY2)) {
    		gunTXT = GameLogicElementInformation.cannonPlayer01AText;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_ENEMY2.getWidthShow(), ElementEnum.GUN_ENEMY2.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_ENEMY2.getWidthShow()/2), iniPositionY+8);
    	}
    	enemy_parts.add(gun);
    	
    	
    	DynElementPart exhaust_left = new DynElementPart(DynamicElementPartType.EXHAUST_LEFT_PLAYER);
    	exhaust_left.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_left.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_left.setPosition(iniPositionX+width/2-16, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	enemy_parts.add(exhaust_left);
    	
    	DynElementPart exhaust_right = new DynElementPart(DynamicElementPartType.EXHAUST_RIGHT_PLAYER);
    	exhaust_right.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_right.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_right.setPosition(iniPositionX+width/2, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	enemy_parts.add(exhaust_right);
    }
        
    
	
	
	public void generateIAPathFinding(MapGraph graph, NewItem posTask, NewItem posObjective) {
		super.initIAPathFinding(graph, posTask, posObjective);
	}
	
	

	@Override
	public void setSpawned(boolean spawned) {
		this.isSpawned = spawned;
		
	}

	@Override
	public boolean isSpawned() {
		return this.isSpawned;
	}

	@Override
	public void setPool(SpawnPool pool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(SpawnPool pool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, float boostFactor) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		for(DynElementPart pP: enemy_parts) {
			pP.draw(sb);
		}
	}
	

	@Override
	public void AnimationByMovement(PlayerMovementsEnum movement, float moveStepX, float moveStepY, boolean isAccX,
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
