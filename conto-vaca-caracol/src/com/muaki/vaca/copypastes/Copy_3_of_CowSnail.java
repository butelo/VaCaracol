package com.muaki.vaca.copypastes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;

//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;

public class Copy_3_of_CowSnail implements ApplicationListener {

	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Environment lights;
	public boolean loading;

	public Array<ModelInstance> blocks = new Array<ModelInstance>();
	public Array<ModelInstance> invaders = new Array<ModelInstance>();
	public ModelInstance ship;
	public ModelInstance space;

	@Override
	public void create() {
		modelBatch = new ModelBatch();
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		lights.add(new PointLight().set(0.8f, 0.8f, 0.8f, -0.15267295f,
				8.6140175f, 9.30487f, 40));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(0f, 7f, 10f);
		cam.lookAt(0, 0, 0);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		assets = new AssetManager();
		// assets.load("data/shipB.obj", Model.class);
		// assets.load("data/block.obj", Model.class);
		// assets.load("data/invader.obj", Model.class);
		// assets.load("data/spacesphere.obj", Model.class);
		assets.load("data/scene.g3db", Model.class);
		loading = true;
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
	}

	private void doneLoading() {
		Model model = assets.get("data/scene.g3db", Model.class);
		for (int i = 0; i < model.nodes.size; i++) {
			String id = model.nodes.get(i).id;
			ModelInstance instance = new ModelInstance(model, id);
			Node node = instance.getNode(id);

			instance.transform.set(node.globalTransform);
			node.translation.set(0, 0, 0);
			node.scale.set(1, 1, 1);
			node.rotation.idt();
			instance.calculateTransforms();

			if (id.equals("space")) {
				space = instance;
				continue;
			}

			instances.add(instance);

			if (id.equals("ship"))
				ship = instance;
			else if (id.startsWith("block"))
				blocks.add(instance);
			else if (id.startsWith("invader"))
				invaders.add(instance);
		}

		loading = false;
	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		if (loading && assets.update())
			doneLoading();
		camController.update();

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
