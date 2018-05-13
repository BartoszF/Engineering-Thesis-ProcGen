package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Water extends Tile {

    public Water() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "water");
    }

    public Water(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "water", position, 5000f, height);
    }

    @Override
    public Tile clone() {
        return new Water();
    }
}
