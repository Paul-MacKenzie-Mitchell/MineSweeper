import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Handler handler =  new Handler();
    JFrame frame = new JFrame();
    Game game = new Game (GameInfo.WIDTH, GameInfo.HEIGHT, GameInfo.MINECOUNT, GameInfo.getGridsize(), handler);
    Cell mineTopLeft = new Cell(CellType.MINE, 0, false, false, handler, game);
    Cell unClickedCellMiddle = new Cell(CellType.BLANK, game.getGridSize() * game.getGridSize()/ 2 + game.getGridSize() / 2, false, false, handler, game);
    Cell unFlaggedMiddle = new Cell(CellType.BLANK, game.getGridSize() * game.getGridSize()/ 2 + game.getGridSize() / 2, false, false, handler, game);
    Cell clickedCellMiddle = new Cell(CellType.BLANK, game.getGridSize() * game.getGridSize()/ 2 + game.getGridSize() / 2, true, false, handler, game);
    Cell rightClickedFlaggedCell = new Cell(CellType.BLANK, 0, false, true, handler, game);
    Cell numberBottomLeftCorner = new Cell(CellType.NUMBER, game.getGridSize() *(game.getGridSize() -1), false, false, handler, game);
    @Test
    void shouldNotBeNull() {
        CellType actual = mineTopLeft.getCellType();
        assertNotNull(actual);
    }
    @Test
    void shouldReturnMine() {
        CellType actual = mineTopLeft.getCellType();
        CellType expected = CellType.MINE;
        assertEquals(actual, expected);
    }
    @Test
    void shouldNotReturnIncorrectTypeForMineCell() {
        CellType actual = mineTopLeft.getCellType();
        CellType unexpected1 = CellType.BLANK;
        CellType unexpected2 = CellType.NUMBER;
        assertNotEquals(unexpected1, actual);
        assertNotEquals(unexpected2, actual);
    }
    @Test
    void shouldReturnNumberCell() {
        CellType actual = numberBottomLeftCorner.getCellType();
        CellType expected = CellType.NUMBER;
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotReturnIncorrectTypeForNumberCell() {
        CellType actual = numberBottomLeftCorner.getCellType();
        CellType unexpected1 = CellType.BLANK;
        CellType unexpected2 = CellType.MINE;
        assertNotEquals(unexpected1, actual);
        assertNotEquals(unexpected2, actual);
    }
    @Test
    void shouldReturBlankCell() {
        CellType actual = unClickedCellMiddle.getCellType();
        CellType expected = CellType.BLANK;
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotReturnIncorrectTypeForBlankCell() {
        CellType actual = unClickedCellMiddle.getCellType();
        CellType unexpected1 = CellType.NUMBER;
        CellType unexpected2 = CellType.MINE;
        assertNotEquals(unexpected1, actual);
        assertNotEquals(unexpected2, actual);
    }
    @Test
    void shouldReturnCorrectPosition() {
        int actualPosition = mineTopLeft.getPosition();
        int expectedPosition = 0;
        assertEquals(expectedPosition, actualPosition);
    }
    @Test
    void shouldNotReturnIncorrectPostion() {
        int actualPosition = mineTopLeft.getPosition();
        assertTrue(actualPosition < 1);
        assertTrue(actualPosition > -1);
    }
    @Test
    void shouldBeDiscovered() {
        boolean actualDiscovered = clickedCellMiddle.isDiscovered();
        boolean expected = true;
        assertEquals(expected, actualDiscovered);
    }
    @Test
    void shouldNotReturnDiscovered() {
        boolean actualDiscovered = unClickedCellMiddle.isDiscovered();
        boolean expected = false;
        assertEquals(false, actualDiscovered);
    }
    @Test
    void shouldReturnFlagged() {
        boolean actualFlagged = rightClickedFlaggedCell.isFlagged();
        boolean expected = true;
        assertEquals(expected, actualFlagged);
    }
    @Test
    void shouldNotReturnFlagged() {
        boolean actualFlagged = unClickedCellMiddle.isFlagged();
        boolean expected = false;
        assertEquals(expected, actualFlagged);
    }
    @Test
    void shouldSetFlagged() {
        unFlaggedMiddle.setFlagged(true);
        boolean actualFlagged = unFlaggedMiddle.isFlagged();
        boolean expected = true;
        assertEquals(expected, actualFlagged);
    }
}