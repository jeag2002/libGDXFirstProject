package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.game.FirstTestGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 600;
		config.height = 900;
		
		config.title = "ShooterDemo";
		config.useGL30 = true;
		
		new LwjglApplication(new FirstTestGDX(), config);
	}
}
