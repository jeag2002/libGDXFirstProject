package com.gdx.game.stages.elements;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.gameplay.WindowsItem;

public class GUIStageSettings {
	
	private Label lblUP;
	private Label lblDOWN;
	private Label lblLEFT;
	private Label lblRIGHT;
	private Label lblSHOOT;
	private Label lblCHANGEARM;
	
	private Stage stage;
	private GamePlayScreen gPS;
	private WindowsItem wItem;
	private WindowsItem logo_result;
	
	private Group grpMenuUI;
	private ImageButton button_Menu;
	private BitmapFont font;
	
	
	private Image shoot;
	private Image change;
	private Image cursor;
	
	
	public GUIStageSettings(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
	
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(0.75f, 0.75f);
	
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initSettingsGUI();
	}
	
	private void initSettingsGUI() {
		
		grpMenuUI = new Group();
		
		Texture textImgWindows = FirstTestGDX.resources.get(FirstTestGDX.resources.imgWindows, Texture.class);
		wItem = new WindowsItem(textImgWindows, gPS);
		wItem.setPosition(FirstTestGDX.screenWidth/2-200, FirstTestGDX.screenHeight/2-300);
		wItem.setSize(400, 600);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Menu = new ImageButton(uiSkin_1);
		button_Menu.setSize(100.0f,100.0f);
		button_Menu.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuBtn, Texture.class)));
		button_Menu.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuBtn, Texture.class)));
		button_Menu.setPosition((FirstTestGDX.screenWidth / 2) - 50,95);
		button_Menu.setVisible(false);
		button_Menu.addListener(new InputListener(){
	    	  @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	    		  gPS.dispose();
	    		  gPS.initGame();
	    	  }
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpMenuUI.addActor(button_Menu);
		
	    
	    
	    logo_result = new WindowsItem(FirstTestGDX.resources.get(FirstTestGDX.resources.imgSettingsGUI, Texture.class),gPS);
		logo_result.setSize(200, 30);
		logo_result.setPosition((FirstTestGDX.screenWidth / 2) - 100, FirstTestGDX.screenHeight/2 + 200);
		logo_result.setVisible(false);
		grpMenuUI.addActor(logo_result);
	    
	    
	    
	    if (!Gdx.app.getType().equals(ApplicationType.Android)) {
	    
		    String U = "FORWARD ............... (UP/W)";
		    String D = "BACKWARD ........ (DOWN/S)";
		    String L = "LEFT ...................... (LEFT/A)";
		    String R = "RIGHT ................ (RIGHT/D)";
		    String S = "SHOOT ................... (SPACE)";
		    String C = "CHANGE ARM ............. (ALT)";
		    
		    
			lblUP = new Label(U, new Label.LabelStyle(font, Color.WHITE));
			lblUP.setPosition( FirstTestGDX.screenWidth / 2-170, (FirstTestGDX.screenHeight / 2) + 100, Align.left);
			lblUP.setSize(100, 50);
			lblUP.setVisible(false);
		    grpMenuUI.addActor(lblUP);
		        
		    lblDOWN = new Label(D, new Label.LabelStyle(font, Color.WHITE));
		    lblDOWN.setPosition( FirstTestGDX.screenWidth / 2-170 , (FirstTestGDX.screenHeight / 2) + 50, Align.left);
		    lblDOWN.setSize(100, 50);
		    lblDOWN.setVisible(false);
		    grpMenuUI.addActor(lblDOWN);
		        
		    lblLEFT = new Label(L, new Label.LabelStyle(font, Color.WHITE));
		    lblLEFT.setPosition( FirstTestGDX.screenWidth / 2-170 , (FirstTestGDX.screenHeight / 2), Align.left);
		    lblLEFT.setSize(100, 50);
		    lblLEFT.setVisible(false);
		    grpMenuUI.addActor(lblLEFT);
		     
		    lblRIGHT = new Label(R, new Label.LabelStyle(font, Color.WHITE));
		    lblRIGHT.setPosition( FirstTestGDX.screenWidth / 2-170 , (FirstTestGDX.screenHeight / 2) - 50, Align.left);
		    lblRIGHT.setSize(100, 50);
		    lblRIGHT.setVisible(false);
		    grpMenuUI.addActor(lblRIGHT);
			
		     
		    lblSHOOT = new Label(S, new Label.LabelStyle(font, Color.WHITE));
		    lblSHOOT.setPosition( FirstTestGDX.screenWidth / 2-170 , (FirstTestGDX.screenHeight / 2) - 100, Align.left);
		    lblSHOOT.setSize(100, 50);
		    lblSHOOT.setVisible(false);
		    grpMenuUI.addActor(lblSHOOT); 
		    
		    lblCHANGEARM = new Label(C, new Label.LabelStyle(font, Color.WHITE));
		    lblCHANGEARM.setPosition( FirstTestGDX.screenWidth / 2-170 , (FirstTestGDX.screenHeight / 2) - 150, Align.left);
		    lblCHANGEARM.setSize(100, 50);
		    lblCHANGEARM.setVisible(false);
		    grpMenuUI.addActor(lblCHANGEARM); 
		    
	    }else {
	    	
	    	Texture textShoot = FirstTestGDX.resources.get(FirstTestGDX.resources.shoot, Texture.class);
	    	Texture textChange = FirstTestGDX.resources.get(FirstTestGDX.resources.changeArm, Texture.class);
	    	Texture textLogo = FirstTestGDX.resources.get(FirstTestGDX.resources.joystick_logo, Texture.class);
	    	
	    	shoot = new Image(textShoot);
	    	shoot.setPosition( FirstTestGDX.screenWidth / 2-170, (FirstTestGDX.screenHeight / 2) + 140, Align.left);
	    	shoot.setSize(64, 64);
	    	shoot.setVisible(false);
		    grpMenuUI.addActor(shoot);
		    
		    lblUP = new Label("SHOOT", new Label.LabelStyle(font, Color.WHITE));
			lblUP.setPosition( FirstTestGDX.screenWidth / 2-100, (FirstTestGDX.screenHeight / 2) + 110, Align.left);
			lblUP.setSize(100, 64);
			lblUP.setVisible(false);
		    grpMenuUI.addActor(lblUP);
		    
	    	
		    change = new Image(textChange);
		    change.setPosition( FirstTestGDX.screenWidth / 2-170, (FirstTestGDX.screenHeight / 2) + 60, Align.left);
		    change.setSize(64, 64);
		    change.setVisible(false);
		    grpMenuUI.addActor(change);
		    
		    lblDOWN = new Label("CHANGE ARM", new Label.LabelStyle(font, Color.WHITE));
		    lblDOWN.setPosition( FirstTestGDX.screenWidth / 2-100, (FirstTestGDX.screenHeight / 2) + 40, Align.left);
		    lblDOWN.setSize(100, 64);
		    lblDOWN.setVisible(false);
		    grpMenuUI.addActor(lblDOWN);
		    
		    
		    cursor = new Image(textLogo);
		    cursor.setPosition( FirstTestGDX.screenWidth / 2-170, (FirstTestGDX.screenHeight / 2) + 40, Align.left);
		    cursor.setSize(64, 64);
		    cursor.setVisible(false);
		    grpMenuUI.addActor(cursor);
		    
		    lblLEFT = new Label("CURSOR", new Label.LabelStyle(font, Color.WHITE));
		    lblLEFT.setPosition( FirstTestGDX.screenWidth / 2-100 , (FirstTestGDX.screenHeight / 2) - 40, Align.left);
		    lblLEFT.setSize(100, 64);
		    lblLEFT.setVisible(false);
		    grpMenuUI.addActor(lblLEFT);
		    
		    
	    	
	    }
	    
		
		stage.addActor(grpMenuUI);
		
	}
	
	public void showSettingsGUI(boolean show) {	
		
		wItem.setVisible(show);
		
		logo_result.setVisible(show);
		button_Menu.setVisible(show);
		
		
		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
			lblUP.setVisible(show);
			lblDOWN.setVisible(show);
			lblLEFT.setVisible(show);
			lblRIGHT.setVisible(show);
			lblSHOOT.setVisible(show);
			lblCHANGEARM.setVisible(show);
		}else {
			shoot.setVisible(show);
			change.setVisible(show);
			cursor.setVisible(show);
			lblUP.setVisible(show);
			lblDOWN.setVisible(show);
			lblLEFT.setVisible(show);
			
		}
		
	}
	
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}		
	}
	
	
	

}
