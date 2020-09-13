package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.GUIEnum;
import com.mygdx.game.logic.GameInput;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.GamePlay;
import com.mygdx.game.stages.GUIStage;

public class GamePlayScreen implements Screen{
	
	private static final float volumeMusic = 0.25f;

	public SecondTestGDX game;
	
	private GUIStage guiStage;
	private GameInput gameInput;
	private GamePlay gamePlay;
	
	private InputMultiplexer inGameUI;
	private SpriteBatch spriteBatch;
	
	private Music music = null;
	private Sound sound = null;
	
	private StretchViewport viewportUI;
	
	
	public GamePlayScreen(SecondTestGDX game) {
		this.game = game;
	}
	
	public void generateGamePlay() {
		gamePlay = new GamePlay(this);
	}
	
	
	public void init() {	
		generateGamePlay();
		spriteBatch = new SpriteBatch();
		viewportUI = new StretchViewport(game.screenWidth, game.screenHeight); 
		Stage uiStage = new Stage(viewportUI);
		guiStage = new GUIStage(uiStage,this);
		guiStage.init();
		initGame();	
	}
	
	
	public void initStart() {
		
		GameLogicInformation.setLevel(GameLogicInformation.START);
		gamePlay.background();
		guiStage.activeGUI(GUIEnum.START);
  	  	stopMusic();
  	  	initMusic();
	}
	
	
	public void initGame() {
		gameInput = new GameInput(gamePlay);
		inGameUI = new InputMultiplexer();
		
		inGameUI.addProcessor(guiStage.getStage());
		inGameUI.addProcessor(gameInput);
		
		Gdx.input.setInputProcessor(inGameUI);
		
		gamePlay.initStart();
		guiStage.activeGUI(GUIEnum.START);
	}
	
	
	public void initIntermision() {
  	  	GameLogicInformation.setLevel(GameLogicInformation.INTERMISSION);
  	  	
  	  	gamePlay.initStart();
  	  	guiStage.activeGUI(GUIEnum.INTERMISSION);
  	  	
  	  	stopMusic();
  	  	initMusic();
	}
	
	
	public void initSettings() {
		GameLogicInformation.setLevel(GameLogicInformation.SETTINGS);
		gamePlay.background();
		guiStage.activeGUI(GUIEnum.SETTINGS);
		stopMusic();
	}
	
	public void initRanking() {
		GameLogicInformation.setLevel(GameLogicInformation.RANKING);
		gamePlay.background();
		guiStage.activeGUI(GUIEnum.RANKING);
		stopMusic();
	}
	
	
	public void initEndLevel() {
		
		GameLogicInformation.setLevel(GameLogicInformation.ENDLEVEL);
		gamePlay.background();
		guiStage.activeGUI(GUIEnum.ENDLEVEL);
		
		stopMusic();
		initMusic();
		
	}
	
	
	public void initEndGame() {
		
	}
	
	@SuppressWarnings("deprecation")
	public void initGamePlay() {
		GameLogicInformation.setLevel(GameLogicInformation.GAMEPLAY);
		gamePlay.start();
		
		gamePlay.initGamePlay();
		guiStage.activeGUI(GUIEnum.GAMEPLAY);
		
		spriteBatch.setProjectionMatrix(gamePlay.getCamera().combined);
		gamePlay.getGameLogic().getRayHandler().setCombinedMatrix(gamePlay.getCamera().combined.cpy().scl(GameLogicInformation.PIXELS_TO_METERS));
		
		stopMusic();
	    initMusic();
	}
	
	
	
	
	
	public void initMusic() {
		music = SecondTestGDX.resources.get(GameLogicInformation.getBackgroundMusic());
		music.setVolume(volumeMusic);
		music.setLooping(true);
		music.play();
	}
	
	
	public void stopMusic() {
		music.stop();
	}
	
	public void initSound(String soundFile) {
		sound = Gdx.audio.newSound(Gdx.files.internal(soundFile));
		sound.play();
	}
	
	public GamePlay getGamePlay() {
		return this.gamePlay;
	}
	
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		
		
		if (gamePlay != null) {
			gamePlay.runLevelTime(delta);
			gamePlay.updateElements(delta);
		}
		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		//DRAW BACKGROUND WHEN NO GAMEPLAY
		spriteBatch.begin();		
		
		if (gamePlay != null) {
			gamePlay.drawBackground(spriteBatch);
		}
		spriteBatch.end();
		
		if (gamePlay != null) {
			if (gamePlay.isStart()) {
				spriteBatch.setProjectionMatrix(gamePlay.getCamera().combined);
			}
		}
		
		

		
		
		//DRAW BACKGROUND WHEN GAMEPLAY (BACKGROUND, BORDER, WALLS)
		if (gamePlay != null) {
			if (gamePlay.isStart()) {
				
				gamePlay.evaluateEndLevel();
				gamePlay.evaluateEndGame();
				
				gamePlay.drawMapCamera();
				gamePlay.drawMapBef();				
			}
		}
	
		
		//DRAW ELEMENTS (PLAYER, ITEMS, ENEMIES)
		spriteBatch.begin();
		
		if (gamePlay != null) {
			if (gamePlay.isStart()) {
				if ((gamePlay.getLevelInformation().getType() == GameLogicInformation.CITY_LEVEL) && (gamePlay.getLevelInformation().getLights() == GamePlay.NO_LIGHTS)){
					spriteBatch.setShader(gamePlay.getShader());
				}
			}
		}
		
		
		if (gamePlay != null) {
			if (gamePlay.isStart()) {
				gamePlay.drawElements(spriteBatch);
			}
		}	
		spriteBatch.end();
		
	
		
		
		//DRAW BACKGROUND WHEN GAMEPLAY (FOREST) + LIGHTS
		if (gamePlay != null) {
			if (gamePlay.isStart()) {
				gamePlay.drawMapAf();
				
				gamePlay.getGameLogic().getRayHandler().setCombinedMatrix(gamePlay.getCamera().combined.cpy().scl(GameLogicInformation.PIXELS_TO_METERS));
				gamePlay.renderRayHandler();
				
				gamePlay.drawMapGloomingVolcano(delta);
				gamePlay.drawForestSpace();
				
				gamePlay.drawGraphGrid();
				
			}
		}
		
		
		if (guiStage != null) {
			guiStage.draw(delta);
		}
		
	}
	@Override
	public void resize(int width, int height) {
		viewportUI.update(width, height,true);
		guiStage.getStage().getViewport().update(width, height, true);
		
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, game.screenWidth, game.screenHeight);
		game.initGraphicRatio();
		
	}
	@Override
	public void pause() {
		music.stop();
		
	}
	
	@Override
	public void resume() {
		//music.play();
	}
	@Override
	public void hide() {
	}
	
	@Override
	public void dispose() {
		gamePlay.dispose();
	}
	
	
	
}
