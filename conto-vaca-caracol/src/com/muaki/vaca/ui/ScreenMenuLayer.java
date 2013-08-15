package com.muaki.vaca.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.muaki.vaca.screens.MainScreen;

public class ScreenMenuLayer extends MainScreen{
	private SpriteBatch batch;
	Texture rgba8888;

	

	@Override
	public void render(float delta) {
		super.render(delta);
		
		
		
		
		batch.begin();
		
		if(!loading){
		batch.draw(rgba8888, 850, 0);
		batch.draw(rgba8888, 175, 0, -140, 140);
		}
		
		batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		super.show();
		batch = new SpriteBatch();
		rgba8888 = new Texture("data/arrow.png");
		
		
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		super.dispose();
		
		
		batch.dispose();
		rgba8888.dispose();
		
		
	}

}
