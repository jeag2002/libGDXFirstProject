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
	
	//loading screen + splash page
	//public String imgLoadingLogo = "splash/loading_logo.png";
	public String imgLogo = "splash/logo.png";
	
	//background test
	public String imgBackground = "background/Background.png";
	
	//background parallax test
	public String imgBackgroundParallaxBG = "background/parallax/BG.png";
	public String imgBackgroundParallaxStars = "background/parallax/Stars.png";
	public String imgBackgroundParallaxPlanets = "background/parallax/Planets.png";
	public String imgBackgroundParallaxMeteors = "background/parallax/Meteors.png";
	
	//player atlas
	public String atlasPlayerSpriteSheet = "player/spritesheet_1.atlas";
	
	//player
	public String imgPlayerRed_01 =  "player_1/PlayerRed_Frame_01.png";
	public String imgPlayerRed_02 =  "player_1/PlayerRed_Frame_02.png";
	public String imgPlayerRed_03 =  "player_1/PlayerRed_Frame_03.png";
	
	public String imgShadowPlayerRed_01 = "player_1/PlayerShadow_Frame_01.png";
	public String imgShadowPlayerRed_02 = "player_1/PlayerShadow_Frame_02.png";
	public String imgShadowPlayerRed_03 = "player_1/PlayerShadow_Frame_03.png";
	
	
	public BitmapFont font1;
	
	public void loadAssets() {
		BitmapFontParameter fontParam = new BitmapFontParameter();
		fontParam.magFilter = Texture.TextureFilter.Linear;
		fontParam.minFilter = Texture.TextureFilter.Linear;
		
		//fonts
		load(fnt,BitmapFont.class,fontParam);
		
		//images
		//load(imgLoadingLogo, Texture.class);
		load(imgLogo, Texture.class);
		
		load(imgBackground, Texture.class);
		load(imgBackgroundParallaxBG, Texture.class);
		load(imgBackgroundParallaxStars, Texture.class);
		load(imgBackgroundParallaxPlanets, Texture.class);
		load(imgBackgroundParallaxMeteors, Texture.class);
		
		//atlas
		load(atlasPlayerSpriteSheet,TextureAtlas.class);
		
		//player
		load(imgPlayerRed_01, Texture.class);
		load(imgPlayerRed_02, Texture.class);
		load(imgPlayerRed_03, Texture.class);
		
		load(imgShadowPlayerRed_01, Texture.class);
		load(imgShadowPlayerRed_02, Texture.class);
		load(imgShadowPlayerRed_03, Texture.class);

		
		
		Gdx.app.log(TAG, "ASSETS LOADED");
		
	
	}
	
	public void initLoadedAssets() {
		font1 = get(fnt, BitmapFont.class);
		font1.setColor(Color.WHITE);
	}

}
