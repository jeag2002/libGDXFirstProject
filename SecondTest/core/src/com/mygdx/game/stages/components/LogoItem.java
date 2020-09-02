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
import com.mygdx.game.enums.ElementEnum;
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
	ShapeRenderer shapeRenderer;
	
	
	public LogoItem(ElementsGUI typeProgressBar, Texture textureBase, GamePlayScreen gPS) {
		
		this.typeProgressBar = typeProgressBar;
		
		this.base = new Sprite(textureBase);	
		this.isVisible = false;
		this.gPS = gPS;
		
		shapeRenderer = new ShapeRenderer();
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
	
	
	private void drawGunIcon(Batch batch) {
		
		Texture texLaser = SecondTestGDX.resources.get(SecondTestGDX.resources.laser, Texture.class);
		Texture texMissile = SecondTestGDX.resources.get(SecondTestGDX.resources.missile_1, Texture.class);
		Texture texFlame = SecondTestGDX.resources.get(SecondTestGDX.resources.flame_4, Texture.class);
		
		Sprite sprite_1 = null;
		Sprite sprite_2 = null;
		
		
		if (gPS.getGamePlay().getGameLogic().getPlayer().getCannonType().equals(ElementEnum.GUN_PLAYER_1_A)) {
			
			sprite_1 = new Sprite(texLaser);
			sprite_1.setSize(16, 32);
			sprite_1.setPosition(X+W/2-8, Y+16);
			sprite_1.draw(batch);
			
		}else if (gPS.getGamePlay().getGameLogic().getPlayer().getCannonType().equals(ElementEnum.GUN_PLAYER_1_B)) {
			
			sprite_1 = new Sprite(texLaser);
			sprite_1.setSize(16, 32);
			sprite_1.setPosition(X+W/2-12, Y+16);
			sprite_1.draw(batch);
			
			sprite_2 = new Sprite(texLaser);
			sprite_2.setSize(16, 32);
			sprite_2.setPosition(X+W/2, Y+16);
			sprite_2.draw(batch);
			
			
		}else if (gPS.getGamePlay().getGameLogic().getPlayer().getCannonType().equals(ElementEnum.GUN_PLAYER_1_C)) {
			
			sprite_1 = new Sprite(texMissile);
			sprite_1.rotate(90);
			sprite_1.setSize(16, 32);
			sprite_1.setPosition(X+W/2, Y+24);
			sprite_1.draw(batch);
			
		}else if (gPS.getGamePlay().getGameLogic().getPlayer().getCannonType().equals(ElementEnum.GUN_PLAYER_1_D)) {
			
			sprite_1 = new Sprite(texFlame);
			sprite_1.setSize(32, 32);
			sprite_1.setPosition(X+W/2-16, Y+16);
			sprite_1.draw(batch);
		}
		
		
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			this.base.draw(batch);
			
			if (typeProgressBar.equals(ElementsGUI.RADAR)) {
				drawPlayerExitPosition(batch);
			}else if (typeProgressBar.equals(ElementsGUI.ICON)) {
				drawGunIcon(batch);
			}
			
		}
	}
	
	
	
	
	

}
