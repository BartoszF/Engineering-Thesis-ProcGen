package pl.bartoszf.procgen;

public enum GameState {
    INSTANCE;

    public boolean mapNoiseDone = false;
    public boolean mapTilesDone = false;
    public boolean riversDone = false;
    public boolean citiesDone = false;

    private GameState() {

    }
}
