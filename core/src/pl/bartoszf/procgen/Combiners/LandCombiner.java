package pl.bartoszf.procgen.Combiners;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.GeneratorConfigs.LandCombinerConfig;
import pl.bartoszf.procgen.Generators.GeneratorTile;
import pl.bartoszf.procgen.Map.Tile;

import java.util.HashMap;
import java.util.Map;

public class LandCombiner {

    Map<Vector2, GeneratorTile> tiles;
    int size;

    public LandCombiner(Map<Vector2, GeneratorTile> tiles, int size) {
        this.tiles = tiles;
        this.size = size;
    }

    public Map<Vector2, Tile> combineLand() {
        Map<Vector2, Tile> land = new HashMap<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Vector2 pos = new Vector2(x, y);
                GeneratorTile tile = tiles.get(pos);
                Tile result = LandCombinerConfig.getMapTile(tile, pos);
                if (result != null)
                    land.put(pos, result);
            }
        }

        return land;
    }


}
