package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.HouseFloor;
import pl.bartoszf.procgen.Map.Tiles.HouseWall;
import pl.bartoszf.procgen.Tree.BSP;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityGenerator {

    private static float RATIO_W = 0.25f;
    private static float RATIO_H = 0.25f;

    private LandPlot plot;
    private int width, height;

    public CityGenerator(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        plot = new LandPlot(new Rectangle(x, y, width, height));
    }

    public List<Tile> generate(int iters) {
        System.out.println(this.getClass().getName() + " started generating city");
        BSP<LandPlot> tree = splitLand(plot, iters);
        System.out.println(this.getClass().getName() + " ended generating city");

        System.out.println(this.getClass().getName() + " started parsing city");
        List<Tile> tiles = new ArrayList<>();

        for (LandPlot plot : tree.getLeafs()) {
            Building building = new Building(plot);
            tiles.addAll(getWalls(building));
            tiles.addAll(getInteriors(building));
        }

        //Add paths

        System.out.println(this.getClass().getName() + " ended parsing city");
        return tiles;
    }

    public List<Tile> getWalls(Building building) {
        List<Tile> tiles = new ArrayList<>();

        Rectangle rect = building.getRect();

        for (int x = (int) rect.getX(); x < (int) rect.getX() + (int) rect.getWidth(); x++) {
            Tile upper = new HouseWall(new Vector2(x, rect.getY()));
            Tile lower = new HouseWall(new Vector2(x, rect.getY() + rect.getHeight()));

            tiles.add(upper);
            tiles.add(lower);
        }

        for (int y = (int) rect.getY(); y < (int) rect.getY() + (int) rect.getHeight(); y++) {
            Tile left = new HouseWall(new Vector2(rect.getX(), y));
            Tile right = new HouseWall(new Vector2(rect.getX() + rect.getWidth(), y));

            tiles.add(left);
            tiles.add(right);
        }

        return tiles;
    }

    public List<Tile> getInteriors(Building building) {
        List<Tile> tiles = new ArrayList<>();

        Rectangle rect = building.getRect();

        for (int y = (int) rect.getY() + 1; y < (int) rect.getY() + (int) rect.getHeight(); y++) {
            for (int x = (int) rect.getX() + 1; x < (int) rect.getX() + (int) rect.getWidth(); x++) {
                tiles.add(new HouseFloor(new Vector2(x, y)));
            }
        }

        return tiles;
    }



    public BSP<LandPlot> splitLand(LandPlot plot, int iter) {

        BSP<LandPlot> root = new BSP<>(plot);
        System.out.println(this.getClass().getName() + " iteration " + iter);
        if (iter != 0) {
            LandPlot[] splits = randomSplit(plot);

            root.setLeftChild(splitLand(splits[0], iter - 1));
            root.setRightChild(splitLand(splits[1], iter - 1));
        }

        return root;
    }

    public LandPlot[] randomSplit(LandPlot plot) {
        LandPlot[] splits = new LandPlot[2];
        Random random = GeneratorUtils.random;
        float next = random.nextFloat();

        if (next < 0.5f) {
            //Vertical
            float r1_w_ratio;
            float r2_w_ratio;
            do {
                splits[0] = new LandPlot(
                        (int) plot.getX(), (int) plot.getY(),
                        random.nextInt((int) (plot.getW() * (2.0f / 3.0f))), (int) plot.getH()
                );
                splits[1] = new LandPlot(
                        (int) plot.getX() + (int) splits[0].getW(), (int) plot.getY(),
                        (int) plot.getW() - (int) splits[0].getW(), (int) plot.getH()
                );
                r1_w_ratio = splits[0].getW() / splits[0].getH();
                r2_w_ratio = splits[1].getW() / splits[1].getH();
            } while (r1_w_ratio < RATIO_W || r2_w_ratio < RATIO_W);

        } else {
            //Horizontal
            float r1_h_ratio;
            float r2_h_ratio;
            do {
                splits[0] = new LandPlot(
                        (int) plot.getX(), (int) plot.getY(),
                        (int) plot.getW(), random.nextInt((int) (plot.getH() * (2.0f / 3.0f)))
                );
                splits[1] = new LandPlot(
                        (int) plot.getX(), (int) plot.getY() + (int) splits[0].getH(),
                        (int) plot.getW(), (int) plot.getH() - (int) splits[0].getH()
                );

                r1_h_ratio = splits[0].getH() / splits[0].getW();
                r2_h_ratio = splits[1].getH() / splits[1].getW();

            } while (r1_h_ratio < RATIO_H || r2_h_ratio < RATIO_H);
        }

        return splits;
    }


}
