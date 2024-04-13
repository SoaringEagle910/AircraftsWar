package edu.hitsz.Prop;

public class BombProp extends BaseProp{
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    int power=20;
    public int Bomb()
    {
        return power;
    }
}
