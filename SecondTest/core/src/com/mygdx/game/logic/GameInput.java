package com.mygdx.game.logic;

import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.SecondTestGDX;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInput implements InputProcessor {
	
	//https://www.reddit.com/r/libgdx/comments/2meqpo/how_do_i_rotate_a_gun_sprite_towards_the_mouse/
	
	private GamePlay gamePlay;
	
	private int oldScreenX;
	private int oldScreenY;
	
	public GameInput(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
		
		this.oldScreenX = 0;
		this.oldScreenY = 0;
		
	}
	

	@Override
	public boolean keyDown(int keycode) {
		
		if (gamePlay.isStart()) {
			
			if ((keycode == Keys.UP) || (keycode == Keys.W)) {
				gamePlay.playerMoveUp();
			}else if ((keycode == Keys.LEFT) || (keycode == Keys.A)) {
				gamePlay.playerMoveLeft();
			}else if ((keycode == Keys.RIGHT) || (keycode == Keys.D)){
				gamePlay.playerMoveRight();
			}else if ((keycode == Keys.DOWN)|| (keycode == Keys.S)) {
				gamePlay.playerMoveDown();
			}else if ((keycode == Keys.CONTROL_LEFT) || (keycode == Keys.ALT_RIGHT)) {
				gamePlay.playerTurretAntiClockWise();
			}else if ((keycode == Keys.CONTROL_RIGHT) || (keycode == Keys.ALT_LEFT)) {
				gamePlay.playerTurretClockWise();
			}else if (keycode == Keys.SPACE) {
				gamePlay.playerShoot();
			}else if ((keycode == Keys.N) && (SecondTestGDX.isDebugKeys)) {
				gamePlay.setNextLevel(true);
			}else if ((keycode == Keys.D) && (SecondTestGDX.isDebugKeys)) {
				gamePlay.setPlayerDied(true);
			}else if ((keycode == Keys.E) && (SecondTestGDX.isDebugKeys)) {
				gamePlay.setEndGame(true);
			}
		}
		
		
		if (keycode == Keys.ESCAPE) {
			Gdx.app.exit();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (gamePlay.isStart()) {
			
			if ((keycode == Keys.UP) || (keycode == Keys.W)){
				gamePlay.playerMoveUp();
			}else if ((keycode == Keys.LEFT) || (keycode == Keys.A)) {
				gamePlay.playerMoveLeft();
			}else if ((keycode == Keys.RIGHT) || (keycode == Keys.D)) {
				gamePlay.playerMoveRight();
			}else if ((keycode == Keys.DOWN)|| (keycode == Keys.S)){
				gamePlay.playerMoveDown();
			}else if ((keycode == Keys.CONTROL_LEFT) || (keycode == Keys.ALT_RIGHT)) {
				gamePlay.playerTurretAntiClockWise();
			}else if ((keycode == Keys.CONTROL_RIGHT) || (keycode == Keys.ALT_LEFT)) {
				gamePlay.playerTurretClockWise();
			}else if (keycode == Keys.SPACE) {
				gamePlay.playerShoot();
			}else if (keycode == Keys.P) {
				gamePlay.setNextLevel(true);
			}
			
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (gamePlay.isStart()) {
			if (button == Input.Buttons.LEFT) {
				gamePlay.playerShoot();
			}
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (gamePlay.isStart()) {
			
			if ((this.oldScreenX != screenX) || (this.oldScreenY != screenY)) {
				this.oldScreenX = screenX;
				this.oldScreenY = screenY;
				gamePlay.playerMouseMoved();
			}
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
