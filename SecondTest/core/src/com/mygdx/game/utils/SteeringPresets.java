package com.mygdx.game.utils;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.behaviors.Flee;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.elements.simpleenemy.IASteeringBehaviourEnemiesObject;

/**
 * Steering Strategies
 * @author jeag2
 *
 */

public class SteeringPresets {
	
	//OLD
	
	public static Arrive<Vector2> arrive(IASteeringBehaviourEnemiesObject entity, IASteeringBehaviourEnemiesObject target, boolean active) {
		
		Arrive<Vector2> arriveSB = new Arrive<Vector2>(entity,target)
				.setArrivalTolerance(2f)
				//.setArrivalTolerance(4.5f)
				.setDecelerationRadius(10);
		
		arriveSB.setEnabled(active);
		return arriveSB;	
	}
	
	
	public static Wander<Vector2> wander(IASteeringBehaviourEnemiesObject entity, boolean active){
		Wander<Vector2> wander = new Wander<Vector2>(entity)
				.setFaceEnabled(false) 
				.setWanderOffset(5f) 
				.setWanderOrientation(180f) 
				.setWanderRadius(10f)  
				.setWanderRate(MathUtils.PI2 * 4); 
		wander.setEnabled(active);
		return wander;
	}
	
	public static Seek<Vector2> seek(IASteeringBehaviourEnemiesObject entity, IASteeringBehaviourEnemiesObject target, boolean active){
		Seek<Vector2> seek = new Seek<Vector2>(entity,target);
		seek.setEnabled(active);
		return seek;
	}
	
	public static Flee<Vector2> flee(IASteeringBehaviourEnemiesObject entity, IASteeringBehaviourEnemiesObject target, boolean active){
		Flee<Vector2> flee = new Flee<Vector2>(entity, target);
		flee.setEnabled(active);
		return flee;
	}
	
	/*
	public static Arrive<Vector2> arrive(IASteeringBehaviourEnemiesObject entity, IASteeringBehaviourEnemiesObject target, boolean active){
		Arrive<Vector2> arrive = new Arrive<Vector2>(entity, target)
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(7f) 
				.setDecelerationRadius(10f);
		arrive.setEnabled(active);
		return arrive;
	}
	*/
}
	

