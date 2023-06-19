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
        handler.setFlaggedCells(0);
        mockGrid.cellGrid.clear();
    }
    @Test
    void clickedUnFlaggedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler);
        handler.click(mineTopLeft);
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
    void getCurrentWhenEmptyShouldBeEmpty() {
        ArrayList<Cell> current = handler.getCurrent();
        int numCellsInCurrent = current.size();
        assertTrue( numCellsInCurrent == 0 );
    }
    @Test
    void getCurrentShouldReturnLisContaining1CellIContains1Cell() {
        Handler handlerMock = Mockito.mock(Handler.class);
        ArrayList<Cell> current = handler.getCurrent();
        ArrayList<Cell> queue = handler.getQueue();
        Cell blankRightColumn =  new Cell(CellType.BLANK, Game.GRIDSIZE * 3 - 1, false, false, handler);
        queue.add(blankRightColumn);
        when(handlerMock.getQueue())
                .thenReturn(queue);
        Handler.addToCurrentList(queue, current);
        current = handler.getCurrent();
        int numCellsInCurrent = current.size();
        assertTrue( numCellsInCurrent == 1 );
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
    void numberCellInTopLeftCornerSurroundedShouldReturnDangerCount3() {
        Cell numberTopLeftCorner = new Cell(CellType.NUMBER, 0, false, false, handler);
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(3, handler.setDangerCountTopRow(numberTopLeftCorner.getPosition()));
    }
    @Test
    void numberCellInTopRightCornerSurroundedShouldReturnDangerCount3() {
        Cell numberTopRightCorner = new Cell(CellType.NUMBER, Game.GRIDSIZE - 1, false, false, handler);
        Grid.cellGrid.set(numberTopRightCorner.getPosition() - 1, new Cell(CellType.MINE, numberTopRightCorner.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberTopRightCorner.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberTopRightCorner.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberTopRightCorner.getPosition() + Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberTopRightCorner.getPosition() + Game.GRIDSIZE - 1, false, false, handler));
        assertEquals(3, handler.setDangerCountTopRow(numberTopRightCorner.getPosition()));
    }
    @Test
    void numberCellOnTopRowMiddleSurroundedShouldReturnDangerCount5() {
        Cell numberTopRowMiddle = new Cell(CellType.NUMBER, Game.GRIDSIZE - (Game.GRIDSIZE / 2), false, false, handler);
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() - 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(5, handler.setDangerCountTopRow(numberTopRowMiddle.getPosition()));
    }
    @Test
    void numberCellInBottomLeftCornerSurroundedShouldReturnDangerCount3() {
        Cell numberBottomLeftCorner = new Cell(CellType.NUMBER, Game.GRIDSIZE *(Game.GRIDSIZE -1), false, false, handler);
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() + 1, new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() - Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() - Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(3, handler.setDangerCounterBottomRow(numberBottomLeftCorner.getPosition()));
    }
    @Test
    void numberCellInBottomRightCornerSurroundedShouldReturnDangerCount3() {
        Cell numberBottomRightCorner = new Cell(CellType.NUMBER, Game.GRIDSIZE *Game.GRIDSIZE - 1, false, false, handler);
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - 1, new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - Game.GRIDSIZE - 1, false, false, handler));
        assertEquals(3, handler.setDangerCounterBottomRow(numberBottomRightCorner.getPosition()));
    }
    @Test
    void numberCellInBottomMiddleSurroundedShouldReturnDangerCount5() {
        Cell numberBottomMiddle = new Cell(CellType.NUMBER, Game.GRIDSIZE * Game.GRIDSIZE - Game.GRIDSIZE / 2, false, false, handler);
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() + 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(5, handler.setDangerCounterBottomRow(numberBottomMiddle.getPosition()));
    }
    @Test
    void numberCellInLeftColumnShouldReturnDangerCount5() {
        Cell numberLeftColumn = new Cell(CellType.NUMBER, Game.GRIDSIZE + Game.GRIDSIZE, false, false, handler);
        Grid.cellGrid.set(numberLeftColumn.getPosition() + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberLeftColumn.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberLeftColumn.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberLeftColumn.getPosition() - Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() - Game.GRIDSIZE + 1, false, false, handler));
        Grid.cellGrid.set(numberLeftColumn.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberLeftColumn.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberLeftColumn.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(5, handler.setDangerCountLeftColumn(numberLeftColumn.getPosition()));
    }
    @Test
    void numberCellInRightColumnShouldReturnDangerCount5() {
        Cell numberRightColumn = new Cell(CellType.NUMBER, (Game.GRIDSIZE + Game.GRIDSIZE * 2) - 1, false, false, handler);
        Grid.cellGrid.set(numberRightColumn.getPosition() - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberRightColumn.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberRightColumn.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberRightColumn.getPosition() - Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() - Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberRightColumn.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberRightColumn.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberRightColumn.getPosition() + Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() + Game.GRIDSIZE - 1, false, false, handler));
        assertEquals(5, handler.setDangerCounterRightColumn(numberRightColumn.getPosition()));
    }
    @Test
    void numberCellNotOnEdgeShouldReturnDangerCount8() {
        Cell numberCenter = new Cell(CellType.NUMBER, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        Grid.cellGrid.set(numberCenter.getPosition() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE + 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        assertEquals(8, handler.setDangerCounterNotOnEdge(numberCenter.getPosition()));
    }
    @Test
    void numberCellNotOnEdgeShouldHaveText8() {
        Cell numberCenter = new Cell(CellType.NUMBER, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        Grid.cellGrid.set(numberCenter.getPosition() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() - Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberCenter.getPosition() - Game.GRIDSIZE + 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE - 1, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE - 1, false, false, handler));
        Grid.cellGrid.set(numberCenter.getPosition() + Game.GRIDSIZE + 1, new Cell(CellType.MINE, numberCenter.getPosition() + Game.GRIDSIZE + 1, false, false, handler));
        handler.handleNumberCell(Game.GRIDSIZE + (Game.GRIDSIZE / 2), numberCenter);
        assertEquals("8", numberCenter.getText());
    }
    @Test
    void ifClickMineCellAllCellsNoCellsEnabled() {
        Cell mineCenter = new Cell(CellType.MINE, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        handler.handleMineCell(mineCenter);
        int cells = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
        assertTrue(cells == mockGrid.getBound());
    }
    @Test
    void shouldNotWinIfConditionsNotMet() {
        int discoveredCells = 0;
        Handler.winConditionsMet(discoveredCells);
        int cells = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();

       assertTrue(cells == 0);
    }
    @Test
    void shouldWinIfConditionsMet() {
        int discoveredCells = 90;
        Handler.winConditionsMet(discoveredCells);
        int cellCounter = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
        assertTrue(cellCounter == 100);
    }
    @Test
    void ifUndiscoveredCellRightClickedIsFlagged() {
        Cell mineCenter = new Cell(CellType.MINE, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        handler.rightClick(mineCenter);
        assertTrue(mineCenter.isFlagged());
    }
    @Test
    void ifNotRightClickedIsNotFlagged() {
        Cell mineCenterUnFlagged = new Cell(CellType.MINE, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        assertFalse(mineCenterUnFlagged.isFlagged());
    }
    @Test
    void ifFlaggedCellIsRightClickedShouldUnFlagCell() {
        Cell mineCenterFlagged = new Cell(CellType.MINE, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, true, handler);
        handler.rightClick(mineCenterFlagged);
        assertFalse(mineCenterFlagged.isFlagged());
    }
    @Test
    void if1CellFlaggedShouldReturn1FlaggedCell() {
        Cell mineCenter1 = new Cell(CellType.MINE, Game.GRIDSIZE + (Game.GRIDSIZE / 2), false, false, handler);
        handler.rightClick(mineCenter1);
        assertEquals(1, handler.getFlaggedCells());
    }
}