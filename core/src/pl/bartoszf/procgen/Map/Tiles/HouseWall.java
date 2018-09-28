package pl.bartoszf.procgen.Map.Tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    //TODO: Wall should be entity
    @Override
    public void draw(SpriteBatch sb) {
        float height = getHeight();
        float brightness = 0.9f + (height / 10f);
        sb.setColor(brightness, brightness, brightness, 1.0f);
        sb.draw(getTextureRegion(), getPosition().x, getPosition().y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE * 2);
    }

    @Override
    public Tile clone() {
        return new HouseWall();
    }
}
