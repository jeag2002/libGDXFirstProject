package com.mygdx.game.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.enums.GUIEnum;
import com.mygdx.game.stages.elements.GUIStageStart;

public class GUIStage {
	
	private Stage stage;
	private GUIStageStart start;
	
	private GUIEnum activeGUI;

	public GUIStage(Stage stage) {
		this.stage = stage;
		start = new GUIStageStart(stage);
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public void activeGUI(GUIEnum menu) {
		
		switch(menu) {
				
			case START:
				start.showStartGUI(true);
				activeGUI = GUIEnum.START;
				break;
			
			default:
				start.showStartGUI(false);
				activeGUI = GUIEnum.NOTHING;
				break;		
		}
		
	}
	
	public void init() {
		start.init();
	}
	
	public void draw(float delta) {
		
		switch(activeGUI) {
			case START:
				start.draw(delta);
				break;
		}
			
	}
	
}
