package pl.bartoszf.procgen.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import pl.bartoszf.procgen.Game;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;

public class FrameRate implements Disposable {
    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera cam;


    public FrameRate() {
        lastTimeCounted = TimeUtils.millis();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void resize(int screenWidth, int screenHeight) {
        cam = new OrthographicCamera(screenWidth, screenHeight);
        cam.translate(screenWidth / 2, screenHeight / 2);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    public void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if (sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    public void render(OrthographicCamera camera2, GameMap map) {
        int x = (int) (camera2.position.x / Tile.TILE_SIZE);
        int y = (int) (camera2.position.y / Tile.TILE_SIZE);
        String tile = "null";
        if (x >= 0 && x < Game.GAME_SIZE && y >= 0 && y < Game.GAME_SIZE) {
            Tile centerTile = map.getTileAt(x, y);

            if (centerTile != null) {
                tile = centerTile.toString();//getClass().getName();
            }
        }
        batch.begin();
        font.draw(batch, (int) frameRate + " fps " + camera2.position.toString() + " zoom : " + camera2.zoom + " Tile at center : " + tile, 3, Gdx.graphics.getHeight() - 3);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}