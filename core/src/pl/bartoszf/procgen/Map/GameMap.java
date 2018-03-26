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
import pl.bartoszf.procgen.Tiles.Grass;
import pl.bartoszf.procgen.Tiles.Rock;
import pl.bartoszf.procgen.Tiles.Sand;
import pl.bartoszf.procgen.Utils.FastNoise;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    public final float delay = 0.00001f;
    public final int tileSize = 32;
    HashMap<Vector2, Tile> tiles;
    int size;
    double[][] noiseArray;
    int lastX = 0, lastY = 0;
    float timeBuffer = 0;
    Texture noiseTex;
    FastNoise fastNoise;

    public GameMap() {
        this.size = 1024;
        prepareMap();
    }

    public GameMap(int size) {
        this.size = size;
        prepareMap();
    }

    public void prepareMap() {
        fastNoise = new FastNoise();
        fastNoise.SetSeed((int) (Math.random() * Integer.MAX_VALUE));
        fastNoise.SetNoiseType(FastNoise.NoiseType.PerlinFractal);
        fastNoise.SetFractalOctaves(16);
        fastNoise.SetFractalLacunarity(0.6f);
        fastNoise.SetFractalGain(0.0033f);
        fastNoise.SetFrequency(0.01f);

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
            notDoneDraw(sb, cam);
        } else {
            for (Map.Entry<Vector2, Tile> entry : tiles.entrySet()) {
                Tile tile = entry.getValue();
                if (tile == null) continue;
                if (cam.frustum.sphereInFrustum(new Vector3(tile.getPosition().x, tile.getPosition().y, 0), tileSize)) {
                    sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
                }
            }
        }

    }

    private void notDoneDraw(SpriteBatch sb, OrthographicCamera cam) {
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
        } else if (!GameState.INSTANCE.mapTilesDone) {
            for (Map.Entry<Vector2, Tile> entry : tiles.entrySet()) {
                Tile tile = entry.getValue();
                if (tile == null) continue;
                if (cam.frustum.sphereInFrustum(new Vector3(tile.getPosition().x, tile.getPosition().y, 0), tileSize)) {
                    sb.draw(tile.getTexture(), tile.position.x, tile.position.y);
                }
            }
        }
    }

    private void nextMapTile() {
        if (GameState.INSTANCE.mapTilesDone) {
            lastX = 0;
            lastY = 0;
            return;
        }
        if (lastX >= size - 1 && lastY >= size - 1) {
            GameState.INSTANCE.mapTilesDone = true;
        }

        double val = noiseArray[lastY][lastX];
        Vector2 position = new Vector2(lastX * tileSize, lastY * tileSize);

        if (val < 0.15) {

        } else if (val <= 0.16) {
            tiles.put(position, new Sand(position, tileSize));
        } else if (val <= 0.5) {
            tiles.put(position, new Grass(position, tileSize));
        } else if (val <= 1) {
            tiles.put(position, new Rock(position, tileSize));
        }

        lastX++;
        if (lastX >= size) {
            lastX = 0;
            lastY++;
        }
    }

    private void nextNoiseCell() {

        if (GameState.INSTANCE.mapNoiseDone) {
            lastX = 0;
            lastY = 0;
            return;
        }
        if (lastX >= size - 1 && lastY >= size - 1) {
            GameState.INSTANCE.mapNoiseDone = true;
        }

        double temp = fastNoise.GetNoise(lastX, lastY);
        float gradient = getGradient(lastX, lastY);
        double noise = ((temp + 1) / 2) * gradient;
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
