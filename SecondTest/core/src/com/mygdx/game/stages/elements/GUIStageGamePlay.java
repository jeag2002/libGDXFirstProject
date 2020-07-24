package com.mygdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;

public class GUIStageGamePlay {
	
	private Stage stage;
	private GamePlayScreen gPS;
	private Cursor cursor;
	private DrawUtils dU;
	
	public GUIStageGamePlay(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		this.dU = new DrawUtils();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initGamePlayGUI();
	}
	
	private void initGamePlayGUI() {
		if (SecondTestGDX.isMouseEnabled) {
			Pixmap pm = new Pixmap(Gdx.files.internal("gui/gameplay/cursor.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
		}
	}
	
	public void showGamePlayGUI(boolean show) {
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	

}
