package com.gdx.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.gdx.game.screens.GamePlayScreen;

public class GameInput implements InputProcessor{
	
	private GamePlayScreen gamePlayScreen;
	private GamePlay gamePlay;
	
	public GameInput(GamePlayScreen screen, GamePlay gamePlay) {
		this.gamePlayScreen = screen;
		this.gamePlay = gamePlay;
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (keycode == Keys.SPACE
				|| keycode == Keys.A
				|| keycode == Keys.UP) {
				gamePlayScreen.startGame(gamePlay.isGameover());
		}else if (keycode == Keys.ESCAPE) {
				Gdx.app.exit();
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
