package edu.hitsz.Thread;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shootStrategy.arcShoot;
import edu.hitsz.shootStrategy.circleShoot;
import edu.hitsz.shootStrategy.straightShoot;

public class ShootStrategyThread extends Thread{
    private String strategy;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public ShootStrategyThread(){
        super();
        this.strategy=strategy;
    }

    @Override
    public void run(){
        if(strategy=="arc"){
            HeroAircraft.getHeroAircraft().setShootNum(5);
            HeroAircraft.getHeroAircraft().setStrategy(new arcShoot());
        }
        else if(strategy=="circle"){
            HeroAircraft.getHeroAircraft().setShootNum(20);
            HeroAircraft.getHeroAircraft().setStrategy(new circleShoot());
        }
        try{
            Thread.sleep(3000); // 休眠3秒
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        HeroAircraft.getHeroAircraft().setShootNum(1);
        HeroAircraft.getHeroAircraft().setStrategy(new straightShoot());
    }


}
