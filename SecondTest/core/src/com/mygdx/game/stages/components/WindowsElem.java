package com.mygdx.game.stages.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.StringUtils;

public class WindowsElem extends Actor {
	
	private Sprite base;
	private GamePlayScreen gPS;
	private boolean isVisible;
	private ElementsGUI type;
	private BitmapFont font;
	
	private float X;
	private float Y;
	private float W;
	private float H;
	
	public WindowsElem(Texture textureBase, ElementsGUI typeElem, GamePlayScreen gPS) {
		
		this.base = new Sprite(textureBase);
		this.isVisible = false;
		this.gPS = gPS;
		this.type = typeElem;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(0.75f, 0.75f);
		
	}
	
	public void setPosition(float X, float Y) {
		
		this.X = X;
		this.Y = Y;
		
		this.base.setPosition(X, Y);
	}
	
	public void setSize(float width, float height) {
		
		this.W = width;
		this.H = height;
		
		this.base.setSize(width, height);
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public void setTexture(Texture textureBase) {
		this.base.setTexture(textureBase);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
	}
	
	private void processText(Batch batch) {
		if (type.equals(ElementsGUI.SCORE)) {
			String score = StringUtils.leftPaddedString(15, gPS.getGamePlay().getGameLogic().getPlayer().getStatsDynElement().getScore());
			this.font.draw(batch, score, X+W/4-20, Y+H-10);
		}
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			this.base.draw(batch);
			processText(batch);
			
		}
	}
	
	
	
	

}
