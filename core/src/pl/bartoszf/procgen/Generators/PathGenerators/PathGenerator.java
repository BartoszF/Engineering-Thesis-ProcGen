package pl.bartoszf.procgen.Generators.PathGenerators;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.City;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.Path;
import pl.bartoszf.procgen.Pathfinding.PathSearcher;
import pl.bartoszf.procgen.Pathfinding.TilePath;

public class PathGenerator {

    GameMap map;

    public PathGenerator(GameMap map) {
        this.map = map;
    }

    public void generate() {
        PathFinder pathFinder = new IndexedAStarPathFinder<Tile>(map);
        PathSearcher tempSearch = new PathSearcher() {
            public TilePath path = new TilePath();

            @Override
            public TilePath getPath() {
                return path;
            }
        };
        for (City c1 : map.getCities()) {
            Vector2 c1v = c1.getCenter();
            for (City c2 : map.getCities()) {
                if (c1.equals(c2)) continue;
                Vector2 c2v = c2.getCenter();
                pathFinder.searchConnectionPath(map.getTileAt((int) c1v.x, (int) c1v.y), map.getTileAt((int) c2v.x, (int) c2v.y), GameMap.distance, tempSearch.getPath());
                for (Connection<Tile> conn : tempSearch.getPath()) {
                    Tile from = conn.getFromNode();
                    Tile to = conn.getToNode();

                    map.setTileAt((int) (from.getPosition().x / Tile.TILE_SIZE), (int) (from.getPosition().y / Tile.TILE_SIZE), new Path(from.getPosition())); //TODO: Change to path tile
                }
                map.populateConnections();
            }
        }
    }
}
