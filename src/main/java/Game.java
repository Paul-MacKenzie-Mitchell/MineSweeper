import javax.swing.*;

public class Game {

    private boolean won = false;
    private boolean lost = false;
    private boolean menu = true;

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    private boolean play = false;
    private boolean exit = false;
    private final int width;
    private final int height;
    private final int gridSize;
    private final int mineCount;
    private Handler handler;
    private Window window;

    private final JFrame frame;

    public boolean isWon() {
        return won;
    }

    public boolean isLost() {
        return lost;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

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

    public JFrame getFrame() {
        return frame;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public Game(int width, int height, int gridSize, int mineCount, Handler handler, JFrame frame) {
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        this.handler = handler;
        this.frame = frame;
    }
    //creates JFrame and Grid Window
    public void runGame(Game game, JFrame frame) {
        this.window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);
    }
}