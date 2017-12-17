package pl.bartoszf.procgen.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Rock extends Tile {

    public Rock() {
        super();
    }

    public Rock(Vector2 position, float size) {
        super(TextureManager.INSTANCE.getTexture("stone"), position, size);
    }

    @Override
    public Tile clone() {
        return new Rock();
    }
}
