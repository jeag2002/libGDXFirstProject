package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SecondTestGDX;

public class DFUtils {
	
	public static float vectorToAngle (Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}

	public static Vector2 angleToVector (Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}
	
	public static Vector2 aimTo(Vector2 shooter, Vector2 target){
		Vector2 aim = new Vector2();
		float velx = target.x - shooter.x;
		float vely = target.y - shooter.y;
		float length = (float) Math.sqrt(velx * velx + vely * vely);
		if (length != 0) {
			aim.x = velx / length; 
			aim.y = vely / length;  
		}
		return aim;
	}
	
	public static Vector2 aimTo(Vector2 shooter, Vector3 target){
		return aimTo(shooter, new Vector2(target.x,target.y));
	}
	
	
	public static double distance(Vector2 entity, Vector2 target) {
		return Math.sqrt( Math.pow( (entity.x - target.x) , 2) +  Math.pow( (entity.y - target.y) ,2));
	}
	
	public static double distance(float entityX, float entityY, float targetX, float targetY) {
		return Math.sqrt( Math.pow( (entityX - targetX) , 2) +  Math.pow( (entityY - targetY) ,2));
	}
	
	

}
