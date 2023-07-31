import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AppTest {
    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);
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
        game.runGame(game);
        MenuWindow windowActual = game.getWindow();

        MenuWindow windowExpected = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper",
                handler, game);
        assertEquals(windowExpected.getHeight(), windowActual.getHeight());
        assertEquals(windowExpected.getWidth(), windowActual.getHeight());
        assertEquals(windowExpected.getTitle(), windowActual.getTitle());
        assertEquals(windowExpected.getFrame(), windowActual.getFrame());
    }

}