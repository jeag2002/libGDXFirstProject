package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.elements.Background;

public class GamePlay {
	
	private GamePlayScreen gPS;
	private Background background;
	
	
	public GamePlay(GamePlayScreen gPS) {
		this.gPS = gPS;
		init();
	}
	
	
	public void init() {
		background();
	}

	public void background() {
		background = new Background();
		background.backgroundImage();
		background.setBounds(0,0, SecondTestGDX.screenWidth, SecondTestGDX.screenHeight);
	}
	
	public void drawBackground(SpriteBatch sb) {
		background.draw(sb);
	}
	
	
	public void draw(SpriteBatch sb) {
		if (GameLogicInformation.getLevel() == GameLogicInformation.START) {
			drawBackground(sb);
		}
	}
	
	

}
