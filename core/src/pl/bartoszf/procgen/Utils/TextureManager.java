package pl.bartoszf.procgen.Utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public enum TextureManager {

    INSTANCE;

    HashMap<String, TextureAtlas> textures;
    HashMap<String, TextureRegion> regions;

    private TextureManager() {
        textures = new HashMap<String, TextureAtlas>();
        regions = new HashMap<>();
    }

    public static TextureRegion fixBleeding(TextureRegion region) {
        float fix = 0.02f;

        float x = region.getRegionX();
        float y = region.getRegionY();
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        float invTexWidth = 1f / region.getTexture().getWidth();
        float invTexHeight = 1f / region.getTexture().getHeight();
        region.setRegion((x + fix) * invTexWidth, (y + fix) * invTexHeight, (x + width - fix) * invTexWidth, (y + height - fix) * invTexHeight); // Trims

        return region;
    }

    public TextureAtlas getTexture(String textureName) {
        return textures.get(textureName);
    }

    public TextureAtlas setTexture(String textureName, String path) {
        System.out.println("[TEXTURE_MANAGER] Adding texture atlas " + path + " as " + textureName);
        return textures.put(textureName, new TextureAtlas(path));
    }

    public TextureRegion getRegion(TextureAtlas texture, String name) {
        if (!regions.containsKey(name)) {
            regions.put(name, texture.findRegion(name));
        }

        return regions.get(name);
    }
}
