package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

//https://gist.github.com/mattdesl/4653464

public class ShaderPrograms {
	
	
	public static final String VERT_SEPIA_MAP =
			"uniform mat4 u_projTrans;\r\n" + 
			"attribute vec4 a_position;\r\n" + 
			"attribute vec2 a_texCoord0;\r\n" + 
			"attribute vec4 a_color;\r\n" + 
			"varying vec4 v_color;\r\n" + 
			"varying vec2 v_texCoord;\r\n" + 
			"void main() {\r\n" + 
				"gl_Position = u_projTrans * a_position;\r\n" + 
				"v_texCoord = a_texCoord0;\r\n" + 
				"v_color = a_color;\r\n" + 
			"}\r\n" + 
			"";
	
	public static final String FRAG_SEPIA_MAP =
			"#ifdef GL_ES\r\n" + 
			"precision mediump float;\r\n" + 
			"precision mediump int;\r\n" + 
			"#else\r\n" + 
			"#define highp;\r\n" + 
			"#endif\r\n" + 
			"uniform sampler2D u_texture;\r\n" + 
			"varying vec4 v_color;\r\n" + 
			"varying vec2 v_texCoord;\r\n" + 
			"const vec3 grayScaleMultiplier = vec3(0.299, 0.587, 0.114);\r\n" + 
			"const vec3 sepia = vec3(1.2, 1.0, 0.8);\r\n" + 
			"void main() {\r\n" + 
			"vec4 texColor = texture2D(u_texture, v_texCoord);\r\n" + 
			"vec3 gray = vec3(dot(texColor.rgb, grayScaleMultiplier));\r\n" + 
			"gl_FragColor = vec4(gray * sepia, texColor.a);\r\n" + 
			"}\r\n" + 
			"";
	
	
	
	public static final String VERT_NORMAL_MAP =  
			"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
			"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			
			"uniform mat4 u_projTrans;\n" + 
			" \n" + 
			"varying vec4 vColor;\n" +
			"varying vec2 vTexCoord;\n" +
			
			"void main() {\n" +  
			"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
			"}";
	
	
	public static final String FRAG_NORMAL_MAP = 
			//GL ES specific stuff
			  "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n" + //
			"//attributes from vertex shader\n" + 
			"varying LOWP vec4 vColor;\n" + 
			"varying vec2 vTexCoord;\n" + 
			"\n" + 
			"//our texture samplers\n" + 
			"uniform sampler2D u_texture;   //diffuse map\n" + 
			"uniform sampler2D u_normals;   //normal map\n" + 
			"\n" + 
			"//values used for shading algorithm...\n" + 
			"uniform vec2 Resolution;         //resolution of screen\n" + 
			"uniform vec3 LightPos;           //light position, normalized\n" + 
			"uniform LOWP vec4 LightColor;    //light RGBA -- alpha is intensity\n" + 
			"uniform LOWP vec4 AmbientColor;  //ambient RGBA -- alpha is intensity \n" + 
			"uniform vec3 Falloff;            //attenuation coefficients\n" + 
			"\n" + 
			"void main() {\n" + 
			"	//RGBA of our diffuse color\n" + 
			"	vec4 DiffuseColor = texture2D(u_texture, vTexCoord);\n" + 
			"	\n" + 
			"	//RGB of our normal map\n" + 
			"	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;\n" + 
			"	\n" + 
			"	//The delta position of light\n" + 
			"	vec3 LightDir = vec3(LightPos.xy - (gl_FragCoord.xy / Resolution.xy), LightPos.z);\n" + 
			"	\n" + 
			"	//Correct for aspect ratio\n" + 
			"	LightDir.x *= Resolution.x / Resolution.y;\n" + 
			"	\n" + 
			"	//Determine distance (used for attenuation) BEFORE we normalize our LightDir\n" + 
			"	float D = length(LightDir);\n" + 
			"	\n" + 
			"	//normalize our vectors\n" + 
			"	vec3 N = normalize(NormalMap * 2.0 - 1.0);\n" + 
			"	vec3 L = normalize(LightDir);\n" + 
			"	\n" + 
			"	//Pre-multiply light color with intensity\n" + 
			"	//Then perform \"N dot L\" to determine our diffuse term\n" + 
			"	vec3 Diffuse = (LightColor.rgb * LightColor.a) * max(dot(N, L), 0.0);\n" + 
			"\n" + 
			"	//pre-multiply ambient color with intensity\n" + 
			"	vec3 Ambient = AmbientColor.rgb * AmbientColor.a;\n" + 
			"	\n" + 
			"	//calculate attenuation\n" + 
			"	float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );\n" + 
			"	\n" + 
			"	//the calculation which brings it all together\n" + 
			"	vec3 Intensity = Ambient + Diffuse * Attenuation;\n" + 
			"	vec3 FinalColor = DiffuseColor.rgb * Intensity;\n" + 
			"	gl_FragColor = vColor * vec4(FinalColor, DiffuseColor.a);\n" + 
			"}";
	
	

}
