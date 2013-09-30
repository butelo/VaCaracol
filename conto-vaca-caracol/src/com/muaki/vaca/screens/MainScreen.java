package com.muaki.vaca.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MainScreen implements Screen {

	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Environment lights;
	public boolean loading;

	public ModelInstance book;
	public ModelInstance cubierta;
	public ModelInstance pagina34;
	public ModelInstance pagina56;

	public ModelInstance space;
	public ModelInstance table;
	public ModelInstance floor;
	public ModelInstance vaquita;
	public ModelInstance bed;
	public ModelInstance caracol;
	public InputMultiplexer mpex;
	public boolean clicadopadiante;

	float angleX = -90;

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

		modelBatch.dispose();
		instances.clear();
		assets.dispose();

	}

	private void doneLoading() {
		model = assets.get("data/roomBI.g3db", Model.class);

		book = new ModelInstance(model, "book");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(book);

		cubierta = new ModelInstance(model, "cubiertas");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(cubierta);

		pagina34 = new ModelInstance(model, "book34");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(pagina34);

		pagina56 = new ModelInstance(model, "book56");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(pagina56);

		bed = new ModelInstance(model, "bed");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(bed);

		table = new ModelInstance(model, "table");
		// table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(table);

		floor = new ModelInstance(model, "floor");
		// floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);

		instances.add(floor);

		vaquita = new ModelInstance(model, "vaca");
		Node node = vaquita.getNode("vaca");

		vaquita.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);

		node.rotation.idt();
		vaquita.calculateTransforms();

		textureAttribute = new TextureAttribute(TextureAttribute.Diffuse,
				texture);
		colorAttribute = new ColorAttribute(ColorAttribute.Diffuse,
				Color.ORANGE);
		blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA,
				GL20.GL_ONE_MINUS_SRC_ALPHA);
		backfaceculling = new IntAttribute(IntAttribute.CullFace, GL20.GL_BACK);

		material = vaquita.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));

		material.set(blendingAttribute);

		vaquita.transform.rotate(Vector3.X, angleX);

		instances.add(vaquita);

		caracol = new ModelInstance(model, "caracol");
		// floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);

		// textureAttribute = new TextureAttribute(TextureAttribute.Diffuse,
		// texture);
		// colorAttribute = new ColorAttribute(ColorAttribute.Diffuse,
		// Color.ORANGE);
		// blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA,
		// GL20.GL_ONE_MINUS_SRC_ALPHA);
		//
		material = caracol.materials.get(0);
		material.set(blendingAttribute);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));

		instances.add(caracol);

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

		// camController.update();
		cam.rotate(movementIncrement * 20, 0, 1, 0);
		cam.translate(movementIncrement, 0, movementIncrement);
		cam.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);

		for (ModelInstance instance : instances) {
			modelBatch.render(instance, lights);

			rotation = 10 * Gdx.graphics.getDeltaTime();

			if (rotationtotal < 90) {
				vaquita.transform.rotate(Vector3.X, rotation);
				rotationtotal += rotation;
			}

		}

		if (space != null) {
			modelBatch.render(space);

		}

		// System.out.println("x: " +cam.position.x +" y: "
		// +cam.position.y+" z: " +cam.position.z );

		modelBatch.end();

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
		texture = new Texture(Gdx.files.internal("data/vaquita.png"));

		modelBatch = new ModelBatch();

		
		
		
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		lights.add(new PointLight().set(0.8f, 0.8f, 0.8f, -0.15267295f,
				8.6140175f, 9.30487f, 40));
		
		
		
		
		
		
		
		
		
		
		

		cam = new PerspectiveCamera(35, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(-0.15267295f, 8.6140175f, 9.30487f);

		cam.lookAt(-0f, -0f, -50.6217f);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);

		// Gdx.input.setInputProcessor(camController);

		mpex.addProcessor(camController);

		assets = new AssetManager();

		assets.load("data/roomBI.g3db", Model.class);

		loading = true;

	}

}
