package com.gdx.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.gdx.game.screens.GamePlayScreen;

public class GameInput implements InputProcessor{
	
	private GamePlay gamePlay;
	
	public GameInput(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
	}

	@Override
	public boolean keyDown(int keycode) {
		
		if (gamePlay.isStarted()) {
			if ((keycode == Keys.W) || (keycode == Keys.UP)) {
				gamePlay.playerMoveUp();
			}else if ((keycode == Keys.A) || (keycode == Keys.LEFT)) {
				gamePlay.playerMoveLeft();
			}else if ((keycode == Keys.D) || (keycode == Keys.RIGHT)) {
				gamePlay.playerMoveRight();
			}else if ((keycode == Keys.S) || (keycode == Keys.DOWN)) {
				gamePlay.playerMoveDown();
			}else if (keycode == Keys.SPACE) {
				gamePlay.playerShoot();
			}
		}
		if (keycode == Keys.ESCAPE) {
			   Gdx.app.exit();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (gamePlay.isStarted()) {
			if ((keycode == Keys.W) || (keycode == Keys.UP)) {
				gamePlay.playerMoveUp();
			}else if ((keycode == Keys.A) || (keycode == Keys.LEFT)) {
				gamePlay.playerMoveLeft();
			}else if ((keycode == Keys.D) || (keycode == Keys.RIGHT)) {
				gamePlay.playerMoveRight();
			}else if ((keycode == Keys.S) || (keycode == Keys.DOWN)) {
				gamePlay.playerMoveDown();
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
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	
	
	
	
}
