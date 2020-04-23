package com.gdx.game.stages.elements;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	
	public GUIStageGamePlay(Stage stage, GamePlayScreen gPS){
		this.stage = stage;
		this.gPS = gPS;
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
	}
	
	
	
	public void draw(float delta) {
		if (stage != null) {
			stage.act(delta);
			stage.draw();
		}
	}
	

}
