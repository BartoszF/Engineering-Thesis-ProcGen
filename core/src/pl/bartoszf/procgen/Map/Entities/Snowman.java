package pl.bartoszf.procgen.Map.Entities;

import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Snowman extends Entity {

    public Snowman() {
        super(TextureManager.INSTANCE.getTexture("entities"), "snowman");
    }

    @Override
    public Entity clone() {
        return new Snowman();
    }
}
