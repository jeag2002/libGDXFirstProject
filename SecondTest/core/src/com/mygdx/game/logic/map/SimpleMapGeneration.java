package com.mygdx.game.logic.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.DynamicElementPositionEnum;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.ia.MapConnection;
import com.mygdx.game.ia.MapGraph;
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
   private MapGraph graph;
   
   
   private int typeMap;
   
   private DrawUtils dU;
   private CaveGenerationImpl caveGenerator;
   private CaveGenerationImpl caveGeneratorCity;
   private ForestGenerationImpl forestGenerator;
   
   private ArrayList<NewItem> playersSituation;
   
   private ArrayList<NewItem> enemiesDRONSituation;
   private ArrayList<NewItem> enemiesCENTROIDSituation;
   private ArrayList<NewItem> enemiesTANKSituation;
   private ArrayList<NewItem> tankIniNodeSituation;
   private ArrayList<NewItem> enemiesMINESituation;
   
   private NewItem exit;
   
   private ArrayList<StaticTiledMapColl> wallsLst;
   private ArrayList<StaticTiledMapColl> forestLst;
   
   private Random random;
   
   
   private TiledMapTileLayer backLayer;
   private TiledMapTileLayer borderLayer;
   private TiledMapTileLayer caveLayer;
   private TiledMapTileLayer forestLayer;
   
   
   
   private TiledMapTileLayer alternativeCaveLayer;
   private TiledMapTileLayer lightWallLayer;
   private TiledMapTileLayer lightForestLayer;
   
   private TiledMapTileLayer graphLayer;
   
    
   public static final int INDEX_BACKGROUND = 0;
   public static final int INDEX_BORDER = 1;
   public static final int INDEX_LIGHTMAPSWALLS = 2;
   public static final int INDEX_WALLS = 3;
   public static final int INDEX_FOREST = 4;
   public static final int INDEX_ALTERNATIVE = 5; 
   public static final int INDEX_LIGHTMAPSFOREST = 6;
   public static final int INDEX_GRAPHPATH = 7;
   
   
   public static final int INDEX_PLAYER = 5;
   public static final int SINGLE_PLAYER = 1;
   
   
   public static final int TYPE_DESERT = 0;
   public static final int TYPE_JUNGLE = 1;
   public static final int TYPE_FABRIC = 2;
   public static final int TYPE_WINTER = 3;
   public static final int TYPE_BADLANDS = 4;
   public static final int TYPE_VOLCANO = 5;
   public static final int TYPE_CITY = 6;
   public static final int TYPE_SPACE = 7;
   public static final int TYPE_ISLAND = 8;
   public static final int TYPE_WASTELAND = 9;
   
  
   public SimpleMapGeneration() {
	   
	   playersSituation = new ArrayList<NewItem>();
	   
	   enemiesDRONSituation = new ArrayList<NewItem>();
	   
	   enemiesTANKSituation = new ArrayList<NewItem>();
	   tankIniNodeSituation = new ArrayList<NewItem>();
	   
	   enemiesMINESituation = new ArrayList<NewItem>();
	   enemiesCENTROIDSituation = new ArrayList<NewItem>();
	   
	   
	   wallsLst = new ArrayList<StaticTiledMapColl>();
	   forestLst = new ArrayList<StaticTiledMapColl>();
	   
	   graph = new MapGraph();
	   
	   exit = null;
	   random = new Random();
	   
   }
  
   public TiledMapTileLayer getAlternativeCaveLayer() {
	   return this.alternativeCaveLayer;
   }

   
   public TiledMapTileLayer getBackgroundLayer() {
	   return this.backLayer;
   }
   
   public TiledMapTileLayer getBorderLayer() {
	   return this.borderLayer;
   }
   
   public TiledMapTileLayer getCaveLayer() {
	   return this.caveLayer;
   }
   
   public TiledMapTileLayer getForestLayer() {
	   return this.forestLayer;
   }
   
   
   public TiledMapTileLayer getLightWallLayer() {return this.lightWallLayer;}
   public TiledMapTileLayer getLightForestLayer() {return this.lightForestLayer;}
   
   
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
		
	  backLayer = new TiledMapTileLayer(width, height, tileBack.getWidthShow(), tileBack.getHeightShow());
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				Cell cell = new Cell();
				Texture text = SecondTestGDX.resources.get(tileBack.getTileMapStr());
				TextureRegion tRegion = new TextureRegion(text);
				cell.setTile(new StaticTiledMapTile(tRegion));
				backLayer.setCell(x, y, cell);
			}
		}
		return backLayer;
	}
	
	
	public TiledMapTileLayer createBorder(int width, int height, TileMapEnum tileBorder) {
		
		borderLayer = new TiledMapTileLayer(width, height, tileBorder.getWidthShow(), tileBorder.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileBorder.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileBorder.getWidthBef(), tileBorder.getHeightBef(), tileBorder.getWidthShow(), tileBorder.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		for(int x=0; x<width; x++) {
			
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)x*tileBorder.getWidthShow() ,0.0f , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), x, 0, world, true));
			borderLayer.setCell(x, 0, cell);
			
			
			cell = new Cell();
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)x*tileBorder.getWidthShow() ,  (float)(height-1)*tileBorder.getWidthShow()  , (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), x, height-1, world, true));
			borderLayer.setCell(x, height-1, cell);
		}
		
		
		for(int y=1; y<height-1; y++) {
			
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, 0.0f, (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), 0, y, world, true));
			borderLayer.setCell(0, y, cell);
			
			
			cell = new Cell();
			cell.setTile(new StaticTiledMapColl(SpawnType.Border, tRegion, (float)(width-1)*tileBorder.getWidthShow(), (float)y*tileBorder.getHeightShow(), (float)tileBorder.getWidthShow(), (float)tileBorder.getHeightShow(), width-1, y, world, true));
			borderLayer.setCell(width-1, y, cell);
		}
		
		return borderLayer;
	}
	
	
	
	public TiledMapTileLayer createLightWallMap(int width, int height, int width_tile, int heigh_tile) {
		
		this.lightWallLayer = new TiledMapTileLayer(width, height, width_tile, heigh_tile);
		
		Texture text = null;
		
		if (this.typeMap == TYPE_VOLCANO) {
			text = SecondTestGDX.resources.get(SecondTestGDX.resources.lightmap);
			text = DrawUtils.resizeTexture(text, 256, 256, 128, 128);
		} else if (this.typeMap == TYPE_CITY) {
			text = SecondTestGDX.resources.get(SecondTestGDX.resources.wall_city_normal);
			text = DrawUtils.resizeTexture(text, 128, 128, 128, 128);
		}else {
			text = SecondTestGDX.resources.get(SecondTestGDX.resources.lightmap);
			text = DrawUtils.resizeTexture(text, 256, 256, 128, 128);
		}
		
		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (caveMap[x-1][y-1]) {
					Cell cell = new Cell();
					StaticTiledMapTile ligthmap = new StaticTiledMapTile(tRegion);
					cell.setTile(ligthmap);
					this.lightWallLayer.setCell(x, y, cell);
				}
				
			}
		}
		
		return this.lightWallLayer;
	}
	
	
	
	public TiledMapTileLayer createAlternativeCave(int width, int height, int width_tile, int height_tile) {
		alternativeCaveLayer = new TiledMapTileLayer(width, height, width_tile, height_tile);
		Texture text = null;
		
		
		if (typeMap == this.TYPE_WASTELAND) {
			text = SecondTestGDX.resources.get(TileMapEnum.SLIME_2.getTileMapStr());
			text = DrawUtils.resizeTexture(text, TileMapEnum.SLIME_2.getWidthBef(), TileMapEnum.SLIME_2.getHeightBef(), TileMapEnum.SLIME_2.getWidthShow(), TileMapEnum.SLIME_2.getHeightShow());
		}else if (typeMap == this.TYPE_VOLCANO) {
			text = SecondTestGDX.resources.get(TileMapEnum.VOLCANO_FOREST_2.getTileMapStr());
			text = DrawUtils.resizeTexture(text, TileMapEnum.VOLCANO_FOREST_2.getWidthBef(), TileMapEnum.VOLCANO_FOREST_2.getHeightBef(), TileMapEnum.VOLCANO_FOREST_2.getWidthShow(), TileMapEnum.VOLCANO_FOREST_2.getHeightShow());
		}else {
			text = SecondTestGDX.resources.get(TileMapEnum.VOLCANO_FOREST_2.getTileMapStr());
			text = DrawUtils.resizeTexture(text, TileMapEnum.VOLCANO_FOREST_2.getWidthBef(), TileMapEnum.VOLCANO_FOREST_2.getHeightBef(), TileMapEnum.VOLCANO_FOREST_2.getWidthShow(), TileMapEnum.VOLCANO_FOREST_2.getHeightShow());
		}

		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (caveMap[x-1][y-1]) {
					Cell cell = new Cell();
					
					StaticTiledMapColl wall = new StaticTiledMapColl(
							SpawnType.getByIndex(typeMap+13), 
							tRegion, 
							(float)x*width_tile, 
							(float)y*height_tile, 
							(float)width_tile, 
							(float)height_tile, 
							x,y,
							world, 
							true); 
				
						
					wallsLst.add(wall);
					
					cell.setTile(wall);
					alternativeCaveLayer.setCell(x, y, cell);
				}
				
			}
		}
		
		return alternativeCaveLayer;
	}
	
	
	
	
	
	
	public TiledMapTileLayer createCave(int width, int height, TileMapEnum tileMap) {
		
		caveLayer = new TiledMapTileLayer(width, height, tileMap.getWidthShow(), tileMap.getHeightShow());
		
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());

		
		
		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = null; 
		caveMap = caveGenerator.getMap();
	
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
							x,y,
							world, 
							true); 
				
						
					wallsLst.add(wall);
					
					cell.setTile(wall);
					caveLayer.setCell(x, y, cell);
				}
				
			}
		}
		
		return caveLayer;
	}
	
	
	private TextureRegion processTextureRegion(int index) {
		
		TileMapEnum tileMap = TileMapEnum.getByIndex(index);
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = DrawUtils.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		return tRegion;
	}
	
	
	public TiledMapTileLayer createLightForestMap(int width, int height, int width_tile, int heigh_tile) {
		
		this.lightForestLayer = new TiledMapTileLayer(width, height, width_tile, heigh_tile);
		
		Texture text = null;
		text = SecondTestGDX.resources.get(SecondTestGDX.resources.lightmap);
		text = DrawUtils.resizeTexture(text, 256, 256, 128, 128);
		TextureRegion tRegion = new TextureRegion(text);
		
		byte[][] forestMap = forestGenerator.getForest();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) {
					Cell cell = new Cell();
					StaticTiledMapTile ligthmap = new StaticTiledMapTile(tRegion);
					cell.setTile(ligthmap);
					this.lightForestLayer.setCell(x, y, cell);
				}
				
			}
		}
		
		return this.lightForestLayer;
		
		
		
	}
	
	
	public TiledMapTileLayer createForest(int typeMap, int width, int height, TileMapEnum tileMapElem_1, int numRandomForest) {
	
		forestLayer = new TiledMapTileLayer(width, height, tileMapElem_1.getWidthShow(), tileMapElem_1.getHeightShow());
		
		int indexForest = 0;
		
		if (typeMap == TYPE_SPACE) {
			indexForest = numRandomForest + 3;
		}else {
			indexForest = numRandomForest;
		}
		
		TextureRegion[] bufferForest = new TextureRegion[indexForest];
		for(int i=0; i<indexForest; i++) {bufferForest[i] = processTextureRegion(tileMapElem_1.getIndex()+i);}	
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && 
					(caveMap[x-1][y-1] == false)) {
					Cell cell = new Cell();
					
					
					int index = random.nextInt(numRandomForest);
					
					if (typeMap == TYPE_SPACE) {
						
						//BLOCK;
						if (index == 0) {
							
							TextureRegion tRegion = bufferForest[index];
							
							StaticTiledMapColl forest = new StaticTiledMapColl(
									SpawnType.getByIndex(typeMap+23), 
									tRegion, 
									(float)x*tileMapElem_1.getWidthShow(), 
									(float)y*tileMapElem_1.getHeightShow(), 
									(float)tileMapElem_1.getWidthShow(), 
									(float)tileMapElem_1.getHeightShow(),
									x,y,
									world,
									true
									);
							
							forestLst.add(forest);
							cell.setTile(forest);
							
						//FAN	
						}else if (index == 1){
							
							Array<StaticTiledMapTile> tiles_anim = new Array<StaticTiledMapTile>();
							
							//tile-1
							TextureRegion tRegion = bufferForest[index];
							
							StaticTiledMapColl forest = new StaticTiledMapColl(
									SpawnType.getByIndex(typeMap+23), 
									tRegion, 
									(float)x*tileMapElem_1.getWidthShow(), 
									(float)y*tileMapElem_1.getHeightShow(), 
									(float)tileMapElem_1.getWidthShow(), 
									(float)tileMapElem_1.getHeightShow(),
									x,y,
									world,
									true
									);
							
							tiles_anim.add(forest);
							forestLst.add(forest);
							
							//tile-2
							tRegion = bufferForest[index+1];
							forest = new StaticTiledMapColl(
									SpawnType.getByIndex(typeMap+23), 
									tRegion, 
									(float)x*tileMapElem_1.getWidthShow(), 
									(float)y*tileMapElem_1.getHeightShow(), 
									(float)tileMapElem_1.getWidthShow(), 
									(float)tileMapElem_1.getHeightShow(),
									x,y,
									world,
									true
									);
							tiles_anim.add(forest);
							
							//tile-3
							tRegion = bufferForest[index+2];
							forest = new StaticTiledMapColl(
									SpawnType.getByIndex(typeMap+23), 
									tRegion, 
									(float)x*tileMapElem_1.getWidthShow(), 
									(float)y*tileMapElem_1.getHeightShow(), 
									(float)tileMapElem_1.getWidthShow(), 
									(float)tileMapElem_1.getHeightShow(),
									x,y,
									world,
									true
									);
							tiles_anim.add(forest);
							
							//tile-4
							tRegion = bufferForest[index+3];
							forest = new StaticTiledMapColl(
									SpawnType.getByIndex(typeMap+23), 
									tRegion, 
									(float)x*tileMapElem_1.getWidthShow(), 
									(float)y*tileMapElem_1.getHeightShow(), 
									(float)tileMapElem_1.getWidthShow(), 
									(float)tileMapElem_1.getHeightShow(),
									x,y,
									world,
									true
									);
							tiles_anim.add(forest);
							
							AnimatedTiledMapTile aTMT = new AnimatedTiledMapTile(0.02f, tiles_anim);
							cell.setTile(aTMT);
							
						}
						
					}else {
					
						TextureRegion tRegion = bufferForest[index];
						
						StaticTiledMapColl forest = new StaticTiledMapColl(
								SpawnType.getByIndex(typeMap+23), 
								tRegion, 
								(float)x*tileMapElem_1.getWidthShow(), 
								(float)y*tileMapElem_1.getHeightShow(), 
								(float)tileMapElem_1.getWidthShow(), 
								(float)tileMapElem_1.getHeightShow(),
								x,y,
								world,
								(typeMap == TYPE_WINTER) || (typeMap == TYPE_VOLCANO)  || (typeMap == TYPE_WASTELAND)
								);
						
						forestLst.add(forest);
						cell.setTile(forest);
					}
					forestLayer.setCell(x, y, cell);
				}
			}
		}
		return forestLayer;
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
		
		if ((caveGenerator != null) && (forestGenerator != null)) {
			boolean[][] caveMap = caveGenerator.getMap();
			byte[][] forestMap = forestGenerator.getForest();
			
			int situationPlayer = random.nextInt(4);
			DynamicElementPositionEnum ppE = DynamicElementPositionEnum.getByIndex(situationPlayer);
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
		}
		
		if (playersSituation.size() == 0) {
			NewItem player = new NewItem(playerId, 
					                     DynamicElementPositionEnum.IDLE, 
					                     128+64, 
					                     (SecondTestGDX.sizeMapTileHeight_TL/4) * SecondTestGDX.tileHeight_TL);
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
	
	
	//DRON SITUATION
	//////////////////////////////////////////////////////////////////////////////////////////////////
	private void setEnemiesDRONPositionSector(DynamicElementPositionEnum ppE, int numEnemies, boolean isSameSectorAsPlayer) {
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		if (!isSameSectorAsPlayer) {
		
			boolean DONE = false;
			for(int x=ppE.getXIni()+1; (x<ppE.getXFin()-1) && (!DONE); x++) {
				for(int y=ppE.getYIni()+1; (y<ppE.getYFin()-1) && (!DONE); y++) {
					//FREE SPACE
					if ((caveMap[x-1][y-1] == false) && (forestMap[x-1][y-1] == ForestGenerationImpl.EMPTY)) {	
						//ENEMIES
						if (!noMinimunDistanceBetweenEnemies(x,y,enemiesDRONSituation)) {
							//PLAYER
							if (!noSameSituationAsPlayer(x,y)) {
								NewItem sE = new NewItem(SpawnType.Enemy_01, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
								enemiesDRONSituation.add(sE);
								numEnemies--;							
								DONE = (numEnemies <= 0);	
							}
						}
					}
				}	
			}
			
		}
	}
	
	
	public void setEnemiesDRONPosition(NewItem player,int numEnemies) {
		setEnemiesDRONPositionSector(DynamicElementPositionEnum.LEFTDOWN, numEnemies/4 + numEnemies%4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH));
		setEnemiesDRONPositionSector(DynamicElementPositionEnum.LEFTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN));
	    setEnemiesDRONPositionSector(DynamicElementPositionEnum.RIGHTDOWN, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH));
		setEnemiesDRONPositionSector(DynamicElementPositionEnum.RIGHTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN));
		Gdx.app.log("[SINGLEMAPGENERATION]","NUM ENEMIES TYPE (" + SpawnType.Enemy_01 + ") GENERATED (" + enemiesDRONSituation.size() + ")");
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//WATCHTOWER SITUATION
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setEnemiesCENTROIDPositionSector(DynamicElementPositionEnum ppE, int numEnemies, boolean isSamePlayerSector) {
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		boolean DONE = false;
		
		if (!isSamePlayerSector) {
			for(int x=ppE.getXIni()+1; (x<ppE.getXFin()-1) && (!DONE); x++) {
				for(int y=ppE.getYIni()+1; (y<ppE.getYFin()-1) && (!DONE); y++) {
					//FREE SPACE
					if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && (caveMap[x-1][y-1] == false) ) {	
						//ENEMIES
						if (!noMinimunDistanceBetweenEnemies(x,y,enemiesMINESituation) && 
						   (!noMinimunDistanceBetweenEnemies(x,y,enemiesCENTROIDSituation))) {
							//PLAYER
							if (!noSameSituationAsPlayer(x,y)) {
								NewItem sE = new NewItem(SpawnType.Enemy_03, 
										x*SecondTestGDX.tileWidth_TL + SecondTestGDX.tilePlayerWidth_TL , 
										y*SecondTestGDX.tileHeight_TL + SecondTestGDX.tilePlayerHeight_TL, 
										SecondTestGDX.tilePlayerWidth_TL, 
										SecondTestGDX.tilePlayerHeight_TL, 0,0);
								enemiesCENTROIDSituation.add(sE);
								numEnemies--;							
								DONE = (numEnemies <= 0);	
							}
						}
					}
				}	
			}
		}
	}
	
	public void setEnemiesCENTROIDPosition(NewItem player, int numEnemies) {
		
		if ((typeMap != TYPE_WINTER) && (typeMap != TYPE_VOLCANO) && (typeMap != TYPE_SPACE) && (typeMap != TYPE_WASTELAND)) {
			setEnemiesCENTROIDPositionSector(DynamicElementPositionEnum.LEFTDOWN, numEnemies/4 + numEnemies%4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH));
			setEnemiesCENTROIDPositionSector(DynamicElementPositionEnum.LEFTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN));
			setEnemiesCENTROIDPositionSector(DynamicElementPositionEnum.RIGHTDOWN, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH));
			setEnemiesCENTROIDPositionSector(DynamicElementPositionEnum.RIGHTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN));	
			Gdx.app.log("[SINGLEMAPGENERATION]","NUM ENEMIES TYPE (" + SpawnType.Enemy_03 + ") GENERATED (" + enemiesCENTROIDSituation.size() + ")");
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//MINE SITUATION
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void setEnemiesMINEPositionSector(DynamicElementPositionEnum ppE, int numEnemies, boolean isSamePlayerSector) {
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		boolean DONE = false;
		
		if (!isSamePlayerSector) {
			for(int x=ppE.getXIni()+1; (x<ppE.getXFin()-1) && (!DONE); x++) {
				for(int y=ppE.getYIni()+1; (y<ppE.getYFin()-1) && (!DONE); y++) {
					//FREE SPACE
					if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && (caveMap[x-1][y-1] == false) ) {	
						//ENEMIES
						if (!noMinimunDistanceBetweenEnemies(x,y,enemiesMINESituation)) {
							//PLAYER
							if (!noSameSituationAsPlayer(x,y)) {
								NewItem sE = new NewItem(SpawnType.Item, 
										x*SecondTestGDX.tileWidth_TL + SecondTestGDX.tilePlayerWidth_TL , 
										y*SecondTestGDX.tileHeight_TL + SecondTestGDX.tilePlayerHeight_TL, 
										SecondTestGDX.tilePlayerWidth_TL, 
										SecondTestGDX.tilePlayerHeight_TL, 0,0);
								enemiesMINESituation.add(sE);
								numEnemies--;							
								DONE = (numEnemies <= 0);	
							}
						}
					}
				}	
			}
		}
	}
	
	
	public void setEnemiesMINEPosition(NewItem player, int numEnemies) {
		
		if ((typeMap != TYPE_WINTER) && (typeMap != TYPE_VOLCANO) && (typeMap != TYPE_SPACE) && (typeMap != TYPE_WASTELAND)) {
			setEnemiesMINEPositionSector(DynamicElementPositionEnum.LEFTDOWN, numEnemies/4 + numEnemies%4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH));
			setEnemiesMINEPositionSector(DynamicElementPositionEnum.LEFTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN));
			setEnemiesMINEPositionSector(DynamicElementPositionEnum.RIGHTDOWN, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH));
			setEnemiesMINEPositionSector(DynamicElementPositionEnum.RIGHTHIGH, numEnemies/4, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH) || player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN));	
			Gdx.app.log("[SINGLEMAPGENERATION]","NUM ENEMIES TYPE (" + SpawnType.Item_Mine + ") GENERATED (" + enemiesMINESituation.size() + ")");
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//TANKS
	//////////////////////////////////////////////////////////////////////////////////////////////////
	public void setEnemiesTANKPosition(DynamicElementPositionEnum ppE, int numEnemies, boolean sameSitAsPlayer) {
		
		if (!sameSitAsPlayer) {
			
			boolean[][] caveMap = caveGenerator.getMap();
			byte[][] forestMap = forestGenerator.getForest();
			
			
			boolean DONE = false;
			
			for(int x=ppE.getXIni()+1; (x<ppE.getXFin()-1) && (!DONE); x++) {
				for(int y=ppE.getYIni()+1; (y<ppE.getYFin()-1) && (!DONE); y++) {
					//FREE SPACE
					if ((caveMap[x-1][y-1] == false) && (forestMap[x-1][y-1] == ForestGenerationImpl.EMPTY)) {	
						//ENEMIES
						if (!noMinimunDistanceBetweenEnemies(x,y,enemiesTANKSituation)) {
							//PLAYER
							if (!noSameSituationAsPlayer(x,y)) {
								
								if (hasPlayerNoObstacleAround(x-1, y-1)) {
								
									if (numEnemies == 1) {
									
										NewItem sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_1, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);	
									
									}else if (numEnemies == 2) {
										
										NewItem sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_1, x*SecondTestGDX.tileWidth_TL, (y-1)*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);
										
										sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_2, x*SecondTestGDX.tileWidth_TL, (y+1)*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);
										
									}else {
										
										NewItem sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_1, x*SecondTestGDX.tileWidth_TL, (y-1)*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);

										sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_2, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);
										
										sE = new NewItem(SpawnType.Enemy_02, SpawnType.Tank_Level_3, x*SecondTestGDX.tileWidth_TL, (y+1)*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.tilePlayerHeight_TL, 0,0);
										enemiesTANKSituation.add(sE);

									}
									
									if (ppE.equals(DynamicElementPositionEnum.LEFTDOWN) || ppE.equals(DynamicElementPositionEnum.LEFTHIGH)) {
										
										NewItem node_tank = new NewItem(SpawnType.Item_PlatformEnemy, 
												x*SecondTestGDX.tileWidth_TL - SecondTestGDX.tilePlayerWidth_TL, 
												y*SecondTestGDX.tileHeight_TL, 
								                SecondTestGDX.tilePlayerWidth_TL, 
								                SecondTestGDX.tilePlayerHeight_TL, 0,0);
										
										this.tankIniNodeSituation.add(node_tank);	
										
									}else {
									
										NewItem node_tank = new NewItem(SpawnType.Item_PlatformEnemy, 
												x*SecondTestGDX.tileWidth_TL + SecondTestGDX.tilePlayerWidth_TL, 
												y*SecondTestGDX.tileHeight_TL, 
								                SecondTestGDX.tilePlayerWidth_TL, 
								                SecondTestGDX.tilePlayerHeight_TL, 0,0);
										
										this.tankIniNodeSituation.add(node_tank);
									}
									
									DONE = true;
								}
								
							}
						}
					}
				}	
			}		
		}
	}
	
	
	
	
	public void setEnemiesTANKPosition(NewItem player, int numTanks) {
		
		setEnemiesTANKPosition(DynamicElementPositionEnum.LEFTDOWN, numTanks/3 + numTanks%3, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTDOWN)); 
		setEnemiesTANKPosition(DynamicElementPositionEnum.LEFTHIGH, numTanks/3, player.getPlayerPosition().equals(DynamicElementPositionEnum.LEFTHIGH)); 
		setEnemiesTANKPosition(DynamicElementPositionEnum.RIGHTDOWN, numTanks/3, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTDOWN)); 
		setEnemiesTANKPosition(DynamicElementPositionEnum.RIGHTHIGH, numTanks/3, player.getPlayerPosition().equals(DynamicElementPositionEnum.RIGHTHIGH)); 
		
		Gdx.app.log("[SINGLEMAPGENERATION]","NUM ENEMIES TYPE (" + SpawnType.Enemy_02 + ") GENERATED (" + enemiesTANKSituation.size() + ")");
		Gdx.app.log("[SINGLEMAPGENERATION]","SPAWN ENEMIES NODES (" + SpawnType.Item_PlatformEnemy + ") GENERATED (" + tankIniNodeSituation.size() + ")");		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//GRAPH GENERATOR
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public boolean checkNode(NewItem fromItem, int i, int j, int width, int heigh) {
		boolean res = false;
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		List<MapConnection> connections = graph.getEdges();
		
		MapConnection conn = new MapConnection(fromItem, new NewItem(SpawnType.Path_Node, i*SecondTestGDX.tileWidth_TL, j*SecondTestGDX.tileHeight_TL, i, j, 0),1);
		
		if (i < 0 || i >= width) {
			return false;
		}else if (j < 0 || j >= heigh) {
			return false;
		}else if ((caveMap[i-1][j-1])) {
			return false;
		}else if ((forestMap[i-1][j-1] == ForestGenerationImpl.FOREST) && ((this.typeMap == TYPE_WINTER) || (this.typeMap == TYPE_VOLCANO) || (typeMap != TYPE_WASTELAND))){
			return false;
		}else if (connections.contains(conn)) {
			return false;
		}
	    else {
			return true;
		}	
	}
	
	public void generateGraph(int width, int height) {
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		
		int index = 0;
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if (caveMap[x-1][y-1] == false) {
					if ((forestMap[x-1][y-1] == ForestGenerationImpl.EMPTY) ||
					   ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && (this.typeMap != TYPE_WINTER) && (this.typeMap != TYPE_VOLCANO) && (typeMap != TYPE_WASTELAND) )) {
						graph.addNode(new NewItem(SpawnType.Path_Node, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, x, y, index));
						index++;
						
					}
				}
			}	
		}
		
		ArrayList<NewItem> nodes = graph.getNodes();
		
		
		for(int i=0; i<nodes.size(); i++) {
			NewItem fromItem = nodes.get(i);
			
			
			if (checkNode(fromItem, fromItem.getIndex_X(), fromItem.getIndex_Y()+1, width, height)) {
				
				NewItem toItem = new NewItem(SpawnType.Path_Node, 
						(fromItem.getIndex_X())*SecondTestGDX.tileWidth_TL, 
						(fromItem.getIndex_Y()+1)*SecondTestGDX.tileHeight_TL, 
						fromItem.getIndex_X(), 
						fromItem.getIndex_Y()+1, 0 );
				
				index = nodes.indexOf(toItem);
				if (index != -1) {
					graph.addEdge(fromItem, nodes.get(index),1);
					graph.addEdge(nodes.get(index),fromItem,1);
				}
			
			}
			
			
			if (checkNode(fromItem, fromItem.getIndex_X()-1, fromItem.getIndex_Y(), width, height)) {
				
				NewItem toItem = new NewItem(SpawnType.Path_Node, 
						(fromItem.getIndex_X()-1)*SecondTestGDX.tileWidth_TL, 
						(fromItem.getIndex_Y())*SecondTestGDX.tileHeight_TL, 
						fromItem.getIndex_X()-1, 
						fromItem.getIndex_Y(), 0 );
				
				index = nodes.indexOf(toItem);
				
				if (index != -1) {
					graph.addEdge(fromItem, nodes.get(index),1);
					graph.addEdge(nodes.get(index),fromItem,1);
				}
			}
			
			
			if (checkNode(fromItem, fromItem.getIndex_X()+1, fromItem.getIndex_Y(), width, height)) {
				
				NewItem toItem = new NewItem(SpawnType.Path_Node, 
						(fromItem.getIndex_X()+1)*SecondTestGDX.tileWidth_TL,
						(fromItem.getIndex_Y())*SecondTestGDX.tileHeight_TL,
						fromItem.getIndex_X()+1, 
						fromItem.getIndex_Y(), 0 );
				
				index = nodes.indexOf(toItem);
				
				if (index != -1) {
					graph.addEdge(fromItem, nodes.get(index),1);
					graph.addEdge(nodes.get(index),fromItem,1);
				}
			}
			
			
			if (checkNode(fromItem, fromItem.getIndex_X(), fromItem.getIndex_Y()-1, width, height)) {
				
				NewItem toItem = new NewItem(SpawnType.Path_Node, 
						(fromItem.getIndex_X())*SecondTestGDX.tileWidth_TL, 
						(fromItem.getIndex_Y()-1)*SecondTestGDX.tileHeight_TL, 
						fromItem.getIndex_X(), 
						fromItem.getIndex_Y()-1, 0 );
				index = nodes.indexOf(toItem);
				
				if (index != -1) {
					graph.addEdge(fromItem, nodes.get(index),1);
					graph.addEdge(nodes.get(index),fromItem,1);
				}
				
			}
			
		}
		
		Gdx.app.log("[SINGLEMAPGENERATION]", "AI A* MAP GENERATION DONE");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//EXIT 
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public NewItem setExitInMap(DynamicElementPositionEnum ppE) {
		
		NewItem location = null;
		
		boolean[][] caveMap = caveGenerator.getMap();
		byte[][] forestMap = forestGenerator.getForest();
		boolean DONE = false;
		
		for(int i=ppE.getXIni()+1; (i<ppE.getXFin()-1) && (!DONE); i++) {
			for (int j=ppE.getYIni()+1; (j<ppE.getYFin()-1) && (!DONE); j++) {
				if ((caveMap[i-1][j-1] == false) && (forestMap[i-1][j-1] == ForestGenerationImpl.EMPTY)) {	
					location = new NewItem(SpawnType.Item, i*SecondTestGDX.tileWidth_TL, j*SecondTestGDX.tileHeight_TL, SecondTestGDX.tilePlayerWidth_TL, SecondTestGDX.sizeMapTileHeight_TL, 0,0);
					DONE = true;
				}
			}
		}
		
		
		
		return location;
		
	}
	
	
	
	public NewItem setExit() {
		
		int data = random.nextInt(3);
		DynamicElementPositionEnum exitPos = DynamicElementPositionEnum.IDLE; 
		
		if (data == 0) {exitPos = DynamicElementPositionEnum.CENTER_LOW;}
		else if (data == 1) {exitPos = DynamicElementPositionEnum.CENTER;}
		else if (data == 2) {exitPos = DynamicElementPositionEnum.CENTER_HIGH;}
		
		this.exit = setExitInMap(exitPos);
		if(this.exit != null) {Gdx.app.log("[SINGLEMAPGENERATION]", "EXIT NODE CREATED!");}
		
		
		
		return this.exit;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	
	public ArrayList<NewItem> getPlayers(){return playersSituation;}
	public ArrayList<NewItem> getEnemiesDRON(){return enemiesDRONSituation;}
	public ArrayList<NewItem> getEnemiesTANK(){return enemiesTANKSituation;}
	public ArrayList<NewItem> getEnemiesMINE(){return enemiesMINESituation;}
	public ArrayList<NewItem> getEnemiesWATCHTOWER(){return this.enemiesCENTROIDSituation;}
	public ArrayList<NewItem> getTankIniNodes(){return this.tankIniNodeSituation;}
	
	
	public void restart() {
		this.playersSituation.clear();
		this.enemiesDRONSituation.clear();
		this.enemiesTANKSituation.clear();
		this.enemiesMINESituation.clear();
		this.enemiesCENTROIDSituation.clear();
		this.tankIniNodeSituation.clear();
		
		wallsLst = new ArrayList<StaticTiledMapColl>();
		forestLst = new ArrayList<StaticTiledMapColl>();   
		graph = new MapGraph();   
		exit = null;
		random = new Random();
		map = new TiledMap();
		
	}
	

	public int getTypeMap() {return typeMap;}
	public MapGraph getGraph() {return graph;}
	public void setGraph(MapGraph graph) {this.graph = graph;}
	
	
	//TEST GRAPH MAP
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public TiledMapTileLayer createGraphLayerMap(int width, int height, int width_tile, int heigh_tile) {
		
		this.graphLayer = new TiledMapTileLayer(width, height, width_tile, heigh_tile);
		
		ArrayList<NewItem> nodes = this.graph.getNodes();
		

		Texture text = null;
		text = SecondTestGDX.resources.get(SecondTestGDX.resources.testSquare);
		text = DrawUtils.resizeTexture(text, 128, 128, 128, 128);
		TextureRegion tRegion = new TextureRegion(text);
		
		
		for(NewItem node: nodes) {
		
			Cell cell = new Cell();
			StaticTiledMapTile ligthmap = new StaticTiledMapTile(tRegion);
			cell.setTile(ligthmap);
			this.graphLayer.setCell(node.getIndex_X(), node.getIndex_Y(), cell);
		}
		
	
	
		return graphLayer;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
		
	//MOST IMPORTANT FUNCTION. WORLD GENERATION
	////////////////////////////////////////////////////////////////////////////////////////////////
	public TiledMap createSimpleMap(int typeMap, 
			int width_bg, 
			int height_bg, 
			int width_tl, 
			int height_tl, 
			TileMapEnum tileBack, 
			TileMapEnum tileBorder, 
			TileMapEnum tileMap,
			TileMapEnum tileMapElem_1, 
			int numRandomForest, 
			int numPlayers, 
			int numEnemiesDRON, 
			int numEnemiesTANK, 
			int numEnemiesMINE,
			int numEnemiesWATCHTOWER) {
		
		map = new TiledMap();
		
		this.typeMap = typeMap;
		MapLayers layers = map.getLayers();
		generateCave(width_tl-2, height_tl-2);
	    generateForest(width_tl-2, height_tl-2);
		
		
		/*0-BACKGROUND*/layers.add(createBackground(width_bg, height_bg, tileBack));
		/*1-BORDER*/layers.add(createBorder(width_tl, height_tl, tileBorder));
		/*2-LIGHTWALL*/layers.add(createLightWallMap(width_tl, height_tl, 128, 128));	
		/*3-WALL*/layers.add(createCave(width_tl, height_tl, tileMap));
		/*4-FOREST*/layers.add(createForest(typeMap, width_tl, height_tl, tileMapElem_1, numRandomForest));    
		/*5-ALT_WALL*/layers.add(createAlternativeCave(width_tl, height_tl, 128, 128)); //alternative
		/*6-LIGHT_FOREST*/layers.add(createLightForestMap(width_tl, height_tl, 128, 128));
		
		
		
		if (numPlayers > SINGLE_PLAYER) {
			setPlayersPosition(width_tl, height_tl, numPlayers);			//PLAYERS
		}else {
			setSinglePlayerPosition(SpawnType.getByIndex(0));
		}
		
		//generateTestGraph(width_tl, height_tl);
		generateGraph(width_tl, height_tl);
		/*7-GRAPH*/layers.add(createGraphLayerMap(width_tl, height_tl, 128, 128));
		
	
		NewItem player = playersSituation.get(0);
		if (numEnemiesTANK >0 ) {setEnemiesTANKPosition(player,numEnemiesTANK);}
		if (numEnemiesMINE > 0) {setEnemiesMINEPosition(player, numEnemiesMINE);}
		if (numEnemiesWATCHTOWER > 0) {setEnemiesCENTROIDPosition(player, numEnemiesWATCHTOWER);}
		if (numEnemiesDRON > 0) {setEnemiesDRONPosition(player,numEnemiesDRON);}
		
		return map;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
}


//CHECK GRAPH GENERATION TEST
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
public boolean checkTestNode(NewItem fromItem, int i, int j, int width, int heigh) {
	boolean res = false;
	
	List<MapConnection> connections = graph.getEdges();
	
	MapConnection conn = new MapConnection(fromItem, new NewItem(SpawnType.Path_Node, i*SecondTestGDX.tileWidth_TL, j*SecondTestGDX.tileHeight_TL, i, j, 0),1);
	//MapConnection revConn = new MapConnection(new NewItem(SpawnType.Path_Node, i*SecondTestGDX.tileWidth_TL, j*SecondTestGDX.tileHeight_TL, i, j, 0), fromItem);
	
	if (i < 1 || i >= (width-1)) {
		return false;
	}else if (j < 1 || j >= (heigh-1)) {
		return false;
	}else if (connections.contains(conn)) {
		return false;
	}
	//else if (connections.contains(revConn)) {
	//	return false;
	//}
	else {
		return true;
	}	

}



public void generateTestGraph(int width, int height) {
	
	int index = 0;
	
	
	//NODES
	for(int x=1; x<width-1; x++) {
		for(int y=1; y<height-1; y++) {
			graph.addNode(new NewItem(SpawnType.Path_Node, x*SecondTestGDX.tileWidth_TL, y*SecondTestGDX.tileHeight_TL, x, y, index ));
			index++;
		}	
	}
	
	
	
	//EDGES
	ArrayList<NewItem> nodes = graph.getNodes();
	
	
	for(int i=0; i<nodes.size(); i++) {
		NewItem fromItem = nodes.get(i);
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()-1, fromItem.getIndex_Y()+1, width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X()-1)*SecondTestGDX.tileWidth_TL, 
					(fromItem.getIndex_Y()+1)*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X()-1,
					fromItem.getIndex_Y()+1, 0 );
			
		    index = nodes.indexOf(toItem);
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X(), fromItem.getIndex_Y()+1, width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X())*SecondTestGDX.tileWidth_TL, 
					(fromItem.getIndex_Y()+1)*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X(), 
					fromItem.getIndex_Y()+1, 0 );
			
			index = nodes.indexOf(toItem);
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()+1, fromItem.getIndex_Y()+1, width, height)) {
			
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X()+1)*SecondTestGDX.tileWidth_TL, 
					(fromItem.getIndex_Y()+1)*SecondTestGDX.tileHeight_TL,
					fromItem.getIndex_X()+1, 
					fromItem.getIndex_Y()+1, 0 );
			
			index = nodes.indexOf(toItem);
			graph.addEdge(fromItem,  nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()-1, fromItem.getIndex_Y(), width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X()-1)*SecondTestGDX.tileWidth_TL, 
					(fromItem.getIndex_Y())*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X()-1, 
					fromItem.getIndex_Y(), 0 );
			
			index = nodes.indexOf(toItem);
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()+1, fromItem.getIndex_Y(), width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X()+1)*SecondTestGDX.tileWidth_TL,
					(fromItem.getIndex_Y())*SecondTestGDX.tileHeight_TL,
					fromItem.getIndex_X()+1, 
					fromItem.getIndex_Y(), 0 );
			
			index = nodes.indexOf(toItem);
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()-1, fromItem.getIndex_Y()-1, width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node,
					(fromItem.getIndex_X()-1)*SecondTestGDX.tileWidth_TL,
					(fromItem.getIndex_Y()-1)*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X()-1, 
					fromItem.getIndex_Y()-1, 0 );
			index = nodes.indexOf(toItem);
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X(), fromItem.getIndex_Y()-1, width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X())*SecondTestGDX.tileWidth_TL, 
					(fromItem.getIndex_Y()-1)*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X(), 
					fromItem.getIndex_Y()-1, 0 );
			index = nodes.indexOf(toItem);
			
			
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
			
		}
		
		if (checkTestNode(fromItem, fromItem.getIndex_X()+1, fromItem.getIndex_Y()-1, width, height)) {
			
			NewItem toItem = new NewItem(SpawnType.Path_Node, 
					(fromItem.getIndex_X()+1)*SecondTestGDX.tileWidth_TL,
					(fromItem.getIndex_Y()-1)*SecondTestGDX.tileHeight_TL, 
					fromItem.getIndex_X()+1, 
					fromItem.getIndex_Y()-1, 0 );
			
			index = nodes.indexOf(toItem);
			
			graph.addEdge(fromItem, nodes.get(index),1);
			graph.addEdge(nodes.get(index),fromItem,1);
		}
	}
	
	
	
	Gdx.app.log("[SINGLEMAPGENERATION]"," NODES " +  graph.getNodes());
	Gdx.app.log("[SINGLEMAPGENERATION]"," EDGES " +  graph.getEdges());
	Gdx.app.log("[SINGLEMAPGENERATION]"," CONNECTIONS " + graph.getConnectionsMap());
	
	Gdx.app.log("[SINGLEMAPGENERATION]", "AI A* TEST MAP GENERATION DONE");
}
*/
///////////////////////////////////////////////////////////////////////////////////////////////
