package GUI;

import all.SpaceGame;

import javax.swing.*;
import java.awt.*;

public class AboutScr extends JPanel {
    /**
     * this method is to write str in the centre of the rectangle
     * @param g a Graphics object
     * @param str the sentence that you want to draw
     * @param size the size of the sentence
     * @param r the rectangle
     */
    public void drawTitle(Graphics g,String str, int size,Rectangle r){
        g.setColor(Color.GREEN);
        Font fond = new Font("Arial",Font.BOLD,size);
        FontMetrics metrics = g.getFontMetrics();
        int x = r.x + (r.width - metrics.stringWidth(str)) / 2;
        int y = r.y + ((r.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(fond);
        g.drawRect(r.x,r.y,r.width,r.height);
        g.drawString(str,x,y);
    }

    /**
     * draw all components in this class
     * @param g a Graphics object
     */
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, SpaceGame.GAME_WIDTH,SpaceGame.GAME_LENGTH);
        drawTitle(g,"Control Menu",20, new Rectangle(0,0,SpaceGame.GAME_WIDTH,60));
        drawTitle(g,"Exit Game",20,new Rectangle(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 4/5 ,200, 100));
        drawTitle(g,"Menu",20,new Rectangle(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 4/5 - 100,200, 100));
        g.drawString("Anticlockwise rotation",SpaceGame.GAME_WIDTH/2 - 400,150);
        g.drawString("Clockwise rotation", SpaceGame.GAME_WIDTH/2 - 400,200);
        g.drawString("Speed up", SpaceGame.GAME_WIDTH/2 - 400,250);
        g.drawString("Jump", SpaceGame.GAME_WIDTH/2 - 400,300);
        g.drawString("Stop game",SpaceGame.GAME_WIDTH/2 - 400,350);
        g.drawString("fire", SpaceGame.GAME_WIDTH/2 - 400, 400);
        g.drawString("left arrow ",SpaceGame.GAME_WIDTH/2 + 300,150);
        g.drawString("right arrow", SpaceGame.GAME_WIDTH/2 + 300,200);
        g.drawString("on arrow", SpaceGame.GAME_WIDTH/2 + 300,250);
        g.drawString("j", SpaceGame.GAME_WIDTH/2 + 300,300);
        g.drawString("p",SpaceGame.GAME_WIDTH/2 + 300,350);
        g.drawString("space", SpaceGame.GAME_WIDTH/2 + 300, 400);
    }
}
