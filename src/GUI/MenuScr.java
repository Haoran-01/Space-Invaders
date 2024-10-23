package GUI;

import all.SpaceGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MenuScr extends JPanel implements ImageObserver{
    Button[] buttons = new Button[4];
    /**
     * this method is to write str in the centre of the rectangle
     * @param g a Graphics object
     * @param str the sentence that you want to draw
     * @param size the size of the sentence
     * @param r the rectangle
     */
    public void drawTitle(Graphics g, String str, int size, Rectangle r){
        g.setColor(Color.GREEN);
        Font fond = new Font("Arial",Font.BOLD,size);
        FontMetrics metrics = g.getFontMetrics();
        int x = r.x + (r.width - metrics.stringWidth(str)) / 2;
        int y = r.y + ((r.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(fond);
        g.drawString(str,x,y);
    }

    /**
     * draw all components in this class
     * @param g a Graphics object
     */

    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, SpaceGame.GAME_WIDTH,SpaceGame.GAME_LENGTH);

        drawTitle(g,"WELCOME TO PLAY THIS SPACE GAME",50, new Rectangle(0,0,SpaceGame.GAME_WIDTH - 700,200));

        buttons[0] = new Button(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH / 5,"New Game");
        buttons[1] = new Button(SpaceGame.GAME_WIDTH/2 - 100, SpaceGame.GAME_LENGTH * 2/5, "Control");
        buttons[2] = new Button(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 3/5,"High Score list");
        buttons[3] = new Button(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 4/5,"Exit game");

        buttons[0].draw(g);
        buttons[0].draw(g);
        buttons[1].draw(g);
        buttons[2].draw(g);
        buttons[3].draw(g);
    }
}
