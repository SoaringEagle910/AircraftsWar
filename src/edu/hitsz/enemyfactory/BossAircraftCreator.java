package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.Boss;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossAircraftCreator implements EnemyAircraftCreator{
    public Boss Create(){

        return new Boss(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                2,
                0,
                200,
                20,
                30,
                1,
                3
        );
    }
}
