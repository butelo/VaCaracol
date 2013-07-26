#ifdef GL_ES
	precision highp float; 
#endif

uniform float InvFar;	// 1.0 / 30.0;

uniform vec3 Direction;
uniform float Cutoff;

uniform vec4 MaterialAmbient;
uniform vec4 MaterialDiffuse;
uniform vec4 MaterialSpecular;
uniform float MaterialShininess;

uniform sampler2D DepthMap;

varying vec3 vNormal;
varying vec3 vLightDis;
varying vec3 vEye;

varying vec4 shadowCoord;

float unpack (vec4 colour) {
	const vec4 bitShifts = vec4(1.0,
								1.0 / 255.0,
								1.0 / (255.0 * 255.0),
								1.0 / (255.0 * 255.0 * 255.0));
	return dot(colour, bitShifts);
}

vec3 calculateLighting() {
	vec3 normal = normalize(vNormal);
	vec3 color = vec3(0.0);
	
	float dist = length(vLightDis);
	vec3 lightDirection = vLightDis / dist;
	float diffuse = dot(normal, lightDirection);
	
	float intensity = clamp(1.0 - (dist * InvFar), 0.0, 1.0);
	intensity = (diffuse >= 0.0) ? intensity : 0.0;
	
	float lightConeAngle = dot(Direction, -lightDirection);
	lightConeAngle = clamp(((lightConeAngle - Cutoff) / (1.0 - Cutoff)), 0.0, 1.0);
	intensity *= lightConeAngle;
	
	vec3 fromEye = normalize(vEye);
	vec3 halfAngle = normalize(lightDirection + fromEye);
	float specular = clamp(dot(halfAngle, normal), 0.0, 1.0);
	specular = pow(specular, MaterialShininess);
	
	color += intensity * (MaterialSpecular.rgb * specular + MaterialDiffuse.rgb * diffuse);
	
	return color;
}

float calculateShadow() {
	vec3 depth = (shadowCoord.xyz / shadowCoord.w) * 0.5 + 0.5;
	depth.z = length(vLightDis) * InvFar;
	float shadow = 1.0;
	
	depth.z *= 0.99;
	
	// PCF	
	float texelSize = 1.0 / 1024.0;
	for (int y = -1; y <= 1; ++y) {
		for (int x = -1; x <= 1; ++x) {
			vec2 offset = depth.xy + vec2(float(x) * texelSize, float(y) * texelSize);
			if ( (offset.x >= 0.0) && (offset.x <= 1.0) && (offset.y >= 0.0) && (offset.y <= 1.0) ) {
				float shadowDepth = unpack(texture2D(DepthMap, offset));
				if ( depth.z > shadowDepth )
					shadow *= 0.9;
			}
		}
	}
	
	return shadow;
}

void main() {
	vec3 color = MaterialAmbient.rgb;
	
	// Calculate (diffuse + specular) * shadow
	color += calculateLighting() * calculateShadow();
	
	gl_FragColor = vec4(color, 1.0);
}