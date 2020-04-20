package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;

public class BottonBar extends Actor {
	
	Sprite counters;
	GamePlayScreen gPS;
	BitmapFont font;
	
	
	boolean isVisible;
	
	public BottonBar(Texture textureBase, GamePlayScreen gPS) {
		this.counters = new Sprite(textureBase);
		this.isVisible = false;
		this.gPS = gPS;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(0.50f, 0.6f);
		
	}	
	
	public void setPosition(float X, float Y) {
		this.counters.setPosition(X, Y);
	}
	
	public void setSize(float width, float height) {
		this.counters.setSize(width, height);
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
			
			String value = String.valueOf(gPS.getgLL().getScorePlayer());
			String print = "";
			
			for(int i=0; i< 10-value.length(); i++) {print += "0";}
			print+=value;
			
			//String value = String.format("%010d",gPS.getgLL().getScorePlayer());
			this.counters.draw(batch);
			this.font.draw(batch, print,FirstTestGDX.screenWidth/2 - 55 , this.counters.getY()+29);
		}
	}
}
	
	
