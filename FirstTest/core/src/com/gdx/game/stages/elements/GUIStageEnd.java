package com.gdx.game.stages.elements;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.hide;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.show;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.gameplay.WindowsItem;
import com.gdx.game.utils.StringUtils;
import com.gdx.game.utils.TimeConversion;

public class GUIStageEnd {
	
	private Stage stage;
	private GamePlayScreen gPS;
	public Group grpMenuUI;
	
	private WindowsItem wItem;
	private ImageButton button_Menu;
	private ImageButton button_Next;
	
	private WindowsItem logo_result;
	
	private Image Star_1;
	private Image Star_2;
	private Image Star_3;
	
	private Label lblStart_1;
	private Label lblStart_2;
	private Label lblStart_3;
	
	private BitmapFont font;
	private Sound sound;
	
	public GUIStageEnd(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(0.75f, 0.75f);
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initEndGUI();
	}
	
	private void initEndGUI() {
		grpMenuUI = new Group();
		
		Texture textImgWindows = FirstTestGDX.resources.get(FirstTestGDX.resources.imgWindows, Texture.class);
		wItem = new WindowsItem(textImgWindows, gPS);
		wItem.setPosition(FirstTestGDX.screenWidth/2-200, FirstTestGDX.screenHeight/2-300);
		wItem.setSize(400, 600);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		
		String logo = "";
		
		if (gPS.getgLL().isEndLevel()) {
			logo = FirstTestGDX.resources.imgYouWin;
		}else {
			logo = FirstTestGDX.resources.imgYouLose;
		}
		
		logo_result = new WindowsItem(FirstTestGDX.resources.get(logo, Texture.class),gPS);
		
		logo_result.setSize(200, 30);
		logo_result.setPosition((FirstTestGDX.screenWidth / 2) - 100, FirstTestGDX.screenHeight/2 + 200);
		logo_result.setVisible(false);
		
		grpMenuUI.addActor(logo_result);
		
		
		Texture textStarBlue = FirstTestGDX.resources.get(FirstTestGDX.resources.imgStart01, Texture.class);
		Texture textStarSilver = FirstTestGDX.resources.get(FirstTestGDX.resources.imgStart02, Texture.class);
		Texture textStarGold = FirstTestGDX.resources.get(FirstTestGDX.resources.imgStart03, Texture.class);
		
		Star_1 = new Image(textStarGold);
		Star_1.setSize(64, 64);
		Star_1.setPosition((FirstTestGDX.screenWidth / 2) - 96, FirstTestGDX.screenHeight/2 + 120);
		Star_1.setVisible(false);
		grpMenuUI.addActor(Star_1);
		
		Star_2 = new Image(textStarGold);
		Star_2.setSize(64, 64);
		Star_2.setPosition((FirstTestGDX.screenWidth / 2) - 32, FirstTestGDX.screenHeight/2 + 120);
		Star_2.setVisible(false);
		grpMenuUI.addActor(Star_2);
		
		Star_3 = new Image(textStarGold);
		Star_3.setSize(64, 64);
		Star_3.setPosition((FirstTestGDX.screenWidth / 2) + 32, FirstTestGDX.screenHeight/2 + 120);
		Star_3.setVisible(false);
		grpMenuUI.addActor(Star_3);
		
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Menu = new ImageButton(uiSkin_1);
		button_Menu.setSize(100.0f,100.0f);
		button_Menu.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuBtn, Texture.class)));
		button_Menu.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuBtn, Texture.class)));
		button_Menu.setPosition((FirstTestGDX.screenWidth / 2) - 100,95);
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
		
	    Skin uiSkin_2 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
	    button_Next = new ImageButton(uiSkin_2);
	    button_Next.setSize(100.0f,100.0f);
	    button_Next.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuOK, Texture.class)));
	    button_Next.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuOK, Texture.class)));
	    button_Next.setPosition((FirstTestGDX.screenWidth / 2),95);
	    button_Next.setVisible(false);
	    button_Next.addListener(new InputListener(){
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
	    grpMenuUI.addActor(button_Next);
	    
	    String TIMES = "TIME  .............." + TimeConversion.getDurationString((int)gPS.getgLL().getTime());
	    String KILLS = "KILLS ............" + StringUtils.leftPaddedString(10, gPS.getgLL().getKills());
	    String SCORE = "SCORE .........." + StringUtils.leftPaddedString(10, gPS.getgLL().getScorePlayer());
	    
	    lblStart_1 = new Label(TIMES, new Label.LabelStyle(font, Color.WHITE));
        lblStart_1.setPosition( FirstTestGDX.screenWidth / 2, (FirstTestGDX.screenHeight / 2), Align.center);
        lblStart_1.setSize(100, 50);
        lblStart_1.setVisible(false);
        grpMenuUI.addActor(lblStart_1);
        
        lblStart_2 = new Label(KILLS, new Label.LabelStyle(font, Color.WHITE));
        lblStart_2.setPosition( FirstTestGDX.screenWidth / 2 , (FirstTestGDX.screenHeight / 2)- 50, Align.center);
        lblStart_2.setSize(100, 50);
        lblStart_2.setVisible(false);
        grpMenuUI.addActor(lblStart_2);
        
        lblStart_3 = new Label(SCORE, new Label.LabelStyle(font, Color.WHITE));
        lblStart_3.setPosition( FirstTestGDX.screenWidth / 2 , (FirstTestGDX.screenHeight / 2) - 100, Align.center);
        lblStart_3.setSize(100, 50);
        lblStart_3.setVisible(false);
        grpMenuUI.addActor(lblStart_3);
	    
	    
	    
		stage.addActor(grpMenuUI);
		
		
		
	}
	
	
	public void showEndGUI(boolean show) {	
		
		wItem.setVisible(show);
		logo_result.setVisible(show);
		
		button_Menu.setVisible(show);
		button_Next.setVisible(show);
		
		Star_1.setVisible(show);
		Star_2.setVisible(show);
		Star_3.setVisible(show);
		
		lblStart_1.setVisible(show);
		lblStart_2.setVisible(show);
		lblStart_3.setVisible(show);
		
		
		if (show == true) {
			
			String logo = "";
			
			if (gPS.getgLL().isEndLevel()) {
				logo = FirstTestGDX.resources.imgYouWin;
			}else {
				logo = FirstTestGDX.resources.imgYouLose;
			}
			
			logo_result.setTexture(FirstTestGDX.resources.get(logo, Texture.class));
			
			String TIMES = "TIME  .............." + TimeConversion.getDurationString((int)gPS.getgLL().getTime());
		    String KILLS = "KILLS ............" + StringUtils.leftPaddedString(10, gPS.getgLL().getKills());
		    String SCORE = "SCORE .........." + StringUtils.leftPaddedString(10, gPS.getgLL().getScorePlayer());
		    
		    lblStart_1.setText(TIMES);
		    lblStart_2.setText(KILLS);
		    lblStart_3.setText(SCORE);
			
			
			lblStart_1.addAction(sequence(hide(), delay(1.0f), show()));
			lblStart_2.addAction(sequence(hide(), delay(2.0f), show()));
			lblStart_3.addAction(sequence(hide(), delay(3.0f), show()));
			
			Star_1.addAction(sequence(hide(), delay(3.5f), show()));
			Star_2.addAction(sequence(hide(), delay(4.0f), show()));
			Star_3.addAction(sequence(hide(), delay(4.5f), show()));
			
			
		}else {
			
			Star_1.setVisible(false);
			Star_2.setVisible(false);
			Star_3.setVisible(false);
			
			lblStart_1.setVisible(false);
			lblStart_2.setVisible(false);
			lblStart_3.setVisible(false);
			
		}
		
		
		
	}
	
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	

}
