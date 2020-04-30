package com.gdx.game.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.background.Background;
import com.gdx.game.elements.player.Player;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.screens.GamePlayScreen;
import com.gdx.game.stages.enums.PlayerMovements;

import java.util.Random;

public class GamePlay {
	
	private static final float bgSpeed = 50.0f;
	
	private GameElementLogic gEL;
	private GamePlayScreen gPS;
	
	//game states
	private boolean started;
	private boolean gameover;
	private boolean paused;
	
	
	private float timerEnemyGenerator = 0;
	
	
	
	private Player player;
	private Background background;
	
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private OrthographicCamera camera;
	
	
	
	public static Random random;
	
	public GamePlay(GamePlayScreen gPS) {
		this.gEL = new GameElementLogic(gPS);
		this.random = new Random(System.currentTimeMillis());
		this.gPS = gPS;
		init();
	}
	
	private void init() {
		initBackground();
		initTiledBackground();
		initPlayer();	
	}
	
	private void initTiledBackground() {
		this.tiledMap = new TmxMapLoader().load(gPS.getgLL().map_level_1);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
		gEL.setTiledMap(tiledMap);	
	}
	

	private void initBackground() {
		background = new Background();
	}
	
	private void initPlayer() {
		
		player = new Player(this.getgEL().getSpawnPool(), this.getgEL().getWorld(), gPS);
		player.setLocationAndSize(FirstTestGDX.screenWidth / 2, FirstTestGDX.screenHeight / 2-389, 64, 64);
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
		
		if (started) {
			player.update(delta);
			gEL.generateElements(delta);
			gEL.updateSpawns(delta);
			gEL.processCollisionWorld(camera);
			gEL.processCollision(delta);
			gEL.removeOldBodies();
			gEL.createNewBodies();
			
		}
	}
	
	public void drawBackground(SpriteBatch sb) {
		background.draw(sb);
	}
	
	
	public void drawMap() {
		if (started) {
			camera.update();
			tiledMapRenderer.setView(camera);
			tiledMapRenderer.render();
		}
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void draw(SpriteBatch sb) {
		if (started) {			
			player.draw(sb);
			gEL.drawSpawns(sb);
		}	
	}
	
	public void moveCamera(float delta) {
		if (started) {
			if (!gPS.getgLL().isEndLevel() && !gPS.getgLL().isGameOver()) {camera.translate(0, GameLevelLogic.speedUpFactor * bgSpeed * delta);}
		}
	}
	
	
	public void dispose() {
		if (started) {
			player.dispose();
		}
		gEL.dispose();
		tiledMap.dispose();
		
	}
	
	
	public boolean isStarted() {
	    return started;
	}
	
	public void setStarted(boolean started) {
		this.started = started;
	}
    
    public void start() {
        started = true;
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
