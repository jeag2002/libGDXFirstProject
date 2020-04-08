package com.gdx.game.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Dynamic Object without animation
 * @author jeag2
 *
 */

public class DynamicObject extends CollisionObject{

	private Sprite sprite;
	
	private float speedX;
	private float speedY;
	
	private Texture[] textures;
	
	private float timer;
	private int index;
	
	public DynamicObject() {
		super();
		sprite = null;
		timer = 0;
		index = 0;
	}
	
	public void init(Texture[] textures) {
		this.textures = textures;
		sprite = new Sprite(textures[0]);
		
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}
	
	public void setSize(float width, float height) {
		sprite.setSize(width, height);
	}
	
	public void setSpeed(float x, float y) {
		this.speedX = x;
		this.speedY = y;
	}
	
	public void animate(float delta) {
		
		timer += delta;
	
	    if (timer >= 0.005f) {
	    	timer = 0.0f;
			index++;
			if (index < textures.length) {sprite.setTexture(textures[index]);}
		}
		
	}
	
	public void draw(SpriteBatch sb) {
		sprite.draw(sb);
	}
	
	
	
	
	
	
}
