package com.mygdx.game.elements;

public class ElementDefinitionObject {

	private int life;
	private int shield;
	private int ammo;
	private int score;
	
	public ElementDefinitionObject() {
		life = 0;
		shield = 0;
		ammo = 0;
		score = 0;
	}
	
	public ElementDefinitionObject(ElementDefinitionObject edoClone) {
		life = edoClone.getLife();
		shield = edoClone.getShield();
		ammo = edoClone.getAmmo();
		score = edoClone.getScore();
	}
	
	
	public static class Builder{
		
		
		private ElementDefinitionObject elementDO = new ElementDefinitionObject();
		
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
		
		public ElementDefinitionObject build() {
			return elementDO;
		}
		
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


	
	
	
}
