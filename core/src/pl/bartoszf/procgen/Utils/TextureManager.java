package pl.bartoszf.procgen.Utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public enum TextureManager {

    INSTANCE;

    HashMap<String, TextureAtlas> textures;

    private TextureManager() {
        textures = new HashMap<String, TextureAtlas>();
    }

    public TextureAtlas getTexture(String textureName) {
        return textures.get(textureName);
    }

    public TextureAtlas setTexture(String textureName, String path) {
        System.out.println("[TEXTURE_MANAGER] Adding texture atlas " + path + " as " + textureName);
        return textures.put(textureName, new TextureAtlas(path));
    }
}
