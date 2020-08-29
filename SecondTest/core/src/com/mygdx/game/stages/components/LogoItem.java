package com.mygdx.game.stages.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;

public class LogoItem extends Actor {
	
	Sprite base;
	GamePlayScreen gPS;
	boolean isVisible;
	ElementsGUI typeProgressBar;
	float X;
	float Y;
	float W;
	float H;
	
	
	public LogoItem(ElementsGUI typeProgressBar, Texture textureBase, GamePlayScreen gPS) {
		
		this.typeProgressBar = typeProgressBar;
		
		this.base = new Sprite(textureBase);	
		this.isVisible = false;
		this.gPS = gPS;
	}	
	
	public void setPosition(float X, float Y) {
		this.base.setPosition(X, Y);
		this.X = X;
		this.Y = Y;
	}
	
	public void setSize(float width, float height) {
		this.base.setSize(width, height);
		this.W = width;
		this.H = height;
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	 
	
	private void drawPlayerExitPosition(Batch batch) {
		
		int player_x = Math.round(gPS.getGamePlay().getGameLogic().getPlayer().getX()); 
		int player_y = Math.round(gPS.getGamePlay().getGameLogic().getPlayer().getY());
		
		int exit_x =  Math.round(gPS.getGamePlay().getExit().getX());
		int exit_y =  Math.round(gPS.getGamePlay().getExit().getY());
		
		player_x = player_x /SecondTestGDX.tileWidth_TL;
		player_y = player_y /SecondTestGDX.tileHeight_TL;
		
		exit_x = exit_x /SecondTestGDX.tileWidth_TL;
		exit_y = exit_y /SecondTestGDX.tileHeight_TL;
		
		
		float lim_X = X + W/3 + 10;
		float lim_Y = Y + 5;
		
		batch.flush();
		batch.end();  
		
		
	    Gdx.gl.glEnable(GL20.GL_ARRAY_BUFFER_BINDING);
		
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		
		for(int i=0; i<SecondTestGDX.sizeMapTileWidth_TL; i++) {
			for(int j=0; j<SecondTestGDX.sizeMapTileHeight_TL; j++) {
				
				if ((i == player_y) && (j == player_x)){
					shapeRenderer.setColor(Color.RED);
					shapeRenderer.begin(ShapeType.Filled);
					shapeRenderer.circle(lim_X +j*2, lim_Y+i*2, 4);
					shapeRenderer.flush();
				    shapeRenderer.end();
				}else if ((i == exit_y) && (j == exit_x)) {
					shapeRenderer.setColor(Color.YELLOW);
					shapeRenderer.begin(ShapeType.Filled);
					shapeRenderer.circle(lim_X +j*2, lim_Y+i*2, 4);
					shapeRenderer.flush();
					shapeRenderer.end();
				}
				
			}
		}
		
	   Gdx.gl.glDisable(GL20.GL_BLEND);
	   batch.begin();
		
		
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			this.base.draw(batch);
			
			if (typeProgressBar.equals(ElementsGUI.RADAR)) {
				drawPlayerExitPosition(batch);
			}
			
		}
	}
	
	
	
	
	

}
