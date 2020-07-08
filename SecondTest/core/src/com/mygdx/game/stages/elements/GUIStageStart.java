package com.mygdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.WindowsItem;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GUIStageStart {
	
	public Group grpStartUI;
	private Stage stage;
	private GamePlayScreen gPS;
	
	
	private WindowsItem wItem;
	private ImageButton buttonStart;
	private ImageButton buttonSettings;
	private ImageButton buttonScore;
	private ImageButton buttonGameMode;
	private ImageButton buttonExit;
	
	
	public GUIStageStart(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initStartGUI();
	}
	
	
	private void initStartGUI() {
		grpStartUI = new Group();	
		
		Texture baseLogo = SecondTestGDX.resources.get(SecondTestGDX.resources.tableSplash, Texture.class);
		Texture textLogo = SecondTestGDX.resources.get(SecondTestGDX.resources.headerSplash, Texture.class);
		
		
		wItem = new WindowsItem(baseLogo,textLogo);
		wItem.setBound((SecondTestGDX.screenWidth / 2)-200, (SecondTestGDX.screenHeight / 2)+200, 400.0f, 200.0f);
		wItem.setVisible(false);
		
		grpStartUI.addActor(wItem);
		
		
		Skin uiSkin = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    buttonStart = new ImageButton(uiSkin);
	    buttonStart.setSize(700.0f,75.0f);
	    buttonStart.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.startButton, Texture.class)));
	    buttonStart.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.startButton, Texture.class)));
	    buttonStart.setPosition((SecondTestGDX.screenWidth / 2) - 350,SecondTestGDX.screenHeight / 2 - 100);
	    buttonStart.setVisible(false);
	    buttonStart.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	  
	        	  gPS.initIntermision();
	        	  
	        	  Gdx.app.postRunnable(new Runnable() {
	        		  @Override
	        		  public void run() {
	        			  gPS.getGamePlay().processTileGeneration();
	        			  Gdx.app.log("[GUISTAGESTART]", "TILE GENERATION MAP FINISHED");
	        		  }
	        	  });

	        	  
	        	  Timer.schedule(new Task() {
	          		@Override
	          	    public void run() {
	          			gPS.initGamePlay();
	          			Gdx.app.log("[GUISTAGESTART]", "INIT GAMEPLAY");
	          		}},6);
	        	  
	        	  
	          }
	          
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpStartUI.addActor(buttonStart);
	    //stage.addActor(buttonStart);
	    
	    Skin uiSkin_2 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    buttonSettings = new ImageButton(uiSkin_2);
	    buttonSettings.setSize(700.0f,75.0f);
	    buttonSettings.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.settingsButton, Texture.class)));
	    buttonSettings.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.settingsButton, Texture.class)));
	    buttonSettings.setPosition((SecondTestGDX.screenWidth / 2) - 350,(SecondTestGDX.screenHeight / 2) - 175);
	    buttonSettings.setVisible(false);
	    buttonSettings.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	          }
	          
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpStartUI.addActor(buttonSettings);
	    //stage.addActor(buttonSettings);
	    
	    Skin uiSkin_3 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    buttonScore = new ImageButton(uiSkin_3);
	    buttonScore.setSize(700.0f,75.0f);
	    buttonScore.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.scoreButton, Texture.class)));
	    buttonScore.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.scoreButton, Texture.class)));
	    buttonScore.setPosition((SecondTestGDX.screenWidth / 2) - 350,(SecondTestGDX.screenHeight / 2) - 250);
	    buttonScore.setVisible(false);
	    buttonScore.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	          }
	          
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpStartUI.addActor(buttonScore);
		
	    
	    Skin uiSkin_4 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    buttonExit = new ImageButton(uiSkin_4);
	    buttonExit.setSize(700.0f,75.0f);
	    buttonExit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.exitButton, Texture.class)));
	    buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.exitButton, Texture.class)));
	    buttonExit.setPosition((SecondTestGDX.screenWidth / 2) - 350,(SecondTestGDX.screenHeight / 2) - 325);
	    buttonExit.setVisible(false);
	    buttonExit.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	  Gdx.app.exit();
	          }
	          
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpStartUI.addActor(buttonExit);
		
		
		stage.addActor(grpStartUI);
		
	}
	
	public void showStartGUI(boolean show) {
		wItem.setVisible(show);
		buttonStart.setVisible(show);
		buttonSettings.setVisible(show);
		buttonScore.setVisible(show);;
		buttonExit.setVisible(show);
		
		if (show) {
			wItem.addAction(Actions.moveToAligned((SecondTestGDX.screenWidth / 2)-200, (SecondTestGDX.screenHeight / 2)+110, Align.left, 0.4f));
		}
		
		
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
}
