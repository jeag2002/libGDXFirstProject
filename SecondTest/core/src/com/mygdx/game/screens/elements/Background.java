package com.mygdx.game.screens.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.logic.GameLogicInformation;

public class Background {
	
	private Sprite sprite;
	
	public Background() {
	}
	
	public void backgroundImage() {		
		String spriteBMP = GameLogicInformation.getBackgroundImage();
		Texture text = SecondTestGDX.resources.get(spriteBMP);
		sprite = new Sprite(text);
	}
	
	public void setBounds(float X, float Y, float Width, float Height) {
		sprite.setBounds(X, Y, Width, Height);
	}
	
	public void draw(SpriteBatch sb) {
		sprite.draw(sb);
	}

}
