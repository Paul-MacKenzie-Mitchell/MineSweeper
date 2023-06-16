public class Game {

    private boolean won = false;
    public static final int WIDTH = 720;
    public static final int Height = 720;
    public static final int GRIDSIZE = 10;
    public static final int MINECOUNT = (int)Math.round(GRIDSIZE * GRIDSIZE * .1);

    private Handler handler =  new Handler();

    private Window window = new Window(WIDTH, Height, GRIDSIZE, "Minesweeper", handler);

    //getters and setters

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

    public Game() {
//    Window window = new Window(WIDTH, Height, GRIDSIZE, "Minesweeper", Game.this, handler);

}
    public Game(Window window, Handler handler) {
        this.window = window;
        this.handler = handler;
    }
    public static void main(String[] args) {
        new Game();
    }
}
