package pl.bartoszf.procgen.GeneratorConfigs;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Generators.GeneratorTile;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.Grass;
import pl.bartoszf.procgen.Map.Tiles.Mountain;
import pl.bartoszf.procgen.Map.Tiles.Sand;

public class LandCombinerConfig {

    public static Tile getMapTile(GeneratorTile tile, Vector2 pos) {
        if (tile.getHeight() < 0.2f) {
            return null;
        }

        if (tile.isRiver()) {
            return null;
        }

        if (tile.getHeight() >= 0.2f && tile.getHeight() <= 0.25f) {
            return new Sand(pos);
        }

        if (tile.getHeight() > 0.25f && tile.getHeight() <= 0.7f) {
            return new Grass(pos);
        }

        if (tile.getHeight() > 0.7f) {
            return new Mountain(pos);
        }

        return null;
    }
}
