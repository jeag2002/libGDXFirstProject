package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SecondTestGDX;

public class ShaderEngine {
	
	//public static final float DEFAULT_LIGHT_Z = 0.075f;
	//public static final float AMBIENT_INTENSITY = 0.2f;
	//public static final float LIGHT_INTENSITY = 1f;

	public static final float DEFAULT_LIGHT_Z = 1f;
	public static final float AMBIENT_INTENSITY = 1f;
	public static final float LIGHT_INTENSITY = 1f;
	
	public static final Vector3 LIGHT_POS = new Vector3(0f,0f,DEFAULT_LIGHT_Z);
	//public static final Vector3 LIGHT_COLOR = new Vector3(1f, 0.8f, 0.6f);
	//public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);
	//public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);
	
	public static final Vector3 LIGHT_COLOR = new Vector3(1f, 1f, 1f);
	public static final Vector3 AMBIENT_COLOR = new Vector3(1f, 1f, 1f);
	public static final Vector3 FALLOFF = new Vector3(1f, 1f, 1f);


	
	public static ShaderProgram generateShaderNormalMap() {
		
		ShaderProgram shader = new ShaderProgram(
				ShaderPrograms.VERT_NORMAL_MAP,
				ShaderPrograms.FRAG_NORMAL_MAP);
		
		if (shader.isCompiled()) {
			shader.begin();
			
			shader.setUniformi("u_normals", 1);
			shader.setUniformf("Resolution", SecondTestGDX.screenWidth, SecondTestGDX.screenHeight);
			shader.setUniformf("LightColor", LIGHT_COLOR.x, LIGHT_COLOR.y, LIGHT_COLOR.z, LIGHT_INTENSITY);
			shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
			shader.setUniformf("Falloff", FALLOFF);
			
			shader.end();
			
			return shader;
		}else {
			return null;
		}
		
	}

}
