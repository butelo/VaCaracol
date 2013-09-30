package com.muaki.vaca.copypastes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.shaders.GLES10Shader;
import com.badlogic.gdx.graphics.g3d.utils.BaseShaderProvider;

public class ShaderProvider extends BaseShaderProvider {

	private static String defaultVertexShader = null;

	private static String defaultFragmentShader = null;

	public final static String getDefaultFragmentShader() {
		if (defaultFragmentShader == null) {
			// defaultFragmentShader =
			// Gdx.files.internal("data/shaders/vertexpath.fragment.glsl").readString();
			defaultFragmentShader = Gdx.files.internal(
					"data/shaders/default.fragment.glsl").readString();
			// defaultFragmentShader =
			// Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.fragment.glsl").readString();
		}
		return defaultFragmentShader;
	}

	public final static String getDefaultVertexShader() {
		if (defaultVertexShader == null) {
			// defaultVertexShader =
			// Gdx.files.internal("data/shaders/vertexpath.vertex.glsl").readString();
			defaultVertexShader = Gdx.files.internal(
					"data/shaders/autumn.vertex.glsl").readString();
			// defaultVertexShader =
			// Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.vertex.glsl").readString();
		}
		return defaultVertexShader;
	}

	@Override
	protected Shader createShader(final Renderable renderable) {
		if (Gdx.graphics.isGL20Available())
			return new AutumnShader(getDefaultVertexShader(),
					getDefaultFragmentShader(), renderable.material,
					renderable.mesh.getVertexAttributes(),
					renderable.environment != null, 2, 5, 3,
					renderable.bones == null ? 0 : 12);
		return new GLES10Shader();
	}

}