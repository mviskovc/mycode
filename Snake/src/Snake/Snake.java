package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JFrame {
    private JPanel panel1;
    private JButton button;
    private JLabel label1;
    private JButton exitToDesktopButton;


    public Snake() {
        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Snake");
        frame.setContentPane((Container) add(panel1));
        frame.setResizable(false);

        frame.setSize(805, 700);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
            }
        });
        exitToDesktopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            frame.dispose();

            }
        });
    }


    public static void main(String[] args) {

        Snake zmija=new Snake();

    }
}
