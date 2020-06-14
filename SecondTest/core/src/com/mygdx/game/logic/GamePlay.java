package com.mygdx.game.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.logic.map.SimpleMapGeneration;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.elements.Background;
import com.mygdx.game.utils.NewItem;

public class GamePlay {
	
	private static final int INDEX_BACKGROUND = 0;
	private static final int INDEX_BORDER = 1;
	private static final int INDEX_TILEMAP = 2;
	private static final int INDEX_FOREST = 3;
	
	private GamePlayScreen gPS;
	private Background background;
	
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private OrthographicCamera camera;
	
	private PlayerMovementsEnum pEnum;
	
	private GameElementLogic gameLogic;
	
	private SimpleMapGeneration sMG;
	private boolean started;
	
	private ShapeRenderer render;
	
	private Random rand;
	
	public GamePlay(GamePlayScreen gPS) {	
		this.gPS = gPS;
		this.background = new Background();
		this.sMG = new SimpleMapGeneration();
		this.camera = new OrthographicCamera();
		this.started = false;
		this.pEnum = PlayerMovementsEnum.IDLE;
		this.rand = new Random();	
		this.gameLogic = new GameElementLogic(gPS);
	}
	
	
	
	public void start() {
		started = true;
	}
	
	public boolean isStart() {
		return this.started;
	}
	
	
	public void init() {
		
		if (
		(GameLogicInformation.getLevel() == GameLogicInformation.START) || 
		(GameLogicInformation.getLevel() == GameLogicInformation.INTERMISSION)){
			initStart();
		}else if (GameLogicInformation.getLevel() > GameLogicInformation.INTERMISSION) {
			initGamePlay();
		}
	}
	
	public void processTileGeneration() {
		
		int index = rand.nextInt(3);
		
		TileMapEnum[] data = GameLogicInformation.getRandomTileMap(index);
		
		this.gameLogic.initWorld();
		
		sMG.setWorld(this.gameLogic.getWorld());
		tiledMap = sMG.createSimpleMap(SecondTestGDX.sizeMapTileWidth_BG, 
									   SecondTestGDX.sizeMapTileHeight_BG,
									   SecondTestGDX.sizeMapTileWidth_TL, 
									   SecondTestGDX.sizeMapTileHeight_TL, 
									   data[INDEX_BACKGROUND],
									   data[INDEX_BORDER], 
									   data[INDEX_TILEMAP],
									   data[INDEX_FOREST],
									   GameLogicInformation.PLAYERS,
									   GameLogicInformation.ENEMIES);
		situationPlayer();
		//sMG.createSimpleMapTest_1(SecondTestGDX.sizeMapTileWidth_TL, SecondTestGDX.sizeMapTileHeight_TL, TileMapEnum.GROUND_TILE_02_C);
	}
	
	
	public void situationPlayer() {
		
		ArrayList<NewItem> posPlayers = sMG.getPlayers();
		
		if (posPlayers.size() > 0) {
			
			NewItem posPlayer = posPlayers.get(0);
			this.gameLogic.initPlayer(posPlayer.getType(),
									  posPlayer.getX(), //+  (SecondTestGDX.tileWidth_TL/4), 
									  posPlayer.getY(), //+  (SecondTestGDX.tileHeight_TL/4), 
									  SecondTestGDX.tilePlayerWidth_TL, 
									  SecondTestGDX.tilePlayerHeight_TL);
		}
	}
	
	
	
	public void initStart() {
		background();
	}
	
	public void background() {	
		background.backgroundImage();
		background.setBounds(0,0, SecondTestGDX.screenWidth, SecondTestGDX.screenHeight);
	}
	
