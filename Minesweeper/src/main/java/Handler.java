import java.util.ArrayList;
import java.util.List;

public class Handler {
    private ArrayList<Cell> current = new ArrayList<Cell>();

    private ArrayList<Cell> queue = new ArrayList<Cell>();
    private static int flaggedCells = 0;

    public static int getFlaggedCells() {
        return flaggedCells;
    }

    public static void setFlaggedCells(int flaggedCells) {
        Handler.flaggedCells = flaggedCells;
    }
    public ArrayList<Cell> getCurrent() {
        return current;
    }

    public ArrayList<Cell> getQueue() {
        return queue;
    }

    public void click(Cell cell) {
        int discoveredCells = 0;
        if (!cell.isFlagged()) {
                cell.setEnabled(false);
                cell.setDiscovered(true);

            int position = cell.getPosition();
            //Determine Action Taken
            if (cell.getCellType() == CellType.BLANK) {
               handleBlankCell(position);
            } else if (cell.getCellType() == CellType.NUMBER) {
                handleNumberCell(position, cell);
            } else if (cell.getCellType() == CellType.MINE) {
                handleMineCell(cell);
            }

            addToCurrentList(queue, current);

            revealCells(current);

            discoveredCells = determineDiscoveredCells(discoveredCells);

            winConditionsMet(discoveredCells);
        }
    }


    public void handleMineCell(Cell cell) {
        for (int x = 0; x < Grid.cellGrid.size(); x++) {
            Grid.cellGrid.get(x).setEnabled(false);
            Grid.cellGrid.get(x).setText("");
            if (Grid.cellGrid.get(x).getCellType() == CellType.MINE ) {
                Grid.cellGrid.get(x).setText("\uD83D\uDCA3");
            }
            cell.setText("\uD83D\uDCA3");
        }
    }

    public void handleNumberCell(int position, Cell cell) {
        int dangerCount = 0;
        if (position < Game.GRIDSIZE) {
            dangerCount = setDangerCountTopRow(position);
        } else if (position >= (Game.GRIDSIZE *(Game.GRIDSIZE -1))) {
            dangerCount = setDangerCounterBottomRow(position);
        } else if (position % Game.GRIDSIZE == 0) {
            dangerCount = setDangerCountLeftColumn(position);
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE -1) {
            dangerCount = setDangerCounterRightColumn(position);
        } else {
            dangerCount = setDangerCounterNotOnEdge(position);
        }
        cell.setText(String.valueOf(dangerCount));
    }

    public void handleBlankCell(int position) {
        //if cell is on first row
        if(position < Game.GRIDSIZE) {
            queueIfTopRow(position);
//                    //if the cell is in the bottom row
        } else if (position >= (Game.GRIDSIZE * (Game.GRIDSIZE -1))) {
            queueIfBottomRow(position);
            //if cells on left most column
        } else if (position % Game.GRIDSIZE == 0) {
            queueIfLeftColumn(position);
            //if cells on right most column
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
            queueIfRightColumn(position);
            //if cell is not on edge of grid
        } else {
            queueIfNotOnEdge(position);
        }
    }

    public void queueIfTopRow(int position) {
        if (position % Game.GRIDSIZE ==0) {
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            //if the cell is in the top right
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        } else {
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
            queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        }
    }

    public void queueIfBottomRow(int position) {
        //if the cell is in the bottom left
        if (position % Game.GRIDSIZE ==0) {
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            //if the cell is in the bottom right
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        } else {
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
            queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        }
    }

    public void queueIfLeftColumn(int position) {
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
        queue.add(Grid.cellGrid.get((position + 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
    }

    public void queueIfRightColumn(int position) {
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
        queue.add(Grid.cellGrid.get((position - 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
    }

    public void queueIfNotOnEdge(int position) {
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
        queue.add(Grid.cellGrid.get((position - 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
        queue.add(Grid.cellGrid.get((position + 1)));
    }

    public int setDangerCountTopRow(int position) {
        int dangerCount = 0;
        if(position % Game.GRIDSIZE == 0) {
            if (Grid.cellGrid.get((position +Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position +Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
            if (Grid.cellGrid.get((position +Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position +Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else {
            if (Grid.cellGrid.get((position +Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position +Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position +Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        }
        return dangerCount;
    }
    public int setDangerCounterBottomRow (int position) {
        int dangerCount = 0;
        if(position % Game.GRIDSIZE == 0) {
            if (Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else if (position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
            if (Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else {
            if (Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        }
        return dangerCount;
    }
    public int setDangerCountLeftColumn(int position) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        return dangerCount;
    }
    public int setDangerCounterRightColumn(int position) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        return dangerCount;
    }
    public int setDangerCounterNotOnEdge(int position) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount++;
        if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getCellType() == CellType.MINE) dangerCount ++;
        return dangerCount;
    }
    public static void addToCurrentList(ArrayList<Cell> queue, ArrayList<Cell> current) {
        for(int x = 0; x < queue.size(); x++) {
            if(!queue.get(x).isDiscovered()) {
                current.add(queue.get(x));
                queue.get(x).setDiscovered(true);
            }
        }
        queue.clear();
    }
    public static void revealCells(ArrayList<Cell> current) {
        while (!current.isEmpty()) {
            Cell temp = current.get(0);
            current.remove(0);
            temp.clickButton();
        }
    }
    public static int determineDiscoveredCells(int discoveredCells) {
        for (int x = 0; x < Grid.cellGrid.size(); x++) {
            if(Grid.cellGrid.get(x).isDiscovered()) {
                discoveredCells++;
            }
        }
        return discoveredCells;
    }
    public static void winConditionsMet(int discoveredCells) {
        if (discoveredCells == Grid.cellGrid.size() - Game.MINECOUNT) {
            for (int x = 0; x < Grid.cellGrid.size(); x ++) {
                if (Grid.cellGrid.get(x).getCellType() == CellType.MINE) {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("\uD83D\uDCA3");
                } else {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("\uD83E\uDD73");
                }
            }
        }
    }
    public void rightClick(Cell cell) {
        if (!cell.isDiscovered()) {
            if (!cell.isFlagged()) {
                cell.setFlagged(true);
                cell.setText("⚑");
                flaggedCells++;
                Window.update(flaggedCells);
            } else {
                cell.setFlagged(false);
                cell.setText("");
                flaggedCells--;
                Window.update(flaggedCells);
            }
        }
    }
}