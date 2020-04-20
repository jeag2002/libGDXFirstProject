package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.game.screens.GamePlayScreen;

public class ProgressBar extends Actor {
	
	Sprite base;
	Sprite step[];
	GamePlayScreen gPS;
	
	boolean isVisible;
	
	public ProgressBar(Texture textureBase, Texture textureStep, GamePlayScreen gPS) {
		this.base = new Sprite(textureBase);
		this.step = new Sprite[10];
		for(int i=0; i<10; i++) {this.step[i] = new Sprite(textureStep);}
		
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
			
			float width = this.base.getWidth()/14;
			float height = this.base.getHeight()-10;
			
			for(int i=0; i<10; i++) {
				this.step[i].setPosition(this.base.getX()+5+((width+2)*i),this.base.getY()+5);
				this.step[i].setSize(width, height);
			}
			
			
			this.base.draw(batch);
			for(int i=0; i<10; i++) {this.step[i].draw(batch);}
		}
	}
}
