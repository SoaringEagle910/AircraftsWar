package edu.hitsz.application;

public class NormalGame extends Game{
    public NormalGame()
    {
        super();
    }
    @Override
    public void setDifficluty()
    {
        super.enemyshootT=100;
        super.enemycreateT=20;
        super.enemyMaxNumber=4;
        super.bossMode=true;
        super.moreDifficulty=true;
        super.greaterBoss=false;
        super.heroshootT=3;
        ELITE_POSIBILITY=0.5;
        ELITE_PLUS_POSIBILITY=0.1;
        scoreCreateBoss=200;
    }
}
