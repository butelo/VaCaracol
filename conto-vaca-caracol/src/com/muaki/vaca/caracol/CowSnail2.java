package com.muaki.vaca.caracol;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;




import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
//import com.badlogic.gdx.graphics.g3d.materials.BlendingAttribute;
//import com.badlogic.gdx.graphics.g3d.materials.IntAttribute;
//import com.badlogic.gdx.graphics.g3d.materials.Material;
//import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;


import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;



import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;

public class CowSnail2 extends Game {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Environment lights;
	public boolean loading;
	public FPSLogger fps;

	// public ModelInstance book;
	// public ModelInstance space;
	// public ModelInstance table;
	// public ModelInstance floor;
	// public ModelInstance vaquita;
	// public ModelInstance bed;
	// public ModelInstance caracol;

	public ModelInstance uno;
	public ModelInstance dos;
	public ModelInstance tres;
	public ModelInstance cuatro;
	public ModelInstance cinco;
	public ModelInstance seis;
	// public ModelInstance caracol;

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
	public void create() {
		fps = new FPSLogger();

		modelBatch = new ModelBatch();
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		
		
		
		
		

		cam = new PerspectiveCamera(35, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(3.5107954f, 2.319038f, 5.35728f);

		cam.lookAt(-0f, -0f, 0f);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		assets = new AssetManager();
		// assets.load("data/book.obj", Model.class);
		// assets.load("data/table.obj", Model.class);
		// assets.load("data/floor.obj", Model.class);

		assets.load("data/follasdolibrotest.g3db", Model.class);

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
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
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
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
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

		// book = new ModelInstance(model, "book");
		// // book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(book);
		//
		// bed = new ModelInstance(model, "bed");
		// // book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(bed);
		//
		// // book = new ModelInstance(assets.get("data/book.obj",
		// Model.class));
		// // book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// // instances.add(book);
		//
		// table = new ModelInstance(model, "table");
		// // table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(table);
		//
		// floor = new ModelInstance(model, "floor");
		// // floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		//
		// instances.add(floor);
		//
		// vaquita = new ModelInstance(model, "vaca");
		// Node node = vaquita.getNode("vaca");
		//
		// vaquita.transform.set(node.globalTransform);
		// node.translation.set(0,0,0);
		// node.scale.set(1,1,1);
		//
		// node.rotation.idt();
		// vaquita.calculateTransforms();
		//
		//
		// textureAttribute = new TextureAttribute(TextureAttribute.Diffuse,
		// texture);
		// colorAttribute = new ColorAttribute(ColorAttribute.Diffuse,
		// Color.ORANGE);
		// blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA,
		// GL20.GL_ONE_MINUS_SRC_ALPHA);
		// backfaceculling =new IntAttribute(IntAttribute.CullFace,
		// GL20.GL_BACK);
		//
		//
		// material = vaquita.materials.get(0);
		// material.set(new IntAttribute(IntAttribute.CullFace, 0));
		//
		// material.set(blendingAttribute);
		//
		// vaquita.transform.rotate(Vector3.X, angleX);
		//
		//
		//
		//
		//
		//
		//
		//
		//
		// instances.add(vaquita);
		//
		// caracol = new ModelInstance(model, "caracol");
		// // floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		//
		// // textureAttribute = new TextureAttribute(TextureAttribute.Diffuse,
		// // texture);
		// // colorAttribute = new ColorAttribute(ColorAttribute.Diffuse,
		// // Color.ORANGE);
		// // blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA,
		// // GL20.GL_ONE_MINUS_SRC_ALPHA);
		// //
		// material = caracol.materials.get(0);
		// material.set(blendingAttribute);
		// material.set(new IntAttribute(IntAttribute.CullFace, 0));
		//
		// instances.add(caracol);
		//
		// // Model blockModel = assets.get("data/block.obj", Model.class);
		// // for (float x = -5f; x <= 5f; x += 2f) {
		// // ModelInstance block = new ModelInstance(blockModel);
		// // block.transform.setToTranslation(x, 0, 3f);
		// // instances.add(block);
		// // blocks.add(block);
		// // }
		//
		// // Model invaderModel = assets.get("data/invader.obj", Model.class);
		// // for (float x = -5f; x <= 5f; x += 2f) {
		// // for (float z = -8f; z <= 0f; z += 2f) {
		// // ModelInstance invader = new ModelInstance(invaderModel);
		// // invader.transform.setToTranslation(x, 0, z);
		// // instances.add(invader);
		// // invaders.add(invader);
		// // }
		// // }
		//
		// // space = new ModelInstance(assets.get("data/spacesphere.obj",
		// // Model.class));

		loading = false;
	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {

		// texture.bind();
		fps.log();
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

			rotation = 10 * Gdx.graphics.getDeltaTime();

			if (rotationtotal < 90) {
				uno.transform.rotate(Vector3.X, rotation);
				rotationtotal += rotation;
			}

		}

		// if (space != null){
		// modelBatch.render(space);
		//
		// }

		// System.out.println("x: " +cam.position.x +" y: "
		// +cam.position.y+" z: " +cam.position.z );

		modelBatch.end();

		// decalBatch.add(esprait);
		// decalBatch.flush();

	}

	@Override
	public void resize(int width, int height) {

		System.out.println("resize");

	}

	@Override
	public void resume() {
	}

}
