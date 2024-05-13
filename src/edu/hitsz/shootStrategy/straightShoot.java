package edu.hitsz.shootStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class straightShoot implements ShootStrategy{
    @Override
    public List<BaseBullet> ShootAction(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        double x = aircraft.getLocationX();
        double y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        double speedX = 0;
        double speedY = aircraft.getSpeedY() + aircraft.getDirection() * 5;
        BaseBullet bullet;
        if(aircraft.getClass()==HeroAircraft.class){
            for (int i = 0; i < aircraft.getShootNum(); i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet(x + (i * 2 - aircraft.getShootNum() + 1) * 10, y, speedX, speedY*2, aircraft.getPower());
                res.add(bullet);
            }
        }
        else {
            for (int i = 0; i < aircraft.getShootNum(); i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet(x + (i * 2 - aircraft.getShootNum() + 1) * 10, y, speedX, speedY, aircraft.getPower());
                res.add(bullet);
            }
        }
        return res;
    }
}
