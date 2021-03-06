package com.muaki.vaca.caracol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;




import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader {

	public static class TestColorAttribute extends ColorAttribute {
		public final static String DiffuseUAlias = "diffuseUColor";
		public final static long DiffuseU = register(DiffuseUAlias);

		public final static String DiffuseVAlias = "diffuseVColor";
		public final static long DiffuseV = register(DiffuseVAlias);

		static {
			Mask = Mask | DiffuseU | DiffuseV;
		}

		public TestColorAttribute(long type, float r, float g, float b, float a) {
			super(type, r, g, b, a);
		}
	}

	ShaderProgram program;
	Camera camera;
	RenderContext context;
	int u_projTrans;
	int u_worldTrans;
	int u_colorU;
	int u_colorV;

	@Override
	public void begin(Camera camera, RenderContext context) {
		this.camera = camera;
		this.context = context;
		program.begin();
		program.setUniformMatrix(u_projTrans, camera.combined);
		context.setDepthTest(GL20.GL_LEQUAL);
		context.setCullFace(GL20.GL_BACK);
	}

	@Override
	public boolean canRender(Renderable renderable) {
		return renderable.material.has(TestColorAttribute.DiffuseU
				| TestColorAttribute.DiffuseV);
	}

	@Override
	public int compareTo(Shader other) {
		return 0;
	}

	@Override
	public void dispose() {
		program.dispose();
	}

	@Override
	public void end() {
		program.end();
	}

	@Override
	public void init() {
		String vert = Gdx.files.internal("data/shaders/test.vertex.glsl")
				.readString();
		String frag = Gdx.files.internal("data/shaders/test.fragment.glsl")
				.readString();
		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled())
			throw new GdxRuntimeException(program.getLog());
		u_worldTrans = program.getUniformLocation("u_worldTrans");
		u_colorU = program.getUniformLocation("u_colorU");
		u_colorV = program.getUniformLocation("u_colorV");
	}

	@Override
	public void render(Renderable renderable) {
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
		Color colorU = ((ColorAttribute) renderable.material
				.get(TestColorAttribute.DiffuseU)).color;
		Color colorV = ((ColorAttribute) renderable.material
				.get(TestColorAttribute.DiffuseV)).color;
		program.setUniformf(u_colorU, colorU.r, colorU.g, colorU.b);
		program.setUniformf(u_colorV, colorV.r, colorV.g, colorV.b);
		renderable.mesh.render(program, renderable.primitiveType,
				renderable.meshPartOffset, renderable.meshPartSize);
	}
}