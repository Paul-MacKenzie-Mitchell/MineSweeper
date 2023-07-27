import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AppTest {
    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (Constants.WIDTH, Constants.HEIGHT, Constants.MINECOUNT, Constants.GRIDSIZE, handler, frame);
    Grid mockGrid = Mockito.mock(Grid.class);
    @BeforeEach
    void setup() {
        when(mockGrid.getBound())
                .thenReturn((game.getGridSize() * game.getGridSize()));
    }
    @AfterEach
    void reset() {
        Grid.cellGrid.clear();
    }
    @Test
    void shouldReturnValidWindowWhenGameRun() {
        game.runGame(game, game.getFrame());
        Window windowActual = game.getWindow();
        Window windowExpected = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper",
                handler, game, frame);
        assertEquals(windowExpected.getHeight(), windowActual.getHeight());
        assertEquals(windowExpected.getWidth(), windowActual.getHeight());
        assertEquals(windowExpected.getTitle(), windowActual.getTitle());
        assertEquals(windowExpected.getFrame(), windowActual.getFrame());
    }

}