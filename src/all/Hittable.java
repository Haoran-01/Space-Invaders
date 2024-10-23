package all;

import java.awt.*;

/**
 * Interface for all objects that can crash
 */
public interface Hittable {
    public boolean isHit(Hittable b);
    public boolean isAlive();
    public int getPoint();
    public boolean isPlayer();
    public boolean isEnemy();
    public boolean isMeteorites();
    public boolean isBonus();
    public Rectangle getHitBox();
    public void destroyIt();
}
