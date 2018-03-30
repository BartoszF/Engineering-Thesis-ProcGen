package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    Map<Vector2, Tile> tiles;
    int size;

    public GameMap() {
        this.size = 1024;
        //prepareMap();
    }

    public GameMap(int size) {
        this.size = size;
        //prepareMap();
    }

    public void prepareMap() {
        tiles = new HashMap<>();
    }

    public void render(SpriteBatch sb, OrthographicCamera cam) {
        //Nope... Get points from frustum
        /*for (Map.Entry<Vector2, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            if (tile == null || tile.getTexture() == null) continue;
            if (cam.frustum.sphereInFrustum(new Vector3(tile.getPosition().x, tile.getPosition().y, 0), Tile.TILE_SIZE)) {
                sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
            }
        }*/

        //Here
        Vector3 bottomLeft = cam.frustum.planePoints[0];
        Vector3 topLeft = cam.frustum.planePoints[3];
        Vector3 topRight = cam.frustum.planePoints[2];
        Vector3 bottomRight = cam.frustum.planePoints[1];

        float minX = (float) Math.floor(bottomLeft.x);
        float maxX = topRight.x;
        float minY = (float) Math.floor(bottomLeft.y);
        float maxY = topRight.y;

        for (float y = minY; y <= maxY; y += 1.0f) {
            for (float x = minX; x <= maxX; x += 1.0f) {
                Vector2 pos = new Vector2(x, y);
                Tile tile = tiles.get(pos);
                if (tile == null || tile.getTexture() == null) continue;
                if (cam.frustum.sphereInFrustum(new Vector3(tile.getPosition().x, tile.getPosition().y, 0), Tile.TILE_SIZE)) {
                    sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
                }
            }
        }
    }

    public Map<Vector2, Tile> getTiles() {
        return tiles;
    }

    public void setTiles(Map<Vector2, Tile> tiles) {
        this.tiles = tiles;
    }
}
