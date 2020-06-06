package com.mygdx.game.stages.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.LoadingBarWithBorders;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class GUIStageIntermission{
	
	public Group grpStartUI;
	private Stage stage;
	private GamePlayScreen gPS;
	
	private LoadingBarWithBorders bar;
	
	
	private Label lblLevel;
	
	
	public GUIStageIntermission(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initIntermissionGUI();
	}
	
	private void initIntermissionGUI() {
		
		String txt = "GENERATING MAP ...";
		
		lblLevel = new Label(txt, new Label.LabelStyle(SecondTestGDX.resources.font1,Color.BLACK));
		lblLevel.setPosition(5, 70, Align.left);
		lblLevel.setVisible(false);
		
		stage.addActor(lblLevel);
		
		bar = new LoadingBarWithBorders(SecondTestGDX.screenWidth-10,20);
		bar.setPosition(5, 30);	
		bar.setVisible(false);
		
		stage.addActor(bar);
	}
	
	public void showIntermissionGUI(boolean show) {
		lblLevel.setVisible(show);
		bar.setVisible(show);
		
		if (show) {
			lblLevel.addAction(sequence(hide(), delay(1.0f), show(), delay(1.0f), hide(), delay(1.0f), show(), delay(1.0f), hide(), delay(1.0f), show() ));
		}
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			bar.setValue(bar.getValue() + 0.1f);
			stage.act(delta);
			stage.draw();
		}
	}
	

}
