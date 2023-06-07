import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid extends JPanel {
    //Total number of cells
    private int bound = Game.GRIDSIZE * Game.GRIDSIZE;
  //
    private boolean picked = false;
    //position of mines in grid
    private ArrayList<Integer> mines = new ArrayList<Integer>();
    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();

    public Grid(GridLayout g, Handler handler) {
        super(g);
        createCells(handler);
        addCells();
    }

    private void createCells(Handler handler) {
        //creates mine positions for the number of mines in the game.  Does not allow duplicate positions
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
        // Creates types of cells for each of the positions in the grid
        for (int i = 0; i < bound; i++) {
            // if this position has been determined to be a mine creates a mine cell and add it to the gridCell list
            if (mines.contains(i)) {
                cellGrid.add(new Cell(1, i, false, false, handler));
            // if the position of the cell is on the far left side of the grid
            } else if (i % Game.GRIDSIZE == 0){
            //determine if it is adjacent to a mine and assign it as a number and add it to the gridCell list
                if (mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(2, i, false, false, handler));
                } else {
                // or assign it as blank and add it to the gridCell list
                    cellGrid.add(new Cell(0, i, false, false, handler));
                }
            // if the position of the cell is on the far right
            } else if (i % Game.GRIDSIZE == Game.GRIDSIZE - 1){
                //determine if it is adjacent to a mine and assign it as a number and add it to the gridCell list
                if (mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + Game.GRIDSIZE - 1) ||
                        mines.contains(i + Game.GRIDSIZE)) {
                    cellGrid.add(new Cell(2, i, false, false, handler));
                } else {
                // or assign it as blank and add it to the gridCell list
                    cellGrid.add(new Cell(0, i, false, false, handler));
                }
            } else {
                //the cell is not on the far left or far right of the grid
                if (mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE - 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    //determine if it is adjacent to a mine and assign it as a number and add it to the gridCell list
                    cellGrid.add(new Cell(2, i, false, false, handler));
                } else {
                    // or assign it as blank and add it to the gridCell list
                    cellGrid.add(new Cell(0, i, false, false, handler));
                }
            }
        }
    }
    //Add the cells to the JPanel from cellGrid List
    private void addCells() {
        for (int i = 0; i < cellGrid.size(); i++) {
            add(cellGrid.get(i));
        }
    }
}