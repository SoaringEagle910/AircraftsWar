package edu.hitsz.Prop;

import edu.hitsz.Thread.BaseMusicThread;
import edu.hitsz.aircraft.AbstractEnemyAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;
import java.util.List;



public class BombProp extends BaseProp{

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    public void valid(){
        if(Game.getMusic_on()==1){
            BaseMusicThread bombMusicThread=new BaseMusicThread("src/videos/bomb_explosion.wav");
            bombMusicThread.start();
            getSupplyMusic();
        }
        BombValid.bombWork(30);
        return;
    }
}
