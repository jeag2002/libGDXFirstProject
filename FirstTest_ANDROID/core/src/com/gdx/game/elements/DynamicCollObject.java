package com.gdx.game.elements;

//import java.util.UUID;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.gdx.game.elements.interfaz.GameAnimationInterface;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.utils.GUID;

/**
 * Dynamic Object without animation
 * @author jeag2
 *
 */

public abstract class DynamicCollObject extends CollisionObject implements GameAnimationInterface{

	private SpriteObject sO;
	
	private String code;
	
	
	public DynamicCollObject(World world) {
		super(world);
		sO = new SpriteObject();
		code = GUID.get();
		
	}
	
	
	public String getCode() {
		return code;
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
	
	public void setSpeed(float x, float y) {
		sO.setSpeed(x, y);
	}
	
	public float getSpeedX() {
		return sO.getSpeedX();
	}
	
	public float getSpeedY() {
		return sO.getSpeedY();
	}
	
	public Sprite getSprite() {
		return sO.getSprite();
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
