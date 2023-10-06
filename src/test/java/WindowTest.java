import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {

    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);
    Grid grid = new Grid(new GridLayout(GameInfo.getGridsize(), GameInfo.getGridsize()), handler, game);


    @BeforeEach
    public void setup() {
        game.runGame(game);
        MenuWindow window = game.getWindow();
    }
    @AfterEach
    void reset() {
        Grid.cellGrid.clear();
    }
    @Test
    void windowNotNull() {
        assertNotNull(game.getWindow());
    }
    @Test
    void createsValidWindowHeight() {
        assertEquals(800, game.getWindow().getHeight());
    }
    @Test
    void doesNotCreateInvalidWindowHeight() {
        assertTrue(game.getWindow().getHeight() < 801);
        assertTrue(game.getWindow().getHeight() > 799);
    }

    @Test
    void createsValidWindowWidth() {
        assertEquals(800, game.getWindow().getWidth());
    }
    @Test
    void doesNotCreateInvalidWindowWidth() {
        assertTrue(game.getWindow().getWidth() < 801);
        assertTrue(game.getWindow().getHeight() > 799);
    }
    @Test
    void createsWindowWithCorrectTitle() {
        MenuWindow validWindow = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler, game);

        String expected = "Minesweeper";
        String actual = validWindow.getTitle();
        assertEquals(expected, actual);
    }
    @Test
    void returnsJFrame() {
        MenuWindow validWindow = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler, game);
        JFrame expected = new JFrame();
        expected.setPreferredSize(new Dimension(GameInfo.WIDTH, GameInfo.HEIGHT));
        expected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expected.setResizable(false);
        expected.setMinimumSize(new Dimension(GameInfo.WIDTH, GameInfo.HEIGHT));
        expected.setMaximumSize(new Dimension(GameInfo.WIDTH, GameInfo.HEIGHT));
        expected.setLocationRelativeTo(null);
        JPanel panel = new Grid(new GridLayout(GameInfo.getGridsize(), GameInfo.getGridsize()), handler, game);
        expected.setContentPane(panel);
        expected.setTitle("Minesweeper | Mines: 10 - Flags: 0");
        expected.pack();
        expected.setVisible(true);
        JFrame actual = validWindow.getFrame();
        validWindow.update(0);
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDefaultCloseOperation(), actual.getDefaultCloseOperation());
    }
    @Test
    void returnsGrid() {
        game.setPlay(true);
        game.playGame();
        MenuWindow validWindow = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler, game);
        Grid expected = new Grid(new GridLayout(GameInfo.getGridsize(), GameInfo.getGridsize()), handler, game);
        Grid actual = game.getWindow().getGrid();
        assertEquals(expected.getBound(), actual.getBound());
        assertEquals(expected.getCellGrid(), actual.getCellGrid());
//        assertEquals(expected.isPicked(), actual.isPicked());
    }
}