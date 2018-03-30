package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Mountain extends Tile {

    public Mountain() {
        super();
    }

    public Mountain(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("stone"), position, 3.5f);
    }

    @Override
    public Tile clone() {
        return new Grass();
    }
}