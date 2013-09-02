package com.muaki.vaca.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.muaki.vaca.caracol.Preferencias;
import com.muaki.vaca.simulations.Control;

public class Escrin  extends _DbgOfMainScreen {
//	public class ScreenMenuLayer extends MainScreen {
	private Stage stage;
	private Table table;
	private Button boton;
	private Skin skin;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Button botonRwdFwd2, botonRwdFwd;
	private Label label;
	private Preferencias prefs;
	private Control crtl;

	// private FPSLogger fps;

	@Override
	public void dispose() {

		stage.dispose();
		skin.dispose();
		font.dispose();
		atlas.dispose();

	}

	@Override
	public void hide() {
//		super.hide();

	}

	@Override
	public void pause() {
//		super.pause();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		
//		super.render(delta);
//		if (!loading) {

			//XXX para que saia o debug de table
			Table.drawDebug(stage);

			stage.act(delta);

			stage.draw();
//		}
		// fps.log();


	}

	@Override
	public void resize(int width, int height) {
//		super.resize(width, height);

		System.out.println("resize");

	}

	@Override
	public void resume() {
//		super.resume();

	}

	@Override
	public void show() {
//		super.show();
		// fps = new FPSLogger();
		
		prefs = new Preferencias();
		crtl = new Control();
		
		System.out.println(prefs.TITLE);
		
		stage = new Stage();
//		mpex.addProcessor(stage);
//		Gdx.input.setInputProcessor(mpex);

		font = new BitmapFont(Gdx.files.internal("data/arialrounded.fnt"),
				false);
	

		 Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("data/ui/screenmenu.pack");
		skin = new Skin(atlas);

		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//		XXX debug de table
		table.debug();

		table.bottom();
		stage.addActor(table);

		ButtonStyle style = new ButtonStyle();
		style.up = skin.getDrawable("arrow.left.up");
		style.down = skin.getDrawable("arrow.left.down");

		ButtonStyle style2 = new ButtonStyle();
		style2.up = skin.getDrawable("arrow.right.up");
		style2.down = skin.getDrawable("arrow.right.down");

		botonRwdFwd = new Button(style);

		botonRwdFwd2 = new Button(style2);

		botonRwdFwd2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Gdx.app.exit();
				label.setText("padiante");
				
				if(crtl.CurrPax<crtl.PaxsTot){
				crtl.CurrPax++;

		}
				
				
				System.out.println(crtl.CurrPax + " "+ crtl.CicloCorto());


			}
		});

		LabelStyle lablsty = new LabelStyle(font, Color.GREEN);

		
		label = new Label("text", lablsty);

		
		
		botonRwdFwd.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				// Gdx.app.exit();
				label.setText("patras");
				
				if(crtl.CurrPax>0){

				crtl.CurrPax--;
				}
				
				System.out.println(crtl.CurrPax + " "+ crtl.CicloCorto());
			
//				clicadopadiante = false;

			}
		});
		table.add();
		 table.add(label).left();
		 table.row();
		table.add(botonRwdFwd);

		table.add().size(700, 0);

		

		table.add(botonRwdFwd2);
		

//		 Table teibol = new Table();
//		 teibol.setTransform(true);
//		 teibol.setPosition(-400, -400);
//		 teibol.setOrigin(0, 0);
//		 teibol.setRotation(30);
//		 teibol.setColor(Color.WHITE);
//		 // teibol.setScale(2);
//		 teibol.add(label);
//		
//		 teibol.pack();
//		 teibol.debug();
//		
//		 table.add(teibol);
//		
//		
//		 //DEBUG lines de table
//		 table.debug();

		// batch = new SpriteBatch();
		// rgba8888 = new Texture("data/arrow.png");
	}

}
