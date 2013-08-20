package com.muaki.vaca.caracol;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.muaki.vaca.screens.Escrin;
import com.muaki.vaca.ui.ScreenMenuLayer;
//import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;

public class CowSnail extends Game {

	private Screen screen;

	@Override
	public void create() {
		screen = new ScreenMenuLayer();
//		pantalla para probas
//		screen = new Escrin();
		
		setScreen(screen);
		
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	@Override
	public void render() {
		super.render();
		
	}

	@Override
	public void pause() {
		super.pause();
		
	}

	@Override
	public void resume() {
		super.render();
		
	}

	@Override
	public void dispose() {
		super.dispose();
		
		screen.dispose();
		
	}
	
	
	
}
