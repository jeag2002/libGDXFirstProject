package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.game.FirstTestGDX;

public class LoadingScreen implements Screen{
	
	private FirstTestGDX game;
	private Stage stage;
	private Image image;
	
	private String backgroundPath = "splash/loading_logo.png";
	
	
	public LoadingScreen(FirstTestGDX game) {
		this.game = game;
		init();
	}
	
	private void init() {
		//StretchViewport viewport = new StretchViewport(game.screenWidth, game.screenHeight); 
		
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
			//-> Arranca pantalla videojuegos
			game.gamePlayScreen.init();
			
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
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
