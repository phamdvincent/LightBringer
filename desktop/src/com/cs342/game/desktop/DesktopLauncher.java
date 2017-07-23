package com.cs342.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cs342.game.LightBringer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = LightBringer.WIDTH;
		config.height = LightBringer.HEIGHT;
		config.title = LightBringer.TITLE;

		new LwjglApplication(new LightBringer(), config);
	}
}
