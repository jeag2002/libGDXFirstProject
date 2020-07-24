package com.mygdx.game.elements.items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.elements.DynElementPart;
import com.mygdx.game.elements.players.DynamicCollPlayerObject;
import com.mygdx.game.enums.DynamicElementPartType;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.GameLogicElementInformation;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class Item extends DynamicCollPlayerObject implements SpawnObject{

	private static final int INDEX_SUBITEM = 0;
	private static final float TRANSITION_BETWEEN_ANIM = 0.05f;
	
	
	private GamePlayScreen gPS;
	
	private SpawnType type;
	private SpawnType subType;
	
    private SpawnPool spawnPool;
	
	private boolean isSpawned;
	
	private ArrayList<DynElementPart> item_parts;
	
	private PointLight light;
	private float timer;
	private int index;
	
	
	
	public Item(SpawnPool spawnPool, SpawnType type, World world, GamePlayScreen gPS) {
		super(world,type);
		this.gPS = gPS;
		this.type = type;
		this.timer = 0;
		this.index = 0;
		this.spawnPool = spawnPool;
		this.isSpawned = false;
		this.item_parts = new ArrayList<DynElementPart>();
	}	
	
	
	public void init (RayHandler rayHandler, SpawnType subtype, float iniPositionX, float iniPositionY, float width,  float height) {
		
		this.subType = subtype;
		
		
		setAnimation();
		setAnimationPart();
		
		setSize(width, height);
		setPosition(iniPositionX, iniPositionY);
		setPositionPart(iniPositionX, iniPositionY);
		setSpeed(0,0);
		
		createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		
		if (!type.equals(SpawnType.Item_Mine)) {
			this.light = new PointLight(rayHandler, 20, Color.WHITE, 1, 0, 0);
			this.light.setSoftnessLength(0f);
			this.light.attachToBody(this.getBody());
		}
	}
	
	
	public void setPositionPart(float X, float Y) {
		
		if (subType.equals(SpawnType.Item_PlatformPlayer) || subType.equals(SpawnType.Item_PlatformEnemy) || subType.equals(SpawnType.Item_PlatformEndLevel)) {
			item_parts.get(INDEX_SUBITEM).getSprite().setOriginCenter();
			item_parts.get(INDEX_SUBITEM).getSprite().setOriginBasedPosition(getX() + getWidth()/2, getY() + getHeight() / 2);
		}
		
	}
	
	
	public void setAnimationPart() {
		
		if (subType.equals(SpawnType.Item_PlatformPlayer) || subType.equals(SpawnType.Item_PlatformEnemy) || subType.equals(SpawnType.Item_PlatformEndLevel)) {
			
			DynElementPart badge = new DynElementPart(DynamicElementPartType.BADGE);
			
			if (subType.equals(SpawnType.Item_PlatformPlayer)) {
				badge.init(GameLogicElementInformation.dot_red, 0);
			}else if (subType.equals(SpawnType.Item_PlatformEnemy)) {
				badge.init(GameLogicElementInformation.dot_blue, 0);
			}else {
				badge.init(GameLogicElementInformation.dot_end, 0);
			}
			
			badge.setSize(ElementEnum.PLATFORM_DOT_R.getWidthShow(), ElementEnum.PLATFORM_DOT_R.getHeightShow());
	    	item_parts.add(badge);
			
		}
		
	}
	
	
	public void setAnimation() {
		
		if (subType.equals(SpawnType.Item_PlatformPlayer) || subType.equals(SpawnType.Item_PlatformEnemy) || subType.equals(SpawnType.Item_PlatformEndLevel)) {
			
			Texture[] platformTXT = GameLogicElementInformation.platform;
    		init(platformTXT,0);
			
		}else if (subType.equals(SpawnType.Item_Mine)) {
			
			Texture[] mine = GameLogicElementInformation.Enemy_03;
			init(mine, 0);
			
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
		
		this.timer += delta;
		
		if (subType.equals(SpawnType.Item_Mine)) {
			if (timer > TRANSITION_BETWEEN_ANIM) {
				timer = 0;
				index++;
				if (index >= GameLogicElementInformation.Enemy_03.length) {index = 0;}
				setTextureToSpriteByIndex(index); 	
			}	
		}
		
		
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
		this.spawnPool = pool;
	}


	@Override
	public void kill(SpawnPool pool) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(float delta, float boostFactor) {
		setCollisionRef(getX(),getY());
		AnimationLoop(delta, true);
	}

	@Override
	public void draw(SpriteBatch sb) {
    	super.draw(sb);
    	if (subType.equals(SpawnType.Item_PlatformPlayer) || subType.equals(SpawnType.Item_PlatformEnemy) || subType.equals(SpawnType.Item_PlatformEndLevel)) {
    		item_parts.get(this.INDEX_SUBITEM).draw(sb);
    	}
	}	
	
	
	
	
}
