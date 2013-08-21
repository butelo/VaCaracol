package com.muaki.vaca.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.lights.PointLight;
import com.badlogic.gdx.graphics.g3d.materials.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.materials.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.materials.IntAttribute;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class _DbgOfMainScreen implements Screen {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Lights lights;
	public boolean loading;
	public InputMultiplexer mpex;
	public boolean clicadopadiante = false;

	// XXX quitar o log dos frames
//	public FPSLogger fps;

	public ModelInstance uno;
	public ModelInstance dos;
	public ModelInstance tres;
	public ModelInstance cuatro;
	public ModelInstance cinco;
	public ModelInstance seis;

	Node node;

	float angleX = 0;

	float rotation;

	TextureAttribute textureAttribute;
	ColorAttribute colorAttribute;
	BlendingAttribute blendingAttribute;
	IntAttribute backfaceculling;

	Material material;

	private int total = 0;
	private float movementIncrement = 0.0006f;
	float rotationtotal;

	public Texture texture;

	Model model;

	@Override
	public void dispose() {

	}

	private void doneLoading() {
		model = assets.get("data/follasdolibrotest.g3db", Model.class);

		uno = new ModelInstance(model, "uno");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = uno.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = uno.getNode("uno");
		uno.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		uno.calculateTransforms();
		uno.transform.rotate(Vector3.X, angleX);
		instances.add(uno);

		dos = new ModelInstance(model, "dos");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = dos.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = dos.getNode("dos");
		dos.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		dos.calculateTransforms();
		dos.transform.rotate(Vector3.X, angleX);
		instances.add(dos);

		tres = new ModelInstance(model, "tres");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = tres.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = tres.getNode("tres");
		tres.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		tres.calculateTransforms();
		tres.transform.rotate(Vector3.X, angleX);
		instances.add(tres);

		cuatro = new ModelInstance(model, "cuatro");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = cuatro.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = cuatro.getNode("cuatro");
		cuatro.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		cuatro.calculateTransforms();
		cuatro.transform.rotate(Vector3.X, angleX);
		instances.add(cuatro);

		cinco = new ModelInstance(model, "cinco");
		material = cinco.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = cinco.getNode("cinco");
		cinco.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		cinco.calculateTransforms();
		cinco.transform.rotate(Vector3.X, angleX);
		instances.add(cinco);

		seis = new ModelInstance(model, "seis");
		material = seis.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = seis.getNode("seis");
		seis.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		seis.calculateTransforms();
		seis.transform.rotate(Vector3.X, angleX);
		instances.add(seis);

		loading = false;
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float delta) {

		total += 1;
		if (total > 500) {
			movementIncrement = -movementIncrement;
			total = -200;

		}

		if (loading && assets.update()) {

			doneLoading();
		}

		camController.update();
		cam.rotate(movementIncrement * 20, 0, 1, 0);
		cam.translate(movementIncrement, 0, movementIncrement);
		cam.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);

		for (ModelInstance instance : instances) {
			modelBatch.render(instance, lights);

			rotation = -10 * Gdx.graphics.getDeltaTime();

			if (rotationtotal > -90 && clicadopadiante) {
				uno.transform.rotate(Vector3.X, rotation);
				rotationtotal += rotation;

			}

		}

		modelBatch.end();

		// XXX quitar o log dos frames
//		fps.log();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {
		mpex = new InputMultiplexer();

		// XXX quitar o log dos frames
//		fps = new FPSLogger();
		modelBatch = new ModelBatch();

		lights = new Lights();
		lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f,
				-0.2f));
		lights.add(new PointLight().set(0.8f, 0.8f, 0.8f, -0.15267295f,
				8.6140175f, 9.30487f, 40));

		cam = new PerspectiveCamera(35, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(3.5107954f, 2.319038f, 5.35728f);
		cam.lookAt(-0f, -0f, 0f);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		mpex.addProcessor(camController);

		assets = new AssetManager();
		assets.load("data/follasdolibrotest.g3db", Model.class);

		loading = true;

	}

}
