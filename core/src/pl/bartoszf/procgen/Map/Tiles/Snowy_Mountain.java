package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Snowy_Mountain extends Tile {

    public Snowy_Mountain() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snowy_mountain");
    }

    public Snowy_Mountain(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "snowy_mountain", position, 400f, height);
    }

    @Override
    public Tile clone() {
        return new Snowy_Mountain();
    }
}
