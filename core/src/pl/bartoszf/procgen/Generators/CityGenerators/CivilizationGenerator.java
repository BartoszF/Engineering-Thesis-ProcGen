package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import pl.bartoszf.procgen.Combiners.CityCombiner;
import pl.bartoszf.procgen.Game;
import pl.bartoszf.procgen.Generators.NameGenerators.MarkovChain.MarkovChain;
import pl.bartoszf.procgen.Map.City;
import pl.bartoszf.procgen.Map.GameMap;
import pl.bartoszf.procgen.Map.MultipleTile;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.HouseFloor;
import pl.bartoszf.procgen.Map.Tiles.HouseWall;
import pl.bartoszf.procgen.Map.Tiles.Mountain;
import pl.bartoszf.procgen.Map.Tiles.Water;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.Random;

public class CivilizationGenerator {

    int sitesNum = 5;
    GameMap gameMap;
    MarkovChain chain;

    public CivilizationGenerator(GameMap gameMap, MarkovChain markov) {
        this.gameMap = gameMap;
        this.chain = markov;
    }

    public void generate() {
        Random random = GeneratorUtils.random;
        for (int i = 0; i < sitesNum; i++) {
            System.out.println("Generating city no. " + i);
            Rectangle rect;
            int size;
            do {
                size = 100 + random.nextInt(400);
                rect = new Rectangle(random.nextInt(Game.GAME_SIZE - size), random.nextInt(Game.GAME_SIZE - size), size, size);
            } while (!properSite(rect));

            CityGenerator city = new CityGenerator((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
            CityCombiner tempCityCombiner = new CityCombiner(gameMap);

            CityResult result = city.generate(3 + (int) Math.ceil(size / 120.0f));

            tempCityCombiner.combine(result.tiles);
            gameMap.addCity(new City(result.center, chain.getRandom()));
        }
    }

    public boolean properSite(Rectangle rect) {

        if (rect.getX() + rect.getWidth() > Game.GAME_SIZE) return false;
        if (rect.getY() + rect.getHeight() > Game.GAME_SIZE) return false;

        for (int y = (int) rect.getY(); y < (int) (rect.getY() + rect.getHeight()); y++) {
            for (int x = (int) rect.getX(); x < (int) (rect.getX() + rect.getWidth()); x++) {
                Tile tile = gameMap.getTileAt(x, y);
                if (tile == null) return false;
                if (tile instanceof Mountain) return false;
                if (tile instanceof HouseFloor) return false;
                if (tile instanceof HouseWall) return false;
                if (tile instanceof Water) return false;
                if (tile instanceof MultipleTile) {
                    MultipleTile t = (MultipleTile) tile;
                    Tile a = t.getTileA();
                    if (a instanceof Mountain) return false;
                    if (a instanceof HouseFloor) return false;
                    if (a instanceof HouseWall) return false;
                    if (a instanceof Water) return false;

                    Tile b = t.getTileB();
                    if (b instanceof Mountain) return false;
                    if (b instanceof HouseFloor) return false;
                    if (b instanceof HouseWall) return false;
                    if (b instanceof Water) return false;
                }
            }
        }

        return true;
    }
}
