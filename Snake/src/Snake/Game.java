package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author Mateo Višković , Mikaela Radin-Mačukat
 */


public class Game extends JFrame implements ActionListener,KeyListener{

    public static Game snake;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public int ticks = 0, direction = DOWN, score, tailLength = 10;
    public Point head, cherry;
    public Random random;
    public boolean over = false, paused;
    public Dimension dim;



    public Game() {

        dim = Toolkit.getDefaultToolkit().getScreenSize();
            jframe = new JFrame("Snake");
            jframe.setVisible(true);
            jframe.setSize(805, 700);
            jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
            jframe.setResizable(false);
            jframe.setVisible(true);
            jframe.add(renderPanel= new RenderPanel());

            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.addKeyListener((KeyListener) this);

            startGame();
    }

        public void startGame () {
            over = false;
            paused = false;
            score = 0;
            tailLength = 3;
            direction = DOWN;
            head = new Point(0, -1);
            random = new Random();
            snakeParts.clear();
            cherry = new Point(random.nextInt(79), random.nextInt(66));
            timer.start();
        }

  // upisi rezultat u datoteku

    public void upisi(String line){
        java.util.List<String> lista=new ArrayList<>();
        try {
            FileWriter writer = new FileWriter("HighScore.txt",true) ;
            BufferedWriter bw=new BufferedWriter(writer);
            lista.add(line);
            int lineNumber = 0;
            if (lineNumber++ > 0) {
            }
            bw.write(line);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void gameOver () {
            Object[] options = {"Yes, please",
                    "No, thanks"};
            int n = JOptionPane.showOptionDialog(jframe,
                    "Would you like to start new game?",
                    "Game over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            over = true;
            if (n == 1) {
                Menu menu = new Menu();
                jframe.dispose();

            } else {
                head = new Point(0, -1);
                snakeParts.clear();
                if (n == 0) {
                    startGame();
                }
            }
        System.out.println(score);
        String rez= String.valueOf(score);
        upisi(rez);
    }

  // dohvati brzinu iz datoteke

public Integer speed () {
        Integer brzina=0;
    try {
        BufferedReader reader = new BufferedReader(new FileReader("snakeSpeed.txt"));
        String line = reader.readLine();
         brzina= Integer.parseInt(line);
        reader.close();

        if (brzina==1){
            brzina=10;
        }else if(brzina==2){
            brzina=9;
        }else if(brzina==3){
            brzina=8;
        }else if(brzina==4){
            brzina=7;
        }else if(brzina==5){
            brzina=6;
        }else if(brzina==6){
            brzina=5;
        }else if(brzina==7){
            brzina=4;
        }else if(brzina==8){
            brzina=3;
        }else if(brzina==9){
            brzina=2;
        }else if(brzina==10){
            brzina=1;
        }return brzina;

    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    } catch (IOException e1) {
        e1.printStackTrace();
    }
    return brzina;
}
//dodavanje dijelova na zmiju

    Integer brzina= speed();
    @Override
        public void actionPerformed (ActionEvent arg0){

        renderPanel.repaint();
            ticks++;
            if (ticks % brzina == 0 && head != null && !over && !paused) {
                snakeParts.add(new Point(head.x, head.y));

                if (direction == UP)
                    if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                        head = new Point(head.x, head.y - 1);
                    else {
                        gameOver();
                    }
                if (direction == DOWN)

                    if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
                        head = new Point(head.x, head.y + 1);
                    else {

                        gameOver();
                    }
                if (direction == LEFT)
                    if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                        head = new Point(head.x - 1, head.y);
                    else {
                        gameOver();
                    }
                if (direction == RIGHT)
                    if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y))
                        head = new Point(head.x + 1, head.y);
                    else {
                        gameOver();
                    }
                if (snakeParts.size() > tailLength)
                    snakeParts.remove(0);

                if (cherry != null) {
                    if (head.equals(cherry)) {
                        score += 10;
                        tailLength++;
                        cherry.setLocation(random.nextInt(79), random.nextInt(66));
                    }
                }
            }
        }

        public boolean noTailAt ( int x, int y){
            for (Point point : snakeParts) {
                if (point.equals(new Point(x, y)))
                    return false;
            }
            return true;
        }

        public static void main (String[]args){
            snake=new Game();
        }

        @Override
        public void keyTyped (KeyEvent e){
        }

//upravljanje zmijom

        @Override
        public void keyPressed (KeyEvent e){

            int i = e.getKeyCode();

            if (i == KeyEvent.VK_A && direction != RIGHT)
                direction = LEFT;
            if (i == KeyEvent.VK_D && direction != LEFT)
                direction = RIGHT;
            if (i == KeyEvent.VK_W && direction != DOWN)
                direction = UP;
            if (i == KeyEvent.VK_S && direction != UP)
                direction = DOWN;
            if (i == KeyEvent.VK_SPACE) {
                if (over)
                    startGame();
                else {
                    if (paused = !paused) {
                        Object[] options = {"Yes",
                                "No"};
                        int n = JOptionPane.showOptionDialog(jframe,
                                "Continue?",
                                "Paused",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);
                        if (n == 0) {
                            paused = false;
                        } else if (n == 1)
                            gameOver();

                    }
                }
            }
        }

        @Override
        public void keyReleased (KeyEvent e){
        }
    }