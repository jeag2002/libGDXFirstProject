package com.gdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

//https://www.gamefromscratch.com/post/2013/10/02/LibGDX-Tutorial-3-Basic-graphics.aspx
//https://github.com/libgdx/libgdx/wiki/Logging
//https://github.com/mrafayaleem/gdx-sqlite

public class Resources extends AssetManager{
	
	
	private static final String TAG = "[RESOURCES]";
	
	//assets
	
	//fonts
	private String fnt = "fonts/Bangers_bitmap.fnt";
	private String fnt_1 = "fonts/Bangers_bitmap.fnt";
	
	public String imgLogo = "splash/logo.png";
	
	//GUI BUTTONS TEST
	public String imgStartButton = "gui/Start_BTN.png";
	public String imgRatingButton = "gui/Rating_A_PNG.png";
	public String imgSettingsButton = "gui/Settings_A_PNG.png";
	public String imgExitButton = "gui/Exit_BTN.png";
	
	public String imgSettingsGUI = "gui/Settings_Banner.png";
	
	
	//background parallax test
	public String imgBackgroundParallaxBG = "background/parallax/BG.png";
	public String imgBackgroundParallaxStars = "background/parallax/Stars.png";
	public String imgBackgroundParallaxPlanets = "background/parallax/Planets.png";
	public String imgBackgroundParallaxMeteors = "background/parallax/Meteors.png";
	
	//background parallax test_1
	
	//background parallax test_2
	
	//background parallax test_3
	
	//player
	public String imgPlayerRed_01 =  "player_1/unpacket/PlayerRed_Frame_01.png";
	public String imgPlayerRed_02 =  "player_1/unpacket/PlayerRed_Frame_02.png";
	public String imgPlayerRed_03 =  "player_1/unpacket/PlayerRed_Frame_03.png";
	public String imgPlayerRed_04 =  "player_1/unpacket/PlayerRed_Frame_04.png";
	public String imgPlayerRed_05 =  "player_1/unpacket/PlayerRed_Frame_05.png";
	
	//shadow-player
	public String imgShadowPlayerRed_01 = "player_1/unpacket/PlayerShadow_Frame_01.png";
	public String imgShadowPlayerRed_02 = "player_1/unpacket/PlayerShadow_Frame_02.png";
	public String imgShadowPlayerRed_03 = "player_1/unpacket/PlayerShadow_Frame_03.png";
	public String imgShadowPlayerRed_04 = "player_1/unpacket/PlayerShadow_Frame_04.png";
	public String imgShadowPlayerRed_05 = "player_1/unpacket/PlayerShadow_Frame_05.png";
	
	//exhaust-player
	public String imgExhaustFrame_01 = "player_1/unpacket/exhaust/Exhaust_Frame_01.png";
	public String imgExhaustFrame_02 = "player_1/unpacket/exhaust/Exhaust_Frame_02.png";
	public String imgExhaustFrame_03 = "player_1/unpacket/exhaust/Exhaust_Frame_03.png";
	public String imgExhaustFrame_04 = "player_1/unpacket/exhaust/Exhaust_Frame_04.png";
	public String imgExhaustFrame_05 = "player_1/unpacket/exhaust/Exhaust_Frame_05.png"; 
	public String imgExhaustFrame_06 = "player_1/unpacket/exhaust/Exhaust_Frame_06.png";
	public String imgExhaustFrame_07 = "player_1/unpacket/exhaust/Exhaust_Frame_07.png";
	
	//exhaust-player_1
	public String imgRetroUL_1 = "player_1/unpacket/exhaust_1/retros-UL_1.png";
    public String imgRetroUL_2 = "player_1/unpacket/exhaust_1/retros-UL_2.png";
	public String imgRetroUR_1 = "player_1/unpacket/exhaust_1/retros-UR_1.png";
    public String imgRetroUR_2 = "player_1/unpacket/exhaust_1/retros-UR_2.png";
    public String imgRetroDL_1 = "player_1/unpacket/exhaust_1/retros-DL_1.png";
    public String imgRetroDL_2 = "player_1/unpacket/exhaust_1/retros-DL_2.png";
    public String imgRetroDR_1 = "player_1/unpacket/exhaust_1/retros-DR_1.png";
    public String imgRetroDR_2 = "player_1/unpacket/exhaust_1/retros-DR_2.png";
    
    //shoot
    public String laser_small  = "shoot/shoot_1/laser_small.png";
    public String laser_medium = "shoot/shoot_1/laser_medium.png";
    public String laser_large  = "shoot/shoot_1/laser_large.png";
    
