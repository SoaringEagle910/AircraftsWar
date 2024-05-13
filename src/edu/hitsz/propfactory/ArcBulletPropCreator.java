package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.ArcBulletProp;

public class ArcBulletPropCreator implements PropCreator{
    public BaseProp Create(int locationX,int locationY,int propSpeedX,int propSpeedY)
    {
        return new ArcBulletProp(locationX,locationY,propSpeedX,propSpeedY);
    }
}
