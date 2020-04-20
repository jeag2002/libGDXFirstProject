package com.gdx.game.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.background.Background;
import com.gdx.game.elements.player.Player;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.PlayerMovements;

import java.util.Random;

public class GamePlay {
	
	private GameElementLogic gEL;
	private GamePlayScreen gPS;
	
	//game states
	private boolean started;
	private boolean gameover;
	private boolean paused;
	
	
	private float timerEnemyGenerator = 0;
	
	
	private Player player;
	private Background background;
	
	public static Random random;
	
	public GamePlay(GamePlayScreen gs) {
		
		
		gEL = new GameElementLogic();
		random = new Random(System.currentTimeMillis());
		gPS = gs;
		
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
		
		player = new Player(this.getgEL().getSpawnPool(), this.getgEL().getWorld());
		player.setLocationAndSize(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2-389, 64, 64);
		//player.setCollisionArea(0, 0, 64, 64);
		gEL.setPlayer(player);
		
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
		
		if (!gEL.isEnemyGenerationTrigger()) {
			timerEnemyGenerator += delta * GameLevelLogic.speedUpFactor;
			if (timerEnemyGenerator >= GameLevelLogic.timeGapEnemyGeneration) {
				gEL.setEnemyGenerationTrigger(true);
				timerEnemyGenerator = 0.0f;
			}
		}
		
		background.update(delta);
		player.update(delta);
		
		gEL.generateElements(delta);
		gEL.updateSpawns(delta);
		gEL.processCollision(delta);
		gEL.removeOldBodies();
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
		gEL.dispose();
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
