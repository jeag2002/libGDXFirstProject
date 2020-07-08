package com.mygdx.game.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.DynamicElementPartType;

public class DynElementPart extends AnimatedObject{

	private DynamicElementPartType dynElementPartType;
	
	private static final float LIMIT = 0.05f;
	private float timer;
	private boolean flag;
	
	public DynElementPart(DynamicElementPartType playerPartType) {
		super();
		this.dynElementPartType = playerPartType;
		this.timer = 0;
		this.flag = false;
	}
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
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
		if (dynElementPartType.equals(DynamicElementPartType.TRACK_LEFT_PLAYER) || dynElementPartType.equals(DynamicElementPartType.TRACK_RIGHT_PLAYER)) {
			AnimationPlayerLoopTrack(delta,loop);
		}else if (dynElementPartType.equals(DynamicElementPartType.EXHAUST_LEFT_PLAYER) || dynElementPartType.equals(DynamicElementPartType.EXHAUST_RIGHT_PLAYER)) {
			AnimationPlayerLoopExhaust(loop);
		}
	}
	
	private void AnimationPlayerLoopExhaust(boolean loop) {
		
		if (loop) {this.setTextureToSpriteByIndex(0);}
		else {this.setTextureToSpriteByIndex(6);}
		
	}
	
	private void AnimationPlayerLoopTrack(float delta, boolean loop) {	
		if (loop) {
			timer += delta;
			if (timer > LIMIT) {
				timer = 0;
				flag = !flag;
				this.setTextureToSpriteByIndex(Boolean.compare(flag, false));
				
			}
		}
	}

}
