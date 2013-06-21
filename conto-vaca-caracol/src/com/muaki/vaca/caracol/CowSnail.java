package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.materials.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g3d.model.Node;

public class CowSnail implements ApplicationListener {public PerspectiveCamera cam;
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
private int total = 0;
private float movementIncrement = 0.0006f;



 
@Override
public void create () {
    modelBatch = new ModelBatch();
    lights = new Lights();
    lights.ambientLight.set(0.4f, 0.4f, 0.4f, 1f);
    lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
     
    cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    cam.position.set(0f, 7f, 10f);
    cam.lookAt(0,0,0);
    cam.near = 0.1f;
    cam.far = 300f;
    cam.update();

//    camController = new CameraInputController(cam);
//    Gdx.input.setInputProcessor(camController);
     
    assets = new AssetManager();
//    assets.load("data/book.obj", Model.class);
//    assets.load("data/table.obj", Model.class);
//    assets.load("data/floor.obj", Model.class);

    assets.load("data/untitled.g3db", Model.class);

    
//    assets.load("data/block.obj", Model.class);
//    assets.load("data/invader.obj", Model.class);
//    assets.load("data/spacesphere.obj", Model.class);

    loading = true;
}

private void doneLoading() {
	Model model = assets.get("data/untitled.g3db", Model.class);
	
	
    book = new ModelInstance(model, "book");
//    book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(book);
	
	
//	book = new ModelInstance(assets.get("data/book.obj", Model.class));
//	book.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
//    instances.add(book);
    
    
    
    table = new ModelInstance(model, "table");
//    table.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(table);
    
    floor = new ModelInstance(model, "floor");
//    floor.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
    instances.add(floor);
    
    

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
	total += 1;
    if (total > 500) {
        movementIncrement = -movementIncrement;
        total = -200;
    }


    
    if (loading && assets.update())
        doneLoading();
//    camController.update();
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
