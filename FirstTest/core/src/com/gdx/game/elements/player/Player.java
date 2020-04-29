package com.gdx.game.elements.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicCollObject;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.engine.GamePlay;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.PlayerPartType;
import com.gdx.game.stages.enums.SpawnType;

public class Player extends ShootObject{

	
	private float moveStepX;
    private float moveStepY;
	private PlayerMovements orientation;
	private Sound sfxShot;
	private float sfxShotVolume; 
	
	private boolean isEndMap;
	
	private static final int collisionMarginRight = 64;
    private static final int collisionMarginLeft = 30;
    
    private static final int collisionMarginUp = 200;
    private static final int collisionMarginDown = 200;
    
    private static final float accelerationUp = 500;
    private static final float accelerationDown = 500;
    
    
    private static final int INDEX_SHADOW = 0;
    private static final int INDEX_BOOST_LEFT = 1;
    private static final int INDEX_BOOST_RIGHT = 2;
    private static final int INDEX_EXHAUST_UL = 3;
    private static final int INDEX_EXHAUST_UR = 4;
    private static final int INDEX_EXHAUST_DL = 5;
    private static final int INDEX_EXHAUST_DR = 6;
    
    
    private static final int CENTER  = 0;
    private static final int LEFT_1 = 1;
    private static final int LEFT_2 = 2;
    private static final int RIGHT_1 = 3;
    private static final int RIGHT_2 = 4;
    
	ArrayList<PlayerPart> player_parts;
	
	boolean isAccX;
	boolean isAccY;
	
	private GamePlayScreen gPS;
	
	public Player(SpawnPool spawnPool, World world, GamePlayScreen gPS) {
		super(spawnPool,world);
		
		moveStepX = 0;
	    moveStepY = 0;
	    orientation = PlayerMovements.IDLE; 
	    sfxShotVolume = 0.97f;
	   
	    isAccX = false;
	    isAccY = false;
	    
	    this.gPS = gPS;
	    
	    this.gPS.getgLL().setShootTypePlayer(MissileTypeEnum.LASER_1);
	    
	    this.isEndMap = false;
	    
	    
	    setShotSound("sounds/laser4.mp3", sfxShotVolume);
	    super.resetGuns();
		
		player_parts = new ArrayList<PlayerPart>();
	}
	
	public void setLocationAndSize(float iniPositionX, float iniPositionY, float width, float height) {
	
		setReference(this);
		setAnimation(iniPositionX, iniPositionY, width, height);
		setAnimationParts(iniPositionX, iniPositionY, width, height);
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		setSpeed(0, 0);
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		
	}
	
	
	public void setAnimation(float iniPositionX, float iniPositionY, float width, float height) {
		
		Texture[] playerTXT = new Texture[5];
		playerTXT[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_01,Texture.class);
		playerTXT[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_02,Texture.class);
		playerTXT[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_03,Texture.class);
		playerTXT[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_04,Texture.class);
		playerTXT[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_05,Texture.class);
		
		init(playerTXT,0);
		
	}
	
	
	public void setAnimationParts(float iniPositionX, float iniPositionY, float width, float height) {
		
		
		Texture[] shadowTXT = new Texture[5];
		shadowTXT[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_01,Texture.class);
		shadowTXT[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_02,Texture.class);
		shadowTXT[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_03,Texture.class);
		shadowTXT[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_04,Texture.class);
		shadowTXT[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_05,Texture.class);
		
		//SHADOWS
		PlayerPart shadow = new PlayerPart(PlayerPartType.SHADOW);
		shadow.init(shadowTXT,0);
		shadow.setSize(width/2, height/2);
		shadow.setPosition(iniPositionX+16, iniPositionX+16);
		shadow.setSpeed(0, 0);
		player_parts.add(shadow);
		
		
		Texture[] exhaustTxt = new Texture[7];
		exhaustTxt[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_01,Texture.class); //(-)
		exhaustTxt[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_02,Texture.class);
		exhaustTxt[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_03,Texture.class); //(-+)
		exhaustTxt[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_04,Texture.class); //(+-)
		exhaustTxt[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_05,Texture.class);
		exhaustTxt[5] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_06,Texture.class); //(-)
		exhaustTxt[6] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
		
		//EXHAUST - CDL
		PlayerPart exhaustLeft = new PlayerPart(PlayerPartType.BOOSTS);
		exhaustLeft.init(exhaustTxt,6);
		exhaustLeft.setSize(10, 32);
		exhaustLeft.setPosition(iniPositionX+25, iniPositionY-32);
		exhaustLeft.setSpeed(0, 0);
		player_parts.add(exhaustLeft);
		
		//EXHAUST - CDR
		PlayerPart exhaustRight = new PlayerPart(PlayerPartType.BOOSTS);
		exhaustRight.init(exhaustTxt,6);
		exhaustRight.setSize(10, 32);
		exhaustRight.setPosition(iniPositionX+32, iniPositionY-32);
		exhaustRight.setSpeed(0, 0);
		player_parts.add(exhaustRight);
		
