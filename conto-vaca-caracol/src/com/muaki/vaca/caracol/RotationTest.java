/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.muaki.vaca.caracol;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.materials.Material;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class RotationTest extends Game {
	Model modelo;
	ModelInstance modelInstance;
	ModelBatch modelBatch;
	Material material;
	Camera camera;

	public AssetManager assets;
	Model model;

	@Override
	public void create() {

		ModelBuilder builder = new ModelBuilder();
		modelo = builder.createBox(1, 1, 1, new Material(), Usage.Position
				| Usage.Normal | Usage.TextureCoordinates);

		modelInstance = new ModelInstance(modelo);

		assets = new AssetManager();
		assets.load("data/roomBI.g3db", Model.class);

		modelBatch = new ModelBatch();

		camera = new PerspectiveCamera(45, 4, 4);
		camera.position.set(3, 3, 3);
		camera.direction.set(-1, -1, -1);

	}

	@Override
	public void dispose() {
		model.dispose();
		modelBatch.dispose();
	}

	@Override
	public void render() {

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		camera.update();

		modelInstance.transform.rotate(Vector3.Y,
				30 * Gdx.graphics.getDeltaTime());

		modelBatch.begin(camera);
		modelBatch.render(modelInstance);
		modelBatch.end();
	}

}