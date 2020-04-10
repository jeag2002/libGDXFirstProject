package com.gdx.game.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.elements.interfaz.GameAnimationInterface;
import com.gdx.game.stages.enums.PlayerMovements;

public abstract class AnimatedObject implements GameAnimationInterface{
	
	SpriteObject sO;
	
	public AnimatedObject() {
		super();
		sO = new SpriteObject();
	}
	
	public void init(Texture[] textures, int index) {
		sO.init(textures, index);
		
	}
	
	public void setTextureToSpriteByIndex(int index) {
		sO.setTextureToSpriteByIndex(index);
	}
	
	public Texture getTextureByIndex(int index) {
		return sO.getTextureByIndex(index);
	}
	
	
	
	
	public void setPosition(float x, float y) {
		sO.setPosition(x, y);
	}
	
	public void setSize(float width, float height) {
		sO.setSize(width, height);
	}
	
	public void setSpeed(float x, float y) {
		sO.setSpeed(x, y);
	}
	
	public Sprite getSprite() {
		return sO.getSprite();
	}
	
	public float getX() {
		return sO.getX();
	}

	public float getY() {
		return sO.getY();
	}
	
	public void setX(float x) {
		sO.setX(x);
	}

	public void setY(float y) {
		sO.setY(y);
	}
	
	
	
	public float getWidth() {
		return sO.getWidth();
	}

	public float getHeight() {
		return sO.getHeight();
	}
	
	
	public void draw(SpriteBatch sb) {
		sO.draw(sb);
	}
	

	@Override
	public abstract void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY,boolean isAccX, boolean isAccY);
	@Override
	public abstract void AnimationByTime(float delta);
	@Override
	public abstract void AnimationLoop(float delta, boolean loop);
	

}
