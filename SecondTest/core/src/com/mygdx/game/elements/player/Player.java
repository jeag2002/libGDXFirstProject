package com.mygdx.game.elements.player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.ShootObject;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.PlayerPartType;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;

public class Player extends ShootObject{
	
	
	private static final int INDEX_TRACK_LEFT = 0;
	private static final int INDEX_TRACK_RIGHT = 1;
	private static final int INDEX_GUN = 2;
	
	private static final int INDEX_EXHAUST_LEFT = 3;
	private static final int INDEX_EXHAUST_RIGHT = 4;
	
	private PlayerMovementsEnum orientationUP;
	private PlayerMovementsEnum orientationDOWN;
	private PlayerMovementsEnum orientationLEFT;
	private PlayerMovementsEnum orientationRIGHT;
	private PlayerMovementsEnum orientationA;
	private PlayerMovementsEnum orientationS;
	private PlayerMovementsEnum orientationSHOOT;
	private PlayerMovementsEnum orientationCHANGE;
	
	//private PlayerMovementsEnum orientation;
	
	
    private GamePlayScreen gPS;
    private DrawUtils dU;
    private ArrayList<PlayerPart> player_parts;
    private SpawnType type;
    private ElementEnum cannonType;
    
    private float speed;
    
    private float angle;
    private float angleTurret;
    
    private Queue<PlayerMovementsEnum> movements;
    
    
    private Vector2 movement = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    
    
    public Player(SpawnPool spawnPool, SpawnType type, ElementEnum cannonType, World world, GamePlayScreen gPS) {
    	super(spawnPool, world);
    	
    	this.gPS = gPS;
    	
    	this.type = type;
    	this.cannonType = cannonType;
    	//this.orientation = PlayerMovementsEnum.IDLE;
    	
    	this.orientationUP= PlayerMovementsEnum.IDLE;
    	this.orientationDOWN= PlayerMovementsEnum.IDLE;
    	this.orientationLEFT= PlayerMovementsEnum.IDLE;
    	this.orientationRIGHT= PlayerMovementsEnum.IDLE;
    	this.orientationA= PlayerMovementsEnum.IDLE;
    	this.orientationS= PlayerMovementsEnum.IDLE;
    	this.orientationSHOOT= PlayerMovementsEnum.IDLE;
    	this.orientationCHANGE= PlayerMovementsEnum.IDLE;
    	
    	
    	this.dU = new DrawUtils();
    	this.player_parts = new ArrayList<PlayerPart>();
    	this.angle = 0.0f;
    	this.angleTurret = 0.0f;
    	
    	this.movements = new LinkedList<>(); 
    	
    	setShootingActive(true);
    	
    }
    
	
    public void setLocationAndSize(float iniPositionX, float iniPositionY, float width, float height) {
		
		setAnimation();
		setAnimationParts(iniPositionX,iniPositionY,width,height);
		
		setReference(this);
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		
		position.set(iniPositionX, iniPositionY);	
		direction.set((float)Math.cos(Math.toRadians(angle+270)), (float)Math.sin(Math.toRadians(angle+270))).nor();
		
		setSpeed(0, 0);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
	}
    
    public void setAnimation() {
    	
    	if (type.equals(SpawnType.Player_01)) {
    		Texture[] hullTXT = GameLogicElementInformation.hullPlayer01Text;
    		init(hullTXT,0);
    	}
    }
    
