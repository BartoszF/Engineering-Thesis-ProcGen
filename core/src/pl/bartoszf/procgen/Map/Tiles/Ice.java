package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Ice extends Tile {

    public Ice() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "ice", 400f);
    }

    public Ice(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "ice", position, 400f, height);
    }

    @Override
    public Tile clone() {
        return new Ice();
    }
}
