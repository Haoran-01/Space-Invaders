package GUI;

import all.*;
import javax.swing.*;
import java.awt.*;

/**
 * Draw the game screen
 */
public class GameScr extends JPanel {
    private SpaceGame spaceGame;

    public GameScr(SpaceGame spaceGame){
        this.spaceGame = spaceGame;
    }

    /**
     * Draw the Player
     * @param g a Graphics object
     * @param p a player object
     */
    public static void drawPlayer(Graphics g, Player p){
        int x = p.getX();
        int y = p.getY();
        int radius = Player.RADIUS;
        double radian = p.getRadian();
        double radianA = Math.toRadians(radian);
        double radianB = Math.toRadians(radian + 150);
        double radianC = Math.toRadians(radian + 210);
        Polygon player = new Polygon();
        player.addPoint(x + (int)(radius * Math.cos(radianA)), y + (int)(radius * Math.sin(radianA)));
        player.addPoint(x + (int)(radius * Math.cos(radianB)), y + (int)(radius * Math.sin(radianB)));
        player.addPoint(x + (int)(radius * Math.cos(radianC)), y + (int)(radius * Math.sin(radianC)));
        g.setColor(Color.YELLOW);
        g.drawPolygon(player);
        if (p.getTime() >= 0){
            g.fillPolygon(player);
        } else {
            g.drawString("you can jump now", 10, 100);
        }
        if (p.invincible() == true){
            g.drawString("invincible now", 10 , 80);
        }
        if (p.isGetShield() == true){
            g.drawString("get a shield bonus", 10 , 120);
            int shieldRadius = radius + 15;
            g.drawOval(x - shieldRadius, y - shieldRadius, 2 * shieldRadius, 2 * shieldRadius);
        }
        if (p.isGetMoreFire() == true){
            g.drawString("get a triple bonus", 10 ,140);
        }
    }

    /**
     * Draw all bullets
     * @param g a Graphics object
     * @param b a Bullet object
     */
    public static void drawBullet(Graphics g, Bullet b){
        int x = b.getX();
        int y = b.getY();
        int playerRadius = Player.RADIUS;
        int radius = Bullet.BULLET_WIDTH/2;
        int length = Bullet.BULLET_LENGTH;
        double radian = Math.toRadians(b.getRadian());
        x += (int) (playerRadius * Math.cos(radian));
        y += (int) (playerRadius * Math.sin(radian));
        Polygon bulletShape = new Polygon();
        double x1 = Math.cos(radian + Math.toRadians(90));
        double x2 = Math.cos(radian - Math.toRadians(90));
        double y1 = Math.sin(radian + Math.toRadians(90));
        double y2 = Math.sin(radian - Math.toRadians(90));

        bulletShape.addPoint((int) (x + radius * x1) , (int) (y + radius * y1));
        bulletShape.addPoint((int) (x + radius * x2), (int) (y + radius * y2));
        bulletShape.addPoint((int) (x + radius * x2 + length * Math.cos(radian)), (int) (y + radius * y2 + length * Math.sin(radian)));
        bulletShape.addPoint((int) (x + radius * x1 + length * Math.cos(radian)), (int) (y + radius * y1 + length * Math.sin(radian)));

        g.setColor(Color.GREEN);
        g.fillPolygon(bulletShape);
    }

