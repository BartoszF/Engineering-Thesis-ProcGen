package pl.bartoszf.procgen.GeneratorConfigs;

import com.badlogic.gdx.math.Vector3;
import pl.bartoszf.procgen.Map.Tile;

public class TileDefinition implements Comparable<TileDefinition> {
    public Vector3 position;
    public Tile tile;
    public float radius;

    public TileDefinition(Vector3 pos, Tile tile, float radius) {
        this.position = pos;
        this.tile = tile;
        this.radius = radius;
    }

    @Override
    public int compareTo(TileDefinition o) {
        return position.dst(Vector3.Zero) > o.position.dst((Vector3.Zero)) ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TileDefinition)) return false;

        TileDefinition that = (TileDefinition) o;

        if (!position.equals(that.position)) return false;
        return tile.equals(that.tile);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + tile.hashCode();
        return result;
    }
}
