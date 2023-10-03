import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {
    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);

    MenuWindow window = new MenuWindow(GameInfo.WIDTH, GameInfo.HEIGHT, "Minesweeper", handler, game);
    Grid grid = new Grid(new GridLayout(GameInfo.getGridsize(), GameInfo.getGridsize()), handler, game);
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
        int expectedCellGridSize = GameInfo.getGridsize() * GameInfo.getGridsize();
        assertEquals(expectedCellGridSize, actualCellGridSize);
    }
    @Test
    void shouldReturnMinesSetForGame() {
        grid.createCells(handler, game);
        int minesActualCount = 0;
        for (int x = 0; x < grid.getCellGrid().size(); x++) {
            if (grid.getCellGrid().get(x).getCellType().equals(CellType.MINE)) {
                minesActualCount++;
            }
        }
        int expectedMines = GameInfo.MINECOUNT;
        assertEquals(expectedMines, minesActualCount);
    }
    @Test
    void shouldReturnCorrectBound() {
        int actualBound = grid.getBound();
        int expectedBound = GameInfo.getGridsize() * GameInfo.getGridsize();
        assertEquals(expectedBound, actualBound);
    }

}