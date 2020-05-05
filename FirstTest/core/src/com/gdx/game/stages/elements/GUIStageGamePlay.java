package com.gdx.game.stages.elements;


import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.hide;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.show;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.gameplay.BottonBar;
import com.gdx.game.stages.gameplay.LogoItem;
import com.gdx.game.stages.gameplay.ProgressBarLife;
import com.gdx.game.stages.gameplay.ProgressBarShield;

public class GUIStageGamePlay {
	
	
	private GamePlayScreen gPS;
	private Stage stage;
	
	public Group grpMenuUI;
	
	public ProgressBarLife pBarHealth;
	public ProgressBarShield pBarShield;
	public BottonBar pBottonBar;
	public LogoItem lItem;
	
	private BitmapFont font;
	private Label lblStart_1;
	
	
	public GUIStageGamePlay(Stage stage, GamePlayScreen gPS){
		this.stage = stage;
		this.gPS = gPS;
		
		font = new BitmapFont(Gdx.files.internal("fonts/Bangers_bitmap.fnt"),false);
		font.getData().setScale(1f, 1f);
		
	}
	
	public void init() {
		initGamePlayGUI();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	
	private void initGamePlayGUI() {
		grpMenuUI = new Group();
		
		
		Texture textBarHealth = FirstTestGDX.resources.get(FirstTestGDX.resources.imgHealthBar, Texture.class);
		Texture textBarHealthDot = FirstTestGDX.resources.get(FirstTestGDX.resources.imgHealthBarDot, Texture.class);
		Texture textBarShield = FirstTestGDX.resources.get(FirstTestGDX.resources.imgArmorBar, Texture.class);		
		Texture textBarShieldDot = FirstTestGDX.resources.get(FirstTestGDX.resources.imgArmorBarDot, Texture.class);
		Texture textBarBotton = FirstTestGDX.resources.get(FirstTestGDX.resources.imgStatsBar,Texture.class);
		Texture textBarLogo = FirstTestGDX.resources.get(FirstTestGDX.resources.imgIconSpecial,Texture.class);
		
		Texture textClockLogo = FirstTestGDX.resources.get(FirstTestGDX.resources.imgClock,Texture.class);
		Texture textCrystalLogo = FirstTestGDX.resources.get(FirstTestGDX.resources.imgCristal,Texture.class);
				
		pBarHealth = new ProgressBarLife(textBarHealth, textBarHealthDot, gPS);
		pBarHealth.setPosition(10, FirstTestGDX.screenHeight-50);
		pBarHealth.setSize(200, 32);
		pBarHealth.setVisible(false);
		grpMenuUI.addActor(pBarHealth);
		
		pBarShield = new ProgressBarShield(textBarShield, textBarShieldDot, gPS);
		pBarShield.setPosition(10, FirstTestGDX.screenHeight-84);
		pBarShield.setSize(200, 32);
		pBarShield.setVisible(false);
		grpMenuUI.addActor(pBarShield);
		
		pBottonBar = new BottonBar(textBarBotton,textClockLogo,textCrystalLogo,gPS);
		pBottonBar.setPosition(FirstTestGDX.screenWidth/2-200, 20);
		pBottonBar.setSize(400, 32);
		pBottonBar.setVisible(false);
		grpMenuUI.addActor(pBottonBar);
		
		String TIMES = "";
		
		lblStart_1 = new Label(TIMES, new Label.LabelStyle(font, Color.WHITE));
        lblStart_1.setPosition(FirstTestGDX.screenWidth/2 - 110 , FirstTestGDX.screenHeight-250, Align.center);
        lblStart_1.setSize(100, 50);
        lblStart_1.setVisible(true);
        grpMenuUI.addActor(lblStart_1);
		
		
		lItem = new LogoItem(textBarLogo,gPS);
		lItem.setPosition(FirstTestGDX.screenWidth-74, FirstTestGDX.screenHeight-75);
		lItem.setSize(64, 64);
		lItem.setVisible(false);
		grpMenuUI.addActor(lItem);
		
		stage.addActor(grpMenuUI);
	}
	
	public void showGamePlayGUI(boolean show) {
		
		pBarHealth.setVisible(show);
		pBarShield.setVisible(show);
		pBottonBar.setVisible(show);
		lItem.setVisible(show);	
		lblStart_1.setText("");
	}
	
	
	
	public void draw(float delta) {
		
		
		String TIME = "";
		if ((this.gPS.getgLL().isEndLevel() || this.gPS.getgLL().isGameOver())) {
			
			if (this.gPS.getgLL().isEndLevel()) {TIME = "LEVEL COMPLETED!";}
			else if (this.gPS.getgLL().isGameOver()) {TIME = "GAME OVER!";}
			lblStart_1.setText(TIME);
		}else {
			lblStart_1.setText("");
		}
		
		
		
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	

}
