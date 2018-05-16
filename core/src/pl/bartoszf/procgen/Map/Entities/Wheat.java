package pl.bartoszf.procgen.Map.Entities;

import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Wheat extends Entity {

    public Wheat() {
        super(TextureManager.INSTANCE.getTexture("entities"), "wheat");
    }

    @Override
    public Entity clone() {
        return new Wheat();
    }
}
