package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Rock extends Tile {

    public Rock() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "stone");
    }

    public Rock(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "stone", position, 400f, height);
    }

    @Override
    public Tile clone() {
        return new Rock();
    }
}
