package com.gdx.game.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteObject {
	
	private Sprite sprite;
	private float speedX;
    private float speedY;
    private Texture[] textures;
    private float timer;
    private int index;
		
    public SpriteObject() {
		super();
		sprite = null;
		timer = 0;
		index = 0;
	}
		
	public void init(Texture[] textures, int index) {
		this.textures = textures;
		sprite = new Sprite(textures[index]);		
	}
	
	public void setTextureToSpriteByIndex(int index) {
		if (index >= 0 && index < textures.length) {
			sprite.setTexture(this.textures[index]);
		}
	}
	
	public Texture getTextureByIndex(int index) {
		if (index >= 0 && index < textures.length) {
			return this.textures[index];
		}else {
			return null;
		}
	}
	
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}
		
	public void setSize(float width, float height) {
		sprite.setSize(width, height);
	}
	
	public void setRotate(float angle) {
		
		sprite.setRotation(angle);
		sprite.setOriginCenter();
		sprite.setOriginBasedPosition(sprite.getX() + sprite.getWidth()/2+64, sprite.getY() + sprite.getHeight() / 2+64);
	}
	
	
	public float getX() {
		return sprite.getX();
	}

	public float getY() {
		return sprite.getY();
	}
	
	public void setX(float x) {
		sprite.setX(x);
	}

	public void setY(float y) {
		sprite.setY(y);
	}
	
	
	public float getWidth() {
		return sprite.getWidth();
	}

	public float getHeight() {
		return sprite.getHeight();
	}

	public void setSpeed(float x, float y) {
		this.speedX = x;
		this.speedY = y;
	}
	
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	
	public Sprite getSprite() {
		return sprite;
	}
		
	public void draw(SpriteBatch sb) {
		sprite.draw(sb);
	}
	
}
