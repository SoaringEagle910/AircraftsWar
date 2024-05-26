package edu.hitsz.application;

public class HardGame extends Game{
    public HardGame()
    {
        super();
    }
    @Override
    public void setDifficluty()
    {
        super.enemyshootT=200;
        super.enemycreateT=20;
        super.enemyMaxNumber=6;
        super.bossMode=true;
        super.moreDifficulty=true;
        super.greaterBoss=true;
        super.heroshootT=1;
        ELITE_POSIBILITY=0.5;
        ELITE_PLUS_POSIBILITY=0.2;
    }
}