    public String laser_small_l  = "shoot/shoot_1/laser_small_l.png";
    public String laser_medium_l = "shoot/shoot_1/laser_medium_l.png";
    public String laser_large_l  = "shoot/shoot_1/laser_large_l.png";
    
    public String laser_small_rt  = "shoot/shoot_1/laser_small_r.png";
    public String laser_medium_rt = "shoot/shoot_1/laser_medium_r.png";
    public String laser_large_rt  = "shoot/shoot_1/laser_large_r.png";
    
    public String laser_small_r  = "shoot/shoot_1_r/laser_small.png";
    public String laser_medium_r = "shoot/shoot_1_r/laser_medium.png";
    public String laser_large_r  = "shoot/shoot_1_r/laser_large.png";
    
    public String proton_small_r = "shoot/shoot_2/Proton_Small.png";
    public String proton_medium_r = "shoot/shoot_2/Proton_Medium.png";
    public String proton_large_r = "shoot/shoot_2/Proton_Large.png";
    
    //enemy_1
    public String imgEnemy_1_01 = "enemies/enemy_1/Enemy01_Green_Frame_1.png";
    public String imgEnemy_1_02 = "enemies/enemy_1/Enemy01_Green_Frame_2.png";
    public String imgEnemy_1_03 = "enemies/enemy_1/Enemy01_Green_Frame_3.png";
    public String imgEnemy_1_04 = "enemies/enemy_1/Enemy01_Green_Frame_4.png";
    public String imgEnemy_1_05 = "enemies/enemy_1/Enemy01_Green_Frame_5.png";
    
    public String imgShadowEnemy_1_01 = "enemies/enemy_1/Enemy01_Shadow_Frame_1.png";
    public String imgShadowEnemy_1_02 = "enemies/enemy_1/Enemy01_Shadow_Frame_2.png";
    public String imgShadowEnemy_1_03 = "enemies/enemy_1/Enemy01_Shadow_Frame_3.png";
    public String imgShadowEnemy_1_04 = "enemies/enemy_1/Enemy01_Shadow_Frame_4.png";
    public String imgShadowEnemy_1_05 = "enemies/enemy_1/Enemy01_Shadow_Frame_5.png";
    
    //enemy_2
    public String imgEnemy_2_01 = "enemies/enemy_2/Enemy02_Teal_Frame_1.png";
    public String imgEnemy_2_02 = "enemies/enemy_2/Enemy02_Teal_Frame_2.png";
    public String imgEnemy_2_03 = "enemies/enemy_2/Enemy02_Teal_Frame_3.png";
    public String imgEnemy_2_04 = "enemies/enemy_2/Enemy02_Teal_Frame_4.png";
    public String imgEnemy_2_05 = "enemies/enemy_2/Enemy02_Teal_Frame_5.png";
    
    public String imgShadowEnemy_2_01 = "enemies/enemy_2/Enemy02_Shadow_Frame_1.png";
    public String imgShadowEnemy_2_02 = "enemies/enemy_2/Enemy02_Shadow_Frame_2.png";
    public String imgShadowEnemy_2_03 = "enemies/enemy_2/Enemy02_Shadow_Frame_3.png";
    public String imgShadowEnemy_2_04 = "enemies/enemy_2/Enemy02_Shadow_Frame_4.png";
    public String imgShadowEnemy_2_05 = "enemies/enemy_2/Enemy02_Shadow_Frame_5.png";
   
    //enemy_exhaust
    public String imgExhaustEnemy_1_01 = "enemies/enemy_1/exhaust/Exhaust_Frame_01.png";
    public String imgExhaustEnemy_1_02 = "enemies/enemy_1/exhaust/Exhaust_Frame_02.png";
    public String imgExhaustEnemy_1_03 = "enemies/enemy_1/exhaust/Exhaust_Frame_03.png";
    public String imgExhaustEnemy_1_04 = "enemies/enemy_1/exhaust/Exhaust_Frame_04.png";
    public String imgExhaustEnemy_1_05 = "enemies/enemy_1/exhaust/Exhaust_Frame_05.png";
    public String imgExhaustEnemy_1_06 = "enemies/enemy_1/exhaust/Exhaust_Frame_06.png";
    public String imgExhaustEnemy_1_07 = "enemies/enemy_1/exhaust/Exhaust_Frame_07.png";
    
    //turrets
    public String img_turret_00 = "assets/SpaceRageTileset-Assets/Export/64x64px/GunTurret/GunTurretMount.png";
    public String img_turret_01 = "enemies/turrets/GunTurret_Green.png";
    public String img_turret_02 = "enemies/turrets/GunTurret_Gun.png";
    
