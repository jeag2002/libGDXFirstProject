package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.screens.GamePlayScreen;

public class WindowsItem extends Actor {
	
	Sprite base;
	GamePlayScreen gPS;
	boolean isVisible;
	
	public WindowsItem(Texture textureBase, GamePlayScreen gPS) {
		
		this.base = new Sprite(textureBase);
		this.isVisible = false;
		this.gPS = gPS;
		
	}
	
	public void setPosition(float X, float Y) {
		this.base.setPosition(X, Y);
	}
	
	public void setSize(float width, float height) {
		this.base.setSize(width, height);
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public void setTexture(Texture textureBase) {
		this.base.setTexture(textureBase);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			this.base.draw(batch);
		}
	}
	
	
	
	

}
