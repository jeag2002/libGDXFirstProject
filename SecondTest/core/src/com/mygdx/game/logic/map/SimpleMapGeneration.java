package com.mygdx.game.logic.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.mygdx.game.SecondTestGDX;

public class SimpleMapGeneration {
	
	private TiledMap map;
	
	
	
	//ONE LAYER, ONE TILE 
	public TiledMap createSimpleMapTest_1(int width, int height, int width_tile, int height_tile) {
	
		map = new TiledMap();
		
		
		MapLayers layers = map.getLayers();
		
		TiledMapTileLayer layer = new TiledMapTileLayer(width, height, width_tile, height_tile);
		
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				Cell cell = new Cell();
				Texture text = SecondTestGDX.resources.get(SecondTestGDX.resources.ground_tile_03_C);
				TextureRegion tRegion = new TextureRegion(text);
				cell.setTile(new StaticTiledMapTile(tRegion));
				layer.setCell(x, y, cell);
			}
		}
		
		layers.add(layer);
		return map;
	}
	

}
