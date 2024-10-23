package all;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * a Player class
 */
public class Player implements Hittable {
    private int x;
    private int y;
    private int life;
    public int radian;
    public int time;
    private int coolDown;
    private boolean alive;
    private boolean invincible;
    private boolean addLife;
    private boolean getShield;
    private boolean getMoreFire;
    public double playerSpeed;
    public double playerSpeedX;
    public double playerSpeedY;
    private Rectangle hitBox;
    private Shape shape;
    private Random random = new Random();
    public static final int RADIUS = 30;

    /**
     * Create a new Player object, which have 3 life and the location is the
     * center of the GameScreen, and be invincible for a period of time
     */
    public Player(){
        x = SpaceGame.GAME_WIDTH/2;
        y = SpaceGame.GAME_LENGTH/2;
        shape = new Shape(this);
        playerSpeedY = 0.0;
        playerSpeedX = 0.0;
        playerSpeed = 0.0;
        life = 3;
        radian = -90;
        hitBox = shape.getPlayerShape().getBounds();
        coolDown = 25;
        time = 100;
        alive = true;
        invincible = false;
        addLife = false;
        getShield = false;
        getMoreFire = false;
    }

    /**
     * The player moves and appears from the other side when the boundary is reached
     */
    public void move(){
        x += playerSpeedX;
        y += playerSpeedY;
        if (x >= SpaceGame.GAME_WIDTH || x <= 0){
            x = SpaceGame.GAME_WIDTH - x;
        }
        if (y >= SpaceGame.GAME_LENGTH || y <= 0) {
            y = SpaceGame.GAME_LENGTH - y;
        }
        invincible = invincible();
        if (invincible){
            hitBox = new Rectangle(0,0,0,0);
        } else {
            hitBox = shape.getPlayerShape().getBounds();
        }
        time--;
        coolDown--;
    }

    /**
     * Enables the ship to leap to a random location and be invincible
     * for a period of time (when the player is yellow)
     */
    public void jump(){
        x = random.nextInt(SpaceGame.GAME_WIDTH);
        y = random.nextInt(SpaceGame.GAME_LENGTH);
        hitBox = shape.getPlayerShape().getBounds();
        time = 100;
    }

    /**
     * The ship fires one round in the current direction
     * @return a new Bullet which the direction is player move direction
     */
    public Bullet fire(){
        Bullet b = null;
        if(coolDown <= 0){
            b = new Bullet(x,y,radian);
            coolDown = 25;
        }
        return b;
    }

    /**
     * The ship fires one round in the current direction,
     * 30 degrees to the left and 30 degrees to the right,
     * when the player get the triple bonus that this method
     * will be used
     * @return an ArrayList of three Bullets object
     */
    public ArrayList<Bullet> moreFire(){
        ArrayList<Bullet> bullets = new ArrayList<>();
        if (coolDown <= 0){
            bullets.add(new Bullet(x,y,radian - 30));
            bullets.add(new Bullet(x,y,radian));
            bullets.add(new Bullet(x,y,radian + 30));
            coolDown = 25;
        }
        return bullets;
    }

    /**
     * Make the player hitBox is 0, to protecting players from harm
     * @return a boolean which indicates whether it is invincible
     */
    public boolean invincible(){
        if (time >= 0){
            invincible = true;
        } else {
            invincible = false;
        }
        return invincible;
    }

    /**
     * When a player reaches 10,000 points and multiples
     * thereof, an extra life is given to the player
     * @param playerScore player score in one game
     */
    public void addLife(int playerScore){
        if (playerScore % 10000 == 0 && playerScore != 0){
            if (!addLife){
                life++;
                addLife = true;
            }
        } else {
            addLife = false;
        }
    }
    public void restPlayer(){
        alive = true;
        x = SpaceGame.GAME_WIDTH/2;
        y = SpaceGame.GAME_LENGTH/2;
        hitBox = new Rectangle(0,0,0,0);
        radian = -90;
        playerSpeedX = 0;
        playerSpeedY = 0;
        time = 100;

    }
    public void getShield(){
        getShield = true;
    }
    public void getGetMoreFire(){
        getMoreFire = true;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLife(){
        return life;
    }
    public int getTime(){
        return time;
    }
    public double getRadian(){
        return radian;
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    public boolean isGetShield(){
        return getShield;
    }
    public boolean isGetMoreFire(){
        return getMoreFire;
    }

    @Override
    public void destroyIt() {
        if (!getShield){
            life--;
            alive = false;
            getMoreFire = false;
        } else {
            getShield = false;
        }
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
        return alive;
    }

    @Override
    public int getPoint() {
        return -1000;
    }

    @Override
    public boolean isPlayer() {
        return true;
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
