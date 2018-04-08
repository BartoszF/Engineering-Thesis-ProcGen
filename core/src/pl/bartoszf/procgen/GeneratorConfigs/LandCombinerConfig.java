package pl.bartoszf.procgen.GeneratorConfigs;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Generators.GeneratorTile;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.*;

public class LandCombinerConfig {

    public static Tile getMapTile(GeneratorTile tile, Vector2 pos) {
        //Ocean
        if (tile.getHeight() < 0.2f) {
            return null;
        }

        if (tile.isRiver()) {
            return null;
        }

        //Shore
        if (tile.getHeight() >= 0.2f && tile.getHeight() <= 0.25f) {
            //Warm
            if (tile.getTemp() >= 0.3f) {
                if (tile.getMoisture() < 0.5f) {
                    return new Sand(pos);
                }
                if (tile.getMoisture() >= 0.5f) {
                    //Grassy shore
                    return new Grass();
                }
                return new Sand(pos);
            }
            //Cold - icy shore
            if (tile.getTemp() < 0.3f) {
                if (tile.getMoisture() < 0.3f) {
                    return new Rock(pos);
                }
                if (tile.getMoisture() >= 0.5f) {
                    //Ice
                }
                return new Sand(pos);
            }
            return new Sand(pos);
        }

        //Normal land
        if (tile.getHeight() > 0.25f && tile.getHeight() <= 0.7f) {
            if (tile.getTemp() < 0.3f) {
                //Cold
                if (tile.getMoisture() < 0.3f) {
                    return new Rock(pos);
                }
                if (tile.getMoisture() >= 0.3f) {
                    return new Snow(pos);
                }
            }
            if (tile.getTemp() >= 0.3f && tile.getTemp() < 0.8f) {
                //Normal temp
            }
            if (tile.getTemp() >= 0.8f) {
                //Desert or jungle
                if (tile.getMoisture() < 0.5f) {
                    return new Sand(pos);
                }
                if (tile.getMoisture() >= 0.5f) {
                    //Jungle
                }
            }
            return new Grass(pos);
        }

        //Mountains
        if (tile.getHeight() > 0.7f) {
            if (tile.getTemp() >= 0.3f) {
                //Ok
            }
            if (tile.getTemp() < 0.3f) {
                //Icy mountain
            }
            return new Mountain(pos);
        }

        return null;
    }
}
