import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JFrame frame;
    private JButton playGameButton;
    private JButton exitGameButton;

    private boolean play;
    private boolean exit;
    private boolean menu;

    public MainMenu(JFrame frame) {
        this.frame = frame;
        frame.dispose();
        play = false;
        exit = false;
        menu = false;
        playGameButton = new JButton();
        playGameButton.addActionListener(this);
        playGameButton.setBounds(100, 100, 100, 50);
        playGameButton.setText("PLAY");
        exitGameButton = new JButton();
        exitGameButton.addActionListener(this);
        exitGameButton.setBounds(520,100,100,50);
        exitGameButton.setText("EXIT");

        frame.setTitle("Welcome to Minesweeper!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(playGameButton);
        frame.add(exitGameButton);
    }

    public boolean isPlay() {
        return play;
    }

    public boolean isExit() {
        return exit;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playGameButton) {
            this.play = true;
        }
        if (e.getSource() == exitGameButton) {
            this.exit = true;
        }
    }
}
