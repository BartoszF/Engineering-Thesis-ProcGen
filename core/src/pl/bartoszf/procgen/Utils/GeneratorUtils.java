package pl.bartoszf.procgen.Utils;

public class GeneratorUtils {
    public static float getGradient(int x, int y, int size) {
        float distance = getDistance(x, y, size);

        float max_width = size * 0.5f - 10.0f;
        float delta = distance / max_width;
        float gradient = delta * delta;

        return Math.max(0.0f, 1.0f - gradient);
    }

    public static float getDistance(int x, int y, int size) {
        float distance_x = Math.abs(x - size * 0.5f);
        float distance_y = Math.abs(y - size * 0.5f);
        return (float) (Math.sqrt(distance_x * distance_x + distance_y * distance_y));
    }
}
