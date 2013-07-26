package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
//import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
//import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.JsonReader;

public class ShaderTest implements ApplicationListener {
	   public PerspectiveCamera cam;
	   public CameraInputController camController;
	   public Shader shader;
	   public RenderContext renderContext;
	   public Model model;
	   public Lights lights;
	   public Renderable renderable;
	     
	   @Override
	   public void create () {
		    cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		    cam.position.set(2f, 2f, 2f);
		    cam.lookAt(0,0,0);
		    cam.near = 0.1f;
		    cam.far = 300f;
		    cam.update();
		     
		    camController = new CameraInputController(cam);
		    Gdx.input.setInputProcessor(camController);
		 
		    ModelBuilder modelBuilder = new ModelBuilder();
		    model = modelBuilder.createSphere(2f, 2f, 2f, 20, 20,
		      new Material(),
		      Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		 
		    NodePart blockPart = model.nodes.get(0).parts.get(0);
		      
		    renderable = new Renderable();
	        renderable.mesh = blockPart.meshPart.mesh;
	        renderable.meshPartOffset = blockPart.meshPart.indexOffset;
	        renderable.meshPartSize = blockPart.meshPart.numVertices;
	        renderable.primitiveType = blockPart.meshPart.primitiveType;
	        renderable.material = blockPart.material;
//	        renderable.primitiveType = GL20.GL_POINTS;
	        renderable.lights = lights;
	        renderable.worldTransform.idt();
	        
	        


	        
	        
		      
		    renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.WEIGHTED, 1));
		    shader = new TestShader();
		    shader.init();
	   }
	     
	   @Override
	   public void render () {
	       camController.update();
	         
	       Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	       Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	 
	       renderContext.begin();
	       shader.begin(cam, renderContext);
	       shader.render(renderable);
	       shader.end();
	       renderContext.end();
	   }
	     
	   @Override
	   public void dispose() {
	       shader.dispose();
	       model.dispose();
	   }
	  
	    @Override public void resume () {}
	    @Override public void resize (int width, int height) {}
	    @Override public void pause () {}
	
	}