package com.bees.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bees.game.MyBeesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = MyBeesGame.WIDTH;
		config.height = MyBeesGame.HEIGHT;
		config.resizable = true;
		new LwjglApplication(new MyBeesGame(), config);
	}
}
