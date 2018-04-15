package pl.bartoszf.procgen.Generators.IslandGenerators;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Utils.FastNoise;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.Map;
import java.util.Random;

public class HeightGenerator extends BaseLandGenerator {

    FastNoise fastNoise;

    /**
     * @param size
     */
    public HeightGenerator(int size) {
        super(size);
    }

    /**
     * @param size
     * @param tiles
     */
    public HeightGenerator(int size, Map<Vector2, GeneratorTile> tiles) {
        super(size, tiles);
    }

    @Override
    public Map<Vector2, GeneratorTile> generate() {
        fastNoise = new FastNoise();
        fastNoise.SetSeed((int) (Math.random() * Integer.MAX_VALUE));
        fastNoise.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
        fastNoise.SetFractalOctaves(16);
        fastNoise.SetFractalLacunarity(0.8f);
        fastNoise.SetFractalGain(0.0153f);
        fastNoise.SetFrequency(0.01f);

        Random random = GeneratorUtils.random;
        float centerSize = getSize() * 0.5f;
        float modifier = getSize() / 10;
        Vector2 center = new Vector2(centerSize + random.nextFloat() * modifier, centerSize + random.nextFloat() * modifier);

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                Vector2 pos = new Vector2(x, y);
                double temp = fastNoise.GetNoise(x, y);
                float gradient = GeneratorUtils.getGradient(x, y, (int) center.x, (int) center.y, getSize());
                double noise = ((temp + 1) / 2) * gradient;

                getTiles().get(pos).setHeight((float) noise);

                //float color = (float) (noise);
                //drawOnMap(x, y, color);
            }
        }

        return getTiles();
    }

    @Override
    public void saveImage(String fileName) {
        PixmapIO.writePNG(new FileHandle(fileName), getMap());

        System.out.println(this.getClass().getName() + " output saved to : " + fileName);
    }
}
