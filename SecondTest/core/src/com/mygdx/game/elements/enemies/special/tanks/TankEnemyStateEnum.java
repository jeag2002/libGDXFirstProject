package com.mygdx.game.elements.enemies.special.tanks;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.*;
import com.mygdx.game.elements.enemies.drons.SimpleEnemy;
import com.mygdx.game.utils.NewItem;
import com.mygdx.game.logic.GameLogicInformation;
import com.mygdx.game.logic.elements.*;
import com.badlogic.gdx.Gdx;

//MOVE --> MOVE TANK
//ATTACK --> STOP & ATTACK PLAYER
//BLOCK --> STOP WITH OTHER ELEMENTS
//STOP --> END OF MOVEMENT


public enum TankEnemyStateEnum implements State<TankEnemy>{
		
	ATTACK(){
		
		//private static final float DST_EXIT = 100.0f;
		//private static final float DST_PLAYER = 50.0f;
		
		private String data = null;
		
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
			
			if (dstPlayer > GameLogicInformation.DST_TANK_PLAYER) {
				
				if (data == null) {
				
					float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
					if (dst < GameLogicInformation.DST_TANK_EXIT) {
						enemy.getStateMachine().changeState(STOP);
						//Gdx.app.log("[IA-ATTACK]","ENEMY COLL (" + enemy.getIdCode() + ") NO DATA DETECT EXIT_ITEM STOP FLAG");
					}else {
						enemy.getStateMachine().changeState(MOVE);
						//Gdx.app.log("[IA-ATTACK]","ENEMY COLL (" + enemy.getIdCode() + ") NO DATA EXIT_ITEM TOO FAR MOVE FLAG");
					}
				
				}else {
					
					//Gdx.app.log("[IA-ATTACK]","ENEMY COLL (" + enemy.getIdCode() + ") DATA FLAG (" + (data==null?"MOVE":data) + ")");
					
					if (data.equals("STOP")) {enemy.getStateMachine().changeState(STOP);}
					else {enemy.getStateMachine().changeState(MOVE);}
					
					//data = null;
				}
				
			}
		}
		
		@Override
		public void exit(TankEnemy enemy) {
		}

