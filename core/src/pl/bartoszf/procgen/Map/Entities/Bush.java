package pl.bartoszf.procgen.Map.Entities;

import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Bush extends Entity {

    public Bush() {
        super(TextureManager.INSTANCE.getTexture("entities"), "bush");
    }

    @Override
    public Entity clone() {
        return new Bush();
    }
}
