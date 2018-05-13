package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Grass extends Tile {

    public Grass() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "grass", 100f);
    }

    public Grass(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "grass", position, 100f, height);
    }

    @Override
    public Tile clone() {
        return new Grass();
    }
}
