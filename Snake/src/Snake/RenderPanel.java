package Snake;

import java.awt.*;
import javax.swing.*;


public class RenderPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(0,0,800,700);
            Game zmija = Game.snake;
            g.setColor(Color.GREEN);

            for(Point point:zmija.snakeParts){
                g.fillRect(point.x*Game.SCALE,point.y*Game.SCALE, Game.SCALE, Game.SCALE);
            }
            g.fillRect(zmija.head.x*Game.SCALE,zmija.head.y*Game.SCALE,Game.SCALE,Game.SCALE);
            g.setColor(Color.RED);
            g.fillRect(zmija.cherry.x*Game.SCALE,zmija.cherry.y*Game.SCALE,Game.SCALE,Game.SCALE);

            String string="Score: "+zmija.score+", Length:"+zmija.tailLength+", Time:"+zmija.ticks/40;
            g.setFont(new Font("Chiller", Font.BOLD, 14));

            g.setColor(Color.white);
            g.drawString(string, (int) (getWidth()/2-string.length()*2),10);
}
}