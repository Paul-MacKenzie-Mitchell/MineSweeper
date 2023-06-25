import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    private static JFrame frame;
    private static String title;
    private static int width;
    private static int height;
    private Game game;

    private static Grid grid;

    //Getters and Setters

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static Grid getGrid() {
        return grid;
    }

    //Constructor

    public Window(int width, int height, int gridSize, String title, Handler handler, Game game, JFrame frame) {
        Window.width = width;
        Window.height = height;
        Window.title = title;
        Window.frame = frame;
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        //exits application on closing window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //places window in center of screen
        frame.setLocationRelativeTo(null);
        Window.grid = new Grid(new GridLayout(gridSize, gridSize), handler, game);
        JPanel panel = grid;

        frame.setContentPane(panel);
        //initiates with 0 flags posted
        update(0);
        frame.pack();
        frame.setVisible(true);
    }

    //Method updates the number of Flags Placed
    public static void update(int flagged) {
        frame.setTitle(title + " | Mines: " + Constants.MINECOUNT + " - Flags: " + flagged);
    }

    //methods for pop up window when game is a win or loss
    public void win() {
        JOptionPane.showMessageDialog(Window.frame,"You Won!");
    }
    public void lose() {
        JOptionPane.showMessageDialog(Window.frame, "You Lost...");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Window window)) return false;

        return game.equals(window.game);
    }

    @Override
    public int hashCode() {
        return game.hashCode();
    }
}
