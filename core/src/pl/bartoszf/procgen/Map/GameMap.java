package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    public final int tileSize = 32;
    HashMap<Vector2, Tile> tiles;
    int size;

    public GameMap() {
        this.size = 1024;
        prepareMap();
    }

    public GameMap(int size) {
        this.size = size;
        prepareMap();
    }

    public void prepareMap() {
        tiles = new HashMap<>();
    }

    public void render(SpriteBatch sb, OrthographicCamera cam) {
        for (Map.Entry<Vector2, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            if (tile == null) continue;
            if (cam.frustum.sphereInFrustum(new Vector3(tile.getPosition().x, tile.getPosition().y, 0), tileSize)) {
                sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
            }
        }
    }

}
