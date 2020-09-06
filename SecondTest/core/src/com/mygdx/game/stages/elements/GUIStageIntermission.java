package com.mygdx.game.stages.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementsGUI;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.stages.components.LoadingBarWithBorders;
import com.mygdx.game.stages.components.LogoItem;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class GUIStageIntermission{
	
	public Group grpStartUI;
	private Stage stage;
	private GamePlayScreen gPS;
	
	private LoadingBarWithBorders bar;
	private LogoItem msgWindow;
	
	
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
		
		Texture textwindows = SecondTestGDX.resources.get(SecondTestGDX.resources.Window, Texture.class);
		
		lblLevel = new Label(txt, new Label.LabelStyle(SecondTestGDX.resources.font1,Color.BLACK));
		lblLevel.setPosition(5, 70, Align.left);
		lblLevel.setVisible(false);
		
		stage.addActor(lblLevel);
		
		bar = new LoadingBarWithBorders(SecondTestGDX.screenWidth-10,20);
		bar.setPosition(5, 30);	
		bar.setVisible(false);
		
		stage.addActor(bar);
		
		msgWindow = new LogoItem(ElementsGUI.MSGWINDOWS,textwindows,gPS);
		msgWindow.setPosition(SecondTestGDX.screenWidth/2-256, SecondTestGDX.screenHeight/2-128);
		msgWindow.setSize(512, 256);
		msgWindow.setVisible(false);
		
		stage.addActor(msgWindow);
		
	}
	
	public void showIntermissionGUI(boolean show) {
		lblLevel.setVisible(show);
		bar.setVisible(show);
		msgWindow.setVisible(show);
		
		if (show) {
			lblLevel.addAction(sequence(hide(), delay(1.0f), show(), delay(1.0f), hide(), delay(1.0f), show(), delay(1.0f), hide()));
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
