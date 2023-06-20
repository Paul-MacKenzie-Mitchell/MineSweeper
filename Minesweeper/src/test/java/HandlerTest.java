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
    Game game = TestHelper.makeGame();
    Grid mockGrid = Mockito.mock(Grid.class);

    @BeforeEach
    void setup() {
        game.runGame(game);
        when(mockGrid.getBound())
                .thenReturn((game.getGridSize() * game.getGridSize()));
    }
    @AfterEach
    void reset() {
        handler.setFlaggedCells(0);
        mockGrid.cellGrid.clear();
    }
    @Test
    void clickedUnFlaggedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
        assertTrue(mineTopLeft.isDiscovered() == true);
    }
    @Test
    void clickedUnFlaggedCellShouldNotRemainUndiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
        assertFalse(mineTopLeft.isDiscovered() == false);

    }
    @Test
    void clickedUnFlaggedCellShouldBecomeUnEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
        assertTrue(mineTopLeft.isEnabled() == false);

    }
    @Test
    void clickedUnFlaggedCellShouldNotBecomeEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false, false, handler, game);
        handler.click(mineTopLeft, game);
        assertFalse(mineTopLeft.isEnabled() == true);


    }
    @Test
    void unClickedUnFlaggedCellShouldNotBecomeDiscovered() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + game.getGridSize() / 2, false, false, handler, game);
        assertTrue(unClickedCellMiddle.isDiscovered() == false);

    }
    @Test
    void unClickedUnFlaggedCellShouldBecomeEnabled() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + game.getGridSize() / 2, false, false, handler, game);
        assertTrue(unClickedCellMiddle.isEnabled() == true);

    }
    @Test
    void flaggedCellShouldRemainEnabled() {
        Cell clickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler, game);
        handler.click(clickedFlaggedCell, game);
        assertTrue(clickedFlaggedCell.isEnabled() == true);

    }
    @Test
    void flaggedCellShouldNotBecomeDiscovered() {
        Cell clickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler, game);
        handler.click(clickedFlaggedCell, game);
        assertTrue(clickedFlaggedCell.isDiscovered() == false);
    }
    @Test
    void ifBlankCellTopLeftShouldAdd3ToQueue() {
        Cell blankTopLeft = new Cell(CellType.BLANK, 0, false,false, handler, game);
        handler.handleBlankCell(blankTopLeft.getPosition(), game);
        assertEquals(3, handler.getQueue().size());
    }
    @Test
     void ifBlankCellTopRightShouldAdd3ToQueue() {
        Cell blankTopRight = new Cell(CellType.BLANK, game.getGridSize() - 1, false,false, handler, game);
        handler.handleBlankCell(blankTopRight.getPosition(), game);
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellTopRowMiddleShouldAdd5ToQueue() {
        Cell blankTopMiddle = new Cell(CellType.BLANK, game.getGridSize() / 2, false,false, handler, game);
        handler.handleBlankCell(blankTopMiddle.getPosition(), game);
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRightShouldAdd3ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, mockGrid.getBound() - game.getGridSize(), false, false, handler, game);
        handler.handleBlankCell(blankBottomRight.getPosition(), game);
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomLeftShouldAdd3ToQueue() {
        Cell blankBottomLeft = new Cell(CellType.BLANK, mockGrid.getBound() - 1, false, false, handler, game);
        handler.handleBlankCell(blankBottomLeft.getPosition(), game);
        assertEquals(3, handler.getQueue().size());
    }
    @Test
    void ifBlankCellBottomRowMiddleShouldAdd5ToQueue() {
        Cell blankBottomRight = new Cell(CellType.BLANK, mockGrid.getBound() - game.getGridSize() / 2, false,false, handler, game);
        handler.handleBlankCell(blankBottomRight.getPosition(), game);
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnLeftColumnShouldAdd5ToQueue() {
        Cell blankLeftColumn =  new Cell(CellType.BLANK, game.getGridSize() * 2, false, false, handler, game);
        handler.handleBlankCell(blankLeftColumn.getPosition(), game);
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellOnRightColumnShouldAdd5ToQueue() {
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
        handler.handleBlankCell(blankRightColumn.getPosition(), game);
        assertEquals(5, handler.getQueue().size());
    }
    @Test
    void ifBlankCellInMiddleShouldQueEight() {
        Cell blankMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + game.getGridSize() / 2, false, false, handler, game);
        handler.handleBlankCell(blankMiddle.getPosition(), game);
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
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
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
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
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
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
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
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current, game);
        assertTrue(current.size() == 0);
    }
    @Test
    void shouldMakeAdjacentBlankCellDiscovered() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn =  new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current, game);
        assertTrue(blankRightColumn.isDiscovered() == true);
    }
    @Test
    void adjacentBlankCellShouldNotBeEnabled() {
        Handler handlerMock = Mockito.mock(Handler.class);
        Cell blankRightColumn = new Cell(CellType.BLANK, game.getGridSize() * 3 - 1, false, false, handler, game);
        ArrayList<Cell> current = new ArrayList<>();
        current.add(blankRightColumn);
        when(handlerMock.getCurrent())
                .thenReturn(current);
        Handler.revealCells(current, game);
        assertTrue(blankRightColumn.isEnabled() == false);
    }
    @Test
    void numberCellInTopLeftCornerSurroundedShouldReturnDangerCount3() {
        Cell numberTopLeftCorner = new Cell(CellType.NUMBER, 0, false, false, handler, game);
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberTopLeftCorner.getPosition() + game.getGridSize() + 1, new Cell(CellType.MINE, numberTopLeftCorner.getPosition() + game.getGridSize() + 1, false, false, handler, game));
        assertEquals(3, handler.setDangerCountTopRow(numberTopLeftCorner.getPosition(), game));
    }
    @Test
    void numberCellInTopRightCornerSurroundedShouldReturnDangerCount3() {
        Cell numberTopRightCorner = new Cell(CellType.NUMBER, game.getGridSize() - 1, false, false, handler, game);
        Grid.cellGrid.set(numberTopRightCorner.getPosition() - 1, new Cell(CellType.MINE, numberTopRightCorner.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberTopRightCorner.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberTopRightCorner.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberTopRightCorner.getPosition() + game.getGridSize() - 1, new Cell(CellType.MINE, numberTopRightCorner.getPosition() + game.getGridSize() - 1, false, false, handler, game));
        assertEquals(3, handler.setDangerCountTopRow(numberTopRightCorner.getPosition(), game));
    }
    @Test
    void numberCellOnTopRowMiddleSurroundedShouldReturnDangerCount5() {
        Cell numberTopRowMiddle = new Cell(CellType.NUMBER, game.getGridSize() - (game.getGridSize() / 2), false, false, handler, game);
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() - 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + game.getGridSize() - 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberTopRowMiddle.getPosition() + game.getGridSize() + 1, new Cell(CellType.MINE, numberTopRowMiddle.getPosition() + game.getGridSize() + 1, false, false, handler, game));
        assertEquals(5, handler.setDangerCountTopRow(numberTopRowMiddle.getPosition(), game));
    }
    @Test
    void numberCellInBottomLeftCornerSurroundedShouldReturnDangerCount3() {
        Cell numberBottomLeftCorner = new Cell(CellType.NUMBER, game.getGridSize() *(game.getGridSize() -1), false, false, handler, game);
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() + 1, new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberBottomLeftCorner.getPosition() - game.getGridSize() + 1, new Cell(CellType.MINE, numberBottomLeftCorner.getPosition() - game.getGridSize() + 1, false, false, handler, game));
        assertEquals(3, handler.setDangerCounterBottomRow(numberBottomLeftCorner.getPosition(), game));
    }
    @Test
    void numberCellInBottomRightCornerSurroundedShouldReturnDangerCount3() {
        Cell numberBottomRightCorner = new Cell(CellType.NUMBER, game.getGridSize() *game.getGridSize() - 1, false, false, handler, game);
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - 1, new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberBottomRightCorner.getPosition() - game.getGridSize() - 1, new Cell(CellType.MINE, numberBottomRightCorner.getPosition() - game.getGridSize() - 1, false, false, handler, game));
        assertEquals(3, handler.setDangerCounterBottomRow(numberBottomRightCorner.getPosition(), game));
    }
    @Test
    void numberCellInBottomMiddleSurroundedShouldReturnDangerCount5() {
        Cell numberBottomMiddle = new Cell(CellType.NUMBER, game.getGridSize() * game.getGridSize() - game.getGridSize() / 2, false, false, handler, game);
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() + 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberBottomMiddle.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - game.getGridSize() - 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberBottomMiddle.getPosition() - game.getGridSize() + 1, new Cell(CellType.MINE, numberBottomMiddle.getPosition() - game.getGridSize() + 1, false, false, handler, game));
        assertEquals(5, handler.setDangerCounterBottomRow(numberBottomMiddle.getPosition(), game));
    }
    @Test
    void numberCellInLeftColumnShouldReturnDangerCount5() {
        Cell numberLeftColumn = new Cell(CellType.NUMBER, game.getGridSize() + game.getGridSize(), false, false, handler, game);
        Grid.cellGrid.set(numberLeftColumn.getPosition() + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberLeftColumn.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberLeftColumn.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberLeftColumn.getPosition() - game.getGridSize() + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() - game.getGridSize() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberLeftColumn.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberLeftColumn.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberLeftColumn.getPosition() + game.getGridSize() + 1, new Cell(CellType.MINE, numberLeftColumn.getPosition() + game.getGridSize() + 1, false, false, handler, game));
        handler.handleNumberCell(numberLeftColumn.getPosition(), numberLeftColumn, game);
        assertEquals("5", numberLeftColumn.getText());
    }
    @Test
    void numberCellInRightColumnShouldReturnDangerCount5() {
        Cell numberRightColumn = new Cell(CellType.NUMBER, (game.getGridSize() + game.getGridSize() * 2) - 1, false, false, handler, game);
        Grid.cellGrid.set(numberRightColumn.getPosition() - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberRightColumn.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberRightColumn.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberRightColumn.getPosition() - game.getGridSize() - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() - game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberRightColumn.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberRightColumn.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberRightColumn.getPosition() + game.getGridSize() - 1, new Cell(CellType.MINE, numberRightColumn.getPosition() + game.getGridSize() - 1, false, false, handler, game));
        assertEquals(5, handler.setDangerCounterRightColumn(numberRightColumn.getPosition(), game));
    }
    @Test
    void numberCellNotOnEdgeShouldReturnDangerCount8() {
        Cell numberCenter = new Cell(CellType.NUMBER, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        Grid.cellGrid.set(numberCenter.getPosition() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize() + 1, new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize() - 1, new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize() + 1, false, false, handler, game));
        assertEquals(8, handler.setDangerCounterNotOnEdge(numberCenter.getPosition(), game));
    }
    @Test
    void numberCellNotOnEdgeShouldHaveText8() {
        Cell numberCenter = new Cell(CellType.NUMBER, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        Grid.cellGrid.set(numberCenter.getPosition() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize(), new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize(), new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize(), false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize() - 1, new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() - game.getGridSize() + 1, new Cell(CellType.MINE, numberCenter.getPosition() - game.getGridSize() + 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize() - 1, new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize() - 1, false, false, handler, game));
        Grid.cellGrid.set(numberCenter.getPosition() + game.getGridSize() + 1, new Cell(CellType.MINE, numberCenter.getPosition() + game.getGridSize() + 1, false, false, handler, game));
        handler.handleNumberCell(game.getGridSize() + (game.getGridSize() / 2), numberCenter, game);
        assertEquals("8", numberCenter.getText());
    }
    @Test
    void ifClickMineCellAllCellsNoCellsEnabled() {
        Cell mineCenter = new Cell(CellType.MINE, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        handler.handleMineCell(mineCenter);
        int cells = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
        assertTrue(cells == mockGrid.getBound());
    }
    @Test
    void shouldNotWinIfConditionsNotMet() {
        int discoveredCells = 0;
        Handler.winConditionsMet(discoveredCells, game);
        int cells = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();

       assertTrue(cells == 0);
    }
    @Test
    void shouldWinIfConditionsMet() {
        int discoveredCells = 90;
        Handler.winConditionsMet(discoveredCells, game);
        int cellCounter = (int) mockGrid.cellGrid.stream()
                .filter(x -> x.isEnabled() == false)
                .count();
        assertTrue(cellCounter == 100);
    }
    @Test
    void ifUndiscoveredCellRightClickedIsFlagged() {
        Cell mineCenter = new Cell(CellType.MINE, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        handler.rightClick(mineCenter);
        assertTrue(mineCenter.isFlagged());
    }
    @Test
    void ifNotRightClickedIsNotFlagged() {
        Cell mineCenterUnFlagged = new Cell(CellType.MINE, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        assertFalse(mineCenterUnFlagged.isFlagged());
    }
    @Test
    void ifFlaggedCellIsRightClickedShouldUnFlagCell() {
        Cell mineCenterFlagged = new Cell(CellType.MINE, game.getGridSize() + (game.getGridSize() / 2), false, true, handler, game);
        handler.rightClick(mineCenterFlagged);
        assertFalse(mineCenterFlagged.isFlagged());
    }
    @Test
    void if1CellFlaggedShouldReturn1FlaggedCell() {
        Cell mineCenter1 = new Cell(CellType.MINE, game.getGridSize() + (game.getGridSize() / 2), false, false, handler, game);
        handler.rightClick(mineCenter1);
        assertEquals(1, handler.getFlaggedCells());
    }
}