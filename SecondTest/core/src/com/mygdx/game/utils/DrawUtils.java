package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DrawUtils {

	public static Drawable getColoredDrawable(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		pixmap.dispose();
		return drawable;
	}
	
	public static Texture resizeTexture(Texture texture, int befWidth, int befHeight, int afWidth, int afHeight) {
		
		TextureData textureData = texture.getTextureData();
		if (!textureData.isPrepared()) {
		    textureData.prepare();
		}
		Pixmap pixmapbef = textureData.consumePixmap();
		Pixmap pixmapaf = new Pixmap(afWidth, afHeight, pixmapbef.getFormat());
		
		pixmapaf.drawPixmap(pixmapbef,0,0,pixmapbef.getWidth(),pixmapbef.getHeight(),0,0,pixmapaf.getWidth(),pixmapaf.getHeight());
		Texture textureAf = new Texture(pixmapaf);
		
		pixmapbef.dispose();
		pixmapaf.dispose();
				
		return textureAf;
		
	}
	
	

}
