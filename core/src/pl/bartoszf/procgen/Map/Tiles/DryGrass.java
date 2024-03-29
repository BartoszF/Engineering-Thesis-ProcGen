package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class DryGrass extends Tile {

    public DryGrass() {
        super(TextureManager.INSTANCE.getTexture("tiles"), "dry_land", 100f);
    }

    public DryGrass(Vector2 position, float height) {
        super(TextureManager.INSTANCE.getTexture("tiles"), "dry_land", position, 100f, height);
    }

    @Override
    public Tile clone() {
        return new DryGrass();
    }
}
