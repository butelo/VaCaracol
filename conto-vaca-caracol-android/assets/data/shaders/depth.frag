#ifdef GL_ES
	precision highp float; 
#endif

uniform float InvFar;

varying vec4 vPosition;

vec4 pack (float depth) {
	const vec4 bias = vec4(1.0 / 255.0,
							1.0 / 255.0,
							1.0 / 255.0,
							0.0);

	float r = depth;
	float g = fract(r * 255.0);
	float b = fract(g * 255.0);
	float a = fract(b * 255.0);
	vec4 colour = vec4(r, g, b, a);
	
	return colour - (colour.yzww * bias);
}

void main() {
	float linearDepth = length(vPosition) * InvFar;
	gl_FragColor = pack(linearDepth);
}