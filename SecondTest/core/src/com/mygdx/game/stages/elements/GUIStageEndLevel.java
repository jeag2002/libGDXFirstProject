package com.mygdx.game.stages.elements;

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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.ElementDefinitionObject;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.enums.LevelEnum;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.WindowsElem;
import com.mygdx.game.stages.components.WindowsItem;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.hide;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.show;


public class GUIStageEndLevel {
	
	private Stage stage;
	private GamePlayScreen gPS;
	public Group grpMenuUI;
	
	private WindowsElem wItem;
	private WindowsElem wStar;
	
	private WindowsElem level;
	private WindowsElem score;
	
	private WindowsElem wStarElem_1;
	private WindowsElem wStarElem_2;
	private WindowsElem wStarElem_3;
	
	private WindowsItem logo_result;
	private WindowsItem logo_score;
	private ImageButton button_Menu;
	private ImageButton button_Next;
	
	private Label WIN;
	private Label CONGRATULATION;
	
	private BitmapFont font;
	private BitmapFont font_1;
	
	public GUIStageEndLevel(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		
		this.font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		this.font.getData().setScale(1.5f, 1.5f);
		
		font_1 = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font_1.getData().setScale(1f, 1f);

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
		logo_result.setPosition(SecondTestGDX.screenWidth/2-100, SecondTestGDX.screenHeight/2+200);
		logo_result.setSize(200, 50);
		logo_result.setVisible(false);
		grpMenuUI.addActor(logo_result);
		
		//WINDOWS
		Texture textImgWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Window, Texture.class);
		wItem = new WindowsElem(textImgWindows, ElementsGUI.IDLE, gPS);
		wItem.setPosition(SecondTestGDX.screenWidth/2-300, SecondTestGDX.screenHeight/2-200);
		wItem.setSize(600, 400);
		wItem.setVisible(false);
		grpMenuUI.addActor(wItem);
		
		
		WIN = new Label("THE END", new Label.LabelStyle(font, Color.WHITE));
		WIN.setPosition( (SecondTestGDX.screenWidth / 2) - 20, (SecondTestGDX.screenHeight / 2)+150, Align.center);
		WIN.setSize(100, 50);
		WIN.setVisible(true);
	    grpMenuUI.addActor(WIN);
	    
	    
	    CONGRATULATION = new Label("CONGRATULATIONS!!", new Label.LabelStyle(font_1, Color.WHITE));
	    CONGRATULATION.setPosition( (SecondTestGDX.screenWidth / 2) , (SecondTestGDX.screenHeight / 2)+80, Align.center);
	    CONGRATULATION.setSize(100, 50);
	    CONGRATULATION.setVisible(true);
	    grpMenuUI.addActor(CONGRATULATION);
		
		
		//SCORE
		Texture texConsole = SecondTestGDX.resources.get(SecondTestGDX.resources.Table03, Texture.class);
		Texture texConsoleText = SecondTestGDX.resources.get(SecondTestGDX.resources.Score, Texture.class);
		
		logo_score = new WindowsItem(texConsole,texConsoleText);
		logo_score.setPosition(SecondTestGDX.screenWidth/2-250, SecondTestGDX.screenHeight/2-10);
		logo_score.setSize(150, 50);
		logo_score.setVisible(false);
		grpMenuUI.addActor(logo_score);
		
		
		level = new WindowsElem(texConsole, ElementsGUI.LEVEL ,gPS);
		level.setPosition(SecondTestGDX.screenWidth/2-70, SecondTestGDX.screenHeight/2-10);
		level.setSize(50, 50);
		level.setVisible(false);
		grpMenuUI.addActor(level);
		
		
		Texture texConsoleScore = SecondTestGDX.resources.get(SecondTestGDX.resources.Table01, Texture.class);
		score = new WindowsElem(texConsoleScore, ElementsGUI.SCORE ,gPS);
		score.setPosition(SecondTestGDX.screenWidth/2+20, SecondTestGDX.screenHeight/2-10);
		score.setSize(225, 50);
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
	    		  
	    		  gPS.getGamePlay().setNextLevel(false);
	    		  gPS.getGamePlay().getGameLogic().endLevel();
	    		  gPS.getGamePlay().restartTileGenerator();
	    		  GameLogicInformation.setLevelGamePlay(0);
	    		  
