package pl.bartoszf.procgen.GeneratorConfigs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import pl.bartoszf.procgen.Generators.IslandGenerators.GeneratorTile;
import pl.bartoszf.procgen.Map.MultipleTile;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.*;

import java.util.Iterator;
import java.util.PriorityQueue;

public class LandCombinerConfig {

    public static PriorityQueue<TileDefinition> tileDefinitions = new PriorityQueue<>();

    public static void initDefinitions() {
        //Vector as follows : Height, Temp, Moisture
        //Shores
        //Cold Shores
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.1f, 0.1f), new Rock(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.1f, 0.5f), new Ice(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.1f, 0.75f), new Snow(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.2f, 0.1f), new Rock(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.2f, 0.5f), new Ice(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.2f, 0.75f), new Snow(), 0.32f));
        //Warm Shores
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.5f, 0.25f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.5f, 0.5f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.5f, 0.75f), new Grass(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.75f, 0.25f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.75f, 0.5f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.15f, 0.75f, 0.75f), new Sand(), 0.32f));

        //Normal land
        //Cold
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.15f, 0.15f), new Ice(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.15f, 0.6f), new Snow(), 0.32f));
        //Normal temp
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.5f, 0.1f), new DryGrass(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.5f, 0.3f), new Grass(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.5f, 0.5f), new Fields(), 0.2f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.5f, 0.9f), new Jungle(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.6f, 0.6f), new Fields(), 0.23f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.7f, 0.2f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.7f, 0.3f), new DryGrass(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.7f, 0.75f), new Jungle(), 0.32f));
        //Hot
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.9f, 0.2f), new Sand(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.9f, 0.475f), new DryGrass(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.4f, 0.9f, 0.75f), new Jungle(), 0.32f));

        //Mountains
        //Cold
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.15f, 0.0f), new Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.15f, 0.5f), new Snowy_Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.15f, 1.0f), new Snowy_Mountain(), 0.32f));
        //Otherwise
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.4f, 0.0f), new Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.4f, 0.5f), new Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.4f, 1.0f), new Snowy_Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.75f, 0.0f), new Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.75f, 0.5f), new Mountain(), 0.32f));
        tileDefinitions.add(new TileDefinition(new Vector3(0.8f, 0.75f, 1.0f), new Mountain(), 0.32f));

    }

    public static TileResult[] getNearest(Vector3 pos) {
        TileResult[] selected = new TileResult[2];

        float min = Float.POSITIVE_INFINITY;
        TileDefinition firstDef = null;

        Iterator<TileDefinition> it = tileDefinitions.iterator();
        while (it.hasNext()) {
            TileDefinition def = it.next();
            float distance = def.position.dst(pos);
            if (distance < min) {
                min = distance;
                firstDef = def;
                selected[0] = new TileResult(distance, def.tile);
            }
        }
        min = Float.POSITIVE_INFINITY;

        it = tileDefinitions.iterator();
        while (it.hasNext()) {
            TileDefinition def = it.next();
            float distance = def.position.dst(pos);
            float d = def.position.dst(firstDef.position);
            if (distance < min && !def.tile.equals(selected[0].tile) && d < def.radius && d < firstDef.radius) {
                min = distance;
                selected[1] = new TileResult(distance, def.tile);
            }
        }

        return selected;
    }

    public static Tile getMapTile(GeneratorTile tile, Vector2 pos) {
        //Ocean
        if (tile.getHeight() < 0.2f) {
            return new Water(pos, tile.getHeight());
        }

        if (tile.isRiver()) {
            return new Water(pos, tile.getHeight());
        }

        TileResult[] selected = getNearest(new Vector3(tile.getHeight(), tile.getTemp(), tile.getMoisture()));

        if (selected[1] == null || (selected[1] != null && selected[0].equals(selected[1]))) {
            Tile stile = selected[0].tile.clone();
            stile.setPosition(pos);
            stile.setHeight(tile.getHeight());
            stile.setMoisture(tile.getMoisture());
            stile.setTemp(tile.getTemp());
            return stile;
        } else {
            Tile stile = new MultipleTile(selected[0].tile.clone(), selected[1].tile.clone(), pos, tile.getHeight(), selected[0].distance, selected[1].distance);
            stile.setMoisture(tile.getMoisture());
            stile.setTemp(tile.getTemp());
            return stile;
        }
    }
}
