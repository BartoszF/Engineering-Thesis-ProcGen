package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class HouseFloor extends Tile {

    public HouseFloor() {
        super();
    }

    public HouseFloor(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "house_floor", position, 0.1f);
    }

    @Override
    public Tile clone() {
        return new HouseFloor();
    }
}
