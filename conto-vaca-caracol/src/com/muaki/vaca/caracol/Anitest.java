package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;

//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;

public class Anitest extends InputAdapter implements ApplicationListener {

	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Lights lights;
	public boolean loading;

	// public Array<ModelInstance> blocks = new Array<ModelInstance>();
	// public Array<ModelInstance> invaders = new Array<ModelInstance>();
	public ModelInstance book;
	public ModelInstance space;
	public ModelInstance table;
	public ModelInstance floor;
	private int total = 0;
	private float movementIncrement = 0.0006f;
	AnimationController animation;
	ModelInstance character;

	final AnimationController.Transform trForward = new AnimationController.Transform();
	boolean rightKey, leftKey, upKey, downKey, spaceKey;

	@Override
	public void create() {
		modelBatch = new ModelBatch();
		lights = new Lights();
		lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f,
				-0.2f));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(12.576112f, 15.914165f, 11.904736f);

		cam.lookAt(0, 0, 0);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);

		Gdx.input.setInputProcessor(camController);

		Gdx.input.setInputProcessor(this);

		assets = new AssetManager();
		// assets.load("data/book.obj", Model.class);
		// assets.load("data/table.obj", Model.class);
		// assets.load("data/floor.obj", Model.class);

		assets.load("data/knight.g3db", Model.class);

		assets.load("data/anitest.g3db", Model.class);

		// assets.load("data/block.obj", Model.class);
		// assets.load("data/invader.obj", Model.class);
		// assets.load("data/spacesphere.obj", Model.class);

		loading = true;
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
	}

	private void doneLoading() {
		Model model = assets.get("data/anitest.g3db", Model.class);

		// book = new ModelInstance(model, "book");
		// // book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(book);
		//
		// animation = new AnimationController(book);
		// animation.animate("Take 001", -1, 1f, null, 0.2f);

		// assets.load("data/anitest.g3db", Model.class);

		character = new ModelInstance(assets.get("data/anitest.g3db",
				Model.class));

		BoundingBox bbox = new BoundingBox();
		character.calculateBoundingBox(bbox);
		character.transform.setToRotation(Vector3.Y, 180)
				.trn(0, -bbox.min.y, 0);
		instances.add(character);
		animation = new AnimationController(character);
		animation.animate("PoseLib", -1, 1f, null, 0.2f);
		// status = idle;
		// for (Animation anim : character.animations)
		// Gdx.app.log("Test", anim.id);

		// book = new ModelInstance(assets.get("data/book.obj", Model.class));
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(book);

		table = new ModelInstance(model, "table");
		// table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(table);

		floor = new ModelInstance(model, "floor");
		// floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(floor);

		// Model blockModel = assets.get("data/block.obj", Model.class);
		// for (float x = -5f; x <= 5f; x += 2f) {
		// ModelInstance block = new ModelInstance(blockModel);
		// block.transform.setToTranslation(x, 0, 3f);
		// instances.add(block);
		// blocks.add(block);
		// }

		// Model invaderModel = assets.get("data/invader.obj", Model.class);
		// for (float x = -5f; x <= 5f; x += 2f) {
		// for (float z = -8f; z <= 0f; z += 2f) {
		// ModelInstance invader = new ModelInstance(invaderModel);
		// invader.transform.setToTranslation(x, 0, z);
		// instances.add(invader);
		// invaders.add(invader);
		// }
		// }

		// space = new ModelInstance(assets.get("data/spacesphere.obj",
		// Model.class));

		loading = false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			leftKey = true;
		if (keycode == Keys.RIGHT)
			rightKey = true;
		if (keycode == Keys.UP)
			upKey = true;
		if (keycode == Keys.DOWN)
			downKey = true;
		if (keycode == Keys.SPACE)
			spaceKey = true;
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			leftKey = false;
		if (keycode == Keys.RIGHT)
			rightKey = false;
		if (keycode == Keys.UP)
			upKey = false;
		if (keycode == Keys.DOWN)
			downKey = false;
		if (keycode == Keys.SPACE)
			spaceKey = false;
		return super.keyUp(keycode);
	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {
		total += 1;
		if (total > 500) {
			movementIncrement = -movementIncrement;
			total = -200;
		}
		camController.update();
		cam.rotate(movementIncrement * 20, 0, 1, 0);
		cam.translate(movementIncrement, 0, movementIncrement);
		cam.update();

		if (character != null) {
			animation.update(Gdx.graphics.getDeltaTime());

			if (spaceKey) {

				animation.animate("ArmatureAction", 1, 1f, null, 0.2f);
				// animation.animate("Attack", 1, 1f, null, 0.2f);
				// animation.action("Take 001", 1, 1f, null, 0.2f);
			}

			// System.out.println("x: " +cam.position.x +" y: "
			// +cam.position.y+" z: " +cam.position.z );

		}

		if (loading && assets.update())
			doneLoading();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);

		for (ModelInstance instance : instances)
			modelBatch.render(instance, lights);
		if (space != null)
			modelBatch.render(space);

		modelBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

}
