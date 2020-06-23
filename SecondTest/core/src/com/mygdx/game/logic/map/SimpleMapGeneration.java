package com.mygdx.game.logic.map;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.DynamicElementPositionEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.enums.TileMapLevelEnum;
import com.mygdx.game.logic.map.elements.StaticTiledMapColl;
import com.mygdx.game.logic.map.procedural.CaveGenerationImpl;
import com.mygdx.game.logic.map.procedural.ForestGenerationImpl;
import com.mygdx.game.utils.DrawUtils;
import com.mygdx.game.utils.NewItem;

public class SimpleMapGeneration {
	
   private long seed;
   private TiledMap map;
   private World world;
	
   private DrawUtils dU;
   private CaveGenerationImpl caveGenerator;
   private ForestGenerationImpl forestGenerator;
   
   private ArrayList<NewItem> playersSituation;
   private ArrayList<NewItem> enemiesSituation;
   
   private Random random;
   
    
   public static final int INDEX_BACKGROUND = 0;
   public static final int INDEX_BORDER = 1;
   public static final int INDEX_WALLS = 2;
   public static final int INDEX_FOREST = 3;
    
   public static final int INDEX_PLAYER = 4;
   public static final int INDEX_ENEMIES = 5;
   
   
   public static final int SINGLE_PLAYER = 1;
   
	
   public SimpleMapGeneration() {
	   
	   playersSituation = new ArrayList<NewItem>();
	   enemiesSituation = new ArrayList<NewItem>();
	   
	   random = new Random();
	   
   }
   
   public void setWorld(World world) {
	   this.world = world;
	   
	   
	   
   }
   
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
			cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.BORDER, tRegion, (float)x*tileBorder.getWidthShow() ,0.0f , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world));
			layer.setCell(x, 0, cell);
			
			
			cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			
			cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.BORDER, tRegion, (float)x*tileBorder.getWidthShow() ,  (float)(height-1)*tileBorder.getWidthShow()  , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world));
			layer.setCell(x, height-1, cell);
		}
		
		
		for(int y=1; y<height-1; y++) {
			
			Cell cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.BORDER, tRegion, 0.0f, (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world));
			layer.setCell(0, y, cell);
			
			
			cell = new Cell();
			//cell.setTile(new StaticTiledMapTile(tRegion));
			cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.BORDER, tRegion, (float)(width-1)*tileBorder.getWidthShow(), (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), world));
			layer.setCell(width-1, y, cell);
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
					cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.WALL, tRegion, (float)x*tileMap.getWidthShow(), (float)y*tileMap.getHeightShow(), (float)tileMap.getWidthShow(), (float)tileMap.getHeightShow(), world));
					layer.setCell(x, y, cell);
				}
				
			}
		}
		
		return layer;
	}
	
	
	public TiledMapTileLayer createForest(int width, int height, TileMapEnum tileMap) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileMap.getWidthShow(), tileMap.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && 
					(caveMap[x-1][y-1] == false)) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapColl(TileMapLevelEnum.FOREST, tRegion, (float)x*tileMap.getWidthShow(), (float)y*tileMap.getHeightShow(), (float)tileMap.getWidthShow(), (float)tileMap.getHeightShow(), world));
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
		
		Gdx.app.log("[SingleMapGeneration]","SINGLE PLAYER POSITION " + ppE.toString());
		
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
	
	
	public void setEnemiesPosition(int width, int height, int numEnemies) {
		
	}
	
	public ArrayList<NewItem> getPlayers(){return playersSituation;}
	public ArrayList<NewItem> getEnemies(){return enemiesSituation;}

	
	public TiledMap createSimpleMap(int width_bg, int height_bg, int width_tl, int height_tl, TileMapEnum tileBack, TileMapEnum tileBorder, TileMapEnum tileMap, TileMapEnum tileMapElem, int numPlayers, int numEnemies) {
		
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		generateCave(width_tl-2, height_tl-2);
		generateForest(width_tl-2, height_tl-2);
		
		
		layers.add(createBackground(width_bg, height_bg, tileBack));   //INDEX_0 => Background
		layers.add(createBorder(width_tl, height_tl, tileBorder));	   //INDEX_1 => Border
		layers.add(createCave(width_tl, height_tl, tileMap));		   //INDEX_2 => Walls
		layers.add(createForest(width_tl, height_tl, tileMapElem));    //INDEX_3 => Forests
		
		
		if (numPlayers > SINGLE_PLAYER) {
			setPlayersPosition(width_tl, height_tl, numPlayers);			//PLAYERS
		}else {
			setSinglePlayerPosition(SpawnType.getByIndex(0));
		}
		
		
		setEnemiesPosition(width_tl, height_tl, numEnemies);			//ENEMIES
		
		return map;
	}
	
	
	
}
