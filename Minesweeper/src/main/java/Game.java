public class Game {

    private boolean won = false;
    private int width;
    private int height;
    private int gridSize;
    private int mineCount;
    private Handler handler;
    private Window window;


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    public void setWon(boolean won) {
        this.won = won;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getMineCount() {
        return mineCount;
    }

    public Game() {
        this.window = window;
        this.handler = handler;
        this.gridSize = gridSize;
        this.width = width;
        this.height = height;
        this.won = won;
        this.mineCount = mineCount;
}

    public Game(boolean won, int width, int height, int gridSize, int mineCount, Handler handler) {
        this.won = won;
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        this.handler = handler;
    }

    public void runGame(Game game) {
        Window window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game);
    }
}
