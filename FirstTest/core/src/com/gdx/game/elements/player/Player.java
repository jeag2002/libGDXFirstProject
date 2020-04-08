package com.gdx.game.elements.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.CollisionObject;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.engine.GamePlay;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Player extends ShootObject{
	
	
	private static final String TAG = "[PLAYER]";
	
	private static final int collisionMarginRight = 64;
    private static final int collisionMarginLeft = 30;
    
    private static final int collisionMarginUp = 200;
    private static final int collisionMarginDown = 200;
    
    private static final float accelerationUp = 500;
    private static final float accelerationDown = 500;
    
    
    private Texture[] rotateLeftPlayer;
    private Texture[] rotateRightPlayer;
    private Texture centerPlayer;
    
    private Texture[] rotateLeftPlayerShw;
    private Texture[] rotateRightPlayerShw;
    private Texture centerPlayerShw;
    
    private Texture[] exhaustTxt;
    
    private Texture[] exhaustUL;
    private Texture[] exhaustUR;
    
    private Texture[] exhaustDL;
    private Texture[] exhaustDR;
    
	
    private Sprite spritePlayer;
	private Sprite shadow;
	
	private Sprite exhaustLeft;
	private Sprite exhaustRight;
	
	private Sprite exhaustSUL;
	private Sprite exhaustSUR;
	private Sprite exhaustSDL;
	private Sprite exhaustSDR;
	
	
	private float moveStepX;
    private float moveStepY;
	
	private PlayerMovements orientation;
	
    private Sound sfxShot;
	private float sfxShotVolume; 
	
	private GamePlay gP;

	public Player(GamePlay gP) {
		
		super(gP);
		
		moveStepX = 0;
	    moveStepY = 0;
	    orientation = PlayerMovements.IDLE;
		
		rotateLeftPlayer = new Texture[2];
		rotateLeftPlayer[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_02,Texture.class);
		rotateLeftPlayer[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_03,Texture.class);
		
		rotateLeftPlayerShw = new Texture[2];
		rotateLeftPlayerShw[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_02,Texture.class);
		rotateLeftPlayerShw[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_03,Texture.class);
		
		rotateRightPlayer = new Texture[2];
		rotateRightPlayer[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_04,Texture.class);
		rotateRightPlayer[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_05,Texture.class);
		
		rotateRightPlayerShw = new Texture[2];
		rotateRightPlayerShw[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_04,Texture.class);
		rotateRightPlayerShw[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_05,Texture.class);
		
		
		exhaustTxt = new Texture[7];
		exhaustTxt[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_01,Texture.class); //(-)
		exhaustTxt[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_02,Texture.class);
		exhaustTxt[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_03,Texture.class); //(-+)
		exhaustTxt[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_04,Texture.class); //(+-)
		exhaustTxt[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_05,Texture.class);
		exhaustTxt[5] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_06,Texture.class); //(-)
		exhaustTxt[6] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class);
		
		
		
	    exhaustUL = new Texture[2];
	    //0,0,32,32
	    exhaustUL[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUL_1,Texture.class);
	    exhaustUL[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUL_2,Texture.class);
	    		
	    exhaustUR = new Texture[2];
	    exhaustUR[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUR_1,Texture.class);
	    exhaustUR[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroUR_2,Texture.class);
	    
	    exhaustDL = new Texture[2];
	    exhaustDL[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDL_1,Texture.class);
	    exhaustDL[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDL_2,Texture.class);
	    
	    exhaustDR = new Texture[2];
	    exhaustDR[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDR_1,Texture.class);
	    exhaustDR[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgRetroDR_2,Texture.class);
		
		
		centerPlayer = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPlayerRed_01,Texture.class);
		centerPlayerShw = FirstTestGDX.resources.get(FirstTestGDX.resources.imgShadowPlayerRed_01,Texture.class);
		
	    spritePlayer = new Sprite(centerPlayer);
	    shadow = new Sprite(centerPlayerShw);
	    
	    exhaustLeft = new Sprite(exhaustTxt[6]);
	    exhaustRight = new Sprite(exhaustTxt[6]);
	    
		exhaustSUL = new Sprite(exhaustTxt[6]);
		exhaustSUR = new Sprite(exhaustTxt[6]);
		exhaustSDL = new Sprite(exhaustTxt[6]);
		exhaustSDR = new Sprite(exhaustTxt[6]);
		
		
		setShotSound("sounds/laser4.mp3", 0.97f);
	    super.setCollisionRef(spritePlayer.getX(), spritePlayer.getY());
	    super.resetGuns();
		
	}
	
	public void setSize(float width, float height) {
		spritePlayer.setSize(width, height);
		shadow.setSize(width/2, height/2);
		
		exhaustLeft.setSize(10, 32);
		exhaustRight.setSize(10, 32);
		
		exhaustSUL.setSize(32, 32);
		exhaustSUR.setSize(32, 32);
		exhaustSDL.setSize(32, 32);
		exhaustSDR.setSize(32, 32);
		
	}
	
	public void setPosition(float x, float y) {
		spritePlayer.setPosition(x, y);
		shadow.setPosition(x+16, y+16);
		
		exhaustLeft.setPosition(x+25, y-32);
		exhaustRight.setPosition(x+32, y-32);
		
		
		exhaustSUL.setPosition(x-32, y+32);
		exhaustSUR.setPosition(x+64, y+32);
		exhaustSDL.setPosition(x-32, y-32);
		exhaustSDR.setPosition(x+64, y-32);
		
	}
	
	public void start() {
		super.init(SpawnType.MissilePlayer);
		super.setShootingActive(true);
	}
	
	
	public void update(float delta) {
		movement(delta);
		
		if (super.isShootEvent()) {
			setGun();
			super.setShootEvent(false);
		}
		super.update(delta);
	}
	
	
	
	public void movement(float delta) {
		
		if (orientation.equals(PlayerMovements.UP)) {
			
			spritePlayer.setTexture(centerPlayer);
			shadow.setTexture(centerPlayerShw);
			
			if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W) ) {
				accelerateUpY(delta);
				
				//movement
				if (spritePlayer.getY() > (FirstTestGDX.screenHeight - collisionMarginUp)) {
					spritePlayer.setY(FirstTestGDX.screenHeight - collisionMarginUp);
				}else {
					spritePlayer.setY(spritePlayer.getY() +  moveStepY*delta);
				}
				
				//exhaust
				if (moveStepY <= 0) {
					exhaustLeft.setTexture(exhaustTxt[6]);
					exhaustRight.setTexture(exhaustTxt[6]);	
				}else if ((moveStepY > 0) && (moveStepX<= 200)) {
					exhaustLeft.setTexture(exhaustTxt[0]);
					exhaustRight.setTexture(exhaustTxt[0]);	
				}else if ((moveStepY > 200) && (moveStepX<= 400)) {
					exhaustLeft.setTexture(exhaustTxt[1]);
					exhaustRight.setTexture(exhaustTxt[1]);	
				}else if (moveStepX > 400) {
					exhaustLeft.setTexture(exhaustTxt[2]);
					exhaustRight.setTexture(exhaustTxt[2]);	
				}
			}
			else {fallY(delta);
				
				//exhaust
				if (moveStepY <= 0) {
					exhaustLeft.setTexture(exhaustTxt[6]);
					exhaustRight.setTexture(exhaustTxt[6]);	
				}else if ((moveStepY > 0) && (moveStepX<= 200)) {
					exhaustLeft.setTexture(exhaustTxt[5]);
					exhaustRight.setTexture(exhaustTxt[5]);	
				}else if ((moveStepY > 200) && (moveStepX<= 400)) {
					exhaustLeft.setTexture(exhaustTxt[4]);
					exhaustRight.setTexture(exhaustTxt[4]);	
				}else if (moveStepX > 400) {
					exhaustLeft.setTexture(exhaustTxt[3]);
					exhaustRight.setTexture(exhaustTxt[3]);	
				}
			}
			
			
			exhaustSUL.setTexture(exhaustTxt[6]);
			exhaustSUR.setTexture(exhaustTxt[6]);
			exhaustSDL.setTexture(exhaustTxt[6]);
			exhaustSDR.setTexture(exhaustTxt[6]);
			
				
			
			

		}else if (orientation.equals(PlayerMovements.DOWN)) {
			
			spritePlayer.setTexture(centerPlayer);
			shadow.setTexture(centerPlayerShw);
			
			
			exhaustSUL.setTexture(exhaustTxt[6]);
			exhaustSUR.setTexture(exhaustTxt[6]);
			exhaustSDL.setTexture(exhaustTxt[6]);
			exhaustSDR.setTexture(exhaustTxt[6]);
			
			
			if (moveStepY <= 0) {
				exhaustSUL.setTexture(exhaustTxt[6]);
				exhaustSUR.setTexture(exhaustTxt[6]);
			}else if ((moveStepY > 0) && (moveStepY <= 350)) {
				exhaustSUL.setTexture(exhaustUL[0]);
				exhaustSUR.setTexture(exhaustUR[0]);
			}else if (moveStepY > 350) {
				exhaustSUL.setTexture(exhaustUL[1]);
				exhaustSUR.setTexture(exhaustUR[1]);
			}
			
			//movement 
			if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S) ) {
				 accelerateUpY(delta);
				if ((spritePlayer.getY() + spritePlayer.getHeight()) < collisionMarginDown) {
					spritePlayer.setY(spritePlayer.getHeight()-10);
				}else {
					spritePlayer.setY(spritePlayer.getY()- moveStepY*delta);
				}			
			}
			else {fallY(delta);}
			
			exhaustLeft.setTexture(exhaustTxt[6]);
			exhaustRight.setTexture(exhaustTxt[6]);	
			
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			
			//animation player
			if ((moveStepX < 350) && (moveStepX > 0)) {
				spritePlayer.setTexture(rotateLeftPlayer[0]);
				shadow.setTexture(rotateLeftPlayerShw[0]);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				spritePlayer.setTexture(rotateLeftPlayer[1]);
				shadow.setTexture(rotateLeftPlayerShw[1]);
			}else if (moveStepX <= 0) {
				spritePlayer.setTexture(centerPlayer);
				shadow.setTexture(centerPlayerShw);
			}
			
			
			exhaustSUL.setTexture(exhaustTxt[6]);
			exhaustSUR.setTexture(exhaustTxt[6]);
			exhaustSDL.setTexture(exhaustTxt[6]);
			exhaustSDR.setTexture(exhaustTxt[6]);
			
			
			if (moveStepX <= 0) {
				exhaustSUR.setTexture(exhaustTxt[6]);
				exhaustSDR.setTexture(exhaustTxt[6]);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				exhaustSUR.setTexture(exhaustUR[0]);
				exhaustSDR.setTexture(exhaustDR[0]);
			}else if (moveStepX > 350) {
				exhaustSUR.setTexture(exhaustUR[1]);
				exhaustSDR.setTexture(exhaustDR[1]);
			}
			

			//movement player
			if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
				accelerateUpX(delta);
				if (spritePlayer.getX() < this.collisionMarginLeft) {
					spritePlayer.setX(this.collisionMarginLeft);
				}else {
					spritePlayer.setX(spritePlayer.getX() - moveStepX*delta);
				}
			}
			else {fallX(delta);}
			
			exhaustLeft.setTexture(exhaustTxt[6]);
			exhaustRight.setTexture(exhaustTxt[6]);	
			
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			
			//animation player
			if ((moveStepX < 350) && (moveStepX > 0)) {
				spritePlayer.setTexture(rotateRightPlayer[0]);
				shadow.setTexture(rotateRightPlayerShw[0]);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				spritePlayer.setTexture(rotateRightPlayer[1]);
				shadow.setTexture(rotateRightPlayerShw[1]);
			}else if (moveStepX <= 0) {
				spritePlayer.setTexture(centerPlayer);
				shadow.setTexture(centerPlayerShw);
			}
			
			exhaustSUL.setTexture(exhaustTxt[6]);
			exhaustSUR.setTexture(exhaustTxt[6]);
			exhaustSDL.setTexture(exhaustTxt[6]);
			exhaustSDR.setTexture(exhaustTxt[6]);
			
			
			if (moveStepX <= 0) {
				exhaustSUL.setTexture(exhaustTxt[6]);
				exhaustSDL.setTexture(exhaustTxt[6]);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				exhaustSUL.setTexture(exhaustUL[0]);
				exhaustSDL.setTexture(exhaustDL[0]);
			}else if (moveStepX > 350) {
				exhaustSUL.setTexture(exhaustUL[1]);
				exhaustSDL.setTexture(exhaustDL[1]);
			}
			
			
			//movement player
			if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) ) {
				accelerateUpX(delta);
				if ((spritePlayer.getX() + spritePlayer.getWidth()) > (FirstTestGDX.screenWidth - collisionMarginRight)) {
					spritePlayer.setX(FirstTestGDX.screenWidth - collisionMarginRight);
				}else {
					spritePlayer.setX(spritePlayer.getX()+ moveStepX*delta);
				}
			}
			else {fallX(delta);}
			
			exhaustLeft.setTexture(exhaustTxt[6]);
			exhaustRight.setTexture(exhaustTxt[6]);		
		}
		
		
		
		
		shadow.setPosition(spritePlayer.getX()+16, spritePlayer.getY()+16);
		shadow.setAlpha( (FirstTestGDX.screenHeight - spritePlayer.getY())/FirstTestGDX.screenHeight );
		
		exhaustLeft.setPosition(spritePlayer.getX()+25, spritePlayer.getY()-32);
		exhaustRight.setPosition(spritePlayer.getX()+32, spritePlayer.getY()-32);
		
		exhaustSUL.setPosition(spritePlayer.getX()-32, spritePlayer.getY()+32);
		exhaustSUR.setPosition(spritePlayer.getX()+64, spritePlayer.getY()+32);
		exhaustSDL.setPosition(spritePlayer.getX()-32, spritePlayer.getY()-32);
		exhaustSDR.setPosition(spritePlayer.getX()+64, spritePlayer.getY()-32);
		
		
		super.setCollisionRef(spritePlayer.getX(), spritePlayer.getY());
		
	}
	
	private void setGun() {
		
		float intervalGun = 0.35f;
		float speedGun = 800.0f;
		
		
	
		this.setGunPower(100.0f);
		this.setShootingInterval(intervalGun);
		this.setGunType(MissileTypeEnum.LASER_1);
		this.addGun(90.0f, speedGun, spritePlayer.getX() , spritePlayer.getY(), (spritePlayer.getWidth()/2)-5, 30);
		
	}

	public void setShotSound(String path, float volume) {
        sfxShot = Gdx.audio.newSound(Gdx.files.internal(path));
        sfxShotVolume = volume;
    }
	
	public void move(PlayerMovements orientation) {
		
		if (!orientation.equals(PlayerMovements.SHOOT)) {
			this.orientation = orientation;
		}else if (orientation.equals(PlayerMovements.SHOOT)){
			sfxShot.play();
			this.setShootEvent(true);
		}
		
		
	}
	
	
	
	public void draw(SpriteBatch sb) {		
		shadow.draw(sb);
		spritePlayer.draw(sb);
		exhaustLeft.draw(sb);
		exhaustRight.draw(sb);
		
		exhaustSUL.draw(sb);
		exhaustSUR.draw(sb);
		exhaustSDL.draw(sb);
		exhaustSDR.draw(sb);
		
    }
	
	
	public void dispose() {
	}
	

   private  void accelerateUpY(float delta) {
	   if (moveStepY < 650) {
	       moveStepY += accelerationUp * delta;
	   }
   } 
   
   private void fallY(float delta) {
	    if (moveStepY >= 0) {
	        moveStepY -= accelerationDown * delta;
	    }
  }

   private  void accelerateUpX(float delta) {
	   if (moveStepX < 650) {
		   moveStepX += accelerationUp * delta;
	   }
   }

  
	
   private void fallX(float delta) {
	    if (moveStepX >= 0) {
	    	moveStepX -= accelerationDown * delta;
	    }
	}
	
	

}
