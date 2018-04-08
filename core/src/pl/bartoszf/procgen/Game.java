package pl.bartoszf.procgen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.bartoszf.procgen.Combiners.LandCombiner;
import pl.bartoszf.procgen.Controllers.MapController;
import pl.bartoszf.procgen.Generators.*;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.FastNoise;
import pl.bartoszf.procgen.Utils.FrameRate;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera cam;
	private GameMap gameMap;
	private MapController controller;

	private float camSize = 1024 * 32 * 1.2f;

	private FastNoise fastNoise;
	private FrameRate fps;
	private BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		TextureManager.INSTANCE.setTexture("tiles", "img/Tiles.pack");

		gameMap = new GameMap();

		font = new BitmapFont();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(camSize, camSize * (h / w));

		cam.position.set(512 * Tile.TILE_SIZE, 512 * Tile.TILE_SIZE, 0);

		cam.zoom = 0.8f;
		cam.update();

		controller = new MapController(cam);

		fps = new FrameRate();

		BaseLandGenerator generator = new HeightGenerator(1024);
		generator.generate();
		generator.saveImage("generators/heightMap.png");

		BaseLandGenerator tempGen = new TempGenerator(1024, generator.getTiles());
		tempGen.generate();
		tempGen.saveImage("generators/tempMap.png");

		BaseLandGenerator moistGen = new MoistureGenerator(1024, tempGen.getTiles());
		moistGen.generate();
		moistGen.saveImage("generators/moistGen.png");

		BaseLandGenerator riverGen = new RiverGenerator(1024, moistGen.getTiles());
		//riverGen.generate();
		//riverGen.saveImage("generators/riverGen.png");

		LandCombiner combiner = new LandCombiner(riverGen.getTiles(), 1024);
		gameMap.setTiles(combiner.combineLand());
	}

	@Override
	public void render () {

		controller.update();
		cam.update();
		fps.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameMap.render(batch, cam);
		fps.render(cam, gameMap);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
