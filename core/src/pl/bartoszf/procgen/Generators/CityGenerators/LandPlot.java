package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LandPlot {
    private Rectangle rect;

    public LandPlot() {

    }

    public LandPlot(Rectangle rect) {
        this.rect = rect;
    }

    public LandPlot(int x, int y, int w, int h) {
        rect = new Rectangle(x, y, w, h);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public float getX() {
        return rect.getX();
    }

    public float getY() {
        return rect.getY();
    }

    public float getW() {
        return rect.getWidth();
    }

    public float getH() {
        return rect.getHeight();
    }

    public Vector2 getCenterPoint() {
        return new Vector2((rect.x + rect.width) / 2, (rect.y + rect.height) / 2);
    }
}
