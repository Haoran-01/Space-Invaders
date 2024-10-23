package all;
import java.awt.*;

/**
 * this class contain all Shape in this game
 */
public class Shape {
    private Player player;
    private Meteorites meteorite;
    private Bullet bullet;
    private Enemy enemy;
    private Bonus bonus;

    public Shape(Bullet bullet){
        this.bullet = bullet;
    }
    public Shape(Player player){
        this.player = player;
    }
    public Shape(Meteorites meteorite){
        this.meteorite = meteorite;
    }
    public Shape(Enemy enemy){
        this.enemy = enemy;
    }
    public Shape(Bonus bonus) {this.bonus = bonus;}

    public Polygon getPlayerShape(){
        int radian = player.radian;
        int x = player.getX();
        int y = player.getY();
        double radianA = Math.toRadians(radian);
        double radianB = Math.toRadians(radian + 150);
        double radianC = Math.toRadians(radian + 210);
        Polygon playerShape = new Polygon();
        playerShape.addPoint(x + (int)(Player.RADIUS * Math.cos(radianA)), y + (int)(Player.RADIUS * Math.sin(radianA)));
        playerShape.addPoint(x + (int)(Player.RADIUS * Math.cos(radianB)), y + (int)(Player.RADIUS * Math.sin(radianB)));
        playerShape.addPoint(x + (int)(Player.RADIUS * Math.cos(radianC)), y + (int)(Player.RADIUS * Math.sin(radianC)));
        if (player.isGetShield() == true){
            int shieldRadius = Player.RADIUS + 15;
            playerShape = new Polygon();
            playerShape.addPoint(x - shieldRadius, y - shieldRadius);
            playerShape.addPoint(x + shieldRadius, y - shieldRadius);
            playerShape.addPoint(x + shieldRadius, y + shieldRadius);
            playerShape.addPoint(x - shieldRadius, y + shieldRadius);
        }
        return playerShape;
    }
    public Polygon getMeteoriteShape(){
        int radius = meteorite.getMeteoriteRadius();
        int x = meteorite.getX();
        int y = meteorite.getY();
        double radian = meteorite.getRotationRadian();
        Polygon meteoriteShape = new Polygon();
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian))),(int) (y + radius * Math.sin(Math.toRadians(radian))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 43))),(int) (y + radius * Math.sin(Math.toRadians(radian + 43))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 123))),(int) (y + radius * Math.sin(Math.toRadians(radian + 123))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 216))),(int) (y + radius * Math.sin(Math.toRadians(radian + 216))));
        meteoriteShape.addPoint((int) (x + radius * Math.cos(Math.toRadians(radian + 245))),(int) (y + radius * Math.sin(Math.toRadians(radian + 245))));

        return meteoriteShape;
    }

    public Polygon getBulletShape(){
        int x = bullet.getX();
        int y = bullet.getY();
        int playerRadius = Player.RADIUS;
        int radius = Bullet.BULLET_WIDTH/2;
        int length = Bullet.BULLET_LENGTH;
        double radian = Math.toRadians(bullet.getRadian());
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

        return bulletShape;
    }

    public Polygon getEnemyShipShape(){
        int x = enemy.getX();
        int y = enemy.getY();
        double radian = enemy.getRotationRadian();
        int radius = Enemy.ENEMY_SHIP_RADIUS;
        Polygon enemyShip = new Polygon();
        double radianA = radian + Math.toRadians(15);
        double radianB = radian + Math.toRadians(150);
        double radianC = radian + Math.toRadians(210);
        double radianD = radian + Math.toRadians(345);
        enemyShip.addPoint((int) (x + radius * Math.cos(radianA)), (int) (y + radius * Math.sin(radianA)));
        enemyShip.addPoint((int) (x + radius * Math.cos(radianB)), (int) (y + radius * Math.sin(radianB)));
        enemyShip.addPoint((int) (x + radius * Math.cos(radianC)), (int) (y + radius * Math.sin(radianC)));
        enemyShip.addPoint((int) (x + radius * Math.cos(radianD)), (int) (y + radius * Math.sin(radianD)));

        return enemyShip;
    }

    public Polygon getBonusShape(){
        int x = bonus.getX();
        int y = bonus.getY();

        Polygon bonusShape = new Polygon();
        bonusShape.addPoint(x - 10, y - 10);
        bonusShape.addPoint( x + 10, y - 10);
        bonusShape.addPoint(x + 10, y + 10);
        bonusShape.addPoint(x - 10, y + 10);

        return bonusShape;
    }
}
