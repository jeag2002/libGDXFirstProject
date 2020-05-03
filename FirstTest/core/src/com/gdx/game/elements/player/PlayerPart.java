package com.gdx.game.elements.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.elements.AnimatedObject;
import com.gdx.game.stages.enums.PlayerMovements;
import com.gdx.game.stages.enums.PlayerPartType;

public class PlayerPart extends AnimatedObject{
	
	private PlayerPartType playerPartType;
	
    private static final int CENTER  = 0;
    private static final int LEFT_1 = 1;
    private static final int LEFT_2 = 2;
    private static final int RIGHT_1 = 3;
    private static final int RIGHT_2 = 4;
	
	
	public PlayerPart(PlayerPartType playerPartType) {
		super();
		this.playerPartType = playerPartType;
	}
	
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}
	
	
	@Override
	public void AnimationByMovement(PlayerMovements movement,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
		
		if (playerPartType.equals(PlayerPartType.SHADOW)) {
			AnimationByMovementSHADOW(movement, moveStepX, moveStepY);
		}else if (playerPartType.equals(PlayerPartType.BOOSTS)) {
			AnimationByMovementBOOST(movement,moveStepX,moveStepY, isAccX, isAccY);
		}else if (playerPartType.equals(PlayerPartType.EXHAUST_UL)) {
			 AnimationByMovementEXHAUST_UL(movement, moveStepX, moveStepY, isAccX, isAccY);
		}else if (playerPartType.equals(PlayerPartType.EXHAUST_UR)) {
			 AnimationByMovementEXHAUST_UR(movement, moveStepX, moveStepY, isAccX,  isAccY);
		}else if (playerPartType.equals(PlayerPartType.EXHAUST_DL)) {
			AnimationByMovementEXHAUST_DL(movement, moveStepX, moveStepY,isAccX, isAccY);
		}else if (playerPartType.equals(PlayerPartType.EXHAUST_DR)) {
			AnimationByMovementEXHAUST_DR(movement, moveStepX, moveStepY,  isAccX, isAccY);
		}
	}
	
	public void AnimationByMovementEXHAUST_DR(PlayerMovements orientation,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
		
		if (orientation.equals(PlayerMovements.UP)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			if (moveStepX <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepX > 350) {
				this.setTextureToSpriteByIndex(1);
			}
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			this.setTextureToSpriteByIndex(2);
		}
	}
	
	
	public void AnimationByMovementEXHAUST_DL(PlayerMovements orientation,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
			
		if (orientation.equals(PlayerMovements.UP)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			if (moveStepX <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepX > 350) {
				this.setTextureToSpriteByIndex(1);
			}
		}
	}
	
	public void AnimationByMovementEXHAUST_UR(PlayerMovements orientation,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
		if (orientation.equals(PlayerMovements.UP)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			if (moveStepY <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepY > 0) && (moveStepY <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepY > 350) {
				this.setTextureToSpriteByIndex(1);
			}			
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			if (moveStepX <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepX > 350) {
				this.setTextureToSpriteByIndex(1);
			}
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			this.setTextureToSpriteByIndex(2);
		}
	}
	
	public void AnimationByMovementEXHAUST_UL(PlayerMovements orientation,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
		if (orientation.equals(PlayerMovements.UP)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			if (moveStepY <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepY > 0) && (moveStepY <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepY > 350) {
				this.setTextureToSpriteByIndex(1);
			}
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			this.setTextureToSpriteByIndex(2);
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			if (moveStepX <= 0) {
				this.setTextureToSpriteByIndex(2);
			}else if ((moveStepX > 0) && (moveStepX <= 350)) {
				this.setTextureToSpriteByIndex(0);
			}else if (moveStepX > 350) {
				this.setTextureToSpriteByIndex(1);
			}
		}
	}
	
	
	
	public void AnimationByMovementBOOST(PlayerMovements orientation,float moveStepX, float moveStepY, boolean isAccX, boolean isAccY) {
		
		if (orientation.equals(PlayerMovements.UP)) {
			
			if (isAccY) {				
				//exhaust
				if (moveStepY <= 0) {
					this.setTextureToSpriteByIndex(6);
				}else if ((moveStepY > 0) && (moveStepY<= 200)) {
					this.setTextureToSpriteByIndex(0);
				}else if ((moveStepY > 200) && (moveStepY<= 400)) {
					this.setTextureToSpriteByIndex(1);
				}else if (moveStepY > 400) {
					this.setTextureToSpriteByIndex(2);
				}
			}else {	
				//exhaust
				if (moveStepY <= 0) {
					this.setTextureToSpriteByIndex(6);
				}else if ((moveStepY > 0) && (moveStepY<= 200)) {
					this.setTextureToSpriteByIndex(5);	
				}else if ((moveStepY > 200) && (moveStepY<= 400)) {
					this.setTextureToSpriteByIndex(4);
				}else if (moveStepY > 400) {
					this.setTextureToSpriteByIndex(3);
				}
			}
		}else {
			this.setTextureToSpriteByIndex(6);
		}
		
	}
	
	
	public void AnimationByMovementSHADOW(PlayerMovements orientation,float moveStepX, float moveStepY) {
		
		if (orientation.equals(PlayerMovements.UP)) {
			super.setTextureToSpriteByIndex(this.CENTER);
		}else if (orientation.equals(PlayerMovements.DOWN)) {
			super.setTextureToSpriteByIndex(this.CENTER);
		}else if (orientation.equals(PlayerMovements.LEFT)) {
			if ((moveStepX < 350) && (moveStepX > 0)) {
				super.setTextureToSpriteByIndex(this.LEFT_1);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				super.setTextureToSpriteByIndex(this.LEFT_2);
			}else if (moveStepX <= 0) {
				super.setTextureToSpriteByIndex(this.CENTER);
			}
		}else if (orientation.equals(PlayerMovements.RIGHT)) {
			if ((moveStepX < 350) && (moveStepX > 0)) {
				super.setTextureToSpriteByIndex(this.RIGHT_1);
			}else if ((moveStepX < 650) && (moveStepX >= 350)) {
				super.setTextureToSpriteByIndex(this.RIGHT_2);
			}else if (moveStepX <= 0) {
				super.setTextureToSpriteByIndex(this.CENTER);
			}
		}
	}
	
	

	@Override
	public void AnimationByTime(float delta) {
	}

	@Override
	public void AnimationLoop(float delta, boolean loop) {
	}

}
