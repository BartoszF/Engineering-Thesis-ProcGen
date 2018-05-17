package pl.bartoszf.procgen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.bartoszf.procgen.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1366;
		config.height = 768;
        config.vSyncEnabled = false;
        config.foregroundFPS = 60;
        config.backgroundFPS = 0;
        new LwjglApplication(new Game(), config);
	}
}
