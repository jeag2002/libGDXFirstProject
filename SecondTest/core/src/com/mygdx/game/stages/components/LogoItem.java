package com.mygdx.game.stages.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;

public class LogoItem extends Actor {
	
	Sprite base;
	GamePlayScreen gPS;
	boolean isVisible;
	ElementsGUI typeProgressBar;
	
	public LogoItem(ElementsGUI typeProgressBar, Texture textureBase, GamePlayScreen gPS) {
		
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
