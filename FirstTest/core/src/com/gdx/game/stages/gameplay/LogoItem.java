package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.MissileTypeEnum;

public class LogoItem extends Actor {
	
	Sprite base;
	Sprite typeOfFire;
	GamePlayScreen gPS;
	boolean isVisible;
	
	Texture textMissile_1;
	
	
	
	public LogoItem(Texture textureBase, GamePlayScreen gPS) {
		
		this.base = new Sprite(textureBase);
		
		textMissile_1 = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_small,Texture.class);
		
		this.typeOfFire = new Sprite(textMissile_1);
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
			
			this.typeOfFire.setPosition(this.base.getX() + this.base.getWidth()/2 - 5, this.base.getY()+20);
			this.typeOfFire.setSize(10,32);
			
			
			if (gPS.getgLL().getShootTypePlayer().equals(MissileTypeEnum.LASER_1)) {
				typeOfFire.setTexture(textMissile_1);
			}
			
			this.base.draw(batch);
			this.typeOfFire.draw(batch);
		}
	}
	
	

}
