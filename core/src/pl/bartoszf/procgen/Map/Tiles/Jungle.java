package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Jungle extends Tile {

    public Jungle() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "jungle");
    }

    public Jungle(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "jungle", position, 170f, height);
    }

    @Override
    public Tile clone() {
        return new Jungle();
    }
}
