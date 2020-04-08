package com.gdx.game.stages.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GUIStageIntermission {
	
	private String txt_Level = "ENTER LEVEL 0";
	private String txt_3 = "3";
	private String txt_2 = "2";
	private String txt_1 = "1";
	private String txt_GO = "GO!";
	
	
	public Group grpMenuUI;
	private Stage stage;
	
	private Label lblLevel;
	
	private Label lblStart_1;
	private Label lblStart_2;
	private Label lblStart_3;
	private Label lblStart_4;
	
	private GamePlayScreen gPS;
	
	public GUIStageIntermission(Stage stage, GamePlayScreen gPS){
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
		
		grpMenuUI = new Group();
		
		//Label  LEVEL
		lblLevel = new Label(txt_Level, new Label.LabelStyle(FirstTestGDX.resources.font1,Color.WHITE));
		lblLevel = new Label(txt_3, new Label.LabelStyle(FirstTestGDX.resources.font1, Color.WHITE));
		lblLevel.setPosition( FirstTestGDX.screenWidth / 2-100, FirstTestGDX.screenHeight / 2 + 150, Align.center);
		lblLevel.setVisible(false);
		grpMenuUI.addActor(lblLevel);
		
		BitmapFont font_2 = FirstTestGDX.resources.font2;

		
		//label TIME
        lblStart_1 = new Label(txt_3, new Label.LabelStyle(font_2, Color.WHITE));
        lblStart_1.setPosition( FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        lblStart_1.setVisible(false);
        grpMenuUI.addActor(lblStart_1);
        
        lblStart_2 = new Label(txt_2, new Label.LabelStyle(font_2, Color.WHITE));
        lblStart_2.setPosition( FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        lblStart_2.setVisible(false);
        grpMenuUI.addActor(lblStart_2);
        
        lblStart_3 = new Label(txt_1, new Label.LabelStyle(font_2, Color.WHITE));
        lblStart_3.setPosition( FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        lblStart_3.setVisible(false);
        grpMenuUI.addActor(lblStart_3);
        
        lblStart_4 = new Label(txt_GO, new Label.LabelStyle(font_2, Color.WHITE));
        lblStart_4.setPosition( FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        lblStart_4.setVisible(false);
        grpMenuUI.addActor(lblStart_4);
        

        //Add GUI to stage
        stage.addActor(grpMenuUI);

	}
	
	
	public void showIntermissionGUI(boolean show) {
		lblLevel.setVisible(show);
		lblStart_1.setVisible(show);
		lblStart_2.setVisible(show);
		lblStart_3.setVisible(show);
		lblStart_4.setVisible(show);
		
		if (show) {
			
			lblLevel.setText(gPS.getgLL().level);			
			lblStart_1.addAction(sequence(hide(), delay(1.0f), show(), delay(1.0f), hide()));
			lblStart_2.addAction(sequence(hide(), delay(2.0f), show(), delay(1.0f), hide()));
			lblStart_3.addAction(sequence(hide(), delay(3.0f), show(), delay(1.0f), hide()));
			lblStart_4.addAction(sequence(hide(), delay(4.0f), show(), delay(1.0f)));

			
		}else {
			lblLevel.setVisible(false);
			lblStart_1.setVisible(false);
			lblStart_2.setVisible(false);
			lblStart_3.setVisible(false);
			lblStart_4.setVisible(false);
		}
	}
	
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	public String getTxt_Level() {
		return txt_Level;
	}

	public void setTxt_Level(String txt_Level) {
		this.txt_Level = txt_Level;
	}
	
	

	
	
	
	
	
	

}
