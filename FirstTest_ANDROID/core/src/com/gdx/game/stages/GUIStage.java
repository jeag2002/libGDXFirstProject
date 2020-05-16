package com.gdx.game.stages;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.elements.GUIStageGamePlayAndroid;
import com.gdx.game.stages.elements.GUIStageIntermission;
import com.gdx.game.stages.elements.GUIStageMenu;
import com.gdx.game.stages.elements.GUIStageEnd;
import com.gdx.game.stages.elements.GUIStageGamePlay;
import com.gdx.game.stages.enums.GUIEnum;

public class GUIStage {
	
	private Stage stage;
	
	
	private GUIStageMenu menu;
	private GUIStageIntermission intermission;
	private GUIStageGamePlay gameplay;
	private GUIStageGamePlayAndroid gameplayAndroid;
	
	private GUIStageEnd end;
	
	private GUIEnum activeGUI;
	
	
	public GUIStage(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		menu = new GUIStageMenu(stage, gPS);
		intermission = new GUIStageIntermission(stage, gPS);
		gameplay = new GUIStageGamePlay(stage,gPS);
		end = new GUIStageEnd(stage,gPS);
		gameplayAndroid = new GUIStageGamePlayAndroid(stage,gPS);

		activeGUI = GUIEnum.NOTHING;
	
	}
	
	public Stage getStage() {
        return stage;
    }
	
	public void activeGUI(GUIEnum level) {
		
		switch (level) {
		
		case MENU:
			
			menu.showMenuGUI(true);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(false);
			end.showEndGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			activeGUI = GUIEnum.MENU;
			
			break;
		
		case INTERMISSION:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(true);
			gameplay.showGamePlayGUI(false);
			end.showEndGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);

			
			activeGUI = GUIEnum.INTERMISSION;
			
			break;
			
		case GAMEPLAY:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(true);
			end.showEndGUI(false);

			if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
				gameplayAndroid.showGamePlayAndroidGUI(true);
			}else{
				gameplayAndroid.showGamePlayAndroidGUI(false);
			}
			
			activeGUI = GUIEnum.GAMEPLAY;
			
			break;
		
		case ENDLEVEL:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			end.showEndGUI(true);

			activeGUI = GUIEnum.ENDLEVEL;
			
			break;
			
		case NOTHING: 
		
		default:
		
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(false);

			end.showEndGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);

			activeGUI = GUIEnum.NOTHING;
			
			break;
		}
		
	}
	
	
	public void init() {
		menu.init();
		intermission.init();
		gameplay.init();
		gameplayAndroid.init();
		end.init();
	}
	
	public void draw(float delta) {
		
		switch(activeGUI) {
			case MENU:
				menu.draw(delta);
				break;
				
			case INTERMISSION:
				intermission.draw(delta);
				break;
				
			case GAMEPLAY:
				gameplay.draw(delta);
				if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
					gameplayAndroid.draw(delta);
				}

				break;
			
			case ENDLEVEL:
				end.draw(delta);
				break;
		
		}
	}
	

}
