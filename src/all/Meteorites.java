package all;
import java.awt.*;
import java.util.Random;

/**
 * A class of meteorites
 */
public class Meteorites implements Hittable {
    private int x;
    private int y;
    public int level;
    private int speedRadian;
    private int rotationRadian;
    private double meteoritesSpeed;
    private boolean alive;
    private boolean spilt;
    private boolean createBonus;
    private Shape shape;
    private Rectangle hitBox;
    private Random random = new Random();
    public static final double METEORITE_SPEED = 1 + Math.random();
    public static final int METEORITE_RADIUS = 20;

    /**
     * The first constructor creates the first meteorite that appears,
     * with a random speed, direction
     * @param level the type of the meteorites. 1 - small, 2 - middle, 3 - large
     */
    public Meteorites(int level){
        x = random.nextInt(SpaceGame.GAME_WIDTH);
        y = random.nextInt(SpaceGame.GAME_LENGTH);
        speedRadian = random.nextInt(360);
        rotationRadian = speedRadian;
        this.level = level;
        shape = new Shape(this);
        hitBox = shape.getMeteoriteShape().getBounds();
        alive = true;
        spilt = false;
        createBonus = false;
        meteoritesSpeed = getMeteoriteSpeed();
    }

    /**
     * The second constructor is used to create the split meteorite
     * @param x Value of the x-coordinate of the split meteorite point
     * @param y Value of the y-coordinate of the split meteorite point
     * @param radian Direction of movement of split meteorites
     * @param level Type of split meteorite
     */
    public Meteorites (int x, int y, int radian, int level){
        this.x = x;
        this.y = y;
        this.speedRadian = radian;
        this.level = level;
        shape = new Shape(this);
        hitBox = shape.getMeteoriteShape().getBounds();
        meteoritesSpeed = getMeteoriteSpeed();
        alive = true;
        spilt = false;
        createBonus = false;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double getMeteoriteSpeed(){
        if (level == 3){
            return METEORITE_SPEED;
        } else if (level == 2){
            return METEORITE_SPEED * (Math.random() + 1);
        } else {
            return METEORITE_SPEED * (Math.random() + 1) * (Math.random() + 1);
        }
    }
    public int getMeteoriteRadius(){
        if (level == 3){
            return METEORITE_RADIUS * 3;
        } else if (level == 2){
            return METEORITE_RADIUS * 2;
        } else {
            return METEORITE_RADIUS;
        }
    }
    public int getLevel(){
        return level;
    }
    public int getRotationRadian(){
        return rotationRadian;
    }
    public int getSpeedRadian(){return speedRadian;}
    public boolean getSpilt(){
        return spilt;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }

    /**
     * The meteorite moves in a direction and will appear
     * from the other side when it reaches the boundary
     */
    public void move(){
        x += meteoritesSpeed * Math.cos(Math.toRadians(speedRadian));
        y += meteoritesSpeed * Math.sin(Math.toRadians(speedRadian));
        rotationRadian += 1;
        if (x >= SpaceGame.GAME_WIDTH || x <= 0){
            x = SpaceGame.GAME_WIDTH - x;
        }
        if (y >= SpaceGame.GAME_LENGTH || y <= 0) {
            y = SpaceGame.GAME_LENGTH - y;
        }
        hitBox = shape.getMeteoriteShape().getBounds();
    }
    public void destroyIt(){
        level--;
        createBonus = true;
        if (level == 2){
            spilt = true;
        } else if (level == 1){
            spilt = true;
        } else {
            spilt = false;
        }
        alive = false;
    }

    /**
     * Create a new Bonus, which can provide a shield for player
     * @return a Bonus object, type is shield
     */
    public Bonus createBonus(){
        if (createBonus == true){
            Bonus bonus = new Bonus(x, y, speedRadian,"Triple");
            createBonus = false;
            return bonus;
        } else {
            return null;
        }
    }

    @Override
    public boolean isHit(Hittable b) {
        if (hitBox.intersects(b.getHitBox())){
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
        if (level == 3){
            return 25;
        } else if (level == 2) {
            return 50;
        } else {
            return 100;
        }
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
        return true;
    }

    @Override
    public boolean isBonus() {
        return false;
    }
}
