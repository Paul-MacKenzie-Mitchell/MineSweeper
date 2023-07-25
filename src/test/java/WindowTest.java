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
    Game game = TestHelper.makeGame();
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
        Window validWindow = TestHelper.makeWindow();
        String expected = "Minesweeper";
        String actual = validWindow.getTitle();
        assertEquals(expected, actual);
    }
    @Test
    void returnsJFrame() {
        Window validWindow = TestHelper.makeWindow();
        JFrame expected = TestHelper.makeJFrame();
        JFrame actual = validWindow.getFrame();
        validWindow.update(0);
        assertEquals(expected.getWidth(), actual.getWidth());
        assertEquals(expected.getHeight(), actual.getHeight());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDefaultCloseOperation(), actual.getDefaultCloseOperation());
    }
    @Test
    void returnsGrid() {
        Window validWindow = TestHelper.makeWindow();
        Grid expected = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
        validWindow.play();
        Grid actual = validWindow.getGrid();
        assertEquals(expected.getBound(), actual.getBound());
        assertEquals(expected.getCellGrid(), actual.getCellGrid());
//        assertEquals(expected.isPicked(), actual.isPicked());
    }
}