package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Entities.JungleTree;
import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

import java.util.HashMap;

public class Jungle extends Tile {

    public Jungle() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "jungle", 170f);
    }

    public Jungle(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "jungle", position, 170f, height);
    }

    @Override
    public Tile clone() {
        return new Jungle();
    }

    @Override
    public HashMap<Entity, Float> setupPossibleEntities() {
        return new HashMap<Entity, Float>() {{
            put(new JungleTree(), 0.8f);
        }};
    }
}
