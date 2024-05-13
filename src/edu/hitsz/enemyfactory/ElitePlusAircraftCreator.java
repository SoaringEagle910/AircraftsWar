package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.ElitePlus;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class ElitePlusAircraftCreator implements EnemyAircraftCreator{
    public AbstractEnemyAircraft Create(){
        return new ElitePlus(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_PLUS_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                3,
                4,
                60,
                3,
                30,
                1,
                10
        );
    }
}
