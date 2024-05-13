package edu.hitsz.aircraft;
import edu.hitsz.Prop.BaseProp;
import edu.hitsz.application.ImageManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.propfactory.ArcBulletPropCreator;
import edu.hitsz.propfactory.BloodPropCreator;
import edu.hitsz.propfactory.BombPropCreator;
import edu.hitsz.propfactory.CircleBulletPropCreator;

import java.util.LinkedList;
import java.util.List;


public abstract class AbstractEnemyAircraft extends AbstractAircraft{

    public AbstractEnemyAircraft(double locationX, double locationY, double speedX, double speedY, int hp,int shootNum,int power,int direction,int bulletspeed) {
        super(locationX, locationY, speedX, speedY,hp,shootNum,power,direction,bulletspeed);
    }
    public abstract List<BaseBullet> shoot();
    public abstract int getScore();
    private int propSpeedX=0,propSpeedY=4;
    public  List<BaseProp> dropProp() {
        int propnum=0;
        if(this.getClass()== MobEnemy.class)propnum=0;
        else if(this.getClass()== EliteEnemy.class||this.getClass()== ElitePlus.class)propnum=1;
        else if(this.getClass()== Boss.class)propnum=3;
        else System.out.println("dropprop wrong!");
        List<BaseProp>res=new LinkedList<>();
        int d= ImageManager.PROP_BLOOD_IMAGE.getWidth();
        for(int i=0;i<propnum;i++)
        {
            double random_num = Math.random();
            if (random_num < 0.2) {
                //System.out.println("无道具掉落");
            }
            else if(random_num<0.3){
                res.add(new CircleBulletPropCreator().Create((int)locationX+(i-propnum/2)*d, (int)locationY, propSpeedX, propSpeedY));
            }
            else if (random_num < 0.6) {
                //System.out.println("blood道具掉落");
                res.add(new BloodPropCreator().Create((int)locationX+(i-propnum/2)*d, (int)locationY, propSpeedX, propSpeedY));
            }
            else if (random_num < 0.8) {
                //System.out.println("bomb道具掉落");
                res.add(new BombPropCreator().Create((int)locationX+(i-propnum/2)*d, (int)locationY, propSpeedX, propSpeedY));
            }
            else {
                //System.out.println("bullet道具掉落");
                res.add(new ArcBulletPropCreator().Create((int)locationX+(i-propnum/2)*d, (int)locationY, propSpeedX, propSpeedY));
            }
        }
        return res;
    }
}
