package pl.bartoszf.procgen.GeneratorConfigs;

import pl.bartoszf.procgen.Map.Tile;

public class TileResult {
    public float distance;
    public Tile tile;

    public TileResult(float distance, Tile tile) {
        this.distance = distance;
        this.tile = tile;
    }
}
