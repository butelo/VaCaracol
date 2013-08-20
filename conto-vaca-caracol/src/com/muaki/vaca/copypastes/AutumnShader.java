package com.muaki.vaca.copypastes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;

public class AutumnShader implements Shader {

	public AutumnShader(String defaultVertexShader,
			String defaultFragmentShader, Material material,
			VertexAttributes vertexAttributes, boolean b, int i, int j, int k,
			int l) {
	}

	@Override
	public void begin(Camera camera, RenderContext context) {

	}

	@Override
	public boolean canRender(Renderable instance) {
		return false;
	}

	@Override
	public int compareTo(Shader other) {
		return 0;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void end() {

	}

	@Override
	public void init() {

	}

	@Override
	public void render(Renderable renderable) {

	}

}
