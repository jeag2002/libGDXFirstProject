package com.gdx.game.engine.logic;

import com.gdx.game.elements.SpawnPool;
import com.gdx.game.stages.enums.LaserTypePlayer;
import com.gdx.game.stages.enums.MissileTypeEnum;
import com.gdx.game.utils.StringUtils;

/**
 * Level Definition
 * @author jeag2
 */

public class GameLevelLogic {
	
	//GLOBAL PARAMETERS
	
	public static final String music_menu = "sounds/back_music.ogg";
	
	public static final String sound_intermission = "sfx/voices/goodluck.wav";
	public static final String sound_splash = "sounds/splash.wav";
	public static final String sound_levelcomplete = "sounds/levelcomplete.wav";
	
	public static final String music_final_level = "sounds/endlevel.mp3";
	public static final String music_gameover = "sounds/gameover.mp3";
	
	public static final float speedUpFactor = 1.0f;
	public static final float PIXELS_TO_METERS = 100f;
	
	public static final float timeGapEnemyGeneration = 10.0f;
	
	public static final int NUM_MAX_TYPE_MISSILE_PLAYER = 10; 
	
	public static final int MAX_LIFE = 10;
	public static final int MAX_SHIELD = 10;
	
	private int lifePlayer;
	private int kills;
	private int shieldPlayer;
	private int scorePlayer;
	private float time;
	
	private boolean endLevel;
	private boolean gameOver;
	
	private boolean launchEndLevel;
	private boolean launchGOLevel;
	private boolean launchSoundEndLevel;
	

	private int ammoMissile[];
	
	
	private LaserTypePlayer shootTypePlayer;
	
	public GameLevelLogic() {
		this.lifePlayer = 10;
		this.shieldPlayer = 10;
		this.scorePlayer = 0;
		this.time = 0.0f;
		this.kills = 0;
		
		this.endLevel = false;
		this.gameOver = false;
		
		this.launchEndLevel = (this.endLevel || this.gameOver);
		this.launchGOLevel = (this.endLevel || this.gameOver);
		
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
	
	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
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
	
	public LaserTypePlayer getShootTypePlayer() {
		return shootTypePlayer;
	}
	
	public void setShootTypePlayer(LaserTypePlayer shootTypePlayer) {
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
		
		if (lifePlayer == 0) {
			if (!this.gameOver) {
				this.setGameOver(true);
			}
		}
		
		
		
	}
	
	public void addLife(int life) {lifePlayer += life;}
	public void addShield(int shield) {shieldPlayer += shield;}
	
	
	public int getLife() {return lifePlayer;}
	public int getShield() {return shieldPlayer;}
	
	public boolean isEndLevel() {
		return endLevel;
	}

	public void setEndLevel(boolean endLevel) {
		this.endLevel = endLevel;
		if (this.endLevel) {this.launchEndLevel = true;}
		if ((this.endLevel) && (!this.launchSoundEndLevel)) {this.launchSoundEndLevel = true;}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		if (this.gameOver) {this.launchGOLevel = true;}
	}
	
	public boolean isLaunchGOLevel() {
		return launchGOLevel;
	}

	public void setLaunchGOLevel(boolean launchGOLevel) {
		this.launchGOLevel = launchGOLevel;
	}

	public boolean isLaunchEndLevel() {
		return launchEndLevel;
	}

	public void setLaunchEndLevel(boolean launchEndLevel) {
		this.launchEndLevel = launchEndLevel;
	}
	
	public boolean isLaunchSoundEndLevel() {
		return launchSoundEndLevel;
	}

	public void setLaunchSoundEndLevel(boolean launchSoundEndLevel) {
		this.launchSoundEndLevel = launchSoundEndLevel;
	}

	
	public void nextLevel() {
		
		this.kills = 0;
		this.time = 0.0f;
		
		this.endLevel = false;
		this.gameOver = false;
		
		this.launchEndLevel = false;
		this.launchGOLevel = false;
		this.launchSoundEndLevel = false;
		
	}
	
	public void dispose() {
		
		this.lifePlayer = 10;
		this.shieldPlayer = 10;
		this.scorePlayer = 0;
		this.kills = 0;
		this.time = 0.0f;
		
		this.endLevel = false;
		this.gameOver = false;
		
		this.launchEndLevel = false;
		this.launchGOLevel = false;
		this.launchSoundEndLevel = false;
		
	}
	

}
