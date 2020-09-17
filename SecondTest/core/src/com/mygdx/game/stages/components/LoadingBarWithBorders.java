package com.mygdx.game.stages.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.utils.*;

/**
 * Progress bar which reassembles the behaviour of the loading bar (with left and right borders).
 * 
 * @author serhiy
 */
public class LoadingBarWithBorders extends ProgressBar {

	private TextureRegion leftBorder;
	private TextureRegion rightBorder;
	
	private DrawUtils utils;
	
	private float iniX;
	private float iniY;
	private float iniWidth;
	private float iniHeight;
	
	public LoadingBarWithBorders(int width, int height) {
		super(0f, 1f, 0.01f, false, new ProgressBarStyle());
		
		utils = new DrawUtils();
		
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("gui/elements/progress-bars.pack"));
		getStyle().background = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-background"));
		getStyle().knob = utils.getColoredDrawable(0, height, Color.WHITE);
		getStyle().knobBefore = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-knobbefore"));
		
		leftBorder = textureAtlas.findRegion("loading-bar-2-left");
		rightBorder = textureAtlas.findRegion("loading-bar-2-right");
		
		setWidth(width);
		setHeight(height);
		
		this.iniX = getX();
		this.iniY = getY();
		this.iniWidth = getWidth();
		this.iniHeight = getHeight();
		
		setAnimateDuration(5f);
	}
	
	public void resetValues() {
		
		this.setValue(0);
	}
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		
		batch.draw(leftBorder, getX(), getY());
		float prevX = getX();
		float prevWidth = getWidth();
		setX(prevX + leftBorder.getRegionWidth());
		setWidth(prevWidth - leftBorder.getRegionWidth() - rightBorder.getRegionWidth());
		super.draw(batch, parentAlpha);
		setX(getX() + getWidth());
		batch.draw(rightBorder, getX(), getY());
		setX(prevX);
		setWidth(prevWidth);
	}
}
