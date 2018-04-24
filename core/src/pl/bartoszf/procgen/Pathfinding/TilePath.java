package pl.bartoszf.procgen.Pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import pl.bartoszf.procgen.Map.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TilePath implements GraphPath<Connection<Tile>> {

    List<Connection<Tile>> path;

    public TilePath() {
        path = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Connection<Tile> get(int index) {
        return path.get(index);
    }

    @Override
    public void add(Connection<Tile> node) {
        path.add(node);
    }

    @Override
    public void clear() {
        path.clear();
    }

    @Override
    public void reverse() {
        Collections.reverse(path);
    }

    @Override
    public Iterator<Connection<Tile>> iterator() {
        return path.iterator();
    }
}
