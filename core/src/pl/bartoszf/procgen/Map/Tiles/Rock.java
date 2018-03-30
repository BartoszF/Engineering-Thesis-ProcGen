package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Rock extends Tile {

    public Rock() {
        super();
    }

    public Rock(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("stone"), position, 2f);
    }

    @Override
    public Tile clone() {
        return new Rock();
    }
}
