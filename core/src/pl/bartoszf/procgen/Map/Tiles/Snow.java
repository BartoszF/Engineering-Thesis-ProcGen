package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Entities.Snowman;
import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

import java.util.HashMap;

public class Snow extends Tile {

    public Snow() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snow", 300f);
    }

    public Snow(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snow", position, 300f, height);
    }

    @Override
    public Tile clone() {
        return new Snow();
    }

    @Override
    public HashMap<Entity, Float> setupPossibleEntities() {
        return new HashMap<Entity, Float>() {{
            put(new Snowman(), 0.001f);
        }};
    }
}
