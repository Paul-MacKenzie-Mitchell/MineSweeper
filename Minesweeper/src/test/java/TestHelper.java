import java.util.ArrayList;

public class TestHelper {

        private static Handler handler = new Handler();

        private static Game game = new Game(false, Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
            Constants.MINECOUNT, handler);

        public static Game makeGame() {
            Game game = new Game(false, Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                    Constants.MINECOUNT, handler);
            return game;
        }

        public static Window makeWindow() {
            Window window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game);
            return window;
        }
    }