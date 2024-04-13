package edu.hitsz.Prop;

public class BloodProp extends BaseProp{
    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    private int Blood=20;
    public int AddBlood()
    {
        return Blood;
    }
}
