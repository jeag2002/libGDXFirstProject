package com.mygdx.game.logic.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.TileMapEnum;
import com.mygdx.game.logic.map.procedural.CaveGenerationImpl;
import com.mygdx.game.logic.map.procedural.ForestGenerationImpl;
import com.mygdx.game.utils.DrawUtils;

public class SimpleMapGeneration {
	
	private long seed;
	private TiledMap map;
    private DrawUtils dU;
    private CaveGenerationImpl caveGenerator;
    private ForestGenerationImpl forestGenerator;
   
	
   public SimpleMapGeneration() {
	   dU = new DrawUtils();
   }
   
   
   private void generateCave(int width, int height) {
	   seed = System.currentTimeMillis();
	   caveGenerator = CaveGenerationImpl
			   .Builder
			   .create()
			   .withSize(width,height)
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
			   .withSize(width,height)
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
		text = dU.resizeTexture(text, tileBorder.getWidthBef(), tileBorder.getHeightBef(), tileBorder.getWidthShow(), tileBorder.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		for(int x=0; x<width; x++) {
			
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapTile(tRegion));
			layer.setCell(x, 0, cell);
			
			
			cell = new Cell();
			cell.setTile(new StaticTiledMapTile(tRegion));
			layer.setCell(x, height-1, cell);
		}
		
		
		for(int y=1; y<height-1; y++) {
			
			Cell cell = new Cell();
			cell.setTile(new StaticTiledMapTile(tRegion));
			layer.setCell(0, y, cell);
			
			
			cell = new Cell();
			cell.setTile(new StaticTiledMapTile(tRegion));
			layer.setCell(width-1, y, cell);
		}
		
		return layer;
	}
	
	
	public TiledMapTileLayer createCave(int width, int height, TileMapEnum tileMap) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileMap.getWidthShow(), tileMap.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = dU.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				
				if (caveMap[x-1][y-1]) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(tRegion));
					layer.setCell(x, y, cell);
				}
				
			}
		}
		
		
		return layer;
	}
	
	
	public TiledMapTileLayer createForest(int width, int height, TileMapEnum tileMap) {
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, tileMap.getWidthShow(), tileMap.getHeightShow());
		Texture text = SecondTestGDX.resources.get(tileMap.getTileMapStr());
		text = dU.resizeTexture(text, tileMap.getWidthBef(), tileMap.getHeightBef(), tileMap.getWidthShow(), tileMap.getHeightShow());
		TextureRegion tRegion = new TextureRegion(text);
		
		byte[][] forestMap = forestGenerator.getForest();
		boolean[][] caveMap = caveGenerator.getMap();
		
		for(int x=1; x<width-1; x++) {
			for(int y=1; y<height-1; y++) {
				if ((forestMap[x-1][y-1] == ForestGenerationImpl.FOREST) && 
					(caveMap[x-1][y-1] == false)) {
					Cell cell = new Cell();
					cell.setTile(new StaticTiledMapTile(tRegion));
					layer.setCell(x, y, cell);
				}
			}
		}
		
		
		return layer;
	}

	
	
	
	public TiledMap createSimpleMap(int width_bg, int height_bg, int width_tl, int height_tl, TileMapEnum tileBack, TileMapEnum tileBorder, TileMapEnum tileMap, TileMapEnum tileMapElem) {
		
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		generateCave(width_tl-2, height_tl-2);
		generateForest(width_tl-2, height_tl-2);
		
		layers.add(createBackground(width_bg, height_bg, tileBack));
		layers.add(createBorder(width_tl, height_tl, tileBorder));
		layers.add(createCave(width_tl, height_tl, tileMap));
		layers.add(createForest(width_tl, height_tl, tileMapElem));
		
		return map;
	}
	
	
	
}
