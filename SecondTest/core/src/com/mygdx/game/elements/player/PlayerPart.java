package com.mygdx.game.elements.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.elements.AnimatedObject;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.PlayerPartType;

public class PlayerPart extends AnimatedObject{

	private PlayerPartType playerPartType;
	
	public PlayerPart(PlayerPartType playerPartType) {
		super();
		this.playerPartType = playerPartType;
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
	}

}