	    		  if (gPS.getGamePlay().isEndGame() || gPS.getGamePlay().isPlayerDied()) {
	    			  gPS.getGamePlay().setEndGame(false);
	    			  gPS.getGamePlay().setPlayerDied(false);
	    			  gPS.getGamePlay().setScore(gPS.getGamePlay().getGameLogic().getPlayer().getStatsDynElement().getScore());		  
	    			  GameLogicInformation.setCurrentPlayerVariables(new ElementDefinitionObject(), ElementEnum.GUN_PLAYER_1_A);
	    			  gPS.initRanking();
	    		  }else {
	    			  gPS.initStart();
	    		  }
	    		  
	    		 
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
	    		  
	    		  gPS.getGamePlay().setNextLevel(false);
	    		  gPS.getGamePlay().getGameLogic().endLevel();
	    		  GameLogicInformation.setLevelGamePlay(GameLogicInformation.getLevelGamePlay()+1);
	    		  gPS.getGamePlay().setLevelInformation(GameLogicInformation.getLevelGamePlay());
	        	  gPS.initIntermision();
	        	  
	        	  Gdx.app.postRunnable(new Runnable() {
	        		  @Override
	        		  public void run() {
	        			  gPS.getGamePlay().restartTileGenerator();
	        			  gPS.getGamePlay().processTileGeneration(GameLogicInformation.getCurrentCannon());
	        			  Gdx.app.log("[GUISTAGESTART]", "TILE GENERATION MAP FINISHED");
	        		  }
	        	  });

	        	  
	        	  Timer.schedule(new Task() {
	          		@Override
	          	    public void run() {
	          			gPS.reinitGamePlay();
	          			gPS.getGamePlay().setPlayerCurrentVariables();
	          			Gdx.app.log("[GUISTAGESTART]", "INIT GAMEPLAY");
	          		}},6);
	    		  
	    		   		  
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
		level.setVisible(show);
		wItem.setVisible(show);
		wStar.setVisible(show);
		wStarElem_1.setVisible(show);
		wStarElem_2.setVisible(show);
		wStarElem_3.setVisible(show);
		button_Menu.setVisible(show);
		button_Next.setVisible(show);	
		
		WIN.setVisible(show);
		CONGRATULATION.setVisible(show);
		
		
		if (show) {
			
			wStarElem_2.addAction(sequence(hide(), delay(1.0f), show()));
			wStarElem_1.addAction(sequence(hide(), delay(2.0f), show()));
			wStarElem_3.addAction(sequence(hide(), delay(3.0f), show()));
			
			
			if (gPS.getGamePlay().isNextLevel()) {
				button_Menu.setVisible(true);
				button_Next.setVisible(true);
				button_Menu.setPosition((SecondTestGDX.screenWidth / 2) - 100,SecondTestGDX.screenHeight/2-300);
				button_Next.setPosition((SecondTestGDX.screenWidth / 2),SecondTestGDX.screenHeight/2-300);
				

				button_Menu.addAction(sequence(hide(), delay(5.0f), show()));
				button_Next.addAction(sequence(hide(), delay(5.5f), show()));
				
				
				WIN.setText("END LEVEL");
				WIN.setVisible(true);
				CONGRATULATION.setVisible(false);
				
			}else if (gPS.getGamePlay().isPlayerDied() || gPS.getGamePlay().isEndGame()){
				button_Menu.setVisible(true);
				button_Next.setVisible(false);
				button_Menu.setPosition((SecondTestGDX.screenWidth / 2) - 50,SecondTestGDX.screenHeight/2-300);
				
				button_Menu.addAction(sequence(hide(), delay(5.0f), show()));
				
				
				if (gPS.getGamePlay().isEndGame()) {
					
					WIN.setText("END GAME");
					WIN.setVisible(true);
					
					CONGRATULATION.setText("CONGRATULATIONS!!");
					CONGRATULATION.setVisible(true);
					CONGRATULATION.addAction(sequence(hide(), delay(1.0f), show(), delay(1.0f), hide(), delay(1.0f), show(), delay(1.0f), hide(), delay(1.0f), show()));
					
				}else {
					
					WIN.setText("YOU LOSE");
					WIN.setVisible(true);
					CONGRATULATION.setVisible(false);
					
					
				}
				
				
			}
			
		}
		
		
	}
	
	
	public void processStageEndLevel(){
		
		Texture logoWindows = null;
		
		if (gPS.getGamePlay().isNextLevel() || gPS.getGamePlay().isEndGame()) {
			logoWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Victory, Texture.class);
		}else {
			logoWindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Defeat, Texture.class);
		}
		
		logo_result.setTextureLogo(logoWindows);
		
	}
	
	
	public void draw(float delta) {
		if (stage != null) {
			processStageEndLevel();
			stage.act(delta);
			stage.draw();
		}
	}
	

}
