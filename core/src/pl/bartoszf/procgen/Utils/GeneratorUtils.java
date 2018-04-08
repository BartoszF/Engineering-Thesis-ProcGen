package pl.bartoszf.procgen.Utils;

public class GeneratorUtils {
    public static float getGradient(int x, int y, int cx, int cy, int size) {
        float distance = getDistance(x, y, cx, cy);

        float max_width = size * 0.5f - 10.0f;
        float delta = distance / max_width;
        float gradient = delta * delta;

        return Math.max(0.0f, 1.15f - gradient);
    }

    public static float getDistance(int x, int y, int cx, int cy) {
        float distance_x = Math.abs(x - cx);
        float distance_y = Math.abs(y - cy);
        return (float) (Math.sqrt(distance_x * distance_x + distance_y * distance_y));
    }
}
