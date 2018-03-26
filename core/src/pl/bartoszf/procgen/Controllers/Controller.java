package pl.bartoszf.procgen.Controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Controller {

    private OrthographicCamera camera;

    public Controller(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void update() {

    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
