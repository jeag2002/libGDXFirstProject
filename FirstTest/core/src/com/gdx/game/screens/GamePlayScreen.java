package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.engine.GameInput;
import com.gdx.game.engine.GamePlay;
import com.gdx.game.stages.GUIStage;

public class GamePlayScreen implements Screen {
	
	//reference
	public FirstTestGDX game;
	
	//stage for menu page; and in-game page
	private GUIStage guiStage;
	
	
	private GameInput gameInput;
	private GamePlay gamePlay;
	private InputMultiplexer inGameUI;
	
	//////////////////////////////
	private SpriteBatch spriteBatch;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/back_music.ogg"));
	private float volumeMusic = 0.25f;
	
	public GamePlayScreen(FirstTestGDX game) {
		this.game = game;
	}
	
	
	public void init() {
		spriteBatch = new SpriteBatch();
		StretchViewport viewport = new StretchViewport(game.screenWidth, game.screenHeight); 
		Stage uiStage = new Stage(viewport);
		guiStage = new GUIStage(uiStage);
		guiStage.init();
		
		initGame();
	}
	
	private void initGame() {
		
		gamePlay = new GamePlay(this);
		gameInput = new GameInput(this, gamePlay);
		
		inGameUI = new InputMultiplexer();
		inGameUI.addProcessor(guiStage.getStage());
		inGameUI.addProcessor(gameInput);
		
		Gdx.input.setInputProcessor(inGameUI);
		
		guiStage.showMenuGUI(true);
		
		music.setVolume(volumeMusic);
		music.setLooping(true);
		music.play();
	}
	
	
	public void startGame(boolean restart) {
		guiStage.showMenuGUI(false);	
	}
	
	
	public void resumeGame() {
		music.play();
	}

	
	@Override
	public void show() {
		if (gamePlay != null)
			gamePlay.resume();
		
	}

	@Override
	public void render(float delta) {

		if (gamePlay != null) {
			gamePlay.update(delta);
		}
		
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		if (gamePlay != null) {
			gamePlay.draw(spriteBatch);
		}
		spriteBatch.end();

		////render GUI
		if (guiStage != null) {
			guiStage.draw(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, game.screenWidth, game.screenHeight);
		game.initGraphicRatio();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		music.pause();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		music.play();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		spriteBatch.dispose();
		music.dispose();
		
	}

}
