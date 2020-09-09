package com.mygdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.WindowsElem;
import com.mygdx.game.stages.components.WindowsItem;

public class GUIStageEndLevel {
	
	private Stage stage;
	private GamePlayScreen gPS;
	public Group grpMenuUI;
	
	private WindowsElem wItem;
	private WindowsElem wStar;
	private WindowsElem score;
	
	private WindowsElem wStarElem_1;
	private WindowsElem wStarElem_2;
	private WindowsElem wStarElem_3;
	
	private WindowsItem logo_result;
	private WindowsItem logo_score;
	private ImageButton button_Menu;
	private ImageButton button_Next;
	
	public GUIStageEndLevel(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initGUIStageEnd();
	}
	
	private void initGUIStageEnd() {
		
		grpMenuUI = new Group();
		
		//LAYER
		Texture textHeaderWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.HeaderTable, Texture.class);
		
		Texture logoWindows = null;
		
		if (gPS.getGamePlay().isNextLevel()) {
			logoWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Victory, Texture.class);
		}else {
			logoWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Defeat, Texture.class);
		}
		
		logo_result = new WindowsItem(textHeaderWindows,logoWindows);
		logo_result.setPosition(SecondTestGDX.screenWidth/2-150, SecondTestGDX.screenHeight/2+200);
		logo_result.setSize(300, 100);
		logo_result.setVisible(false);
		grpMenuUI.addActor(logo_result);
		
		//WINDOWS
		Texture textImgWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Window, Texture.class);
		wItem = new WindowsElem(textImgWindows, ElementsGUI.IDLE, gPS);
		wItem.setPosition(SecondTestGDX.screenWidth/2-300, SecondTestGDX.screenHeight/2-200);
		wItem.setSize(600, 400);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		//SCORE
		Texture texConsole = SecondTestGDX.resources.get(SecondTestGDX.resources.Table03, Texture.class);
		Texture texConsoleText = SecondTestGDX.resources.get(SecondTestGDX.resources.Score, Texture.class);
		
		logo_score = new WindowsItem(texConsole,texConsoleText);
		logo_score.setPosition(SecondTestGDX.screenWidth/2-250, SecondTestGDX.screenHeight/2+50);
		logo_score.setSize(150, 50);
		logo_score.setVisible(false);
		grpMenuUI.addActor(logo_score);
		
		
		Texture texConsoleScore = SecondTestGDX.resources.get(SecondTestGDX.resources.Table01, Texture.class);
		score = new WindowsElem(texConsoleScore, ElementsGUI.SCORE ,gPS);
		score.setPosition(SecondTestGDX.screenWidth/2-75, SecondTestGDX.screenHeight/2+50);
		score.setSize(325, 50);
		score.setVisible(false);
		grpMenuUI.addActor(score);
		
		
		Texture textImgStar = SecondTestGDX.resources.get(SecondTestGDX.resources.DecorTable, Texture.class);
		wStar = new WindowsElem(textImgStar, ElementsGUI.IDLE, gPS);
		wStar.setPosition(SecondTestGDX.screenWidth/2-300, SecondTestGDX.screenHeight/2-195);
		wStar.setSize(600, 150);
		wStar.setVisible(false);
		grpMenuUI.addActor(wStar);
		
		Texture Star = SecondTestGDX.resources.get(SecondTestGDX.resources.GoldStar);
		
		wStarElem_1 = new WindowsElem(Star, ElementsGUI.IDLE, gPS);
		wStarElem_1.setPosition(SecondTestGDX.screenWidth/2-50, SecondTestGDX.screenHeight/2-160);
		wStarElem_1.setSize(100, 100);
		wStarElem_1.setVisible(false);
		grpMenuUI.addActor(wStarElem_1);
		
		wStarElem_2 = new WindowsElem(Star, ElementsGUI.IDLE, gPS);
		wStarElem_2.setPosition(SecondTestGDX.screenWidth/2-150, SecondTestGDX.screenHeight/2-160);
		wStarElem_2.setSize(100, 100);
		wStarElem_2.setVisible(false);
		grpMenuUI.addActor(wStarElem_2);
		
		wStarElem_3 = new WindowsElem(Star, ElementsGUI.IDLE, gPS);
		wStarElem_3.setPosition(SecondTestGDX.screenWidth/2+50, SecondTestGDX.screenHeight/2-160);
		wStarElem_3.setSize(100, 100);
		wStarElem_3.setVisible(false);
		grpMenuUI.addActor(wStarElem_3);
		
		
		
		//BUTTONS
		Skin uiSkin_1 = new Skin(Gdx.files.internal("gui/skin/uiskin.json"));
		button_Menu = new ImageButton(uiSkin_1);
		button_Menu.setSize(100.0f,100.0f);
		button_Menu.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Menu.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.MenuBTN, Texture.class)));
		button_Menu.setPosition((SecondTestGDX.screenWidth / 2) - 100,SecondTestGDX.screenHeight/2-300);
		button_Menu.setVisible(false);
		button_Menu.addListener(new InputListener(){
	    	  @Override
	          public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
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
	    button_Next.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.PlayBTN, Texture.class)));
	    button_Next.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(SecondTestGDX.resources.get(SecondTestGDX.resources.PlayBTN, Texture.class)));
	    button_Next.setPosition((SecondTestGDX.screenWidth / 2),SecondTestGDX.screenHeight/2-300);
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
	
	public void showGUIStageEndLevel(boolean show) {
		
		logo_result.setVisible(show);
		logo_score.setVisible(show);
		score.setVisible(show);
		wItem.setVisible(show);
		wStar.setVisible(show);
		wStarElem_1.setVisible(show);
		wStarElem_2.setVisible(show);
		wStarElem_3.setVisible(show);
		button_Menu.setVisible(show);
		button_Next.setVisible(show);
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	

}