    //meteors
    public String imgMeteor_01_a = "items/meteors/Meteor_01_a.png";
    public String imgMeteor_01_b = "items/meteors/Meteor_01_b.png";
    public String imgMeteor_02_a = "items/meteors/Meteor_02_a.png";
    public String imgMeteor_02_b = "items/meteors/Meteor_02_b.png";
    
    //powerup
    public String imgPower_A = "items/bonus/Powerup_Ammo.png";
    public String imgPower_E = "items/bonus/Powerup_Energy.png";
    public String imgPower_H = "items/bonus/Powerup_Health.png";
    public String imgPower_S = "items/bonus/Powerup_Shields.png";
    public String imgItemBorder = "items/bonus/item_border.png";
    
    //explosion
    public String imgExplosion02_Frame_01 = "explosions/explosion_1/Explosion02_Frame_01.png";
    public String imgExplosion02_Frame_02 = "explosions/explosion_1/Explosion02_Frame_02.png";
    public String imgExplosion02_Frame_03 = "explosions/explosion_1/Explosion02_Frame_03.png";
    public String imgExplosion02_Frame_04 = "explosions/explosion_1/Explosion02_Frame_04.png";
    public String imgExplosion02_Frame_05 = "explosions/explosion_1/Explosion02_Frame_05.png";
    public String imgExplosion02_Frame_06 = "explosions/explosion_1/Explosion02_Frame_06.png";
    public String imgExplosion02_Frame_07 = "explosions/explosion_1/Explosion02_Frame_07.png";
    public String imgExplosion02_Frame_08 = "explosions/explosion_1/Explosion02_Frame_08.png";
    public String imgExplosion02_Frame_09 = "explosions/explosion_1/Explosion02_Frame_09.png";
    
    public String imgExplosion01_Frame_01 = "explosions/explosion_2/Explosion01_Frame_01.png";
    public String imgExplosion01_Frame_02 = "explosions/explosion_2/Explosion01_Frame_02.png";
    public String imgExplosion01_Frame_03 = "explosions/explosion_2/Explosion01_Frame_03.png";
    public String imgExplosion01_Frame_04 = "explosions/explosion_2/Explosion01_Frame_04.png";
    public String imgExplosion01_Frame_05 = "explosions/explosion_2/Explosion01_Frame_05.png";
    public String imgExplosion01_Frame_06 = "explosions/explosion_2/Explosion01_Frame_06.png";
    public String imgExplosion01_Frame_07 = "explosions/explosion_2/Explosion01_Frame_07.png";
    public String imgExplosion01_Frame_08 = "explosions/explosion_2/Explosion01_Frame_08.png";
    public String imgExplosion01_Frame_09 = "explosions/explosion_2/Explosion01_Frame_09.png";
    
    //gameplay GUI
    public String imgHealthBar = "gui/gameplay/Health_Bar_Table.png";
    public String imgHealthBarDot = "gui/gameplay/Health_Bar_Dot.png";
    public String imgArmorBar = "gui/gameplay/Armor_Bar_Table.png";
    public String imgArmorBarDot = "gui/gameplay/Armor_Bar_Dot.png";
    public String imgStatsBar = "gui/gameplay/Stats_Bar.png";
    public String imgIconSpecial = "gui/gameplay/Table_03.png";
    
    public String imgClock = "gui/gameplay/Clock_Icon.png";
    public String imgCristal = "gui/gameplay/Cristal_Icon.png";
    
    
    //icons
    public String imgIcon_2 = "icons/icon_2.png";
    public String imgIcon_3 = "icons/icon_3.png";
    public String imgIcon_4 = "icons/icon_4.png";
    
    //end level GUI
    public String imgMenuBtn = "gui/endlevel/Menu_BTN.png";
    public String imgMenuOK = "gui/endlevel/Ok_BTN.png";
    
    public String imgStart01 = "gui/endlevel/Star_01.png";
    public String imgStart02 = "gui/endlevel/Star_02.png"; 
    public String imgStart03 = "gui/endlevel/Star_03.png";
    
    public String imgWindows = "gui/endlevel/Window.png";
    public String imgYouLose = "gui/endlevel/youlose.png";
    public String imgYouWin = "gui/endlevel/youwin.png";
     
    //GUI ANDROID
    public String joystick = "gui/gui-android/touchKnob.png"; 
    public String joystick_background = "gui/gui-android/touchBackground.png";
    public String shoot = "gui/gui-android/flatDark25.png";
    public String changeArm = "gui/gui-android/flatDark26.png";
    public String joystick_logo = "gui/gui-android/joystick.png";
    
	//music
	public String menu_MUSIC = "sounds/back_music.ogg";
	
	
	public BitmapFont font1;
	public BitmapFont font2;
	
