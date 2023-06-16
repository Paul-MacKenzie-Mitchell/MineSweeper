import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid extends JPanel {
    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    //Total number of cells
    private int bound = Game.GRIDSIZE * Game.GRIDSIZE;
    //used to determine if a cell has already been chosen as a mine
    private boolean picked = false;


    //position of mines in grid
    private ArrayList<Integer> mines = new ArrayList<Integer>();


    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();

    public Grid(GridLayout g, Handler handler) {
        super(g);
        createCells(handler);
        addCells();
    }
    public ArrayList<Integer> getMines() {
        return mines;
    }

    public static ArrayList<Cell> getCellGrid() {
        return cellGrid;
    }


    public void createCells(Handler handler) {
        createMineLocations();
        // Creates types of cells for each of the positions in the grid
        for (int i = 0; i < bound; i++) {
            // if this position has been determined to be a mine creates a mine cell and add it to the gridCell list
            if (mines.contains(i)) {
                cellGrid.add(new Cell(CellType.MINE, i, false, false, handler));
            // if the position of the cell is on the left column of the grid
            } else if (i % Game.GRIDSIZE == 0){
                farLeftColumnCellAssignment(i, handler);
            // if the position of the cell is on the far right column
            } else if (i % Game.GRIDSIZE == Game.GRIDSIZE - 1){
                farRightColumnCellAssignment(i, handler);
            } else {
                //the cell is not on the far left or far right of the grid
                remainingCellAssignments(i, handler);
            }
        }
    }

    private void remainingCellAssignments(int i, Handler handler) {
        if (mines.contains(i - Game.GRIDSIZE - 1) ||
                mines.contains(i - Game.GRIDSIZE) ||
                mines.contains(i - Game.GRIDSIZE + 1) ||
                mines.contains(i - 1) ||
                mines.contains(i + 1) ||
                mines.contains(i + Game.GRIDSIZE - 1) ||
                mines.contains(i + Game.GRIDSIZE) ||
                mines.contains(i + Game.GRIDSIZE + 1)) {
            //determine if it is adjacent to a mine and assign it as a number and add it to the gridCell list
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler));
        }
    }

    private void farRightColumnCellAssignment(int i, Handler handler) {
        if (mines.contains(i - Game.GRIDSIZE - 1) ||
                mines.contains(i - Game.GRIDSIZE) ||
                mines.contains(i - 1) ||
                mines.contains(i + Game.GRIDSIZE - 1) ||
                mines.contains(i + Game.GRIDSIZE)) {
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler));
        }
    }

    private void farLeftColumnCellAssignment(int i, Handler handler) {
        if (mines.contains(i - Game.GRIDSIZE) ||
                mines.contains(i - Game.GRIDSIZE + 1) ||
                mines.contains(i + 1) ||
                mines.contains(i + Game.GRIDSIZE) ||
                mines.contains(i + Game.GRIDSIZE + 1)) {
            cellGrid.add(new Cell(CellType.NUMBER, i, false, false, handler));
        } else {
            // or assign it as blank and add it to the gridCell list
            cellGrid.add(new Cell(CellType.BLANK, i, false, false, handler));
        }
    }

    public void createMineLocations() {
        for (int i = 1; i <= Game.MINECOUNT; i++) {
            while (!picked) {
                int minePosition = (int)(Math.random() * bound);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    picked = true;
                }
            }
            picked = false;
        }
    }
    //Add the cells to the JPanel from cellGrid List
    private void addCells() {
        for (int i = 0; i < cellGrid.size(); i++) {
            add(cellGrid.get(i));
        }
    }
}