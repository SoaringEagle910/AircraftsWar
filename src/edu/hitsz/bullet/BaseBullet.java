package edu.hitsz.bullet;

import edu.hitsz.Prop.PropObserver;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author hitsz
 */
public class BaseBullet extends AbstractFlyingObject implements PropObserver {

    private int power = 10;

    public BaseBullet(double locationX, double locationY, double speedX, double speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= -300){
            // 向上飞行出界
            vanish();
        }
    }

    public int getPower() {
        return power;
    }

    @Override
    public void update(int power) {
        return;
    }


}