	public void loadAssets() {
		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.magFilter = Texture.TextureFilter.Linear;
		fontParam.minFilter = Texture.TextureFilter.Linear;
		
		//fonts
		load(fnt,BitmapFont.class,fontParam);
		load(fnt_1,BitmapFont.class,fontParam);
		
		//images
		//load(imgLoadingLogo, Texture.class);
		load(imgLogo, Texture.class);
		
		/////////////////////////////////////////////////////////////
		load(imgStartButton, Texture.class);
		load(imgRatingButton, Texture.class);
		load(imgSettingsButton, Texture.class);
		load(imgExitButton, Texture.class);
		load(imgSettingsGUI, Texture.class);
		
		////////////////////////////////////////////////////////////
		
		load(imgBackgroundParallaxBG, Texture.class);
		load(imgBackgroundParallaxStars, Texture.class);
		load(imgBackgroundParallaxPlanets, Texture.class);
		load(imgBackgroundParallaxMeteors, Texture.class);
		
		//shoot
		////////////////////////////////////////////////////////////
		load(laser_small, Texture.class);
		load(laser_medium, Texture.class);
		load(laser_large, Texture.class);
		
		load(laser_small_l, Texture.class);
		load(laser_medium_l, Texture.class);
		load(laser_large_l, Texture.class);
	    
		load(laser_small_rt, Texture.class);
		load(laser_medium_rt, Texture.class);
		load(laser_large_rt, Texture.class);
		
		load(laser_small_r, Texture.class);
		load(laser_medium_r, Texture.class);
		load(laser_large_r, Texture.class);
		
		load(proton_small_r, Texture.class);
		load(proton_medium_r, Texture.class);
		load(proton_large_r, Texture.class);
		////////////////////////////////////////////////////////////
		
		//player
		////////////////////////////////////////////////////////////
		load(imgPlayerRed_01, Texture.class);
		load(imgPlayerRed_02, Texture.class);
		load(imgPlayerRed_03, Texture.class);
		load(imgPlayerRed_04, Texture.class);
		load(imgPlayerRed_05, Texture.class);
		
		load(imgShadowPlayerRed_01, Texture.class);
		load(imgShadowPlayerRed_02, Texture.class);
		load(imgShadowPlayerRed_03, Texture.class);
		load(imgShadowPlayerRed_04, Texture.class);
		load(imgShadowPlayerRed_05, Texture.class);
        ////////////////////////////////////////////////////////////
		
		//exhaust 
        ////////////////////////////////////////////////////////////
		load(imgExhaustFrame_01, Texture.class);
		load(imgExhaustFrame_02, Texture.class);
		load(imgExhaustFrame_03, Texture.class);
		load(imgExhaustFrame_04, Texture.class);
		load(imgExhaustFrame_05, Texture.class);
		load(imgExhaustFrame_06, Texture.class);
		load(imgExhaustFrame_07, Texture.class);
		////////////////////////////////////////////////////////////
		
		//exhaust_1
        ////////////////////////////////////////////////////////////
		load(imgRetroUL_1, Texture.class);
		load(imgRetroUL_2, Texture.class);
		load(imgRetroUR_1, Texture.class);
		load(imgRetroUR_2, Texture.class);
		load(imgRetroDL_1, Texture.class);
		load(imgRetroDL_2, Texture.class);
		load(imgRetroDR_1, Texture.class);
		load(imgRetroDR_2, Texture.class);
        ////////////////////////////////////////////////////////////
		
		//enemy_1
		////////////////////////////////////////////////////////////
		load(imgEnemy_1_01, Texture.class);
		load(imgEnemy_1_02, Texture.class);
		load(imgEnemy_1_03, Texture.class);
		load(imgEnemy_1_04, Texture.class);
		load(imgEnemy_1_05, Texture.class);
		
		
		load(imgShadowEnemy_1_01, Texture.class);
		load(imgShadowEnemy_1_02, Texture.class);
		load(imgShadowEnemy_1_03, Texture.class);
		load(imgShadowEnemy_1_04, Texture.class);
		load(imgShadowEnemy_1_05, Texture.class);
		
		
		load(imgExhaustEnemy_1_01, Texture.class);
		load(imgExhaustEnemy_1_02, Texture.class);
		load(imgExhaustEnemy_1_03, Texture.class);
		load(imgExhaustEnemy_1_04, Texture.class);
		load(imgExhaustEnemy_1_05, Texture.class);
		load(imgExhaustEnemy_1_06, Texture.class);
		load(imgExhaustEnemy_1_07, Texture.class);
		////////////////////////////////////////////////////////////
		
		//enemy_2
		////////////////////////////////////////////////////////////
		load(imgEnemy_2_01, Texture.class);
		load(imgEnemy_2_02, Texture.class);
		load(imgEnemy_2_03, Texture.class);
		load(imgEnemy_2_04, Texture.class);
		load(imgEnemy_2_05, Texture.class);
	    
		load(imgShadowEnemy_2_01, Texture.class);
		load(imgShadowEnemy_2_02, Texture.class);
		load(imgShadowEnemy_2_03, Texture.class);
		load(imgShadowEnemy_2_04, Texture.class);
		load(imgShadowEnemy_2_05, Texture.class);
		////////////////////////////////////////////////////////////
		
		//turret
		////////////////////////////////////////////////////////////
		load(img_turret_00, Texture.class);
		load(img_turret_01, Texture.class);
		load(img_turret_02, Texture.class);
		////////////////////////////////////////////////////////////
		
		//meteor
		////////////////////////////////////////////////////////////
		load(imgMeteor_01_a, Texture.class);
		load(imgMeteor_01_b, Texture.class);
		load(imgMeteor_02_a, Texture.class);
		load(imgMeteor_02_b, Texture.class);
		////////////////////////////////////////////////////////////
		
		//bonus
		////////////////////////////////////////////////////////////
		load(imgPower_A, Texture.class);
		load(imgPower_E, Texture.class);
		load(imgPower_H, Texture.class);
		load(imgPower_S, Texture.class);
		load(imgItemBorder, Texture.class);
		////////////////////////////////////////////////////////////
		
		//icons
		///////////////////////////////////////////////////////////
		load(imgIcon_2, Texture.class);
		load(imgIcon_3, Texture.class);
		load(imgIcon_4, Texture.class);
		///////////////////////////////////////////////////////////
		
		
		//explosions
		////////////////////////////////////////////////////////////
		load(imgExplosion02_Frame_01, Texture.class);
		load(imgExplosion02_Frame_02, Texture.class);
		load(imgExplosion02_Frame_03, Texture.class);
		load(imgExplosion02_Frame_04, Texture.class);
		load(imgExplosion02_Frame_05, Texture.class);
		load(imgExplosion02_Frame_06, Texture.class);
		load(imgExplosion02_Frame_07, Texture.class);
		load(imgExplosion02_Frame_08, Texture.class);
		load(imgExplosion02_Frame_09, Texture.class);
		
		
		
		load(imgExplosion01_Frame_01, Texture.class);
		load(imgExplosion01_Frame_02, Texture.class);
		load(imgExplosion01_Frame_03, Texture.class);
		load(imgExplosion01_Frame_04, Texture.class);
		load(imgExplosion01_Frame_05, Texture.class);
		load(imgExplosion01_Frame_06, Texture.class);
		load(imgExplosion01_Frame_07, Texture.class);
		load(imgExplosion01_Frame_08, Texture.class);
		load(imgExplosion01_Frame_09, Texture.class);
		////////////////////////////////////////////////////////////
		
		//GUI
		////////////////////////////////////////////////////////////
		load(imgHealthBar, Texture.class);
		load(imgHealthBarDot, Texture.class);
		load(imgArmorBar, Texture.class);
		load(imgArmorBarDot, Texture.class);
		load(imgStatsBar, Texture.class);
		load(imgIconSpecial, Texture.class);
		load(imgClock, Texture.class);
		load(imgCristal, Texture.class);
		////////////////////////////////////////////////////////////
		
		//END LEVEL GUI
		////////////////////////////////////////////////////////////
		load(imgMenuBtn, Texture.class);
		load(imgMenuOK, Texture.class);
		load(imgStart01, Texture.class);
		load(imgStart02, Texture.class);
		load(imgStart03, Texture.class);
		load(imgWindows, Texture.class);
		load(imgYouLose, Texture.class);
		load(imgYouWin, Texture.class);;
		////////////////////////////////////////////////////////////
		
		//ANDROID GUI
		////////////////////////////////////////////////////////////
		load(joystick, Texture.class); 
		load(joystick_background, Texture.class);
		load(shoot, Texture.class);
		load(changeArm, Texture.class);
		load(joystick_logo,Texture.class);
		////////////////////////////////////////////////////////////
		
		
		//load(imgExhaustRetro, Texture.class);
		
		Gdx.app.log(TAG, "ASSETS LOADED");
		
	
	}
	
	public void initLoadedAssets() {
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
		
		font2 = get(fnt_1, BitmapFont.class);
		font2.setColor(Color.WHITE);
		font2.getData().setScale(2);
	}

}
