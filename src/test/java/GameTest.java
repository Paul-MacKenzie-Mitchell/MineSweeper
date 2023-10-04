import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GameTest {
    JFrame frame = new JFrame();
    Handler handler = new Handler();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);
    Grid grid = new Grid(new GridLayout(GameInfo.getGridsize(), GameInfo.getGridsize()), handler, game);
    @BeforeEach
    void setup() {
        game.runGame(game);
    }
    @AfterEach
    void reset() {
        Grid.cellGrid.clear();
    }
    @Test
    void newGameShouldNotBeNull() {
        assertNotNull(game);
    }
    @Test
    void newGameWindowShouldNotBeNull() {
        MenuWindow window = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler,
                game);
        assertNotNull(game.getWindow());
    }
    @Test
    void newGameHandlerShouldNotBeNull() {
        Handler handler = new Handler();
        assertNotNull(game.getHandler());
    }
    @Test
    void getHandlerShouldReturnHandler() {
        assertEquals(Handler.class, game.getHandler().getClass());
    }
    @Test
    void setHandlerShouldReturnCorrectHandler() {
        Handler expected = new Handler();
        expected.setFlaggedCells(9);
        game.setHandler(expected);
        assertEquals(9, game.getHandler().getFlaggedCells());
        assertEquals(expected, game.getHandler());
    }
    @Test
    void shouldReturnNotWon() {
        boolean acutalWon = game.isWon();
        boolean expected = false;
        assertEquals(expected, acutalWon);
    }
    @Test
    void shouldReturnNotLost() {
        boolean acutalLost = game.isLost();
        boolean expected = false;
        assertEquals(expected, acutalLost);
    }
    @Test
    void shouldSetGameToWon() {
        game.setWon(true);
        boolean acutalWon = game.isWon();
        boolean expected = true;
        assertEquals(expected, acutalWon);
    }
    @Test
    void shouldSetGameToLost() {
        game.setLost(true);
        boolean acutalLost = game.isLost();
        boolean expected = true;
        assertEquals(expected, acutalLost);
    }
}