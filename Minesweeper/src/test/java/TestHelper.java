import java.util.ArrayList;

public class TestHelper {
        public static final int WIDTH = 720;
        public static final int HEIGHT= 720;
        public static final int GRIDSIZE = 10;
        public static final int MINECOUNT = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);

        private static Handler handler = new Handler();

        public static Game makeGame() {
            Game game = new Game();
            return game;
        }

        public static Window makeWindow() {
            Window window = new Window(WIDTH, HEIGHT, GRIDSIZE, "Minesweeper", handler);
            return window;
        }

        public static ArrayList<Cell> makeCellGrid() {
            ArrayList<Cell> testCellGrid = new ArrayList<>();
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 1,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 2,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 3,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 4,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 5,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 6,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 7,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 8,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 9,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 10,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 1,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 12,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 13,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 14,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 15,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 16,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 17,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 18,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 19,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 20,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 21,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 22,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 23,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 24,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 25,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 26,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 27,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 28,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 29,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 30,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 31,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 32,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 33,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 34,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 35,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 36,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 37,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 38,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));
            testCellGrid.add(new Cell(CellType.BLANK, 0,false, false, handler));

            return testCellGrid;
        }
    }