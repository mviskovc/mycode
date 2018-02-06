package Snake;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Settings extends JFrame {
    public JSlider slider1;
    private JPanel panel4;

    public JTextField textField;
    private JButton okButton;
    private JButton goBackButton;

//zapisivanje nicknameau datoteku
    public void writeLines(String line){
        List<String> ret=new ArrayList<>();
        try {
                FileWriter writer = new FileWriter("HighScore.txt",true) ;
                BufferedWriter bw=new BufferedWriter(writer);
                ret.add(line);
            int lineNumber = 0;
            if (lineNumber++ > 0) {
                bw.newLine();
             }
            bw.write(line);
             bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Settings() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Settings");
        frame.setContentPane(panel4);
        frame.setResizable(false);

        frame.setSize(805, 700);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
//                menu.setVisible(true);
                frame.dispose();
            }
        });

//postavljanje brzine i zapisivanje u datoteku

        slider1.addChangeListener(new ChangeListener( ) {
            @Override
            public void stateChanged(ChangeEvent e) {

                int brzina = slider1.getValue();
                String speed=String.valueOf(brzina);


                if(slider1.getValueIsAdjusting()==true) {
                    List<String> sp=new ArrayList<>();
                    try {
                        FileWriter writer = new FileWriter("snakeSpeed.txt") ;
                        BufferedWriter bw=new BufferedWriter(writer);
                        sp.add(speed);
                        int lineNumber = 0;
                        if (lineNumber++ > 0) {
                            bw.newLine();
                        }
                        bw.write(speed);
                        bw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick=textField.getText();
                writeLines(nick+",");
            }
        });

    }}
