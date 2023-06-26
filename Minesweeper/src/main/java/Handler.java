import java.util.ArrayList;

public class Handler {

    private ArrayList<Cell> current = new ArrayList<Cell>();

    private ArrayList<Cell> queue = new ArrayList<Cell>();
    private static int flaggedCells = 0;

    //Getters and Setters

    public int getFlaggedCells() {
        return flaggedCells;
    }

    public void setFlaggedCells(int flaggedCells) {
        Handler.flaggedCells = flaggedCells;
    }

    public ArrayList<Cell> getCurrent() {
        return current;
    }

    public ArrayList<Cell> getQueue() {
        return queue;
    }

    //Methods

    public void click(Cell cell, Game game) {
        int discoveredCells = 0;
        if (!cell.isFlagged()) {
            cell.setEnabled(false);
            cell.setDiscovered(true);

            int position = cell.getPosition();
            //Determine Action Taken
            if (cell.getCellType() == CellType.BLANK) {
                handleBlankCell(position, game);
            } else if (cell.getCellType() == CellType.NUMBER) {
                handleNumberCell(position, cell, game);
            } else if (cell.getCellType() == CellType.MINE) {
                handleMineCell(cell, game);
            }

            addToCurrentList(queue, current);

            revealCells(current, game);

            discoveredCells = determineDiscoveredCells(discoveredCells);

            winConditionsMet(discoveredCells, game);
        }
    }


    public void handleMineCell(Cell cell, Game game) {
        for (int x = 0; x < Grid.cellGrid.size(); x++) {
            Grid.cellGrid.get(x).setEnabled(false);
            Grid.cellGrid.get(x).setText("");
            if (Grid.cellGrid.get(x).getCellType() == CellType.MINE ) {
                Grid.cellGrid.get(x).setText("\uD83D\uDCA3");
            }
            cell.setText("\uD83D\uDCA3");
        }
        game.getWindow().lose();
        game.setLost(true);
    }

    public void handleNumberCell(int position, Cell cell, Game game) {
        int dangerCount = 0;
        if (position < game.getGridSize()) {
            dangerCount = setDangerCountTopRow(position, game);
        } else if (position >= (game.getGridSize() *(game.getGridSize() -1))) {
            dangerCount = setDangerCounterBottomRow(position, game);
        } else if (position % game.getGridSize() == 0) {
            dangerCount = setDangerCountLeftColumn(position, game);
        } else if (position % game.getGridSize() == game.getGridSize() -1) {
            dangerCount = setDangerCounterRightColumn(position, game);
        } else {
            dangerCount = setDangerCounterNotOnEdge(position, game);
        }
        cell.setText(String.valueOf(dangerCount));
    }

    public void handleBlankCell(int position, Game game) {
        //if cell is on first row
        if(position < game.getGridSize()) {
            queueIfTopRow(position, game);
//                    //if the cell is in the bottom row
        } else if (position >= (game.getGridSize() * (game.getGridSize() -1))) {
            queueIfBottomRow(position, game);
            //if cells on left most column
        } else if (position % game.getGridSize() == 0) {
            queueIfLeftColumn(position, game);
            //if cells on right most column
        } else if (position % game.getGridSize() == game.getGridSize() - 1) {
            queueIfRightColumn(position, game);
            //if cell is not on edge of grid
        } else {
            queueIfNotOnEdge(position, game);
        }
    }

