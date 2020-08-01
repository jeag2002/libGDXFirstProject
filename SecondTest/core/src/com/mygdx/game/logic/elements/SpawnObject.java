package com.mygdx.game.logic.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public interface SpawnObject {
	
	void setSpawned(boolean spawned);
	Body getBox2DBody();
	boolean isSpawned();
    void setPool(SpawnPool pool);
    void kill(SpawnPool pool);
    void update(float delta, float boostFactor);
    void draw(SpriteBatch sb);
    

}
