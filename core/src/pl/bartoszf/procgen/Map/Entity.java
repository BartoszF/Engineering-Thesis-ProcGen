package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Entity {

    TextureAtlas texture;
    TextureRegion textureRegion;

    public Entity() {
    }

    public Entity(TextureAtlas texture, String tileName) {
        this.texture = texture;
        this.setTextureRegion(TextureManager.INSTANCE.getRegion(texture, tileName));
    }

    public void draw(SpriteBatch sb, Tile tile) {
        if (!drawable(tile)) return;
        sb.draw(getTextureRegion(), tile.getPosition().x + 4, tile.getPosition().y + 4, Tile.TILE_SIZE - 8, Tile.TILE_SIZE - 8);
        //if(GeneratorUtils.random.nextFloat() < 0.1f)
        //    Game.font.draw(sb,".",tile.getPosition().x,tile.getPosition().y); //Dirty and slow hack to fix bug in spritebatch
    }

    public boolean drawable(Tile tile) {
        return true;
    }

    public TextureAtlas getTexture() {
        return texture;
    }

    public void setTexture(TextureAtlas texture) {
        this.texture = texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        //TextureRegion temp = new TextureRegion(textureRegion);
        this.textureRegion = textureRegion;//TextureManager.fixBleeding(temp);
    }

    public Entity clone() {
        return new Entity();
    }
}
