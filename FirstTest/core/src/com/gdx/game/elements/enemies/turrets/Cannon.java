package com.gdx.game.elements.enemies.turrets;

import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.elements.ShootObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.stages.enums.PlayerMovements;

public class Cannon extends ShootObject implements SpawnObject {
	
	public Cannon(SpawnPool sP, World world) {
		super(sP, world); 
	}
	
	@Override
	public void setSpawned(boolean spawned) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPool(SpawnPool pool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill(SpawnPool pool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, float boostFactor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
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
