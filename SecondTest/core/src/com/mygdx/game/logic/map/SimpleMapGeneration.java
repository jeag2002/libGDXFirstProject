package com.mygdx.game.logic.map;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile.BlendMode;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.simpleenemy.drons.SimpleEnemy;
import com.mygdx.game.enums.DynamicElementPositionEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.enums.TileMapLevelEnum;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.logic.map.elements.StaticTiledMapColl;
import com.mygdx.game.logic.map.procedural.CaveGenerationImpl;
import com.mygdx.game.logic.map.procedural.ForestGenerationImpl;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.DrawUtils;
import com.mygdx.game.utils.NewItem;

public class SimpleMapGeneration {
	
   private long seed;
   private TiledMap map;
   
   private World world;
   private GamePlayScreen gPS;
   private SpawnPool pool;
   
	
   private int typeMap;
   
   private DrawUtils dU;
   private CaveGenerationImpl caveGenerator;
   private ForestGenerationImpl forestGenerator;
   
   private ArrayList<NewItem> playersSituation;
   private ArrayList<NewItem> enemiesSituation;
   //private ArrayList<SimpleEnemy> enemiesSituation;
   
   private ArrayList<StaticTiledMapColl> wallsLst;
   private ArrayList<StaticTiledMapColl> forestLst;
   
   private Random random;
   
    
   public static final int INDEX_BACKGROUND = 0;
   public static final int INDEX_BORDER = 1;
   public static final int INDEX_LIGHTMAPS = 2;
   public static final int INDEX_WALLS = 3;
   public static final int INDEX_FOREST = 4;
   public static final int INDEX_ENEMIES = 5;
    
   public static final int INDEX_PLAYER = 5;
   public static final int SINGLE_PLAYER = 1;
   
   
   public static final int TYPE_DESERT = 0;
   public static final int TYPE_JUNGLE = 1;
   public static final int TYPE_FABRIC = 2;
   public static final int TYPE_WINTER = 3;
   public static final int TYPE_BADLANDS = 4;
   public static final int TYPE_VOLCANO = 5;
   
  
   public SimpleMapGeneration() {
	   
	   playersSituation = new ArrayList<NewItem>();
	   enemiesSituation = new ArrayList<NewItem>();
	   
	   wallsLst = new ArrayList<StaticTiledMapColl>();
	   forestLst = new ArrayList<StaticTiledMapColl>();
	   
	   random = new Random();
	   
   }
   
   public void setWorld(SpawnPool pool, World world, GamePlayScreen gPS) {
	   this.pool = pool;
	   this.world = world;
	   this.gPS = gPS;
   }
   
   public ArrayList<StaticTiledMapColl> getWallsList(){return wallsLst;}
   public ArrayList<StaticTiledMapColl> getForestList(){return forestLst;}
   
   private void generateCave(int width, int height) {
	   seed = System.currentTimeMillis();
	   caveGenerator = CaveGenerationImpl
			   .Builder
			   .create()
			   //.withSize(width,height)
			   .withSize(height,width)
			   .withRandomSeed(seed)
			   .addPhase(5, 2, 4)
			   .addPhase(5,-1, 5)
			   .build();
	   caveGenerator.generate();
   }
   
