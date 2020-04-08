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
	
	//background test
	public String imgBackground = "background/Background.png";
	
	//background parallax test
	public String imgBackgroundParallaxBG = "background/parallax/BG.png";
	public String imgBackgroundParallaxStars = "background/parallax/Stars.png";
	public String imgBackgroundParallaxPlanets = "background/parallax/Planets.png";
	public String imgBackgroundParallaxMeteors = "background/parallax/Meteors.png";
	
	//background parallax test_1
	
	//background parallax test_2
	
	//background parallax test_3
	
	//player_1 atlas
	public String atlasPlayer_1SpriteSheet = "player_1/res/player_1.atlas";
	
	//player_1 array
	public String Player1ArrayPNG = "player_1/res/player_1.png";
	
	
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
		
		//images
		//load(imgLoadingLogo, Texture.class);
		load(imgLogo, Texture.class);
		
		/////////////////////////////////////////////////////////////
		load(imgStartButton, Texture.class);
		load(imgRatingButton, Texture.class);
		load(imgSettingsButton, Texture.class);
		load(imgExitButton, Texture.class);
		////////////////////////////////////////////////////////////
		
		
		load(imgBackground, Texture.class);
		load(imgBackgroundParallaxBG, Texture.class);
		load(imgBackgroundParallaxStars, Texture.class);
		load(imgBackgroundParallaxPlanets, Texture.class);
		load(imgBackgroundParallaxMeteors, Texture.class);
		
		//atlas
		load(atlasPlayer_1SpriteSheet,TextureAtlas.class);
		
		//player_array
		load(Player1ArrayPNG,Texture.class);
		
		//shoot
		////////////////////////////////////////////////////////////
		load(laser_small, Texture.class);
		load(laser_medium, Texture.class);
		load(laser_large, Texture.class);
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
