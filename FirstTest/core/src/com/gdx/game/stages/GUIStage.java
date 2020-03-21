package com.gdx.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.gdx.game.FirstTestGDX;



public class GUIStage {
	
	public Group grpMenuUI;
	private Stage stage;
	
	private Image imgLogo;
	
	private Label lblStart;
	private String txtStart = "Press \"A\" to start!";
	
	public GUIStage(Stage stage) {
		this.stage = stage;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initMenuGUI();
	}
	
	private void initMenuGUI() {
		
		grpMenuUI = new Group();
		
		//logo
		Texture texture_logo = FirstTestGDX.resources.get(FirstTestGDX.resources.imgLogo, Texture.class);
		TextureRegion txLogo = new TextureRegion(texture_logo);
        imgLogo = new Image(txLogo);
        imgLogo.setPosition(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 + 550, Align.center);
        imgLogo.setVisible(false);
        grpMenuUI.addActor(imgLogo);
        
        //label "Start"
        lblStart = new Label(txtStart, new Label.LabelStyle(FirstTestGDX.resources.font1, Color.WHITE));
        lblStart.setPosition(FirstTestGDX.screenWidth/2, FirstTestGDX.screenHeight / 2 - 150, Align.center);
        lblStart.setVisible(false);
        grpMenuUI.addActor(lblStart);
        
        stage.addActor(grpMenuUI);
	}
	
	public void showMenuGUI(boolean show) {
		
		imgLogo.setVisible(show);
		
		if (show == true) {
			
			imgLogo.addAction(Actions.moveToAligned(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 + 100, Align.center, 0.4f));
            lblStart.addAction(sequence(hide(), delay(0.7f), show()));

			
		}else {
			
			imgLogo.setVisible(false);
			lblStart.setVisible(false);
			
		}
		
	}
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	
	
	

}
