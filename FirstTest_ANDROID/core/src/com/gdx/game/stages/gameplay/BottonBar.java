package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.utils.StringUtils;
import com.gdx.game.utils.TimeConversion;

public class BottonBar extends Actor {
	
	Sprite counters;
	
	Sprite clock;
	Sprite crystal;
	
	GamePlayScreen gPS;
	BitmapFont font;
	
	float time;
	
	
	boolean isVisible;
	
	public BottonBar(Texture textureBase, Texture textureClock, Texture textureCrystal, GamePlayScreen gPS) {
		this.counters = new Sprite(textureBase);
		
		this.clock = new Sprite(textureClock);
		this.crystal = new Sprite(textureCrystal);
		
		
		this.isVisible = false;
		this.gPS = gPS;
		
		this.time = 0.0f;
		
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
			
			//BACKGROUND
			////////////////////////////////////////////////////////
			this.counters.draw(batch);
			////////////////////////////////////////////////////////
			
			//TIMER
			////////////////////////////////////////////////////////
			this.clock.setSize(16, 16);
			this.clock.setPosition(FirstTestGDX.screenWidth/2 - 190,this.counters.getY()+10);
			this.clock.draw(batch);
			
			int timeSeconds = Math.round(gPS.getgLL().getTime());
			String timeStr = TimeConversion.getDurationString(timeSeconds);
			this.font.draw(batch, timeStr, FirstTestGDX.screenWidth/2 - 172, this.counters.getY()+29);
			/////////////////////////////////////////////////////////
			
			//SCORE
			//////////////////////////////////////////////////////////
			String print = StringUtils.leftPaddedString(10, gPS.getgLL().getScorePlayer());
			this.font.draw(batch, print,FirstTestGDX.screenWidth/2 - 55 , this.counters.getY()+29);
			///////////////////////////////////////////////////////////
			
			
			//AMMO
			///////////////////////////////////////////////////////////
			this.crystal.setSize(16,16);
			this.crystal.setPosition(FirstTestGDX.screenWidth/2 + 80,this.counters.getY()+10);
			this.crystal.draw(batch);
			
			print = gPS.getgLL().getShoot();
			this.font.draw(batch, print,FirstTestGDX.screenWidth/2 + 100 , this.counters.getY()+29);
			///////////////////////////////////////////////////////////
			
			
			
		}
	}
}
	
	
