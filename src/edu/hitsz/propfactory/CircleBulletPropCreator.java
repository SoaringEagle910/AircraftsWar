package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.CircleBulletProp;

public class CircleBulletPropCreator implements PropCreator{
    public BaseProp Create(int locationX, int locationY, int propSpeedX, int propSpeedY)
    {
        return new CircleBulletProp(locationX,locationY,propSpeedX,propSpeedY);
    }
}