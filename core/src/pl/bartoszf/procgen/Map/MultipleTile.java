package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MultipleTile extends Tile {

    Tile tileA, tileB;
    float aFactor, bFactor; //Must sum to 1.0f!!

    public MultipleTile(Tile tileA, Tile tileB, Vector2 pos, float height, float aFactor, float bFactor) {
        this.tileA = tileA;
        this.tileB = tileB;
        this.position = pos.scl(TILE_SIZE);
        this.size = TILE_SIZE;
        this.cost = (tileA.getCost() + tileB.getCost()) / 2;
        this.height = height;

        float sum = aFactor + bFactor;
        this.aFactor = bFactor / sum;
        this.bFactor = aFactor / sum;
    }

    @Override
    public void draw(SpriteBatch sb) {
        float height = getHeight();
        float brightness = 0.9f + (height / 10f);
        sb.setColor(brightness, brightness, brightness, this.aFactor);
        sb.draw(tileA.getTextureRegion(), position.x, position.y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
        sb.setColor(brightness, brightness, brightness, this.bFactor);
        sb.draw(tileB.getTextureRegion(), position.x, position.y, Tile.TILE_SIZE + 2, Tile.TILE_SIZE + 2);
    }

    public Tile getTileA() {
        return tileA;
    }

    public Tile getTileB() {
        return tileB;
    }

    @Override
    public String toString() {
        return "Multiple tile : " + tileA + " (" + aFactor + "), " + tileB + " (" + bFactor + ")";
    }
}
