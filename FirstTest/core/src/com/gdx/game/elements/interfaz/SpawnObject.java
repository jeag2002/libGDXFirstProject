package com.gdx.game.elements.interfaz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.elements.SpawnPool;

public interface SpawnObject {
	
    void setSpawned(boolean spawned);
    boolean isSpawned();
    void setPool(SpawnPool pool);
    void kill(SpawnPool pool);
    void update(float delta, float boostFactor);
    void draw(SpriteBatch sb);
    
}
