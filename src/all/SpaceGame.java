package all;

import GUI.PlayerListener;
import ucd.comp2011j.engine.Game;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the Game class
 */
public class SpaceGame implements Game {
    private Player player;
    private int playerScore;
    private int playerLives;
    private int nowLevel;
    private int time;
    private ArrayList<Levels> levels;
    private boolean paused = true;
    private final PlayerListener playerListener;
    public static final int GAME_WIDTH = 1400;
    public static final int GAME_LENGTH = 1000;
    public static final Rectangle GAME_BOUND = new Rectangle(0, 0, GAME_WIDTH, GAME_LENGTH);
    private Random random = new Random();
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Meteorites> meteorites;
    private ArrayList<Enemy> enemies;
    private ArrayList<Hittable> entity;
    private ArrayList<Bonus> bonuses = new ArrayList<>();


    public SpaceGame(PlayerListener playerListener){
        this.playerListener = playerListener;
    }

    /**
     * Check and let the player move through the PlayerListener, also check if Player
     * want to jump and if the player get triple bonus that can shut three bullets
     */
    public void movePlayer(){
        if (playerListener.isPressedLeft()){
            player.radian -= 5;
        }
        if (playerListener.isPressedRight()){
            player.radian += 5;
        }
        if (playerListener.isPressedUp()){
            player.playerSpeed = 0.1;
            player.playerSpeedX += player.playerSpeed * Math.cos(Math.toRadians(player.radian));
            player.playerSpeedY += player.playerSpeed * Math.sin(Math.toRadians(player.radian));
        }
        if (playerListener.isPressedFire()){
            if (player.isGetMoreFire()){
                List<Bullet> bullets = player.moreFire();
                if (bullets.size() != 0){
                    playerBullets.addAll(bullets);
                    playerListener.restFire();
                }
            } else {
                Bullet b = player.fire();
                if (b != null){
                    playerBullets.add(b);
                    playerListener.restFire();
                }
            }
        }
        if (checkForJump() == true){
            player.jump();
        }
        player.move();
    }

    /**
     * Move player bullets, stores bullets fired by the player and removes those that need to be destroyed.
     * Detects if a bullet has hit a Hittable object
     */
    public void setPlayerBullets(){
        List<Bullet> remove = new ArrayList<>();
        for (int i = 0; i < playerBullets.size(); i++){
            Bullet b = playerBullets.get(i);
            if (b.isAlive() && b.getHitBox().intersects(GAME_BOUND)){
                b.move();
                for (Hittable e : entity){
                    if (e != b){
                        if (e.isPlayer() == false){
                            if (e.isHit(b)){
                                playerScore += e.getPoint();
                                e.destroyIt();
                                b.destroyIt();
                            }
                        }
                    }
                }
            } else {
                remove.add(b);
            }
        }
        playerBullets.removeAll(remove);
    }

    /**
     * Move enemy bullets, stores bullets fired by the enemy and removes those that need to be destroyed.
     * Detects if a bullet has hit a Hittable object
     */
    public void setEnemyBullets(){
        List<Bullet> remove = new ArrayList<>();
        for (int i = 0; i < enemyBullets.size(); i++){
            Bullet b = enemyBullets.get(i);
            if (b.isAlive() && b.getHitBox().intersects(GAME_BOUND)){
                b.move();
                for (Hittable e : entity){
                    if (e != b){
                        if (e.isEnemy() == false){
                            if (e.isHit(b)){
                                e.destroyIt();
                                b.destroyIt();
                            }
                        }
                    }
                }
            } else {
                remove.add(b);
            }
        }
        enemyBullets.removeAll(remove);
    }

    /**
     * Move meteorites, store all meteorites and remove damaged meteorites,
     * add split meteorites and bonus items
     */
    public void setMeteorites(){
        List<Meteorites> remove = new ArrayList<>();
        for (int i = 0; i < meteorites.size(); i++){
            Meteorites m = meteorites.get(i);
            for (Hittable entity : entity){
                if (entity.isMeteorites() == false){
                    if (entity.isHit(m)){
                        entity.destroyIt();
                        m.destroyIt();
                    }
                }
            }
            if (m.getSpilt()){
                levels.get(nowLevel).addSpiltMeteorite();
            }
            if (m.isAlive() && m.getHitBox().intersects(GAME_BOUND)){
                m.move();
            } else {
                levels.get(nowLevel).addBonus();
                remove.add(m);
            }
        }
        meteorites.removeAll(remove);
    }