   public int setLights() {
	   int lights = random.nextInt(2);
	   return lights;
   }
   
   
   private void generateForest(int width, int height) {
	   
	   seed = System.currentTimeMillis();
	   
	   forestGenerator = ForestGenerationImpl
			   .Builder
			   .create()
			   //.withSize(width,height)
			   .withSize(height,width)
			   .withRandomSeed(seed)
               .withInitialTrees(20)
               .withSeedParams(7, 0.1, 0.05)
			   .build();
	   forestGenerator.generate();
   }
   

	
  public TiledMapTileLayer createBackground(int width, int height, TileMapEnum tileBack) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileBack.getWidthShow(), tileBack.getHeightShow());
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				Cell cell = new Cell();
				Texture text = SecondTestGDX.resources.get(tileBack.getTileMapStr());
				TextureRegion tRegion = new TextureRegion(text);
				cell.setTile(new StaticTiledMapTile(tRegion));
				layer.setCell(x, y, cell);
			}
		}
		return layer;
	}
	
	
	public TiledMapTileLayer createBorder(int width, int height, TileMapEnum tileBorder) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileBorder.getWidthShow(), tileBorder.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileBorder.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileBorder.getWidthBef(), tileBorder.getHeightBef(), tileBorder.getWidthShow(), tileBorder.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		for(int x=0; x<width; x++) {
			
			Cell cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)x*tileBorder.getWidthShow() ,0.0f , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world, true));
			layer.setCell(x, 0, cell);
			
			
			cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)x*tileBorder.getWidthShow() ,  (float)(height-1)*tileBorder.getWidthShow()  , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world, true));
			layer.setCell(x, height-1, cell);
		}
		
		
		for(int y=1; y<height-1; y++) {
			
			Cell cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, 0.0f, (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world, true));
			layer.setCell(0, y, cell);
			
			
			cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)(width-1)*tileBorder.getWidthShow(), (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world, true));
			layer.setCell(width-1, y, cell);
		}
		
		return layer;
	}
	
	
	
	public TiledMapTileLayer createLightMap(int width, int height, int width_tile, int heigh_tile) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, width_tile, heigh_tile);
		
		Texture text = SecondTestGDX.resources.get(SecondTestGDX.resources.lightmap);
		text = DrawUtils.resizeTexture(text, 256, 256, 128, 128);
		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (caveMap[x-1][y-1]) {
					Cell cell = new Cell();
					StaticTiledMapTile ligthmap = new StaticTiledMapTile(tRegion);
					cell.setTile(ligthmap);
					layer.setCell(x, y, cell);
				}
				
			}
		}
		
		return layer;
	}
	
	
	
	
	public TiledMapTileLayer createCave(int width, int height, TileMapEnum tileMap) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileMap.getWidthShow(), tileMap.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (caveMap[x-1][y-1]) {
					Cell cell = new Cell();
					
					StaticTiledMapColl wall = new StaticTiledMapColl(
							SpawnType.getByIndex(typeMap+13), 
							tRegion, 
							(float)x*tileMap.getWidthShow(), 
							(float)y*tileMap.getHeightShow(), 
							(float)tileMap.getWidthShow(), 
							(float)tileMap.getHeightShow(), 
							world, 
							true); 
				
						
					wallsLst.add(wall);
					
					cell.setTile(wall);
					layer.setCell(x, y, cell);
				}
				
			}
		}
		
		return layer;
	}
	
	
	private TextureRegion processTextureRegion(int index) {
		
		TileMapEnum tileMap = TileMapEnum.getByIndex(index);
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		return tRegion;
	}
	
	
	
	public TiledMapTileLayer createForest(int typeMap, int width, int height, TileMapEnum tileMapElem_1, int numRandomForest) {
	
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileMapElem_1.getWidthShow(), tileMapElem_1.getHeightShow());
		
		TextureRegion[] bufferForest = new TextureRegion[numRandomForest];
		
		for(int i=0; i<numRandomForest; i++) {bufferForest[i] = processTextureRegion(tileMapElem_1.getIndex()+i);}	
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && 
					(caveMap[x-1][y-1] == false)) {
					Cell cell = new Cell();
					
					
					int index = random.nextInt(numRandomForest);				
					TextureRegion tRegion = bufferForest[index];
					
					StaticTiledMapColl forest = new StaticTiledMapColl(
							SpawnType.getByIndex(typeMap+19), 
							tRegion, 
							(float)x*tileMapElem_1.getWidthShow(), 
							(float)y*tileMapElem_1.getHeightShow(), 
							(float)tileMapElem_1.getWidthShow(), 
							(float)tileMapElem_1.getHeightShow(),
							world,
							(typeMap == TYPE_WINTER) || (typeMap == TYPE_VOLCANO)
							);
					
					forestLst.add(forest);
					
					cell.setTile(forest);
					layer.setCell(x, y, cell);
				}
			}
		}
		return layer;
	}
	
	
	public void setPlayerPosition(int width, int height, SpawnType playerId) {
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		boolean DONE = false;
	    int x = 1;
	    int y = 1;
	    
	    
		do {
			// EMPTY SPACE FOR A PLAYER 
			if ((caveMap[x-1][y-1] == false) && (forestMap[x-1][y-1] == ForestGenerationImpl.EMPTY)) {
				
				NewItem player = new NewItem(playerId, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL);
				playersSituation.add(player);
				DONE = true;
				
			}else {
				x++;
				if (x >= width-1) {
					x = 1;		
					if (y < height-1) 
					{y++;}
					else 
					{DONE = true;}
				}
			}
		}while(!DONE);
		
	}
	
	
	public boolean hasPlayerNoObstacleAround(int i, int j) {
		boolean noObstacle = false;
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		noObstacle = (caveMap[i-1][j] == false && forestMap[i-1][j] == ForestGenerationImpl.EMPTY) &&
				     (caveMap[i-1][j-1] == false && forestMap[i-1][j-1] == ForestGenerationImpl.EMPTY) && 
				     (caveMap[i-1][j+1] == false && forestMap[i-1][j+1] == ForestGenerationImpl.EMPTY) &&
				     
				     (caveMap[i][j-1] == false && forestMap[i][j-1] == ForestGenerationImpl.EMPTY) &&
				     (caveMap[i][j+1] == false && forestMap[i][j+1] == ForestGenerationImpl.EMPTY) &&
				     
				     (caveMap[i+1][j] == false && forestMap[i+1][j] == ForestGenerationImpl.EMPTY) &&
				     (caveMap[i+1][j-1] == false && forestMap[i+1][j-1] == ForestGenerationImpl.EMPTY) && 
				     (caveMap[i+1][j+1] == false && forestMap[i+1][j+1] == ForestGenerationImpl.EMPTY);
				     
		return noObstacle;
		
	}
	
	
	
	public void setSinglePlayerPosition(SpawnType playerId) {
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		int situationPlayer = random.nextInt(4);
		DynamicElementPositionEnum ppE = DynamicElementPositionEnum.getByIndex(situationPlayer);
		
		//DynamicElementPositionEnum ppE = DynamicElementPositionEnum.LEFTDOWN;
		
		Gdx.app.log("[SINGLEMAPGENERATION]","SINGLE PLAYER POSITION " + ppE.toString());
		
		boolean DONE = false;
		
		for(int i=ppE.getXIni()+1; (i<ppE.getXFin()-1) && (!DONE); i++) {
			for (int j=ppE.getYIni()+1; (j<ppE.getYFin()-1) && (!DONE); j++) {
				if ((caveMap[i-1][j-1] == false) && (forestMap[i-1][j-1] == ForestGenerationImpl.EMPTY)) {	
					if (hasPlayerNoObstacleAround(i-1, j-1)) {
						NewItem player = new NewItem(playerId, ppE, i*SecondTestGDX.tileWidth_TL, j*SecondTestGDX.tileHeight_TL);
						playersSituation.add(player);
						DONE = true;
					}
				}
			}
		}
		
		if (playersSituation.size() == 0) {
			NewItem player = new NewItem(playerId, 
					                     DynamicElementPositionEnum.IDLE, 
					                     (SecondTestGDX.sizeMapTileWidth_TL/2) * SecondTestGDX.tileWidth_TL, 
					                     (SecondTestGDX.sizeMapTileHeight_TL/2) * SecondTestGDX.tileHeight_TL);
			playersSituation.add(player);
		}
		
	}
	
	
	public void setPlayersPosition(int width, int height, int numPlayers) {
		for (int i=0; i<numPlayers; i++) {setPlayerPosition(width,height, SpawnType.getByIndex(i));}
	}
	
	
	public boolean noSameSituationAsPlayer(int i, int j) {
		boolean sameSituation = false;
		
		for(int p=0; p<playersSituation.size() && (!sameSituation); p++){
			NewItem player = playersSituation.get(p);
			double distance = Math.sqrt( Math.pow( (player.getX() - SecondTestGDX.tileWidth_TL*i) , 2) +  Math.pow( (player.getY() - SecondTestGDX.tileHeight_TL * j) ,2));
			sameSituation = (distance <= GameLogicInformation.MIN_DISTANCE_BETWEEN_ENEMIES);
		
		}
		
		return sameSituation;
	}
	
	
	public boolean noMinimunDistanceBetweenEnemies(int i, int j, ArrayList<NewItem> auxEnemies) {
		boolean noMinimunDistance = false;
		
		for(int p=0; p<auxEnemies.size() && (!noMinimunDistance); p++) {
			NewItem enemy = auxEnemies.get(p);
			double distance = Math.sqrt( Math.pow( (enemy.getX() - SecondTestGDX.tileWidth_TL*i) , 2) +  Math.pow( (enemy.getY() - SecondTestGDX.tileHeight_TL * j) ,2));
			noMinimunDistance = (distance <= GameLogicInformation.MIN_DISTANCE_BETWEEN_ENEMIES);
		}
		
		
		return noMinimunDistance; 
		
	}
	
	
	
	private void setEnemiesPositionSector(int width, int height, DynamicElementPositionEnum ppE, int numEnemies) {
		
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, SecondTestGDX.tileWidth_TL, SecondTestGDX.tileHeight_TL);
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		
		boolean DONE = false;
		
		for(int x=ppE.getXIni()+1; (x<ppE.getXFin()-1) && (!DONE); x++) {
			for(int y=ppE.getYIni()+1; (y<ppE.getYFin()-1) && (!DONE); y++) {
				//FREE SPACE
				if ((caveMap[x-1][y-1] == false) && (forestMap[x-1][y-1] == ForestGenerationImpl.EMPTY)) {	
					//ENEMIES
					if (!noMinimunDistanceBetweenEnemies(x,y,enemiesSituation)) {
						//PLAYER
						if (!noSameSituationAsPlayer(x,y)) {
							NewItem sE = new NewItem(SpawnType.Enemy_01, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
							enemiesSituation.add(sE);
							numEnemies--;							
							DONE = (numEnemies <= 0);	
						}
					}
				}
			}	
		}
		
		Gdx.app.log("[SINGLEMAPGENERATION]","NUM ENEMIES "  + ppE +  " GENERATED (" + enemiesSituation.size() + ")");
	}
	
	
	public void setEnemiesPosition(int width, int height, int numEnemies) {
		setEnemiesPositionSector(width, height, DynamicElementPositionEnum.LEFTDOWN, numEnemies/4 + numEnemies%4);
		setEnemiesPositionSector(width, height, DynamicElementPositionEnum.LEFTHIGH, numEnemies/4);
	    setEnemiesPositionSector(width, height, DynamicElementPositionEnum.RIGHTDOWN, numEnemies/4);
		setEnemiesPositionSector(width, height, DynamicElementPositionEnum.RIGHTHIGH, numEnemies/4);
	}
	
	
	
	public ArrayList<NewItem> getPlayers(){return playersSituation;}
	public ArrayList<NewItem> getEnemies(){return enemiesSituation;}
	
	
	public int getTypeMap() {return typeMap;}

	public TiledMap createSimpleMap(int typeMap, int width_bg, int height_bg, int width_tl, int height_tl, TileMapEnum tileBack, TileMapEnum tileBorder, TileMapEnum tileMap, TileMapEnum tileMapElem_1, int numRandomForest, int numPlayers, int numEnemies) {
		
		map = new TiledMap();
		
		this.typeMap = typeMap;
		MapLayers layers = map.getLayers();
		generateCave(width_tl-2, height_tl-2);
		generateForest(width_tl-2, height_tl-2);
		
		
		layers.add(createBackground(width_bg, height_bg, tileBack));   //INDEX_0 => Background
		layers.add(createBorder(width_tl, height_tl, tileBorder));	   //INDEX_1 => Border
		layers.add(createLightMap(width_tl, height_tl, 128, 128));	   //INDEX_2 => LightMap	
		layers.add(createCave(width_tl, height_tl, tileMap));		   //INDEX_3 => Walls
		layers.add(createForest(typeMap, width_tl, height_tl, tileMapElem_1, numRandomForest));    //INDEX_4 => Forests
		
		
		if (numPlayers > SINGLE_PLAYER) {
			setPlayersPosition(width_tl, height_tl, numPlayers);			//PLAYERS
		}else {
			setSinglePlayerPosition(SpawnType.getByIndex(0));
		}
		
		setEnemiesPosition(width_tl, height_tl, numEnemies);
		
		return map;
	}
	
	
	
}
