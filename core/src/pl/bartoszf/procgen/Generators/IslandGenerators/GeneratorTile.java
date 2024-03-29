package pl.bartoszf.procgen.Generators.IslandGenerators;

public class GeneratorTile {
    private float height;
    private float temp;
    private float moisture;
    private boolean isRiver = false;

    public GeneratorTile() {
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getMoisture() {
        return moisture;
    }

    public void setMoisture(float moisture) {
        this.moisture = moisture;
    }

    public boolean isRiver() {
        return isRiver;
    }

    public void setRiver(boolean river) {
        isRiver = river;
    }
}
