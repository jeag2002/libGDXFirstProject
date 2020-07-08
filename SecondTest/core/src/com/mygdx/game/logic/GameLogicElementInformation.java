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
		
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_01.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_01.getWidthBef(),
					ElementEnum.EXHAUST_01.getHeightBef(),
					ElementEnum.EXHAUST_01.getWidthShow(),
					ElementEnum.EXHAUST_01.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_02.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_02.getWidthBef(),
					ElementEnum.EXHAUST_02.getHeightBef(),
					ElementEnum.EXHAUST_02.getWidthShow(),
					ElementEnum.EXHAUST_02.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_03.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_03.getWidthBef(),
					ElementEnum.EXHAUST_03.getHeightBef(),
					ElementEnum.EXHAUST_03.getWidthShow(),
					ElementEnum.EXHAUST_03.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_04.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_04.getWidthBef(),
					ElementEnum.EXHAUST_04.getHeightBef(),
					ElementEnum.EXHAUST_04.getWidthShow(),
					ElementEnum.EXHAUST_04.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_05.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_05.getWidthBef(),
					ElementEnum.EXHAUST_05.getHeightBef(),
					ElementEnum.EXHAUST_05.getWidthShow(),
					ElementEnum.EXHAUST_05.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_06.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_06.getWidthBef(),
					ElementEnum.EXHAUST_06.getHeightBef(),
					ElementEnum.EXHAUST_06.getWidthShow(),
					ElementEnum.EXHAUST_06.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.EXHAUST_07.getElementStr(),Texture.class),
					ElementEnum.EXHAUST_07.getWidthBef(),
					ElementEnum.EXHAUST_07.getHeightBef(),
					ElementEnum.EXHAUST_07.getWidthShow(),
					ElementEnum.EXHAUST_07.getHeightShow())
	};
	
	
	public static final Texture[] Enemy_01_LeftWing = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_LEFT_WING.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_LEFT_WING.getWidthBef(),
					ElementEnum.ENEMY_1_LEFT_WING.getHeightBef(),
					ElementEnum.ENEMY_1_LEFT_WING.getWidthShow(),
					ElementEnum.ENEMY_1_LEFT_WING.getHeightShow())	
	};
	
	public static final Texture[] Enemy_01_RightWing = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_RIGHT_WING.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_RIGHT_WING.getWidthBef(),
					ElementEnum.ENEMY_1_RIGHT_WING.getHeightBef(),
					ElementEnum.ENEMY_1_RIGHT_WING.getWidthShow(),
					ElementEnum.ENEMY_1_RIGHT_WING.getHeightShow())	
	};
	
	public static final Texture[] Enemy_01_Body = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_BODY.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_BODY.getWidthBef(),
					ElementEnum.ENEMY_1_BODY.getHeightBef(),
					ElementEnum.ENEMY_1_BODY.getWidthShow(),
					ElementEnum.ENEMY_1_BODY.getHeightShow())
	};


	
	
	public static final Texture[] Enemy_01_Wander = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_01.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_01.getWidthBef(),
					ElementEnum.ENEMY_1_01.getHeightBef(),
					ElementEnum.ENEMY_1_01.getWidthShow(),
					ElementEnum.ENEMY_1_01.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_02.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_02.getWidthBef(),
					ElementEnum.ENEMY_1_02.getHeightBef(),
					ElementEnum.ENEMY_1_02.getWidthShow(),
					ElementEnum.ENEMY_1_02.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_03.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_03.getWidthBef(),
					ElementEnum.ENEMY_1_03.getHeightBef(),
					ElementEnum.ENEMY_1_03.getWidthShow(),
					ElementEnum.ENEMY_1_03.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_04.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_04.getWidthBef(),
					ElementEnum.ENEMY_1_04.getHeightBef(),
					ElementEnum.ENEMY_1_04.getWidthShow(),
					ElementEnum.ENEMY_1_04.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_05.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_05.getWidthBef(),
					ElementEnum.ENEMY_1_05.getHeightBef(),
					ElementEnum.ENEMY_1_05.getWidthShow(),
					ElementEnum.ENEMY_1_05.getHeightShow()),
		
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_06.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_06.getWidthBef(),
					ElementEnum.ENEMY_1_06.getHeightBef(),
					ElementEnum.ENEMY_1_06.getWidthShow(),
					ElementEnum.ENEMY_1_06.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_07.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_07.getWidthBef(),
					ElementEnum.ENEMY_1_07.getHeightBef(),
					ElementEnum.ENEMY_1_07.getWidthShow(),
					ElementEnum.ENEMY_1_07.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_08.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_08.getWidthBef(),
					ElementEnum.ENEMY_1_08.getHeightBef(),
					ElementEnum.ENEMY_1_08.getWidthShow(),
					ElementEnum.ENEMY_1_08.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_09.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_09.getWidthBef(),
					ElementEnum.ENEMY_1_09.getHeightBef(),
					ElementEnum.ENEMY_1_09.getWidthShow(),
					ElementEnum.ENEMY_1_09.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_1_0A.getElementStr(),Texture.class),
					ElementEnum.ENEMY_1_0A.getWidthBef(),
					ElementEnum.ENEMY_1_0A.getHeightBef(),
					ElementEnum.ENEMY_1_0A.getWidthShow(),
					ElementEnum.ENEMY_1_0A.getHeightShow())
	
	};
	
	
	
	
	
	

}
