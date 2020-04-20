package com.gdx.game.engine.logic;

import com.gdx.game.elements.SpawnPool;

/**
 * Level Definition
 * @author jeag2
 */

public class GameLevelLogic {
	
	
	public static final String music_menu = "sounds/back_music.ogg";
	public static final String music_level = "sounds/levels/level-1.ogg";
	public static final String sound_intermission = "sfx/voices/goodluck.wav";
	public static final String level = "level 1";
	
	public static final float speedUpFactor = 1.0f;
	public static final float PIXELS_TO_METERS = 100f;
	
	public static final float timeGapEnemyGeneration = 10.0f;
	
	private int lifePlayer;
	private int shieldPlayer;
	private int scorePlayer;
	
	public GameLevelLogic() {
		
		this.lifePlayer = 100;
		this.shieldPlayer = 100;
		this.scorePlayer = 0;
	}
	
	
	public int getLifePlayer() {
		return lifePlayer;
	}

	public void setLifePlayer(int lifePlayer) {
		this.lifePlayer = lifePlayer;
	}

	public int getShieldPlayer() {
		return shieldPlayer;
	}

	public void setShieldPlayer(int shieldPlayer) {
		this.shieldPlayer = shieldPlayer;
	}

	public int getScorePlayer() {
		return scorePlayer;
	}

	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}

	
	
	

}
