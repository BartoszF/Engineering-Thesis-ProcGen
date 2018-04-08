package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Snow extends Tile {

    public Snow() {
        super();
    }

    public Snow(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snow", position, 3f);
    }

    @Override
    public Tile clone() {
        return new Snow();
    }
}
