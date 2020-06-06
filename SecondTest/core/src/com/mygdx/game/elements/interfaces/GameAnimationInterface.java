package com.mygdx.game.elements.interfaces;

import com.mygdx.game.enums.PlayerMovementsEnum;

public interface GameAnimationInterface {
	
    public abstract void AnimationByMovement(PlayerMovementsEnum movement, float moveStepX, float moveStepY, boolean isAccX, boolean isAccY);
    public abstract void AnimationByTime(float delta);
    public abstract void AnimationLoop(float delta, boolean loop);

}
