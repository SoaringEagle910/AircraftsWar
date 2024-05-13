package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.shootStrategy.ShootStrategy;
import edu.hitsz.shootStrategy.straightShoot;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    private int shootNum;
    private int power;
    private int direction;
    private int bulletspeed;

    public AbstractAircraft(double locationX, double locationY, double speedX, double speedY, int hp,int shootNum,int power,int direction,int bulletspeed) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootNum=shootNum;
        this.power=power;
        this.direction=direction;
        this.bulletspeed=bulletspeed;
    }

    private ShootStrategy strategy=new straightShoot();

    public ShootStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ShootStrategy strategy) {
        this.strategy = strategy;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public int getHp() {
        return hp;
    }

    public void addHp(int hp)
    {
        this.hp+=hp;
        if(this.hp>maxHp)this.hp=maxHp;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    public int getShootNum() {
        return shootNum;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getBulletspeed() {
        return bulletspeed;
    }

    public void setBulletspeed(int bulletspeed) {
        this.bulletspeed = bulletspeed;
    }

    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();

}


