import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {
    Handler handler = new Handler();

    Game game = new Game();

    Cell blankCellMiddle = new Cell(CellType.BLANK, 56, false, false, handler);
    Grid mockGrid;
    @BeforeEach
    void setup() {
        mockGrid = Mockito.mock(Grid.class);
    }
    @Test
    void clickedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertTrue(mineTopLeft.isDiscovered() == true);
    }
    @Test
    void clickedCellShouldNotBeEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertTrue(mineTopLeft.isEnabled() == false);
    }
    @Test
    void unclickedCellShouldNotBeDiscovered() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, 55, false, false, handler);
        assertTrue(unClickedCellMiddle.isDiscovered() == false);
    }
    @Test
    void unlclickedCellshouldBeEnabled() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, 55, false, false, handler);
        assertTrue(unClickedCellMiddle.isEnabled() == true);
    }
    @Test
    void ifBlankCellTopLeftShouldAdd3ToQueue() {
        Cell blankTopLeft = new Cell(CellType.BLANK, 0, false,false, handler);
        handler.handleBlankCell(blankTopLeft.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellTopRightShouldAdd3ToQueue() {
        Cell blankTopRight = new Cell(CellType.BLANK, 9, false,false, handler);
        handler.handleBlankCell(blankTopRight.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellTopRowMiddleShouldAdd5ToQueue() {
        Cell blankTopMiddle = new Cell(CellType.BLANK, 5, false,false, handler);
        handler.handleBlankCell(blankTopMiddle.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRightShouldAdd3ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, 90, false, false, handler);
        handler.handleBlankCell(blankBottomRight.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomLeftShouldAdd3ToQueue() {
        Cell blankBottomLeft = new Cell(CellType.BLANK, 99, false, false, handler);
        handler.handleBlankCell(blankBottomLeft.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRowMiddleShouldAdd5ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, 95, false,false, handler);
        handler.handleBlankCell(blankBottomRight.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnLeftColumnShouldAdd5ToQueue() {
        Cell blankLeftColumn =  new Cell(CellType.BLANK, 30, false, false, handler);
        handler.handleBlankCell(blankLeftColumn.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnRightColumnShouldAdd5ToQueue() {
        Cell blankRightColumn =  new Cell(CellType.BLANK, 39, false, false, handler);
        handler.handleBlankCell(blankRightColumn.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellInMiddleShouldQueEight() {
        Cell blankMiddle = new Cell(CellType.BLANK, 55, false, false, handler);
        handler.handleBlankCell(blankMiddle.getPosition());
        assertEquals(8, handler.getQueue().size());
    }
}