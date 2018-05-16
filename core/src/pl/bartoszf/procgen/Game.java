package pl.bartoszf.procgen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.bartoszf.procgen.Combiners.LandCombiner;
import pl.bartoszf.procgen.Controllers.MapController;
import pl.bartoszf.procgen.GeneratorConfigs.LandCombinerConfig;
import pl.bartoszf.procgen.Generators.CityGenerators.CivilizationGenerator;
import pl.bartoszf.procgen.Generators.IslandGenerators.IslandGenerator;
import pl.bartoszf.procgen.Generators.NameGenerators.MarkovChain.MarkovChain;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Pathfinding.PathFindingManager;
import pl.bartoszf.procgen.Utils.FrameRate;
import pl.bartoszf.procgen.Utils.TextureManager;

public class Game extends ApplicationAdapter {
	public static int GAME_SIZE = 1024;
	public static PathFindingManager pathManager;
	public static BitmapFont font;
	SpriteBatch batch;
	private OrthographicCamera cam;
	private GameMap gameMap;
	private MapController controller;
	private float camSize = 1024 * 32 * 1.2f;
	private FrameRate fps;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		MarkovChain chain = new MarkovChain();
		chain.analyzeFile("dictionaries/cities.txt");

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

		LandCombinerConfig.initDefinitions();

		IslandGenerator generator = new IslandGenerator();

		LandCombiner combiner = new LandCombiner(generator.generateIsland(GAME_SIZE), GAME_SIZE);
		gameMap.setTiles(combiner.combineLand());

		pathManager = new PathFindingManager(gameMap);

		System.out.println("Generating civs");
		CivilizationGenerator civGenerator = new CivilizationGenerator(gameMap, chain);
		civGenerator.generate();

		/*gameMap.populateConnections();

		PathGenerator pathGen = new PathGenerator(gameMap);
		pathGen.generate();*/
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
