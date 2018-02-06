package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Highscore extends JFrame {
    private JPanel panel5;
    private JButton button1;
    private JTextArea textArea1;
    private JTextArea textArea2;

// dohvaćanje reziltata iz datoteke
    public List<String>readIntoLines() throws IOException {

        String filePath = "HighScore.txt";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String line;
        BufferedReader reader;
        {
            try {
                reader = new BufferedReader(new FileReader("HighScore.txt"));
                int br=0;
            while ((line = reader.readLine()) != null) {
                br++;
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, Integer.valueOf(value));
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }

                List<Map.Entry<String, Integer>> list =
                        new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

                Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return (o2.getValue()).compareTo(o1.getValue());
                    }
                });
                Map<String, Integer> result = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> entry : list) {
                    result.put(entry.getKey(), entry.getValue());
                }

            for (String key : result.keySet()) {

                System.out.println(key + ":" + result.get(key));
                String prije = textArea1.getText();
                String prije2 = textArea2.getText();
                textArea1.setFont(new Font("Chiller", Font.BOLD, 20));
                textArea2.setFont(new Font("Chiller", Font.BOLD, 20));
                textArea1.setText(prije+"\n"+key);
                textArea2.setText(prije2+"\n"+ result.get(key));

            }
            reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Highscore(){

        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Highscore");
        frame.setContentPane(panel5);
        frame.setSize(805, 700);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                frame.dispose();
            }
        });

    }


}
