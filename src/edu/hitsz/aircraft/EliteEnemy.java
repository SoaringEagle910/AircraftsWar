package edu.hitsz.aircraft;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.BloodProp;
import edu.hitsz.Prop.BombProp;
import edu.hitsz.Prop.BulletProp;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.propfactory.BloodPropCreator;
import edu.hitsz.propfactory.BombPropCreator;
import edu.hitsz.propfactory.BulletPropCreator;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemyAircraft {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    private int propSpeedX=0,propSpeedY=5;


    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    public BaseProp dropProp()
    {
        BaseProp prop;
        double x=Math.random();
        if(x<0.3)
        {
            //System.out.println("无道具掉落");
            return null;
        }
        else if(x<0.6)
        {
            //System.out.println("blood道具掉落");
            prop= new BloodPropCreator().Create(locationX,locationY,propSpeedX,propSpeedY);
        }
        else if(x<0.8)
        {
            //System.out.println("bomb道具掉落");
            prop=new BombPropCreator().Create(locationX,locationY,propSpeedX,propSpeedY);
        }
        else
        {
            //System.out.println("bullet道具掉落");
            prop =new BulletPropCreator().Create(locationX,locationY,propSpeedX,propSpeedY);
        }
        return prop;
    }

}
