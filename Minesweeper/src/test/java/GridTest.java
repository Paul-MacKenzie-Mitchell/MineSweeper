import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GridTest {
    Game gameMock;
    Grid grid;

    Handler handler;

    @BeforeEach
    void setup() {
        gameMock = Mockito.mock(Game.class);
        grid = Mockito.mock(Grid.class);
        handler = new Handler();
    }

    @Test
    void boundShouldEqualGridSizeXGridSize() {
        int bound = 100;
        when(grid.getBound())
                .thenReturn(bound);
        int GridSizeXGridSize = grid.getBound();
        assertEquals(100, GridSizeXGridSize);
    }
    @Test
    void mineCountShouldEqual10() {
        assertEquals(10,grid.getMines().size());
    }
    @Test
    void cellCountShouldEqual100() {
        grid = null;
        assertEquals(100, grid.getCellGrid().size());
    }
    @Test
    void cellCountShouldNotBeLessThan100() {
        grid = null;
        assertTrue(grid.getCellGrid().size() > 99);
    }
    @Test
    void cellCountShouldNotBeMoreThan100() {
        grid = null;
        assertTrue(grid.getCellGrid().size() < 101);
    }
}