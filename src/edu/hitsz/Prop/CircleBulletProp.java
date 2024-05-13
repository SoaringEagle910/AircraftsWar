package edu.hitsz.Prop;

import edu.hitsz.Thread.ShootStrategyThread;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shootStrategy.circleShoot;

public class CircleBulletProp extends BaseProp{
    public CircleBulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void valid(){
        ShootStrategyThread shootStrategyThread=new ShootStrategyThread();
        shootStrategyThread.setStrategy("circle");
        shootStrategyThread.start();
        getSupplyMusic();
    }
}