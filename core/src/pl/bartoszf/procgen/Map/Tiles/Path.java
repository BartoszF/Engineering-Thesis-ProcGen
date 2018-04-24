package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Path extends Tile {

    public Path() {
        super();
    }

    public Path(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "path", position, 0.1f);
    }

    @Override
    public Tile clone() {
        return new Path();
    }
}
