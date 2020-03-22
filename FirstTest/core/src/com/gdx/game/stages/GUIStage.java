package com.gdx.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.gdx.game.FirstTestGDX;



public class GUIStage {
	
	public Group grpMenuUI;
	private Stage stage;
	
	private Image imgLogo;
	private ImageButton button_Start;
	private ImageButton button_Rating;
	private ImageButton button_Settings;
	private ImageButton button_Exit;
	
	
	//private Label lblStart;
	private String txtStart = "Press \"A\" to start!";
	
	public GUIStage(Stage stage) {
		this.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initMenuGUI();
	}
	
	private void initMenuGUI() {
		
		grpMenuUI = new Group();
		
		//logo
		Texture texture_logo = FirstTestGDX.resources.get(FirstTestGDX.resources.imgLogo, Texture.class);
		TextureRegion txLogo = new TextureRegion(texture_logo);
        imgLogo = new Image(txLogo);
        imgLogo.setPosition(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 + 550, Align.center);
        imgLogo.setVisible(false);
        grpMenuUI.addActor(imgLogo);
        
        
        //Button Start
        
        Skin uiSkin = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
        
        button_Start = new ImageButton(uiSkin);
        button_Start.setSize(100.0f,100.0f);
        button_Start.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgStartButtonOFF, Texture.class)));
        button_Start.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgStartButtonON, Texture.class)));
        button_Start.setPosition((FirstTestGDX.screenWidth / 2) - 200,FirstTestGDX.screenHeight / 2 - 200);
        button_Start.setVisible(false);
        button_Start.addListener(new InputListener(){
          @Override
          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {}
          @Override
          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }
        });
        
        grpMenuUI.addActor(button_Start);
        
        //Button Rating
        Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
        
        button_Rating = new ImageButton(uiSkin_1);
        button_Rating.setSize(100.0f,100.0f);
        button_Rating.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgRatingButtonOFF, Texture.class)));
        button_Rating.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgRatingButtonON, Texture.class)));
        button_Rating.setPosition((FirstTestGDX.screenWidth / 2) - 100,FirstTestGDX.screenHeight / 2 - 200);
        button_Rating.setVisible(false);
        button_Rating.addListener(new InputListener(){
          @Override
          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {}
          @Override
          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }
        });
        
        grpMenuUI.addActor(button_Rating);
        
        //Button Settings
        
        Skin uiSkin_2 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
        
        button_Settings = new ImageButton(uiSkin_2);
        button_Settings.setSize(100.0f,100.0f);
        button_Settings.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgSettingsButtonOFF, Texture.class)));
        button_Settings.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgSettingsButtonON, Texture.class)));
        button_Settings.setPosition(FirstTestGDX.screenWidth / 2,FirstTestGDX.screenHeight / 2 - 200);
        button_Settings.setVisible(false);
        button_Settings.addListener(new InputListener(){
          @Override
          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {}
          @Override
          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }
        });
        
        grpMenuUI.addActor(button_Settings);
        
        
        //Button Exit
        Skin uiSkin_3 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
        
        button_Exit = new ImageButton(uiSkin_3);
        button_Exit.setSize(100.0f,100.0f);
        button_Exit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgCloseButtonOFF, Texture.class)));
        button_Exit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgCloseButtonON, Texture.class)));
        button_Exit.setPosition((FirstTestGDX.screenWidth / 2)+100,FirstTestGDX.screenHeight / 2 - 200);
        button_Exit.setVisible(false);
        button_Exit.addListener(new InputListener(){
          @Override
          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        	  Gdx.app.exit();
          }
          @Override
          public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }
        });
        
        grpMenuUI.addActor(button_Exit);
        
        //label "Start"
        //lblStart = new Label(txtStart, new Label.LabelStyle(FirstTestGDX.resources.font1, Color.WHITE));
        //lblStart.setPosition(FirstTestGDX.screenWidth/2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        //lblStart.setVisible(false);
        //grpMenuUI.addActor(lblStart);
        
        stage.addActor(grpMenuUI);
	}
	
	public void showMenuGUI(boolean show) {
		
		imgLogo.setVisible(show);
		
		button_Start.setVisible(show);
		button_Rating.setVisible(show);
		button_Settings.setVisible(show);
		button_Exit.setVisible(show);
		
		if (show == true) {
			
			imgLogo.addAction(Actions.moveToAligned(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 + 100, Align.center, 0.4f));
            //lblStart.addAction(sequence(hide(), delay(0.7f), show()));

			
		}else {
			
			imgLogo.setVisible(false);
			
			//lblStart.setVisible(false);
			
			button_Start.setVisible(false);
			button_Rating.setVisible(false);
			button_Settings.setVisible(false);
			button_Exit.setVisible(false);
			
			
		}
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	

}
