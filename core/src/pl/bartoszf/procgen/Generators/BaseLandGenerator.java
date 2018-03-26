package pl.bartoszf.procgen.Generators;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class BaseLandGenerator {

    private int size;
    private Map<Vector2, GeneratorTile> tiles;
    private Pixmap map;

    /**
     * Constructor for BaseLandGenerator class
     *
     * @param size Size of map
     */
    public BaseLandGenerator(int size) {
        this.size = size;
        this.tiles = new HashMap<>();
        this.map = new Pixmap(size, size, Pixmap.Format.RGBA8888);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                tiles.put(new Vector2(x, y), new GeneratorTile());
            }
        }
    }

    /**
     * Constructor for BaseLandGenerator that mutates tiles
     *
     * @param size  Size of map
     * @param tiles Tiles for BaseLandGenerator
     */
    public BaseLandGenerator(int size, Map<Vector2, GeneratorTile> tiles) {
        this.size = size;
        this.map = new Pixmap(size, size, Pixmap.Format.RGB888);
        this.tiles = tiles;
    }

    public void saveImage(String filename) {

    }

    public void drawOnMap(int x, int y, float val) {
        Color color = new Color(val, val, val, 1.0f);
        getMap().setColor(color);
        getMap().drawPixel(x, y);
    }

    /**
     * Method to generate tiles
     *
     * @return Generated or mutated GeneratorTile Map
     */
    public Map<Vector2, GeneratorTile> generate() {
        return tiles;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Vector2, GeneratorTile> getTiles() {
        return tiles;
    }

    public void setTiles(Map<Vector2, GeneratorTile> tiles) {
        this.tiles = tiles;
    }

    public Pixmap getMap() {
        return map;
    }

    public void setMap(Pixmap map) {
        this.map = map;
    }
}