    public void setAnimationParts(float iniPositionX, float iniPositionY, float width, float height) {
    	
    	
    	
    	PlayerPart track_left = new PlayerPart(PlayerPartType.TRACK_LEFT);
    	track_left.init(GameLogicElementInformation.trackPlayerText, 0);
    	track_left.setSize(ElementEnum.TRACK_01.getWidthShow(), ElementEnum.TRACK_01.getHeightShow());
    	track_left.setPosition(iniPositionX+8, iniPositionY);
    	player_parts.add(track_left);
    	
    	PlayerPart track_right = new PlayerPart(PlayerPartType.TRACK_RIGHT);
    	track_right.init(GameLogicElementInformation.trackPlayerText, 0);
    	track_right.setSize(ElementEnum.TRACK_01.getWidthShow(), ElementEnum.TRACK_01.getHeightShow());
    	track_right.setPosition(iniPositionX+48-8, iniPositionY);
    	player_parts.add(track_right);
    	
    	
    	PlayerPart gun = new PlayerPart(PlayerPartType.GUN);
    	Texture gunTXT[] = null;
    	if (cannonType.equals(ElementEnum.GUN_PLAYER_1_A)) {
    		gunTXT = GameLogicElementInformation.cannonPlayer01AText;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_PLAYER_1_A.getWidthShow(), ElementEnum.GUN_PLAYER_1_A.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2), iniPositionY+8);
    	}
    	player_parts.add(gun);
    	
    	
    	PlayerPart exhaust_left = new PlayerPart(PlayerPartType.EXHAUST_LEFT);
    	exhaust_left.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_left.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_left.setPosition(iniPositionX+width/2-16, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	player_parts.add(exhaust_left);
    	
    	PlayerPart exhaust_right = new PlayerPart(PlayerPartType.EXHAUST_RIGHT);
    	exhaust_right.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_right.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_right.setPosition(iniPositionX+width/2, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	player_parts.add(exhaust_right);
    	
    	
    	
    	
    }
    
    
    public void update(float delta) {
    	movement(delta);
    	collision();
    	super.update(delta);
    }
    
    
    public void movement(float delta) {
    	
    	
	    	if (orientationUP.equals(PlayerMovementsEnum.UP)) {
	    		if (Gdx.input.isKeyPressed(Keys.UP)) {
	    			movement(delta, -1);
	    			animatedTracks(delta,true,true); 
	    		    animatedExhaust(delta,true,true);
	    		}

	    	}
	    	
	    	if (orientationDOWN.equals(PlayerMovementsEnum.DOWN)) {
	    		if (Gdx.input.isKeyPressed(Keys.DOWN)) {	
	    			movement(delta, 1);	
	    			animatedTracks(delta, true, true);
	    			animatedExhaust(delta,false,false);
	    		}

	    	}
	    	
	    	if (orientationLEFT.equals(PlayerMovementsEnum.LEFT)) {	
	    		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
	    			angle +=  GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta;
	    			rotate();
	    			animatedTracks(delta, true, false);
	    			animatedExhaust(delta,false,true);
	    		}

	    	}
	    	
	    	if (orientationRIGHT.equals(PlayerMovementsEnum.RIGHT)) {
	    		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    			angle -=  GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta;
	    			rotate();
	    			animatedTracks(delta, false, true);
	    			animatedExhaust(delta,true, false);
	    		}

	    	}
	    	
	    	if (orientationS.equals(PlayerMovementsEnum.TURRETCLOCKWISE)) {
	    		if (Gdx.input.isKeyPressed(Keys.S)) {
	    			this.angleTurret -= GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta;
	    			rotateTurret();
	    		}

	    	}
	    	
	    	if (orientationA.equals(PlayerMovementsEnum.TURRETANTICLOCKWISE)) {
	    		
	    		if (Gdx.input.isKeyPressed(Keys.A)) {
	    			this.angleTurret += GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta;
	    			rotateTurret();
	    		}
	    	}
	    	
	    	
	    	if (!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    		animatedExhaust(delta, false, false);
	    	}
	    	
    }
    
    
    public void animatedTracks(float delta, boolean trackleft, boolean trackright) {
    	player_parts.get(INDEX_TRACK_LEFT).AnimationLoop(delta, trackleft);
    	player_parts.get(INDEX_TRACK_RIGHT).AnimationLoop(delta, trackright);
    }
    
    public void animatedExhaust(float delta, boolean trackleft, boolean trackright) {
    	player_parts.get(INDEX_EXHAUST_LEFT).AnimationLoop(delta, trackleft);
    	player_parts.get(INDEX_EXHAUST_RIGHT).AnimationLoop(delta, trackright);
    }
    
    
    
    public void rotate() {
    	super.rotate(angle);
    	direction.set((float)Math.cos(Math.toRadians(angle+270)), (float)Math.sin(Math.toRadians(angle+270))).nor();
    	player_parts.get(INDEX_TRACK_LEFT).rotate(angle,24,getHeight()/2);
    	player_parts.get(INDEX_TRACK_RIGHT).rotate(angle,-8,getHeight()/2); 	
    	player_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	
    	player_parts.get(INDEX_EXHAUST_LEFT).rotate(angle, 16, 56);
    	player_parts.get(INDEX_EXHAUST_RIGHT).rotate(angle, 0, 56);
    	
    }
    
    public void rotateTurret() {
    	player_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    }
    
    
    public void movement(float delta, float index) {
    	 movement.set(direction).scl(GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta * index);
         position.add(movement);
         super.setPosition(position.x, position.y);
         super.setCollisionRef(position.x, position.y);
         
         player_parts.get(INDEX_TRACK_LEFT).setPosition(getX()+8, getY());
         player_parts.get(INDEX_TRACK_RIGHT).setPosition(getX()+40, getY());
         player_parts.get(INDEX_GUN).setPosition(getX()+(getWidth()/2)-(ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2), getY()+8);
         player_parts.get(INDEX_EXHAUST_LEFT).setPosition(getX()+(getWidth()/2)-16, getY()-ElementEnum.EXHAUST_01.getHeightShow()+8);
         player_parts.get(INDEX_EXHAUST_RIGHT).setPosition(getX()+(getWidth()/2), getY()-ElementEnum.EXHAUST_01.getHeightShow()+8);
         
    }
    
    
    
    public void collision() {
    	setCollisionRef(getX(),getY());
    }
    
    
    public void actionPlayerUP(PlayerMovementsEnum orientation) {this.orientationUP = orientation;}
    public void actionPlayerDOWN(PlayerMovementsEnum orientation) {this.orientationDOWN = orientation;}
    public void actionPlayerLEFT(PlayerMovementsEnum orientation) {this.orientationLEFT = orientation;}
    public void actionPlayerRIGHT(PlayerMovementsEnum orientation) {this.orientationRIGHT = orientation;}
    public void actionPlayerA(PlayerMovementsEnum orientation) {this.orientationA = orientation;}
    public void actionPlayerS(PlayerMovementsEnum orientation) {this.orientationS = orientation;}
    
    public void draw(SpriteBatch sb) {
    	player_parts.get(INDEX_TRACK_LEFT).draw(sb);
    	player_parts.get(INDEX_TRACK_RIGHT).draw(sb);
    	player_parts.get(INDEX_EXHAUST_LEFT).draw(sb);
    	player_parts.get(INDEX_EXHAUST_RIGHT).draw(sb);
    	/*HULL*/super.draw(sb);
    	player_parts.get(INDEX_GUN).draw(sb);
    	
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
	
	public void dispose() {
		player_parts.clear();
	}
	
	

}
