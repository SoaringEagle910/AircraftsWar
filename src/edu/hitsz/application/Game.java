package edu.hitsz.application;

import edu.hitsz.Prop.*;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.enemyfactory.BossAircraftCreator;
import edu.hitsz.enemyfactory.EliteAircraftCreator;
import edu.hitsz.enemyfactory.ElitePlusAircraftCreator;
import edu.hitsz.enemyfactory.MobAircraftCreator;
import edu.hitsz.Score.ShowScoreBoard;
import edu.hitsz.Thread.AlwaysMusicThread;
import edu.hitsz.Thread.BaseMusicThread;
import edu.hitsz.swingWindows.ScoreBoard;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;



/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {
    //游戏难度
    protected int enemyshootT;
    protected int enemycreateT;
    protected int enemyMaxNumber;
    public int heroshootT=1;
    protected boolean bossMode=true;
    protected boolean moreDifficulty=true;
    protected boolean greaterBoss=true;
    protected int scoreCreateBoss=200;




    private int enemyshootcount=0;
    private int enemycreatecount=0;
    private int heroshootcount=0;
    private AlwaysMusicThread bgMusicThread = new AlwaysMusicThread("src/videos/bgm.wav");
    private BaseMusicThread endMusicThread = new BaseMusicThread("src/videos/game_over.wav");
    //精英敌机产生的概率
    public static double ELITE_POSIBILITY=0.5;
    public static double ELITE_PLUS_POSIBILITY=0.2;
    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 15;

    private final HeroAircraft heroAircraft;
    public static List<AbstractEnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<BaseProp> props;

    /**
     * 屏幕中出现的敌机最大数量
     */

    /**
     * 当前得分
     */
    public static int score = 0;
    private int prescore=0;
    /**
     * 当前时刻
     */
    private int time = 0;
    private int preTime=0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 5;
    private int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;
    private static int music_on;


    public static int getMusic_on() {
        return music_on;
    }

    public void setMusic_on(int music_on) {
        this.music_on = music_on;
    }

    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props=new LinkedList<>();

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */

    public void action() {
        //背景音效
        // 创建一个MusicThread实例并传入音频文件名

        if(music_on==1){
            // 开启MusicThread线程
            bgMusicThread.start();
        }

        setDifficluty();


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                //System.out.println(time);
                // 新敌机产生
                createNewEnemy();
                // 飞机射出子弹
                if(enemyshootcount<enemyshootT)enemyshootcount=enemyshootcount+1;
                else {
                    enemyshootcount=0;
                    enemyShootAction();
                }
                if(heroshootcount<heroshootT)heroshootcount+=1;
                else {
                    heroshootcount=0;
                    heroShootAction();
                }
            }


            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                if(music_on==1) {
                    bgMusicThread.setStop(true);
                }

                executorService.shutdown();
                gameOverFlag = true;

                System.out.println("Game Over!");
                for(AbstractEnemyAircraft enemyAircraft:enemyAircrafts){
                    if(enemyAircraft.getClass()==Boss.class){
                        ((Boss) enemyAircraft).musicStop();
                    }
                }
                if(music_on==1){
                    endMusicThread.start();
                }
                ScoreBoard scoreBoard=new ScoreBoard();
                Main.cardPanel.add(scoreBoard.getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
                scoreBoard.getName();

            }

            //refreshDifficulty
            if(moreDifficulty&&time%1000==0&&time>0){
                enemyshootT-=1;
                enemycreateT-=1;
                if(enemyshootT==0)enemyshootT=1;
                if(enemycreateT==0)enemycreateT=1;
                //enemyMaxNumber+=1;
                EliteAircraftCreator.eliteHp*=((time+1500)/(double)time);
                ElitePlusAircraftCreator.elitePlusHp*=((time+2000)/(double)time);
                MobAircraftCreator.mobHp*=((time+1000)/(double)time);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("难度提升");
                System.out.println("敌机射击周期："+enemyshootT);
                System.out.println("敌机产生周期："+enemycreateT);
                System.out.println("普通敌机血量："+MobAircraftCreator.mobHp);
                System.out.println("精英敌机血量："+EliteAircraftCreator.eliteHp);
                System.out.println("超级精英敌机血量："+ElitePlusAircraftCreator.elitePlusHp);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            /*
            if(moreDifficulty){
                EliteAircraftCreator.eliteHp=(int)(time/3000*Math.log(time)*Math.log(time));
                ElitePlusAircraftCreator.elitePlusHp=(int)(time/2000*Math.log(time)*Math.log(time));
                MobAircraftCreator.mobHp=(int)(time/5000*Math.log(time)*Math.log(time));
            }

             */
            /*
            if(greaterBoss&&score/200!=prescore/200)
            {
                BossAircraftCreator.bossHp+=(int)(Math.log(BossAircraftCreator.bossHp)/Math.log(2));
            }

             */
            if(greaterBoss)
            {
                BossAircraftCreator.bossHp=(int)(time/200*Math.log(time)*Math.log(time));
            }

            preTime=time;

        };


        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************
    public void setDifficluty()
    {
        enemyshootT=200;
        enemycreateT=20;
        enemyMaxNumber=5;
        bossMode=true;
        moreDifficulty=true;
        greaterBoss=true;
        heroshootT=1;
        ELITE_POSIBILITY=0.5;
        ELITE_PLUS_POSIBILITY=0.2;
        scoreCreateBoss=200;
    }

    //新敌机
    private void createNewEnemy(){
        if(enemycreatecount<enemycreateT)enemycreatecount=enemycreatecount+1;
        else{
            enemycreatecount=0;
            double random_num=Math.random();
            if (enemyAircrafts.size() < enemyMaxNumber) {
                if(random_num<ELITE_PLUS_POSIBILITY){
                    ElitePlus elitePlus=new ElitePlusAircraftCreator().Create();
                    enemyAircrafts.add(elitePlus);
                    BombValid.addObserver(elitePlus);
                }
                else if((random_num-ELITE_PLUS_POSIBILITY)/(1-ELITE_PLUS_POSIBILITY)<ELITE_POSIBILITY){
                    EliteEnemy eliteEnemy=new EliteAircraftCreator().Create();
                    enemyAircrafts.add(eliteEnemy);
                    BombValid.addObserver(eliteEnemy);
                }
                else {
                    MobEnemy mobEnemy=new MobAircraftCreator().Create();
                    enemyAircrafts.add(mobEnemy);
                    BombValid.addObserver(mobEnemy);
                }

            }
        }
        if(bossMode){
            boolean have_boss=false;
            for(AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                // 在这里对每个敌机执行操作
                if(enemyAircraft.getClass()==Boss.class){
                    have_boss=true;
                }
            }

            if(score>0&&prescore/scoreCreateBoss!=score/scoreCreateBoss&&!have_boss)
            {
                Boss boss=new BossAircraftCreator().Create();
                enemyAircrafts.add(boss);
                BombValid.addObserver(boss);
            }

            prescore=score;
        }
    }

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void heroShootAction(){
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }
    private void enemyShootAction() {
        // TODO 敌机射击
        for(AbstractEnemyAircraft enemyAircraft : enemyAircrafts)
        {
            enemyBullets.addAll(enemyAircraft.shoot());
        }

    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction()
    {
        for(BaseProp prop:props)
        {
            prop.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到英雄机子弹
                // 英雄机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    if(music_on==1){
                        BaseMusicThread bulletMusicThread=new BaseMusicThread("src/videos/bullet_hit.wav");
                        bulletMusicThread.start();
                    }

                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score+=enemyAircraft.getScore();
                        props.addAll(enemyAircraft.dropProp());
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);

                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (BaseProp prop : props)
        {
            if(prop.crash(heroAircraft)||heroAircraft.crash(prop))
            {
                prop.vanish();
                prop.valid();//道具生效
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        for (BaseBullet baseBullet:enemyBullets){
            if(baseBullet.notValid()){
                BombValid.removeObserver(baseBullet);
            }
        }
        for(AbstractEnemyAircraft enemyAircraft:enemyAircrafts){
            if(enemyAircraft.notValid()){
                BombValid.removeObserver(enemyAircraft);
            }
        }
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);

    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    int preHp=HeroAircraft.getHeroAircraft().getMaxHp();
    int redCounter=0,redT=5;
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, (int)heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                (int)heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);
        paintHpBar(g);


        if(preHp!=HeroAircraft.getHeroAircraft().getHp()){
            redCounter=1;
        }
        if(redCounter!=0)redCounter=redCounter+1;
        if(redCounter==redT)redCounter=0;
        if(redCounter!=0){
            // 设置颜色为半透明的红色
            g.setColor(new Color(255, 0, 0, 128));

            // 绘制一个矩形来填充整个画面
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        preHp=HeroAircraft.getHeroAircraft().getHp();


    }


    /*
    double theta=Math.atan((double)(HeroAircraft.getHeroAircraft().getLocationX()-object.getLocationX())/(HeroAircraft.getHeroAircraft().getLocationY()-object.getLocationY()));
                System.out.println(theta);
    image=ImageManager.rotateImage(image,theta);


     */
    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            if(object.getClass()==ElitePlus.class){
                double theta;
                double a=(HeroAircraft.getHeroAircraft().getLocationX()-object.getLocationX());
                double b=(HeroAircraft.getHeroAircraft().getLocationY()-object.getLocationY());
                double c=Math.sqrt(a*a+b*b);
                theta=-Math.asin(a/c);
                if(b<0)theta=-theta+210;

                //TODO image旋转theta度
                image = rotateImage(image, Math.toDegrees(theta));
            }
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, (int)object.getLocationX() - image.getWidth() / 2,
                    (int)object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private BufferedImage rotateImage(BufferedImage image, double angle) {
        int width = image.getWidth();
        int height = image.getHeight();
        //BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());
        BufferedImage rotatedImage = new BufferedImage(width,height,image.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.rotate(Math.toRadians(angle), width / 2, height / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
    }



    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);

    }
    private void paintHpBar(Graphics g) {
        int heroBarX = (int)heroAircraft.getLocationX() - 50; // 横坐标
        int heroBarY = (int)heroAircraft.getLocationY() + heroAircraft.getHeight()/2; // 纵坐标
        int width = 100; // 长方形的宽度
        int height = 10; // 长方形的高度
        int hpPercentage = (int) (heroAircraft.getHp() / (double) heroAircraft.getMaxHp() * 100); // 血量百分比
        int currentWidth = (int) (width * hpPercentage / 100); // 根据血量百分比计算当前长方形的宽度

        g.setColor(Color.GREEN); // 设置颜色为红色
        g.fillRect(heroBarX, heroBarY, currentWidth, height); // 绘制血量条
        g.setColor(Color.BLACK); // 设置颜色为黑色
        g.drawRect(heroBarX, heroBarY, width, height); // 绘制边框

        for(AbstractEnemyAircraft enemyAircraft:enemyAircrafts){
            int enemyBarX = (int)enemyAircraft.getLocationX() - 50; // 横坐标
            int enemyBarY = (int)enemyAircraft.getLocationY() + enemyAircraft.getHeight()/2; // 纵坐标
            int enemyHpPercentage = (int) (enemyAircraft.getHp() / (double) enemyAircraft.getMaxHp() * 100); // 血量百分比
            int enemyCurrentWidth = (int) (width * enemyHpPercentage / 100); // 根据血量百分比计算当前长方形的宽度

            g.setColor(Color.RED); // 设置颜色为红色
            g.fillRect(enemyBarX, enemyBarY, enemyCurrentWidth, height); // 绘制血量条
            g.setColor(Color.BLACK); // 设置颜色为黑色
            g.drawRect(enemyBarX, enemyBarY, width, height); // 绘制边框
        }
    }


}
