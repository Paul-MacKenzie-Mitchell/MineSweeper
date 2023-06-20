public class App {


    public static void main(String[] args) {
        Handler handler = new Handler();
        Game game = new Game(false, Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                Constants.MINECOUNT, handler);
        game.runGame(game);
    }
}
