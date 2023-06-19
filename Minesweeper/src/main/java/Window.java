import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame frame;
    private static String title;
    private static int width;
    private static int height;

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
//Constructor

    public Window(int width, int height, int gridSize, String title, Handler handler) {
        Window.width = width;
        Window.height = height;
        Window.title = title;
        frame = new JFrame(title);
        //
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        //exits application on closing window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //places window in center of screen
        frame.setLocationRelativeTo(null);

        JPanel panel = new Grid(new GridLayout(gridSize, gridSize), handler);

        frame.setContentPane(panel);
        //initiates with 0 flags posted
        update(0);
        frame.pack();
        frame.setVisible(true);
    }
    public void resetJPanel(int gridSize, Handler handler) {
        JPanel panel = new Grid(new GridLayout(gridSize, gridSize), handler);
        frame.setContentPane(panel);
        //initiates with 0 flags posted
        update(0);
        frame.pack();
        frame.setVisible(true);
    }
    //Method Updates the number of flags that have been posted on the board

    public static void update(int flagged) {
        frame.setTitle(title + " | Mines: " + Game.MINECOUNT + " - Flags: " + flagged);
    }
}
