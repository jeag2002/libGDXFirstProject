package com.gdx.game.utils;

public class StringUtils {
	
	public static String leftPaddedString(int length, int value) {
		String valueStr = String.valueOf(value);
		String print = "";
		for(int i=0; i< length-valueStr.length(); i++) {print += "0";}
		print+=value;
		return print;
	}
	
	

}
