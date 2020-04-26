package com.gdx.game.engine.logic;

import com.gdx.game.elements.SpawnPool;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.utils.StringUtils;

/**
 * Level Definition
 * @author jeag2
 */

public class GameLevelLogic {
	
	//GLOBAL PARAMETERS
	
	public static final String music_menu = "sounds/back_music.ogg";
	public static final String music_level = "sounds/levels/level-1.ogg";
	public static final String sound_intermission = "sfx/voices/goodluck.wav";
	public static final String level = "level 1";
	
	public static final String map_level_1 = "maps/map_1/tile_test_3.tmx";
	
	public static final float speedUpFactor = 1.0f;
	public static final float PIXELS_TO_METERS = 100f;
	
	public static final float timeGapEnemyGeneration = 10.0f;
	
	public static final int NUM_MAX_TYPE_MISSILE_PLAYER = 10; 
	
	private int lifePlayer;
	private int shieldPlayer;
	private int scorePlayer;
	private float time;
	
	private boolean endLevel;
	private boolean gameOver;
	
	private int ammoMissile[];
	
	
	private MissileTypeEnum shootTypePlayer;
	
	public GameLevelLogic() {
		this.lifePlayer = 10;
		this.shieldPlayer = 10;
		this.scorePlayer = 0;
		this.time = 0.0f;
		
		this.endLevel = false;
		this.gameOver = false;
		
		this.ammoMissile = new int[this.NUM_MAX_TYPE_MISSILE_PLAYER];
		for(int i=0;i<this.NUM_MAX_TYPE_MISSILE_PLAYER; i++) {ammoMissile[i] = 1000;}
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

	public float getTime() {
		return time;
	}


	public void setTime(float time) {
		this.time = time;
	}
	
	public MissileTypeEnum getShootTypePlayer() {
		return shootTypePlayer;
	}
	
	public void setShootTypePlayer(MissileTypeEnum shootTypePlayer) {
		this.shootTypePlayer = shootTypePlayer;
	}
	
	public void useShoot() {
		if (shootTypePlayer.getIndex() < 99) {
			this.ammoMissile[shootTypePlayer.getIndex()] = this.ammoMissile[shootTypePlayer.getIndex()] - 1; 
		}
	}
	
	public void addShot(MissileTypeEnum shoot, int add) {
		this.ammoMissile[shootTypePlayer.getIndex()] = this.ammoMissile[shootTypePlayer.getIndex()] + add; 
	}
	
	
	public String getShoot() {
		if (shootTypePlayer.getIndex() < 98) {
			int result = this.ammoMissile[shootTypePlayer.getIndex()];
			return StringUtils.leftPaddedString(8, result);
			
		}else {
			return "INFINITE";
		}
	}
	
	public void processCollision() {
		
		if (shieldPlayer > 0) {
			shieldPlayer--;
		}else {
			if (lifePlayer > 0) {
				lifePlayer--;
			}
		}
	}
	
	public void addLife(int life) {lifePlayer += life;}
	public void addShield(int shield) {shieldPlayer += shield;}
	
	public boolean isEndLevel() {
		return endLevel;
	}

	public void setEndLevel(boolean endLevel) {
		this.endLevel = endLevel;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void dispose() {
		
		this.lifePlayer = 10;
		this.shieldPlayer = 10;
		this.scorePlayer = 0;
		this.time = 0.0f;
		
		this.endLevel = false;
		this.gameOver = false;
		
	}
	

}
