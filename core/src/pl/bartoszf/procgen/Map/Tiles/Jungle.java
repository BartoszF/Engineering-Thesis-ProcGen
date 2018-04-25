package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Jungle extends Tile {

    public Jungle() {
        super();
    }

    public Jungle(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "jungle", position, 170f);
    }

    @Override
    public Tile clone() {
        return new Jungle();
    }
}
