package com.gdx.game.stages.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.gdx.game.screens.GamePlayScreen;

public class TouchPad extends Actor{
	
	private GamePlayScreen gPS;
	private Skin touchSkin;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	
	private Drawable touchBackground;
	private Drawable touchKnob;
	
	private boolean isVisible;
	
	public TouchPad (Texture pad, Texture knot, GamePlayScreen gPS) {
		
		touchSkin = new Skin();
		touchSkin.add("touchBackground", pad);
		touchSkin.add("touchKnob", knot);
		
		touchBackground = touchSkin.getDrawable("touchBackground");
		touchKnob = touchSkin.getDrawable("touchKnob");
		
		touchpadStyle = new TouchpadStyle();
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpad = new Touchpad(10, touchpadStyle);
		
		
	}

	public void setBounds(float X, float Y, float width, float height) {
		touchpad.setBounds(X, Y, width, height);
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (isVisible) {
			touchpad.draw(batch, parentAlpha);
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	

}
