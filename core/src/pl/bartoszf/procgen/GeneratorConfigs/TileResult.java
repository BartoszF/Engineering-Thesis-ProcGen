package pl.bartoszf.procgen.GeneratorConfigs;

import pl.bartoszf.procgen.Map.Tile;

public class TileResult {
    public float distance;
    public Tile tile;

    public TileResult(float distance, Tile tile) {
        this.distance = distance;
        this.tile = tile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TileResult)) return false;

        TileResult that = (TileResult) o;

        return tile.equals(that.tile);
    }

    @Override
    public int hashCode() {
        return tile.hashCode();
    }
}
