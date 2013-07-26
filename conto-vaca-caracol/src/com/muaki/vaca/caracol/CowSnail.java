package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.BaseLight;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.lights.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.materials.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.materials.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.materials.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class CowSnail implements ApplicationListener {
public PerspectiveCamera cam;
public CameraInputController camController;
public ModelBatch modelBatch;
public AssetManager assets;
public Array<ModelInstance> instances = new Array<ModelInstance>();
public Lights lights;
public boolean loading;
 
//public Array<ModelInstance> blocks = new Array<ModelInstance>();
//public Array<ModelInstance> invaders = new Array<ModelInstance>();
public ModelInstance book;
public ModelInstance space;
public ModelInstance table;
public ModelInstance floor;
public ModelInstance vaquita;
public ModelInstance bed;
public ModelInstance caracol;

public ShaderProgram shader;
TextureAttribute textureAttribute;
ColorAttribute colorAttribute;
BlendingAttribute blendingAttribute;
Material material;



private int total = 0;
private float movementIncrement = 0.0006f;

String vertexShader = "attribute vec4 a_position;    \n" + 
        "attribute vec4 a_color;\n" +
        "attribute vec2 a_texCoord0;\n" + 
        "uniform mat4 u_worldView;\n" + 
        "varying vec4 v_color;" + 
        "varying vec2 v_texCoords;" + 
        "void main()                  \n" + 
        "{                            \n" + 
        "   v_color = vec4(1, 1, 1, 1); \n" + 
        "   v_texCoords = a_texCoord0; \n" + 
        "   gl_Position =  u_worldView * a_position;  \n"      + 
        "}                            \n" ;
String fragmentShader = "#ifdef GL_ES\n" +
          "precision mediump float;\n" + 
          "#endif\n" + 
          "varying vec4 v_color;\n" + 
          "varying vec2 v_texCoords;\n" + 
          "uniform sampler2D u_texture;\n" + 
          "void main()                                  \n" + 
          "{                                            \n" + 
          "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n"+
          "}";
public Texture texture;
public Mesh mesh;
private Matrix4 matrix;

 
@Override
public void create () {
	matrix = new Matrix4();
	 shader = new ShaderProgram(vertexShader, fragmentShader);
	  mesh = new Mesh(true, 4, 6, VertexAttribute.Position(), VertexAttribute.  ColorUnpacked(), VertexAttribute.TexCoords(0));
	 mesh.setVertices(new float[] 
	 {-0.5f, -0.5f, 0, 1, 1, 1, 1, 0, 1,
	 0.5f, -0.5f, 0, 1, 1, 1, 1, 1, 1,
	 0.5f, 0.5f, 0, 1, 1, 1, 1, 1, 0,
	 -0.5f, 0.5f, 0, 1, 1, 1, 1, 0, 0});
	 mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
	  texture = new Texture(Gdx.files.internal("data/vaquita.png"));
	 
	 
	 
	 
    modelBatch = new ModelBatch();
    lights = new Lights();
    lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
//    lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    
    lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    lights.add(new PointLight().set(0.8f, 0.8f, 0.8f, -0.15267295f,  8.6140175f, 9.30487f, 40));
    
    
    cam = new PerspectiveCamera(35, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    cam.position.set(-0.15267295f,  8.6140175f, 9.30487f);
 
    cam.lookAt(-0f,-0f,-50.6217f);
    cam.near = 0.1f;
    cam.far = 300f;
    cam.update();

    camController = new CameraInputController(cam);
    Gdx.input.setInputProcessor(camController);
     
    assets = new AssetManager();
//    assets.load("data/book.obj", Model.class);
//    assets.load("data/table.obj", Model.class);
//    assets.load("data/floor.obj", Model.class);

    assets.load("data/roomBI.g3db", Model.class);

    
//    assets.load("data/block.obj", Model.class);
//    assets.load("data/invader.obj", Model.class);
//    assets.load("data/spacesphere.obj", Model.class);

    loading = true;
}

private void doneLoading() {
	Model model = assets.get("data/roomBI.g3db", Model.class);
	
	
    book = new ModelInstance(model, "book");
//    book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(book);
	
	
    bed = new ModelInstance(model, "bed");
//  book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
  instances.add(bed);
    
    
//	book = new ModelInstance(assets.get("data/book.obj", Model.class));
//	book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
//    instances.add(book);
    
    
    
    table = new ModelInstance(model, "table");
//    table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(table);
    
    floor = new ModelInstance(model, "floor");
//    floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(floor);
    
    vaquita = new ModelInstance(model, "vaca");
//  floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    
    
	textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
	colorAttribute = new ColorAttribute(ColorAttribute.Diffuse, Color.ORANGE);
	blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

	material = vaquita.materials.get(0);
	material.set(blendingAttribute);

  instances.add(vaquita);
  
  
  caracol = new ModelInstance(model, "caracol");
//floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
  
  
//	textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
//	colorAttribute = new ColorAttribute(ColorAttribute.Diffuse, Color.ORANGE);
//	blendingAttribute = new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//
	material = caracol.materials.get(0);
	material.set(blendingAttribute);

instances.add(caracol);

//    Model blockModel = assets.get("data/block.obj", Model.class);
//    for (float x = -5f; x <= 5f; x += 2f) {
//        ModelInstance block = new ModelInstance(blockModel);
//        block.transform.setToTranslation(x, 0, 3f);
//        instances.add(block);
//        blocks.add(block);
//    }
     
//    Model invaderModel = assets.get("data/invader.obj", Model.class);
//    for (float x = -5f; x <= 5f; x += 2f) {
//        for (float z = -8f; z <= 0f; z += 2f) {
//            ModelInstance invader = new ModelInstance(invaderModel);
//            invader.transform.setToTranslation(x, 0, z);
//            instances.add(invader);
//            invaders.add(invader);
//        }
//    }
     
    
    
    
    
    
    
    
    
//    space = new ModelInstance(assets.get("data/spacesphere.obj", Model.class));
     
    loading = false;
}
 
@Override
public void render () {
	texture.bind();
	shader.begin();
	shader.setUniformMatrix("u_worldView", matrix);
	shader.setUniformi("u_texture", 0);
	mesh.render(shader, GL10.GL_TRIANGLES);
	shader.end();
	
	
	
	total += 1;
    if (total > 500) {
        movementIncrement = -movementIncrement;
        total = -200;
    }


    
    if (loading && assets.update())
        doneLoading();
    camController.update();
    cam.rotate(movementIncrement * 20, 0, 1, 0);
    cam.translate(movementIncrement, 0, movementIncrement);
    cam.update();
     
    Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

    modelBatch.begin(cam);
    
    for (ModelInstance instance : instances)
        modelBatch.render(instance, lights);
    if (space != null)
        modelBatch.render(space);
    
//    System.out.println("x: " +cam.position.x +" y: " +cam.position.y+" z: " +cam.position.z );

    modelBatch.end();

}
 
@Override
public void dispose () {
    modelBatch.dispose();
    instances.clear();
    assets.dispose();
}
 
@Override
public void resume () {
}

@Override
public void resize (int width, int height) {
}

@Override
public void pause () {
	
}



}
