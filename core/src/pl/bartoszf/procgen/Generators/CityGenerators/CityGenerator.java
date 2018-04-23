package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.bartoszf.procgen.Map.Tile;
import pl.bartoszf.procgen.Map.Tiles.HouseFloor;
import pl.bartoszf.procgen.Map.Tiles.HouseWall;
import pl.bartoszf.procgen.Map.Tiles.Mountain;
import pl.bartoszf.procgen.Tree.BSP;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityGenerator {

    private static float RATIO_W = 0.40f;
    private static float RATIO_H = 0.40f;
    private static int MIN_SIZE = 6;
    private static int MAX_TRIES = 100;

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

        //Add paths
        tiles.addAll(getPaths(tree));

        for (LandPlot plot : tree.getLeafs()) {
            Building building = new Building(plot);
            tiles.addAll(getWalls(building));
            tiles.addAll(getInteriors(building));
        }

        System.out.println(this.getClass().getName() + " ended parsing city");
        return tiles;
    }

    public List<Tile> getPaths(BSP<LandPlot> tree) {
        List<Tile> tiles = new ArrayList<>();
        if (tree.getLeftChild() == null || tree.getRightChild() == null) return null;

        tiles.addAll(pathBetween(tree.getLeftChild().getLeaf(), tree.getRightChild().getLeaf()));

        List<Tile> leftChilds = getPaths(tree.getLeftChild());
        if (leftChilds != null)
            tiles.addAll(leftChilds);

        List<Tile> rightChilds = getPaths(tree.getRightChild());
        if (rightChilds != null)
            tiles.addAll(rightChilds);

        return tiles;
    }

    public List<Tile> pathBetween(LandPlot a, LandPlot b) {
        Vector2 aCenter = a.getRect().getCenter(new Vector2());
        Vector2 bCenter = b.getRect().getCenter(new Vector2());

        List<Tile> tiles = new ArrayList<>();

        for (int y = (int) aCenter.y; y <= (int) (bCenter.y); y++) {
            for (int x = (int) aCenter.x; x <= (int) (bCenter.x); x++) {
                tiles.add(new Mountain(new Vector2(x, y)));
            }
        }

        return tiles;
    }

    public List<Tile> getWalls(Building building) {
        List<Tile> tiles = new ArrayList<>();

        Rectangle rect = building.getRect();

        for (int x = (int) rect.getX(); x <= (int) rect.getX() + (int) rect.getWidth(); x++) {
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

        if (plot == null) return null;
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
        int tries = 0;

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
                if (++tries > MAX_TRIES) {
                    if (r1_w_ratio < RATIO_W) {
                        splits[0] = null;
                    }
                    if (r2_w_ratio < RATIO_W) {
                        splits[1] = null;
                    }
                    break;
                }
            }
            while (r1_w_ratio < RATIO_W || r2_w_ratio < RATIO_W || splits[0].getW() < MIN_SIZE || splits[1].getW() < MIN_SIZE);

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
                if (++tries > MAX_TRIES) {
                    if (r1_h_ratio < RATIO_H) {
                        splits[0] = null;
                    }
                    if (r2_h_ratio < RATIO_H) {
                        splits[1] = null;
                    }
                    break;
                }
            }
            while (r1_h_ratio < RATIO_H || r2_h_ratio < RATIO_H || splits[0].getH() < MIN_SIZE || splits[1].getH() < MIN_SIZE);
        }

        return splits;
    }


}
