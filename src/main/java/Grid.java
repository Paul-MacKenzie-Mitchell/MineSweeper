import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Grid extends JPanel {
    //Total number of cells
    private int bound;
    //used to determine if a cell has already been chosen as a mine
    private boolean picked = false;


    //position of mines in grid
    private ArrayList<Integer> mines = new ArrayList<Integer>();

    //cells in grid
    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();

    public Grid(GridLayout g, Handler handler, Game game) {
        super(g);
        setBound(game.getGridSize() * game.getGridSize());
        createCells(handler, game);
        addCells();
    }

    public ArrayList<Cell> getCellGrid() {
        return cellGrid;
    }
    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public void createCells(Handler handler, Game game) {

        createMineLocations(game);
        // Creates types of cells for each of the positions in the grid
        for (int i = 0; i < bound; i++) {
            // if this position has been determined to be a mine creates a mine cell and add it to the gridCell list
            if (mines.contains(i)) {
                cellGrid.add(new Cell(CellType.MINE, i, false, false, handler, game));
                // if the position of the cell is on the left column of the grid
            } else if (i % game.getGridSize() == 0){
                farLeftColumnCellAssignment(i, handler, game);
                // if the position of the cell is on the far right column
            } else if (i % game.getGridSize() == game.getGridSize() - 1){
                farRightColumnCellAssignment(i, handler, game);
            } else {
                //the cell is not on the far left or far right of the grid
                remainingCellAssignments(i, handler, game);
            }
        }
    }

    private void remainingCellAssignments(int i, Handler handler, Game game) {
        if (mines.contains(i - game.getGridSize() - 1) ||
                mines.contains(i - game.getGridSize()) ||
                mines.contains(i - game.getGridSize() + 1) ||
                mines.contains(i - 1) ||
                mines.contains(i + 1) ||
                mines.contains(i + game.getGridSize() - 1) ||
                mines.contains(i + game.getGridSize()) ||
                mines.contains(i + game.getGridSize() + 1)) {
            //determine if it is adjacent to a mine and assign it as a number and add it to the gridCell list
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler, game));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler, game));
        }
    }

    private void farRightColumnCellAssignment(int i, Handler handler, Game game) {
        if (mines.contains(i - game.getGridSize() - 1) ||
                mines.contains(i - game.getGridSize()) ||
                mines.contains(i - 1) ||
                mines.contains(i + game.getGridSize() - 1) ||
                mines.contains(i + game.getGridSize())) {
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler, game));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler, game));
        }
    }

    private void farLeftColumnCellAssignment(int i, Handler handler, Game game) {
        if (mines.contains(i - game.getGridSize()) ||
                mines.contains(i - game.getGridSize() + 1) ||
                mines.contains(i + 1) ||
                mines.contains(i + game.getGridSize()) ||
                mines.contains(i + game.getGridSize() + 1)) {
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler, game));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler, game));
        }
    }

    public void createMineLocations(Game game) {

        List<Integer> randomMinePlacement = new ArrayList<>();
        for (int i = 0; i <= game.getGridSize() * game.getGridSize(); i++) {
            randomMinePlacement.add(i);
        }
        Collections.shuffle(randomMinePlacement);
        randomMinePlacement.stream().limit(game.getMineCount()).forEach(m -> picked = true);
        mines = (ArrayList<Integer>) randomMinePlacement.stream()
                .limit(game.getMineCount())
                .collect(Collectors.toList());
    }
    //Add the cells to the JPanel from cellGrid List
    private void addCells() {
        for (Cell cell : cellGrid) {
            add(cell);
        }
    }
    public void clearMines(Grid grid) {
        grid.mines.clear();
    }
}