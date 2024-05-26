package edu.hitsz.shootStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class arcShoot implements ShootStrategy{
    public List<BaseBullet> ShootAction(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        double x = aircraft.getLocationX();
        double y = aircraft.getLocationY() + aircraft.getDirection()*2;
        double speedX = 0;
        BaseBullet bullet;
        if(aircraft.getClass()== HeroAircraft.class)
        {
            double speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
            for(int i=0; i<aircraft.getShootNum(); i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet(x + (i*2 - aircraft.getShootNum() + 1)*10, y, speedX+(i-aircraft.getShootNum()/2)*3, speedY*2, aircraft.getPower());
                res.add(bullet);
            }
        }
        else
        {
            double speed=2;
            //double theta=Math.atan((double)(HeroAircraft.getHeroAircraft().getLocationX()-aircraft.getLocationX())/(HeroAircraft.getHeroAircraft().getLocationY()-aircraft.getLocationY()));

            double theta;
            double a=(HeroAircraft.getHeroAircraft().getLocationX()-aircraft.getLocationX());
            double b=(HeroAircraft.getHeroAircraft().getLocationY()-aircraft.getLocationY());
            double c=Math.sqrt(a*a+b*b);
            theta=Math.asin(a/c);
            if(b<0)theta=-theta+210;


            for(int i=0; i<aircraft.getShootNum(); i++){
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                //System.out.println("theta="+theta+" sppedx="+speed*Math.sin(theta+(i-aircraft.getShootNum()/2)*0.2)+"speedy="+speed*Math.cos(theta+(i-aircraft.getShootNum()/2)*0.2));

                bullet = new EnemyBullet(x + (i*2 - aircraft.getShootNum() + 1)*10, y, speed*Math.sin(theta+(i-aircraft.getShootNum()/2)*0.2), speed*Math.cos(theta+(i-aircraft.getShootNum()/2)*0.2), aircraft.getPower());
                res.add(bullet);
            }
        }

        return res;
    }
}
