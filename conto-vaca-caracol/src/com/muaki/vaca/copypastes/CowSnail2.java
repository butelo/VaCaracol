package com.muaki.vaca.copypastes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.lights.BaseLight;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.lights.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.materials.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.materials.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.materials.IntAttribute;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class CowSnail2 extends Game {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public Lights lights;
	public boolean loading;
	private Sprite sprite;
	
	DecalBatch decalBatch;
	Decal esprait;

	public ModelInstance book;
	public ModelInstance space;
	public ModelInstance table;
	public ModelInstance floor;
	public ModelInstance vaquita;
	public ModelInstance bed;
	public ModelInstance caracol;
	
	float angleX = -90;

	float rotation;
	
	TextureAttribute textureAttribute;
	ColorAttribute colorAttribute;
	BlendingAttribute blendingAttribute;
	IntAttribute backfaceculling;
	
	Material material;
	private SpriteBatch batch;

	private int total = 0;
	private float movementIncrement = 0.0006f;
	float rotationtotal;

	public Texture texture;
	Texture rgba8888;
	Model model;


	@Override
	public void create() {

		
		

		
		

		batch = new SpriteBatch();
		decalBatch = new DecalBatch();

		texture = new Texture(Gdx.files.internal("data/vaquita.png"));
		
		
		rgba8888 = new Texture("data/bobargb8888-32x32.png");
		
		
		esprait = Decal.newDecal(2, 2, new TextureRegion(rgba8888), true);
		

		modelBatch = new ModelBatch();
		lights = new Lights();
		lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
		// lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f,
		// -0.2f));

		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f,
				-0.2f));

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
		Gdx.input.setInputProcessor(camController);

		assets = new AssetManager();
		// assets.load("data/book.obj", Model.class);
		// assets.load("data/table.obj", Model.class);
		// assets.load("data/floor.obj", Model.class);

		assets.load("data/roomBI.g3db", Model.class);

		// assets.load("data/block.obj", Model.class);
		// assets.load("data/invader.obj", Model.class);
		// assets.load("data/spacesphere.obj", Model.class);

		loading = true;
	}

	private void doneLoading() {
		 model = assets.get("data/roomBI.g3db", Model.class);

		book = new ModelInstance(model, "book");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(book);

		bed = new ModelInstance(model, "bed");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(bed);

		// book = new ModelInstance(assets.get("data/book.obj", Model.class));
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		// instances.add(book);

		table = new ModelInstance(model, "table");
		// table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		instances.add(table);

		floor = new ModelInstance(model, "floor");
		// floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		
		instances.add(floor);

		vaquita = new ModelInstance(model, "vaca");
		Node node = vaquita.getNode("vaca");
		
		vaquita.transform.set(node.globalTransform);
        node.translation.set(0,0,0);
        node.scale.set(1,1,1);
       
		node.rotation.idt();
        vaquita.calculateTransforms();
		
		
		textureAttribute = new TextureAttribute(TextureAttribute.Diffuse,
				texture);
		colorAttribute = new ColorAttribute(ColorAttribute.Diffuse,
				Color.ORANGE);
		blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA, 
				GL20.GL_ONE_MINUS_SRC_ALPHA);
	    backfaceculling =new IntAttribute(IntAttribute.CullFace, GL20.GL_BACK);
	    
	    
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
	public void render() {
		
		
		texture.bind();
		
		
	
		
		
		
		
		

		total += 1;
		if (total > 500) {
			movementIncrement = -movementIncrement;
			total = -200;
			

		}

		if (loading && assets.update()){
			
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
		
		for (ModelInstance instance : instances){
			modelBatch.render(instance, lights);
			
			 rotation = 10 * Gdx.graphics.getDeltaTime();
			 
			
		if(rotationtotal<90){
			vaquita.transform.rotate(Vector3.X, rotation);
			 rotationtotal +=rotation;
		}

			
		}

		if (space != null){
			modelBatch.render(space);
		
		}
		


		// System.out.println("x: " +cam.position.x +" y: "
		// +cam.position.y+" z: " +cam.position.z );

		modelBatch.end();
		
		batch.begin();
		batch.draw(rgba8888, 60, 0);
		batch.end();
		

//		decalBatch.add(esprait);
//        decalBatch.flush();

	}



	@Override
	public void dispose() {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
		batch.dispose();
		
		
		rgba8888.dispose();
		

	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {

	}

}
