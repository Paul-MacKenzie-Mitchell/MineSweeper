public class Game {
public static final int WIDTH = 720;
public static final int Height = 720;
public static final int GRIDSIZE = 10;
public static final int MINECOUNT = 10;

public Game() {
    Window window = new Window(WIDTH, Height, GRIDSIZE, "Minesweeper", Game.this);
}

    public static void main(String[] args) {
        new Game();
    }
}
