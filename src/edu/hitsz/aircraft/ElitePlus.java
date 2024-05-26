package edu.hitsz.aircraft;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.PropObserver;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.propfactory.BloodPropCreator;
import edu.hitsz.propfactory.BombPropCreator;
import edu.hitsz.propfactory.ArcBulletPropCreator;
import edu.hitsz.shootStrategy.ShootStrategy;
import edu.hitsz.shootStrategy.arcShoot;

import java.util.LinkedList;
import java.util.List;

public class ElitePlus extends AbstractEnemyAircraft implements PropObserver {
    public ElitePlus(int locationX, int locationY, int speedX, int speedY, int hp,int shootNum,int power,int direction,int bulletspeed) {
        super(locationX, locationY, speedX, speedY, hp,shootNum,power,direction,bulletspeed);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
        if(locationX==Main.WINDOW_WIDTH)this.speedX=-this.speedX;
        else if(locationX==0)this.speedX=-this.speedX;
    }

    private int score=20;

    //private ShootStrategy strategy=new arcShoot();
    @Override
    public List<BaseBullet> shoot() {
        this.setStrategy(new arcShoot());
        return this.getStrategy().ShootAction(this);
    }


    public int getScore()
    {
        return score;
    }

    @Override
    public void update(int power){
        this.addHp(-power);
    }


}
