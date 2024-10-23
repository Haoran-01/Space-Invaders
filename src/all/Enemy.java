package all;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class of enemy in the game
 */
public class Enemy implements Hittable{
    private int x;
    private int y;
    private int coolDown;
    private double speedRadian;
    private double rotationRadian;
    private boolean alive;
    private boolean createBonus;
    public static final int ENEMY_SHIP_SPEED = 3;
    public static final int ENEMY_SHIP_RADIUS = 30;
    private Rectangle hitBox;
    private Shape shape;
    private Random random = new Random();

    /**
     * Create an Enemy ship object
     * The angle of advance and coordinates of enemy planes are randomized,
     * and the Level class ensures that enemy planes are generated on collision course with meteorites,Enemy planes can fire bullets and have a coolDown
     */
    public Enemy(){
        shape = new Shape(this);
        x = random.nextInt(SpaceGame.GAME_WIDTH);
        y = random.nextInt(SpaceGame.GAME_LENGTH);
        speedRadian = random.nextInt(360);
        rotationRadian = speedRadian;
        coolDown = 150;
        alive = true;
        createBonus = false;
        hitBox = shape.getEnemyShipShape().getBounds();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getCoolDown(){
        return coolDown;
    }
    public double getRotationRadian(){
        return rotationRadian;
    }

    /**
     * Control the movement of enemy planes, if they reach the border they will appear from the other side
     */
    public void move(){
        x += ENEMY_SHIP_SPEED * Math.cos(Math.toRadians(speedRadian));
        y += ENEMY_SHIP_SPEED * Math.sin(Math.toRadians(speedRadian));

        if (x >= SpaceGame.GAME_WIDTH || x <= 0){
            x = SpaceGame.GAME_WIDTH - x;
        }
        if (y >= SpaceGame.GAME_LENGTH || y <= 0) {
            y = SpaceGame.GAME_LENGTH - y;
        }
        hitBox = shape.getEnemyShipShape().getBounds();
        coolDown--;
    }
    public Bullet fire(){
        Bullet b = null;
        if (coolDown == 0){
            int angle = (int) ((rotationRadian * 180)/Math.PI);
            b = new Bullet(x,y, angle);
        }
        return b;
    }

    /**
     * This method is used to create a new bonus when an enemy aircraft is destroyed
     * @return a new Bonus object which can provide a shield for player
     */
    public Bonus createBonus(){
        if (createBonus == true){
            Bonus bonus = new Bonus(x, y, speedRadian,"Shield");
            createBonus = false;
            return bonus;
        } else {
            return null;
        }
    }
    public void restFire(){
        coolDown = 150;
    }

    /**
     * This method is used to help enemy planes target the player,
     * using mathematical methods to calculate the angle between two
     * points and rotate the enemy plane to target the player
     * @param player the player in the game
     */
    public void aimPlayer(Player player){
        int distanceX = player.getX() - x;
        int distanceY = player.getY() - y;
        if (distanceX == 0){
            if (player.getY() - y > 0 ){
                rotationRadian = Math.toRadians(90);
            } else {
                rotationRadian = Math.toRadians(-90);
            }
        } else if (distanceY == 0){
            if (player.getX() - x > 0){
                rotationRadian = Math.toRadians(0);
            } else {
                rotationRadian = Math.toRadians(180);
            }
        } else {
            rotationRadian = Math.atan2(distanceY,distanceX);
        }
        hitBox = shape.getEnemyShipShape().getBounds();
    }

    /**
     *This method is used to help enemy planes avoid meteorites by calculating the outer circle of the
     * hitBox to determine if the enemy plane is close to the meteorite, and adjusting the angle of movement if it is.
     * Used part of aimPlyaer's code to change the angle of advance
     * @param meteorites All meteorites in the game
     */
    public void avoidToCrash(ArrayList<Meteorites> meteorites){
        for (int i = 0; i < meteorites.size(); i++){
            Meteorites meteorite = meteorites.get(i);
            double enemyRadius = ENEMY_SHIP_RADIUS * 2;
            double meteoriteRadius = meteorites.get(i).getMeteoriteRadius() * 2;
            int distanceX = x - meteorite.getX();
            int distanceY = y - meteorite.getY();
            hitBox = shape.getEnemyShipShape().getBounds();
            double distance =  Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
            if (distance <= (enemyRadius + meteoriteRadius)){
                if (distanceX == 0){
                    if (meteorite.getY() - y >= 0 ){
                        speedRadian = -90;
                    } else {
                        speedRadian = 90;
                    }
                } else if (distanceY == 0){
                    if (meteorite.getX() - x >= 0){
                        speedRadian = 180;
                    } else {
                        speedRadian = 0;
                    }
                } else {
                    speedRadian = Math.atan2(distanceY,distanceX) * 180 / Math.PI;
                }

            }
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
        return 200;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public boolean isMeteorites() {
        return false;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void destroyIt() {
        createBonus = true;
        alive = false;
    }

}
