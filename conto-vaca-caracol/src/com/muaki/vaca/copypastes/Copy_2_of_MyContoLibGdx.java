package com.muaki.vaca.copypastes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;

public class Copy_2_of_MyContoLibGdx implements ApplicationListener {
	private Mesh squareMesh;
	private Mesh nearSquare;
	private Camera camera;
	// private StillModel book;
	private Texture shipTexture;

	@Override
	public void create() {
		// book =
		// ModelLoaderRegistry.loadStillModel(Gdx.files.internal("data/book.obj"));
		// shipTexture = new Texture(Gdx.files.internal("data/ship.png"),
		// Format.RGB565, true);
		// shipTexture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
		//
		//
		// if (squareMesh == null) {
		// squareMesh = new Mesh(true, 4, 4,
		// new VertexAttribute(Usage.Position, 3, "a_position"),
		// new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
		//
		// squareMesh.setVertices(new float[] {
		// 0, -0.5f, -4, Color.toFloatBits(128, 0, 0, 255),
		// 1, -0.5f, -4, Color.toFloatBits(192, 0, 0, 255),
		// 0, 0.5f, -4, Color.toFloatBits(192, 0, 0, 255),
		// 1, 0.5f, -4, Color.toFloatBits(255, 0, 0, 255) });
		// squareMesh.setIndices(new short[] { 0, 1, 2, 3});
		// }
		//
		// if (nearSquare == null) {
		// nearSquare = new Mesh(true, 4, 4,
		// new VertexAttribute(Usage.Position, 3, "a_position"),
		// new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
		//
		// nearSquare.setVertices(new float[] {
		// -1, -0.5f, -1.1f, Color.toFloatBits(0, 0, 128, 255),
		// 0, -0.5f, -1.1f, Color.toFloatBits(0, 0, 192, 255),
		// -1, 0.5f, -1.1f, Color.toFloatBits(0, 0, 192, 255),
		// 0, 0.5f, -1.1f, Color.toFloatBits(0, 0, 255, 255) });
		// nearSquare.setIndices(new short[] { 0, 1, 2, 3});
		// }
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
	}

	private int total = 0;
	private float movementIncrement = 0.0006f;

	// @Override
	// public void render() {
	// GL10 gl = Gdx.graphics.getGL10();
	// camera.update();
	// camera.apply(Gdx.gl10);
	// gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	// // squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
	// // nearSquare.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
	// renderBook(gl);
	// }

	@Override
	public void render() {
		GL10 gl = Gdx.graphics.getGL10();

		// gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// gl.glViewport(0, 0, Gdx.graphics.getWidth(),
		// Gdx.graphics.getHeight());

		// renderBackground();

		// gl.glDisable(GL10.GL_DITHER);
		// gl.glEnable(GL10.GL_DEPTH_TEST);
		// gl.glEnable(GL10.GL_CULL_FACE);

		// setLighting(gl);
		total += 1;
		if (total > 500) {
			movementIncrement = -movementIncrement;
			total = -200;
		}

		camera.rotate(movementIncrement * 20, 0, 1, 0);
		camera.translate(movementIncrement, 0, movementIncrement);
		camera.update();
		camera.apply(Gdx.gl10);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glDisable(GL10.GL_TEXTURE_2D);
		squareMesh.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
		nearSquare.render(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		renderBook(gl);

	}

	float[] direction = { 1, 0.5f, 0, 0 };

	private void setLighting(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, direction, 0);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);

	}

	private void renderBook(GL10 gl) {

		// shipTexture.bind();
		gl.glPushMatrix();
		gl.glTranslatef(0, -0.5f, -1.1f);
		// gl.glRotatef(45 * (-Gdx.input.getAccelerometerY() / 5), 0, 0, 1);
		// gl.glRotatef(180, 0, 1, 0);
		// book.render();
		gl.glPopMatrix();

	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		// camera = new OrthographicCamera(2f * aspectRatio, 2f);
		camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);

	}

	@Override
	public void resume() {
	}
}