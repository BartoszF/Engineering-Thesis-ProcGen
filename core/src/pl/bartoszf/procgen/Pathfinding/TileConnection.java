package pl.bartoszf.procgen.Pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import pl.bartoszf.procgen.Map.Tile;

public class TileConnection implements Connection<Tile> {
    Tile from, to;

    public TileConnection(Tile from, Tile to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public float getCost() {
        return from.getCost();
    }

    @Override
    public Tile getFromNode() {
        return from;
    }

    @Override
    public Tile getToNode() {
        return to;
    }

    public String toString() {
        return "";//from.biome.name + "(" + from.X + ", " + from.Y + ") -> " + to.biome.name +"("+ to.X + ", " + to.Y + ") with " + getCost();
    }
}
