import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JButton {
    private CellType cellType;
    private int position;
    private boolean discovered;
    private boolean flagged;

    private final Handler handler;

    //Constructor

    public Cell(CellType type, int position, boolean discovered, boolean flagged, Handler handler, Game game) {
        this.cellType = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;

        //Methods

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                        rightClickButton();
                } else {
                    clickButton(game);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    // Getters and Setters

    public CellType getCellType() {
        return cellType;
    }

    public int getPosition() {
        return position;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    // Click Methods

    public void clickButton(Game game) {
        handler.click(this, game);
    }
    public void rightClickButton() {
        handler.rightClick(this);
    }
}
