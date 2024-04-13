package edu.hitsz.propfactory;

import edu.hitsz.Prop.BaseProp;

public interface PropCreator {
    public BaseProp Create(int locationX,int locationY,int propSpeedX,int propSpeedY);
}