    /**
     * Draw all meteorites
     * @param g a Graphics object
     * @param m a Meteorites object
     */
    public static void drawMeteorites(Graphics g, Meteorites m){
        int x = m.getX();
        int y = m.getY();
        double radian = m.getRotationRadian();
        int radius = m.getMeteoriteRadius();

        Polygon meteoriteShape = new Polygon();
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian))),(int) (y + radius * Math.sin(Math.toRadians(radian))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 43))),(int) (y + radius * Math.sin(Math.toRadians(radian + 43))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 123))),(int) (y + radius * Math.sin(Math.toRadians(radian + 123))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 216))),(int) (y + radius * Math.sin(Math.toRadians(radian + 216))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 245))),(int) (y + radius * Math.sin(Math.toRadians(radian + 245))));
        g.setColor(Color.GREEN);
        g.drawPolygon(meteoriteShape);
    }

    /**
     * Draw all enemy ship
     * @param g a Graphics object
     * @param e a Enemy object
     */
    public static void drawEnemyShip(Graphics g, Enemy e){
        int x = e.getX();
        int y = e.getY();
        double radian = e.getRotationRadian();
        int radius1 = Enemy.ENEMY_SHIP_RADIUS;
        Polygon enemyShip = new Polygon();
        double radianA = radian + Math.toRadians(15);
        double radianB = radian + Math.toRadians(150);
        double radianC = radian + Math.toRadians(210);
        double radianD = radian + Math.toRadians(345);
        enemyShip.addPoint((int) (x + radius1 * Math.cos(radianA)), (int) (y + radius1 * Math.sin(radianA)));
        enemyShip.addPoint((int) (x + radius1 * Math.cos(radianB)), (int) (y + radius1 * Math.sin(radianB)));
        enemyShip.addPoint((int) (x + radius1 * Math.cos(radianC)), (int) (y + radius1 * Math.sin(radianC)));
        enemyShip.addPoint((int) (x + radius1 * Math.cos(radianD)), (int) (y + radius1 * Math.sin(radianD)));

        g.setColor(Color.RED);
        // show the hitbox
        // g.drawRect(enemyShip.getBounds().x,enemyShip.getBounds().y,enemyShip.getBounds().width,enemyShip.getBounds().height);
        g.drawPolygon(enemyShip);
    }

    /**
     * Draw all bonus items
     * @param g a Graphics object
     * @param b a Bonus object
     */
    public static void drawBonus(Graphics g, Bonus b){
        int x = b.getX();
        int y = b.getY();
        Polygon bonusShape = new Polygon();
        bonusShape.addPoint(x - 10, y - 10);
        bonusShape.addPoint( x + 10, y - 10);
        bonusShape.addPoint(x + 10, y + 10);
        bonusShape.addPoint(x - 10, y + 10);
        if (b.getType() == "Shield"){
            g.setColor(Color.CYAN);
            g.fillPolygon(bonusShape);
            g.setColor(Color.WHITE);
            g.drawString("S",x - 5 ,y + 5);
        } else if (b.getType() == "Triple"){
            g.setColor(Color.YELLOW);
            g.fillPolygon(bonusShape);
            g.setColor(Color.BLACK);
            g.drawString("T",x - 5 ,y + 5);
        }

    }

    /**
     * Draw all the components on the game screen
     * @param g a Graphics object
     */
    protected void paintComponent(Graphics g){
        if(spaceGame != null){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 15);
            g.setFont(font);
            g.fillRect(0,0,spaceGame.GAME_WIDTH,spaceGame.GAME_LENGTH);
            g.setColor(Color.GREEN);
            g.drawString("Level: " + spaceGame.getLevel() ,10,20);
            g.drawString("life: " + spaceGame.getLives(),10,40);
            g.drawString("Score: " + spaceGame.getPlayerScore(),spaceGame.GAME_WIDTH/2,20 );
            drawPlayer(g,spaceGame.getPlayer());
            // show the hit box
            // g.drawRect(spaceGame.getPlayer().getHitBox().x,spaceGame.getPlayer().getHitBox().y,spaceGame.getPlayer().getHitBox().width,spaceGame.getPlayer().getHitBox().height);
            if (spaceGame.isPaused() && !spaceGame.isGameOver()) {
                g.setColor(Color.GREEN);
                g.drawString("Press 'p' to continue ", SpaceGame.GAME_WIDTH/2, SpaceGame.GAME_LENGTH/2);
            }
            for (Bullet bullets : spaceGame.getBullets()){
                drawBullet(g, bullets);
            }
            for (int i = 0; i < spaceGame.getMeteorites().size(); i++){
                Meteorites meteorites = spaceGame.getMeteorites().get(i);
                drawMeteorites(g, meteorites);
                // show the hitbox
                //g.drawRect(meteorites.getHitBox().x,meteorites.getHitBox().y,meteorites.getHitBox().width,meteorites.getHitBox().height);
            }
            for (int i = 0; i < spaceGame.getEnemies().size(); i++){
                Enemy enemy = spaceGame.getEnemies().get(i);
                drawEnemyShip(g,enemy);
            }
            if (spaceGame.getBonuses() != null){
                for (int i = 0; i < spaceGame.getBonuses().size(); i++){
                    Bonus bonus = spaceGame.getBonuses().get(i);
                    drawBonus(g,bonus);
                }
            }
        }
    }
}
