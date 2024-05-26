package edu.hitsz.aircraft;

import edu.hitsz.Prop.PropObserver;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.Thread.AlwaysMusicThread;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.shootStrategy.circleShoot;

import java.util.List;

public class Boss extends AbstractEnemyAircraft implements PropObserver {
    public  AlwaysMusicThread bossMusicThread=new AlwaysMusicThread("src/videos/bgm_boss.wav");
    public Boss(double locationX, double locationY, double speedX, double speedY, int hp,int shootNum,int power,int direction,int bulletspeed) {
        super(locationX, locationY, speedX, speedY, hp,shootNum,power,direction,bulletspeed);
        if(Game.getMusic_on()==1){
            bossMusicThread.start();
        }
    }

    @Override
    public void forward() {
        super.forward();
        if(locationX==Main.WINDOW_WIDTH)this.speedX=-this.speedX;
        else if(locationX==0)this.speedX=-this.speedX;
    }
    public void musicStop(){
        bossMusicThread.setStop(true);
    }



    private int score=25;


    //private ShootStrategy strategy=new circleShoot();

    @Override
    public List<BaseBullet> shoot() {
        this.setStrategy(new circleShoot());
        return this.getStrategy().ShootAction(this);
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public void vanish() {
        bossMusicThread.setStop(true);
        isValid = false;
    }

    @Override
    public void update(int power){
        return;
    }




}
