package com.muaki.vaca.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
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
import com.muaki.vaca.simulations.Control;
import com.muaki.vaca.tween.CMIAccessor;

public class _DbgOfMainScreen implements Screen {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ContoModelInstance> instances = new Array<ContoModelInstance>();
	public Environment lights;
	public boolean loading;
	public InputMultiplexer mpex;
	public boolean clicadopadiante = false;
	public boolean clicadopatras = false;


	// XXX quitar o log dos frames
//	public FPSLogger fps;

	public ContoModelInstance uno;
	public ContoModelInstance dos;
	public ContoModelInstance tres;
	public ContoModelInstance cuatro;
	public ContoModelInstance cinco;
	public ContoModelInstance seis;
	public ContoModelInstance contra;
	public ContoModelInstance cuberta;
	public ContoModelInstance lombo;
	Vector3 xyz = new Vector3();
	 private TweenManager twman;

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
	
	public Control crtl;

	@Override
	public void dispose() {

	}

	private void doneLoading() {
		model = assets.get("data/follasdolibrotest4.g3db", Model.class);
		
//		Array<Node> jojo = model.nodes;
//		Gdx.app.log("MuakiBooks: ", jojo.size+"");
//		for(int i =0; model.nodes.size>i; i++){
//			Gdx.app.log("MuakiBooks: ", i+"");
//		}
		
		cinco = new ContoModelInstance(model, "pag5");
		material = cinco.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = cinco.getNode("pag5");
		cinco.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		cinco.calculateTransforms();
		cinco.pagename="p5";

		instances.add(cinco);
		
		
		tres = new ContoModelInstance(model, "pag3");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = tres.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = tres.getNode("pag3");
		tres.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		tres.calculateTransforms();
		tres.pagename="p3";

		instances.add(tres);
		
		
		

		uno = new ContoModelInstance(model, "pag1");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = uno.materials.get(0);
		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = uno.getNode("pag1");
		uno.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		uno.calculateTransforms();
//		uno.PecharLibro();
//		Gdx.app.log("MuakiBooks: ", uno.libroaberto+"");
//		uno.AsignamosValores(3);
//		Gdx.app.log("MuakiBooks: ", uno.deltaY+"");
		
//		nome da p��xina solamente para debugear
		uno.pagename="p1";
		instances.add(uno);

		dos = new ContoModelInstance(model, "pag2");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = dos.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = dos.getNode("pag2");
		dos.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		dos.calculateTransforms();
//		dos.PAGINA1 = true;
//		dos.AbrirLibro();
//		Gdx.app.log("MuakiBooks: ", uno.libroaberto+" "+dos.PAGINA1);
		dos.pagename="p2";

		instances.add(dos);



		cuatro = new ContoModelInstance(model, "pag4");
		// book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
		material = cuatro.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = cuatro.getNode("pag4");
		cuatro.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		cuatro.calculateTransforms();
		cuatro.pagename="p4";

		instances.add(cuatro);
		
		


		seis = new ContoModelInstance(model, "pag6");
		material = seis.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = seis.getNode("pag6");
		seis.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		seis.calculateTransforms();
		seis.pagename="p6";
		instances.add(seis);
		
		contra = new ContoModelInstance(model, "contra");
		material = contra.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = contra.getNode("contra");
		contra.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		contra.calculateTransforms();
		instances.add(contra);
		
		cuberta = new ContoModelInstance(model, "cuberta");
		material = cuberta.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = cuberta.getNode("cuberta");
		cuberta.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		cuberta.calculateTransforms();
		instances.add(cuberta);
		
		lombo = new ContoModelInstance(model, "lombo");
		material = lombo.materials.get(0);
//		material.set(new IntAttribute(IntAttribute.CullFace, 0));
		node = lombo.getNode("lombo");
		lombo.transform.set(node.globalTransform);
		node.translation.set(0, 0, 0);
		node.scale.set(1, 1, 1);
		node.rotation.idt();
		lombo.calculateTransforms();
		instances.add(lombo);

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
		twman.update(delta);

	
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

			if(crtl.CurrPax==0){
			abrirLibro();
			}
			else if(crtl.CurrPax<crtl.PaxsTot){
				rotaciondolibro();
			}else{
				pecharLibro();
			}
//			rotaciondolibro();

		}

//		rotaciondolibro();
		

		modelBatch.end();

		// XXX quitar o log dos frames
//		fps.log();

	}

	private void pecharLibro() {
		
//		aqui hai que pechar o libro
	}

	private void abrirLibro() {
		rotation = -10 * Gdx.graphics.getDeltaTime();
		if (rotationtotal > -100 && clicadopadiante) {
			
			cuberta.transform.rotate(Vector3.X, rotation);
			seis.transform.rotate(Vector3.X, rotation);
			seis.transform.translate(0, 0.00017f, 0.00015f);

			
//			if (rotationtotal > -95){
				cuatro.transform.rotate(Vector3.X, rotation);
				cuatro.transform.translate(0, 0, 0.00016f);

//			}
			
			if (rotationtotal > -95){
				dos.transform.rotate(Vector3.X, rotation);
				dos.transform.translate(0, 0, -0.00008f);
			}
						
			if (rotationtotal > -15){
				lombo.transform.rotate(Vector3.X, rotation);
			}
			
			rotationtotal += rotation;
		}	
//		xyz = seis.transform.getTranslation(xyz);
//		 Gdx.app.log("posicion seis ", ""+xyz.x + " "+xyz.y+" "+ xyz.z );
		
	}
	

	
	
	
	

	private void rotaciondolibro() {
		
		rotation = -10 * Gdx.graphics.getDeltaTime();
		if (clicadopadiante){
			
			
			
			
//			seis.PasamosPaxina((crtl.CurrPax+5)%6, seis);
			for (int i= 0;i < instances.size-3; i++  ){
//				uno.PasamosPaxina((crtl.CurrPax)%6);
//				dos.PasamosPaxina((crtl.CurrPax+1)%6);
//				tres.PasamosPaxina((crtl.CurrPax+2)%6);
//				cuatro.PasamosPaxina((crtl.CurrPax+3)%6);
//				cinco.PasamosPaxina((crtl.CurrPax+4)%6);
//				seis.PasamosPaxina((crtl.CurrPax+5)%6);
				
				instances.get(i).PasamosPaxina((crtl.CurrPax+i)%6, instances.get(i), xyz);
				
				
			}
			
			
	
			
			clicadopadiante= false;
			
		}else if (clicadopatras){
			for (int i= 0;i < instances.size-3; i++  ){				
				instances.get(i).PasamosPaxina((crtl.CurrPax+i)%6, instances.get(i),xyz);
				
			}
			
			clicadopatras= false;
		}
		
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {
		twman = new TweenManager();
		
		
		
		
		Tween.registerAccessor(ContoModelInstance.class, new CMIAccessor());
		

		
		
	
	
		
		mpex = new InputMultiplexer();

		// XXX quitar o log dos frames
//		fps = new FPSLogger();
		modelBatch = new ModelBatch();

		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
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
		assets.load("data/follasdolibrotest4.g3db", Model.class);

		loading = true;

	}

}
