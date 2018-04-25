package pl.bartoszf.procgen.GeneratorConfigs;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Generators.IslandGenerators.GeneratorTile;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.*;

public class LandCombinerConfig {

    public static Tile getMapTile(GeneratorTile tile, Vector2 pos) {
        //Ocean
        if (tile.getHeight() < 0.2f) {
            return new Water(pos);
        }

        if (tile.isRiver()) {
            return new Water(pos);
        }

        //Shore
        if (tile.getHeight() >= 0.2f && tile.getHeight() <= 0.225f) {
            //Cold - icy shore
            if (tile.getTemp() < 0.3f) {
                if (tile.getMoisture() < 0.3f) {
                    return new Rock(pos);
                }
                if (tile.getMoisture() >= 0.3f) {
                    //Ice
                    return new Snow(pos);
                }
                return new Sand(pos);
            }
            //Warm
            if (tile.getTemp() >= 0.3f) {
                if (tile.getMoisture() < 0.5f) {
                    return new Sand(pos);
                }
                if (tile.getMoisture() >= 0.5f) {
                    //Grassy shore
                    return new Grass(pos);
                }
                return new Sand(pos);
            }

            return new Sand(pos);
        }

        //Normal land
        if (tile.getHeight() > 0.225f && tile.getHeight() <= 0.7f) {
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
                if (tile.getMoisture() < 0.4f) {
                    //Dry Land
                    return new DryGrass(pos);
                }
                if (tile.getMoisture() >= 0.4f && tile.getMoisture() <= 0.75f) {
                    return new Grass(pos);
                }
                if (tile.getMoisture() > 0.75f) {
                    return new Jungle(pos);
                }
            }
            if (tile.getTemp() >= 0.8f) {
                //Desert or jungle
                if (tile.getMoisture() < 0.45f) {
                    return new Sand(pos);
                }
                if (tile.getMoisture() >= 0.45f && tile.getMoisture() <= 0.5f) {
                    return new DryGrass(pos);
                }
                if (tile.getMoisture() > 0.5f) {
                    //Jungle
                    return new Jungle(pos);
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
