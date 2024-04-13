package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.BulletProp;

public class BulletPropCreator implements PropCreator{
    public BaseProp Create(int locationX,int locationY,int propSpeedX,int propSpeedY)
    {
        return new BulletProp(locationX,locationY,propSpeedX,propSpeedY);
    }
}
