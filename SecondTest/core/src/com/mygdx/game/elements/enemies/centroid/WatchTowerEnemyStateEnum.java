package com.mygdx.game.elements.enemies.centroid;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.utils.DFUtils;


public enum WatchTowerEnemyStateEnum implements State<WatchTowerEnemy>{
	
	SLEEP(){
		@Override
		public void enter(WatchTowerEnemy enemy) {
			
		}
		
		@Override
		public void update(WatchTowerEnemy enemy) {
			
			
			float posEnemyX = enemy.getX();
			float posEnemyY = enemy.getY();
			
			float posEnemyWidth = enemy.getWidth();
			float posEnemyHeight = enemy.getHeight();
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX();
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY();
			
			float posPlayerWidth = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getWidth();
			float posPlayerHeight = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getHeight();
		
			double distance = DFUtils.distance(
					posEnemyX+posEnemyWidth/2, 
					posEnemyY+posEnemyHeight/2, 
					posPlayerX + posPlayerWidth/2, 
					posPlayerY + posPlayerHeight/2);
			
			
			if (distance <= GameLogicInformation.ATTACK_DISTANCE_WATCHTOWER) {
				enemy.getStateMachine().changeState(ATTACK);
				enemy.setShootingActive(true);
			}
			
			
		}
		
		@Override
		public void exit(WatchTowerEnemy enemy) {
			
		}

		@Override
		public boolean onMessage(WatchTowerEnemy entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	},
	
	
	ATTACK(){
		
		@Override
		public void enter(WatchTowerEnemy enemy) {
			
		}
		
		@Override
		public void update(WatchTowerEnemy enemy) {
			
			
			float posEnemyX = enemy.getX();
			float posEnemyY = enemy.getY();
			
			float posEnemyWidth = enemy.getWidth();
			float posEnemyHeight = enemy.getHeight();
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX();
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY();
			
			float posPlayerWidth = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getWidth();
			float posPlayerHeight = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getHeight();
			
			
			double distance = DFUtils.distance(
					posEnemyX+posEnemyWidth/2, 
					posEnemyY+posEnemyHeight/2, 
					posPlayerX + posPlayerWidth/2, 
					posPlayerY + posPlayerHeight/2);
			
			if (distance > GameLogicInformation.ATTACK_DISTANCE_WATCHTOWER){
				enemy.getStateMachine().changeState(SLEEP);
				enemy.setShootingActive(false);
			}
			
		}
		
		@Override
		public void exit(WatchTowerEnemy entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onMessage(WatchTowerEnemy entity, Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
		
	};
	

	@Override
	public boolean onMessage(WatchTowerEnemy entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
	

	

	
	
}
