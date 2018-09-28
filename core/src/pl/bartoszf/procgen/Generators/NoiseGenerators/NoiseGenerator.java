package pl.bartoszf.procgen.Generators.NoiseGenerators;

import pl.bartoszf.procgen.Utils.FastNoise;

public class NoiseGenerator {
    FastNoise fastNoise;
    int size;

    public NoiseGenerator(int size) {
        this.size = size;
    }

    public FastNoise getFastNoise() {
        return fastNoise;
    }

    public void setFastNoise(FastNoise fastNoise) {
        this.fastNoise = fastNoise;
    }

    public double getNoiseAt(int x, int y) {
        return fastNoise.GetNoise(x, y);
    }
}
