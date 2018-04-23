package pl.bartoszf.procgen.Generators.NoiseGenerators;

import pl.bartoszf.procgen.Utils.FastNoise;

public class HeightGenerator extends NoiseGenerator {
    public HeightGenerator(int size) {
        super(size);

        fastNoise = new FastNoise();
        fastNoise.SetSeed((int) (Math.random() * Integer.MAX_VALUE));
        fastNoise.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
        fastNoise.SetFractalOctaves(16);
        fastNoise.SetFractalLacunarity(0.8f);
        fastNoise.SetFractalGain(0.0153f);
        fastNoise.SetFrequency(0.01f);
    }
}
