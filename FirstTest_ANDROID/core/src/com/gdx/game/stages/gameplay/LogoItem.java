package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.LaserTypePlayer;
import com.gdx.game.stages.enums.MissileTypeEnum;

public class LogoItem extends Actor {
	
	Sprite base;
	Sprite typeOfFire;
	GamePlayScreen gPS;
	boolean isVisible;
	
	Texture textMissile_1;
	Texture textMissile_2;
	Texture textMissile_3;
	Texture textMissile_4;
	
	
	
	public LogoItem(Texture textureBase, GamePlayScreen gPS) {
		
		this.base = new Sprite(textureBase);
		
		textMissile_1 = FirstTestGDX.resources.get(FirstTestGDX.resources.laser_small,Texture.class);
		textMissile_2 = FirstTestGDX.resources.get(FirstTestGDX.resources.imgIcon_2,Texture.class);
		textMissile_3 = FirstTestGDX.resources.get(FirstTestGDX.resources.imgIcon_3,Texture.class);
		textMissile_4 = FirstTestGDX.resources.get(FirstTestGDX.resources.imgIcon_4,Texture.class);
		
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
			
			
			
			if (gPS.getgLL().getShootTypePlayer().equals(LaserTypePlayer.LASER_LEVEL_1)) {
				
				typeOfFire.setTexture(textMissile_1);
				typeOfFire.setPosition(this.base.getX() + this.base.getWidth()/2 - 5, this.base.getY()+20);
				typeOfFire.setSize(10,32);
				
			}else if (gPS.getgLL().getShootTypePlayer().equals(LaserTypePlayer.LASER_LEVEL_2)) {
				
				typeOfFire.setTexture(textMissile_2);
				typeOfFire.setPosition(this.base.getX() + this.base.getWidth()/2 - 10, this.base.getY()+20);
				typeOfFire.setSize(20,32);
				
			}else if (gPS.getgLL().getShootTypePlayer().equals(LaserTypePlayer.LASER_LEVEL_2_1)) {
				
				typeOfFire.setTexture(textMissile_3);
				typeOfFire.setSize(64,32);
				typeOfFire.setOriginCenter();
				typeOfFire.setOriginBasedPosition(this.base.getX() + this.base.getWidth()/2, this.base.getY() + this.base.getHeight() / 2);
				
			}else if (gPS.getgLL().getShootTypePlayer().equals(LaserTypePlayer.LASER_LEVEL_3)) {
				
				typeOfFire.setTexture(textMissile_4);
				typeOfFire.setSize(64,32);
				typeOfFire.setOriginCenter();
				typeOfFire.setOriginBasedPosition(this.base.getX() + this.base.getWidth()/2, this.base.getY() + this.base.getHeight() / 2);
				
			}
			
			this.base.draw(batch);
			this.typeOfFire.draw(batch);
		}
	}
	
	

}
