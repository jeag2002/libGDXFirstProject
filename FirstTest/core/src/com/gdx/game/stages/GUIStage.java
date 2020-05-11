package com.gdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.elements.GUIStageIntermission;
import com.gdx.game.stages.elements.GUIStageMenu;
import com.gdx.game.stages.elements.GUIStageRanking;
import com.gdx.game.stages.elements.GUIStageSettings;
import com.gdx.game.stages.elements.GUIStageEnd;
import com.gdx.game.stages.elements.GUIStageFinal;
import com.gdx.game.stages.elements.GUIStageGamePlay;
import com.gdx.game.stages.elements.GUIStageGamePlayAndroid;
import com.gdx.game.stages.enums.GUIEnum;

public class GUIStage {
	
	private Stage stage;
	
	
	private GUIStageMenu menu;
	private GUIStageIntermission intermission;
	private GUIStageGamePlay gameplay;
	private GUIStageGamePlayAndroid gameplayAndroid;
	
	private GUIStageSettings settings;
	private GUIStageEnd end;
	private GUIStageFinal finalGame;
	private GUIStageRanking ranking;
	
	
	private GUIEnum activeGUI;
	
	
	public GUIStage(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		menu = new GUIStageMenu(stage, gPS);
		intermission = new GUIStageIntermission(stage, gPS);
		
		gameplay = new GUIStageGamePlay(stage,gPS);
		gameplayAndroid = new GUIStageGamePlayAndroid(stage,gPS);
		
		end = new GUIStageEnd(stage,gPS);
		settings = new GUIStageSettings(stage,gPS);
		
		finalGame = new GUIStageFinal(stage,gPS);
		ranking = new GUIStageRanking(stage,gPS);
		
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
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			finalGame.showFinalGUI(false);
			ranking.showRankingGUI(false);
			
			activeGUI = GUIEnum.MENU;
			
			break;
			
		case SETTINGS:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(true);
			
			finalGame.showFinalGUI(false);
			ranking.showRankingGUI(false);
			
			activeGUI = GUIEnum.SETTINGS;
			break;
		
		case INTERMISSION:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(true);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			finalGame.showFinalGUI(false);
			ranking.showRankingGUI(false);
			
			activeGUI = GUIEnum.INTERMISSION;
			
			break;
			
		case GAMEPLAY:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(true);
			if (Gdx.app.getType().equals(ApplicationType.Android)) {
				gameplayAndroid.showGamePlayAndroidGUI(true);
			}else{
				gameplayAndroid.showGamePlayAndroidGUI(false);
			}
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			ranking.showRankingGUI(false);
			finalGame.showFinalGUI(false);
			
			activeGUI = GUIEnum.GAMEPLAY;
			
			break;
		
		case ENDLEVEL:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(true);
			settings.showSettingsGUI(false);
			
			ranking.showRankingGUI(false);
			finalGame.showFinalGUI(false);
			
			activeGUI = GUIEnum.ENDLEVEL;
			
			break;
	
		case FINAL:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			ranking.showRankingGUI(false);
			finalGame.showFinalGUI(true);
			
			activeGUI = GUIEnum.FINAL;
			
			break;
		
		
		case RANKING:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			ranking.showRankingGUI(true);
			finalGame.showFinalGUI(false);
			
			activeGUI = GUIEnum.RANKING;
			
			break;
			
		case NOTHING: 
		
		default:
		
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			
			gameplay.showGamePlayGUI(false);
			gameplayAndroid.showGamePlayAndroidGUI(false);
			
			end.showEndGUI(false);
			settings.showSettingsGUI(false);
			
			finalGame.showFinalGUI(false);
			ranking.showRankingGUI(false);
			
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
		settings.init();
		
		ranking.init();
		finalGame.init();
	}
	
	public void draw(float delta) {
		
		switch(activeGUI) {
			case MENU:
				menu.draw(delta);
				break;
				
			case INTERMISSION:
				intermission.draw(delta);
				break;
				
			case SETTINGS:
				settings.draw(delta);
				break;
				
			case GAMEPLAY:
				gameplay.draw(delta);
				if (Gdx.app.getType().equals(ApplicationType.Android)) {
					gameplayAndroid.draw(delta);
				}
				break;
			
			case RANKING:
				ranking.draw(delta);
				break;
				
			case ENDLEVEL:
				end.draw(delta);
				break;
			
			case FINAL:
				finalGame.draw(delta);
				break;
		
		}
	}
	

}
