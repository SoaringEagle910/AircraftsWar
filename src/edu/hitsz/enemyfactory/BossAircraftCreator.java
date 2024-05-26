package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.Boss;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossAircraftCreator implements EnemyAircraftCreator{
    public static int bossHp=10000;

    @Override
    public Boss Create(){
        System.out.println("Boss血量："+bossHp);

        return new Boss(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                1,
                0,
                bossHp,
                20,
                30,
                1,
                1
        );
    }
}
