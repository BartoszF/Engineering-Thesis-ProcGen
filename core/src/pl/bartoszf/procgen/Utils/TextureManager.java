package pl.bartoszf.procgen.Utils;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum TextureManager {

    INSTANCE;

    HashMap<String, Texture> textures;

    private TextureManager() {
        textures = new HashMap<String, Texture>();
    }

    public Texture getTexture(String textureName) {
        return textures.get(textureName);
    }

    public Texture setTexture(String textureName, String path) {
        System.out.println("[TEXTURE_MANAGER] Adding texture " + path + " as " + textureName);
        return textures.put(textureName, new Texture(path));
    }
}
