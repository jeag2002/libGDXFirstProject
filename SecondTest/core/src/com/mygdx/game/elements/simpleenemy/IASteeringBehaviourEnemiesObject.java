package com.mygdx.game.elements.simpleenemy;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.utils.DFUtils;

public class IASteeringBehaviourEnemiesObject implements Steerable<Vector2>{
	
	private Body body;
	private float radius;
	private boolean tagged;
	
	float maxLinealSpeed, maxLinealAcceleration;
	float maxAngularSpeed, maxAngularAcceleration;
	
	SteeringBehavior<Vector2> behavior;
	SteeringAcceleration<Vector2> steeringOutput;
	
	
	public void iniBehaviour(Body body, float boundingRadius) {
		this.body = body;
		this.radius = boundingRadius;
		
		this.maxLinealSpeed = 500;
		this.maxLinealAcceleration = 5000;
		this.maxAngularSpeed = 30;
		this.maxAngularAcceleration = 5;
		
		this.tagged = false;
		this.steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
		
	}
	
	
	public void updateBehaviour(float delta) {
		if (behavior != null) {
			behavior.calculateSteering(steeringOutput);
			applySteering(delta);
		}
	}
	
	private void applySteering(float delta) {
		boolean anyAccelerations = false;
		
		if (!steeringOutput.linear.isZero()) {
			Vector2 force = steeringOutput.linear.scl(delta);
			body.applyForceToCenter(force,true);
			anyAccelerations = true;
		}
		
		if (steeringOutput.angular != 0) {
			body.applyTorque(steeringOutput.angular*delta, true);
			anyAccelerations = true;
		}else {
			Vector2 linVel = getLinearVelocity();
			if (!linVel.isZero()) {
				float newOrientation = vectorToAngle(linVel);
				body.setAngularVelocity((newOrientation-getAngularVelocity())*delta);
				body.setTransform(body.getPosition(), newOrientation);
			}
		}
		
		
		if (anyAccelerations) {
			Vector2 velocity = body.getLinearVelocity();
			float currentSpeedSquare = velocity.len2();
			if (currentSpeedSquare > Math.pow(maxLinealSpeed, 2)) {
				body.setLinearVelocity(velocity.scl(maxLinealSpeed/(float)Math.sqrt(currentSpeedSquare)));
			}
			
			
			if (body.getAngularVelocity() > maxAngularSpeed) {
				body.setAngularVelocity(maxAngularSpeed);
			}
		}
		
	}

	
	
	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public float getOrientation() {
		return body.getAngle();
	}

	@Override
	public void setOrientation(float orientation) {
		
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return DFUtils.vectorToAngle(vector);
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		return DFUtils.angleToVector(outVector,angle);
	}

	@Override
	public Location<Vector2> newLocation() {
		return null;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return 0;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
	}

	@Override
	public float getMaxLinearSpeed() {
		return this.maxLinealSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinealSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return this.maxLinealAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinealAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return this.maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {	
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return this.maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius() {
		return radius;
	}

	@Override
	public boolean isTagged() {
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void setBehavior(SteeringBehavior<Vector2> behavior) {
		this.behavior = behavior;
	}
	
	public SteeringBehavior<Vector2> getBehavior(){
		return this.behavior;
	}
	
}
