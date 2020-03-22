package com.gdx.game.engine.parallax;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdx.game.FirstTestGDX;

public class ParallaxLayer {
	
	private ArrayList<TextureRegion> parts;
	
	private float ratioX;
	private float ratioY;
	
	private float positionX;
	private float positionY;
	
	private int activeIndex = 0;
	
	public ParallaxLayer(float pRatioX, float pRatioY) {
		parts = new ArrayList<TextureRegion>();
		ratioX = pRatioX;
		ratioY = pRatioY;
		positionX = 0;
		positionY = 0;
	}
	
	public void addPart(TextureRegion region) {
		parts.add(region);
	}
	
	public void setPosition(float x, float y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	protected void moveRelative(float xSpeed, float ySpeed) {
		positionX += xSpeed * ratioX;
		positionY += ySpeed * ratioY;
	}
	
	protected void draw(SpriteBatch sb, float parentX, float parentY) {
		
		sb.draw(parts.get(activeIndex),
				parentX, 
				parentY + positionY);
	
		if ((positionY + (parts.get(activeIndex).getRegionHeight() )) < FirstTestGDX.screenHeight) {
			
			if (activeIndex == parts.size()-1)
				sb.draw(parts.get( 0 ),
						parentX,
						parentY + positionY + (parts.get(activeIndex).getRegionHeight())
						
						);
			else
				sb.draw(parts.get(activeIndex +1 ), 
						parentX,
						parentY + positionY + (parts.get(activeIndex).getRegionHeight())
						);
		}
		
		
		if (positionY <= (-parts.get(activeIndex ).getRegionHeight() )) {
			positionY = 0;
			activeIndex++;
			if (activeIndex >= parts.size()) 
				activeIndex = 0;
		}
		
		
		
		
		/*
		sb.draw(parts.get(activeIndex),
				parentX + positionX, 
				parentY + positionY);
	
		if ((positionX + (parts.get(activeIndex).getRegionWidth() )) < FirstTestGDX.screenWidth) {
			
			if (activeIndex == parts.size()-1)
				sb.draw(parts.get( 0 ),
						parentX + positionX + (parts.get(activeIndex).getRegionWidth()),
						parentY + positionY);
			else
				sb.draw(parts.get(activeIndex +1 ), 
						parentX + positionX + (parts.get(activeIndex).getRegionWidth()),
						parentY + positionY);
		}
		
		
		if (positionX <= (-parts.get(activeIndex ).getRegionWidth() )) {
			positionX = 0;
			activeIndex++;
			if (activeIndex >= parts.size()) 
				activeIndex = 0;
		}
		*/
	}
	
	

}
