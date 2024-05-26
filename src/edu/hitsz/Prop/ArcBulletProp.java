package edu.hitsz.Prop;

import edu.hitsz.Thread.ShootStrategyThread;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.shootStrategy.arcShoot;
import edu.hitsz.swingWindows.StartMenu;

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
        if(StartMenu.difficulty==3){
            HeroAircraft.getHeroAircraft().setPower(HeroAircraft.getHeroAircraft().getPower()+1);
        }
        else if(StartMenu.difficulty==2){
            HeroAircraft.getHeroAircraft().setPower(HeroAircraft.getHeroAircraft().getPower()+1);
        }

        return;
    }
}
