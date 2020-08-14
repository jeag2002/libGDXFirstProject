package com.mygdx.game.elements.enemies.special.tanks;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;
import com.mygdx.game.elements.enemies.drons.SimpleEnemy;
import com.mygdx.game.utils.NewItem;

//MOVE --> MOVE TANK
//ATTACK --> STOP & ATTACK PLAYER
//STOP


public enum TankEnemyStateEnum implements State<TankEnemy>{
	
	

	
	MOVE(){
		
		private static final float DST_EXIT = 150.0f;
		private static final float DST_PLAYER = 300.0f;
		
		@Override
		public void enter(TankEnemy enemy) {
		}
		
		@Override
		public void update(TankEnemy enemy) {
			
			NewItem objective = enemy.getObjective();
			float centerEnemyX = enemy.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerEnemyY = enemy.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float centerObjetiveX = objective.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerObjetiveY = objective.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX() + SecondTestGDX.tilePlayerWidth_TL/2;
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY() + SecondTestGDX.tilePlayerHeight_TL/2;
			
			
			if (enemy.getPath().size == 0) {
				enemy.getStateMachine().changeState(STOP);
			}else {
				float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
				if (dst <= DST_EXIT) {
					System.out.println("TANK " + enemy.getIdCode() + " ARRIVE TO DESTINATION. CHANGE TO STOP");
					enemy.getStateMachine().changeState(STOP);
				}
			}	
			
			
			float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
			
			if (dstPlayer <= DST_PLAYER) {
				enemy.getStateMachine().changeState(ATTACK);
			}
					
		}
		
		@Override
		public void exit(TankEnemy enemy) {
		}

		@Override
		public boolean onMessage(TankEnemy entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	ATTACK(){
		
		private static final float DST_EXIT = 100.0f;
		private static final float DST_PLAYER = 500.0f;
		
		@Override
		public void enter(TankEnemy enemy) {
		}
		
		@Override
		public void update(TankEnemy enemy) {
			
			NewItem objective = enemy.getObjective();
			float centerEnemyX = enemy.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerEnemyY = enemy.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float centerObjetiveX = objective.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerObjetiveY = objective.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX() + SecondTestGDX.tilePlayerWidth_TL/2;
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY() + SecondTestGDX.tilePlayerHeight_TL/2;
			
			float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
			
			if (dstPlayer > DST_PLAYER) {
				
				float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
				if (dst < DST_EXIT) {
					enemy.getStateMachine().changeState(STOP);
				}else {
					enemy.getStateMachine().changeState(MOVE);
				}
			}
			
			
			
			
		}
		
		@Override
		public void exit(TankEnemy enemy) {
		}

		@Override
		public boolean onMessage(TankEnemy entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	STOP(){
		
		private static final float DST_EXIT = 100.0f;
		private static final float DST_PLAYER = 500.0f;
		
		
		@Override
		public void enter(TankEnemy enemy) {
		}
		
		@Override
		public void update(TankEnemy enemy) {
			
			float centerEnemyX = enemy.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerEnemyY = enemy.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX() + SecondTestGDX.tilePlayerWidth_TL/2;
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY() + SecondTestGDX.tilePlayerHeight_TL/2;
			
			float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
			
			if (dstPlayer <= DST_PLAYER) {
				enemy.getStateMachine().changeState(ATTACK);
			}
		}
		
		@Override
		public void exit(TankEnemy enemy) {
		}

		@Override
		public boolean onMessage(TankEnemy entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	
}
