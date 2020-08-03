package com.mygdx.game.logic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.PlayerMovementsEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.logic.map.SimpleMapGeneration;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.screens.elements.Background;
import com.mygdx.game.utils.NewItem;
import com.mygdx.game.utils.ShaderEngine;


public class GamePlay {
	
	
	private static final int INDEX_SINGLE_PLAYER = 1;
	
	private static final int INDEX_GAMELOGICINFORMATION_BACKGROUND = 0;
	private static final int INDEX_GAMELOGICINFORMATION_BORDER = 1;
	private static final int INDEX_GAMELOGICINFORMATION_TILEMAP = 2;
	private static final int INDEX_GAMELOGICINFORMATION_FOREST = 3;
	
	
	private static final int NO_LIGHTS = 0;
	private static final int LIGHTS = 1;
	
	private static final float TIME = 1f;
	
	
	private int lights;
	
	private GamePlayScreen gPS;
	private Background background;
	
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private OrthographicCamera camera;
	
	private PlayerMovementsEnum pEnum;
	
	private GameElementLogic gameLogic;
	
	
	private SimpleMapGeneration sMG;
	private boolean started;
	
	private Random rand;
	
	private float time;
	private float time_2;
	
	private boolean pulse;
	private boolean pulse_2;
	
	private NewItem exit;
	
	private ShaderProgram shader;
	
	
	private Vector3 LIGHT_POS;
	
	public GamePlay(GamePlayScreen gPS) {	
		this.gPS = gPS;
		this.background = new Background();
		this.sMG = new SimpleMapGeneration();
		this.camera = new OrthographicCamera();
		this.started = false;
		this.pEnum = PlayerMovementsEnum.IDLE;
		this.rand = new Random();	
		this.gameLogic = new GameElementLogic(gPS);
		
		this.time = 0;
		this.time_2 = 0;
		
		this.lights = -1;
		this.pulse = false;
		this.pulse_2 = false;
		
		this.exit = new NewItem();
		
		this.shader = ShaderEngine.generateShaderNormalMap();
		
		this.LIGHT_POS = new Vector3();
		LIGHT_POS.x = 0;
		LIGHT_POS.y = 0;
		LIGHT_POS.z = ShaderEngine.DEFAULT_LIGHT_Z;
		
	}
	
	public void start() {
		started = true;
	}
	
	public boolean isStart() {
		return this.started;
	}
	
