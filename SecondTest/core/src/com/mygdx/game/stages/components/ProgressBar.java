package com.mygdx.game.stages.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;


public class ProgressBar extends Actor {
	
	Sprite base;
	Sprite step[];
	GamePlayScreen gPS;
	ElementsGUI typeProgressBar;
	
	
	boolean isVisible;
	
	public static final int MAX_STEPS = 10;
	public int limit = 0;
	
	
	public ProgressBar(ElementsGUI typeProgressBar, Texture textureBase, Texture textureStep, GamePlayScreen gPS) {
		
		this.typeProgressBar = typeProgressBar;
		this.base = new Sprite(textureBase);
		this.step = new Sprite[MAX_STEPS];
		for(int i=0; i<MAX_STEPS; i++) {this.step[i] = new Sprite(textureStep);}
		
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
			
			float width = 0.0f;
			float height = 0.0f;
			
			
			if (typeProgressBar.equals(ElementsGUI.PROGRESSBARAMMO)) {
				width = this.base.getWidth()/14.5f;
				height = this.base.getHeight()-10;
			}else {	
				width = this.base.getWidth()/14;
				height = this.base.getHeight()-10;
			}
			
			
			int limitProgressBar = 0;
			
			if (typeProgressBar.equals(ElementsGUI.PROGRESSBARLIFE)) {
				limitProgressBar = gPS.getGamePlay().getGameLogic().getPlayer().getStatsDynElement().getLife();
			}else if (typeProgressBar.equals(ElementsGUI.PROGRESSBARSHIELD)) {
				limitProgressBar = gPS.getGamePlay().getGameLogic().getPlayer().getStatsDynElement().getShield();
			}else if (typeProgressBar.equals(ElementsGUI.PROGRESSBARAMMO)) {
				limitProgressBar = gPS.getGamePlay().getGameLogic().getPlayer().getStatsDynElement().getAmmo()/MAX_STEPS;
			}
			
			for(int i=0; i<limitProgressBar; i++) {
				if (typeProgressBar.equals(ElementsGUI.PROGRESSBARAMMO)) {
					this.step[i].setPosition(this.base.getX()+40+((width+2)*i),this.base.getY()+5);
				}else {
					this.step[i].setPosition(this.base.getX()+35+((width+2)*i),this.base.getY()+5);
				}
				this.step[i].setSize(width, height);
			}
			//}
			
			
			this.base.draw(batch);
			//for(int i=0; i<gPS.getgLL().getLifePlayer(); i++) {this.step[i].draw(batch);}
			for(int i=0; i<limitProgressBar; i++) {this.step[i].draw(batch);}
		}
	}
}
