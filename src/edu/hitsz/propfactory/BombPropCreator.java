package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;
import edu.hitsz.Prop.BombProp;

public class BombPropCreator implements PropCreator
{
    public BaseProp Create(int locationX,int locationY,int propSpeedX,int propSpeedY)
    {
        return new BombProp(locationX,locationY,propSpeedX,propSpeedY);
    }

}
