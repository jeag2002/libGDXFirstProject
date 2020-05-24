package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.logic.GameLogicInformation;

public class LoadingScreen implements Screen{
	
	private SecondTestGDX game;
	private Stage stage;
	private Image image;
	
	
	private String backgroundPath = "splash/loading_logo.png";
	
	public LoadingScreen(SecondTestGDX game) {
		this.game = game;
		init();
	}
	
	
	private void init() {
		FitViewport viewport = new FitViewport(game.screenWidth, game.screenHeight);
		stage = new Stage(viewport);
		image = new Image(new Texture(Gdx.files.internal(backgroundPath)));
		
		image.setPosition(
				(game.screenWidth / 2) - (image.getWidth() / 2),
				(game.screenHeight / 2) - (image.getHeight() / 2));//(0, -1328);
			
	    stage.addActor(image);
	}

	@Override
	public void show() {
		stage.addAction(Actions.sequence( Actions.fadeOut(0.0f),Actions.fadeIn(0.5f))); 
		
	}

	@Override
	public void render(float delta) {
		if (game.resources.update()) {
			game.resources.initLoadedAssets();
			
			GameLogicInformation.setLevel(GameLogicInformation.START);
			game.gamePlayScreen.init();
			game.gamePlayScreen.initMusic();
			
			game.setScreen(game.gamePlayScreen);
		}
		
		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}

}
