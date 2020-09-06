package com.mygdx.game.elements.enemies.special.tanks;

import java.util.ArrayList;

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
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.DynElementPart;
import com.mygdx.game.elements.ElementDefinitionObject;
import com.mygdx.game.elements.enemies.drons.SimpleEnemy;
import com.mygdx.game.elements.enemies.drons.SimpleEnemyStateEnum;
import com.mygdx.game.elements.enemies.special.ShootTankObject;
import com.mygdx.game.enums.DynamicElementPartType;
import com.mygdx.game.enums.ElementDataEnum;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.ia.MapGraph;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.NewItem;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

public class TankEnemy extends ShootTankObject implements SpawnObject, Telegraph{
	
	private static final int INDEX_TRACK_LEFT = 0;
	private static final int INDEX_TRACK_RIGHT = 1;
	private static final int INDEX_GUN = 2;
	
	private static final int INDEX_EXHAUST_LEFT = 3;
	private static final int INDEX_EXHAUST_RIGHT = 4;
	
	private static final int NODE_ARRIVE = 70;
	
	private static final float INTERVAL_BETWEEN_SHOOT = 1f;
	
	
	private static final float DELAY_1 = 1;
	private static final float DELAY_2 = 2;
	private static final float DELAY_3 = 3;

	
	private GamePlayScreen gPS;
	
	private SpawnType typeEnemy;
	private SpawnType subTypeEnemy;
	
	private ArrayList<DynElementPart> enemy_parts;
	private float timer;
	
	private boolean isSpawned;
	
	private float angle;
	private float angleTurret;
	
	private float initDelay;
	private float timer_delay;
	
	private PointLight light;
	private ConeLight cone;
	
	
    private Vector2 movement = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    
	private NewItem previousNode;
	private NewItem objective;
	
	private SpawnPool pool;
	
	private ElementDefinitionObject eDO;
	
    private Sound sfxShot;
    private float sfxShotVolume; 
    
    private Sound sfxMissile;
    private float sfxMissileVolume;


	private StateMachine<TankEnemy, TankEnemyStateEnum> stateMachine;
	
	
	public TankEnemy(SpawnPool spawnPool, SpawnType type, World world, GamePlayScreen gPS) {
		super(spawnPool, type, world);
		
		
		
		
		this.gPS = gPS;
		this.typeEnemy = type;	
		this.timer = 0.0f;
		this.timer_delay = 0.0f;
		this.isSpawned = false;
		this.previousNode = new NewItem();
		enemy_parts = new ArrayList<DynElementPart>();
		
		this.sfxShotVolume = 0.97f;
		this.sfxMissileVolume = 0.25f;
    	
		
		setShotSound("sounds/laser4.mp3", sfxShotVolume);
		setMissileSound("sounds/Missile.mp3", sfxMissileVolume);
		
	}
	
	
	public void init (MapGraph graph, SpawnType subType, NewItem posTank, NewItem posObjective, RayHandler rayHandler, float iniPositionX, float iniPositionY, float width,  float height,  float angle, float speed, boolean activateGun) {
		
		
		this.subTypeEnemy = subType;
		
		setAnimation();
		setAnimationParts(iniPositionX, iniPositionY, width, height);
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		
		
		ElementDataEnum eDU = ElementDataEnum.getBySpawnType(subType);
    	this.eDO = new ElementDefinitionObject.Builder().setLife(eDU.getLife()).setScore(eDU.getScore()).build();
		
		
		this.angle = angle;
		this.angleTurret = angle;
		
		this.timer = 0;
		
		position.set(iniPositionX, iniPositionY);	
		direction.set((float)Math.cos(Math.toRadians(angle+270)), (float)Math.sin(Math.toRadians(angle+270))).nor();
		
		setSpeed(0, 0);
		setShootingActive(activateGun);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);

		this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
		this.light.setSoftnessLength(0f);
		this.light.attachToBody(this.getBody());
		
		this.cone = new ConeLight(rayHandler, 20, Color.WHITE, 25, 0, 0, 0, 9);
		this.cone.setSoftnessLength(1f);
		this.cone.attachToBody(getBody(), 0, 0, 90.0f);
		
		this.objective = posObjective;
		
		generateIAPathFinding(graph, posTank, posObjective);
		
		stateMachine = new DefaultStateMachine<TankEnemy, TankEnemyStateEnum>(this, TankEnemyStateEnum.MOVE);
		
