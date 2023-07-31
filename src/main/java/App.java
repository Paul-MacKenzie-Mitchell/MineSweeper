import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        gameLoop();
    }

    private static void gameLoop() throws InterruptedException {

        Handler handler = new Handler();
        Game game = new Game(GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.getGridsize(),
                GameInfo.MINECOUNT, handler);
        game.runGame(game);
        do {
            TimeUnit.SECONDS.sleep(1);
            //if a game has just been won or lost clears frame and grid and sets the main menu frame
            if ((game.isLost() || game.isWon()) && game.isMenu()) {
                TimeUnit.SECONDS.sleep(3);
                game.setMainMenu();
            }
            //if the game is at the main menu and the play button is clicked
            if (!game.isLost() && !game.isWon()) {
                game.playGame();
            }
            if (game.isDifficulty()) {
                game.setDifficulty();
            }
        } while (!game.isExit());
        // if the exit button is clicked end program
        if (game.isExit()) {
            System.exit(0);
        }
    }
}
