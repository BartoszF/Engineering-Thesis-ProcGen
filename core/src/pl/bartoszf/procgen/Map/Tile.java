package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Tile {

    public static float TILE_SIZE = 128;
    public Array<Connection<Tile>> connections;
    Vector2 position;
    float size;
    TextureAtlas texture;
    TextureRegion textureRegion;
    float height;
    float cost;

    public Tile() {
    }

    public Tile(TextureAtlas texture, String tileName, Vector2 position, float cost) {
        this.texture = texture;
        this.setTextureRegion(texture.findRegion(tileName));
        this.position = position.scl(TILE_SIZE);
        this.size = TILE_SIZE;
        this.cost = cost;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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
        TextureRegion temp = new TextureRegion(textureRegion);
        this.textureRegion = TextureManager.fixBleeding(temp);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Tile clone() {
        return new Tile();
    }
}
