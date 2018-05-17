package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import pl.bartoszf.procgen.Utils.GeneratorUtils;
import pl.bartoszf.procgen.Utils.TextureManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tile {

    public static float TILE_SIZE = 256;
    public Array<Connection<Tile>> connections;
    public HashMap<Entity, Float> possibleEntities;
    Vector2 position;
    float size;
    TextureAtlas texture;
    TextureRegion textureRegion;
    float height;
    float cost;
    String tileName;
    Entity entity;

    float temp;
    float moisture;

    public Tile() {
    }

    public Tile(TextureAtlas texture, String tileName, float cost) {
        this.texture = texture;
        this.setTextureRegion(TextureManager.INSTANCE.getRegion(texture, tileName));
        this.cost = cost;
        this.tileName = tileName;

        this.possibleEntities = setupPossibleEntities();
        prepareEntity();
    }

    public Tile(TextureAtlas texture, String tileName, Vector2 position, float cost, float height) {
        this.texture = texture;
        this.setTextureRegion(TextureManager.INSTANCE.getRegion(texture, tileName));
        this.position = position.scl(TILE_SIZE);
        this.size = TILE_SIZE;
        this.cost = cost;
        this.height = height;
        this.tileName = tileName;

        this.possibleEntities = setupPossibleEntities();
        prepareEntity();
    }

    public void prepareEntity() {
        float rand = GeneratorUtils.random.nextFloat();
        Iterator it = getPossibleEntities().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (rand <= (Float) pair.getValue()) {
                this.entity = ((Entity) pair.getKey()).clone();
            }
        }
    }

    public void draw(SpriteBatch sb) {
        float height = getHeight();
        float brightness = 0.9f + (height / 10f);
        sb.setColor(brightness, brightness, brightness, 1.0f);
        sb.draw(getTextureRegion(), position.x, position.y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
    }

    public void drawEntity(SpriteBatch sb) {
        if (this.entity != null) {
            entity.draw(sb, this);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position.scl(TILE_SIZE);
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
        //TextureRegion temp = new TextureRegion(textureRegion);
        this.textureRegion = textureRegion;//TextureManager.fixBleeding(temp);
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

    public HashMap<Entity, Float> setupPossibleEntities() {
        return new HashMap<>();
    }

    public HashMap<Entity, Float> getPossibleEntities() {
        return possibleEntities;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getMoisture() {
        return moisture;
    }

    public void setMoisture(float moisture) {
        this.moisture = moisture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;

        Tile tile = (Tile) o;

        return tileName.equals(tile.tileName);
    }

    @Override
    public int hashCode() {
        return tileName.hashCode();
    }
}
