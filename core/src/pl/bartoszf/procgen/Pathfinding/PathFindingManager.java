package pl.bartoszf.procgen.Pathfinding;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathFindingManager {
    public static PathFindingManager Instance;
    public static int NUM_PER_FRAME = 30;
    IndexedAStarPathFinder<Tile> pathFinder;

    Map<PathSearcher, SearcherData> searchers = new LinkedHashMap<>();

    public PathFindingManager(GameMap world) {
        Instance = this;
        pathFinder = new IndexedAStarPathFinder<Tile>(world);
    }

    public void requestPath(PathSearcher searcher, Tile from, Tile to) {
        searchers.put(searcher, new SearcherData(from, to));
    }

    public void providePaths() {
        Iterator it = searchers.entrySet().iterator();
        int i = 0;
        while (it.hasNext() && i < NUM_PER_FRAME) {
            Map.Entry pair = (Map.Entry) it.next();
            PathSearcher searcher = (PathSearcher) pair.getKey();
            SearcherData data = (SearcherData) pair.getValue();
            pathFinder.searchConnectionPath(data.from, data.to, GameMap.distance, searcher.getPath());
            it.remove(); // avoids a ConcurrentModificationException
            i++;
        }
    }


}
