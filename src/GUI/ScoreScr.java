package GUI;

import data.ScoreChange;
import all.SpaceGame;
import ucd.comp2011j.engine.Score;
import javax.swing.*;
import java.awt.*;

public class ScoreScr extends JPanel {
    public ScoreChange scoreChange ;
    private Score[] scores;
    public ScoreScr(ScoreChange scoreChange){
        this.scoreChange = scoreChange;
    }
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
        g.drawRect(r.x,r.y,r.width,r.height);
        g.drawString(str,x,y);
    }

    /**
     * Draw out all the components of the ranking screen
     * @param g a Graphics object
     */
    protected void paintComponent(Graphics g){
        scores = scoreChange.getScores();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, SpaceGame.GAME_WIDTH,SpaceGame.GAME_LENGTH);
        drawTitle(g,"Score list",20, new Rectangle(0,0,SpaceGame.GAME_WIDTH,60));
        drawTitle(g,"Exit Game",20,new Rectangle(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 4/5,200, 100));
        drawTitle(g,"Menu",20,new Rectangle(SpaceGame.GAME_WIDTH/2 - 100,SpaceGame.GAME_LENGTH * 4/5 - 100,200, 100));
        for (int i = 9; i >= 0; i--){
            Score score = scores[i];
            g.drawString(score.getName(),SpaceGame.GAME_WIDTH/2 - 150, SpaceGame.GAME_LENGTH * (9 - i)/18 + 100);
            g.drawString(score.getScore() + "",SpaceGame.GAME_WIDTH/2 + 100, SpaceGame.GAME_LENGTH * (9 - i)/18 + 100);
        }

    }
}
