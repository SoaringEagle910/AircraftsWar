package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.BloodProp;

public class BloodPropCreator implements PropCreator{
    public BaseProp Create(int locationX,int locationY,int propSpeedX,int propSpeedY)
    {
        return new BloodProp(locationX,locationY,propSpeedX,propSpeedY);
    }
}
