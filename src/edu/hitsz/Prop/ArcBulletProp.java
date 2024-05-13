package edu.hitsz.Prop;

import edu.hitsz.Thread.ShootStrategyThread;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.shootStrategy.arcShoot;

public class ArcBulletProp extends BaseProp{
    public ArcBulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void valid()
    {
        ShootStrategyThread shootStrategyThread=new ShootStrategyThread();
        shootStrategyThread.setStrategy("arc");
        shootStrategyThread.start();
        getSupplyMusic();
        return;
    }
}
