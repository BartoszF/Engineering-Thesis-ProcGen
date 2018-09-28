package pl.bartoszf.procgen.Generators.PathGenerators;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.math.Vector2;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import pl.bartoszf.procgen.Map.City;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.HouseFloor;
import pl.bartoszf.procgen.Map.Tiles.HouseWall;
import pl.bartoszf.procgen.Map.Tiles.Path;
import pl.bartoszf.procgen.Pathfinding.PathSearcher;
import pl.bartoszf.procgen.Pathfinding.TilePath;

import java.util.ArrayList;
import java.util.List;

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

        List<Pair<City, City>> pairs = new ArrayList<>();

        for (City c1 : map.getCities()) {
            Vector2 c1v = c1.getCenter();
            for (City c2 : map.getCities()) {
                if (c1.equals(c2)) continue;
                if (pairs.contains(new ImmutablePair<>(c1, c2)) || pairs.contains(new ImmutablePair<>(c2, c1)))
                    continue;
                Vector2 c2v = c2.getCenter();
                Tile tileA = map.getTileAt((int) c1v.x, (int) c1v.y);
                Tile tileB = map.getTileAt((int) c2v.x, (int) c2v.y);
                pathFinder.searchConnectionPath(tileA, tileB, GameMap.distance, tempSearch.getPath());
                pairs.add(new ImmutablePair<>(c1, c2));
                for (Connection<Tile> conn : tempSearch.getPath()) {
                    Tile from = conn.getFromNode();
                    Tile to = conn.getToNode();

                    if (from instanceof HouseFloor || from instanceof HouseWall) continue;
                    Vector2 newPos = new Vector2(from.getPosition().x / Tile.TILE_SIZE, from.getPosition().y / Tile.TILE_SIZE);
                    map.setTileAt((int) newPos.x, (int) newPos.y, new Path(newPos, from.getHeight()));
                }
                map.populateConnections();
            }
        }
    }
}
