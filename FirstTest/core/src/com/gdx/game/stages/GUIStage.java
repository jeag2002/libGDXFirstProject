package com.gdx.game.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.elements.GUIStageIntermission;
import com.gdx.game.stages.elements.GUIStageMenu;
import com.gdx.game.stages.elements.GUIStageGamePlay;
import com.gdx.game.stages.enums.GUIEnum;

public class GUIStage {
	
	private Stage stage;
	
	
	private GUIStageMenu menu;
	private GUIStageIntermission intermission;
	private GUIStageGamePlay gameplay;
	
	private GUIEnum activeGUI;
	
	
	public GUIStage(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		menu = new GUIStageMenu(stage, gPS);
		intermission = new GUIStageIntermission(stage, gPS);
		gameplay = new GUIStageGamePlay(stage,gPS);
		
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
			
			activeGUI = GUIEnum.MENU;
			
			break;
		
		case INTERMISSION:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(true);
			gameplay.showGamePlayGUI(false);
			
			activeGUI = GUIEnum.INTERMISSION;
			
			break;
			
		case GAMEPLAY:
			
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(true);
			
			activeGUI = GUIEnum.GAMEPLAY;
			
			break;
			
		case NOTHING: 
		default:
		
			menu.showMenuGUI(false);
			intermission.showIntermissionGUI(false);
			gameplay.showGamePlayGUI(false);
			
			activeGUI = GUIEnum.NOTHING;
			
			break;
		}
		
	}
	
	
	public void init() {
		menu.init();
		intermission.init();
		gameplay.init();
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
				break;
		
		}
	}
	

}
