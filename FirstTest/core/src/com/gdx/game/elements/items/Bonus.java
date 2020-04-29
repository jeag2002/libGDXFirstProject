package com.gdx.game.elements.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicCollObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.stages.enums.BonusTypeEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Bonus extends DynamicCollObject implements SpawnObject {
	
	
	private Vector2 position = new Vector2();
	
	private Texture[] text_bonus;
    private boolean spawned;
    private SpawnPool pool;
    
    private BonusTypeEnum typeBonus;
    private SpawnType type;
   
	public Bonus(SpawnType type, World world) {
		super(world);
		pool = null;
		this.type = type;
	}
	
	
	public void init(BonusTypeEnum type, float xStart, float yStart) {
		
		position.set(xStart, yStart);
		
	    this.typeBonus = type;
	    text_bonus = new Texture[1];
	    
	    
	    if (typeBonus.equals(type.BonusAmmo)) {
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_A,Texture.class); 
	    }else if (typeBonus.equals(type.BonusEnergy)) {
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_E,Texture.class); 
	    }else if (typeBonus.equals(type.BonusHealth)){
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_H,Texture.class); 
	    }else if (typeBonus.equals(type.BonusShield)){
	    	text_bonus[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgPower_S,Texture.class); 
	    }
	    
	    super.init(text_bonus,0);
		super.setPosition(xStart, yStart);
		super.setSize(32, 16);
		super.setSpeed(0,0);
		super.setReference(this);
		super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
	}
	

	@Override
	public void setSpawned(boolean spawned) {		
		this.spawned = spawned;
	}

	@Override
	public boolean isSpawned() {
		return this.spawned;
	}

	@Override
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		super.setPosition(FirstTestGDX.screenWidth, 0);
	}

	@Override
	public void update(float delta, float boostFactor) {	
		/**/
	}
	

	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}
	

	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
	}

	@Override
	public void AnimationByTime(float delta) {
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
	}

}
