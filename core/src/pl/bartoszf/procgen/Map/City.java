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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!center.equals(city.center)) return false;
        return name != null ? name.equals(city.name) : city.name == null;
    }

    @Override
    public int hashCode() {
        int result = center.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
