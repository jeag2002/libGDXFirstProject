package com.mygdx.game.stages.elements;

import java.util.List;

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
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.WindowsElem;
import com.mygdx.game.stages.components.WindowsItem;
import com.mygdx.game.utils.ScoreItem;
import com.mygdx.game.utils.StringUtils;


public class GUIStageRanking {
	
	private Stage stage;
	private GamePlayScreen gPS;
	public Group grpMenuUI;
	
	private WindowsElem wItem;
	private WindowsItem logo_result;
	private ImageButton button_Next;
	
	private BitmapFont font;
	private BitmapFont font_1;
	
	private Label lblStart_1;
	private Label lblStart_2;
	private Label lblStart_3;
	private Label lblStart_4;
	private Label lblStart_5;
	private Label lblStart_6;
	
	
	public GUIStageRanking(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(1.5f, 1.5f);
		
		font_1 = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font_1.getData().setScale(0.75f, 0.75f);
	}
	
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initRankingGUI();
	}
	
	private void initRankingGUI() {
		grpMenuUI = new Group();
		
		Texture textImgWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Window, Texture.class);
		wItem = new WindowsElem(textImgWindows, ElementsGUI.IDLE ,gPS);
		wItem.setPosition(SecondTestGDX.screenWidth/2-200, SecondTestGDX.screenHeight/2-200);
		wItem.setSize(400, 400);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		
		Texture header = SecondTestGDX.resources.get(SecondTestGDX.resources.HeaderTable, Texture.class);
		Texture rating = SecondTestGDX.resources.get(SecondTestGDX.resources.Rating, Texture.class);
		
		logo_result = new WindowsItem(header,rating);
		logo_result.setSize(200, 50);
		logo_result.setPosition((SecondTestGDX.screenWidth / 2) - 100, SecondTestGDX.screenHeight/2 + 200);
		logo_result.setVisible(false);
		grpMenuUI.addActor(logo_result);
	    
		
	    lblStart_2 = new Label("", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_2.setPosition( (SecondTestGDX.screenWidth / 2)-170, (SecondTestGDX.screenHeight / 2)+100, Align.left);
	    lblStart_2.setSize(100, 50);
	    lblStart_2.setVisible(false);
	    grpMenuUI.addActor(lblStart_2);
	    
	    lblStart_3 = new Label("", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_3.setPosition( (SecondTestGDX.screenWidth / 2)-170, (SecondTestGDX.screenHeight / 2)+50, Align.left);
	    lblStart_3.setSize(100, 50);
	    lblStart_3.setVisible(false);
	    grpMenuUI.addActor(lblStart_3);
	    
	    lblStart_4 = new Label("", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_4.setPosition( (SecondTestGDX.screenWidth / 2)-170, (SecondTestGDX.screenHeight / 2), Align.left);
	    lblStart_4.setSize(100, 50);
	    lblStart_4.setVisible(false);
	    grpMenuUI.addActor(lblStart_4);
	    
	    lblStart_5 = new Label("", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_5.setPosition( (SecondTestGDX.screenWidth / 2)-170, (SecondTestGDX.screenHeight / 2)-50, Align.left);
	    lblStart_5.setSize(100, 50);
	    lblStart_5.setVisible(false);
	    grpMenuUI.addActor(lblStart_5);
	    
	    lblStart_6 = new Label("", new Label.LabelStyle(font_1, Color.WHITE));
	    lblStart_6.setPosition( (SecondTestGDX.screenWidth / 2)-170, (SecondTestGDX.screenHeight / 2)-100, Align.left);
	    lblStart_6.setSize(100, 50);
	    lblStart_6.setVisible(false);
	    grpMenuUI.addActor(lblStart_6);
	    
	    
		
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Next = new ImageButton(uiSkin_1);
		button_Next.setSize(100.0f,100.0f);
		button_Next.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Next.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Next.setPosition((SecondTestGDX.screenWidth / 2) - 50, SecondTestGDX.screenHeight/2-300);
		button_Next.setVisible(false);
		button_Next.addListener(new InputListener(){
			
			@Override
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {	
			}
			
			@Override
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               return true;
	        }
		});
		grpMenuUI.addActor(button_Next);
		
		stage.addActor(grpMenuUI);
	
	}	
	
	public void showRankingGUI(boolean show) {
		wItem.setVisible(show);
		
		logo_result.setVisible(show);
		button_Next.setVisible(show);	
		
		lblStart_2.setVisible(show);
		lblStart_3.setVisible(show);
		lblStart_4.setVisible(show);
		lblStart_5.setVisible(show);
		lblStart_6.setVisible(show);
		
		if (show) {
			
			List<ScoreItem> scores = gPS.getGamePlay().getScoresSorted();
			
			String score_1 = "";
			String score_default = StringUtils.leftPaddedString(3, 0) + ".................." + StringUtils.leftPaddedString(10, 0);
			
			if (scores.size() >= 1) {
				score_1 = scores.get(0).getItem() + ".................." + StringUtils.leftPaddedString(10, scores.get(0).getScore().intValue());
			}else {
				score_1 = score_default;
			}
			lblStart_2.setText(score_1);
			
			String score_2 = "";
			if (scores.size() >= 2) {
				score_2 = scores.get(1).getItem() + ".................." + StringUtils.leftPaddedString(10, scores.get(1).getScore().intValue());
			}else {
				score_2 = score_default;
			}
			lblStart_3.setText(score_2);
			
			String score_3 = "";
			if (scores.size() >= 3) {
				score_3 = scores.get(2).getItem() + ".................." + StringUtils.leftPaddedString(10, scores.get(2).getScore().intValue());
			}else {
				score_3 = score_default;
			}
			lblStart_4.setText(score_3);
			
			String score_4 = "";
			if (scores.size() >= 4) {
				score_4 = scores.get(3).getItem() + ".................." + StringUtils.leftPaddedString(10, scores.get(3).getScore().intValue());
			}else {
				score_4 = score_default;
			}
			lblStart_5.setText(score_4);
			
			String score_5 = "";
			if (scores.size() >= 5) {
				score_5 = scores.get(4).getItem() + ".................." + StringUtils.leftPaddedString(10, scores.get(4).getScore().intValue());
			}else {
				score_5 = score_default;
			}
			lblStart_6.setText(score_5);
			
		}
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	

}
