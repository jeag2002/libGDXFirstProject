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
	public String imgIntermission = "splash/intermission_4.jpg";
	
	private String fnt = "fonts/Bangers_bitmap.fnt";
	public BitmapFont font1;
	public BitmapFont font2;
	
	private String fnt_1 = "fonts/digital.fnt";
	public BitmapFont font3;
	
	public String musicSplash = "music/start.mp3";
	public String musicIntermission = "music/intermission.mp3";
	public String musicGameplay_1 = "music/gameplay_1.mp3";
	public String musicGameplay_2 = "music/gameplay_2.mp3";
	public String musicGameplay_3 = "music/gameplay_3.mp3";
	public String musicGameplay_4 = "music/gameplay_4.mp3";
	public String musicGameplay_5 = "music/gameplay_5.mp3";
	public String musicGameplay_6 = "music/gameplay_6.mp3";
	
	public String musicPlayerDied = "music/afterlevel.mp3";
	public String musicEndLevel = "music/victory.mp3";
	public String musicEndGame = "music/victory_end.mp3";
	
	
	public String testSquare = "test/square.png";
	
	
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
	public String dot_b_light = "elements/Dot_B_light.png";
	
	public String dot_c = "elements/Dot_C.png";
	
	//player 
	public String gunPlayer_01_A = "elements/player_1/Gun_01.png";
	public String gunPlayer_01_B = "elements/player_1/Gun_02.png";
	public String gunPlayer_01_C = "elements/player_1/Gun_03.png";
	public String gunPlayer_01_D = "elements/player_1/Gun_04.png";
	public String gunPlayer_01_E = "elements/player_1/Gun_05.png";
	public String gunPlayer_01_F = "elements/player_1/Gun_06.png";
	
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
	
	//enemy_2 (TANK_1)
	public String gunEnemy_2_A = "elements/enemy_2/Gun_01.png";
	public String gunEnemy_2_A_1 = "elements/enemy_2/Gun_02.png";
	
	public String hullEnemy_2_A = "elements/enemy_2/Hull_01.png";
	public String tire_03 = "elements/tires/Tire_Track_03.png";
	public String tire_04 = "elements/tires/Tire_Track_04.png";
	
	//enemy_2 (TANK_2)
	public String gunEnemy_2_B = "elements/enemy_2_1/Gun_06.png";
	public String gunEnemy_2_B_1 = "elements/enemy_2_1/Gun_04.png";
	
	public String hullEnemy_2_B = "elements/enemy_2_1/Hull_05.png";
	public String tire_05 = "elements/tires/Tire_Track_05.png";
	public String tire_06 = "elements/tires/Tire_Track_06.png";
	
	//enemy_2 (TANK_3)
	public String gunEnemy_2_C = "elements/enemy_2_2/Gun_08.png";
	public String gunEnemy_2_C_1 = "elements/enemy_2_2/Gun_03.png";
	
	public String hullEnemy_2_C = "elements/enemy_2_2/Hull_06.png";
	
	
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
	
	//enemy_4
	public String enemy_4_01 = "elements/enemy_4/Bomb_Idle_C_000.png";
	public String enemy_4_02 = "elements/enemy_4/Bomb_Idle_C_001.png";
	public String enemy_4_03 = "elements/enemy_4/Bomb_Idle_C_002.png";		
	public String enemy_4_04 = "elements/enemy_4/Bomb_Idle_C_003.png";
	public String enemy_4_05 = "elements/enemy_4/Bomb_Idle_C_004.png";
	public String enemy_4_06 = "elements/enemy_4/Bomb_Idle_C_005.png";
	public String enemy_4_07 = "elements/enemy_4/Bomb_Idle_C_006.png";
	public String enemy_4_08 = "elements/enemy_4/Bomb_Idle_C_007.png";
	public String enemy_4_09 = "elements/enemy_4/Bomb_Idle_C_008.png";
	public String enemy_4_0A = "elements/enemy_4/Bomb_Idle_C_009.png";
	
	
	public String laser = "elements/shots/laser/Laser.png";
	public String plasma = "elements/shots/laser/Plasma.png";
	public String pulse = "elements/shots/laser/Proton.png";
	public String missile = "elements/shots/missile/Heavy_Shell.png";
	public String missile_1 = "elements/shots/missile/rocket.png";
	public String grenade = "elements/shots/grenade/grenade.png";
	
	
	//lightmaps
	public String lightmap = "maps/lights/lightmap.png";
	
	//gameplay 
	public String cursor = "gui/gameplay/cursor.png";
	
	//city
	public String background_city = "maps/terrain/Ground_Tile_01_G.png";
	public String wall_city = "maps/terrain/building_3.png";
	public String wall_city_normal = "maps/terrain/building_3_normalmap.png";
	public String parterre = "maps/terrain/parterre_1.png";
	
	//bonus
	public String hp_bonus = "elements/bonus/HP_Bonus.png";
	public String shield_bonus = "elements/bonus/Shield_Bonus.png";
	public String armor_bonus = "elements/bonus/Armor_Bonus.png";
	public String ammunition_bonus = "elements/bonus/Ammunition_Bonus.png";
	public String border_bonus = "elements/bonus/item_border.png";
	
	//holes
	public String hole_1 = "maps/terrain/hole_1.png";
	public String hole_2 = "maps/terrain/hole_2.png";
	public String hole_3 = "maps/terrain/hole_3.png";
	public String hole_4 = "maps/terrain/hole_4.png";
	public String hole_5 = "maps/terrain/hole_5.png";
	public String hole_6 = "maps/terrain/hole_6.png";
	
	//space
	public String space_wall = "maps/terrain/zspace.png";
	public String space_forest = "maps/terrain/zspace_forest.png";
	public String space_fan_1 = "maps/terrain/zspace_fan_1.png";
	public String space_fan_2 = "maps/terrain/zspace_fan_2.png";
	public String space_fan_3 = "maps/terrain/zspace_fan_3.png";
	public String space_fan_4 = "maps/terrain/zspace_fan_4.png";
	
	//water-1
	public String water_1 = "maps/terrain/water-2.jpg";
	public String water_2 = "maps/terrain/water-3.jpg";
	public String water_3 = "maps/terrain/water-4.jpg";
	
	public String island_background = "maps/terrain/island_background.png";
	
	//GUI-1
	public String Health_Bar = "gui/gameplay/gameplay_ui/Health_Bar_Table.png";
	public String Shield_Bar = "gui/gameplay/gameplay_ui/Shield_Bar_Table.png";
	public String Weapon_Bar = "gui/gameplay/gameplay_ui/Weapon_Bar_Table.png";
	public String Red_Dot = "gui/gameplay/gameplay_ui/Red_Dot.png";
	public String Blue_Dot = "gui/gameplay/gameplay_ui/Blue_Dot.png";
	public String Yellow_Dot = "gui/gameplay/gameplay_ui/Yellow_Dot_1.png";
	
	public String Clock = "gui/gameplay/gameplay_ui/Clock_Icon.png";
	public String Radar = "gui/gameplay/gameplay_ui/Radar.png";
	public String Icon = "gui/gameplay/gameplay_ui/Armor_Table.png";

	
	public String flame_1 = "elements/shots/flame/flame_01_A.png";
	public String flame_2 = "elements/shots/flame/flame_02_A.png";
	public String flame_3 = "elements/shots/flame/flame_03_A.png";
	public String flame_4 = "elements/shots/flame/flame_04_A.png";
	
	public String rocket_01 = "elements/shots/missile/rocket_A.png";
	public String rocket_02 = "elements/shots/missile/rocket_B.png";
	public String rocket_03 = "elements/shots/missile/rocket_C.png";
	
	public String bonus_Nuke = "elements/bonus/Nuke_Bonus.png";
	public String bonus_Coin = "elements/bonus/Coin_A.png";
	
		
	
	//AFTER PLAY
	//////////////////////////////////////////////////////////////////
	public String HeaderTable = "gui/afterplay/Header_Table.png";
	public String Window = "gui/afterplay/Window.png";
	public String Defeat = "gui/afterplay/Defeat.png";
	public String Victory = "gui/afterplay/Victory.png";
	public String Score = "gui/afterplay/Score.png";
	public String DecorTable = "gui/afterplay/Decor_Table.png";
	public String MenuBTN = "gui/afterplay/Menu_BTN.png";
	public String PlayBTN = "gui/afterplay/Play_BTN.png";
	public String GoldStar = "gui/afterplay/Star_01.png";
	public String Table01 = "gui/afterplay/Table_01.png";
	public String Table03 = "gui/afterplay/Table_03.png";
	
	public String Hangar = "gui/afterplay/Hangar.png";
	public String Description = "gui/afterplay/Description.png";
	public String Rating = "gui/afterplay/Rating.png";
	///////////////////////////////////////////////////////////////////
	
	public String Track_Print = "elements/player/Track_Print.png";
	
	public String Slime_1 = "maps/terrain/slime_1.jpg";
	public String Slime_2 = "maps/terrain/slime_2.jpg";
	
	public String Barrel_1 = "maps/terrain/wasteland_barrell_01.png";
	public String Barrel_2 = "maps/terrain/wasteland_barrell_02.png";
	public String Barrel_3 = "maps/terrain/wasteland_box.png";
	
	
	
	public void loadAssets() {
		
		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.magFilter = Texture.TextureFilter.Linear;
		fontParam.minFilter = Texture.TextureFilter.Linear;
		
		load(fnt,BitmapFont.class,fontParam);
		load(fnt_1,BitmapFont.class,fontParam);
		
		load(testSquare, Texture.class);
		
		load(imgSplash,Texture.class);
		load(imgIntermission,Texture.class);
		
		load(musicSplash,Music.class);
		load(musicIntermission,Music.class);
		
		load(musicPlayerDied,Music.class);
		load(musicEndLevel,Music.class);
		load(musicEndGame,Music.class);
		
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
		load(dot_c,Texture.class);
		load(dot_b_light,Texture.class);
		
		load(gunPlayer_01_A,Texture.class);
		load(gunPlayer_01_B,Texture.class);
		load(gunPlayer_01_C,Texture.class);
		load(gunPlayer_01_D,Texture.class);
		load(gunPlayer_01_E,Texture.class);
		load(gunPlayer_01_F,Texture.class);
		
		
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
		load(gunEnemy_2_A_1,Texture.class);
		
		load(hullEnemy_2_A ,Texture.class);
		load(tire_03 ,Texture.class);
		load(tire_04 ,Texture.class);
		
		
		//enemy_2 (TANK_2)
		load(gunEnemy_2_B,Texture.class);
		load(gunEnemy_2_B_1,Texture.class);
		
		load(hullEnemy_2_B ,Texture.class);
		load(tire_05 ,Texture.class);
		load(tire_06 ,Texture.class);
		
		//enemy_2 (TANK_3)
		load(gunEnemy_2_C ,Texture.class);
		load(gunEnemy_2_C_1 ,Texture.class);
		load(hullEnemy_2_C  ,Texture.class);
		
		
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
		
		//enemy_4
		load(enemy_4_01 ,Texture.class);
		load(enemy_4_02 ,Texture.class);
		load(enemy_4_03 ,Texture.class);		
		load(enemy_4_04 ,Texture.class);
		load(enemy_4_05 ,Texture.class);
		load(enemy_4_06 ,Texture.class);
		load(enemy_4_07 ,Texture.class);
		load(enemy_4_08 ,Texture.class);
		load(enemy_4_09 ,Texture.class);
		load(enemy_4_0A ,Texture.class);
		
		load(laser ,Texture.class);
		load(plasma ,Texture.class);
		load(pulse ,Texture.class);
		load(missile,Texture.class);
		load(missile_1,Texture.class);
		load(grenade,Texture.class);
		
		load(flame_1,Texture.class);
		load(flame_2,Texture.class);
		load(flame_3,Texture.class);
		load(flame_4,Texture.class);
		
		load(lightmap, Texture.class);
		load(cursor, Texture.class);
		
		load(background_city, Texture.class);
		load(wall_city,Texture.class);
		load(wall_city_normal,Texture.class);
		load(parterre,Texture.class);
		
		load(hp_bonus, Texture.class);
		load(shield_bonus, Texture.class);
		load(armor_bonus, Texture.class);
		load(ammunition_bonus, Texture.class);
		load(border_bonus, Texture.class);
		load(bonus_Nuke, Texture.class);
		load(bonus_Coin, Texture.class);
		
		//elements destroyed
		load(hole_1, Texture.class);
		load(hole_2, Texture.class);
		load(hole_3, Texture.class);
		load(hole_4, Texture.class);
		load(hole_5, Texture.class);
		load(hole_6, Texture.class);
		
		//space
		load(space_wall, Texture.class);
		load(space_forest, Texture.class);
		load(space_fan_1, Texture.class);
		load(space_fan_2, Texture.class);
		load(space_fan_3, Texture.class);
		load(space_fan_4, Texture.class);
		
		load(water_1, Texture.class);
		load(water_2, Texture.class);
		load(water_3, Texture.class);
		
		load(rocket_01, Texture.class);
		load(rocket_02, Texture.class);
		load(rocket_03, Texture.class); 
		
		
		load(island_background, Texture.class);
		
		
		//GUI
		//////////////////////////////////////////
		load(Health_Bar, Texture.class);
		load(Shield_Bar, Texture.class);
		load(Weapon_Bar, Texture.class);
		load(Red_Dot, Texture.class);
		load(Blue_Dot, Texture.class);
		load(Yellow_Dot, Texture.class);
		load(Clock, Texture.class);
		load(Radar, Texture.class);
		load(Icon,Texture.class);
		//////////////////////////////////////////
		
		//////////////////////////////////////////
		load(Window,Texture.class);
		load(HeaderTable,Texture.class);
		load(Defeat,Texture.class);
		load(Victory,Texture.class);
		load(Score,Texture.class);
		load(DecorTable,Texture.class);
		load(MenuBTN,Texture.class);
		load(PlayBTN,Texture.class);
		load(GoldStar,Texture.class);
		load(Table01,Texture.class);
		load(Table03,Texture.class);
		load(Hangar,Texture.class);
		load(Description,Texture.class);
		load(Rating,Texture.class);
		/////////////////////////////////////////
		
		//WASTELAND
		/////////////////////////////////////////
		load(Slime_1,Texture.class);
		load(Slime_2,Texture.class);
		
		load(Barrel_1,Texture.class);
		load(Barrel_2,Texture.class);
		load(Barrel_3,Texture.class);
		/////////////////////////////////////////
		 
		Gdx.app.log(TAG, "ASSETS LOADED");
 	}
	
	
	public void initLoadedAssets() {
		
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
		font1.getData().setScale(0.75f,0.75f);
		
		font2 = get(fnt, BitmapFont.class);
		font2.setColor(Color.WHITE);
		font2.getData().setScale(0.5f,0.5f);
		
		font3 = get(fnt_1, BitmapFont.class);
		//font3.setColor(Color.LIME);
		font3.getData().setScale(1.0f,1.0f);
		
	}
}
