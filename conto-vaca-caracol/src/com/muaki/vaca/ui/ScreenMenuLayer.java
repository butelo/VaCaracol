package com.muaki.vaca.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.muaki.vaca.caracol.Preferencias;
import com.muaki.vaca.screens.MainScreen;
import com.muaki.vaca.screens._DbgOfMainScreen;
import com.muaki.vaca.simulations.Control;

//aqui e onde podemos probar a interfaz e o funcionamiento
//para o resultado correcto substituir a _DbgOf e quedar con Main
public class ScreenMenuLayer extends _DbgOfMainScreen {
//	 public class ScreenMenuLayer extends MainScreen {

	private Stage stage;
	private Table table;
	private Button boton;
	private Skin skin;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Button botonRwdFwd2, botonRwdFwd;
	private Label label;
	private Preferencias prefs;
	

//	 private FPSLogger fps;

	@Override
	public void dispose() {
		super.dispose();

		stage.dispose();
		skin.dispose();
		font.dispose();
		atlas.dispose();

	}

	@Override
	public void hide() {
		super.hide();

	}

	@Override
	public void pause() {
		super.pause();

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (!loading) {
			// XXX para que saia o debug de table
			Table.drawDebug(stage);

			stage.act(delta);

			stage.draw();
			
		}
		// fps.log();

		// batch.begin();
		//
		// if(!loading){
		// batch.draw(rgba8888, 850, 0);
		// batch.draw(rgba8888, 175, 0, -140, 140);
		// }
		//
		// batch.end();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		System.out.println("resize");

	}

	@Override
	public void resume() {
		super.resume();

	}

	@Override
	public void show() {
		super.show();
//		 fps = new FPSLogger();

		// coas preferencias e con control o que facemos e levar conta das
		// paxinas e dos ciclos
		prefs = new Preferencias();
		crtl = new Control();

		// System.out.println(prefs.TITLE);

		stage = new Stage();
		mpex.addProcessor(stage);
		Gdx.input.setInputProcessor(mpex);

		font = new BitmapFont(Gdx.files.internal("data/arialrounded.fnt"),
				false);

		// Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("data/ui/screenmenu.pack");
		skin = new Skin(atlas);

		// creamos a tabla e damoslle as settings correspondentes metemola na stage
		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// XXX debug de table
		table.debug();
		table.bottom();
		stage.addActor(table);

		// definimos os estilos dos botons flecha padiante e flecha patras:
		ButtonStyle style = new ButtonStyle();
		style.up = skin.getDrawable("arrow.left.up");
		style.down = skin.getDrawable("arrow.left.down");

		ButtonStyle style2 = new ButtonStyle();
		style2.up = skin.getDrawable("arrow.right.up");
		style2.down = skin.getDrawable("arrow.right.down");

		botonRwdFwd = new Button(style);
		botonRwdFwd2 = new Button(style2);

		// clicamos para ir para adiante
		botonRwdFwd2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Gdx.app.exit();
				label.setText("padiante");
				clicadopadiante = true;

				if (crtl.CurrPax < crtl.PaxsTot) {
					crtl.CurrPax++;

				}

				System.out.println(crtl.CurrPax + " " + crtl.CicloCorto());

			}
		});

		// clicamos no boton para atras
		botonRwdFwd.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
//				Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());
				
				
				clicadopadiante = false;

				// Gdx.app.exit();
				label.setText("patras");

				if (crtl.CurrPax > 0) {

					crtl.CurrPax--;
				}

				System.out.println(crtl.CurrPax + " " + crtl.CicloCorto());

				// clicadopadiante = false;

			}
		});

		// texto que aparece en pantalla
		LabelStyle lablsty = new LabelStyle(font, Color.GREEN);
		label = new Label("text", lablsty);
		
		// metemos todo o que creamos na tabla
		table.add();
		table.add(label).left();
		table.row();
		table.add(botonRwdFwd);
		table.add().size(700, 0);
		table.add(botonRwdFwd2);

		// Table teibol = new Table();
		// teibol.setTransform(true);
		// teibol.setPosition(-400, -400);
		// teibol.setOrigin(0, 0);
		// teibol.setRotation(30);
		// // teibol.setScale(2);
		// teibol.add(botonRwdFwd);
		//
		// teibol.pack();
		// teibol.debug();
		//
		// table.add(teibol);
		//
		//
		// //DEBUG lines de table
		// table.debug();

		// batch = new SpriteBatch();
		// rgba8888 = new Texture("data/arrow.png");
	}

}
