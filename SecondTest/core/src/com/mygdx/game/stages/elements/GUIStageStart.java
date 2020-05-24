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
import com.mygdx.game.stages.components.WindowsItem;

public class GUIStageStart {
	
	public Group grpStartUI;
	private Stage stage;
	
	
	private WindowsItem wItem;
	private ImageButton buttonStart;
	private ImageButton buttonExit;
	
	
	public GUIStageStart(Stage stage) {
		this.stage = stage;
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
	    buttonStart.setSize(200.0f,100.0f);
	    buttonStart.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.startButton, Texture.class)));
	    buttonStart.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.startButton, Texture.class)));
	    buttonStart.setPosition((SecondTestGDX.screenWidth / 2) - 100,SecondTestGDX.screenHeight / 2 - 100);
	    buttonStart.setVisible(false);
	    buttonStart.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	          }
	          
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpStartUI.addActor(buttonStart);
		
	    
	    Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    buttonExit = new ImageButton(uiSkin_1);
	    buttonExit.setSize(200.0f,100.0f);
	    buttonExit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.exitButton, Texture.class)));
	    buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.exitButton, Texture.class)));
	    buttonExit.setPosition((SecondTestGDX.screenWidth / 2) - 100,SecondTestGDX.screenHeight / 2 - 200);
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
