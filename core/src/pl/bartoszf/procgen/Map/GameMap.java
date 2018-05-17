package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.bartoszf.procgen.Game;
import pl.bartoszf.procgen.Pathfinding.TileConnection;
import pl.bartoszf.procgen.Pathfinding.TileDistance;

import java.util.ArrayList;
import java.util.List;

public class GameMap implements IndexedGraph<Tile> {
    public static TileDistance distance = new TileDistance();
    Tile[][] tiles;
    List<City> cities;
    int size;
    SpriteBatch cityBatch;
    Rectangle camRect;
    Tile tempTile;

    public GameMap() {
        this.size = Game.GAME_SIZE;
        cities = new ArrayList<>();
        cityBatch = new SpriteBatch();
        camRect = new Rectangle();
    }

    public GameMap(int size) {
        this.size = size;
        cities = new ArrayList<>();
        cityBatch = new SpriteBatch();
        camRect = new Rectangle();
    }

    public void render(SpriteBatch sb, OrthographicCamera cam) {
        camRect.x = (cam.position.x - cam.viewportWidth / 2 * cam.zoom) / Tile.TILE_SIZE;
        camRect.y = (cam.position.y - cam.viewportHeight / 2 * cam.zoom) / Tile.TILE_SIZE;
        camRect.width = ((cam.viewportWidth + 5) * cam.zoom) / Tile.TILE_SIZE;
        camRect.height = ((cam.viewportHeight + 5) * cam.zoom) / Tile.TILE_SIZE;

        int cx = (int) camRect.x;
        int cy = (int) camRect.y;
        int cw = (int) (camRect.x + camRect.width);
        int ch = (int) (camRect.y + camRect.height + 1);

        for (int y = ch; y >= cy; y--) {
            for (int x = cx; x <= cw; x++) {
                if (x < 0 || x > size - 1)
                    continue;
                if (y < 0 || y > size - 1)
                    continue;

                tempTile = tiles[y][x];
                if (tempTile == null) continue;
                tempTile.draw(sb);
            }
        }

        sb.flush();
        sb.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        for (int y = ch; y >= cy - 1; y--) {
            for (int x = cx; x <= cw; x++) {
                if (x < 0 || x > size - 1)
                    continue;
                if (y < 0 || y > size - 1)
                    continue;

                tempTile = tiles[y][x];
                if (tempTile == null) continue;
                tempTile.drawEntity(sb);
            }
            if (y >= 0 && y < size && cx >= 0 && cx < size)
                Game.font.draw(sb, ".", tiles[y][cx].getPosition().x, tiles[y][cx].getPosition().y); //Dirty and slow hack to fix bug in spritebatch
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
                if (t == null)
                    continue;
                t.connections = new Array<>();
                if (x > 0) {
                    t.connections.add(new TileConnection(t, tiles[y][x - 1]));
                    /*if (y < size - 1) {
                        t.connections.add(new TileConnection(t, tiles[y + 1][x - 1]));
                    }
                    if (y > 0) {
                        t.connections.add(new TileConnection(t, tiles[y - 1][x - 1]));
                    }*/
                }
                if (y < size - 1) {
                    t.connections.add(new TileConnection(t, tiles[y + 1][x]));
                }
                if (x < size - 1) {
                    t.connections.add(new TileConnection(t, tiles[y][x + 1]));
                    /*if (y < size - 1) {
                        t.connections.add(new TileConnection(t, tiles[y + 1][x + 1]));
                    }
                    if (y > 0) {
                        t.connections.add(new TileConnection(t, tiles[y - 1][x + 1]));
                    }*/
                }
                if (y > 0) {
                    t.connections.add(new TileConnection(t, tiles[y - 1][x]));
                }
            }
        }
    }
}
