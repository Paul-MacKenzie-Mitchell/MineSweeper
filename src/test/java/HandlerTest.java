import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class
HandlerTest {
    Handler handler = new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);
    Grid mockGrid = Mockito.mock(Grid.class);

    @BeforeEach
    void setup() {
        handler.setFlaggedCells(0);
        Grid.cellGrid.clear();
        game.runGame(game);
        game.playGame();
        when(mockGrid.getBound())
                .thenReturn((game.getGridSize() * game.getGridSize()));
    }
    @AfterEach
    void reset() {
        game.getWindow().getFrame().dispose();
        handler.setFlaggedCells(0);
        Grid.cellGrid.clear();
    }
    @Test
    void clickedUnFlaggedCellShouldBecomeDiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
//        ActionEvent e = new ActionEvent(MouseEvent,);
//        JButton buttonToSimulateClicking = new JButton(...);
//        buttonToSimulateClicking.doClick(); // As simple as that !;
//        game.getWindow().actionPerformed(e);
        assertTrue(mineTopLeft.isDiscovered());
    }
    @Test
    void clickedUnFlaggedCellShouldNotRemainUndiscovered() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
        assertTrue(mineTopLeft.isDiscovered());

    }
    @Test
    void clickedUnFlaggedCellShouldBecomeUnEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false,false, handler, game);
        handler.click(mineTopLeft, game);
        assertFalse(mineTopLeft.isEnabled());

    }
    @Test
    void clickedUnFlaggedCellShouldNotBecomeEnabled() {
        Cell mineTopLeft = new Cell(CellType.MINE, 0, false, false, handler, game);
        handler.click(mineTopLeft, game);
        assertFalse(mineTopLeft.isEnabled());


    }
    @Test
    void unClickedUnFlaggedCellShouldNotBecomeDiscovered() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + game.getGridSize() / 2, false, false, handler, game);
        assertFalse(unClickedCellMiddle.isDiscovered());

    }
    @Test
    void unClickedUnFlaggedCellShouldBecomeEnabled() {
        Cell unClickedCellMiddle = new Cell(CellType.BLANK, mockGrid.getBound() / 2 + game.getGridSize() / 2, false, false, handler, game);
        assertTrue(unClickedCellMiddle.isEnabled());

    }
    @Test
    void flaggedCellShouldRemainEnabled() {
        Cell clickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler, game);
        handler.click(clickedFlaggedCell, game);
        assertTrue(clickedFlaggedCell.isEnabled());

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
        assertEquals(0, current.size());
    }
    @Test
    void getCurrentWhenEmptyShouldBeEmpty() {
        ArrayList<Cell> current = handler.getCurrent();
        int numCellsInCurrent = current.size();
        assertEquals(0, numCellsInCurrent);
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
        assertEquals(1, numCellsInCurrent);
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
        assertEquals(1, current.size());
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
        assertEquals(0, queue.size());
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
        assertEquals(0, current.size());
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
        assertTrue(blankRightColumn.isDiscovered());
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
        assertFalse(blankRightColumn.isEnabled());
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
        handler.handleMineCell(mineCenter, game);
        int cells = (int) Grid.cellGrid.stream()
                .filter(x -> !x.isEnabled())
                .count();
        assertEquals(cells, mockGrid.getBound());
    }
    @Test
    void shouldNotWinIfConditionsNotMet() {
        int discoveredCells = 0;
        handler.winConditionsMet(discoveredCells, game);
        int cells = (int) Grid.cellGrid.stream()
                .filter(x -> !x.isEnabled())
                .count();

        assertEquals(0, cells);
    }
    @Test
    void shouldWinIfConditionsMet() {
        int discoveredCells = 90;
        handler.winConditionsMet(discoveredCells, game);

        int cellCounter = (int) Grid.cellGrid.stream()
                .filter(x -> !x.isEnabled())
                .count();

        assertEquals(100, cellCounter);
        game.getWindow().getFrame().dispose();
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