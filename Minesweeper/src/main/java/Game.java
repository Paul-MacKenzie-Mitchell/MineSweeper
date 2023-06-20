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


    public int getGridSize() {
        return gridSize;
    }

    public int getMineCount() {
        return mineCount;
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
        this.window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;

        if (won != game.won) return false;
        if (width != game.width) return false;
        if (height != game.height) return false;
        if (getGridSize() != game.getGridSize()) return false;
        if (getMineCount() != game.getMineCount()) return false;
        if (!getHandler().equals(game.getHandler())) return false;
        return getWindow().equals(game.getWindow());
    }

    @Override
    public int hashCode() {
        int result = (won ? 1 : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + getGridSize();
        result = 31 * result + getMineCount();
        result = 31 * result + getHandler().hashCode();
        result = 31 * result + getWindow().hashCode();
        return result;
    }
}
