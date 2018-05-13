package pl.bartoszf.procgen.Generators.IslandGenerators;

import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Generators.NoiseGenerators.HeightGenerator;
import pl.bartoszf.procgen.Generators.NoiseGenerators.MoistureGenerator;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IslandGenerator {

    private Map<Vector2, GeneratorTile> tiles;

    public Map<Vector2, GeneratorTile> generateIsland(int size) {
        Random random = GeneratorUtils.random;

        tiles = new HashMap<>();

        HeightGenerator heightGenerator = new HeightGenerator(size);
        MoistureGenerator moistureGenerator = new MoistureGenerator(size);

        float centerSize = size * 0.5f;
        float modifier = size / 10;
        Vector2 center = new Vector2(centerSize + random.nextFloat() * modifier, centerSize + random.nextFloat() * modifier);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Vector2 pos = new Vector2(x, y);

                double heightBase = heightGenerator.getNoiseAt(x, y);
                float gradient = GeneratorUtils.getRadialGradient(x, y, (int) center.x, (int) center.y, size);
                double height = ((heightBase + 1) / 2) * gradient;

                tiles.put(pos, new GeneratorTile());

                tiles.get(pos).setHeight((float) height);

                double moistBase = moistureGenerator.getNoiseAt(x, y);
                double moist = ((moistBase + 1) / 2);

                tiles.get(pos).setMoisture((float) moist);

                double temp = 1.0 - GeneratorUtils.getVerticalGradient(y, size, 0.01f);//((tempBase + 1) / 2);

                tiles.get(pos).setTemp((float) temp);
            }
        }

        return tiles;
    }
}
