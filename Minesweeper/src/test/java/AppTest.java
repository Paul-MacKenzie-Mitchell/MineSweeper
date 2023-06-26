import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AppTest {
    Handler handler = new Handler();
    Game game = TestHelper.makeGame();
    Grid mockGrid = Mockito.mock(Grid.class);
    @BeforeEach
    void setup() {
        when(mockGrid.getBound())
                .thenReturn((game.getGridSize() * game.getGridSize()));
    }
    @AfterEach
    void reset() {
        mockGrid.cellGrid.clear();
    }
    @Test
    void shouldReturnValidWindowWhenGameRun() {
        game.runGame(game, game.getFrame());
        Window windowActual = game.getWindow();
        Window windowExpected = TestHelper.makeWindow();
        assertEquals(windowExpected.getHeight(), windowActual.getHeight());
        assertEquals(windowExpected.getWidth(), windowActual.getHeight());
        assertEquals(windowExpected.getTitle(), windowActual.getTitle());
        assertEquals(windowExpected.getFrame(), windowActual.getFrame());
    }

}