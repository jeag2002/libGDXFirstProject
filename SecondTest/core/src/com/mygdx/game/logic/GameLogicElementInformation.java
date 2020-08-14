package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.enums.ElementEnum;
import com.mygdx.game.utils.DrawUtils;

public class GameLogicElementInformation {
	
	
	public static final Texture[] hullEnemy02Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.HULL_ENEMY2.getElementStr(),Texture.class),
					ElementEnum.HULL_ENEMY2.getWidthBef(),
					ElementEnum.HULL_ENEMY2.getHeightBef(),
					ElementEnum.HULL_ENEMY2.getWidthShow(),
					ElementEnum.HULL_ENEMY2.getHeightShow())
	};
	
	public static final Texture[] cannonEnemy02Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.GUN_ENEMY2.getElementStr(),Texture.class), 
					ElementEnum.GUN_ENEMY2.getWidthBef(), 
					ElementEnum.GUN_ENEMY2.getHeightBef(), 
					ElementEnum.GUN_ENEMY2.getWidthShow(), 
					ElementEnum.GUN_ENEMY2.getHeightShow())		
	};
	
	public static final Texture[] hullEnemy02_1Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.HULL_ENEMY3.getElementStr(),Texture.class),
					ElementEnum.HULL_ENEMY3.getWidthBef(),
					ElementEnum.HULL_ENEMY3.getHeightBef(),
					ElementEnum.HULL_ENEMY3.getWidthShow(),
					ElementEnum.HULL_ENEMY3.getHeightShow())
	};
	
	public static final Texture[] cannonEnemy02_1Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.GUN_ENEMY3.getElementStr(),Texture.class), 
					ElementEnum.GUN_ENEMY3.getWidthBef(), 
					ElementEnum.GUN_ENEMY3.getHeightBef(), 
					ElementEnum.GUN_ENEMY3.getWidthShow(), 
					ElementEnum.GUN_ENEMY3.getHeightShow())		
	};
	
	
	public static final Texture[] hullEnemy02_2Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.HULL_ENEMY4.getElementStr(),Texture.class),
					ElementEnum.HULL_ENEMY4.getWidthBef(),
					ElementEnum.HULL_ENEMY4.getHeightBef(),
					ElementEnum.HULL_ENEMY4.getWidthShow(),
					ElementEnum.HULL_ENEMY4.getHeightShow())
	};
	
	public static final Texture[] cannonEnemy02_2Text = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.GUN_ENEMY4.getElementStr(),Texture.class), 
					ElementEnum.GUN_ENEMY4.getWidthBef(), 
					ElementEnum.GUN_ENEMY4.getHeightBef(), 
					ElementEnum.GUN_ENEMY4.getWidthShow(), 
					ElementEnum.GUN_ENEMY4.getHeightShow())		
	};
	
	
	
	
	public static final Texture[] trackEnemyText = {
		DrawUtils.resizeTexture(
				SecondTestGDX.resources.get(ElementEnum.TRACK_03.getElementStr(),Texture.class),
				ElementEnum.TRACK_03.getWidthBef(),
				ElementEnum.TRACK_03.getHeightBef(),
				ElementEnum.TRACK_03.getWidthShow(),
				ElementEnum.TRACK_03.getHeightShow()),
		
		DrawUtils.resizeTexture(
				SecondTestGDX.resources.get(ElementEnum.TRACK_04.getElementStr(),Texture.class),
				ElementEnum.TRACK_04.getWidthBef(),
				ElementEnum.TRACK_04.getHeightBef(),
				ElementEnum.TRACK_04.getWidthShow(),
				ElementEnum.TRACK_04.getHeightShow())			
		};
	
	
	public static final Texture[] trackEnemyText_1 = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.TRACK_05.getElementStr(),Texture.class),
					ElementEnum.TRACK_05.getWidthBef(),
					ElementEnum.TRACK_05.getHeightBef(),
					ElementEnum.TRACK_05.getWidthShow(),
					ElementEnum.TRACK_05.getHeightShow()),
			
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.TRACK_06.getElementStr(),Texture.class),
					ElementEnum.TRACK_06.getWidthBef(),
					ElementEnum.TRACK_06.getHeightBef(),
					ElementEnum.TRACK_06.getWidthShow(),
					ElementEnum.TRACK_06.getHeightShow())			
			};
	
	
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
	
	
	public static final Texture[] Enemy_04 = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_01.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_01.getWidthBef(),
					ElementEnum.ENEMY_4_01.getHeightBef(),
					ElementEnum.ENEMY_4_01.getWidthShow(),
					ElementEnum.ENEMY_4_01.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_02.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_02.getWidthBef(),
					ElementEnum.ENEMY_4_02.getHeightBef(),
					ElementEnum.ENEMY_4_02.getWidthShow(),
					ElementEnum.ENEMY_4_02.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_03.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_03.getWidthBef(),
					ElementEnum.ENEMY_4_03.getHeightBef(),
					ElementEnum.ENEMY_4_03.getWidthShow(),
					ElementEnum.ENEMY_4_03.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_04.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_04.getWidthBef(),
					ElementEnum.ENEMY_4_04.getHeightBef(),
					ElementEnum.ENEMY_4_04.getWidthShow(),
					ElementEnum.ENEMY_4_04.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_05.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_05.getWidthBef(),
					ElementEnum.ENEMY_4_05.getHeightBef(),
					ElementEnum.ENEMY_4_05.getWidthShow(),
					ElementEnum.ENEMY_4_05.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_06.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_06.getWidthBef(),
					ElementEnum.ENEMY_4_06.getHeightBef(),
					ElementEnum.ENEMY_4_06.getWidthShow(),
					ElementEnum.ENEMY_4_06.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_07.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_07.getWidthBef(),
					ElementEnum.ENEMY_4_07.getHeightBef(),
					ElementEnum.ENEMY_4_07.getWidthShow(),
					ElementEnum.ENEMY_4_07.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_08.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_08.getWidthBef(),
					ElementEnum.ENEMY_4_08.getHeightBef(),
					ElementEnum.ENEMY_4_08.getWidthShow(),
					ElementEnum.ENEMY_4_08.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_09.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_09.getWidthBef(),
					ElementEnum.ENEMY_4_09.getHeightBef(),
					ElementEnum.ENEMY_4_09.getWidthShow(),
					ElementEnum.ENEMY_4_09.getHeightShow()),		
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_4_0A.getElementStr(),Texture.class),
					ElementEnum.ENEMY_4_0A.getWidthBef(),
					ElementEnum.ENEMY_4_0A.getHeightBef(),
					ElementEnum.ENEMY_4_0A.getWidthShow(),
					ElementEnum.ENEMY_4_0A.getHeightShow())
	
	};
	
	
	
	
	
	public static final Texture[] Enemy_03 = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_01.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_01.getWidthBef(),
					ElementEnum.ENEMY_3_01.getHeightBef(),
					ElementEnum.ENEMY_3_01.getWidthShow(),
					ElementEnum.ENEMY_3_01.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_02.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_02.getWidthBef(),
					ElementEnum.ENEMY_3_02.getHeightBef(),
					ElementEnum.ENEMY_3_02.getWidthShow(),
					ElementEnum.ENEMY_3_02.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_03.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_03.getWidthBef(),
					ElementEnum.ENEMY_3_03.getHeightBef(),
					ElementEnum.ENEMY_3_03.getWidthShow(),
					ElementEnum.ENEMY_3_03.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_04.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_04.getWidthBef(),
					ElementEnum.ENEMY_3_04.getHeightBef(),
					ElementEnum.ENEMY_3_04.getWidthShow(),
					ElementEnum.ENEMY_3_04.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_05.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_05.getWidthBef(),
					ElementEnum.ENEMY_3_05.getHeightBef(),
					ElementEnum.ENEMY_3_05.getWidthShow(),
					ElementEnum.ENEMY_3_05.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_06.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_06.getWidthBef(),
					ElementEnum.ENEMY_3_06.getHeightBef(),
					ElementEnum.ENEMY_3_06.getWidthShow(),
					ElementEnum.ENEMY_3_06.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_07.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_07.getWidthBef(),
					ElementEnum.ENEMY_3_07.getHeightBef(),
					ElementEnum.ENEMY_3_07.getWidthShow(),
					ElementEnum.ENEMY_3_07.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_08.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_08.getWidthBef(),
					ElementEnum.ENEMY_3_08.getHeightBef(),
					ElementEnum.ENEMY_3_08.getWidthShow(),
					ElementEnum.ENEMY_3_08.getHeightShow()),
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_09.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_09.getWidthBef(),
					ElementEnum.ENEMY_3_09.getHeightBef(),
					ElementEnum.ENEMY_3_09.getWidthShow(),
					ElementEnum.ENEMY_3_09.getHeightShow()),		
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.ENEMY_3_0A.getElementStr(),Texture.class),
					ElementEnum.ENEMY_3_0A.getWidthBef(),
					ElementEnum.ENEMY_3_0A.getHeightBef(),
					ElementEnum.ENEMY_3_0A.getWidthShow(),
					ElementEnum.ENEMY_3_0A.getHeightShow())
	
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
	
	
	public static final Texture[] platform = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PLATFORM_01.getElementStr(),Texture.class),
					ElementEnum.PLATFORM_01.getWidthBef(),
					ElementEnum.PLATFORM_01.getHeightBef(),
					ElementEnum.PLATFORM_01.getWidthShow(),
					ElementEnum.PLATFORM_01.getHeightShow())
	};
	
	public static final Texture[] dot_red = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PLATFORM_DOT_R.getElementStr(),Texture.class),
					ElementEnum.PLATFORM_DOT_R.getWidthBef(),
					ElementEnum.PLATFORM_DOT_R.getHeightBef(),
					ElementEnum.PLATFORM_DOT_R.getWidthShow(),
					ElementEnum.PLATFORM_DOT_R.getHeightShow())
	};
	
	public static final Texture[] dot_blue = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PLATFORM_DOT_B.getElementStr(),Texture.class),
					ElementEnum.PLATFORM_DOT_B.getWidthBef(),
					ElementEnum.PLATFORM_DOT_B.getHeightBef(),
					ElementEnum.PLATFORM_DOT_B.getWidthShow(),
					ElementEnum.PLATFORM_DOT_B.getHeightShow())
	};
	
	public static final Texture[] dot_end = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PLATFORM_DOT_E.getElementStr(),Texture.class),
					ElementEnum.PLATFORM_DOT_E.getWidthBef(),
					ElementEnum.PLATFORM_DOT_E.getHeightBef(),
					ElementEnum.PLATFORM_DOT_E.getWidthShow(),
					ElementEnum.PLATFORM_DOT_E.getHeightShow())
	};
	
	
	public static final Texture[] laser = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.LASER.getElementStr(),Texture.class),
					ElementEnum.LASER.getWidthBef(),
					ElementEnum.LASER.getHeightBef(),
					ElementEnum.LASER.getWidthShow(),
					ElementEnum.LASER.getHeightShow())
	};
	
	public static final Texture[] plasma = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PLASMA.getElementStr(),Texture.class),
					ElementEnum.PLASMA.getWidthBef(),
					ElementEnum.PLASMA.getHeightBef(),
					ElementEnum.PLASMA.getWidthShow(),
					ElementEnum.PLASMA.getHeightShow())
	};
	
	public static final Texture[] pulse = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.PULSE.getElementStr(),Texture.class),
					ElementEnum.PULSE.getWidthBef(),
					ElementEnum.PULSE.getHeightBef(),
					ElementEnum.PULSE.getWidthShow(),
					ElementEnum.PULSE.getHeightShow())
	};
	
	public static final Texture[] missile = {
			DrawUtils.resizeTexture(
					SecondTestGDX.resources.get(ElementEnum.MISSILE.getElementStr(),Texture.class),
					ElementEnum.MISSILE.getWidthBef(),
					ElementEnum.MISSILE.getHeightBef(),
					ElementEnum.MISSILE.getWidthShow(),
					ElementEnum.MISSILE.getHeightShow())
			
	};

}
