package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.EliteEnemy;

import edu.hitsz.aircraft.ElitePlus;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteAircraftCreator implements EnemyAircraftCreator{
    public static int eliteHp=90;
    @Override
    public EliteEnemy Create(){
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.Elite_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                2,
                eliteHp,
                1,
                30,
                1,
                3
        );
    }
}
