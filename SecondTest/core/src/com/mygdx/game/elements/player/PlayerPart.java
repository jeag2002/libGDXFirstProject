package com.mygdx.game.elements.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.elements.AnimatedObject;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.PlayerPartType;

public class PlayerPart extends AnimatedObject{

	private PlayerPartType playerPartType;
	
	private static final float LIMIT = 0.05f;
	private float timer;
	private boolean flag;
	
	public PlayerPart(PlayerPartType playerPartType) {
		super();
		this.playerPartType = playerPartType;
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
		if (playerPartType.equals(PlayerPartType.TRACK_LEFT) || playerPartType.equals(PlayerPartType.TRACK_RIGHT)) {
			AnimationLoopTrack(delta,loop);
		}else if (playerPartType.equals(PlayerPartType.EXHAUST_LEFT) || playerPartType.equals(PlayerPartType.EXHAUST_RIGHT)) {
			AnimationLoopExhaust(loop);
		}
	}
	
	private void AnimationLoopExhaust(boolean loop) {
		
		if (loop) {this.setTextureToSpriteByIndex(0);}
		else {this.setTextureToSpriteByIndex(6);}
		
	}
	
	private void AnimationLoopTrack(float delta, boolean loop) {	
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
