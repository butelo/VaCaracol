package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.lights.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.materials.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;

public class SceneTest implements ApplicationListener {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Lights lights;
	public boolean loading;

	public Array<ModelInstance> blocks = new Array<ModelInstance>();
	public Array<ModelInstance> invaders = new Array<ModelInstance>();
	public ModelInstance ship;
	public ModelInstance space;
	public Model model;

	public Renderable renderable;

	public Shader shader;
	public RenderContext renderContext;

	@Override
	public void create() {
		modelBatch = new ModelBatch();
		lights = new Lights();
		lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
		lights.add(new PointLight()
				.set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f, 20));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(2f, 2f, 2f);
		cam.lookAt(0, 0, 0);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		assets = new AssetManager();

		// assets.load("data/ship.obj", Model.class);
		// assets.load("data/block.obj", Model.class);
		// assets.load("data/invader.obj", Model.class);
		// assets.load("data/spacesphere.obj", Model.class);

		// assets.load("data/invaders.g3dj", Model.class);

		// TUTORIAL DE BEHIND 3D SCENES PART 2
		ModelLoader modelLoader = new G3dModelLoader(new JsonReader());
		ModelData modelData = modelLoader.loadModelData(Gdx.files
				.internal("data/invaders.g3dj"));
		model = new Model(modelData, new TextureProvider.FileTextureProvider());

		NodePart blockPart = model.getNode("ship").parts.get(0);

		renderable = new Renderable();
		renderable.mesh = blockPart.meshPart.mesh;
		renderable.meshPartOffset = blockPart.meshPart.indexOffset;
		renderable.meshPartSize = blockPart.meshPart.numVertices;
		renderable.primitiveType = blockPart.meshPart.primitiveType;
		renderable.material = blockPart.material;
		renderable.lights = lights;
		renderable.worldTransform.idt();

		renderContext = new RenderContext(new DefaultTextureBinder(
				DefaultTextureBinder.WEIGHTED, 1));
//		shader = new DefaultShader(renderable.material,
//				renderable.mesh.getVertexAttributes(), true, false, 1, 0, 0, 0);
		shader.init();

		doneLoading();

		loading = true;

	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		instances.clear();
		try {
			assets.dispose();
		} catch (Exception e) {
			//
		}
		model.dispose();

	}

	private void doneLoading() {

		/*
		 * // vello METODO CARGANDO OBEJOTAS ship = new
		 * ModelInstance(assets.get("data/ship.obj", Model.class));
		 * ship.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		 * instances.add(ship);
		 * 
		 * Model blockModel = assets.get("data/block.obj", Model.class); for
		 * (float x = -5f; x <= 5f; x += 2f) { ModelInstance block = new
		 * ModelInstance(blockModel); block.transform.setToTranslation(x, 0,
		 * 3f); instances.add(block); blocks.add(block); }
		 * 
		 * Model invaderModel = assets.get("data/invader.obj", Model.class); for
		 * (float x = -5f; x <= 5f; x += 2f) { for (float z = -8f; z <= 0f; z +=
		 * 2f) { ModelInstance invader = new ModelInstance(invaderModel);
		 * invader.transform.setToTranslation(x, 0, z); instances.add(invader);
		 * invaders.add(invader); } }
		 * 
		 * space = new ModelInstance(assets.get("data/spacesphere.obj",
		 * Model.class));
		 * 
		 * loading = false;
		 */

		/*
		 * 
		 * novo metodo usando o SCENE LOADER
		 * 
		 * Model model = assets.get("data/invaders.g3db", Model.class); ship =
		 * new ModelInstance(model, "ship");
		 * ship.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		 * instances.add(ship);
		 * 
		 * for (float x = -5f; x <= 5f; x += 2f) { ModelInstance block = new
		 * ModelInstance(model, "block"); block.transform.setToTranslation(x, 0,
		 * 3f); instances.add(block); blocks.add(block); }
		 * 
		 * for (float x = -5f; x <= 5f; x += 2f) { for (float z = -8f; z <= 0f;
		 * z += 2f) { ModelInstance invader = new ModelInstance(model,
		 * "invader"); invader.transform.setToTranslation(x, 0, z);
		 * instances.add(invader); invaders.add(invader); } }
		 * 
		 * space = new ModelInstance(model, "spacesphere");
		 * 
		 * loading = false;
		 */

		// AQUI RESPETAMOS A COLOCACION QUE SAE DO BLENDER
		// Model model = assets.get("data/invaders.g3dj", Model.class);
		Material blockMaterial = model.getNode("block").parts.get(0).material;
		ColorAttribute colorAttribute = (ColorAttribute) blockMaterial
				.get(ColorAttribute.Diffuse);
		colorAttribute.color.set(Color.YELLOW);

		for (int i = 0; i < model.nodes.size; i++) {
			String id = model.nodes.get(i).id;
			ModelInstance instance = new ModelInstance(model, id);
			Node node = instance.getNode(id);

			instance.transform.set(node.globalTransform);
			node.translation.set(0, 0, 0);
			node.scale.set(1, 1, 1);
			node.rotation.idt();
			instance.calculateTransforms();

			if (id.equals("spacesphere")) {
				space = instance;
				continue;
			}

			instances.add(instance);

			if (id.equals("ship"))
				ship = instance;
			else if (id.startsWith("block"))
				blocks.add(instance);
			else if (id.startsWith("inveider"))
				invaders.add(instance);
		}

		loading = false;

	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		/*
		 * if (loading && assets.update()) doneLoading();
		 * camController.update();
		 * 
		 * Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
		 * Gdx.graphics.getHeight()); Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
		 * GL10.GL_DEPTH_BUFFER_BIT);
		 * 
		 * modelBatch.begin(cam); for (ModelInstance instance : instances)
		 * modelBatch.render(instance, lights); if (space != null)
		 * modelBatch.render(space); modelBatch.end();
		 */

		/*
		 * camController.update();
		 * 
		 * Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
		 * Gdx.graphics.getHeight()); Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
		 * GL10.GL_DEPTH_BUFFER_BIT);
		 * 
		 * modelBatch.begin(cam); modelBatch.render(renderable);
		 * modelBatch.end();
		 */

		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		renderContext.begin();
		shader.begin(cam, renderContext);
		shader.render(renderable);
		shader.end();
		renderContext.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}
}