    /**
     * Move enemy aircraft, store all enemy aircraft and remove destroyed enemy
     * aircraft, and add bonus items for destroying enemy aircraft
     */
    public void setEnemyShip(){
        if (time == 0){
            levels.get(nowLevel).addEnemy();
        }
        List<Enemy> remove = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            if (enemy.isAlive() && enemy.getHitBox().intersects(GAME_BOUND)){
                Bullet b = enemy.fire();
                for (Hittable entity : entity){
                    if (entity.isEnemy() == false){
                        if (entity.isHit(enemy)){
                            enemy.destroyIt();
                            entity.destroyIt();
                        }
                    }
                }
                if (enemy.getCoolDown() == 0 && b != null){
                    enemyBullets.add(b);
                    enemy.restFire();
                }
                enemy.avoidToCrash(meteorites);
                enemy.move();
                enemy.aimPlayer(player);
            } else {
                levels.get(nowLevel).addBonus();
                remove.add(enemy);
            }
        }
        enemies.removeAll(remove);
    }

    /**
     * Move bonus objects, store all bonus objects and remove bonus objects that the
     * player has received, giving the player the appropriate ability depending on the
     * type of bonus object received
     */
    public void setBonuses(){
        List<Bonus> remove = new ArrayList<>();
        for (int i = 0; i < bonuses.size(); i++){
            Bonus bonus = bonuses.get(i);
            if (bonus.isAlive() && bonus.getHitBox().intersects(GAME_BOUND)){
                for (Hittable hittable : entity){
                    if (bonus.isHit(hittable)){
                        bonus.destroyIt();
                        if (bonus.getType() == "Shield"){
                            player.getShield();
                        }
                        if (bonus.getType() == "Triple"){
                            player.getGetMoreFire();
                        }
                    }
                }
                bonus.move();
            } else {
                remove.add(bonus);
            }
        }
        bonuses.removeAll(remove);
    }
    public ArrayList<Bullet> getBullets(){
        ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
        allBullets.addAll(playerBullets);
        allBullets.addAll(enemyBullets);
        return allBullets;
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public List<Meteorites> getMeteorites(){
        return meteorites;
    }
    public List<Bonus> getBonuses(){
        return bonuses;
    }
    public int getLevel(){
        return nowLevel;
    }

    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    @Override
    public int getLives() {
        return playerLives;
    }

    @Override
    public void updateGame() {
        if (!isPaused()){
            entity.clear();
            entity.add(player);
            entity.addAll(levels.get(nowLevel).getHittable());
            playerLives = player.getLife();
            playerScore = getPlayerScore();
            player.addLife(playerScore);

            setMeteorites();
            setEnemyShip();
            setBonuses();
            setEnemyBullets();
            setPlayerBullets();
            movePlayer();
            resetDestroyedPlayer();
            time--;
        }
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void checkForPause() {
        if (playerListener.isPressedPause()){
            paused = !paused;
            playerListener.restPaused();
        }
    }

    public boolean checkForJump() {
        if (playerListener.isPressedJump() && player.time <= 0){
            player.jump();
            playerListener.restJump();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startNewGame() {
        paused = true;
        entity = new ArrayList<>();
        playerLives = 3;
        playerScore = 0;
        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        player = new Player();
        levels = new ArrayList<>();
        levels.add(new Levels(nowLevel + 4));
        meteorites = levels.get(nowLevel).getMeteorites();
        enemies = levels.get(nowLevel).getEnemies();
        bonuses = levels.get(nowLevel).getBonuses();
        time = random.nextInt(800);
    }

    @Override
    public boolean isLevelFinished() {
        if (meteorites.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getTargetFPS() {
        return 0;
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public void resetDestroyedPlayer() {
        if (isPlayerAlive() == false && playerLives > 0){
            paused = true;
            player.restPlayer();
            playerBullets = new ArrayList<>();
            enemyBullets = new ArrayList<>();
        }
    }

    @Override
    public void moveToNextLevel() {
        nowLevel++;
        levels.add(new Levels(nowLevel + 4));
        paused = true;
        player.restPlayer();
        entity = new ArrayList<Hittable>();
        playerBullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<Bullet>();
        meteorites = levels.get(nowLevel).getMeteorites();
        enemies = levels.get(nowLevel).getEnemies();
        bonuses = levels.get(nowLevel).getBonuses();
        time = random.nextInt(800);
    }

    @Override
    public boolean isGameOver() {
        if (playerLives <= 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getScreenWidth() {
        return GAME_WIDTH;
    }

    @Override
    public int getScreenHeight() {
        return GAME_LENGTH;
    }

    public Player getPlayer(){
        return player;
    }
}
