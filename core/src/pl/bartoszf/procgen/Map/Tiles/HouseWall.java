package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class HouseWall extends Tile {

    public HouseWall() {
        super();
    }

    public HouseWall(Vector2 position) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "house_wall", position, 0f);
    }

    @Override
    public Tile clone() {
        return new HouseWall();
    }
}
