package com.gdx.game.elements.explosions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.AnimatedObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.GameAnimationInterface;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.stages.enums.ExplosionsEnum;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class SimpleExplosion extends AnimatedObject implements GameAnimationInterface, SpawnObject {
	
	
	private SpawnType type;
	private ExplosionsEnum etype;
	private Texture[] simpleexplosion_1;
	
	private boolean spawned;
	private SpawnPool pool;

	private float timer;
	private int index;
	private boolean sensePlus;
	
	
	public SimpleExplosion(SpawnPool pool) {
		super();
		this.pool = pool;
	}
	
	
	public void init(ExplosionsEnum type, float xStart, float yStart) {
		
		this.etype = type;
		
		if (etype.equals(ExplosionsEnum.ExplosionTypeOne)) {
			simpleexplosion_1 = new Texture[9];
			simpleexplosion_1[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_01,Texture.class); 
			simpleexplosion_1[1] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_02,Texture.class);
			simpleexplosion_1[2] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_03,Texture.class);
			simpleexplosion_1[3] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_04,Texture.class); 
			simpleexplosion_1[4] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_05,Texture.class);
			simpleexplosion_1[5] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_06,Texture.class);
			simpleexplosion_1[6] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_07,Texture.class); 
			simpleexplosion_1[7] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_08,Texture.class);
			simpleexplosion_1[8] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExplosion02_Frame_09,Texture.class);
			
			super.init(simpleexplosion_1,0);
			super.setPosition(xStart, yStart);
			super.setSize(64, 64);
			
			this.timer = 0;
			this.index = 0;
			this.sensePlus = true;
		}	
	}
	
	
	@Override
	public void update(float delta, float boostFactor) {
		AnimationByTime(delta);
	}
	
	
	@Override
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}


	@Override
	public boolean isSpawned() {
		return spawned;
	}


	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}


	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationByTime(float delta) {
		timer += delta;
	    if (timer >= 0.05f) {
	    	timer = 0.0f;
			if (this.sensePlus) {
				index++;
				if (index < simpleexplosion_1.length) {setTextureToSpriteByIndex(index);}
				if (index == simpleexplosion_1.length) {sensePlus = false;}
			}else {
				index--;
				if (index >= 0) {setTextureToSpriteByIndex(index);}
				else {
					GameElementLogic.toDeletedBodiesWithoutCollision.add(this);
				}
			}
		}
		
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	
	@Override
	public void kill(SpawnPool pool) {
	}
	


	
	
	
	

}
