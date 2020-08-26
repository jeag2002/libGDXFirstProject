package com.mygdx.game.elements.explosions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.elements.players.CollisionPlayerObject;
import com.mygdx.game.enums.SpawnType;
import com.mygdx.game.logic.elements.SpawnObject;
import com.mygdx.game.logic.elements.SpawnPool;
import com.mygdx.game.screens.GamePlayScreen;
import com.mygdx.game.utils.GUID;

import box2dLight.PointLight;
import box2dLight.RayHandler;

//https://github.com/julienvillegas/libGDX.info-Box2DLight_Basic/blob/master/core/src/com/mygdx/game/FireEmitter.java

public class SimpleExplosion extends CollisionPlayerObject implements SpawnObject {
	
	private GamePlayScreen gPS;
	private SpawnType type;
	private SpawnType subType;
	private boolean spawned;
	private SpawnPool pool;
	private PointLight light;
	
	
	private String idCode;
	
	private ParticleEffect pe;
	
	public SimpleExplosion(SpawnType type, SpawnPool spawnPool, World world, GamePlayScreen gPS) {
		
		super(world, type, GUID.get());
		this.type = type;
		this.pool = spawnPool;
		this.gPS = gPS;
		this.pe = new ParticleEffect();
	}
	
	

	@Override
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	
	public void init(RayHandler rayHandler, SpawnType subType, float xStart, float yStart, float width, float height) {
		
		this.subType = subType;
		
		if (subType.equals(SpawnType.Simple_Explosion)) {
		   pe.load(Gdx.files.internal("elements/explosions/particles/SimpleExplosion.p"),Gdx.files.internal("elements/explosions/particles"));
		   pe.getEmitters().first().setPosition(xStart + width/2,yStart + height/2);
		   pe.scaleEffect(0.40f, 0.40f);
		   pe.start();
		}
		
		createCollisionObject(xStart,yStart,64,64,BodyType.StaticBody,true);
		this.light = new PointLight(rayHandler, 50, Color.WHITE, 1, 0, 0);
		this.light.setSoftnessLength(0f);
		this.light.attachToBody(super.getBody());
	}
	

	@Override
	public boolean isSpawned() {
		return this.spawned;
	}

	@Override
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}

	@Override
	public void kill(SpawnPool pool) {
		dispose();
	}

	@Override
	public void update(float delta, float boostFactor) {
		pe.update(delta);
		if (pe.isComplete()) {
			gPS.getGamePlay().getGameLogic().getSpawnPool().getDeletedBodiesWithCollision().add(this);
		}
	}

	@Override
	public void draw(SpriteBatch sb) {
		pe.draw(sb);
	}



	@Override
	public Body getBox2DBody() {
		return null;
	}
	
	
	public void dispose() {
		this.light.remove();
		this.pe.dispose();
	}



	@Override
	public SpawnType getType() {
		return this.type;
	}


	@Override
	public SpawnType getSubType() {
		return this.subType;
	}
	
}

/*
 float pScale = 0.2f;

 float scaling = pe.getEmitters().get(0).getScale().getHighMax();
 pe.getEmitters().get(0).getScale().setHigh(scaling * pScale);

 scaling = pe.getEmitters().get(0).getScale().getLowMax();
 pe.getEmitters().get(0).getScale().setLow(scaling * pScale);

 scaling = pe.getEmitters().get(0).getVelocity().getHighMax();
 pe.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

 scaling = pe.getEmitters().get(0).getVelocity().getLowMax();
 pe.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
 */

