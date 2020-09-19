package com.mygdx.game.elements;

import java.util.Random;

import com.mygdx.game.enums.ElementEnum;

public class ElementDefinitionObject {

	private int life;
	private int shield;
	private int ammo;
	private int score;
	private ElementEnum cannon;
	public Random rand;
	
	public ElementDefinitionObject() {
		life = 0;
		shield = 0;
		ammo = 0;
		score = 0;
		cannon = ElementEnum.GUN_PLAYER_1_A;
	}
	
	public ElementDefinitionObject(ElementDefinitionObject edoClone) {
		life = edoClone.getLife();
		shield = edoClone.getShield();
		ammo = edoClone.getAmmo();
		score = edoClone.getScore();
		cannon = edoClone.getCannon();
	}
	
	
	public static class Builder{
		
		
		private ElementDefinitionObject elementDO = new ElementDefinitionObject();
		private Random rand= new Random();
		
		
		public Builder setLife(int life) {
			elementDO.life = life;
			return this;
		}
		
		public Builder setShield(int shield) {
			elementDO.shield = shield;
			return this;
		}
		
		public Builder setAmmo(int ammo) {
			elementDO.ammo = ammo;
			return this;
		}
		
		public Builder setScore(int score) {
			elementDO.score = score;
			return this;
		}
		
		
		public Builder setRandomLife(int life) {
			int random_life = rand.nextInt(life/2);
			elementDO.life = random_life + life/2;
			return this;
		}
		
		
		public Builder setRandomShield(int shield) {
			int random_shield = rand.nextInt(shield/2);
			elementDO.shield = random_shield + shield/2;
			return this;
		}
		
		
		public Builder setRandomAmmo(int ammo) {
			int random_ammo = rand.nextInt(ammo/2);
			elementDO.ammo = random_ammo + ammo/2;
			return this;
		}
		
		
		
		public ElementDefinitionObject build() {
			return elementDO;
		}
		
	}
	

	public void setCannon(ElementEnum cannon) {
		this.cannon = cannon;
	}
	
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public ElementEnum getCannon() {
		
		rand = new Random();
		int cannon = rand.nextInt(3);
		if (cannon == 0) {return ElementEnum.GUN_PLAYER_1_A;}
		else if (cannon == 1) {return ElementEnum.GUN_PLAYER_1_B;}
		else if (cannon == 2) {return ElementEnum.GUN_PLAYER_1_C;}
		else {return ElementEnum.GUN_PLAYER_1_A;}
	}
	
	
}
