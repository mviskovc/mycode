package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends JFrame {
    private JPanel panel2;
    private JButton startButton;
    private JButton settingsButton;
    private JButton tutorialButton;
    private JButton exitButton;
    private JButton highscoreButton;


    public Menu() {

        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(panel2);

        frame.setSize(805, 700);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tutorial tutorial=new Tutorial();
                frame.dispose();
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings=new Settings();
                frame.dispose();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("snakeSpeed.txt"));
                    String line = reader.readLine();
                    settings.slider1.setValue(Integer.parseInt(line));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.snake=new Game();
                frame.dispose();

            }
        });
        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Highscore highscore=new Highscore();
                try {
                    highscore.readIntoLines();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

            }
        });

    }

}
