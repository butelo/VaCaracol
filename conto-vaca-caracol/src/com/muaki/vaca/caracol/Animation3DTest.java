package com.muaki.vaca.caracol;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.lights.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.lights.Lights;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.Array;

public class Animation3DTest implements ApplicationListener {
	ModelInstance skydome;
	Model floorModel;
	ModelInstance character;
	AnimationController animation;

	Lights lights = new Lights(0.4f, 0.4f, 0.4f).add(new DirectionalLight()
			.set(0.8f, 0.8f, 0.8f, -1f, -.8f, -.2f));

	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public boolean loading;

	// public Array<ModelInstance> blocks = new Array<ModelInstance>();
	// public Array<ModelInstance> invaders = new Array<ModelInstance>();
	public ModelInstance book;
	public ModelInstance space;
	public ModelInstance table;
	public ModelInstance floor;
	private int total = 0;
	private float movementIncrement = 0.0006f;

	@Override
	public void create() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {

	}

}