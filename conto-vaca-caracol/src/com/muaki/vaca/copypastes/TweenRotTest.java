package com.muaki.vaca.copypastes;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.muaki.vaca.tween.CMIAccessor;

public class TweenRotTest implements ApplicationListener {

	public Environment environment;
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	private TweenManager twman;
	private ModelInstance instance2;
	private Interpolation interpolation;

	@Override
	public void create() {
		interpolation = getInterpolation();
		

		twman = new TweenManager();
		Tween.registerAccessor(ModelInstance.class, new CMIAccessor());

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f,
				0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f,
				-0.8f, -0.2f));

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		cam.position.set(1f, 1f, 1f);
		cam.lookAt(0, 0, 0);
		cam.near = 0.1f;
		cam.far = 300f;
		cam.update();

		ModelLoader loader = new ObjLoader();
		model = loader.loadModel(Gdx.files.internal("data/ship.obj"));
		instance = new ModelInstance(model);
		
		
//		instance2 		 = new ModelInstance(model);

		
//		instance.transform.set(0, 0, 1, 1);
		
		instance.transform.setToRotation(Vector3.X, -20);
		instance.calculateTransforms();

//		Tween.to(instance2, CMIAccessor.ROTATION, 2f).target(-15).start(twman);
//		
//		Tween.to(instance, CMIAccessor.ROTATION, 2f).target(-100).start(twman);
//		Tween.to(instance, CMIAccessor.ROTATION, 2f).target(-100).start(twman);
		Tween.to(instance, CMIAccessor.ROTATION, 2f).target(-100).start(twman);


		Gdx.input.setInputProcessor(camController);
	}

		private Interpolation getInterpolation () {
		try {
//			return (Interpolation)Interpolation.class.getField(list.getSelection()).get(null);
			return (Interpolation)Interpolation.class.getField("bound").get(null);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void render() {
		twman.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		

		modelBatch.begin(cam);
		modelBatch.render(instance, environment);
//		modelBatch.render(instance2, environment);
		modelBatch.end();
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
