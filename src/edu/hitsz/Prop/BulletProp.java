package edu.hitsz.Prop;

public class BulletProp extends BaseProp{
    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    int number=1;
    public int AddBullet()
    {
        return number;
    }
}
