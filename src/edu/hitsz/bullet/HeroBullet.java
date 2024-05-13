package edu.hitsz.bullet;

/**
 * @Author hitsz
 */
public class HeroBullet extends BaseBullet {
    //private BaseMusicThread bulletMusicThread=new BaseMusicThread("src/videos/bullet_hit.wav");

    public HeroBullet(double locationX, double locationY, double speedX, double speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }
/*
    @Override
    public void vanish() {
        isValid = false;
        System.out.println("A");
        bulletMusicThread.start();
        System.out.println("B");
    }

 */



}
