package pl.bartoszf.procgen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.bartoszf.procgen.Combiners.CityCombiner;
import pl.bartoszf.procgen.Combiners.LandCombiner;
import pl.bartoszf.procgen.Controllers.MapController;
import pl.bartoszf.procgen.Generators.CityGenerators.CityGenerator;
import pl.bartoszf.procgen.Generators.IslandGenerators.*;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Utils.FastNoise;
import pl.bartoszf.procgen.Utils.FrameRate;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Game extends ApplicationAdapter {
	public static int GAME_SIZE = 2048;
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

		BaseLandGenerator generator = new HeightGenerator(GAME_SIZE);
		generator.generate();
		//generator.saveImage("generators/heightMap.png");

		BaseLandGenerator tempGen = new TempGenerator(GAME_SIZE, generator.getTiles());
		tempGen.generate();
		//tempGen.saveImage("generators/tempMap.png");

		BaseLandGenerator moistGen = new MoistureGenerator(GAME_SIZE, tempGen.getTiles());
		moistGen.generate();
		//moistGen.saveImage("generators/moistGen.png");

		BaseLandGenerator riverGen = new RiverGenerator(GAME_SIZE, moistGen.getTiles());
		//riverGen.generate();
		//riverGen.saveImage("generators/riverGen.png");

		LandCombiner combiner = new LandCombiner(riverGen.getTiles(), GAME_SIZE);
		gameMap.setTiles(combiner.combineLand());

		CityGenerator tempCityGen = new CityGenerator(512, 512, 100, 100);
		CityCombiner tempCityCombiner = new CityCombiner(gameMap);

		tempCityCombiner.combine(tempCityGen.generate(3));
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
