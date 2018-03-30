package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    public static float TILE_SIZE = 32;
    Vector2 position;
    float size;
    Texture texture;
    float height;
    float cost;

    public Tile() {
    }

    public Tile(Texture texture, Vector2 position, float cost) {
        this.texture = texture;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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
