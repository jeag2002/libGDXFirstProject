package com.gdx.game.elements.enemies.turrets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gdx.game.FirstTestGDX;
import com.gdx.game.elements.DynamicCollObject;
import com.gdx.game.elements.SpawnPool;
import com.gdx.game.elements.interfaz.SpawnObject;
import com.gdx.game.engine.logic.GameElementLogic;
import com.gdx.game.engine.logic.GameLevelLogic;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.SpawnType;

public class Mine extends DynamicCollObject implements SpawnObject{
	
	
	private static final int POSITION_1 = 0;
	private static final int POSITION_2 = 1;
	private static final int POSITION_3 = 2;
	private static final int POSITION_4 = 3;
	
	private SpawnType type;
	private boolean spawned;
	private Texture[] mine;
	private Texture label;
	private Sprite base;
	private Sprite body;
	private float angle;
	private SpawnPool pool;
	
    private Vector2 position = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movement = new Vector2();
    
    private static final float bgSpeed = 50.0f;
    
    private float timer;
    
    private int actPosition;
    
	
	public Mine(SpawnType type, World world) {
		super(world);
		this.type = type;
	}
	
	
	public void init(float xStart, float yStart, float angle) {
		
		setReference(this);
		
		this.angle = angle;
		
		position.set(xStart, yStart);
	    direction.set((float)Math.cos(Math.toRadians(angle)), (float)Math.sin(Math.toRadians(angle))).nor();
		
		mine = new Texture[1];
		mine[0] = FirstTestGDX.resources.get(FirstTestGDX.resources.imgExhaustFrame_07,Texture.class); 
		
		label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMine_01,Texture.class);
		base = new Sprite(label);
		
		label = FirstTestGDX.resources.get(FirstTestGDX.resources.imgMine_02,Texture.class);
		body = new Sprite(label);
		
		this.timer = 0;
		
		
		super.init(mine,0);
		super.setPosition(xStart, yStart);
		super.setSize(64, 64);
		super.setSpeed(0, 0);
		super.createCollisionObject(getX(),getY(),getWidth(),getHeight(),BodyType.DynamicBody);
		
		base.setOriginCenter();
        base.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 - 10);
        
        body.setOriginCenter();
        body.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 - 10);
        
        this.actPosition = POSITION_1;
        
	}
	
	
	
	public void setPool(SpawnPool pool) {
		this.pool = pool;
	}
	
	@Override
	public void setSpawned(boolean spawned) {	
		this.spawned = spawned;
	}

	@Override
	public boolean isSpawned() {
		return this.spawned;
	}

	@Override
	public void kill(SpawnPool pool) {
		pool.returnToPool(this);
		super.setPosition(FirstTestGDX.screenWidth, 0);
	}
	

	@Override
	public void update(float delta, float boostFactor) {
		
		AnimationLoop(delta, true);
		
		if (isSpawned()) {	
			
			movement.set(direction).scl(GameLevelLogic.speedUpFactor * bgSpeed * delta* (-1));
	        position.add(movement);
	        
	        super.setPosition(position.x, position.y);
	        super.setCollisionRef(position.x, position.y);
			
	        if (getX() < 0 || getY() < 0) {
    			if (!GameElementLogic.toDeletedBodiesWithCollision.contains(this.getBody())) {
    				GameElementLogic.toDeletedBodiesWithCollision.add(this.getBody());
    			}
	        } 
		}
	}

	@Override
	public void AnimationByMovement(PlayerMovements movement, float moveStepX, float moveStepY, boolean isAccX,
			boolean isAccY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationByTime(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
		
		timer += delta;
	    if (timer >= 0.05f) {
	    	timer = 0.0f;
	    	
	    	if (this.actPosition == POSITION_1) {
	    		
	    		base.setOriginCenter();
	            base.setOriginBasedPosition(getX() + getWidth()/2 + 10, getY() + getHeight() / 2 - 10);
	            body.setOriginCenter();
	            body.setOriginBasedPosition(getX() + getWidth()/2 + 10, getY() + getHeight() / 2 - 10);
	            this.actPosition = POSITION_2;
	    		
	    	}else if (this.actPosition == POSITION_2) {
	    		
	    		base.setOriginCenter();
	            base.setOriginBasedPosition(getX() + getWidth()/2 + 10, getY() + getHeight() / 2 + 10);
	            body.setOriginCenter();
	            body.setOriginBasedPosition(getX() + getWidth()/2 + 10, getY() + getHeight() / 2 + 10);
	            this.actPosition = POSITION_3;
	    		
	    		
	    	}else if (this.actPosition == POSITION_3) {
	    		
	    		base.setOriginCenter();
	            base.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 + 10);
	            body.setOriginCenter();
	            body.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 + 10);
	            this.actPosition = POSITION_4;
	    		
	    		
	    		
	    	}else if (this.actPosition == POSITION_4) {
	    		
	    		base.setOriginCenter();
	            base.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 - 10);
	            body.setOriginCenter();
	            body.setOriginBasedPosition(getX() + getWidth()/2 - 10, getY() + getHeight() / 2 - 10);
	            this.actPosition = POSITION_1;
	    		
	    	}
	    }
		
	}
	
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
		base.draw(sb);
		body.draw(sb);
	}

	

}
