package pl.bartoszf.procgen.Pathfinding;

import com.badlogic.gdx.ai.pfa.Heuristic;
import pl.bartoszf.procgen.Map.Tile;

public class TileDistance implements Heuristic<Tile> {
    @Override
    public float estimate(Tile node, Tile endNode) {
        return node.getPosition().dst(endNode.getPosition());
    }
}
