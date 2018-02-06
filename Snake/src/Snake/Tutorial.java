package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends JFrame {
    private JButton goBackButton;
    private JPanel panel3;

    public Tutorial() {

        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Tutorial");
        frame.setContentPane(panel3);
        frame.setSize(805, 700);
        frame.setResizable(false);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {

    }
}
