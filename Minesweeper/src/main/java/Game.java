public class Game {
public static final int WIDTH = 720;
public static final int Height = 720;
public static final int GRIDSIZE = 10;
public static final int MINECOUNT = (int)Math.round(GRIDSIZE * GRIDSIZE * .1);

private Handler handler =  new Handler();

public Game() {
    Window window = new Window(WIDTH, Height, GRIDSIZE, "Minesweeper", Game.this, handler);
}

    public static void main(String[] args) {
        new Game();
    }
}
