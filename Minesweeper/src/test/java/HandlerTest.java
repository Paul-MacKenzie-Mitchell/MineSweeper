import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HandlerTest {
    Handler handler = new Handler();
    Game game = Mockito.mock(Game.class);
    Grid mockGrid = Mockito.mock(Grid.class);

    @BeforeEach
    void setup() {
        when(game.getWindow())
                .thenReturn(TestHelper.makeWindow());
        when(mockGrid.getBound())
                .thenReturn(Game.GRIDSIZE*Game.GRIDSIZE);
    }
    @AfterEach
    void reset() {
        mockGrid.cellGrid.clear();
    }
    @Test
    void clickedUnFlaggedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        for (int x = 0; x < mockGrid.getCellGrid().size(); x++) {
            Cell tempCell = mockGrid.cellGrid.get(x);
            System.out.println(tempCell.getCellType() + " " + tempCell.getPosition());
        }
        assertTrue(mineTopLeft.isDiscovered() == true);
    }
    @Test
    void clickedUnFlaggedCellShouldNotRemainUndiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertFalse(mineTopLeft.isDiscovered() == false);

    }
    @Test
    void clickedUnFlaggedCellShouldBecomeUnEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
        assertTrue(mineTopLeft.isEnabled() == false);

    }
    @Test
    void clickedUnFlaggedCellShouldNotBecomeEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false, false, handler);
        handler.click(mineTopLeft);
        assertFalse(mineTopLeft.isEnabled() == true);


    }
    @Test
    void unClickedUnFlaggedCellShouldNotBecomeDiscovered() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + Game.GRIDSIZE / 2, false, false, handler);
        assertTrue(unClickedCellMiddle.isDiscovered() == false);

    }
    @Test
    void unClickedUnFlaggedCellShouldBecomeEnabled() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + Game.GRIDSIZE / 2, false, false, handler);
        assertTrue(unClickedCellMiddle.isEnabled() == true);

    }
    @Test
    void flaggedCellShouldRemainEnabled() {
        Cell clickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler);
        handler.click(clickedFlaggedCell);
        assertTrue(clickedFlaggedCell.isEnabled() == true);

    }
    @Test
    void flaggedCellShouldNotBecomeDiscovered() {
        Cell clickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler);
        handler.click(clickedFlaggedCell);
        assertTrue(clickedFlaggedCell.isDiscovered() == false);
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
        ArrayList<Cell> queue = new ArrayList<>();
        queue.add(blankRightColumn);
        ArrayList<Cell> current = new ArrayList<>();
        when(handlerMock.getQueue())
                .thenReturn(queue);
        Handler.addToCurrentList(queue, current);
        assertTrue(queue.size() == 0);
    }
    @Test
    void shouldRemoveCellsInCurrentAfterRevealCells() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current);
        assertTrue(current.size() == 0);
    }
    @Test
    void shouldMakeAdjacentBlankCellDiscovered() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current);
        assertTrue(blankRightColumn.isDiscovered() == true);
    }
    @Test
    void adjacentBlankCellShouldNotBeEnabled() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn = new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current);
        assertTrue(blankRightColumn.isEnabled() == false);
    }
    @Test
    void numberCellInTopLeftCornerSurroundedShouldReturn3() {
        Cell numberTopLeftCorner = new Cell(CellType.NUMBER, 0, false, false, handler);
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        int dangerCount = handler.setDangerCountTopRow(numberTopLeftCorner.getPosition());
        System.out.println(dangerCount);
    }
    @Test
    void shouldNotWinIfConditionsNotMet() {
        int cells = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
       assertTrue(cells == 0);
    }
    @Test
    void shouldWinIfConditionsNotMet() {
        int discoveredCells = 90;
        Handler.winConditionsMet(discoveredCells);
        int cellCounter = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
        assertTrue(cellCounter == 100);
    }
}