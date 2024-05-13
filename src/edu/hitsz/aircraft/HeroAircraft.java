package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.shootStrategy.ShootStrategy;
import edu.hitsz.shootStrategy.straightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {


    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp,int shootNum,int power,int direction,int bulletspeed) {
        super(locationX, locationY, speedX, speedY, hp,shootNum,power,direction,bulletspeed);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }
    /*
    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    /*
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + this.getDirection()*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + this.getDirection()*this.getBulletspeed();
        BaseBullet bullet;
        for(int i=0; i<this.getShootNum(); i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i*2 - this.getShootNum() + 1)*10, y, speedX, speedY, this.getPower());
            res.add(bullet);
        }
        return res;
    }
    */
    /*
    private ShootStrategy strategy=new straightShoot();

    public ShootStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ShootStrategy strategy) {
        this.strategy = strategy;
    }
    */

    @Override
    public List<BaseBullet> shoot() {

        return this.getStrategy().ShootAction(this);
    }
    private static HeroAircraft myaircraft=new HeroAircraft(
             Main.WINDOW_WIDTH / 2,
             Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
             0, 0, 1000,1,30,-1,10);
    public static HeroAircraft getHeroAircraft(){
        return myaircraft;
    }

}
