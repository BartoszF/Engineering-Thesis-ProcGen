package pl.bartoszf.procgen.Combiners;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;

import java.util.List;

public class CityCombiner {

    private GameMap map;

    public CityCombiner(GameMap map) {
        this.map = map;
    }

    public void combine(List<Tile> tiles) {
        for (Tile tile : tiles) {
            Vector2 pos = tile.getPosition();
            map.setTileAt((int) (pos.x / Tile.TILE_SIZE), (int) (pos.y / Tile.TILE_SIZE), tile);
        }
    }
}
