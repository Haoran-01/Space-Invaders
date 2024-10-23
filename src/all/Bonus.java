package all;

import java.awt.*;

/**
 * Classes of in-game bonus items
 */
public class Bonus implements Hittable{
    private int x;
    private int y;
    private double radian;
    private Shape shape;
    private Rectangle hitBox;
    private boolean alive;
    private String type;
    private static final int BONUS_SPEED = 2;

    /**
     * Create a Bonus object
     * @param x Value of the x-coordinate in which the award appears
     * @param y Value of the y-coordinate in which the award appears
     * @param radian A bonus campaign perspective
     * @param type the string type is represents what the award is.
     *             shield - will give player a shield which can defend against an attack.
     *             triple - Allows the player to fire bullets 30 degrees to the left,
     *             right of centre and 30 degrees to the right at the same time
     */
    public Bonus(int x, int y, double radian,String type){
        this.type = type;
        this.x = x;
        this.y = y;
        this.radian = radian;
        alive = true;
        shape = new Shape(this);
        hitBox = shape.getBonusShape().getBounds();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getType(){
        return type;
    }

    /**
     *Bonus movement along your own direction, if the boundary is reached then come out the other half
     */
    public void move(){
        x += BONUS_SPEED * Math.cos(Math.toRadians(radian));
        y += BONUS_SPEED * Math.sin(Math.toRadians(radian));

        if (x >= SpaceGame.GAME_WIDTH || x <= 0){
            x = SpaceGame.GAME_WIDTH - x;
        }
        if (y >= SpaceGame.GAME_LENGTH || y <= 0) {
            y = SpaceGame.GAME_LENGTH - y;
        }

        hitBox = shape.getBonusShape().getBounds();
    }

    @Override
    public boolean isHit(Hittable b) {
        if (hitBox.intersects(b.getHitBox()) && b.isPlayer()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getPoint() {
        return 0;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    @Override
    public boolean isMeteorites() {
        return false;
    }

    @Override
    public boolean isBonus() {
        return true;
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void destroyIt() {
        alive = false;
    }
}
