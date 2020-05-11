package com.gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.engine.GameInput;
import com.gdx.game.engine.GamePlay;
import com.gdx.game.engine.logic.GameLevelInformation;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.stages.GUIStage;
import com.gdx.game.stages.enums.GUIEnum;
import com.gdx.game.utils.ScoreItem;
import com.gdx.game.utils.StringUtils;

public class GamePlayScreen implements Screen {
	
	//reference
	public FirstTestGDX game;
	
	//stage for menu page; and in-game page
	private GUIStage guiStage;
	
	private GameInput gameInput;
	private GamePlay gamePlay;
	private GameLevelLogic gLL;
	
	private InputMultiplexer inGameUI;
	
	//////////////////////////////
	private SpriteBatch spriteBatch;

	private float volumeMusic = 0.25f;
	
	private static final float bgSpeed = 50.0f;
	
	private float time = 0.0f;
	private float time_before_end = 0.0f;
	
	
	private ScalingViewport viewport;
	
	private Music music = null;
	private Sound sound = null;
	
	private Preferences scores;

	
	public GamePlayScreen(FirstTestGDX game) {
		this.game = game;
		GameLevelInformation.setLevel(GameLevelInformation.FIRST_LEVEL);
		scores = Gdx.app.getPreferences("scores");
		gLL = new GameLevelLogic();
	}
	
	
	public Map<String, ?> getScoreData(){
		return scores.get();
	}
	
	public void setScoreData(String key, long value) {
		scores.putLong(key, value);
		scores.flush();
	}
	
	public void setScore(long value) {
		Map<String, ?> values = getScoreData();
		int size = values.size();
		setScoreData(StringUtils.leftPaddedString(3, (size+1)),value);
	}
	
	
	public List<ScoreItem> getScoresSorted(){
		List<ScoreItem> items = new ArrayList<ScoreItem>();
		
		Map<String, ?> values = getScoreData();
		
		List<String> keys = new ArrayList<String>(values.keySet());
		List<Long> valuesMap = new ArrayList<Long>();
		
		for(String key: keys) {
			valuesMap.add(scores.getLong(key));
		}
		
		
		for(int i=0; i<keys.size(); i++) {
			items.add(new ScoreItem(keys.get(i),valuesMap.get(i)));
		}
		
		Collections.sort(items, new Comparator<ScoreItem>() {
			  @Override
			  public int compare(ScoreItem u1, ScoreItem u2) {
			    return u2.getScore().compareTo(u1.getScore());
			  }
		});
		
		if (items.size() > 5) {
			items = items.subList(0, 5);
		}
		
		return items;
	}
	
	
	
	public void generateGamePlay() {
		gamePlay = new GamePlay(this);
	}
	
	
	public void init() {
		generateGamePlay();
		spriteBatch = new SpriteBatch();
		viewport = new ScalingViewport(Scaling.stretch,game.screenWidth, game.screenHeight, gamePlay.getCamera());
		
		//StretchViewport viewport = new StretchViewport(game.screenWidth, game.screenHeight); 
		//FitViewport viewport = new FitViewport(game.screenWidth, game.screenHeight);
		StretchViewport viewportStage = new StretchViewport(game.screenWidth, game.screenHeight);
		Stage uiStage = new Stage(viewportStage);
		//Stage uiStage = new Stage(viewport);
		guiStage = new GUIStage(uiStage, this);
		guiStage.init();
		
		initGame();
	}
	
