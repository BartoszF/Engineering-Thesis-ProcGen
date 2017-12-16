package pl.bartoszf.procgen.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import pl.bartoszf.procgen.GameState;
import pl.bartoszf.procgen.Utils.FastNoise;

import java.util.HashMap;

public class Map {
    public final float delay = 0.00005f;
    public final int tileSize = 32;
    HashMap<Vector2, Tile> tiles;
    int size;
    double[][] noiseArray;
    int lastX = 0, lastY = 0;
    float timeBuffer = 0;
    Texture noiseTex;
    FastNoise fastNoise;

    public Map() {
        this.size = 1024;
        prepareMap();
    }

    public Map(int size) {
        this.size = size;
        prepareMap();
    }

    public void prepareMap() {
        fastNoise = new FastNoise();
        fastNoise.SetSeed((int) (Math.random() * Integer.MAX_VALUE));
        fastNoise.SetNoiseType(FastNoise.NoiseType.Simplex);
        fastNoise.SetFractalOctaves(16);
        fastNoise.SetFrequency(0.005f);

        noiseArray = new double[size][size];
        tiles = new HashMap<Vector2, Tile>();

        Pixmap pixmap = new Pixmap(tileSize, tileSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(1f, 1f, 1f, 1f));
        pixmap.fillRectangle(0, 0, tileSize, tileSize);
        noiseTex = new Texture(pixmap);
        pixmap.dispose();
    }

    public void render(SpriteBatch sb, OrthographicCamera cam) {
        if (!GameState.INSTANCE.mapNoiseDone || !GameState.INSTANCE.mapTilesDone) {
            timeBuffer += Gdx.graphics.getDeltaTime();
            if (timeBuffer >= delay) {
                int times = (int) (timeBuffer / delay);
                timeBuffer = 0;
                if (!GameState.INSTANCE.mapNoiseDone) {
                    for (int i = 0; i < times; i++)
                        nextNoiseCell();
                } else if (!GameState.INSTANCE.mapTilesDone) {
                    for (int i = 0; i < times; i++)
                        nextMapTile();
                }
            }
        }
        if (!GameState.INSTANCE.mapNoiseDone) {
            Color oldColor = sb.getColor();
            for (int y = 0; y <= lastY; y++) {
                int tempX = y == lastY ? lastX : size;
                for (int x = 0; x < tempX; x++) {
                    if (cam.frustum.sphereInFrustum(new Vector3(x * tileSize, y * tileSize, 0), tileSize)) {
                        float noiseVal = (float) (noiseArray[y][x]);
                        Color color = new Color(noiseVal, noiseVal, noiseVal, 1f);
                        sb.setColor(color.r, color.g, color.b, color.a);
                        sb.draw(noiseTex, x * tileSize, y * tileSize, tileSize, tileSize);
                    }
                }
            }
            sb.setColor(oldColor);
        }
    }

    private void nextMapTile() {

    }

    private void nextNoiseCell() {

        if (GameState.INSTANCE.mapNoiseDone)
            return;
        if (lastX >= size - 1 && lastY >= size - 1) {
            GameState.INSTANCE.mapNoiseDone = true;
        }

        double noise = ((fastNoise.GetNoise(lastX, lastY) + 1) / 2) * getGradient(lastX, lastY);
        noiseArray[lastY][lastX] = noise;

        lastX++;
        if (lastX >= size) {
            lastX = 0;
            lastY++;
        }


    }

    private float getGradient(int x, int y) {
        float distance_x = Math.abs(x - size * 0.5f);
        float distance_y = Math.abs(y - size * 0.5f);
        float distance = (float) (Math.sqrt(distance_x * distance_x + distance_y * distance_y)); // circular mask

        float max_width = size * 0.5f - 10.0f;
        float delta = distance / max_width;
        float gradient = delta * delta;

        return Math.max(0.0f, 1.0f - gradient);
    }
}
