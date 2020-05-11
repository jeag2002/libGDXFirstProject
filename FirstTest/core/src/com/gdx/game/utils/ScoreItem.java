package com.gdx.game.utils;

public class ScoreItem {
	

	private String item;
	private Long score;
	
	public ScoreItem(String item, Long score) {
		this.item = item;
		this.score = score;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	
	

}