	public GameElementLogic getGameLogic() {
		return gameLogic;
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
		
		//int index = rand.nextInt(8);
		int index = GameLogicInformation.WINTER_LEVEL;
		TileMapEnum[] data = GameLogicInformation.getRandomTileMap(index);
		this.gameLogic.initWorld();
		sMG.setWorld(this.gameLogic.getSpawnPool(),this.gameLogic.getWorld(), gPS);
		//this.lights = sMG.setLights();
		this.lights = NO_LIGHTS;
		//this.lights = LIGHTS;
		Gdx.app.log("[SINGLEMAPGENERATION]", "SET LIGHTS " + (this.lights == LIGHTS? "ON":"OFF"));
		
		tiledMap = sMG.createSimpleMap(index,
									   SecondTestGDX.sizeMapTileWidth_BG, 
									   SecondTestGDX.sizeMapTileHeight_BG,
									   SecondTestGDX.sizeMapTileWidth_TL, 
									   SecondTestGDX.sizeMapTileHeight_TL, 
									   data[INDEX_GAMELOGICINFORMATION_BACKGROUND],
									   data[INDEX_GAMELOGICINFORMATION_BORDER], 
									   data[INDEX_GAMELOGICINFORMATION_TILEMAP],
									   data[INDEX_GAMELOGICINFORMATION_FOREST],
									   GameLogicInformation.getRandomForestTileMap(index),
									   GameLogicInformation.PLAYERS,
									   GameLogicInformation.ENEMIESDRON,
									   GameLogicInformation.ENEMIESTANK,
									   GameLogicInformation.ENEMIESMINE);
		
		
		situationPlayer();
	}
	
	
	public void situationPlayer() {
		
		ArrayList<NewItem> posPlayers = sMG.getPlayers();
		
		if (posPlayers.size() == INDEX_SINGLE_PLAYER) {
			
			NewItem posPlayer = posPlayers.get(0);
			
			this.gameLogic.initPlayer(posPlayer.getType(),
									  posPlayer.getX(), 
									  posPlayer.getY(),
									  SecondTestGDX.tilePlayerWidth_TL, 
									  SecondTestGDX.tilePlayerHeight_TL);
			
			
			
			//public NewItem(SpawnType type, float x, float y, float width, float height, float angle, float speed) 
			
			NewItem playerFlag = new NewItem(SpawnType.Item, posPlayer.getX()-SecondTestGDX.tilePlayerWidth_TL, posPlayer.getY(),SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
			this.gameLogic.generateItem(SpawnType.Item_PlatformPlayer, playerFlag);
			
			
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
			    	initialboundaries(this.gameLogic.getPlayer().getX(), this.gameLogic.getPlayer().getY());
			    	
			    }else {
			    	this.camera.position.set(SecondTestGDX.screenWidth/2,SecondTestGDX.screenHeight/2, 0);
			    	this.camera.update();
			    }
			    
			    //-->EXIT
			    exit = sMG.setExit();
			    this.gameLogic.generateItem(SpawnType.Item_PlatformEndLevel, exit);
			    
			    //-->EXPLOSION
			    //NewItem explosion = new NewItem(SpawnType.Explosion,SecondTestGDX.screenWidth/2, SecondTestGDX.screenHeight/4 );
			    //this.gameLogic.generateExplosion(SpawnType.Simple_Explosion, explosion);
			    
			   
			    //-->ENEMY TANK
			    //ArrayList<NewItem>  enemyLst = sMG.getEnemiesTANK();
			    //for(NewItem sE: enemyLst) {this.gameLogic.generateEnemyTANK(sMG.getGraph(), sE, exit);}
			    
			    
			    //-->ENEMY MINE
			    //enemyLst = sMG.getEnemiesMINE();
			    //for(NewItem sE: enemyLst) {this.gameLogic.generateItem(SpawnType.Item_Mine, sE);}
			    
			    //-->ENEMY DRON
			    ArrayList<NewItem> enemyDRONLst = sMG.getEnemiesDRON();
			    for(NewItem sE: enemyDRONLst) {this.gameLogic.generateEnemyDRON(sE);}
			    
			    
			    
			    gameLogic.configureCollision(tiledMap, sMG.getWallsList(), sMG.getForestList());
			}
		}
	}
	
	public void playerMoveUp() {pEnum = PlayerMovementsEnum.UP; gameLogic.getPlayer().actionPlayerUP(pEnum);}
	public void playerMoveLeft() {pEnum = PlayerMovementsEnum.LEFT; gameLogic.getPlayer().actionPlayerLEFT(pEnum);}
	public void playerMoveRight() {pEnum = PlayerMovementsEnum.RIGHT; gameLogic.getPlayer().actionPlayerRIGHT(pEnum);}
	public void playerMoveDown() {pEnum = PlayerMovementsEnum.DOWN; gameLogic.getPlayer().actionPlayerDOWN(pEnum);}
	public void playerShoot() {pEnum = PlayerMovementsEnum.SHOOT; gameLogic.getPlayer().actionPlayerSHOOT(pEnum);}
	public void playerChange() {}
	public void playerTurretClockWise() {pEnum = PlayerMovementsEnum.TURRETCLOCKWISE; gameLogic.getPlayer().actionPlayerS(pEnum);}
	public void playerTurretAntiClockWise() {pEnum = PlayerMovementsEnum.TURRETANTICLOCKWISE; gameLogic.getPlayer().actionPlayerA(pEnum);}
	public void playerMouseMoved() {pEnum = PlayerMovementsEnum.MOUSEMOVED; gameLogic.getPlayer().actionPlayerMOUSEMOVE(pEnum);}
	
	
	public void update(float posX, float posY) {

		if (started) {
			
			 float targetX, targetY;
			   
			 if(posX - camera.viewportWidth / 2 < 0) targetX = camera.viewportWidth / 2;
			 else if(posX + camera.viewportWidth / 2 > SecondTestGDX.sizeMapTileWidth_TL * SecondTestGDX.tileWidth_TL) 
			      targetX = SecondTestGDX.sizeMapTileWidth_TL * SecondTestGDX.tileWidth_TL - camera.viewportWidth / 2;
			 else targetX = posX;
			   
			 if(posY - camera.viewportHeight / 2 < 8) targetY = camera.viewportHeight / 2 + 8;
			 else if(posY + camera.viewportHeight / 2 > SecondTestGDX.sizeMapTileHeight_TL * SecondTestGDX.tileHeight_TL + 8) 
			      targetY = SecondTestGDX.sizeMapTileHeight_TL * SecondTestGDX.tileHeight_TL - camera.viewportHeight / 2 + 8;
			 else targetY = posY;
			   
			 float dx = targetX - camera.position.x, dy = targetY - camera.position.y, dist = (float) Math.hypot(dx, dy);
			 Vector3 cameraVector = new Vector3((float) Math.cos(Math.atan2(dy, dx)), (float) Math.sin(Math.atan2(dy, dx)), 0);
			 cameraVector.scl(Math.max(dist / 25f, .2f));
			   
			 camera.position.add(cameraVector);
			 camera.update();
		}
	}
	
	public void updateElements(float delta) {
		if (started) {
			gameLogic.stepWorld(delta);
			gameLogic.updatePlayer(delta);
			gameLogic.updateSpawns(delta);
			gameLogic.removeSpawn();
		}
	}
	
	
	
	public void initialboundaries(float xInitialPlayer, float yInitialPlayer) {
		
		if (started) {
			
			float cameraX = xInitialPlayer;
			float cameraY = yInitialPlayer;
			
			float limitX = SecondTestGDX.sizeMapTileWidth_TL * SecondTestGDX.tileWidth_TL - SecondTestGDX.screenWidth/2;
			float limitY = SecondTestGDX.sizeMapTileHeight_TL * SecondTestGDX.tileHeight_TL - SecondTestGDX.screenHeight/2;
			
			if (xInitialPlayer < SecondTestGDX.screenWidth/2) {
				cameraX = SecondTestGDX.screenWidth/2;
			}else if (xInitialPlayer > limitX) {
				cameraX = limitX;
			}
			
			if (yInitialPlayer < SecondTestGDX.screenHeight/2){
				cameraY = SecondTestGDX.screenHeight/2;
			}else if (yInitialPlayer > limitY) {
				cameraY = limitY;
			}
		
			camera.position.set(cameraX, cameraY, 0);
			camera.update();
		}
		
	}
	
	public void drawBackground(SpriteBatch sb) {
		if ((GameLogicInformation.getLevel() == GameLogicInformation.START) || 
			(GameLogicInformation.getLevel() == GameLogicInformation.INTERMISSION)){
			background.draw(sb);
		}
	}
	
	
	public void drawMapCamera() {
		if (started) {
			if (tiledMap != null) {
				camera.update();
				tiledMapRenderer.setView(camera);
			}
		}
	}
	
	public void drawMapBef() {
		if (started) {
			if (tiledMap != null) {
				
				if ((sMG.getTypeMap() != GameLogicInformation.WINTER_LEVEL) && (sMG.getTypeMap() != GameLogicInformation.VOLCANO_LEVEL) && (sMG.getTypeMap() != GameLogicInformation.CITY_LEVEL) ) {
					
					int[] data  = {SimpleMapGeneration.INDEX_BACKGROUND, SimpleMapGeneration.INDEX_BORDER, SimpleMapGeneration.INDEX_WALLS};
					tiledMapRenderer.render(data);
				
				}else if ((sMG.getTypeMap() == GameLogicInformation.VOLCANO_LEVEL)) {
					
					int[] data  = {SimpleMapGeneration.INDEX_BACKGROUND, SimpleMapGeneration.INDEX_BORDER, SimpleMapGeneration.INDEX_FOREST};
					tiledMapRenderer.render(data);
				
				}else if ((sMG.getTypeMap() == GameLogicInformation.WINTER_LEVEL)){
					
					int[] data  = {SimpleMapGeneration.INDEX_BACKGROUND, SimpleMapGeneration.INDEX_BORDER, SimpleMapGeneration.INDEX_WALLS, SimpleMapGeneration.INDEX_FOREST};
					tiledMapRenderer.render(data);
					
				}else if (sMG.getTypeMap() == GameLogicInformation.CITY_LEVEL) {
					int[] data  = {SimpleMapGeneration.INDEX_BACKGROUND, SimpleMapGeneration.INDEX_BORDER};
					tiledMapRenderer.render(data);
				
				}
			}
		}
	}
	
	public void drawMapAf() {
		if (started) {
			if (tiledMap != null) {
				if ((sMG.getTypeMap() != GameLogicInformation.WINTER_LEVEL) && (sMG.getTypeMap() != GameLogicInformation.VOLCANO_LEVEL) && (sMG.getTypeMap() != GameLogicInformation.CITY_LEVEL) && (sMG.getTypeMap() != GameLogicInformation.SPACE_LEVEL)) {
					if (this.lights != LIGHTS) {
						int[] data = {SimpleMapGeneration.INDEX_FOREST};
						tiledMapRenderer.render(data);
					}
				}
			}
		}
	}
	
	

	
	public void drawWallCity(float delta) {
		
		if (started) {
			if (tiledMap != null) {
				if ((sMG.getTypeMap() == GameLogicInformation.CITY_LEVEL)) {
					
					
					
					tiledMapRenderer.getBatch().begin();
					tiledMapRenderer.getBatch().setShader(shader);
					
					//float x = this.getGameLogic().getPlayer().getX() / (float)SecondTestGDX.screenWidth;
					//float y = this.getGameLogic().getPlayer().getY() / (float)SecondTestGDX.screenHeight;
					
					float x = this.getGameLogic().getPlayer().getX() / camera.viewportWidth;
					float y = this.getGameLogic().getPlayer().getY() / camera.viewportHeight;
							
					LIGHT_POS.x = x;
					LIGHT_POS.y = y;
					LIGHT_POS.z = Math.max(0f, LIGHT_POS.z - (delta * 0.005f));
					
					//send a Vector4f to GLSL
					shader.setUniformf("LightPos", LIGHT_POS);
					
					tiledMapRenderer.renderTileLayer(sMG.getLightWallLayer());
					tiledMapRenderer.renderTileLayer(sMG.getCaveLayer());
					
					tiledMapRenderer.getBatch().end();
				}
			}
		}
			
	}
	
	
	public void drawForestCity() {
		if (started) {
			if (tiledMap != null) {
				if ((sMG.getTypeMap() == GameLogicInformation.CITY_LEVEL)) {
					
					int[] index_forest = {SimpleMapGeneration.INDEX_FOREST};
					tiledMapRenderer.render(index_forest);
				}
			}	
		}
	}
	
	
	public void drawForestSpace() {
		
		if (started) {
			if (tiledMap != null) {
				if ((sMG.getTypeMap() == GameLogicInformation.SPACE_LEVEL)) {
					
					int[] index_forest = {SimpleMapGeneration.INDEX_FOREST};
					tiledMapRenderer.render(index_forest);
					
					tiledMapRenderer.getBatch().begin();
					tiledMapRenderer.getBatch().setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);	
					tiledMapRenderer.renderTileLayer(sMG.getLightForestLayer());
					tiledMapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
					tiledMapRenderer.getBatch().end();
				}
			}	
		}
	}
	
	
	
	public void drawMapGloomingVolcano(float delta) {
		
		if (started) {
			if (tiledMap != null) {
				if ((sMG.getTypeMap() == GameLogicInformation.VOLCANO_LEVEL)) {
					
					
					this.time += delta;
					if (time >= TIME) {time = 0; pulse = !pulse;}
					
					this.time_2 += delta;
					if (time_2 >= TIME*0.5) {time_2 = 0; pulse_2 = !pulse_2;}
						
					
					if (pulse_2) {
						int[] index_walls = {SimpleMapGeneration.INDEX_WALLS};
						tiledMapRenderer.render(index_walls);
					}else {
						int[] index_walls_2 = {SimpleMapGeneration.INDEX_ALTERNATIVE};
						tiledMapRenderer.render(index_walls_2);
					}
					
					
					if (pulse) {
						tiledMapRenderer.getBatch().begin();
						tiledMapRenderer.getBatch().setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);	
						tiledMapRenderer.renderTileLayer(sMG.getLightWallLayer());
						tiledMapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
						tiledMapRenderer.getBatch().end();
					}
					
					
				}
			}
		}
	}
	
	
	public TiledMap getTiledMap() {
		return this.tiledMap;
	}
	
	public void drawElements(SpriteBatch sb) {
		if (started) {
			if (tiledMap != null) {
				gameLogic.drawPlayer(sb);
				gameLogic.drawSpawns(sb);
			}
		}
	}
	
	
	public void renderRayHandler() {
		if (started) {
			if (tiledMap != null) {
				if (this.lights == LIGHTS) {
					gameLogic.renderRayHandler();
				}
			}
		}
	}
	
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	
	public void dispose() {
		gameLogic.dispose();
		shader.dispose();
	}
	

}
