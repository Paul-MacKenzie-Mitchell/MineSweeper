import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow {
    private static JFrame frame;

    private JButton playGameButton;
    private JButton setDifficultyButton;
    private JButton exitButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private Handler handler;

    private int gridSize;

    private static String title;
    private static int width;
    private static int height;
    private Game game;
    private static Grid grid;

    public MenuWindow(int width, int height, String title, Handler handler, Game game) {
        this.game = game;
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.title = title;
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

    public void initializeBoard() {
        handler.setFlaggedCells(0);
        this.frame =  new JFrame();
//        this.frame.revalidate();
        this.gridSize = GameInfo.getGridsize();
        this.grid = new Grid(new GridLayout(gridSize, gridSize), handler, game);
        this.panel1 = grid;
        frame.setContentPane(panel1);
        this.frame.setTitle(title + " | Difficulty: " + GameInfo.difficulty + " | Mines: " + GameInfo.MINECOUNT + " - Flags: 0" );
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }
    public void initializeMainMenu() {
        this.frame = new JFrame();
        ActionListener mainMenuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == playGameButton) {
                    game.setPlay(true);
                }
                if (e.getSource() == setDifficultyButton) {
                    game.setDifficulty(true);
                }
                if (e.getSource() == exitButton) {
                    game.setExit(true);
                }
            }
        };
        this.panel1  =new JPanel();
        this.panel2 = new JPanel();

        panel1.setBorder(BorderFactory.createEmptyBorder(70, 20, 20, 20));

        ImageIcon mine = new ImageIcon("src/main/resources/minesweeper-clipart-2.jpeg");
        JLabel label = new JLabel();

        label.setIcon(mine);

        playGameButton = new JButton();
        playGameButton.addActionListener(mainMenuActionListener);
        playGameButton.setText("PLAY");

        setDifficultyButton = new JButton();
        setDifficultyButton.addActionListener(mainMenuActionListener);
        setDifficultyButton.setText("DIFFICULTY");

        exitButton = new JButton();
        exitButton.addActionListener(mainMenuActionListener);
        exitButton.setText("EXIT");

        panel1.add(label);
        panel2.setBackground(Color.BLACK);
        panel2.add(playGameButton);
        panel2.add(setDifficultyButton);
        panel2.add(exitButton);

        this.frame.setTitle("Welcome to Minesweeper");
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.add(panel1, BorderLayout.CENTER);
        this.frame.add(panel2, BorderLayout.SOUTH);

        this.frame.setVisible(true);
    }
    public void resetWindow() {
        this.frame.removeAll();
        if (getGrid() != null) {
            getGrid().getCellGrid().clear();
        }
        this.frame.revalidate();
        this.frame.repaint();
    }

    public static void update(int flagged) {
        frame.setTitle(title + " | Mines: " + GameInfo.MINECOUNT + " - Flags: " + flagged);
    }

    public void initializeDifficultyMenu() {
        this.frame = new JFrame();
        JButton easyButton = new JButton();
        JButton moderateButton = new JButton();
        JButton hardButton = new JButton();

        ActionListener difficutyMenuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == easyButton) {
                    GameInfo.setDifficulty("easy");
                    resetWindow();
                    initializeMainMenu();
                }
                if (e.getSource() == moderateButton) {
                    GameInfo.setDifficulty("moderate");
                    resetWindow();
                    initializeMainMenu();
                }
                if (e.getSource() == hardButton) {
                    GameInfo.setDifficulty("hard");
                    resetWindow();
                    initializeMainMenu();
                }
            }
        };

        easyButton.addActionListener(difficutyMenuActionListener);
        easyButton.setText("EASY");


        moderateButton.addActionListener(difficutyMenuActionListener);
        moderateButton.setText("MODERATE");

        hardButton.addActionListener(difficutyMenuActionListener);
        hardButton.setText("HARD");

        this.panel1  =new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(110, 20, 70, 20));
        this.panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(105, 20, 20, 20));
        this.panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createEmptyBorder(70, 20, 110, 20));
        JLabel label1 = new JLabel("Easy");
        JLabel label2 = new JLabel("Moderate");
        JLabel label3 = new JLabel("Hard");
        panel2.add(easyButton);
        panel2.add(moderateButton);
        panel2.add(hardButton);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);
        this.frame.setTitle("Welcome to Minesweeper");
        this.frame.setSize(width,height);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    //methods for pop up window when game is a win or loss

    public void win(MenuWindow window) {
        window.getFrame().setTitle("You Won!!!!");
    }

    public void lose(MenuWindow window) {

        window.getFrame().setTitle("Game Over You Lost...");
    }
}
