package pl.bartoszf.procgen.Generators.CityGenerators;

import com.badlogic.gdx.math.Rectangle;
import pl.bartoszf.procgen.Tree.BSP;
import pl.bartoszf.procgen.Utils.GeneratorUtils;

import java.util.Random;

public class CityGenerator {

    private static boolean DISCARD_BY_RATIO = true;
    private static float RATIO_W = 0.45f;
    private static float RATIO_H = 0.45f;

    private LandPlot plot;
    private int width, height;

    public CityGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        plot = new LandPlot(new Rectangle(0, 0, width, height));
    }

    public BSP<LandPlot> generate(int iters) {
        return splitLand(plot, iters);
    }

    public BSP<LandPlot> splitLand(LandPlot plot, int iter) {

        BSP<LandPlot> root = new BSP<>(plot);
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

        if (random.nextInt(1) == 0) {
            //Vertical
            splits[0] = new LandPlot(
                    (int) plot.getX(), (int) plot.getY(),
                    1 + random.nextInt((int) plot.getW() - 1), (int) plot.getH()
            );
            splits[1] = new LandPlot(
                    (int) plot.getX() + (int) splits[0].getW(), (int) plot.getY(),
                    (int) plot.getW() - (int) splits[0].getW(), (int) plot.getH()
            );
            if (DISCARD_BY_RATIO) {
                float r1_w_ratio = splits[0].getW() / splits[0].getH();
                float r2_w_ratio = splits[1].getW() / splits[1].getH();
                if (r1_w_ratio < RATIO_W || r2_w_ratio < RATIO_W) {
                    return randomSplit(plot);
                }
            }
        } else {
            //Horizontal
            splits[0] = new LandPlot(
                    (int) plot.getX(), (int) plot.getY(),
                    (int) plot.getW(), 1 + random.nextInt((int) plot.getH() - 1)
            );
            splits[1] = new LandPlot(
                    (int) plot.getX(), (int) plot.getY() + (int) splits[0].getH(),
                    (int) plot.getW(), (int) plot.getH() - (int) splits[0].getH()
            );

            if (DISCARD_BY_RATIO) {
                float r1_h_ratio = splits[0].getH() / splits[0].getW();
                float r2_h_ratio = splits[1].getH() / splits[1].getW();
                if (r1_h_ratio < RATIO_H || r2_h_ratio < RATIO_H) {
                    return randomSplit(plot);
                }
            }
        }

        return splits;
    }


}
