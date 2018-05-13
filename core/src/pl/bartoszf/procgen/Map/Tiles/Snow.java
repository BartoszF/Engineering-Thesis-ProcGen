package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Snow extends Tile {

    public Snow() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snow");
    }

    public Snow(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snow", position, 300f, height);
    }

    @Override
    public Tile clone() {
        return new Snow();
    }
}
