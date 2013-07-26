#ifdef GL_ES
precision highp float; 
#endif

attribute vec4 a_position;
attribute vec3 a_normal;
attribute vec2 a_texCoord0;

uniform mat3 NormalMatrix;
uniform mat4 MVPMatrix;
uniform mat4 u_lightProjTrans;

uniform vec3 Position;
uniform vec3 CameraPosition;

varying vec3 vNormal;
varying vec3 vLightDis;
varying vec3 vEye;

varying vec4 shadowCoord;

void main() {
	shadowCoord = u_lightProjTrans * a_position;
	
	vNormal = normalize(a_normal);
	
	vec3 pos = a_position.xyz;
	vLightDis = Position - pos;
	vEye = CameraPosition - pos;
	
	gl_Position = MVPMatrix * a_position;
}