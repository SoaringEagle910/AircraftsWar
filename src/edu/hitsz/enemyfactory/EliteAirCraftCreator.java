package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.EliteEnemy;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteAirCraftCreator implements EnemyAircraftCreator{
    public AbstractEnemyAircraft Create(){
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                60
        );
    }
}
