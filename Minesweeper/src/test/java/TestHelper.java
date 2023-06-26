import javax.swing.*;
import java.awt.*;

public class TestHelper {
        private static final JFrame frame = new JFrame();
        private static final Handler handler = new Handler();

        private static final Game game = new Game( Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
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
        public static JFrame makeJFrame() {
            JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            frame.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            frame.setLocationRelativeTo(null);
            JPanel panel = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
            frame.setContentPane(panel);
            frame.setTitle("Minesweeper | Mines: 10 - Flags: 0");
            frame.pack();
            frame.setVisible(true);

            return frame;
        }
    }