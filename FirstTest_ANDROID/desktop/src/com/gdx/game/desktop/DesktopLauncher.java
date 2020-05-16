package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.game.FirstTestGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 640;
		config.height = 1000;
		
		config.title = "ShooterDemo";
		config.useGL30 = true;
		
		new LwjglApplication(new FirstTestGDX(), config);
	}
}
