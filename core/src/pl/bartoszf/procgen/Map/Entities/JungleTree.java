package pl.bartoszf.procgen.Map.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.bartoszf.procgen.Map.Entity;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.TextureManager;

public class JungleTree extends Entity {

    public JungleTree() {
        super(TextureManager.INSTANCE.getTexture("entities"), "jungle_tree");
    }

    @Override
    public boolean drawable(Tile tile) {

        if (tile.getMoisture() < 0.45f) return false;
        if (tile.getTemp() < 0.4f) return false;
        return true;
    }

    @Override
    public Entity clone() {
        return new JungleTree();
    }

    @Override
    public void draw(SpriteBatch sb, Tile tile) {
        if (!drawable(tile)) return;
        sb.draw(getTextureRegion(), tile.getPosition().x - Tile.TILE_SIZE / 4f, tile.getPosition().y, Tile.TILE_SIZE + Tile.TILE_SIZE / 2f, Tile.TILE_SIZE + Tile.TILE_SIZE);
    }
}
