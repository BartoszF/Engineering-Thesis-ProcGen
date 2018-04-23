package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.bartoszf.procgen.Game;

public class GameMap {
    Tile[][] tiles;//Map<Vector2, Tile> tiles;
    int size;

    public GameMap() {
        this.size = Game.GAME_SIZE;
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
        int points[] = new int[4];
        points[2] = (int) Math.floor(cam.frustum.planePoints[0].x / Tile.TILE_SIZE);
        points[3] = (int) Math.ceil(cam.frustum.planePoints[1].x / Tile.TILE_SIZE);
        points[0] = (int) Math.floor(cam.frustum.planePoints[0].y / Tile.TILE_SIZE);
        points[1] = (int) Math.ceil(cam.frustum.planePoints[2].y / Tile.TILE_SIZE);

        for (float y = points[1]; y >= points[0]; y -= 1.0f) {
            for (float x = points[2]; x <= points[3]; x += 1.0f) {
                if (x < 0 || x > size - 1) continue;
                if (y < 0 || y > size - 1) continue;

                Tile tile = tiles[(int) y][(int) x];
                if (tile == null || tile.getTexture() == null) continue;
                sb.draw(tile.getTextureRegion(), tile.position.x, tile.position.y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        return tiles[y][x];
    }

    public void setTileAt(int x, int y, Tile tile) {
        this.tiles[y][x] = tile;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
