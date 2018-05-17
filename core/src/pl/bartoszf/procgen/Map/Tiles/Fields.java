package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Entities.Wheat;
import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

import java.util.HashMap;

public class Fields extends Tile {

    public Fields() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "fields", 120f);
    }

    public Fields(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "fields", position, 120f, height);
    }

    @Override
    public Tile clone() {
        return new Fields();
    }

    @Override
    public HashMap<Entity, Float> setupPossibleEntities() {
        return new HashMap<Entity, Float>() {{
            put(new Wheat(), 1.0f);
        }};
    }

}