	public void initGamePlay() {
		if (started) {
			if (tiledMap != null) {
				this.tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
				this.camera.setToOrtho(false);
				
				ArrayList<NewItem> posPlayers = sMG.getPlayers();
				
			    if (posPlayers.size() > 0) {
			    	
			    	
			    	this.camera.position.set(
			    			this.gameLogic.getPlayer().getX(), 
			    			this.gameLogic.getPlayer().getY(), 
			    			0);
			    	
			    	//Vector2 vec = new Vector2();
			    	//vec.x = this.gameLogic.getPlayer().getX();
			    	//vec.y = this.gameLogic.getPlayer().getY();
			    	
			    	
			    	
			    	
			    	/*
			    	this.camera.position.set(
			    			this.gameLogic.getPlayer().getX() +  this.gameLogic.getPlayer().getWidth() / 2, 
			    			this.gameLogic.getPlayer().getY() + this.gameLogic.getPlayer().getHeight() / 2, 
			    			0);
			    	
			    	this.camera.viewportWidth = SecondTestGDX.screenWidth;
			    	this.camera.viewportHeight = SecondTestGDX.screenHeight;
			    	*/
			    	//this.camera.translate(vec);
			    }else {
			    	this.camera.position.set(SecondTestGDX.screenWidth/2,SecondTestGDX.screenHeight/2, 0);
			    }
			    
			    this.camera.update();
			}
		}
	}
	
	public void playerMoveUp() {pEnum = PlayerMovementsEnum.UP;}
	public void playerMoveLeft() {pEnum = PlayerMovementsEnum.LEFT;}
	public void playerMoveRight() {pEnum = PlayerMovementsEnum.RIGHT;}
	public void playerMoveDown() {pEnum = PlayerMovementsEnum.DOWN;}
	public void playerShoot() {}
	public void playerChange() {}
	public void playerTurretClockWise() {}
	public void playerTurretAntiClockWise() {}
	
	public void update(float delta) {
		if (started) {
			if (pEnum.equals(PlayerMovementsEnum.UP)) {
				camera.translate(0, GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta);
			}else if (pEnum.equals(PlayerMovementsEnum.LEFT)) {
				camera.translate(GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta*(-1),0);
			}else if (pEnum.equals(PlayerMovementsEnum.RIGHT)) {
				camera.translate(GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta, 0);
			}else if (pEnum.equals(PlayerMovementsEnum.DOWN)) {				
				camera.translate(0, GameLogicInformation.speedUpFactor * GameLogicInformation.bgSpeed * delta*(-1));
			}
			camera.update();
		}
	}
	
	
	public void updateElements(float delta) {
		if (started) {
			gameLogic.updatePlayer(delta);
			gameLogic.updateSpawns(delta);
		}
	}
	
	
	public boolean boundaries() {
		
		boolean response = false;
		
		if (started) {
			if (pEnum.equals(PlayerMovementsEnum.UP)) {
				response = ((camera.position.y + SecondTestGDX.screenHeight/2) > (SecondTestGDX.sizeMapTileHeight_TL * 64));
			}else if (pEnum.equals(PlayerMovementsEnum.DOWN)) {
				response = ((camera.position.y - SecondTestGDX.screenHeight/2) < 0);
			}else if (pEnum.equals(PlayerMovementsEnum.LEFT)) {
				response = ((camera.position.x - SecondTestGDX.screenWidth/2) < 0);
			}else if (pEnum.equals(PlayerMovementsEnum.RIGHT)) {
				response = ((camera.position.x + SecondTestGDX.screenWidth/2) > (SecondTestGDX.sizeMapTileWidth_TL * 64));
			}
		}
		
		return response;
	}
	
	
	public void draw(SpriteBatch sb) {
	}
	
	
	public void drawBackground(SpriteBatch sb) {
		if ((GameLogicInformation.getLevel() == GameLogicInformation.START) || 
			(GameLogicInformation.getLevel() == GameLogicInformation.INTERMISSION)){
			background.draw(sb);
		}
	}
	
	public void drawMap() {
		if (started) {
			if (tiledMap != null) {
				camera.update();
				tiledMapRenderer.setView(camera);
				tiledMapRenderer.render();
			}
		}
	}
	
	public void drawElements(SpriteBatch sb) {
		if (started) {
			if (tiledMap != null) {
				gameLogic.drawPlayer(sb);
				gameLogic.drawSpawns(sb);
			}
		}
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	

}
