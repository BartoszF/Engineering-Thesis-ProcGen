package pl.bartoszf.procgen.Utils;

import java.util.Random;

public class GeneratorUtils {
    public static Random random = new Random();

    public static float getRadialGradient(int x, int y, int cx, int cy, int size) {
        float distance = getDistance(x, y, cx, cy);

        float max_width = size * 0.5f - 10.0f;
        float delta = distance / max_width;
        float gradient = delta * delta;

        return Math.max(0.0f, 1.15f - gradient);
    }

    public static float getVerticalGradient(int y, int maxY) {
        return (float) y / (float) maxY;
    }

    public static float getVerticalGradient(int y, int maxY, float random) {
        float rand = GeneratorUtils.random.nextFloat() * random;
        return ((float) y / (float) maxY) + (rand * 2) - rand;
    }

    public static float getDistance(int x, int y, int cx, int cy) {
        float distance_x = Math.abs(x - cx);
        float distance_y = Math.abs(y - cy);
        return (float) (Math.sqrt(distance_x * distance_x + distance_y * distance_y));
    }
}
