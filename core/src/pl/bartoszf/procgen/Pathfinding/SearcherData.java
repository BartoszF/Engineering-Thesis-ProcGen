package pl.bartoszf.procgen.Pathfinding;

import pl.bartoszf.procgen.Map.Tile;

public class SearcherData {
    public Tile from, to;

    public SearcherData(Tile from, Tile to) {
        this.from = from;
        this.to = to;
    }
}
