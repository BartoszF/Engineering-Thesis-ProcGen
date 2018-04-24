package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.math.Vector2;

public class City {
    Vector2 center;
    String name;

    public City(Vector2 center) {
        this.center = center;
        //TODO: Generate name
    }

    public Vector2 getCenter() {
        return center;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