    public void queueIfTopRow(int position, Game game) {
        if (position % game.getGridSize() == 0) {
            queue.add(Grid.cellGrid.get((position + game.getGridSize())));
            queue.add(Grid.cellGrid.get((position + game.getGridSize() + 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            //if the cell is in the top right
        } else if (position % game.getGridSize() == game.getGridSize() - 1) {
            queue.add(Grid.cellGrid.get((position + game.getGridSize())));
            queue.add(Grid.cellGrid.get((position + game.getGridSize() - 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        } else {
            queue.add(Grid.cellGrid.get((position + game.getGridSize())));
            queue.add(Grid.cellGrid.get((position + game.getGridSize() + 1)));
            queue.add(Grid.cellGrid.get((position + game.getGridSize() - 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        }
    }

    public void queueIfBottomRow(int position, Game game) {
        //if the cell is in the bottom left
        if (position % game.getGridSize() ==0) {
            queue.add(Grid.cellGrid.get((position - game.getGridSize())));
            queue.add(Grid.cellGrid.get((position - game.getGridSize() + 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            //if the cell is in the bottom right
        } else if (position % game.getGridSize() == game.getGridSize() - 1) {
            queue.add(Grid.cellGrid.get((position - game.getGridSize())));
            queue.add(Grid.cellGrid.get((position - game.getGridSize() - 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        } else {
            queue.add(Grid.cellGrid.get((position - game.getGridSize())));
            queue.add(Grid.cellGrid.get((position - game.getGridSize() + 1)));
            queue.add(Grid.cellGrid.get((position - game.getGridSize() - 1)));
            queue.add(Grid.cellGrid.get((position + 1)));
            queue.add(Grid.cellGrid.get((position - 1)));
        }
    }

    public void queueIfLeftColumn(int position, Game game) {
        queue.add(Grid.cellGrid.get((position - game.getGridSize() + 1)));
        queue.add(Grid.cellGrid.get((position - game.getGridSize())));
        queue.add(Grid.cellGrid.get((position + 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize() + 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize())));
    }

    public void queueIfRightColumn(int position, Game game) {
        queue.add(Grid.cellGrid.get((position - game.getGridSize() - 1)));
        queue.add(Grid.cellGrid.get((position - game.getGridSize())));
        queue.add(Grid.cellGrid.get((position - 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize() - 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize())));
    }

    public void queueIfNotOnEdge(int position, Game game) {
        queue.add(Grid.cellGrid.get((position - game.getGridSize() + 1)));
        queue.add(Grid.cellGrid.get((position - game.getGridSize())));
        queue.add(Grid.cellGrid.get((position - game.getGridSize() - 1)));
        queue.add(Grid.cellGrid.get((position - 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize() - 1)));
        queue.add(Grid.cellGrid.get((position + game.getGridSize())));
        queue.add(Grid.cellGrid.get((position + game.getGridSize() + 1)));
        queue.add(Grid.cellGrid.get((position + 1)));
    }

    public int setDangerCountTopRow(int position, Game game) {
        int dangerCount = 0;
        if(position % game.getGridSize() == 0) {
            if (Grid.cellGrid.get((position + game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else if (position % game.getGridSize() == game.getGridSize() - 1) {
            if (Grid.cellGrid.get((position + game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else {
            if (Grid.cellGrid.get((position + game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        }
        return dangerCount;
    }
    public int setDangerCounterBottomRow (int position, Game game) {
        int dangerCount = 0;
        if(position % game.getGridSize() == 0) {
            if (Grid.cellGrid.get((position - game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else if (position % game.getGridSize() == game.getGridSize() - 1) {
            if (Grid.cellGrid.get((position - game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        } else {
            if (Grid.cellGrid.get((position - game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
            if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        }
        return dangerCount;
    }
    public int setDangerCountLeftColumn(int position, Game game) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        return dangerCount;
    }
    public int setDangerCounterRightColumn(int position, Game game) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        return dangerCount;
    }
    public int setDangerCounterNotOnEdge(int position, Game game) {
        int dangerCount = 0;
        if (Grid.cellGrid.get((position - game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount++;
        if (Grid.cellGrid.get((position + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize() + 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize())).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position + game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - 1)).getCellType() == CellType.MINE) dangerCount ++;
        if (Grid.cellGrid.get((position - game.getGridSize() - 1)).getCellType() == CellType.MINE) dangerCount ++;
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
    public static void revealCells(ArrayList<Cell> current, Game game) {
        while (!current.isEmpty()) {
            Cell temp = current.get(0);
            current.remove(0);
            temp.clickButton(game);
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
    public void winConditionsMet(int discoveredCells, Game game) {
        if (discoveredCells == Grid.cellGrid.size() - game.getMineCount()) {
            for (int x = 0; x < Grid.cellGrid.size(); x ++) {
                if (Grid.cellGrid.get(x).getCellType() == CellType.MINE) {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("\uD83D\uDCA3");
                } else {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("\uD83E\uDD73");
                }
            }
            game.getWindow().win();
            game.setWon(true);
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Handler handler)) return false;

        if (!getCurrent().equals(handler.getCurrent())) return false;
        return getQueue().equals(handler.getQueue());
    }

    @Override
    public int hashCode() {
        int result = getCurrent().hashCode();
        result = 31 * result + getQueue().hashCode();
        return result;
    }
}