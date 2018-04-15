package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

public class Building {

    private Rectangle rect;

    public Building(LandPlot plot) {
        rect = new Rectangle();

        rect.setX(plot.getX() + GeneratorUtils.random.nextInt((int) Math.floor((int) (plot.getW() / 3))));
        rect.setY(plot.getY() + GeneratorUtils.random.nextInt((int) Math.floor((int) (plot.getH() / 3))));

        float w = plot.getW() - (rect.getX() - plot.getX());
        float h = plot.getH() - (rect.getY() - plot.getY());

        w -= GeneratorUtils.random.nextFloat() * (w / 3);
        h -= GeneratorUtils.random.nextFloat() * (h / 3);

        rect.setWidth(w);
        rect.setHeight(h);
    }

    public Rectangle getRect() {
        return rect;
    }
}
