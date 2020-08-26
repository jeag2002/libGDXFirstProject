package com.mygdx.game.elements.players.simpleplayer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.DynElementPart;
import com.mygdx.game.elements.ElementDefinitionObject;
import com.mygdx.game.elements.players.ShootPlayerObject;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.DynamicElementPartType;
import com.mygdx.game.enums.ElementDataEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;


public class Player extends ShootPlayerObject{
	
	
	private static final float INTERVAL_BETWEEN_SHOOT = 0.1f;
	
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
	private PlayerMovementsEnum orientationMOUSEMOVE;
	
	
    private GamePlayScreen gPS;
    private DrawUtils dU;
    private ArrayList<DynElementPart> player_parts;
    private SpawnType type;
    private ElementEnum cannonType;
    
    private Sound sfxShot;
    private float sfxShotVolume; 
    
	private PointLight myLight_point;
	private ConeLight myLight_cone;
    
    private float speed;
    
    private float angle;
    private float angleTurret;
    
    private float time;
    
    private Vector2 movement = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    
    private boolean collDetection;
    
    private ElementDefinitionObject eDO;
    
   
    
    public Player(SpawnPool spawnPool, SpawnType type, ElementEnum cannonType, World world, GamePlayScreen gPS) {
    	super(spawnPool, type, world);
    	
    	ElementDataEnum eDU = ElementDataEnum.getBySpawnType(type);
    	this.eDO = new ElementDefinitionObject.Builder().setLife(eDU.getLife()).setShield(eDU.getShield()).setAmmo(eDU.getAmmo()).setScore(0).build();
    	
    	this.gPS = gPS;
    	
    	this.type = type;
    	this.cannonType = cannonType;
    	
    	this.orientationUP= PlayerMovementsEnum.IDLE;
    	this.orientationDOWN= PlayerMovementsEnum.IDLE;
    	this.orientationLEFT= PlayerMovementsEnum.IDLE;
    	this.orientationRIGHT= PlayerMovementsEnum.IDLE;
    	this.orientationA= PlayerMovementsEnum.IDLE;
    	this.orientationS= PlayerMovementsEnum.IDLE;
    	this.orientationSHOOT= PlayerMovementsEnum.IDLE;
    	this.orientationCHANGE= PlayerMovementsEnum.IDLE;
    	this.orientationMOUSEMOVE = PlayerMovementsEnum.IDLE;
    	
    	
    	this.dU = new DrawUtils();
    	this.player_parts = new ArrayList<DynElementPart>();
    	this.angle = 0.0f;
    	this.angleTurret = 0.0f;
    	
    	this.time = 0.0f;
    	
    	this.collDetection = false;
    	this.sfxShotVolume = 0.97f;
    	
	    setShotSound("sounds/laser4.mp3", sfxShotVolume);
	    super.resetGuns();
		
    	
    	initShootingEngine(SpawnType.MissilePlayer);
    	setShootingActive(true);
    	
    }
    
	
    
    public boolean isCollDetection() {
		return collDetection;
	}

	public void setCollDetection(boolean collDetection) {
		this.collDetection = collDetection;
	}
	

	public void setLocationAndSize(RayHandler rayHandler, float iniPositionX, float iniPositionY, float width, float height) {
		
		setAnimation();
		setAnimationParts(iniPositionX,iniPositionY,width,height);
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		
		position.set(iniPositionX, iniPositionY);	
		direction.set((float)Math.cos(Math.toRadians(angle+270)), (float)Math.sin(Math.toRadians(angle+270))).nor();
		
		setSpeed(0, 0);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		this.setShootingRayHandler(rayHandler);
		
		//LIGHT PLAYER
		//////////////////////////////////////////////////////////////////////////////////////////////
		this.myLight_point = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		this.myLight_cone = new ConeLight(rayHandler, 20, Color.WHITE, 25, 0, 0, 0, 9);
		
		this.myLight_point.setSoftnessLength(1f);
		this.myLight_cone.setSoftnessLength(1f);
		
		this.myLight_point.attachToBody(getBody());
		this.myLight_cone.attachToBody(getBody(), 0, 0, 90.0f);
		//////////////////////////////////////////////////////////////////////////////////////////////
	}
    

    public void setAnimation() {
    	
    	if (type.equals(SpawnType.Player_01)) {
    		Texture[] hullTXT = GameLogicElementInformation.hullPlayer01Text;
    		init(hullTXT,0);
    	}
    }
    