		if (subType.equals(SpawnType.Tank_Level_1)) {
			this.initDelay = DELAY_1;
		}else if (subType.equals(SpawnType.Tank_Level_2)) {
			this.initDelay = DELAY_2;
		}else if (subType.equals(SpawnType.Tank_Level_3)) {
			this.initDelay = DELAY_3;
		}
		
		
    	initShootingEngine(SpawnType.MissileEnemy);
    	setShootingRayHandler(rayHandler);
    	setShootingActive(false);
    	
	}
	
	public void setShotSound(String path, float volume) {
	     sfxShot = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxShotVolume = volume;
	}
	
	public void setMissileSound(String path, float volume) {
		sfxMissile = Gdx.audio.newSound(Gdx.files.internal(path));
		sfxMissileVolume = volume;
	}
	
	public StateMachine<TankEnemy, TankEnemyStateEnum> getStateMachine(){
		return stateMachine;
	}
	
	
    public void setAnimation() {
	    	
	    	if (this.subTypeEnemy.equals(SpawnType.Tank_Level_1)) {
	    		
	    		Texture[] hullTXT = GameLogicElementInformation.hullEnemy02Text;
	    		init(hullTXT,0);
	    		
	    	}else if (this.subTypeEnemy.equals(SpawnType.Tank_Level_2)) {
	    		
	    		Texture[] hullTXT = GameLogicElementInformation.hullEnemy02_1Text;
	    		init(hullTXT,0);
	    		
	    	}else if (this.subTypeEnemy.equals(SpawnType.Tank_Level_3)){
	    		Texture[] hullTXT = GameLogicElementInformation.hullEnemy02_2Text;
	    		init(hullTXT,0);
	    	}
	}
	
    public void setAnimationParts(float iniPositionX, float iniPositionY, float width, float height) {
    	
    	if (this.subTypeEnemy.equals(SpawnType.Tank_Level_1)) {
    	
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
    	
    	
    	}else {
    		
    		DynElementPart track_left = new DynElementPart(DynamicElementPartType.TRACK_LEFT_ENEMY_2);
        	track_left.init(GameLogicElementInformation.trackEnemyText_1, 0);
        	track_left.setSize(ElementEnum.TRACK_05.getWidthShow(), ElementEnum.TRACK_05.getHeightShow());
        	track_left.setPosition(iniPositionX+8, iniPositionY);
        	enemy_parts.add(track_left);
        	
        	DynElementPart track_right = new DynElementPart(DynamicElementPartType.TRACK_RIGHT_ENEMY_2);
        	track_right.init(GameLogicElementInformation.trackEnemyText_1, 0);
        	track_right.setSize(ElementEnum.TRACK_05.getWidthShow(), ElementEnum.TRACK_05.getHeightShow());
        	track_right.setPosition(iniPositionX+48-8, iniPositionY);
        	enemy_parts.add(track_right);
    		
    	}
    	
    	
    	
    	DynElementPart gun = new DynElementPart(DynamicElementPartType.GUN_ENEMY_2);
    	Texture gunTXT[] = null;
    	if (this.subTypeEnemy.equals(SpawnType.Tank_Level_1)) {
    		
    		gunTXT = GameLogicElementInformation.cannonEnemy02Text;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_ENEMY2.getWidthShow(), ElementEnum.GUN_ENEMY2.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_ENEMY2.getWidthShow()/2), iniPositionY+8);
        	
    	}else if (this.subTypeEnemy.equals(SpawnType.Tank_Level_2)) {
    		
    		gunTXT = GameLogicElementInformation.cannonEnemy02_1Text;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_ENEMY3.getWidthShow(), ElementEnum.GUN_ENEMY3.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_ENEMY3.getWidthShow()/2), iniPositionY+8);
        	
    	}else if (this.subTypeEnemy.equals(SpawnType.Tank_Level_3)) {
    		
    		gunTXT = GameLogicElementInformation.cannonEnemy02_2Text;
    		gun.init(gunTXT, 0);
    		gun.setSize(ElementEnum.GUN_ENEMY4.getWidthShow(), ElementEnum.GUN_ENEMY4.getHeightShow());
        	gun.setPosition(iniPositionX+(width/2)-(ElementEnum.GUN_ENEMY4.getWidthShow()/2), iniPositionY+8);
        	
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
		this.pool = pool;
	}

	@Override
	public void kill(SpawnPool pool) {
		dispose();
		super.setPosition(SecondTestGDX.screenWidth, 0);
	}

	
	
	@Override
	public void draw(SpriteBatch sb) {
		enemy_parts.get(INDEX_TRACK_LEFT).draw(sb);
		enemy_parts.get(INDEX_TRACK_RIGHT).draw(sb);
		enemy_parts.get(INDEX_EXHAUST_LEFT).draw(sb);
		enemy_parts.get(INDEX_EXHAUST_RIGHT).draw(sb);
    	super.draw(sb);
    	enemy_parts.get(INDEX_GUN).draw(sb);
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
		enemy_parts.clear();
		this.light.remove();
		this.cone.remove();
	}


	@Override
	public boolean handleMessage(Telegram msg) {
		return stateMachine.handleMessage(msg);
	}
	
	
	@Override
	public void update(float delta, float boostFactor) {
		
		
		
		if (this.timer_delay <= this.initDelay) {
			this.timer_delay += delta;
		}else {
			stateMachine.update();
			processIATank(delta);
		}
	}
	
	private void processIATank(float delta) {
		
		if (stateMachine.getCurrentState().equals(TankEnemyStateEnum.MOVE)) {
			movementTANKState(delta);	
		}else if (stateMachine.getCurrentState().equals(TankEnemyStateEnum.ATTACK)) {
			attackTANKState(delta);
		}else if (stateMachine.getCurrentState().equals(TankEnemyStateEnum.STOP)) {
			stopTANKState(delta);
		}	
		
		setCollisionRef(getX(),getY());	
		
	}
	
	public void movementTANKState(float delta) {
		angleTurret = 0.0f;
		setShootingActive(false);
		movement(delta,-1);
		checkTANKFindNode();
		animatedTracks(delta, true, true);
		animatedExhaust(delta, true, true);
	}
	
	
	public void stopTANKState(float delta) {
		stop();
		setShootingActive(false);
		animatedTracks(delta, false, false);
		animatedExhaust(delta, false, false);
	}
	
	
	
	public void attackTANKState(float delta) {
		
		stop();
		setShootingActive(true);
		rotateTurret();
		shootGeneration(delta);
		animatedTracks(delta, false, false);
		animatedExhaust(delta, false, false);
		super.update(delta);
		
	}
	
	
    public void rotateTurret() {
    	
    	Vector2 gunPosition = new Vector2();
    	Vector2 player_position = new Vector2();
    	
    	gunPosition.x = enemy_parts.get(INDEX_GUN).getX();
    	gunPosition.y = enemy_parts.get(INDEX_GUN).getY();
    	
    	player_position.x = this.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX();
    	player_position.y = this.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY();
    	
    	angleTurret = (float) Math.atan2((gunPosition.y-player_position.y),(gunPosition.x-player_position.x));
    	angleTurret = (angleTurret*MathUtils.radDeg);
    	
    	angleTurret += 90;
    	
    	//enemy_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    	enemy_parts.get(INDEX_GUN).rotate(angleTurret, ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2, ElementEnum.GUN_PLAYER_1_A.getHeightShow()/2-8 );
    			
    }
    
	
	
	
	
    public void shootGeneration(float delta) {
	    	
	    	timer += delta;
			float speedGun = 500.0f;
			
			if (timer  >= INTERVAL_BETWEEN_SHOOT) {
				float shootAngle = angleTurret+90;
				float x = 0.0f;
				float y = 0.0f;
				
				if (this.subTypeEnemy.equals(SpawnType.Tank_Level_3)) {
					x = (float) ((getX() + 21.5 + getWidth()/2 - ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2) + 50.0 * Math.cos(shootAngle*MathUtils.degRad)); 
					y = (float) (getY() + 21.5 + 50.0 * Math.sin(shootAngle*MathUtils.degRad));
				}else {
					x = (float) ((getX() + getWidth()/2 - ElementEnum.GUN_PLAYER_1_A.getWidthShow()/2) + 50.0 * Math.cos(shootAngle*MathUtils.degRad)); 
					y = (float) ((getY()) + 50.0 * Math.sin(shootAngle*MathUtils.degRad));
				}
				

				timer = 0.0f;
				
				if (this.subTypeEnemy.equals(SpawnType.Tank_Level_1)) {
					this.addGun(SpawnType.Missile_Plasma, shootAngle, speedGun, x , y, 0, 0, ElementEnum.LASER.getWidthShow(), ElementEnum.LASER.getHeightShow());
					sfxShot.play();
				}else if (this.subTypeEnemy.equals(SpawnType.Tank_Level_2)) {
					this.addGun(SpawnType.Missile_Laser, shootAngle, speedGun, x-5 , y, 0, 0, ElementEnum.LASER.getWidthShow(), ElementEnum.LASER.getHeightShow());
					this.addGun(SpawnType.Missile_Laser, shootAngle, speedGun, x+5 , y, 0, 0, ElementEnum.LASER.getWidthShow(), ElementEnum.LASER.getHeightShow());
					sfxShot.play();
				}else {
					this.addGun(SpawnType.Missile_Missile, angleTurret-90, (-1)*speedGun, x, y, 0, 0, ElementEnum.MISSILE_1.getWidthShow(), ElementEnum.MISSILE_1.getHeightShow());
					sfxMissile.play(sfxMissileVolume);
				}
				
				
				
				this.setShootEvent(true);
			}
	 }
	
	
	
	
	private void checkTANKFindNode() {
		Queue<NewItem> path = this.getPath();
		if (path.size > 0) {
			NewItem node = path.first();
			if (Vector2.dst(getX(), getY(), node.getX(), node.getY()) <= NODE_ARRIVE) {
		        reachNextNode();
		    }
		}
	}
	
	
	private void reachNextNode() {
		
		NewItem node = this.getPath().first();
		this.previousNode = node;
		this.getPath().removeFirst();
		
		if (this.getPath().size > 0) {
			changeOrientationToNextNode();
		}
		
	}
	
	private void changeOrientationToNextNode() {
		NewItem currentNode = this.getPath().first();
		float angleRdn =  MathUtils.atan2(currentNode.getY() - previousNode.getY(), currentNode.getX() - previousNode.getX());
		this.angle = angleRdn * MathUtils.radDeg + 270;
		rotate();
	}
	
	public void rotate() {
    	super.rotate(angle);
    	direction.set((float)Math.cos(Math.toRadians(angle+270)), (float)Math.sin(Math.toRadians(angle+270))).nor();
    	
    	super.setCollisionAngleRef(getX(), getY(), angle*MathUtils.degRad);
    	

    	enemy_parts.get(INDEX_TRACK_LEFT).rotate(angle,24,getHeight()/2);
    	enemy_parts.get(INDEX_TRACK_RIGHT).rotate(angle,-8,getHeight()/2); 	
    	enemy_parts.get(INDEX_GUN).rotate(angle+angleTurret, ElementEnum.GUN_ENEMY2.getWidthShow()/2, ElementEnum.GUN_ENEMY2.getHeightShow()/2-8 );
    	
    	enemy_parts.get(INDEX_EXHAUST_LEFT).rotate(angle, 16, 56);
    	enemy_parts.get(INDEX_EXHAUST_RIGHT).rotate(angle, 0, 56);
    	
    }
	
	
	public void stop() {
		this.getBody().setLinearVelocity(0.0f, 0.0f);
		this.getBody().setAngularVelocity(0.0f);	
	}
	
	
	public void movement(float delta, float index) {
   	     
		movement.set(direction).scl(GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta * index*2);
        position.add(movement);
        
        super.setCollisionVel(movement.x, movement.y);
        Vector2 posRelative = super.getPositionFromBodyToPixel();
        super.setPosition(posRelative.x, posRelative.y);
        
        enemy_parts.get(INDEX_TRACK_LEFT).setPosition(getX()+8, getY());
        enemy_parts.get(INDEX_TRACK_RIGHT).setPosition(getX()+40, getY());
        enemy_parts.get(INDEX_GUN).setPosition(getX()+(getWidth()/2)-(ElementEnum.GUN_ENEMY2.getWidthShow()/2), getY()+8);
        enemy_parts.get(INDEX_EXHAUST_LEFT).setPosition(getX()+(getWidth()/2)-16, getY()-ElementEnum.EXHAUST_01.getHeightShow()+8);
        enemy_parts.get(INDEX_EXHAUST_RIGHT).setPosition(getX()+(getWidth()/2), getY()-ElementEnum.EXHAUST_01.getHeightShow()+8);
   }
	
	public NewItem getObjective() {
		return objective;
	}

	public void setObjective(NewItem objective) {
		this.objective = objective;
	}


	public GamePlayScreen getGamePlayScreen() {
		return this.gPS;
	}	
	
	
    public void animatedTracks(float delta, boolean trackleft, boolean trackright) {
       enemy_parts.get(INDEX_TRACK_LEFT).AnimationLoop(delta, trackleft);
       enemy_parts.get(INDEX_TRACK_RIGHT).AnimationLoop(delta, trackright);
	}
	    
	public void animatedExhaust(float delta, boolean trackleft, boolean trackright) {
		enemy_parts.get(INDEX_EXHAUST_LEFT).AnimationLoop(delta, trackleft);
		enemy_parts.get(INDEX_EXHAUST_RIGHT).AnimationLoop(delta, trackright);
	}


	@Override
	public Body getBox2DBody() {
		return this.getBody();
	}
	
	public ElementDefinitionObject getStatsDynElement() {
		return this.eDO;
	}


	@Override
	public SpawnType getType() {
		return this.typeEnemy;
	}


	@Override
	public SpawnType getSubType() {
		// TODO Auto-generated method stub
		return this.subTypeEnemy;
	}
	
	

}
