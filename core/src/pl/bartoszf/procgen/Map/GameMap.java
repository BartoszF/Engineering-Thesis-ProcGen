package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameMap {
    Tile[][] tiles;//Map<Vector2, Tile> tiles;
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

        tiles = new Tile[size][size];
    }

    public void render(SpriteBatch sb, OrthographicCamera cam) {
        Vector3 bottomLeft = cam.frustum.planePoints[0];
        //Vector3 topLeft = cam.frustum.planePoints[3];
        Vector3 topRight = cam.frustum.planePoints[2];
        //Vector3 bottomRight = cam.frustum.planePoints[1];

        int minX = ((int) Math.floor(bottomLeft.x) / (int) Tile.TILE_SIZE) - 6;
        int maxX = ((int) topRight.x / (int) Tile.TILE_SIZE) + 6;
        int minY = ((int) Math.floor(bottomLeft.y) / (int) Tile.TILE_SIZE) - 6;
        int maxY = ((int) topRight.y / (int) Tile.TILE_SIZE) + 6;

        for (int y = minY; y <= maxY; y += 1) {
            for (int x = minX; x <= maxX; x += 1) {
                if (x < 0 || x > size - 1) continue;
                if (y < 0 || y > size - 1) continue;

                Tile tile = tiles[y][x];
                if (tile == null || tile.getTexture() == null) continue;
                sb.draw(tile.getTextureRegion(), tile.position.x, tile.position.y, Tile.TILE_SIZE, Tile.TILE_SIZE);
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
