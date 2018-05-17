package pl.bartoszf.procgen.Map.Entities;

import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Wheat extends Entity {

    public Wheat() {
        super(TextureManager.INSTANCE.getTexture("entities"), "wheat");
    }

    @Override
    public boolean drawable(Tile tile) {

        if (tile.getMoisture() > 0.6f) return false;
        if (tile.getMoisture() < 0.3f) return false;
        if (tile.getTemp() > 0.6f) return false;
        if (tile.getTemp() < 0.3f) return false;
        return true;
    }
    @Override
    public Entity clone() {
        return new Wheat();
    }
}