		//EXHAUST-UL
		Texture[] exhaustUL = new Texture[3];
	    exhaustUL[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUL_1,Texture.class);
	    exhaustUL[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUL_2,Texture.class);
	    exhaustUL[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
		PlayerPart exhaustSUL = new PlayerPart(PlayerPartType.EXHAUST_UL);
		exhaustSUL.init(exhaustUL,2);
		exhaustSUL.setSize(32, 32);
		exhaustSUL.setPosition(iniPositionX-32, iniPositionY+32);
		player_parts.add(exhaustSUL);
	
		//EXHAUST-UR
		Texture[] exhaustUR = new Texture[3];
	    exhaustUR[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUR_1,Texture.class);
	    exhaustUR[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUR_2,Texture.class);
	    exhaustUR[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
	    PlayerPart exhaustSUR = new PlayerPart(PlayerPartType.EXHAUST_UR);
	    exhaustSUR.init(exhaustUR,2);
	    exhaustSUR.setSize(32, 32);
	    exhaustSUR.setPosition(iniPositionX+64, iniPositionY+32);
	    player_parts.add(exhaustSUR);
		
	    //EXHAUST-DL
		Texture[] exhaustDL = new Texture[3];
	    exhaustDL[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDL_1,Texture.class);
	    exhaustDL[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDL_2,Texture.class);
	    exhaustDL[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
		PlayerPart exhaustSDL  = new PlayerPart(PlayerPartType.EXHAUST_DL);
		exhaustSDL.init(exhaustDL,2);
		exhaustSDL.setSize(32, 32);
		exhaustSDL.setPosition(iniPositionX-32, iniPositionY-32);
		player_parts.add(exhaustSDL);
		
		//EXHAUST-DR
		Texture[] exhaustDR = new Texture[3];
	    exhaustDR[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDR_1,Texture.class);
	    exhaustDR[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDR_2,Texture.class);
	    exhaustDR[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
		PlayerPart exhaustSDR = new PlayerPart(PlayerPartType.EXHAUST_DR);
		exhaustSDR.init(exhaustDR,2);
		exhaustSDR.setSize(32, 32);
		exhaustSDR.setPosition(iniPositionX+64, iniPositionY-32);
		player_parts.add(exhaustSDR);
	}
	
	
	public void start() {
		super.init(SpawnType.MissilePlayer);
		super.setShootingActive(true);
	}
	
	public void update(float delta) {
		
		movement(delta);
		movementParts(delta);
		
		AnimationByMovement(orientation, this.moveStepX, this.moveStepY, false, false);
		AnimationByMovementPart(orientation, this.moveStepX, this.moveStepY);
		
		collision(delta);
		
		if (super.isShootEvent()) {
			if (!isEndMap) {
				setGun();
				super.setShootEvent(false);
			}
		}
		super.update(delta);
	}
	
	public void collision(float delta) {
		setCollisionRef(getX(), getY());
	}
	
	public void setEndMap(boolean isEndMap) {
		this.isEndMap = isEndMap;
	}
	
	
	public void movement(float delta) {
		
		
		if (!isEndMap) {
			if (orientation.equals(PlayerMovements.UP)) {
				
				if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W) ) {
					
					accelerateUpY(delta);
					if (getY() > (FirstTestGDX.screenHeight - collisionMarginUp)) {
						setY(FirstTestGDX.screenHeight - collisionMarginUp);
					}else {
						setY(getY() +  moveStepY*delta);
					}
				}else {fallY(delta);}
				
			}else if (orientation.equals(PlayerMovements.DOWN)) {
				
				if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S) ) {
					
					accelerateUpY(delta);
					if ((getY() + getHeight()) < collisionMarginDown) {
						setY(getHeight()-10);
					}else {
						setY(getY()- moveStepY*delta);
					}			
				}
				else {fallY(delta);}
				
				
			}else if (orientation.equals(PlayerMovements.LEFT)) {
				
				if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
					accelerateUpX(delta);
					if (getX() < this.collisionMarginLeft) {
						setX(this.collisionMarginLeft);
					}else {
						setX(getX() - moveStepX*delta);
					}
				}
				else {fallX(delta);}
				
				
			}else if (orientation.equals(PlayerMovements.RIGHT)) {
				
				//movement player
				if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) ) {
					accelerateUpX(delta);
					if ((getX() + getWidth()) > (FirstTestGDX.screenWidth - collisionMarginRight)) {
						setX(FirstTestGDX.screenWidth - collisionMarginRight);
					}else {
						setX(getX()+ moveStepX*delta);
					}
				}
				else {fallX(delta);}
			}		
		}
	}
	
	
	public void movementParts(float delta) {
		player_parts.get(this.INDEX_SHADOW).setPosition(getX()+16, getY()+16);
//		player_parts.get(this.INDEX_SHADOW).getSprite().setAlpha((FirstTestGDX.screenHeight - getY())/FirstTestGDX.screenHeight);
		player_parts.get(this.INDEX_BOOST_LEFT).setPosition(getX()+25, getY()-32);
		player_parts.get(this.INDEX_BOOST_RIGHT).setPosition(getX()+32, getY()-32);
		player_parts.get(this.INDEX_EXHAUST_UL).setPosition(getX()-32, getY()+32);
		player_parts.get(this.INDEX_EXHAUST_UR).setPosition(getX()+64, getY()+32);
		player_parts.get(this.INDEX_EXHAUST_DL).setPosition(getX()-32, getY()-32);
		player_parts.get(this.INDEX_EXHAUST_DR).setPosition(getX()+64, getY()-32);
	}
	
	
	public void draw(SpriteBatch sb) {
		
		if (!isEndMap) {
			super.draw(sb);
			for(PlayerPart pP: player_parts) {
				pP.draw(sb);
			}
		}
	}
	
	public void move(PlayerMovements orientation) {	
		if (!orientation.equals(PlayerMovements.SHOOT)) {
			this.orientation = orientation;
		}else if (orientation.equals(PlayerMovements.SHOOT)){
			if (!isEndMap) {
				sfxShot.play();
				this.setShootEvent(true);
			}
			
		}
	}
	
	public void action(PlayerMovements orientation) {
		if (!orientation.equals(PlayerMovements.SHOOT)) {
			this.orientation = orientation;
		}else if (orientation.equals(PlayerMovements.SHOOT)){
			if (!isEndMap) {
				sfxShot.play();
				this.setShootEvent(true);
			}
		}
	}
	
	
	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY,boolean isAccX, boolean isAccY) {
		
		if (orientation.equals(PlayerMovements.UP)) {
			super.setTextureToSpriteByIndex(this.CENTER);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			super.setTextureToSpriteByIndex(this.CENTER);
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			if ((moveStepX < 350) && (moveStepX > 0)) {
				super.setTextureToSpriteByIndex(this.LEFT_1);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				super.setTextureToSpriteByIndex(this.LEFT_2);
			}else if (moveStepX <= 0) {
				super.setTextureToSpriteByIndex(this.CENTER);
			}
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			if ((moveStepX < 350) && (moveStepX > 0)) {
				super.setTextureToSpriteByIndex(this.RIGHT_1);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				super.setTextureToSpriteByIndex(this.RIGHT_2);
			}else if (moveStepX <= 0) {
				super.setTextureToSpriteByIndex(this.CENTER);
			}
		}
	}
	
	public void AnimationByMovementPart(PlayerMovements movement, float moveStepX, float moveStepY) {
		player_parts.get(this.INDEX_SHADOW).AnimationByMovement(movement, moveStepX, moveStepY, false, false);
		player_parts.get(this.INDEX_BOOST_LEFT).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
		player_parts.get(this.INDEX_BOOST_RIGHT).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
		player_parts.get(this.INDEX_EXHAUST_UL).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
		player_parts.get(this.INDEX_EXHAUST_UR).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
		player_parts.get(this.INDEX_EXHAUST_DL).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
		player_parts.get(this.INDEX_EXHAUST_DR).AnimationByMovement(movement, moveStepX, moveStepY, isAccX, isAccY);
	}
	

	@Override
	public void AnimationByTime(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return " Player (" + getCode() + ")";
	}
	
	
	
	public void dispose() {
		player_parts.clear();
	}
	
	
	 private  void accelerateUpY(float delta) {
		   if (moveStepY < 650) {
			   isAccY = true;
		       moveStepY += accelerationUp * delta;
		   }
	 } 
	   
	 private void fallY(float delta) {
		  if (moveStepY >= 0) {
			  isAccY = false;
		      moveStepY -= accelerationDown * delta;
		  }
	 }

	 private  void accelerateUpX(float delta) {
		  if (moveStepX < 650) {
			  isAccX = true;
			  moveStepX += accelerationUp * delta;
		  }
	 }
	 
	 private void fallX(float delta) {
		    if (moveStepX >= 0) {
		    	isAccX = false;
		    	moveStepX -= accelerationDown * delta;
		    }
     }
	 
	 private void setGun() {
			
		float intervalGun = 0.35f;
		float speedGun = 800.0f;
		
		this.setGunPower(100.0f);
		this.setShootingInterval(intervalGun);
		this.setGunType(MissileTypeEnum.LASER_1);
		this.addGun(90.0f, speedGun, getX() , getY(), (getWidth()/2)-5, 30);
		
		
			
	}

	public void setShotSound(String path, float volume) {
	     sfxShot = Gdx.audio.newSound(Gdx.files.internal(path));
	     sfxShotVolume = volume;
	}
	 

}
