package com.mygdx.game.elements.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.ShootObject;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.PlayerPartType;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;

public class Player extends ShootObject{
	
	
	private static final int INDEX_TRACK_LEFT = 0;
	private static final int INDEX_TRACK_RIGHT = 1;
	private static final int GUN = 2;
	//private static final int EXHAUST_LEFT = 2;
	//private static final int EXHAUST_RIGHT = 3;
	//private static final int GUN = 4;
	
	
    private GamePlayScreen gPS;
    private DrawUtils dU;
    private ArrayList<PlayerPart> player_parts;
    private SpawnType type;
    private ElementEnum cannonType;
   
    
    public Player(SpawnPool spawnPool, SpawnType type, ElementEnum cannonType, World world, GamePlayScreen gPS) {
    	super(spawnPool, world);
    	
    	this.gPS = gPS;
    	
    	this.type = type;
    	this.cannonType = cannonType;
    	
    	this.dU = new DrawUtils();
    	this.player_parts = new ArrayList<PlayerPart>();
    	
    }
    
	
    public void setLocationAndSize(float iniPositionX, float iniPositionY, float width, float height) {
		
		setAnimation();
		setAnimationParts(iniPositionX,iniPositionY,width,height);
		
		
		
		setReference(this);
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		
		
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
    	
    	

    }
    
    
    public void update(float delta) {
    	super.update(delta);
    }
    
    public void draw(SpriteBatch sb) {
    	player_parts.get(INDEX_TRACK_LEFT).draw(sb);
    	player_parts.get(INDEX_TRACK_RIGHT).draw(sb);
    	//player_parts.get(EXHAUST_LEFT).draw(sb);
    	//player_parts.get(EXHAUST_RIGHT).draw(sb);
    	/*HULL*/super.draw(sb);
    	player_parts.get(GUN).draw(sb);
    	
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
