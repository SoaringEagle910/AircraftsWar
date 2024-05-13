package edu.hitsz.Prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BloodProp extends BaseProp{
    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void valid(){
        HeroAircraft.getHeroAircraft().addHp(20);
        getSupplyMusic();
        return;
    }
}
