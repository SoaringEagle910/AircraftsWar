package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.ElitePlus;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class ElitePlusAircraftCreator implements EnemyAircraftCreator{
    public static int elitePlusHp=600;
    @Override
    public ElitePlus Create(){
        return new ElitePlus(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_PLUS_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                3,
                1,
                elitePlusHp,
                3,
                30,
                1,
                3
        );
    }
}
