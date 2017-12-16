package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tile {
    Vector2 position;
    float size;
    Texture texture;

    public Tile() {
    }

    public Tile(Texture texture, Vector2 position, float size) {
        this.texture = texture;
        this.position = position;
        this.size = size;
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
}
