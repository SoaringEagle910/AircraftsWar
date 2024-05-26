package edu.hitsz.shootStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class circleShoot implements ShootStrategy{
    public List<BaseBullet> ShootAction(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        double x = aircraft.getLocationX();
        double y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        double speedX,speedY;
        BaseBullet bullet;
        double PI=3.14159;
        double theta=0;
        double R = 50;
        if(aircraft.getClass()== HeroAircraft.class)
        {
            double v=5;
            for (int i = 0; i < aircraft.getShootNum(); i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                theta=2*PI/aircraft.getShootNum()*i;
                speedX=(v*Math.cos(theta));
                speedY=(v*Math.sin(theta));
                bullet = new HeroBullet(x + (R*Math.cos(theta)), y+(R*Math.sin(theta)), speedX*2, speedY*2, aircraft.getPower());
                res.add(bullet);
            }
        }
        else
        {
            double v=2;
            for (int i = 0; i < aircraft.getShootNum(); i++) {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                theta=2*PI/aircraft.getShootNum()*i;
                speedX=(v*Math.cos(theta));
                speedY=(v*Math.sin(theta));
                bullet = new EnemyBullet(x + (R*Math.cos(theta)), y+(R*Math.sin(theta)), speedX, speedY, aircraft.getPower());
                res.add(bullet);
            }
        }

        return res;
    }
}