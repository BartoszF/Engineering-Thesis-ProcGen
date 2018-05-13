package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Sand extends Tile {

    public Sand() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "sand", 300f);
    }

    public Sand(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "sand", position, 300f, height);
    }

    @Override
    public Tile clone() {
        return new Sand();
    }
}
