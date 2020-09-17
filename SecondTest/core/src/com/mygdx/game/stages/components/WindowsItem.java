package com.mygdx.game.stages.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WindowsItem extends Actor{
	
	private Sprite base;
	private Sprite logo;
	private boolean isVisible;

	public WindowsItem(Texture textureBase, Texture textureLogo) {
		this.base = new Sprite(textureBase);
		this.logo = new Sprite(textureLogo);
		this.isVisible = false;
	}

	
	public void setBound(float X, float Y, float width, float height) {
		this.base.setPosition(X, Y);
		this.base.setSize(width, height);
		this.logo.setSize((width/3)*2, (height/3)*2);
		
		this.logo.setOriginCenter();
		this.logo.setOriginBasedPosition(this.base.getX()+ this.base.getWidth()/2, this.base.getY()+ this.base.getHeight() / 2);
		
		this.setX(X);
		this.setY(Y);
		this.setWidth(width);
		this.setHeight(height);
		
	
	}

	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public void setTexture(Texture textureBase, Texture textureLogo) {
		this.base.setTexture(textureBase);
		this.logo.setTexture(textureLogo);
	}
	
	public void setTextureBase(Texture textureBase) {
		this.base.setTexture(textureBase);
	}
	
	public void setTextureLogo(Texture textureLogo) {
		this.logo.setTexture(textureLogo);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		this.setBound(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			this.base.draw(batch);
			this.logo.draw(batch);
		}
	}
	
	
}
