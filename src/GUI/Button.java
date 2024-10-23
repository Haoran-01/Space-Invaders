package GUI;

import java.awt.*;

/**
 * this class is created a Button component
 */

public class Button {
    private int x;
    private int y;
    private String sentence;
    private Rectangle rectangle;

    /**
     * Create a Button object (which can draw on the screen)
     * @param x The x coordinate value of the button you want to create
     * @param y The y coordinate value of the button you want to create
     * @param sentence Sentences that you want to write on the button
     */
    public Button(int x, int y,String sentence){
        this.x = x;
        this.y = y;
        this.sentence = sentence;
    }

    /**
     * Draw the button out
     * @param g a Graphics object
     */
    public void draw(Graphics g){
        rectangle = new Rectangle(x,y,200,100);
        g.setColor(Color.WHITE);
        g.fillRect(x,y, rectangle.width,rectangle.height);

        Font font = new Font("Arial",Font.BOLD,25);
        FontMetrics metrics = g.getFontMetrics();
        g.setFont(font);

        int x = rectangle.x + (rectangle.width - metrics.stringWidth(sentence)) / 2;
        int y = rectangle.y + ((rectangle.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setColor(Color.BLACK);
        g.drawString(sentence,x,y);
    }
}
