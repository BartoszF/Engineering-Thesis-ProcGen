package pl.bartoszf.procgen.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Sand extends Tile {

    public Sand() {
        super();
    }

    public Sand(Vector2 position, float size) {
        super(TextureManager.INSTANCE.getTexture("sand"), position, size);
    }

    @Override
    public Tile clone() {
        return new Sand();
    }
}
