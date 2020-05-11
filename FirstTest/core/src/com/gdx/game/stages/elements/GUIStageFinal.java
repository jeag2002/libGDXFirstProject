package com.gdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.engine.logic.GameLevelInformation;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.gameplay.WindowsItem;
import com.gdx.game.utils.StringUtils;

public class GUIStageFinal {
	
	private Stage stage;
	private GamePlayScreen gPS;
	public Group grpMenuUI;
	
	private WindowsItem wItem;
	private ImageButton button_Next;
	
	private BitmapFont font;
	private BitmapFont font_1;
	private BitmapFont font_2;
	
	private Label lblStart_1;
	private Label lblStart_2;
	private Label lblStart_3;
	
	public GUIStageFinal(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(1.5f, 1.5f);
		
		font_1 = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font_1.getData().setScale(1f, 1f);
		
		font_2 = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font_2.getData().setScale(0.75f, 0.75f);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initFinalGUI();
	}
	
	private void initFinalGUI() {
		grpMenuUI = new Group();
		
		Texture textImgWindows = FirstTestGDX.resources.get(FirstTestGDX.resources.imgWindows, Texture.class);
		wItem = new WindowsItem(textImgWindows, gPS);
		wItem.setPosition(FirstTestGDX.screenWidth/2-200, FirstTestGDX.screenHeight/2-200);
		wItem.setSize(400, 400);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		lblStart_1 = new Label("THE END", new Label.LabelStyle(font, Color.WHITE));
	    lblStart_1.setPosition( FirstTestGDX.screenWidth / 2, (FirstTestGDX.screenHeight / 2)+50, Align.center);
	    lblStart_1.setSize(100, 50);
	    lblStart_1.setVisible(false);
	    grpMenuUI.addActor(lblStart_1);
	    
	    
	    lblStart_2 = new Label("CONGRATULATIONS!!", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_2.setPosition( FirstTestGDX.screenWidth / 2, (FirstTestGDX.screenHeight / 2)-30, Align.center);
	    lblStart_2.setSize(100, 50);
	    lblStart_2.setVisible(false);
	    grpMenuUI.addActor(lblStart_2);
	    
	        
	    lblStart_3 = new Label("FINAL SCORE ", new Label.LabelStyle(font_2, Color.WHITE));
	    lblStart_3.setPosition( FirstTestGDX.screenWidth / 2 - 90 , (FirstTestGDX.screenHeight / 2)- 100, Align.center);
	    lblStart_3.setSize(100, 50);
	    lblStart_3.setVisible(false);
	    grpMenuUI.addActor(lblStart_3);
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Next = new ImageButton(uiSkin_1);
		button_Next.setSize(100.0f,100.0f);
		button_Next.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuOK, Texture.class)));
		button_Next.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(FirstTestGDX.resources.get(FirstTestGDX.resources.imgMenuOK, Texture.class)));
		button_Next.setPosition((FirstTestGDX.screenWidth / 2) - 50,195);
		button_Next.setVisible(false);
		button_Next.addListener(new InputListener(){
			
			@Override
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {	
				 gPS.startRanking();
				 /*
				 GameLevelInformation.setLevel(GameLevelInformation.FIRST_LEVEL);
	    		 gPS.setScore(gPS.getgLL().getScorePlayer());
				 gPS.dispose();
	    		 gPS.initGame();
	    		 gPS.closeMusic();
	    		 gPS.setInitialMusic();
	    		 */
			}
			
			@Override
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               return true;
	        }
		});
		grpMenuUI.addActor(button_Next);
		
		stage.addActor(grpMenuUI);
	
	}	
	
	public void showFinalGUI(boolean show) {
		wItem.setVisible(show);
		button_Next.setVisible(show);	
		lblStart_1.setVisible(show);
		lblStart_2.setVisible(show);
		lblStart_3.setVisible(show);
		
		if (show) {
			String FINAL_SCORE = "FINAL SCORE : " + StringUtils.leftPaddedString(10, gPS.getgLL().getScorePlayer());
			lblStart_3.setText(FINAL_SCORE);
		}
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	
	
	

}
