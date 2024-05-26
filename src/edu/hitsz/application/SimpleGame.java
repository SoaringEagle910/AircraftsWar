package edu.hitsz.application;

public class SimpleGame extends Game{
    public SimpleGame()
    {
        super();
    }
    @Override
    public void setDifficluty()
    {
        super.enemyshootT=50;
        super.enemycreateT=10;
        super.enemyMaxNumber=1;
        super.bossMode=false;
        super.moreDifficulty=false;
        super.greaterBoss=false;
        heroshootT=5;
        ELITE_POSIBILITY=0.5;
        ELITE_PLUS_POSIBILITY=0.2;
        scoreCreateBoss=100;
    }

}
