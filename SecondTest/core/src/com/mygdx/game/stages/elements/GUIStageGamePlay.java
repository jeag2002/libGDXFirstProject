package com.mygdx.game.stages.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.enemies.special.tanks.TankEnemy;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;

public class GUIStageGamePlay {
	
	private Stage stage;
	private GamePlayScreen gPS;
	private Cursor cursor;
	private DrawUtils dU;
	
	
	private Label frameRate;
	private Label PlayerPos;
	private Label ExitPos;
	
	private Label Enemy_1;
	private Label Enemy_2;
	private Label Enemy_3;
	private Label Enemy_4;
	private Label Enemy_5;
	private Label Enemy_6;
	private Label Enemy_7;
	private Label Enemy_8;
	private Label Enemy_9;
	
	
	
	
	
	public GUIStageGamePlay(Stage stage, GamePlayScreen gPS) {
		this.stage = stage;
		this.gPS = gPS;
		this.dU = new DrawUtils();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void init() {
		initGamePlayGUI();
	}
	
	
	private void tagsDebug() {
		
		frameRate = new Label("1", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		frameRate.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-30, Align.left);
		frameRate.setVisible(false);
		stage.addActor(frameRate);
		
		PlayerPos = new Label("2", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		PlayerPos.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-60, Align.left);
		PlayerPos.setVisible(false);
		stage.addActor(PlayerPos);
		
		ExitPos = new Label("3", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		ExitPos.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-90, Align.left);
		ExitPos.setVisible(false);
		stage.addActor(ExitPos);
		
		Enemy_1 = new Label("4", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_1.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-120, Align.left);
		Enemy_1.setVisible(false);
		stage.addActor(Enemy_1);
		
		Enemy_2 = new Label("5", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_2.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-150, Align.left);
		Enemy_2.setVisible(false);
		stage.addActor(Enemy_2);
		
		Enemy_3 = new Label("6", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_3.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-180, Align.left);
		Enemy_3.setVisible(false);
		stage.addActor(Enemy_3);
		
		Enemy_4 = new Label("4", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_4.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-210, Align.left);
		Enemy_4.setVisible(false);
		stage.addActor(Enemy_4);
		
		Enemy_5 = new Label("5", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_5.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-240, Align.left);
		Enemy_5.setVisible(false);
		stage.addActor(Enemy_5);
		
		Enemy_6 = new Label("6", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_6.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-270, Align.left);
		Enemy_6.setVisible(false);
		stage.addActor(Enemy_6);
		
		Enemy_7 = new Label("7", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_7.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-300, Align.left);
		Enemy_7.setVisible(false);
		stage.addActor(Enemy_7);
		
		Enemy_8 = new Label("8", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_8.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-330, Align.left);
		Enemy_8.setVisible(false);
		stage.addActor(Enemy_8);
		
		Enemy_9 = new Label("9", new Label.LabelStyle(SecondTestGDX.resources.font2,Color.RED));
		Enemy_9.setPosition(SecondTestGDX.screenWidth-300, SecondTestGDX.screenHeight-360, Align.left);
		Enemy_9.setVisible(false);
		stage.addActor(Enemy_9);
		
		
	}
	
	private void enableTagsDebug(boolean show) {
		
		frameRate.setVisible(show);
		PlayerPos.setVisible(show);
		ExitPos.setVisible(show);
		
		Enemy_1.setVisible(show);
		Enemy_2.setVisible(show);
		Enemy_3.setVisible(show);
		
		Enemy_4.setVisible(show);
		Enemy_5.setVisible(show);
		Enemy_6.setVisible(show);
		
		Enemy_7.setVisible(show);		
		Enemy_8.setVisible(show);
		Enemy_9.setVisible(show);

		
	}
	
	
	private void initGamePlayGUI() {
		if (SecondTestGDX.isMouseEnabled) {
			Pixmap pm = new Pixmap(Gdx.files.internal("gui/gameplay/cursor.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
		}
		
		if (SecondTestGDX.debugEngineEnabled){
			tagsDebug(); 
		}
		
	}
	
	public void showGamePlayGUI(boolean show) {
		
		if (SecondTestGDX.debugEngineEnabled) {
			enableTagsDebug(show);
		}
		
	}
	
	
	public void printGamePlayDebugData() {
		
		String frameRate = "FrameRate: " + Gdx.graphics.getFramesPerSecond() +  " fps";
		this.frameRate.setText(frameRate);
		
		float x = gPS.getGamePlay().getGameLogic().getPlayer().getX();
		float y = gPS.getGamePlay().getGameLogic().getPlayer().getY();
		
		String player = "Player: (" + x + "," + y + ")";
		this.PlayerPos.setText(player);
		
		x = gPS.getGamePlay().getExit().getX();
		y = gPS.getGamePlay().getExit().getY();
		
		String exit = "Exit: (" + x + "," + y + ")";
		this.ExitPos.setText(exit);
		
		int size = gPS.getGamePlay().getGameLogic().getTanks().size();
		
		if (size > 0) {
			TankEnemy tank_1 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(0);
			String tank1_Str = "Tank_1: (" + tank_1.getX() + "," + tank_1.getY() + ")";
			this.Enemy_1.setText(tank1_Str);
		}else {
			this.Enemy_1.setText("<EMPTY>");
		}
		
		
		if (size > 1) {
			TankEnemy tank_2 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(1);
			String tank2_Str = "Tank_2: (" + tank_2.getX() + "," + tank_2.getY() + ")";
			this.Enemy_2.setText(tank2_Str);
		}else {
			this.Enemy_2.setText("<EMPTY>");
		}
		
		
		if (size > 2) {
			TankEnemy tank_3 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(2);
			String tank3_Str = "Tank_3: (" + tank_3.getX() + "," + tank_3.getY() + ")";
			this.Enemy_3.setText(tank3_Str);
		}else {
			this.Enemy_3.setText("<EMPTY>");
		}
			
		if (size > 3) {
			TankEnemy tank_4 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(3);
			String tank4_Str = "Tank_4: (" + tank_4.getX() + "," + tank_4.getY() + ")";
			this.Enemy_4.setText(tank4_Str);
		}else {
			this.Enemy_4.setText("<EMPTY>");
		}
		
		if (size > 4) {
			TankEnemy tank_5 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(4);
			String tank5_Str = "Tank_5: (" + tank_5.getX() + "," + tank_5.getY() + ")";
			this.Enemy_5.setText(tank5_Str);
		}else {
			this.Enemy_5.setText("<EMPTY>");
		}
		
		if (size > 5) {
			TankEnemy tank_6 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(5);
			String tank6_Str = "Tank_6: (" + tank_6.getX() + "," + tank_6.getY() + ")";
			this.Enemy_6.setText(tank6_Str);
		}else {
			this.Enemy_6.setText("<EMPTY>");
		}
		
		if (size > 6) {
			TankEnemy tank_7 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(6);
			String tank7_Str = "Tank_7: (" + tank_7.getX() + "," + tank_7.getY() + ")";
			this.Enemy_7.setText(tank7_Str);
		}else {
			this.Enemy_7.setText("<EMPTY>");
		}
		
		
		if (size > 7) {
			TankEnemy tank_8 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(7);
			String tank8_Str = "Tank_8: (" + tank_8.getX() + "," + tank_8.getY() + ")";
			this.Enemy_8.setText(tank8_Str);
		}else {
			this.Enemy_8.setText("<EMPTY>");
		}
		
		if (size > 8) {
			TankEnemy tank_9 = (TankEnemy)gPS.getGamePlay().getGameLogic().getTanks().get(8);
			String tank9_Str = "Tank_9: (" + tank_9.getX() + "," + tank_9.getY() + ")";
			this.Enemy_9.setText(tank9_Str);
		}else {
			this.Enemy_9.setText("<EMPTY>");
		}
	
	}
	
	public void draw(float delta) {
		if (stage != null) {
			
			if (SecondTestGDX.debugEngineEnabled) {
				printGamePlayDebugData();
			}
			
			stage.act(delta);
			stage.draw();
		}
	}
	

}
