package com.mygdx.game.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.enums.GUIEnum;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.elements.GUIStageIntermission;
import com.mygdx.game.stages.elements.GUIStageStart;

public class GUIStage {
	
	private Stage stage;
	private GUIStageStart start;
	private GUIStageIntermission intermission;
	
	private GUIEnum activeGUI;

	public GUIStage(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		start = new GUIStageStart(stage, gPS);
		intermission = new GUIStageIntermission(stage, gPS);
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void activeGUI(GUIEnum menu) {
		
		switch(menu) {
				
			case START:
				start.showStartGUI(true);
				intermission.showIntermissionGUI(false);
				activeGUI = GUIEnum.START;
				break;
			
			case INTERMISSION:
				start.showStartGUI(false);
				intermission.showIntermissionGUI(true);
				activeGUI = GUIEnum.INTERMISSION;
				break;
				
			case NOTHING:
				start.showStartGUI(false);
				intermission.showIntermissionGUI(false);
				activeGUI = GUIEnum.NOTHING;
				break;
			
			case GAMEPLAY:
				start.showStartGUI(false);
				intermission.showIntermissionGUI(false);
				activeGUI = GUIEnum.GAMEPLAY;
				break;
				
			default:
				start.showStartGUI(false);
				intermission.showIntermissionGUI(false);
				activeGUI = GUIEnum.NOTHING;
				break;		
		}
		
	}
	
	public void init() {
		start.init();
		intermission.init();
	}
	
	public void draw(float delta) {
		
		switch(activeGUI) {
			case START:
				start.draw(delta);
				break;
			case INTERMISSION:
				intermission.draw(delta);
				break;
		}
			
	}
	
}