		@Override
		public boolean onMessage(TankEnemy entity, Telegram telegram) {
			data = (String)telegram.extraInfo;
			return false;
		}
		
	},
	
	
	MOVE(){
		
		private NewItem data = null;
		//private static final float DST_DISTANCE = 150.0f;
		//private static final float DST_EXIT = 100.0f;
		
		
		
		@Override
		public void enter(TankEnemy enemy) {
		}
		
		@Override
		public void update(TankEnemy enemy) {
		
			float centerEnemyX = enemy.getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerEnemyY = enemy.getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float centerObjetiveX = enemy.getObjective().getX()+SecondTestGDX.tilePlayerWidth_TL/2;
			float centerObjetiveY = enemy.getObjective().getY()+SecondTestGDX.tilePlayerHeight_TL/2;
			
			float posPlayerX = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getX() + SecondTestGDX.tilePlayerWidth_TL/2;
			float posPlayerY = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getPlayer().getY() + SecondTestGDX.tilePlayerHeight_TL/2;
			
			
			String stopFlag = "";
			
			if (data != null) {
				
				
				String idCode = data.getIdCode();
				
				SpawnObject object = enemy.getGamePlayScreen().getGamePlay().getGameLogic().getSpawnPool().getDynamicElementtWithCollisionById(idCode);
				
				if (object == null) {
					
					float centerBlockX = data.getX() + data.getWidth()/2;
					float centerBlockY = data.getY() + data.getHeight()/2;
					
					float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerBlockX, centerBlockY);
					
					
					if (dst <= GameLogicInformation.DST_TANK_COLL_OTHER) {
						
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA NO OBJECT WITH COLL FLAG [STOP]");
						
						enemy.getStateMachine().changeState(STOP);
						stopFlag = "STOP";
					}
					
					
					
					dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
					
					if (dst < GameLogicInformation.DST_TANK_EXIT) {
						
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA NO OBJECT WITH EXIT FLAG [STOP]");
						
						enemy.getStateMachine().changeState(STOP);
						stopFlag = "STOP";
					}
					
					float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
					
					if (dstPlayer <= GameLogicInformation.DST_TANK_PLAYER) {
						
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA NO OBJECT WITH PLAYER FLAG [ATTACK] WITH STOPFLAG (" + stopFlag + ")");
						
						enemy.getStateMachine().changeState(ATTACK);
						
						Telegram msg = new Telegram();
						msg.extraInfo = stopFlag;
						enemy.handleMessage(msg);
					}
					
					
				}else {
					
					
					float centerBlockX = data.getX() + data.getWidth()/2;
					float centerBlockY = data.getY() + data.getHeight()/2;
					
					float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerBlockX, centerBlockY);
					
					
					if (dst <= GameLogicInformation.DST_TANK_COLL_OTHER) {
						
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA+OBJECT WITH COLL FLAG [STOP]");
						
						enemy.getStateMachine().changeState(STOP);
						stopFlag = "STOP";
					}

					dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
					
					if (dst < GameLogicInformation.DST_TANK_EXIT) {
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA+OBJECT WITH EXIT FLAG [STOP]");
						enemy.getStateMachine().changeState(STOP);
						stopFlag = "STOP";
					}
					
					float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
					
					if (dstPlayer <= GameLogicInformation.DST_TANK_PLAYER) {
						
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") DATA+OBJECT WITH PLAYER FLAG [ATTACK] WITH STOPFLAG (" + stopFlag + ")");
						
						enemy.getStateMachine().changeState(ATTACK);
						
						Telegram msg = new Telegram();
						msg.extraInfo = stopFlag;
						enemy.handleMessage(msg);
						
					}
				}	
				
				//data = null;
				
			}else {
				
				if (enemy.getPath().size == 0) {
					
					//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") NODATA+NOOBJECT END PATH FLAG [STOP]");
					
					enemy.getStateMachine().changeState(STOP);
					stopFlag = "STOP";
				}else {
					float dst = Vector2.dst(centerEnemyX, centerEnemyY, centerObjetiveX, centerObjetiveY);
					if (dst <= GameLogicInformation.DST_TANK_EXIT) {
						//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") NODATA+NOOBJECT WITH EXIT FLAG [STOP]");
						enemy.getStateMachine().changeState(STOP);
						stopFlag = "STOP";
					}
				}	
				
				
				float dstPlayer = Vector2.dst(centerEnemyX, centerEnemyY, posPlayerX, posPlayerY);
				
				if (dstPlayer <= GameLogicInformation.DST_TANK_PLAYER) {
					
					//Gdx.app.log("[IA-MOVE]","ENEMY COLL (" + enemy.getIdCode() + ") NODATA+NOOBJECT WITH PLAYER FLAG [ATTACK] WITH STOPFLAG " + stopFlag);
					
					enemy.getStateMachine().changeState(ATTACK);
					
					Telegram msg = new Telegram();
					msg.extraInfo = stopFlag;
					enemy.handleMessage(msg);
				}
							
			}
		}
		
		
		
		
		@Override
		public void exit(TankEnemy enemy) {
		}

		@Override
		public boolean onMessage(TankEnemy entity, Telegram telegram) {
			data = (NewItem)telegram.extraInfo;
			return false;
		}
	},
	
	STOP(){
		
		
		
		
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
			
			if (dstPlayer <= GameLogicInformation.DST_TANK_PLAYER) {
				
				
				//Gdx.app.log("[IA-STOP]","ENEMY COLL (" + enemy.getIdCode() + ") WITH PLAYER FLAG [ATTACK] WITH STOPFLAG (STOP)");
		
				enemy.getStateMachine().changeState(ATTACK);
				Telegram msg = new Telegram();
				msg.extraInfo = "STOP";
				enemy.handleMessage(msg);
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
