package com.gdx.game.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.background.Background;
import com.gdx.game.elements.player.Player;
import com.gdx.game.elements.player.Player;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.PlayerMovements;

import java.util.Random;

public class GamePlay {
	
	private GameElementLogic gEL;
	
	//game states
	private boolean started;
	private boolean gameover;
	private boolean paused;
	
	
	private Player player;
	private Background background;
	
	
	public static Random random;
	
	public GamePlay(GamePlayScreen gs) {
		gEL = new GameElementLogic();
		random = new Random(System.currentTimeMillis());
		init();
	}
	
	private void init() {
		initBackground();
		initPlayer();
	}

	private void initBackground() {
		background = new Background();
	}
	
	private void initPlayer() {
		
		player = new Player(this);
		player.setLocationAndSize(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2-389, 64, 64);
		player.setCollisionArea(0, 0, 64, 64);
		
		/*
		player = new Player(this);
		player.setSize(64, 64);
		player.setPosition(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2 - 380);
		player.setCollisionArea(0, 0, 64, 64);
		*/
	}
	
	public void playerMoveUp() {		
		player.move(PlayerMovements.UP);
	}
	public void playerMoveLeft() {
		player.move(PlayerMovements.LEFT);
	}
	public void playerMoveRight() {
		player.move(PlayerMovements.RIGHT);
	}
	public void playerMoveDown() {
		player.move(PlayerMovements.DOWN);
	}
	public void playerShoot() {
		player.move(PlayerMovements.SHOOT);
	}
	
	public void update(float delta){
		background.update(delta);
		player.update(delta);
		gEL.updateSpawns(delta);
	}
	
	public void draw(SpriteBatch sb) {
		background.draw(sb);
		if (started) {
			player.draw(sb);
			gEL.drawSpawns(sb);
		}
		
	}
	
	public void dispose() {
		if (started) {
			player.dispose();
		}
	}
	
	
	public boolean isStarted() {
	    return started;
	}
	
    public boolean isGameover() {
        return gameover;
    }
    
    public void start() {
        started = true;
        gameover = false;
        
        player.start();
    }
    
    public void pause() {
        paused = true;
    }
    
    public void resume(){
        paused = false;
    }
    
    public GameElementLogic getgEL() {
		return gEL;
	}



}
