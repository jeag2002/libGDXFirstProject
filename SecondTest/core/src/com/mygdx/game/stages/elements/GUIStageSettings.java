package com.mygdx.game.stages.elements;

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
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.WindowsElem;
import com.mygdx.game.stages.components.WindowsItem;


public class GUIStageSettings {
	
	private Label lblUP;
	private Label lblDOWN;
	private Label lblLEFT;
	private Label lblRIGHT;
	private Label lblSHOOT;
	private Label lblCLOCK;
	private Label lblINVCLOCK;
	
	private Stage stage;
	private GamePlayScreen gPS;
	private WindowsElem wItem;
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
		
		Texture textHeaderWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.HeaderTable, Texture.class);
		Texture textHangar = SecondTestGDX.resources.get(SecondTestGDX.resources.Description, Texture.class);
		
		logo_result = new WindowsItem(textHeaderWindows,textHangar);
		logo_result.setPosition(SecondTestGDX.screenWidth/2-150, SecondTestGDX.screenHeight/2+200);
		logo_result.setSize(300, 50);
		logo_result.setVisible(false);
		grpMenuUI.addActor(logo_result);
		
		
		Texture textImgWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Window, Texture.class);
		wItem = new WindowsElem(textImgWindows, ElementsGUI.IDLE, gPS);
		wItem.setPosition(SecondTestGDX.screenWidth/2-300, SecondTestGDX.screenHeight/2-200);
		wItem.setSize(600, 400);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Menu = new ImageButton(uiSkin_1);
		button_Menu.setSize(100.0f,100.0f);
		button_Menu.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Menu.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Menu.setPosition((SecondTestGDX.screenWidth / 2) - 50,SecondTestGDX.screenHeight/2-300);
		button_Menu.setVisible(false);
		button_Menu.addListener(new InputListener(){
	    	  @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	    		  gPS.initStart();
	    	  }
	          @Override
	          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            return true;
	          }
	        });
	    grpMenuUI.addActor(button_Menu);
		
	    
	    
		String U = "FORWARD .................. (UP/W)";
		String D = "BACKWARD ............... (DOWN/S)";
        String L = "LEFT .......................... (LEFT/A)";
		String R = "RIGHT ....................... (RIGHT/D)";
	    String S = "SHOOT ...................... (SPACE)";
	    String C = "TURRET CLOCK ........... (ALT-L/CRTL-R)";
		String I = "TURRET CLOCK INV ..... (CRTL-L/ALT-R)";
		    
		    
		lblUP = new Label(U, new Label.LabelStyle(font, Color.WHITE));
		lblUP.setPosition( SecondTestGDX.screenWidth / 2-250, (SecondTestGDX.screenHeight / 2) + 150, Align.left);
		lblUP.setSize(100, 50);
		lblUP.setVisible(false);
		grpMenuUI.addActor(lblUP);
		        
		lblDOWN = new Label(D, new Label.LabelStyle(font, Color.WHITE));
		lblDOWN.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2) + 100, Align.left);
		lblDOWN.setSize(100, 50);
		lblDOWN.setVisible(false);
		grpMenuUI.addActor(lblDOWN);
		        
		lblLEFT = new Label(L, new Label.LabelStyle(font, Color.WHITE));
		lblLEFT.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2)+ 50, Align.left);
		lblLEFT.setSize(100, 50);
		lblLEFT.setVisible(false);
		grpMenuUI.addActor(lblLEFT);
		     
		lblRIGHT = new Label(R, new Label.LabelStyle(font, Color.WHITE));
		lblRIGHT.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2), Align.left);
		lblRIGHT.setSize(100, 50);
		lblRIGHT.setVisible(false);
		grpMenuUI.addActor(lblRIGHT);
			
		     
		lblSHOOT = new Label(S, new Label.LabelStyle(font, Color.WHITE));
		lblSHOOT.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2) - 50, Align.left);
		lblSHOOT.setSize(100, 50);
		lblSHOOT.setVisible(false);
		grpMenuUI.addActor(lblSHOOT); 
		    
		lblCLOCK = new Label(C, new Label.LabelStyle(font, Color.WHITE));
		lblCLOCK.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2) - 100, Align.left);
		lblCLOCK.setSize(100, 50);
		lblCLOCK.setVisible(false);
		grpMenuUI.addActor(lblCLOCK); 
		    
		lblINVCLOCK = new Label(I, new Label.LabelStyle(font, Color.WHITE));
		lblINVCLOCK.setPosition( SecondTestGDX.screenWidth / 2-250 , (SecondTestGDX.screenHeight / 2) - 150, Align.left);
		lblINVCLOCK.setSize(100, 50);
		lblINVCLOCK.setVisible(false);
		grpMenuUI.addActor(lblINVCLOCK); 
		
		
		
		stage.addActor(grpMenuUI);
		
	}
	
	public void showSettingsGUI(boolean show) {	
		
		logo_result.setVisible(show);
		wItem.setVisible(show);
		button_Menu.setVisible(show);
		lblUP.setVisible(show);
		lblDOWN.setVisible(show);
		lblLEFT.setVisible(show);
		lblRIGHT.setVisible(show);
		lblSHOOT.setVisible(show);
		lblCLOCK.setVisible(show);
		lblINVCLOCK.setVisible(show);
		
		
	}
	
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}		
	}
	
	
	

}
