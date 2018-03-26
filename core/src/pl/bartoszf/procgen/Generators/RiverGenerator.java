package pl.bartoszf.procgen.Generators;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class RiverGenerator extends BaseLandGenerator {

    float distanceBetweenPoints = 10f;

    /**
     * @param size
     */
    public RiverGenerator(int size) {
        super(size);
    }

    /**
     * @param size
     * @param tiles
     */
    public RiverGenerator(int size, Map<Vector2, GeneratorTile> tiles) {
        super(size, tiles);
    }

    @Override
    public Map<Vector2, GeneratorTile> generate() {

        //TODO: Get TOP 4(?) highest points on map

        TreeMap<Float, Vector2> highestPoints = new TreeMap<>();
        Map<Vector2, GeneratorTile> tiles = getTiles();

        //Rly? I can do something smarter...
        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                Vector2 pos = new Vector2(x, y);
                float height = tiles.get(pos).getHeight();

                if (highestPoints.size() < 4)
                    highestPoints.put(height, pos);
                else {
                    if (highestPoints.firstKey() < height) {
                        boolean valid = true;
                        //TODO: Check distance
                        for (Float f : highestPoints.keySet()) {
                            if (highestPoints.get(f).dst(pos) < distanceBetweenPoints)
                                valid = false;
                        }
                        if (valid) {
                            highestPoints.put(height, pos);
                            highestPoints.remove(highestPoints.firstKey());
                        }
                    }
                }
            }
        }

        //TODO: Cellular automata them

        for (Float f : highestPoints.keySet()) {
            List<Vector2> trail = new ArrayList<>();

            Vector2 position = highestPoints.get(f);
            do {
                trail.add(position);
                getTiles().get(position).setRiver(true);
                drawOnMap((int) position.x, (int) position.y, 1f);
                Map<Vector2, GeneratorTile> neigh = getNeighbours(position.x, position.y);
                Vector2 lower = new Vector2(position);
                float lowerHeight = getTiles().get(position).getHeight();

                for (Map.Entry<Vector2, GeneratorTile> pair : neigh.entrySet()) {
                    if (pair.getValue().getHeight() <= lowerHeight) {
                        lower = pair.getKey();
                        lowerHeight = pair.getValue().getHeight();
                    }
                }
                //TODO: Check multiple by moisture

                position = lower;
            } while (!trail.contains(position) || getTiles().get(position).getHeight() <= 0.0f);


        }

        //TODO: ??
        //TODO: Profit

        /*for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                Vector2 pos = new Vector2(x, y);
                double temp = fastNoise.GetNoise(x, y);
                float gradient = GeneratorUtils.getGradient(x, y, getSize());
                double noise = ((temp + 1) / 2) * gradient;

                getTiles().get(pos).setTemp((float) noise);

                float color = (float) (noise);
                drawOnMap(x, y, color);
            }
        }*/

        return getTiles();
    }

    public Map<Vector2, GeneratorTile> getNeighbours(float x, float y) {
        Map<Vector2, GeneratorTile> neigh = new HashMap<>();

        Vector2 temp = new Vector2(x - 1, y - 1);
        neigh.put(temp, getTiles().get(temp));
        temp = new Vector2(x, y - 1);
        neigh.put(temp, getTiles().get(temp));
        temp = new Vector2(x + 1, y - 1);
        neigh.put(temp, getTiles().get(temp));

        temp = new Vector2(x - 1, y);
        neigh.put(temp, getTiles().get(temp));
        temp = new Vector2(x + 1, y);
        neigh.put(temp, getTiles().get(temp));

        temp = new Vector2(x - 1, y + 1);
        neigh.put(temp, getTiles().get(temp));
        temp = new Vector2(x, y + 1);
        neigh.put(temp, getTiles().get(temp));
        temp = new Vector2(x + 1, y + 1);
        neigh.put(temp, getTiles().get(temp));

        return neigh;
    }

    @Override
    public void saveImage(String fileName) {
        PixmapIO.writePNG(new FileHandle(fileName), getMap());

        System.out.println(this.getClass().getName() + " output saved to : " + fileName);
    }
}
