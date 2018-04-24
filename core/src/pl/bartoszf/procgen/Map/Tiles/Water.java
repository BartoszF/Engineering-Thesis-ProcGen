package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Water extends Tile {

    public Water() {
        super();
    }

    public Water(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "water", position, 3000000f);
    }

    @Override
    public Tile clone() {
        return new Water();
    }
}
