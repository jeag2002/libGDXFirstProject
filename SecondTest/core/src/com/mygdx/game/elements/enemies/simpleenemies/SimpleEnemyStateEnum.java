package com.mygdx.game.elements.enemies.simpleenemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.utils.DFUtils;


public enum SimpleEnemyStateEnum implements State<SimpleEnemy>{
	
	SLEEP(){
		@Override
		public void enter(SimpleEnemy enemy) {
			
		}
		
		@Override
		public void update(SimpleEnemy enemy) {
			
			
			String data = "";
			
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
			
			
			if (distance <= GameLogicInformation.ATTACK_DISTANCE) {
				data = "ATTACK";
				enemy.getStateMachine().changeState(ATTACK);
				enemy.setShootingActive(true);
			}else if ((distance > GameLogicInformation.ATTACK_DISTANCE) && (distance <= GameLogicInformation.SEEK_DISTANCE)){
				data = "SEEK";
				enemy.getStateMachine().changeState(SEEK);
				enemy.setShootingActive(false);
			}else {
				data = "SLEEP";
				enemy.getStateMachine().changeState(SLEEP);
				enemy.setShootingActive(false);
			}
			
			
		}
		
		@Override
		public void exit(SimpleEnemy enemy) {
			
		}
		
	},
	
	SEEK(){
		
		@Override
		public void enter(SimpleEnemy enemy) {
			
		}
		
		@Override
		public void update(SimpleEnemy enemy) {
			
			String data = "";
			
			
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
			
			if (distance <= GameLogicInformation.ATTACK_DISTANCE) {
				data = "ATTACK";
				enemy.getStateMachine().changeState(ATTACK);
				enemy.setShootingActive(true);
			}else if ((distance > GameLogicInformation.ATTACK_DISTANCE) && (distance <= GameLogicInformation.SEEK_DISTANCE)){
				data = "SEEK";
				enemy.getStateMachine().changeState(SEEK);
				enemy.setShootingActive(false);
			}else {
				data = "SLEEP";
				enemy.getStateMachine().changeState(SLEEP);
				enemy.setShootingActive(false);
			}
			
		}
		
		@Override
		public void exit(SimpleEnemy enemy) {
			
		}
		
	},
	
	ATTACK(){
		
		@Override
		public void enter(SimpleEnemy enemy) {
			
		}
		
		@Override
		public void update(SimpleEnemy enemy) {
			
			String data = "";
			
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
			
			
			if (distance <= GameLogicInformation.ATTACK_DISTANCE) {
				data = "ATTACK";
				enemy.getStateMachine().changeState(ATTACK);
				enemy.setShootingActive(true);
			}else if ((distance > GameLogicInformation.ATTACK_DISTANCE) && (distance <= GameLogicInformation.SEEK_DISTANCE)){
				data = "SEEK";
				enemy.getStateMachine().changeState(SEEK);
				enemy.setShootingActive(false);
			}else {
				data = "SLEEP";
				enemy.getStateMachine().changeState(SLEEP);
				enemy.setShootingActive(false);
			}
			
		}
		
		@Override
		public void exit(SimpleEnemy enemy) {
			
		}
		
	};
	

	@Override
	public boolean onMessage(SimpleEnemy entity, Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
	

	

	
	
}
