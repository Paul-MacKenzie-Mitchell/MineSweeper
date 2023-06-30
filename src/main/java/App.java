import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        gameLoop();
    }
    public static void playGame(MainMenu mainMenu, Game game, JFrame frame) throws InterruptedException {
        while (!mainMenu.isExit()) {
            if (mainMenu.isPlay()) {
                game.setLost(false);
                game.setWon(false);
                break;
            }
            if (mainMenu.isExit()) {
                break;
            }
        }
        game.runGame(game, frame);
    }

    public static void gameLoop() throws InterruptedException {
        //Initializes objects for game
        Handler handler = new Handler();
        JFrame frame = new JFrame();
        Game game = new Game(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                Constants.MINECOUNT, handler, frame);
        MainMenu mainMenu = new MainMenu(frame);
        //Main Menu and Game Loop
        do {
            TimeUnit.SECONDS.sleep(1);
            //if a game has just been won or lost clears frame and grid and sets the main menu frame
            if ((game.isLost() || game.isWon()) && mainMenu.isMenu()) {
                TimeUnit.SECONDS.sleep(3);
                game.getWindow().getFrame().getContentPane().removeAll();
                Window.getGrid().getCellGrid().clear();
                mainMenu.setFrame();
                game.setLost(false);
                game.setWon(false);
            }
            //if the game is at the main menu and the play button is clicked
            if (!game.isLost() && !game.isWon()) {
                mainMenu.setMenu(true);
                if (mainMenu.isPlay()) {
                    playGame(mainMenu, game, frame);
                    mainMenu.setPlay(false);
                }
            }
        } while (!mainMenu.isExit());
        // if the exit button is clicked end program
        if (mainMenu.isExit()) {
            System.exit(0);
        }
    }
}