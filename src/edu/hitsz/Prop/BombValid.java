package edu.hitsz.Prop;

import java.util.ArrayList;
import java.util.List;

public class BombValid {
    static private List<PropObserver> bombObservers =new ArrayList<>();

    public static List<PropObserver> getBombObservers() {
        return bombObservers;
    }

    public static void addObserver(PropObserver bombObserver){
        bombObservers.add(bombObserver);
    }
    public static void removeObserver(PropObserver bombObserver)
    {
        bombObservers.remove(bombObserver);
    }
    public static void nodifyAll(int power){
        for (PropObserver bombObserver:bombObservers){
            bombObserver.update(power);
        }
    }
    public static void bombWork(int power){
        nodifyAll(power);
    }
}
