package pl.bartoszf.procgen.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class MapController extends Controller implements InputProcessor {

    private float scrollSpeed = 100f;
    private float zoomAmount = 0.1f;

    public MapController(OrthographicCamera camera) {
        super(camera);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update() {
        float scrollAmount = scrollSpeed * getCamera().zoom;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            getCamera().translate(-scrollAmount, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            getCamera().translate(scrollAmount, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            getCamera().translate(0, scrollAmount, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            getCamera().translate(0, -scrollAmount, 0);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        getCamera().zoom += amount * zoomAmount;
        getCamera().zoom = MathUtils.clamp(getCamera().zoom, 0.05f, 10);
        return false;
    }
}
