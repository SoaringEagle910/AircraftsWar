package edu.hitsz.application;


import edu.hitsz.Prop.BloodProp;
import edu.hitsz.Prop.BombProp;
import edu.hitsz.Prop.ArcBulletProp;
import edu.hitsz.Prop.CircleBulletProp;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.swingWindows.StartMenu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage Elite_ENEMY_IMAGE;
    public static BufferedImage ELITE_PLUS_IMAGE;
    public static BufferedImage BOSS_IMAGE;
    public static BufferedImage PROP_BLOOD_IMAGE;
    public static BufferedImage PROP_BOMB_IMAGE;
    public static BufferedImage PROP_ARC_BULLET_IMAGE;
    public static BufferedImage PROP_CIRCLE_BULLET_IMAGE;

    static {
        try {
            if(StartMenu.difficulty==1){
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            }
            else if(StartMenu.difficulty==2){
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            }
            else if(StartMenu.difficulty==3){
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
            }
            else {
                System.out.println(StartMenu.difficulty);
            }



            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            Elite_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITE_PLUS_IMAGE=ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            BOSS_IMAGE=ImageIO.read(new FileInputStream("src/images/boss.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            PROP_BLOOD_IMAGE=ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            PROP_BOMB_IMAGE=ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            PROP_ARC_BULLET_IMAGE=ImageIO.read(new FileInputStream("src/images/prop_arc_bullet.png"));
            PROP_CIRCLE_BULLET_IMAGE=ImageIO.read(new FileInputStream("src/images/prop_circle_bullet.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), Elite_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlus.class.getName(),ELITE_PLUS_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Boss.class.getName(),BOSS_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BloodProp.class.getName(),PROP_BLOOD_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(),PROP_BOMB_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ArcBulletProp.class.getName(),PROP_ARC_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(CircleBulletProp.class.getName(),PROP_CIRCLE_BULLET_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
