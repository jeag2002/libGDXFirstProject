package com.mygdx.game.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.enums.DynamicElementPartType;

public class DynElementPart extends AnimatedObject{

	private DynamicElementPartType dynElementPartType;
	
	private static final float LIMIT = 0.05f;
	private static final float LIMIT_BONUS = 0.1f; 
	private float timer;
	private boolean flag;
	
	private static final int POSITION_1 = 0;
	private static final int POSITION_2 = 1;

	
	private int actPosition;
	
	
	public DynElementPart(DynamicElementPartType playerPartType) {
		super();
		this.dynElementPartType = playerPartType;
		this.timer = 0;
		this.flag = false;
		this.actPosition = 0;
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
		if (dynElementPartType.equals(DynamicElementPartType.TRACK_LEFT_PLAYER) || 
			dynElementPartType.equals(DynamicElementPartType.TRACK_RIGHT_PLAYER) ||
			dynElementPartType.equals(DynamicElementPartType.TRACK_LEFT_ENEMY_2) ||
			dynElementPartType.equals(DynamicElementPartType.TRACK_RIGHT_ENEMY_2)) {
			AnimationPlayerLoopTrack(delta,loop);
		}else if (
			dynElementPartType.equals(DynamicElementPartType.EXHAUST_LEFT_PLAYER) || 
			dynElementPartType.equals(DynamicElementPartType.EXHAUST_RIGHT_PLAYER) ||
			dynElementPartType.equals(DynamicElementPartType.EXHAUST_LEFT_ENEMY_2) ||
			dynElementPartType.equals(DynamicElementPartType.EXHAUST_RIGHT_ENEMY_2)) {
			AnimationPlayerLoopExhaust(loop);
		}else if (dynElementPartType.equals(DynamicElementPartType.BONUS)) {
			AnimationBonusLoop(delta,loop);
		}
	}
	
	private void AnimationBonusLoop(float delta, boolean loop) {
		
		if (loop) {
			timer += delta;
			if (timer > LIMIT_BONUS) {
				timer = 0;
				
				if (actPosition == POSITION_1) {
					this.setSize(64, 64);
					this.actPosition = POSITION_2;
				}else if (actPosition == POSITION_2) {
					this.setSize(66, 66);
					this.actPosition = POSITION_1;
				}
				
			}
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
