import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WindowTest {

    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (Constants.WIDTH, Constants.HEIGHT, Constants.MINECOUNT, Constants.GRIDSIZE, handler, frame);
    Grid mockGrid = Mockito.mock(Grid.class);


    @BeforeEach
    public void setup() {
        game.runGame(game, game.getFrame());
        Window window = game.getWindow();
        when(mockGrid.getBound())
                .thenReturn((game.getGridSize() * game.getGridSize()));
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
        assertEquals(720, game.getWindow().getHeight());
    }
    @Test
    void doesNotCreateInvalidWindowHeight() {
        assertTrue(game.getWindow().getHeight() < 721);
        assertTrue(game.getWindow().getHeight() > 719);
    }

    @Test
    void createsValidWindowWidth() {
        assertEquals(720, game.getWindow().getWidth());
    }
    @Test
    void doesNotCreateInvalidWindowWidth() {
        assertTrue(game.getWindow().getWidth() < 721);
        assertTrue(game.getWindow().getHeight() > 719);
    }
    @Test
    void createsWindowWithCorrectTitle() {
        Window validWindow = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);

        String expected = "Minesweeper";
        String actual = validWindow.getTitle();
        assertEquals(expected, actual);
    }
    @Test
    void returnsJFrame() {
        Window validWindow = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);
        JFrame expected = new JFrame();
        expected.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        expected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expected.setResizable(false);
        expected.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        expected.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        expected.setLocationRelativeTo(null);
        JPanel panel = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
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
        Window validWindow = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);
        Grid expected = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
        validWindow.play();
        Grid actual = validWindow.getGrid();
        assertEquals(expected.getBound(), actual.getBound());
        assertEquals(expected.getCellGrid(), actual.getCellGrid());
//        assertEquals(expected.isPicked(), actual.isPicked());
    }
}