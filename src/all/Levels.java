package all;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * a Level class, includes all meteorites, enemy planes and bonus items in the level
 */
public class Levels {
    private int numberOfMeteorites;
    private int numberOfEnemy;
    private int radian1;
    private int radian2;
    private Meteorites meteorites1;
    private Meteorites meteorites2;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Meteorites> meteorites = new ArrayList<>();
    private ArrayList<Bonus> bonuses = new ArrayList<>();
    public Random random = new Random();
    private Rectangle safePlace = new Rectangle(SpaceGame.GAME_WIDTH/2-50,SpaceGame.GAME_LENGTH/2-50,100,100);

    /**
     * Create a new Levels object
     * @param numberOfMeteorites the number of meteorites in this level that you want
     */
    public Levels(int numberOfMeteorites){
        this.numberOfMeteorites = numberOfMeteorites;
        this.numberOfEnemy = 1;
        addMeteorite();
        addBonus();
    }

    /**
     * Add an enemy plane and make sure it doesn't appear on the meteorite
     */
    public void addEnemy(){
        Enemy enemy = new Enemy();
        int number = 0;
        if (enemies.size() < numberOfEnemy){
            for (int i = 0; i < meteorites.size(); i++){
                if (!enemy.getHitBox().intersects(meteorites.get(i).getHitBox())){
                    number++;
                }
            }
            if (number == numberOfMeteorites){
                enemies.add(enemy);
            }
        }
    }

    /**
     *Added bonus items, 10% chance of Triple bonus when meteorite
     * is destroyed, shield bonus when enemy plane is destroyed
     */
    public void addBonus(){
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            Bonus bonus = enemy.createBonus();
            if (bonus != null){
                bonuses.add(bonus);
            }
        }
        for (int i = 0; i < meteorites.size(); i++){
            int create = random.nextInt(100);
            Meteorites meteorite = meteorites.get(i);
            Bonus bonus = meteorite.createBonus();
            if (bonus != null && create > 90){
                bonuses.add(bonus);
            }
        }
    }

    /**
     * Add the number of enemy planes entered the constructor,
     * and make sure it doesn't appear near the player's birth point
     */
    public void addMeteorite(){
        for (int i = 0; i < numberOfMeteorites; i++){
            Meteorites meteorite = new Meteorites(3);
            while (meteorite.getHitBox().intersects(safePlace)){
                meteorite = new Meteorites(3);
            }
            meteorites.add(meteorite);
        }
    }

    /**
     * Add split meteorites, when a meteorite is destroyed it will
     * detect if it needs to be split, and meet the requirements of the project
     */
    public void addSpiltMeteorite(){
        for (int i = 0; i < meteorites.size(); i++){
            Meteorites meteorite = meteorites.get(i);
            if (meteorite.getSpilt()){
                if (meteorite.getLevel() == 2){
                    radian1 = random.nextInt(meteorite.getSpeedRadian() + 30 - (meteorite.getSpeedRadian()+ 10) + 1)  + meteorite.getSpeedRadian() + 10;
                    radian2 = random.nextInt(meteorite.getSpeedRadian() - 10 - (meteorite.getSpeedRadian() - 30) + 1) + meteorite.getSpeedRadian() -30;
                    meteorites1 = new Meteorites(meteorite.getX(),meteorite.getY(),radian1,2);
                    meteorites2 = new Meteorites(meteorite.getX(),meteorite.getY(),radian2,2);
                    meteorites.add(meteorites1);
                    meteorites.add(meteorites2);
                }
                if (meteorite.getLevel() == 1){
                    radian1 = random.nextInt(meteorite.getSpeedRadian()+ 40 - (meteorite.getSpeedRadian()+ 20) + 1)  + meteorite.getSpeedRadian() + 20;
                    radian2 = random.nextInt(meteorite.getSpeedRadian() - 20 - (meteorite.getSpeedRadian() - 40) + 1) + meteorite.getSpeedRadian() -40;
                    meteorites1 = new Meteorites(meteorite.getX(),meteorite.getY(),radian1,1);
                    meteorites2 = new Meteorites(meteorite.getX(),meteorite.getY(),radian2,1);
                    meteorites.add(meteorites1);
                    meteorites.add(meteorites2);
                }
            }
        }
    }

    public ArrayList<Meteorites> getMeteorites(){
        return meteorites;
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public ArrayList<Bonus> getBonuses() {return bonuses;}

    /**
     * get all Hittable object in this level
     * @return a list of Hittable object in this level
     */
    public List<Hittable> getHittable(){
        List<Hittable> entity = new ArrayList<>();
        entity.addAll(meteorites);
        entity.addAll(enemies);
        entity.addAll(bonuses);
        return entity;
    }
}
