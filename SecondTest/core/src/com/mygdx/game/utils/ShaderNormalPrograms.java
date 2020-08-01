package com.mygdx.game.utils;

public class ShaderNormalPrograms {
	
	public static final String VERT_NORMAL_MAP =
	"#include \"g_attributes.glsl:VS\"\r\n" + 
	"#include \"u_uniforms.glsl\"\r\n" + 
	"#include \"skinning.glsl\"\r\n" + 
	"#include \"common.glsl:VS\"\r\n" + 
	" \r\n" + 
	"void main() {\r\n" + 
	"	g_position = u_worldTrans * applySkinning(g_position);\r\n" + 
	"	gl_Position = u_projViewTrans * g_position;\r\n" + 
	"\r\n" + 
	"#ifdef normalTextureFlag\r\n" + 
	"	g_binormal = normalize(u_normalMatrix * applySkinning(g_binormal));\r\n" + 
	"	g_tangent = normalize(u_normalMatrix * applySkinning(g_tangent));\r\n" + 
	"	pushBinormal();\r\n" + 
	"	pushTangent();\r\n" + 
	"	passTexCoord0();\r\n" + 
	"#endif\r\n" + 
	"	\r\n" + 
	"	passNormalValue(normalize(u_normalMatrix * applySkinning(g_normal)));\r\n" + 
	"}";
			
			
	
	public static final String FRAG_NORMAL_MAP = 
	"#ifdef GL_ES \r\n" + 
	"#define LOWP lowp\r\n" + 
	"#define MED mediump\r\n" + 
	"#define HIGH highp\r\n" + 
	"precision mediump float;\r\n" + 
	"#else\r\n" + 
	"#define MED\r\n" + 
	"#define LOWP\r\n" + 
	"#define HIGH\r\n" + 
	"#endif\r\n" + 
	"\r\n" + 
	"#include \"g_attributes.glsl:FS\"\r\n" + 
	"#include \"u_uniforms.glsl\"\r\n" + 
	"#include \"common.glsl:FS\"\r\n" + 
	"\r\n" + 
	"void main() {\r\n" + 
	"	pullNormal();\r\n" + 
	"	\r\n" + 
	"#ifdef normalTextureFlag\r\n" + 
	"	pullBinormal();\r\n" + 
	"	pullTangent();\r\n" + 
	"	\r\n" + 
	"	vec3 normal = normalize(texture2D(u_normalTexture, g_texCoord0).xyz * 2.0 - 1.0);\r\n" + 
	"	g_normal = normalize((g_tangent * normal.x) + (g_binormal * normal.y) + (g_normal * normal.z));\r\n" + 
	"#endif\r\n" + 
	"	\r\n" + 
	"	gl_FragColor = vec4( normalize( g_normal * vec3( 0.5 ) + vec3( 0.5 ) ), 1.0 );\r\n" + 
	"}";
	
	

}