    public void setAnimationParts(float iniPositionX, float iniPositionY, float width, float height) {
    	
    	
    	
    	DynElementPart track_left = new DynElementPart(DynamicElementPartType.TRACK_LEFT_PLAYER);
    	track_left.init(GameLogicElementInformation.trackPlayerText, 0);
    	track_left.setSize(ElementEnum.TRACK_01.getWidthShow(), ElementEnum.TRACK_01.getHeightShow());
    	track_left.setPosition(iniPositionX+8, iniPositionY);
    	player_parts.add(track_left);
    	
    	DynElementPart track_right = new DynElementPart(DynamicElementPartType.TRACK_RIGHT_PLAYER);
    	track_right.init(GameLogicElementInformation.trackPlayerText, 0);
    	track_right.setSize(ElementEnum.TRACK_01.getWidthShow(), ElementEnum.TRACK_01.getHeightShow());
    	track_right.setPosition(iniPositionX+48-8, iniPositionY);
    	player_parts.add(track_right);
    	
    	
    	DynElementPart gun = new DynElementPart(DynamicElementPartType.GUN_PLAYER);
    	Texture gunTXT[] = null;
    	if (cannonType.equals(ElementEnum.GUN_PLAYER_1_A)) {
    		gunTXT = GameLogicElementInformation.cannonPlayer01AText;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_PLAYER_1_A.getWidthShow(), ElementEnum.GUN_PLAYER_1_A.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2), iniPositionY+8);
    	}
    	player_parts.add(gun);
    	
    	
    	DynElementPart exhaust_left = new DynElementPart(DynamicElementPartType.EXHAUST_LEFT_PLAYER);
    	exhaust_left.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_left.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_left.setPosition(iniPositionX+width/2-16, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	player_parts.add(exhaust_left);
    	
    	DynElementPart exhaust_right = new DynElementPart(DynamicElementPartType.EXHAUST_RIGHT_PLAYER);
    	exhaust_right.init(GameLogicElementInformation.ExhaustPlayerText, 6);
    	exhaust_right.setSize(ElementEnum.EXHAUST_01.getWidthShow(), ElementEnum.EXHAUST_01.getHeightShow());
    	exhaust_right.setPosition(iniPositionX+width/2, iniPositionY-ElementEnum.EXHAUST_01.getHeightShow()+8);
    	player_parts.add(exhaust_right);
    }
    
    
    public void update(float delta) {
    	movement(delta);
    	super.update(delta);
    }
    
    
    public void movement(float delta) {
    	
    	   	
		    if (orientationUP.equals(PlayerMovementsEnum.UP)) {
		    		if (Gdx.input.isKeyPressed(Keys.UP)) {
		    			movement(delta, -1);
		    			gPS.getGamePlay().update(getX(),getY());
		    			animatedTracks(delta,true,true); 
		    		    animatedExhaust(delta,true,true);
		    		}
		    }
		    	
		    if (orientationDOWN.equals(PlayerMovementsEnum.DOWN)) {
		    		if (Gdx.input.isKeyPressed(Keys.DOWN)) {	
		    			movement(delta, 1);		    			
		    			gPS.getGamePlay().update(getX(),getY());
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
	    	
	    	if (orientationSHOOT.equals(PlayerMovementsEnum.SHOOT)) {
	    		
	    		if (Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT) && SecondTestGDX.isMouseEnabled) {
	    			shootGeneration(delta);
	    		}
	    	}
	    	
	    	if (orientationMOUSEMOVE.equals(PlayerMovementsEnum.MOUSEMOVED)) {
		    	if ((SecondTestGDX.isMouseEnabled) && (!Gdx.input.isKeyPressed(Keys.A)) && (!Gdx.input.isKeyPressed(Keys.S))) {
		    		rotateTurretMouse();
		    		orientationMOUSEMOVE = PlayerMovementsEnum.IDLE;
		    	}
	    	}
	    	
	    	if (!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
	    		
	    		super.setCollisionVel(0.0f, 0.0f);
	    		animatedExhaust(delta, false, false);
	    	}
	    	
    }
    
    
    public void shootGeneration(float delta) {
    	
    	time += delta;
		float speedGun = 800.0f;
		
		if (time  >= INTERVAL_BETWEEN_SHOOT) {
			
			float shootAngle = 0.0f;
			
			if (SecondTestGDX.isMouseEnabled) {shootAngle = angleTurret + 90;} else {shootAngle = angle+angleTurret+90;}
			
			float x = (float) ((getX() + getWidth()/2 - ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2) + 50.0 * Math.cos(shootAngle*MathUtils.degRad)); 
			float y = (float) ((getY()) + 50.0 * Math.sin(shootAngle*MathUtils.degRad)); 
			
			time = 0.0f;
			this.addGun(SpawnType.Missile_Laser, shootAngle, speedGun, x , y, 0, 0, ElementEnum.LASER.getWidthShow(), ElementEnum.LASER.getHeightShow());
			this.setShootEvent(true);
			sfxShot.play();
		
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
    	super.setCollisionAngleRef(getX(), getY(), angle*MathUtils.degRad);
    	
    	//DISABLED ROTATION OF CAMERA.
    	
    	if (SecondTestGDX.rotateCameraWithPlayer) {
    		testingCameraRotation();
    	}
    	
    	player_parts.get(INDEX_TRACK_LEFT).rotate(angle,24,getHeight()/2);
    	player_parts.get(INDEX_TRACK_RIGHT).rotate(angle,-8,getHeight()/2); 	
    	
    	
    	if (!SecondTestGDX.isMouseEnabled) {
    		player_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	}else {
    		player_parts.get(INDEX_GUN).rotate(angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	}
    	
    	player_parts.get(INDEX_EXHAUST_LEFT).rotate(angle, 16, 56);
    	player_parts.get(INDEX_EXHAUST_RIGHT).rotate(angle, 0, 56);
    	
    }
    
    
    public void rotateTurretMouse() {
    	
    	Vector2 gunPosition = new Vector2();
    	Vector2 mousePosition = new Vector2();
    	
    	gunPosition.x = player_parts.get(INDEX_GUN).getX();
    	gunPosition.y = player_parts.get(INDEX_GUN).getY();
    	
    	mousePosition.x = Gdx.input.getX();
    	mousePosition.y = Gdx.input.getY();
    	
    	
    	float originX = this.gPS.getGamePlay().getCamera().position.x - SecondTestGDX.screenWidth/2;
    	float originY = this.gPS.getGamePlay().getCamera().position.y - SecondTestGDX.screenHeight/2; 
    	
    	gunPosition.x = gunPosition.x;
    	gunPosition.y = gunPosition.y;
    	
    	mousePosition.x += originX;
    	mousePosition.y += originY;
    	
    	
    	angleTurret = (float) Math.atan2((gunPosition.y-mousePosition.y),(gunPosition.x-mousePosition.x));
    	//angleTurret = (angleTurret*MathUtils.radDeg + 270)*(-1);
    	angleTurret = (-1)*(angleTurret*MathUtils.radDeg - 90);
    	
    	player_parts.get(INDEX_GUN).rotate(angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );		
    			
    }
    
    
    
    public void rotateTurret() {
    	
    	if (!SecondTestGDX.isMouseEnabled) {
    		player_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	}else {
    		player_parts.get(INDEX_GUN).rotate(angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	}
    }
    
    
    public void movement(float delta, float index) {
    	 movement.set(direction).scl(GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta * index*2);
         position.add(movement);
         
         super.setCollisionVel(movement.x, movement.y);
         Vector2 posRelative = super.getPositionFromBodyToPixel();
         super.setPosition(posRelative.x, posRelative.y);
         
         
         
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
    public void actionPlayerSHOOT(PlayerMovementsEnum orientation) {this.orientationSHOOT = orientation;}
    public void actionPlayerMOUSEMOVE(PlayerMovementsEnum orientation) {this.orientationMOUSEMOVE = orientation;}
    
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
		myLight_point.remove();
		myLight_cone.remove();
	}
	
	
	public void setShotSound(String path, float volume) {
	     sfxShot = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxShotVolume = volume;
	}

	
	public ElementDefinitionObject getStatsDynElement() {
		return this.eDO;
	}
	
	//ROTATION CAMERA
	public void testingCameraRotation() {
    	
    	OrthographicCamera cameraCobayera = this.gPS.getGamePlay().getCamera();
    	float anglePlayerCobayero = this.angle;
    	float angleCameraCobayera = (float)Math.atan2(cameraCobayera.up.x, cameraCobayera.up.y)*MathUtils.radiansToDegrees;
    	angleCameraCobayera = (-1)*angleCameraCobayera + 180;
    	this.gPS.getGamePlay().getCamera().rotate((angleCameraCobayera-anglePlayerCobayero)+180);
    	
    }

	


	
	
	

}