	public void initGame() {
		gamePlay = new GamePlay(this);
		gameInput = new GameInput(gamePlay);
		inGameUI = new InputMultiplexer();
		inGameUI.addProcessor(guiStage.getStage());
		inGameUI.addProcessor(gameInput);
		
		
		Gdx.input.setInputProcessor(inGameUI);
		guiStage.activeGUI(GUIEnum.MENU);
		//setInitialMusic();
	}
	
	
	public void setInitialMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal(GameLevelLogic.music_menu));
		music.setVolume(volumeMusic);
		music.setLooping(true);
		music.play();
	}
	
	
	public void closeMusic() {
		music.stop();
		//music = null;
	}
	
	
	public void setLevelMusic() {
				
		music = Gdx.audio.newMusic(Gdx.files.internal(GameLevelInformation.getMusicLevel(GameLevelInformation.getLevel())));
		music.setVolume(0.25f);
		music.setLooping(true);
		music.play();	
	}
	
	
	public void setEndLevelMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal(GameLevelLogic.music_final_level));
		music.setVolume(0.25f);
		music.play();
	}
	
	public void setFinalMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal(GameLevelLogic.music_final));
		music.setVolume(0.25f);
		music.play();
	}
	
	
	public void setGameOverMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal(GameLevelLogic.music_gameover));
		music.setVolume(0.25f);
		music.play();
	}
	
	
	public void setIntermissionVoice() {
		sound = Gdx.audio.newSound(Gdx.files.internal(GameLevelLogic.sound_intermission));
		sound.play();
		
	}
	
	public void setEndLevelVoice() {
		sound = Gdx.audio.newSound(Gdx.files.internal(GameLevelLogic.sound_levelcomplete));
		sound.play();	
	}
	
	
	public void startSettings() {
		guiStage.activeGUI(GUIEnum.SETTINGS);
	}
	
    public void startRanking() {
    	guiStage.activeGUI(GUIEnum.RANKING);
    }
	
	public void startIntermission() {
		closeMusic();
		guiStage.activeGUI(GUIEnum.INTERMISSION);
	}
	
	public void startGame() {
		guiStage.activeGUI(GUIEnum.GAMEPLAY);
		gamePlay.start();
		setIntermissionVoice();
		setLevelMusic();
	}
	
	
	//Execute Menu.
	public void reinitGameFirstLevel() {
		gLL.dispose();
		gamePlay.dispose();
		gamePlay = null;
		initGame();
	}
	
	public void resumeGame() {
		music.play();
	}

	
	@Override
	public void show() {
		if (gamePlay != null)
			gamePlay.resume();
		
	}
	
	
	public void setGameplayTime(float delta) {
		
		if (gamePlay.isStarted()) {
			time += delta;
			if (time > 1.0f) {
				this.getgLL().setTime(time + this.getgLL().getTime());
				time = 0.0f;
			}
		}
		
		gamePlay.moveCamera(delta);
	}
	
	

	@Override
	public void render(float delta) {

		if (gamePlay != null) {
			
			if (!this.getgLL().isEndLevel() && !this.getgLL().isGameOver()) {
				this.setGameplayTime(delta);
			}else {
				
				if (this.getgLL().isLaunchSoundEndLevel()) {
					setEndLevelVoice();
					this.getgLL().setLaunchSoundEndLevel(false);
				}
				
				stopGame();
				
				time_before_end += delta;
				
		        if (time_before_end >= 2.0f) {
		        	time_before_end = 0;
		        	endGame();
		        }
			}
			
			gamePlay.update(delta);
		}
		
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		
		spriteBatch.begin();
		if (gamePlay != null) {
			gamePlay.drawBackground(spriteBatch);
		}
		
		spriteBatch.end();
		
		if (gamePlay != null) {
			gamePlay.drawMap();
		}
		
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
	
	
	public void stopGame() {
		gamePlay.setLevelfinished(true);
	}
	
	
	private void endGame() {
		
		if (this.getgLL().isLaunchEndLevel() || this.getgLL().isLaunchGOLevel()) {
			
			
			closeMusic();
		
			if (this.getgLL().isEndLevel()) {
				if (GameLevelInformation.getLevel() >= GameLevelInformation.FINAL_LEVEL) { 
					setFinalMusic();
				}else {
					setEndLevelMusic();
				}
			}else {
				setGameOverMusic();
			}
		
			gamePlay.setStarted(false);
			
			if ((GameLevelInformation.getLevel() >= GameLevelInformation.FINAL_LEVEL) && (this.getgLL().isEndLevel())) {
				setScore(getgLL().getScorePlayer());
				guiStage.activeGUI(GUIEnum.FINAL);
			}else {
				guiStage.activeGUI(GUIEnum.ENDLEVEL);
			}
				
			this.getgLL().setLaunchEndLevel(false);
			this.getgLL().setLaunchGOLevel(false);
		}
		//initGame();
	}
	
	
	

	@Override
	public void resize(int width, int height) {
		
		viewport.update(width, height,true);
		guiStage.getStage().getViewport().update(width, height, true);
		
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
		//spriteBatch.dispose();
		gamePlay.dispose();
		gLL.dispose();
		music.dispose();
		
	}
	
	
	public GamePlay getGamePlay() {
		return gamePlay;
	}


	public void setGamePlay(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
	}
	
	public GameLevelLogic getgLL() {
		return gLL;
	}


	public void setgLL(GameLevelLogic gLL) {
		this.gLL = gLL;
	}
	

}
