package pl.bartoszf.procgen.Generators.NoiseGenerators;

import pl.bartoszf.procgen.Utils.FastNoise;

public class TempGenerator extends NoiseGenerator {
    public TempGenerator(int size) {
        super(size);

        fastNoise = new FastNoise();
        fastNoise.SetSeed((int) (Math.random() * Integer.MAX_VALUE));
        fastNoise.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
        fastNoise.SetFractalOctaves(20);
        fastNoise.SetFractalLacunarity(0.6f);
        fastNoise.SetFractalGain(0.0023f);
        fastNoise.SetFrequency(0.005f);
    }
}
