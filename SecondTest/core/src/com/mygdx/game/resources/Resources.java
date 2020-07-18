package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Resources extends AssetManager{

	private static final String TAG = "[RESOURCES]";
	
	public String imgSplash = "splash/splash-2.png";
	public String imgSplash_1 = "splash/intermission_2.jpg";
	public String imgIntermission = "splash/intermission_4.jpg";
	
	private String fnt = "fonts/Bangers_bitmap.fnt";
	public BitmapFont font1;
	
	public String musicSplash = "music/start.mp3";
	public String musicIntermission = "music/intermission.mp3";
	public String musicGameplay_1 = "music/gameplay_1.mp3";
	public String musicGameplay_2 = "music/gameplay_2.mp3";
	public String musicGameplay_3 = "music/gameplay_3.mp3";
	public String musicGameplay_4 = "music/gameplay_4.mp3";
	public String musicGameplay_5 = "music/gameplay_5.mp3";
	public String musicGameplay_6 = "music/gameplay_6.mp3";
	
	public String startButton = "gui/start/Start_BTN.png";
	public String exitButton = "gui/start/Exit_1_BTN.png";
	public String gameButton = "gui/start/GameMode_BTN.png";
	public String settingsButton = "gui/start/Settings_BTN.png";
	public String scoreButton = "gui/start/Score_BTN.png";
	
	public String headerSplash = "gui/start/Header.png";
	public String tableSplash = "gui/start/Table.png";
	
	//blocks
	public String block_A_01 = "maps/terrain/Block_A_01.png";
	public String block_A_02 = "maps/terrain/Block_A_02.png";
	public String block_B_01 = "maps/terrain/Block_B_01.png";
	public String block_B_02 = "maps/terrain/Block_B_02.png";
	public String block_C_01 = "maps/terrain/Block_C_01.png";
	public String block_C_02 = "maps/terrain/Block_C_02.png";
	public String block_D_01 = "maps/terrain/Block_D_01.png";
	public String block_D_02 = "maps/terrain/Block_D_02.png";
	
	public String block_E_01 = "maps/terrain/block_ice_2.png";
	public String block_E_02 = "maps/terrain/Ice_01.png";
	
	
	//ground
	public String ground_tile_01_A = "maps/terrain/Ground_Tile_01_A.png"; 
	public String ground_tile_01_B = "maps/terrain/Ground_Tile_01_B.png";
	public String ground_tile_01_C = "maps/terrain/Ground_Tile_01_C.png";
	public String ground_tile_01_D = "maps/terrain/Ground_Tile_01_D.png";
	
	public String ground_tile_02_A = "maps/terrain/Ground_Tile_02_A.png";
	public String ground_tile_02_B = "maps/terrain/Ground_Tile_02_B.png";
	public String ground_tile_02_C = "maps/terrain/Ground_Tile_02_C.png";
	public String ground_tile_02_D = "maps/terrain/Ground_Tile_02_D.png";
	
	//ice
	public String ground_tile_01_E = "maps/terrain/Ground_Tile_01_E.png";
	public String ground_tile_02_E = "maps/terrain/Ground_Tile_02_E.png";
	
	//badlands
	public String ground_tile_01_F = "maps/terrain/Ground_Tile_01_F.png";
	public String ground_tile_02_F = "maps/terrain/Ground_Tile_02_F.png";
	
	
	//elements_terrains
	public String Palm_01 = "maps/terrain/Palm_01.png";
	public String Palm_02 = "maps/terrain/Palm_02.png";
	public String Palm_03 = "maps/terrain/Palm_03.png";
	
	public String Cactus_01 = "maps/terrain/Cactus_01.png";
	public String Cactus_02 = "maps/terrain/Cactus_02.png";
	public String Cactus_03 = "maps/terrain/Cactus_03.png";
	
	public String Czech_01 = "maps/terrain/Czech_01.png";
	public String Czech_02 = "maps/terrain/Czech_02.png";
	
	public String Solar_01 = "maps/terrain/solar_panel_1.png";
	public String Solar_02 = "maps/terrain/solar_panel_2.png";
	
	
	public String Tree_01 = "maps/terrain/Tree_01.png";
	public String Tree_02 = "maps/terrain/Tree_02.png";
	public String Tree_03 = "maps/terrain/Tree_03.png";
	public String Tree_04 = "maps/terrain/Tree_04.png";
	public String Tree_05 = "maps/terrain/Tree_05.png";
	public String Tree_06 = "maps/terrain/Tree_06.png";
	public String Tree_07 = "maps/terrain/Tree_07.png";
	public String Tree_08 = "maps/terrain/Tree_08.png";
	public String Tree_09 = "maps/terrain/Tree_09.png";
	
	public String Building_01 = "maps/terrain/Building_A_01.png";
	
	public String block_F_01 = "maps/terrain/Block_E_01.png";
	public String block_F_02 = "maps/terrain/Block_E_02.png";
	
	public String Iceberg_01 = "maps/terrain/Iceberg.png";
	
	//volcano
	public String VolcanoFloor = "maps/terrain/volcano_floor_1.png";
	public String VolcanoForest_1 = "maps/terrain/volcano_forest_1.png";
	public String VolcanoForest_2 = "maps/terrain/volcano_forest_2.png";
	public String VolcanoWall = "maps/terrain/volcano_wall.png";
	
	
	//elements
	public String platform = "elements/Platform.png";
	public String dot_a = "elements/Dot_A.png";
	public String dot_b = "elements/Dot_B.png";
	
	//player 
	public String gunPlayer_01_A = "elements/player_1/Gun_01.png";
	public String bodyPlayer_01 = "elements/player_1/Hull_01_a.png";
	public String track_01 = "elements/player/Track_1_A.png";
	public String track_02 = "elements/player/Track_1_B.png";
	
	//exhaust
	public String exhaustFire_01 = "elements/player/exhaust/Exhaust_Frame_01.png";
	public String exhaustFire_02 = "elements/player/exhaust/Exhaust_Frame_02.png";
	public String exhaustFire_03 = "elements/player/exhaust/Exhaust_Frame_03.png";
	public String exhaustFire_04 = "elements/player/exhaust/Exhaust_Frame_04.png";
	public String exhaustFire_05 = "elements/player/exhaust/Exhaust_Frame_05.png";
	public String exhaustFire_06 = "elements/player/exhaust/Exhaust_Frame_06.png";
	public String exhaustFire_07 = "elements/player/exhaust/Exhaust_Frame_07.png";
	
	//tyres
	public String tire_01 = "elements/tires/Tire_Track_01.png";
	public String tire_02 = "elements/tires/Tire_Track_02.png";
	
	//enemy_1
	public String enemy_1_01 = "elements/enemy_1/Bomb_Idle_B_000.png";
	public String enemy_1_02 = "elements/enemy_1/Bomb_Idle_B_001.png";
	public String enemy_1_03 = "elements/enemy_1/Bomb_Idle_B_002.png";
	public String enemy_1_04 = "elements/enemy_1/Bomb_Idle_B_003.png";
	public String enemy_1_05 = "elements/enemy_1/Bomb_Idle_B_004.png";
	public String enemy_1_06 = "elements/enemy_1/Bomb_Idle_B_005.png";
	public String enemy_1_07 = "elements/enemy_1/Bomb_Idle_B_006.png";
	public String enemy_1_08 = "elements/enemy_1/Bomb_Idle_B_007.png";
	public String enemy_1_09 = "elements/enemy_1/Bomb_Idle_B_008.png";
	public String enemy_1_0A = "elements/enemy_1/Bomb_Idle_B_009.png";
	
	//enemy_1_parts
	public String enemy_1_part_01 = "elements/enemy_1/Bomb_Idle_B_000.png";
	public String enemy_1_part_02 = "elements/enemy_1/Bomb_Idle_B_001.png";
	public String enemy_1_part_03 = "elements/enemy_1/Bomb_Idle_B_002.png";
	
	
	//enemy_2
	public String gunEnemy_2_A = "elements/enemy_2/Gun_01.png";
	public String hullEnemy_2_A = "elements/enemy_2/Hull_01.png";
	public String tire_03 = "elements/tires/Tire_Track_03.png";
	public String tire_04 = "elements/tires/Tire_Track_04.png";
	
	//enemy_3
	public String enemy_3_01 = "elements/enemy_3/Bomb_Idle_A_000.png";
	public String enemy_3_02 = "elements/enemy_3/Bomb_Idle_A_001.png";
	public String enemy_3_03 = "elements/enemy_3/Bomb_Idle_A_002.png";		
	public String enemy_3_04 = "elements/enemy_3/Bomb_Idle_A_003.png";
	public String enemy_3_05 = "elements/enemy_3/Bomb_Idle_A_004.png";
	public String enemy_3_06 = "elements/enemy_3/Bomb_Idle_A_005.png";
	public String enemy_3_07 = "elements/enemy_3/Bomb_Idle_A_006.png";
	public String enemy_3_08 = "elements/enemy_3/Bomb_Idle_A_007.png";
	public String enemy_3_09 = "elements/enemy_3/Bomb_Idle_A_008.png";
	public String enemy_3_0A = "elements/enemy_3/Bomb_Idle_A_009.png";
	
	public String laser = "elements/shots/laser/Laser.png";
	public String plasma = "elements/shots/laser/Plasma.png";
	
	public String explosions_01 = "elements/explosions/Explosion_A_01.png";
	public String explosions_02 = "elements/explosions/Explosion_A_02.png";
	public String explosions_03 = "elements/explosions/Explosion_A_03.png";
	public String explosions_04 = "elements/explosions/Explosion_A_04.png";
	public String explosions_05 = "elements/explosions/Explosion_A_05.png";
	
	//lightmaps
	public String lightmap = "maps/lights/lightmap.png";
	
	
	
	public void loadAssets() {
		
		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.magFilter = Texture.TextureFilter.Linear;
		fontParam.minFilter = Texture.TextureFilter.Linear;
		
		load(fnt,BitmapFont.class,fontParam);
		
		load(imgSplash,Texture.class);
		load(imgSplash_1,Texture.class);
		load(imgIntermission,Texture.class);
		
		load(musicSplash,Music.class);
		load(musicIntermission,Music.class);
		
		load(musicGameplay_1,Music.class);
		load(musicGameplay_2,Music.class);
		load(musicGameplay_3,Music.class);
		load(musicGameplay_4,Music.class);
		load(musicGameplay_5,Music.class);
		load(musicGameplay_6,Music.class);
		
		
		load(startButton,Texture.class);
		load(exitButton,Texture.class);
		load(gameButton,Texture.class);
		load(settingsButton,Texture.class);
		load(scoreButton,Texture.class);
		
		load(headerSplash,Texture.class);
		load(tableSplash,Texture.class);
		
		load(block_A_01,Texture.class);				
		load(block_A_02,Texture.class);
		
		load(block_B_01,Texture.class);
		load(block_B_02,Texture.class);
		
		load(block_C_01,Texture.class);
		load(block_C_02,Texture.class);
		
		load(block_D_01,Texture.class);
		load(block_D_02,Texture.class);
		
		load(ground_tile_01_A,Texture.class); 
		load(ground_tile_01_B,Texture.class);
		load(ground_tile_01_C,Texture.class);
		load(ground_tile_01_D,Texture.class);
		
		load(ground_tile_02_A,Texture.class);
		load(ground_tile_02_B,Texture.class);
		load(ground_tile_02_C,Texture.class);
		load(ground_tile_02_D,Texture.class); 
		
		load(Palm_01,Texture.class);
		load(Palm_02,Texture.class);
		load(Palm_03,Texture.class);
		
		load(Cactus_01,Texture.class);
		load(Cactus_02,Texture.class);
		load(Cactus_03,Texture.class);
		
		load(Czech_01,Texture.class);
		load(Czech_02,Texture.class);
		
		load(Solar_01,Texture.class);
		load(Solar_02,Texture.class);
		
		
		load(ground_tile_01_E,Texture.class);
		load(ground_tile_02_E,Texture.class);
		
		load(block_E_01,Texture.class);
		load(block_E_02,Texture.class);
		load(Iceberg_01,Texture.class);
		
		
		load(platform,Texture.class);
		load(dot_a,Texture.class);
		load(dot_b,Texture.class);
		
		load(gunPlayer_01_A,Texture.class);
		load(bodyPlayer_01,Texture.class);
		load(track_01,Texture.class);
		load(track_02,Texture.class);
		
		load(exhaustFire_01,Texture.class);
		load(exhaustFire_02,Texture.class);
		load(exhaustFire_03,Texture.class);
		load(exhaustFire_04,Texture.class);
		load(exhaustFire_05,Texture.class);
		load(exhaustFire_06,Texture.class);
		load(exhaustFire_07,Texture.class);
		
		load(tire_01,Texture.class);
		load(tire_02,Texture.class);
		
		load(enemy_1_01,Texture.class);
		load(enemy_1_02,Texture.class);
		load(enemy_1_03,Texture.class);
		load(enemy_1_04,Texture.class);
		load(enemy_1_05,Texture.class);
		load(enemy_1_06,Texture.class);
		load(enemy_1_07,Texture.class);
		load(enemy_1_08,Texture.class);
		load(enemy_1_09,Texture.class);
		load(enemy_1_0A,Texture.class);
		
		load(enemy_1_part_01,Texture.class);
		load(enemy_1_part_02,Texture.class);
		load(enemy_1_part_03,Texture.class);
		
		//Badland
		load(ground_tile_01_F,Texture.class);
		load(ground_tile_02_F,Texture.class);
		
		load(block_F_01,Texture.class);
		load(block_F_02,Texture.class);
		
		load(Building_01,Texture.class);
		
		load(Tree_01,Texture.class);
		load(Tree_02,Texture.class);
		load(Tree_03,Texture.class);
		load(Tree_04,Texture.class);
		load(Tree_05,Texture.class);
		load(Tree_06,Texture.class);
		load(Tree_07,Texture.class);
		load(Tree_08,Texture.class);
		load(Tree_09,Texture.class);
		
		//Volcano
		load(VolcanoFloor,Texture.class);
		load(VolcanoForest_1,Texture.class);
		load(VolcanoForest_2,Texture.class);
		load(VolcanoWall,Texture.class);
		
		
		//enemy_2
		load(gunEnemy_2_A,Texture.class);
		load(hullEnemy_2_A ,Texture.class);
		load(tire_03 ,Texture.class);
		load(tire_04 ,Texture.class);
		
		//enemy_3
		load(enemy_3_01 ,Texture.class);
		load(enemy_3_02 ,Texture.class);
		load(enemy_3_03 ,Texture.class);		
		load(enemy_3_04 ,Texture.class);
		load(enemy_3_05 ,Texture.class);
		load(enemy_3_06 ,Texture.class);
		load(enemy_3_07 ,Texture.class);
		load(enemy_3_08 ,Texture.class);
		load(enemy_3_09 ,Texture.class);
		load(enemy_3_0A ,Texture.class);
		
		load(laser ,Texture.class);
		load(plasma ,Texture.class);
		
		load(explosions_01 ,Texture.class);
		load(explosions_02 ,Texture.class);
		load(explosions_03 ,Texture.class);
		load(explosions_04 ,Texture.class);
		load(explosions_05 ,Texture.class);
		
		load(lightmap, Texture.class);
		
		Gdx.app.log(TAG, "ASSETS LOADED");
	}
	
	
	public void initLoadedAssets() {
		
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
		font1.getData().setScale(0.75f,0.75f);
		
	}
}
