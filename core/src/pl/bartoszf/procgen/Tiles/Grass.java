package pl.bartoszf.procgen.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Grass extends Tile {

    public Grass() {
        super();
    }

    public Grass(Vector2 position, float size) {
        super(TextureManager.INSTANCE.getTexture("grass"), position, size);
    }

    @Override
    public Tile clone() {
        return new Grass();
    }
}
