package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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

        int minX = ((int) Math.floor(bottomLeft.x) / (int) Tile.TILE_SIZE) - 1;
        int maxX = ((int) topRight.x / (int) Tile.TILE_SIZE) - 1;
        int minY = (int) Math.floor(bottomLeft.y) / (int) Tile.TILE_SIZE;
        int maxY = (int) topRight.y / (int) Tile.TILE_SIZE;

        minX = minX < 0 ? 0 : minX;
        minY = minY < 0 ? 0 : minY;
        maxX = maxX >= size ? size - 1 : maxX;
        maxY = maxY >= size ? size - 1 : maxY;

        for (int y = minY; y <= maxY; y += 1) {
            for (int x = minX; x <= maxX; x += 1) {
                Vector2 pos = new Vector2(x, y);
                Tile tile = tiles[y][x];
                if (tile == null || tile.getTexture() == null) continue;
                    sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
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
