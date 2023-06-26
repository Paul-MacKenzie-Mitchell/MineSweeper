import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {
    Handler handler = new Handler();
    Window window = TestHelper.makeWindow();
    Game game =  TestHelper.makeGame();
    Grid grid = new Grid(new GridLayout(Constants.GRIDSIZE, Constants.GRIDSIZE), handler, game);
    @BeforeEach
    void setup() {
        handler.setFlaggedCells(0);
        Grid.cellGrid.clear();
        grid.clearMines();
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
        int actaulBound = grid.getBound();
        int expectedBound = Constants.GRIDSIZE * Constants.GRIDSIZE;
        assertEquals(expectedBound, actaulBound);
    }

}