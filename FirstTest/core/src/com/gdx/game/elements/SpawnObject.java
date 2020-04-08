package com.gdx.game.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface SpawnObject {
	
    void setSpawned(boolean spawned);
    boolean isSpawned();
    void setPool(SpawnPool pool);
    void kill(SpawnPool pool);
    void update(float delta, float boostFactor);
    void draw(SpriteBatch sb);
    
}
