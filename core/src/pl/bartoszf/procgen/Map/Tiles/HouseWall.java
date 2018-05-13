package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class HouseWall extends Tile {

    public HouseWall() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "house_wall", 100000f);
    }

    public HouseWall(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "house_wall", position, 100000f, height);
    }

    @Override
    public Tile clone() {
        return new HouseWall();
    }
}
