package edu.hitsz.bullet;

import edu.hitsz.Prop.BombValid;
import edu.hitsz.Prop.PropObserver;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet{

    public EnemyBullet(double locationX, double locationY, double speedX, double speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        BombValid.addObserver(this);
    }

    @Override
    public void update(int power) {
        this.vanish();
    }
}
