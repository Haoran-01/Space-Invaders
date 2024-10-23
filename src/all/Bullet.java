package all;
import java.awt.*;
import java.lang.*;

/**
 * A class of Bullet
 */
public class Bullet implements Hittable {
    private int x;
    private int y;
    private int distance;
    private Shape shape;
    public double radian;
    public static final int BULLET_SPEED = 10;
    public static final int BULLET_WIDTH = 6;
    public static final int BULLET_LENGTH = 10;
    private boolean isAlive;
    private Rectangle hitBox;

    /**
     * Create a Bullet object
     * @param x x-axis coordinates of the point from which the bullet was fired
     * @param y y-axis coordinates of the point from which the bullet was fired
     * @param radian Orientation of the bullets
     */
    public Bullet(int x, int y, int radian){
        this.x = x;
        this.y = y;
        this.radian = radian;
        isAlive = true;
        shape = new Shape(this);
        hitBox = shape.getBulletShape().getBounds();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double getRadian() {
        return radian;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }

    public void destroyIt(){
        isAlive = false;
    }

    /**
     * Bullets travel in a direction and self-destruct beyond a certain distance
     */
    public void move(){
        double theRadian = Math.toRadians(radian);
        x = x + (int) (BULLET_SPEED * Math.cos(theRadian));
        y = y + (int) (BULLET_SPEED * Math.sin(theRadian));
        distance += BULLET_SPEED;
        if (distance >= 500){
            destroyIt();
            distance = 0;
        }
        hitBox = shape.getBulletShape().getBounds();
    }
    @Override
    public boolean isHit(Hittable b) {
        if(hitBox.intersects(b.getHitBox())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
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
        return false;
    }
}
