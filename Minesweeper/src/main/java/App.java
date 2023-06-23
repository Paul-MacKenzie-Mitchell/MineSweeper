import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static void gameLoop (MainMenu mainMenu, Game game, JFrame frame) {
        while (!mainMenu.isExit()) {
            System.out.println(mainMenu.isExit());
            System.out.println(mainMenu.isPlay());
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

    public static void main(String[] args) throws InterruptedException {
        Handler handler = new Handler();
        JFrame frame = new JFrame();
        Game game = new Game(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE,
                Constants.MINECOUNT, handler, frame);

        MainMenu mainMenu = new MainMenu(frame);

        do {
            TimeUnit.SECONDS.sleep(1);
            if ((game.isLost() || game.isWon()) && mainMenu.isMenu()) {
                game.getWindow().getFrame().getContentPane().removeAll();
                game.getWindow().getGrid().getCellGrid().clear();
                mainMenu.setFrame();
                game.setLost(false);
                game.setWon(false);
            }
            if (!game.isLost() && !game.isWon()) {
                mainMenu.setMenu(true);
                if (mainMenu.isPlay()) {
                    gameLoop(mainMenu, game, frame);
                    mainMenu.setPlay(false);
                }
            }
        } while (!mainMenu.isExit());

        if (mainMenu.isExit()) {
            System.exit(0);
        }
    }
}
