package com.gdx.game.elements.interfaz;

import com.gdx.game.stages.enums.PlayerMovements;

public interface GameAnimationInterface {
	
    public abstract void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX, boolean isAccY);
    public abstract void AnimationByTime(float delta);
    public abstract void AnimationLoop(float delta, boolean loop);

}
