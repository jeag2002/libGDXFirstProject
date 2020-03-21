package com.gdx.game.engine.parallax;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxBackground {
	
	private ParallaxLayer[] layers;
	
	protected float x;
	protected float y;
	
	private boolean shaking;
	private float shakeDuration = 0.05f;
	private float shakeDurationGeneral = 0.3f;
	private float shakeDirection = 1.0f;
	private float shakeDistance = 2.0f;
	private float shakeTimer;
	
	public ParallaxBackground(ParallaxLayer[] pLayers) {
		layers = pLayers;
		y = 0;
		x = 0;
	}
	
	public void draw(SpriteBatch sb) {
		for(int i=0; i<layers.length; i++) {
			layers[i].draw(sb, this.x, this.y);
		}
	}
	
	public void move(float delta, float xSpeed, float ySpeed) {
		if (shaking) {
			calcShake(delta);
		}
		for (int i=0; i<layers.length; i++) {
			layers[i].moveRelative(xSpeed * delta, ySpeed * delta);
		}
	}
	
	public void resetPos() {
		this.x = 0;
		this.y = 0;
	}

	
	
	public void shake() {
		this.shaking = true;
	}

	private void calcShake(float delta) {
		shakeTimer += delta;
		x = shakeDirection * shakeDistance;
		y = shakeDirection * shakeDistance;
		if (shakeTimer >= shakeDuration) {
			shakeDirection = -shakeDirection;
		}
		if (shakeTimer >= shakeDurationGeneral) {
			shaking = false;
			shakeTimer = 0.0f;
			resetPos();
		}
	}

}
