import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HandlerTest {
    Handler handler = new Handler();

    Game game = new Game();
    Grid mockGrid;
    @BeforeEach
    void setup() {
        mockGrid = Mockito.mock(Grid.class);
        when(mockGrid.getBound())
                .thenReturn(Game.GRIDSIZE*Game.GRIDSIZE);
    }
    @Test
    void clickedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertTrue(mineTopLeft.isDiscovered() == true);
    }
    @Test
    void clickedCellShouldNotRemainUndiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertFalse(mineTopLeft.isDiscovered() == false);
    }
    @Test
    void clickedCellShouldBecomeUnEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertTrue(mineTopLeft.isEnabled() == false);
    }
    @Test
    void clickedCellShouldNotBeEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false, false, handler);
        handler.click(mineTopLeft);
        assertFalse(mineTopLeft.isEnabled() == true);
    }
    @Test
    void unclickedCellShouldNotBeDiscovered() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + Game.GRIDSIZE / 2, false, false, handler);
        assertTrue(unClickedCellMiddle.isDiscovered() == false);
    }
    @Test
    void unlclickedCellshouldBeEnabled() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + Game.GRIDSIZE / 2, false, false, handler);
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
        Cell blankTopRight = new Cell(CellType.BLANK, Game.GRIDSIZE - 1, false,false, handler);
        handler.handleBlankCell(blankTopRight.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellTopRowMiddleShouldAdd5ToQueue() {
        Cell blankTopMiddle = new Cell(CellType.BLANK, Game.GRIDSIZE / 2, false,false, handler);
        handler.handleBlankCell(blankTopMiddle.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRightShouldAdd3ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, mockGrid.getBound() - Game.GRIDSIZE, false, false, handler);
        handler.handleBlankCell(blankBottomRight.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomLeftShouldAdd3ToQueue() {
        Cell blankBottomLeft = new Cell(CellType.BLANK, mockGrid.getBound() - 1, false, false, handler);
        handler.handleBlankCell(blankBottomLeft.getPosition());
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRowMiddleShouldAdd5ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, mockGrid.getBound() - Game.GRIDSIZE / 2, false,false, handler);
        handler.handleBlankCell(blankBottomRight.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnLeftColumnShouldAdd5ToQueue() {
        Cell blankLeftColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 2, false, false, handler);
        handler.handleBlankCell(blankLeftColumn.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnRightColumnShouldAdd5ToQueue() {
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        handler.handleBlankCell(blankRightColumn.getPosition());
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellInMiddleShouldQueEight() {
        Cell blankMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + Game.GRIDSIZE / 2, false, false, handler);
        handler.handleBlankCell(blankMiddle.getPosition());
        assertEquals(8, handler.getQueue().size());
    }
    @Test
    void ifQueueIsEmptyAddNothingToCurrent() {
        Handler handlerMock = Mockito.mock(Handler.class);
        ArrayList<Cell> queueMock = new ArrayList<>();
        ArrayList<Cell> current = new ArrayList<>();
        when(handlerMock.getQueue())
                .thenReturn(queueMock);
        Handler.addToCurrentList(queueMock, current);
        assertTrue(current.size() == 0);
    }
    @Test
    void shouldAddNumberOfCellsInQueueToCurrent() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        ArrayList<Cell> queueMock = new ArrayList<>();
        queueMock.add(blankRightColumn);
        ArrayList<Cell> current = new ArrayList<>();
        when(handlerMock.getQueue())
                .thenReturn(queueMock);
        Handler.addToCurrentList(queueMock, current);
        assertTrue(current.size() == 1);
    }
    @Test
    void shouldClearQueueAfterAddingCellsToCurrent() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        ArrayList<Cell> queueMock = new ArrayList<>();
        queueMock.add(blankRightColumn);
        ArrayList<Cell> current = new ArrayList<>();
        when(handlerMock.getQueue())
                .thenReturn(queueMock);
        Handler.addToCurrentList(queueMock, current);
        assertTrue(queueMock.size() == 0);
    }
}