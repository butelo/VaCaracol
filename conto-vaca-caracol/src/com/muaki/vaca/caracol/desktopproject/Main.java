package com.muaki.vaca.caracol.desktopproject;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.muaki.vaca.caracol.CowSnail;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.samples = 3;
		cfg.width = 1025;
		cfg.height = 769;
		cfg.vSyncEnabled = true;
		

		new LwjglApplication(new CowSnail(), cfg);

	}
}
