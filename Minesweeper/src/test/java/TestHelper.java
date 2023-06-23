import javax.swing.*;
import java.util.ArrayList;

public class TestHelper {
        private static JFrame frame = new JFrame();
        private static Handler handler = new Handler();

        private static Game game = new Game( Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
            Constants.MINECOUNT, handler, frame);

        public static Game makeGame() {
            Game game = new Game( Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                    Constants.MINECOUNT, handler, frame);
            return game;
        }

        public static Window makeWindow() {
            Window window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);
            return window;
        }
    }