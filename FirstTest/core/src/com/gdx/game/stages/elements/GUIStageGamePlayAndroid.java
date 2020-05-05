package com.gdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.gameplay.TouchPad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public class GUIStageGamePlayAndroid {
	
	
	private static final float blockSpeed = 5;
	
	private GamePlayScreen gPS;
	private Stage stage;
	public Group grpMenuUI;
	
	private ImageButton button_SHOOT;
	private ImageButton button_CHANGE;
	
	
	private Skin touchSkin;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	
	private Drawable touchBackground;
	private Drawable touchKnob;
	
	private TouchPad touch;
	
	
	public GUIStageGamePlayAndroid(Stage stage, GamePlayScreen gPS){
		this.stage = stage;
		this.gPS = gPS;	
	}
	
	
	public void init() {
		initGamePlayAndroidGUI();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	
	private void initGamePlayAndroidGUI() {
		
		grpMenuUI = new Group();
		
		Texture touchBack = FirstTestGDX.resources.get(FirstTestGDX.resources.joystick_background, Texture.class);
		Texture touchKnot = FirstTestGDX.resources.get(FirstTestGDX.resources.joystick, Texture.class);
		
		Texture shootButton = FirstTestGDX.resources.get(FirstTestGDX.resources.shoot, Texture.class);
		Texture changeButton = FirstTestGDX.resources.get(FirstTestGDX.resources.changeArm, Texture.class);
		
		
		touchSkin = new Skin();
		touchSkin.add("touchBackground", touchBack);
		touchSkin.add("touchKnob", touchKnot);
		
		touchBackground = touchSkin.getDrawable("touchBackground");
		touchKnob = touchSkin.getDrawable("touchKnob");
		
		touchpadStyle = new TouchpadStyle();
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpad = new Touchpad(10, touchpadStyle);
		
		touchpad.setBounds(FirstTestGDX.screenWidth-110, 100, 100, 100);
		touchpad.setVisible(false);
		
		touchpad.addListener(
				new ChangeListener() {
					
			    @Override
				public void changed(ChangeEvent event, Actor actor) {
				        float deltaX = ((Touchpad) actor).getKnobPercentX();
				        float deltaY = ((Touchpad) actor).getKnobPercentY();
				        float X = gPS.getGamePlay().getPlayer().getX() + deltaX*blockSpeed;
				        float Y = gPS.getGamePlay().getPlayer().getY() + deltaY*blockSpeed;		
				        gPS.getGamePlay().getPlayer().updateTouchPad(X, Y);
			    }});
		
		grpMenuUI.addActor(touchpad);
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_CHANGE = new ImageButton(uiSkin_1);
		button_CHANGE.setSize(64.0f,64.0f);
		button_CHANGE.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(changeButton));
		button_CHANGE.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(changeButton));
		button_CHANGE.setPosition(32,200);
		button_CHANGE.setVisible(false);
		button_CHANGE.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	  gPS.getGamePlay().playerChange();
	          }
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	      });
	    grpMenuUI.addActor(button_CHANGE);
		
		
		 
		 Skin uiSkin_2 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		 button_SHOOT = new ImageButton(uiSkin_2);
		 button_SHOOT.setSize(64.0f,64.0f);
		 button_SHOOT.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(shootButton));
		 button_SHOOT.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(shootButton));
		 button_SHOOT.setPosition(32,100);
		 button_SHOOT.setVisible(false);
		 button_SHOOT.addListener(new InputListener(){
	          @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	  gPS.getGamePlay().playerShoot();
	          }
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	      });
	     grpMenuUI.addActor(button_SHOOT);
		
		stage.addActor(grpMenuUI);
		
	}
	
	
	
	public void showGamePlayAndroidGUI(boolean show) {
		touchpad.setVisible(show);
		button_CHANGE.setVisible(show);
		button_SHOOT.setVisible(show);
	}
	
	
	public void draw(float delta) {
		
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	
	
	

}
