package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

public class Building {

    private Rectangle rect;

    public Building(LandPlot plot) {
        rect = new Rectangle();

        rect.setX(plot.getX() + GeneratorUtils.random.nextInt((int) Math.ceil((int) (plot.getW() / 4.0f))));
        rect.setY(plot.getY() + GeneratorUtils.random.nextInt((int) Math.ceil((int) (plot.getH() / 4.0f))));

        float w = plot.getW() - (rect.getX() - plot.getX());
        float h = plot.getH() - (rect.getY() - plot.getY());

        w -= GeneratorUtils.random.nextFloat() * (w / 4.0f);
        h -= GeneratorUtils.random.nextFloat() * (h / 4.0f);

        rect.setWidth(w);
        rect.setHeight(h);
    }

    public Rectangle getRect() {
        return rect;
    }
}
