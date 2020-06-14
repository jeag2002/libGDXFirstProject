package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.utils.DrawUtils;

public class GameLogicElementInformation {
	
	
	public static final Texture[] hullPlayer01Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.HULL_PLAYER_1.getElementStr(),Texture.class),
					ElementEnum.HULL_PLAYER_1.getWidthBef(),
					ElementEnum.HULL_PLAYER_1.getHeightBef(),
					ElementEnum.HULL_PLAYER_1.getWidthShow(),
					ElementEnum.HULL_PLAYER_1.getHeightShow())
	};
	
	public static final Texture[] cannonPlayer01AText = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.GUN_PLAYER_1_A.getElementStr(),Texture.class), 
					ElementEnum.GUN_PLAYER_1_A.getWidthBef(), 
					ElementEnum.GUN_PLAYER_1_A.getHeightBef(), 
					ElementEnum.GUN_PLAYER_1_A.getWidthShow(), 
					ElementEnum.GUN_PLAYER_1_A.getHeightShow())
			
	};
	

	
	public static final Texture[] trackPlayerText = {
		DrawUtils.resizeTexture(
				SecondTestGDX.resources.get(ElementEnum.TRACK_01.getElementStr(),Texture.class),
				ElementEnum.TRACK_01.getWidthBef(),
				ElementEnum.TRACK_01.getHeightBef(),
				ElementEnum.TRACK_01.getWidthShow(),
				ElementEnum.TRACK_01.getHeightShow()),
		
		DrawUtils.resizeTexture(
				SecondTestGDX.resources.get(ElementEnum.TRACK_02.getElementStr(),Texture.class),
				ElementEnum.TRACK_02.getWidthBef(),
				ElementEnum.TRACK_02.getHeightBef(),
				ElementEnum.TRACK_02.getWidthShow(),
				ElementEnum.TRACK_02.getHeightShow())			
		};
	
	
	public static final Texture[] ExhaustPlayerText = {
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_01,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_02,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_03,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_04,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_05,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_06,Texture.class),
		SecondTestGDX.resources.get(SecondTestGDX.resources.exhaustFire_07,Texture.class)
	};

}
