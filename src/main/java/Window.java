import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener {
    private static JFrame frame;

    private JButton playGameButton;
    private JButton exitGameButton;
    private JPanel panel1;
    private JPanel panel2;
    private Handler handler;

    private int gridSize;


    //    private boolean play;
//    private boolean exit;
//    private boolean menu;
    private static String title;
    private static int width;
    private static int height;
    private Game game;
    private static Grid grid;
//    private static JOptionPane message;
//    private static boolean clicked;

    public Window(int width, int height, int gridSize, String title, Handler handler, Game game, JFrame frame) {
        this.frame = frame;
        this.game = game;
        this.gridSize = gridSize;
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.title = title;

        this.panel1  =new JPanel();
        this.panel2 = new JPanel();

        panel1.setBorder(BorderFactory.createEmptyBorder(70, 20, 20, 20));

        ImageIcon mine = new ImageIcon("src/main/resources/minesweeper-clipart-2.jpeg");
        JLabel label = new JLabel();

        label.setIcon(mine);

        playGameButton = new JButton();
        playGameButton.addActionListener(this);

        playGameButton.setText("PLAY");
        exitGameButton = new JButton();
        exitGameButton.addActionListener(this);

        exitGameButton.setText("EXIT");

        panel1.add(label);
        panel2.setBackground(Color.BLACK);
        panel2.add(playGameButton);
        panel2.add(exitGameButton);

        this.frame.setTitle("Welcome to Minesweeper");
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(panel1, BorderLayout.CENTER);
        this.frame.add(panel2, BorderLayout.SOUTH);

        this.frame.setVisible(true);

    }
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

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void play() {
        this.frame =  new JFrame();

        this.frame.revalidate();
        this.grid = new Grid(new GridLayout(gridSize, gridSize), handler, game);
        this.panel1 = grid;
        frame.setContentPane(panel1);
        this.frame.setTitle(title);
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
    public void resetMenu() {
        this.frame = new JFrame();
        this.panel1  =new JPanel();
        this.panel2 = new JPanel();

        panel1.setBorder(BorderFactory.createEmptyBorder(70, 20, 20, 20));

        ImageIcon mine = new ImageIcon("src/main/resources/minesweeper-clipart-2.jpeg");
        JLabel label = new JLabel();

        label.setIcon(mine);

        playGameButton = new JButton();
        playGameButton.addActionListener(this);

        playGameButton.setText("PLAY");
        exitGameButton = new JButton();
        exitGameButton.addActionListener(this);

        exitGameButton.setText("EXIT");

        panel1.add(label);
        panel2.setBackground(Color.BLACK);
        panel2.add(playGameButton);
        panel2.add(exitGameButton);

        this.frame.setTitle("Welcome to Minesweeper");
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(panel1, BorderLayout.CENTER);
        this.frame.add(panel2, BorderLayout.SOUTH);

        this.frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playGameButton) {
            game.setPlay(true);
        }
        if (e.getSource() == exitGameButton) {
            game.setExit(true);
        }
    }

    public static void update(int flagged) {
        frame.setTitle(title + " | Mines: " + Constants.MINECOUNT + " - Flags: " + flagged);
    }

    //methods for pop up window when game is a win or loss

    public void win(Window window) {
        window.getFrame().setTitle("You Won!!!!");
    }

    public void lose(Window window) {

        window.getFrame().setTitle("Game Over You Lost...");
    }
}
