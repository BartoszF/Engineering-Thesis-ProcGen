package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.bartoszf.procgen.Game;
import pl.bartoszf.procgen.Pathfinding.TileConnection;
import pl.bartoszf.procgen.Pathfinding.TileDistance;

import java.util.ArrayList;
import java.util.List;

public class GameMap implements IndexedGraph<Tile> {
    public static TileDistance distance = new TileDistance();
    Tile[][] tiles;//Map<Vector2, Tile> tiles;
    List<City> cities;
    int size;

    public GameMap() {
        this.size = Game.GAME_SIZE;
        cities = new ArrayList<>();
        //prepareMap();
    }

    public GameMap(int size) {
        this.size = size;
        cities = new ArrayList<>();
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

    public void addCity(City city) {
        cities.add(city);
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public int getIndex(Tile node) {
        Vector2 pos = node.getPosition();
        return (int) (pos.x / Tile.TILE_SIZE) * size + (int) (pos.y / Tile.TILE_SIZE);
    }

    @Override
    public int getNodeCount() {
        return size * size;
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        return fromNode.connections;
    }

    public void populateConnections() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Tile t = tiles[y][x];
                if (t == null) continue;
                t.connections = new Array<>();
                if (x > 0) {
                    t.connections.add(new TileConnection(t, tiles[y][x - 1]));
                    if (y < size - 1) {
                        t.connections.add(new TileConnection(t, tiles[y + 1][x - 1]));
                    }
                    if (y > 0) {
                        t.connections.add(new TileConnection(t, tiles[y - 1][x - 1]));
                    }
                }
                if (y < size - 1) {
                    t.connections.add(new TileConnection(t, tiles[y + 1][x]));
                }
                if (x < size - 1) {
                    t.connections.add(new TileConnection(t, tiles[y][x + 1]));
                    if (y < size - 1) {
                        t.connections.add(new TileConnection(t, tiles[y + 1][x + 1]));
                    }
                    if (y > 0) {
                        t.connections.add(new TileConnection(t, tiles[y - 1][x + 1]));
                    }
                }
                if (y > 0) {
                    t.connections.add(new TileConnection(t, tiles[y - 1][x]));
                }
            }
        }
    }
}
