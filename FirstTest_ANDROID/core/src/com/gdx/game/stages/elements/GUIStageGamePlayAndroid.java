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

	public static ImageButton button_UP;
	public static ImageButton button_DOWN;
	public static ImageButton button_LEFT;
	public static ImageButton button_RIGHT;
	
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

		Texture up = FirstTestGDX.resources.get(FirstTestGDX.resources.android_up, Texture.class);
		Texture down = FirstTestGDX.resources.get(FirstTestGDX.resources.android_down, Texture.class);

		Texture left = FirstTestGDX.resources.get(FirstTestGDX.resources.android_left, Texture.class);
		Texture right = FirstTestGDX.resources.get(FirstTestGDX.resources.android_right, Texture.class);
		
		touchSkin = new Skin();
		touchSkin.add("touchBackground", touchBack);
		touchSkin.add("touchKnob", touchKnot);
		
		touchBackground = touchSkin.getDrawable("touchBackground");
		touchKnob = touchSkin.getDrawable("touchKnob");
		
		touchpadStyle = new TouchpadStyle();
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpad = new Touchpad(10, touchpadStyle);
		
		touchpad.setBounds(FirstTestGDX.screenWidth-110, 200, 100, 100);
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


		Skin uiSkin_3 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_UP = new ImageButton(uiSkin_3);
		button_UP.setSize(64.0f,64.0f);
		button_UP.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(up));
		button_UP.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(up));
		button_UP.setPosition(FirstTestGDX.screenWidth-132,200);
		button_UP.setVisible(false);
		button_UP.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				gPS.getGamePlay().playerMoveUp();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		grpMenuUI.addActor(button_UP);


		Skin uiSkin_4 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_DOWN = new ImageButton(uiSkin_4);
		button_DOWN.setSize(64.0f,64.0f);
		button_DOWN.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(down));
		button_DOWN.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(down));
		button_DOWN.setPosition(FirstTestGDX.screenWidth-132,100);
		button_DOWN.setVisible(false);
		button_DOWN.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				gPS.getGamePlay().playerMoveDown();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		grpMenuUI.addActor(button_DOWN);


		Skin uiSkin_5 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_LEFT = new ImageButton(uiSkin_5);
		button_LEFT.setSize(64.0f,64.0f);
		button_LEFT.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(left));
		button_LEFT.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(left));
		button_LEFT.setPosition(FirstTestGDX.screenWidth-200,150);
		button_LEFT.setVisible(false);
		button_LEFT.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				gPS.getGamePlay().playerMoveLeft();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		grpMenuUI.addActor(button_LEFT);


		Skin uiSkin_6 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_RIGHT = new ImageButton(uiSkin_6);
		button_RIGHT.setSize(64.0f,64.0f);
		button_RIGHT.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(right));
		button_RIGHT.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(right));
		button_RIGHT.setPosition(FirstTestGDX.screenWidth-64,150);
		button_RIGHT.setVisible(false);
		button_RIGHT.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				gPS.getGamePlay().playerMoveRight();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		grpMenuUI.addActor(button_RIGHT);




		
		stage.addActor(grpMenuUI);
		
	}
	
	
	
	public void showGamePlayAndroidGUI(boolean show) {
		touchpad.setVisible(false);
		button_CHANGE.setVisible(show);
		button_SHOOT.setVisible(show);

		button_UP.setVisible(show);
		button_DOWN.setVisible(show);
		button_LEFT.setVisible(show);
		button_RIGHT.setVisible(show);
	}
	
	
	public void draw(float delta) {
		
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	
	
	

}
