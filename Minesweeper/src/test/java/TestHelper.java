public class TestHelper {
        public static final int WIDTH = 720;
        public static final int HEIGHT= 720;
        public static final int GRIDSIZE = 10;
        public static final int MINECOUNT = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);

        private static Handler handler = new Handler();

        public static Game makeGame() {
            Game game = new Game();
            return game;
        }

        public static Window makeWindow() {
            Game game = new Game();
            Window window = game.getWindow();
            return window;
        }


    }