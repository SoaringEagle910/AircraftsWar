package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.EliteEnemy;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteAircraftCreator implements EnemyAircraftCreator{
    public AbstractEnemyAircraft Create(){
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.Elite_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                5,
                90,
                1,
                30,
                1,
                8
        );
    }
}
