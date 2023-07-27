import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {
    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (Constants.WIDTH, Constants.HEIGHT, Constants.MINECOUNT, Constants.GRIDSIZE, handler, frame);

    Window window = new Window(Constants.WIDTH, Constants.HEIGHT, Constants.GRIDSIZE, "Minesweeper", handler, game, frame);
    Grid grid = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
    @BeforeEach
    void setup() {
        handler.setFlaggedCells(0);
        Grid.cellGrid.clear();
        grid.clearMines(grid);
    }
    @AfterEach
    void reset() {
        Grid.cellGrid.clear();
    }
    @Test
    void shouldCreateCorrectNumberOfCells() {
        grid.createCells(handler, game);
        int actualCellGridSize = grid.getCellGrid().size();
        int expectedCellGridSize = Constants.GRIDSIZE * Constants.GRIDSIZE;
        assertEquals(expectedCellGridSize, actualCellGridSize);
    }
    @Test
    void shouldReturnMinesSetForGame() {
        grid.createCells(handler, game);
        int minesActualCount = (int) grid.getCellGrid().stream()
                .filter(x -> x.getCellType().equals(CellType.MINE))
                .count();
        int expectedMines = Constants.MINECOUNT;
        assertEquals(expectedMines, minesActualCount);
    }
    @Test
    void shouldReturnCorrectBound() {
        int actualBound = grid.getBound();
        int expectedBound = Constants.GRIDSIZE * Constants.GRIDSIZE;
        assertEquals(expectedBound, actualBound);
    }

}