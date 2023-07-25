import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        gameLoop();
    }

    private static void gameLoop() throws InterruptedException {

        Handler handler = new Handler();
        JFrame frame = new JFrame();
        Game game = new Game(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                Constants.MINECOUNT, handler, frame);
        game.runGame(game, frame);
        do {
            TimeUnit.SECONDS.sleep(1);
            //if a game has just been won or lost clears frame and grid and sets the main menu frame
            if ((game.isLost() || game.isWon()) && game.isMenu()) {
                TimeUnit.SECONDS.sleep(3);


                game.getWindow().getFrame().getContentPane().removeAll();
                game.getWindow().getGrid().getCellGrid().clear();
                game.getWindow().getFrame().revalidate();
                game.getWindow().getFrame().repaint();

                game.getWindow().getFrame().dispose();
                game.getWindow().resetMenu();
                game.setMenu(false);
                game.setLost(false);
                game.setWon(false);
            }
            //if the game is at the main menu and the play button is clicked
            if (!game.isLost() && !game.isWon()) {
                game.setMenu(true);
                if (game.isPlay()) {
                    game.getWindow().getFrame().dispose();
                    game.getWindow().play();
                    game.setPlay(false);
                }
            }
        } while (!game.isExit());
        // if the exit button is clicked end program
        if (game.isExit()) {
            System.exit(0);
        }
    }
}
