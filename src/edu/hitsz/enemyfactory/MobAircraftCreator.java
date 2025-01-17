package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobAircraftCreator implements EnemyAircraftCreator{
    public MobAircraftCreator()
    {
        return;
    }
    public static int mobHp=30;
    @Override
    public MobEnemy Create() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                4,
                mobHp,
                0,
                0,
                0,
                0
        );
    }
}
